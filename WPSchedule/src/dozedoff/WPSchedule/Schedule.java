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
	
	//TODO add time validation, start time < end time
	/**
	 * Add a ImageGroup to the schedule. Will ignore the group if one with an identical name is already present.
	 * @param group the ImageGroup to add
	 * @return true if the group was added
	 */
	public boolean addGroup(ImageGroup group){
		if(imageGroups.contains(group)){
			return false;
		}else{
			imageGroups.add(group);
			return true;
		}
	}
	
	/**
	 * Check if the schedule is active.
	 * @param time the current time
	 * @return true if active
	 */
	public boolean isActive(Date time){
		if(! enabled){
			return false;
		}
		
		//TODO add time comparison code here
		
		return false;
	}
	
	/**
	 * Query the state of the schedule.
	 * @return true if enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Enable the schedule.<br>
	 * A schedule set to false (disabled) will always return false on isActive(Date time).
	 * @param enabled enabled if set to true
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * If set to true, Images assigned to this schedule will be displayed in random order.
	 * @return true if enabled
	 */
	public boolean isRandomOrder() {
		return randomOrder;
	}

	/**
	 * Set the display order.
	 * @param randomOrder enabled if set to true
	 */
	public void setRandomOrder(boolean randomOrder) {
		this.randomOrder = randomOrder;
	}

	/**
	 * Returns the time when the schedule starts.<br>
	 * <b>Note:</b> The schedule is active on and after the start time.
	 * @return the start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time of the schedule.<br>
	 * <b>Note:</b> The schedule is active up to and including the end time.
	 * @return the end time of the schedule
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Set the start and end time of a schedule.<br><br> If the start time is later than the
	 * end time, start time will be set to match end time. The same is true for the end time.
	 * A null value will not change the current time.<br>
	 * <b>Note:</b><br>
	 * The schedule is active on and after the start time.<br>
	 * The schedule is active up to and including the end time.
	 * 
	 * @param starttime the start time to set
	 * @param endtime the end time to set
	 */
	public void setTimeWindow(Date starttime, Date endtime){
		
	}
	
	/**
	 * Get the ImageGroups assigned to this schedule
	 * @return all {@link ImageGroup} items assigned to this schedule
	 */
	public LinkedList<ImageGroup> getImageGroups() {
		return imageGroups;
	}
	
	/**
	 * Attempt to add a ImageGroup to the schedule.<br>
	 * Duplicate image groups will not be added.
	 * @param imageGroup {@link ImageGroup} to add
	 * @return true if successfully added
	 */
	public boolean addImageGroup(ImageGroup imageGroup){
		if(imageGroups.contains(imageGroup)){
			return false;
		}else{
			imageGroups.add(imageGroup);
			return true;
		}
	}
}
