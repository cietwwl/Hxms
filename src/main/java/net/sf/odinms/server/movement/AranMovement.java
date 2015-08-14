package net.sf.odinms.server.movement;

import java.awt.Point;
import net.sf.odinms.tools.data.output.LittleEndianWriter;

public class AranMovement extends AbstractLifeMovement {
    
    public AranMovement(int type, Point position, int duration, int newstate) {
	super(type, position, duration, newstate);
    }
    
    @Override
    public void serialize(LittleEndianWriter lew) {
        lew.write(getType());
        lew.write(getNewstate());
        lew.writeShort(getDuration());
    }
}
