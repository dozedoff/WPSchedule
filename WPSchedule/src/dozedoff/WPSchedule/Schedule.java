/*  Copyright (C) 2012  Nicholas Wright
	
	part of 'WPSchedule', an Android wallpaper changer.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dozedoff.WPSchedule;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;

/**
 * Responsible for what pictures are displayed when and at what intervals.
 */
public class Schedule implements Serializable{
	private static final long serialVersionUID = 1L;
	boolean enabled = false;
	boolean randomOrder = false; // set true to display images in random order
	//TODO  weekdays  -- how to do this? enums with MO || WE ? Is that possible?
	Date startTime, endTime;
	LinkedList<ImageGroup> imageGroups = new LinkedList<ImageGroup>();
}
