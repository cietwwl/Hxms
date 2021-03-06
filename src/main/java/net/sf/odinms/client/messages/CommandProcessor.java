/*
 This file is part of the OdinMS Maple Story Server
 Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc> 
 Matthias Butz <matze@odinms.de>
 Jan Christian Meyer <vimes@odinms.de>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License version 3
 as published by the Free Software Foundation. You may not use, modify
 or distribute this program under any other version of the
 GNU Affero General Public License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.odinms.client.messages;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.odinms.client.MapleCharacter;
import net.sf.odinms.client.MapleClient;
import net.sf.odinms.database.DatabaseConnection;
import net.sf.odinms.server.TimerManager;
import net.sf.odinms.tools.ClassFinder;
import net.sf.odinms.tools.Pair;
import net.sf.odinms.tools.StringUtil;
import org.apache.log4j.Logger;

public final class CommandProcessor implements CommandProcessorMBean {

	private static final Logger log = Logger.getLogger(CommandProcessor.class);
	private static final List<Pair<Integer, String>> gmlog = new LinkedList<Pair<Integer, String>>();
	private static final Runnable persister = new PersistingTask();
	private static CommandProcessor instance = new CommandProcessor();
	private Map<String, DefinitionCommandPair> commands = new LinkedHashMap<String, DefinitionCommandPair>();
	private ScriptEngineFactory sef;

	static {
		TimerManager.getInstance().register(persister, 620000);
	}

	private CommandProcessor() {
		ScriptEngineManager sem = new ScriptEngineManager();
		sef = sem.getEngineByName("javascript").getFactory();

		reloadCommands();
	}

	public static class PersistingTask implements Runnable {

		@Override
		public void run() {
			synchronized (gmlog) {
				try {
					Connection con = DatabaseConnection.getConnection();
					PreparedStatement ps = con
							.prepareStatement("INSERT INTO gmlog (cid, command) VALUES (?, ?)");
					for (Pair<Integer, String> logentry : gmlog) {
						ps.setInt(1, logentry.getLeft());
						ps.setString(2, logentry.getRight());
						ps.addBatch();
					}
					ps.executeBatch();
					ps.close();
					con.close();
				} catch (SQLException e) {
					log.error("error persisting gmlog", e);
				}
				gmlog.clear();
			}
		}
	}

	public static void registerMBean() {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			mBeanServer.registerMBean(instance, new ObjectName(
					"net.sf.odinms.client.messages:name=CommandProcessor"));
		} catch (Exception e) {
			log.error("Error registering CommandProcessor MBean");
		}
	}

	public static String joinAfterString(String splitted[], String str) {
		for (int i = 1; i < splitted.length; i++) {
			if (splitted[i].equalsIgnoreCase(str) && i + 1 < splitted.length) {
				return StringUtil.joinStringFrom(splitted, i + 1);
			}
		}
		return null;
	}

	public static int getOptionalIntArg(String splitted[], int position, int def) {
		if (splitted.length > position) {
			try {
				return Integer.parseInt(splitted[position]);
			} catch (NumberFormatException nfe) {
				return def;
			}
		}
		return def;
	}

	public static String getNamedArg(String splitted[], int startpos,
			String name) {
		for (int i = startpos; i < splitted.length; i++) {
			if (splitted[i].equalsIgnoreCase(name) && i + 1 < splitted.length) {
				return splitted[i + 1];
			}
		}
		return null;
	}

	public static Integer getNamedIntArg(String splitted[], int startpos,
			String name) {
		String arg = getNamedArg(splitted, startpos, name);
		if (arg != null) {
			try {
				return Integer.parseInt(arg);
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static int getNamedIntArg(String splitted[], int startpos,
			String name, int def) {
		Integer ret = getNamedIntArg(splitted, startpos, name);
		if (ret == null) {
			return def;
		}
		return ret.intValue();
	}

	public static Double getNamedDoubleArg(String splitted[], int startpos,
			String name) {
		String arg = getNamedArg(splitted, startpos, name);
		if (arg != null) {
			try {
				return Double.parseDouble(arg);
			} catch (Exception e) {
				// swallow - we don't really care
			}
		}
		return null;
	}

	public boolean processCommand(MapleClient c, String line) {
		return instance.processCommandInternal(c,
				new ServerNoticeMapleClientMessageCallback(c), c.getPlayer()
						.getGm(), line);
	}

	public static void forcePersisting() {
		persister.run();
	}

	public static CommandProcessor getInstance() {
		return instance;
	}

	public void reloadCommands() {
		commands.clear();
		try {
			ClassFinder classFinder = new ClassFinder();
			String[] classes = classFinder.listClasses(
					"net.sf.odinms.client.messages.commands", true);
			for (String clazz : classes) {
				Class<?> clasz = Class.forName(clazz);
				if (Command.class.isAssignableFrom(clasz)) {
					try {
						Command newInstance = (Command) clasz.newInstance();
						registerCommand(newInstance);
					} catch (Exception e) {
						log.error("ERROR INSTANCIATING COMMAND CLASS", e);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			log.error("THROW", e);
		}
		File scriptFolder = new File("scripts/command");
		for (File file : scriptFolder.listFiles()) {
			if (file.isFile() && file.canRead()) {
				FileReader fr = null;
				try {
					/*
					 * ScriptEngine command = sef.getScriptEngine(); fr = new
					 * FileReader(file); CompiledScript compiled = ((Compilable)
					 * command).compile(fr); compiled.eval(); Command c =
					 * ((Invocable) command).getInterface(Command.class);
					 * registerCommand(c);
					 */
					ScriptCommand scriptCommand = ScriptCommand
							.getScriptCommand(file);
					if (scriptCommand != null) {
						registerCommand(scriptCommand);
					}
				} catch (ScriptException e) {
					log.error(String.format("脚本：%s 运行异常：%s", file.getName(),
							e.getMessage()));
				} catch (IOException e) {
					log.error(String.format("脚本：%s 文件处理异常：%s", file.getName(),
							e.getMessage()));
				} finally {
					if (fr != null) {
						try {
							fr.close();
						} catch (IOException e) {
							log.error("文件关闭异常：", e);
						}
					}
				}
			}
		}
	}

	private void registerCommand(Command command) {
		CommandDefinition[] definition = command.getDefinition();
		for (CommandDefinition def : definition) {
			commands.put(def.getCommand().toLowerCase(),
					new DefinitionCommandPair(command, def));
		}
	}

	public void dropHelp(MapleCharacter chr, MessageCallback mc) {
		List<DefinitionCommandPair> allCommands = new ArrayList<DefinitionCommandPair>(
				commands.values());
		for (DefinitionCommandPair definitionCommandPair : allCommands) {
			if (chr.hasGMLevel(definitionCommandPair.getDefinition()
					.getRequiredLevel())) {
				dropHelpForDefinition(mc, definitionCommandPair.getDefinition());
			}
		}
	}

	private void dropHelpForDefinition(MessageCallback mc,
			CommandDefinition commandDefinition) {
		mc.dropMessage(commandDefinition.getCommand() + " "
				+ commandDefinition.getParameterDescription() + ": "
				+ commandDefinition.getHelp());
	}

	private boolean processCommandInternal(MapleClient c, MessageCallback mc,
			int gmLevel, String line) {
		MapleCharacter player = c.getPlayer();
		if (line.charAt(0) == '!' || line.charAt(0) == '@') {
			byte type;
			if (line.charAt(0) == '!' && c.getPlayer().isGM()
					|| line.charAt(0) == '@') {
				type = 0;
			} else {
				type = 1;
			}
			String[] splitted = line.split(" ");
			splitted[0] = splitted[0].toLowerCase();
			if (splitted.length > 0 && splitted[0].length() > 1) {
				DefinitionCommandPair definitionCommandPair = commands
						.get(splitted[0].substring(1));
				if (definitionCommandPair != null
						&& gmLevel >= definitionCommandPair.getDefinition()
								.getRequiredLevel()) {
					if (type == 0) {
						if (definitionCommandPair.getDefinition()
								.getRequiredLevel() > 0) {
							synchronized (gmlog) {
								gmlog.add(new Pair<Integer, String>(player
										.getId(), line));
							}
						}
					}
					try {
						definitionCommandPair.getCommand().execute(c, mc,
								splitted);
					} catch (IllegalCommandSyntaxException e) {
						mc.dropMessage("异常:" + e.getMessage());
						dropHelpForDefinition(mc,
								definitionCommandPair.getDefinition());
					} catch (Exception e) {
						mc.dropMessage("错误: " + e.getClass().getName() + " "
								+ e.getMessage());
						log.error("COMMAND ERROR", e);
					}
					return true;
				} else {
					if (definitionCommandPair == null
							&& c.getPlayer().getGm() > 0) {
						if (type == 0) {
							mc.dropMessage("[系统] 命令 " + splitted[0]
									+ " 不存在或你无权使用.");
						} else {
							mc.dropMessage("[系统] 命令 " + splitted[0]
									+ " 不存在或你无权使用.");
						}
						return true;
					}
				}
			}
		}
		return false;
	}
}

class DefinitionCommandPair {

	private Command command;
	private CommandDefinition definition;

	public DefinitionCommandPair(Command command, CommandDefinition definition) {
		super();
		this.command = command;
		this.definition = definition;
	}

	public Command getCommand() {
		return command;
	}

	public CommandDefinition getDefinition() {
		return definition;
	}
}