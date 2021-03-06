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

package net.sf.odinms.server.playerinteractions;

import net.sf.odinms.client.IItem;

/**
 *
 * @author Matze
 */
public class MaplePlayerShopItem {

	private IItem item;
	private short bundles;
	private long price;

	public MaplePlayerShopItem(IItem item, short bundles, long price) {
		this.item = item;
		this.bundles = bundles;
		this.price = price;
	}

	public IItem getItem() {
		return item;
	}

	public short getBundles() {
		return bundles;
	}

	public long getPrice() {
		return price;
	}

	public void setBundles(short bundles) {
		this.bundles = bundles;
	}
}
