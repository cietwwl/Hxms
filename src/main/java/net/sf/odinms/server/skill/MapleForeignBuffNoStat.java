/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.odinms.server.skill;

import net.sf.odinms.client.MapleBuffStat;
import net.sf.odinms.tools.data.output.MaplePacketLittleEndianWriter;

/**
 *
 * @author hxms
 */
public class MapleForeignBuffNoStat extends MapleForeignBuffStat {

	public MapleForeignBuffNoStat(MapleBuffStat stat) {
		super(stat);
	}

	@Override
	public void writePacket(MaplePacketLittleEndianWriter mplew, int value) {
	}
}
