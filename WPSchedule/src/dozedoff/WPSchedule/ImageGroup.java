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

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains a list of images and folders that should be displayed.
 * ImageGroups are assigned to Schedules.
 */
public class ImageGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final ArrayList<File> singleImages = new ArrayList<File>(); // single images added by the user
	private final ArrayList<File> folders = new ArrayList<File>(); // image folders added by the user
	private final ArrayList<File> allImages = new ArrayList<File>(); // all images to be displayed
	private final ImageFilter imageFilter= new ImageFilter();
	
	/**
	 * Check that folders and single images exist, remove obsolete entries.
	 * Then re-populate allImages.<br>
	 * <b>Note:</b> Adding files from folders <i>is not recursive</i>, meaning sub-folders
	 * are not added.
	 */
	public void validate(){
		// look for missing single images...
		Iterator<File> ite = singleImages.iterator();
		while (ite.hasNext()){
			File next = ite.next();
			if( !(next.exists() || next.isFile())){
				ite.remove();
			}
		}
		
		// ... and for missing folders
		ite = folders.iterator();
		while (ite.hasNext()){
			File next = ite.next();
			if( !(next.exists() || next.isDirectory())){
				ite.remove();
			}
		}
		
		// now add the images to allImages
		allImages.clear();
		
		for(File image : singleImages){
			allImages.add(image);
		}
		
		for (File folder : folders){
			for(File image : folder.listFiles(imageFilter)){
				allImages.add(image);
			}
		}
		
	}
	
	class ImageFilter implements FilenameFilter{
		public boolean accept(File dir, String filename) {
			if(filename == null){
				return false;
			}
			
			String check = filename.toLowerCase();
			if(check.endsWith(".jpg") || check.endsWith(".png")){
				return true;
			}
			return false;
		}
		
	}
}
