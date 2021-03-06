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
package net.sf.odinms.server.life;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.odinms.server.life.MapleLifeFactory.BanishInfo;

/**
 * Bean ^__^ that holds monster stats - setters shouldn't be called after
 * loading is complete.
 *
 * @author Frz
 */
public class MapleMonsterStats {

	private int id;
	private int exp;
	private int hp, mp;
	private int level;
	private int removeAfter;
	private int dropPeriod;
	private boolean boss;
	private boolean undead;
	private boolean ffaLoot;
	private String name;
	private Map<String, Integer> animationTimes = new HashMap<String, Integer>();
	private final Map<Element, ElementalEffectiveness> resistance = new EnumMap<Element, ElementalEffectiveness>(
			Element.class);
	private List<Integer> revives = Collections.emptyList();
	private byte tagColor;
	private byte tagBgColor;
	private List<MapleMonsterSkill> skills = new ArrayList<MapleMonsterSkill>();
	private boolean firstAttack;
	private int buffToGive;
	private boolean explosive;
	private BanishInfo banish;

	public MapleMonsterStats() {
	}

	public MapleMonsterStats(int id) {
		this.id = id;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRemoveAfter() {
		return removeAfter;
	}

	public void setRemoveAfter(int removeAfter) {
		this.removeAfter = removeAfter;
	}

	public int getDropPeriod() {
		return dropPeriod;
	}

	public void setDropPeriod(int dropPeriod) {
		this.dropPeriod = dropPeriod;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setFfaLoot(boolean ffaLoot) {
		this.ffaLoot = ffaLoot;
	}

	public boolean isFfaLoot() {
		return ffaLoot;
	}

	public void setAnimationTime(String name, int delay) {
		animationTimes.put(name, delay);
	}

	public int getAnimationTime(String name) {
		Integer ret = animationTimes.get(name);
		if (ret == null) {
			return 500;
		}
		return ret.intValue();
	}

	public BanishInfo getBanishInfo() {
		return banish;
	}

	public void setBanishInfo(BanishInfo banish) {
		this.banish = banish;
	}

	public boolean isMobile() {
		return animationTimes.containsKey("move")
				|| animationTimes.containsKey("fly");
	}

	public List<Integer> getRevives() {
		return revives;
	}

	public void setRevives(List<Integer> revives) {
		this.revives = revives;
	}

	public void setUndead(boolean undead) {
		this.undead = undead;
	}

	public boolean getUndead() {
		return undead;
	}

	public void setEffectiveness(Element e, ElementalEffectiveness ee) {
		synchronized (resistance) {
			resistance.put(e, ee);
		}
	}

	public void removeEffectiveness(Element e) {
		synchronized (resistance) {
			resistance.remove(e);
		}
	}

	public ElementalEffectiveness getEffectiveness(Element e) {
		synchronized (resistance) {
			ElementalEffectiveness elementalEffectiveness = resistance.get(e);
			if (elementalEffectiveness == null) {
				return ElementalEffectiveness.NORMAL;
			} else {
				return elementalEffectiveness;
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getTagColor() {
		return tagColor;
	}

	public void setTagColor(int tagColor) {
		this.tagColor = (byte) tagColor;
	}

	public byte getTagBgColor() {
		return tagBgColor;
	}

	public void setTagBgColor(int tagBgColor) {
		this.tagBgColor = (byte) tagBgColor;
	}

	public void setSkills(List<MapleMonsterSkill> skills) {
		this.skills = skills;
	}

	public List<MapleMonsterSkill> getSkills() {
		return Collections.unmodifiableList(this.skills);
	}

	public int getNoSkills() {
		return this.skills.size();
	}

	public boolean hasSkill(int skillId, int level) {
		for (MapleMonsterSkill skill : skills) {
			if (skill.getSkill() == skillId && skill.getLevel() == level) {
				return true;
			}
		}
		return false;
	}

	public void setFirstAttack(boolean firstAttack) {
		this.firstAttack = firstAttack;
	}

	public boolean isFirstAttack() {
		return firstAttack;
	}

	public void setBuffToGive(int buff) {
		this.buffToGive = buff;
	}

	public int getBuffToGive() {
		return buffToGive;
	}

	public void setExplosive(boolean set) {
		this.explosive = set;
	}

	public boolean isExplosive() {
		return this.explosive;
	}

	public String getResistance() {
		StringBuilder buff = new StringBuilder();
		for (Map.Entry<Element, ElementalEffectiveness> entry : resistance
				.entrySet()) {
			buff.append(entry.getKey().getChar());
			buff.append(entry.getValue().getNumber());
		}
		return buff.toString();
	}

	public void setResistance(String s) {
		MapleLifeFactory.decodeElementalString(this, s);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Integer> getAnimationTimes() {
		return animationTimes;
	}

	public void setAnimationTimes(Map<String, Integer> animationTimes) {
		this.animationTimes = animationTimes;
	}

	public BanishInfo getBanish() {
		return banish;
	}

	public void setBanish(BanishInfo banish) {
		this.banish = banish;
	}

	public void setTagColor(byte tagColor) {
		this.tagColor = tagColor;
	}

	public void setTagBgColor(byte tagBgColor) {
		this.tagBgColor = tagBgColor;
	}

	public boolean isUndead() {
		return undead;
	}
}