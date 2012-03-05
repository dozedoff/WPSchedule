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

import static org.junit.matchers.JUnitMatchers.containsString;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Contains a list of images and folders that should be displayed.
 * ImageGroups are assigned to Schedules.
 */
public class ImageGroup implements Serializable{
	private static final long serialVersionUID = 1L;

	private String groupname;
	
	private final ArrayList<File> singleImages = new ArrayList<File>(); // single images added by the user
	private final ArrayList<File> folders = new ArrayList<File>(); // image folders added by the user
	private final ArrayList<File> allImages = new ArrayList<File>(); // all images to be displayed
	private final ImageFilter imageFilter= new ImageFilter();
	private final PropertyChangeSupport change = new PropertyChangeSupport(this);
	
	public ImageGroup(String groupname){
		if(groupname == null || groupname.equals("")){
			this.groupname = "New Group";
		}else{
			this.groupname = groupname;
		}
	}
	
	/**
	 * Check that folders and single images exist, remove obsolete entries.
	 * Then re-populate allImages.<br>
	 * When this method is called, getSequentialImage() will be reset to it's starting position and
	 * the getRandomImage() oder will be scrambled.
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
	
	/**
	 * Filter out JPG and PNG images. API 10 Wallpaper only supports these images (at least from InputStream).
	 */
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
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof ImageGroup)){
			return false;
		}
		
		if(((ImageGroup)o).getGroupname().equals(groupname)){
			return true;
		}else{
			return false;
		}
	}
	
	public String getGroupname() {
		return groupname;
	}

	public boolean setGroupname(String groupname) {
		if(groupname == null || groupname.equals("")){
			return false;
		}else{
			this.groupname = groupname;
			return true;
		}
	}
	
	/**
	 * Add a file or folder to the Image group. Folders and subfolders are scanned for images.
	 * Duplicate files will not be added to the list.
	 * @param file File or folder to add
	 * @return true if the file or folder was addded.
	 */
	public boolean addFile(File file){
		if(file == null || (! file.exists())){
			return false;
		}
		
		if(file.isDirectory() || !folders.contains(file)){
			folders.add(file);
			scanFolder(file);
			return true;
		}
		
		if((file.isFile() || imageFilter.accept(file.getParentFile(), file.getName()))){
			singleImages.add(file);
			return true;
		}
		
		return false;
	}
	
	private void scanFolder(File file) {
		for(File f : fileWalk(file)){
			addFile(f);
		}
	}

	public ArrayList<File> getImages(){
		return allImages;
	}
	
	public ArrayList<File> getFolders(){
		return folders;
	}
	
	public void addListener(PropertyChangeListener listener){
		change.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		change.removePropertyChangeListener(listener);
	}
	
	private LinkedList<File> fileWalk(File file){
		LinkedList<File> walkedFiles = new LinkedList<File>();
		LinkedList<File> pendingFolders = new LinkedList<File>();
		
		pendingFolders.add(file);
		
		while(! pendingFolders.isEmpty()){
			File folder = pendingFolders.pop();
			
			for (File f : folder.listFiles()){
				if(! f.exists())
					continue;
				
				if(f.isFile()){
					walkedFiles.add(f);
				}else if(f.isDirectory()){
					pendingFolders.add(f);
				}
			}
		}
		return walkedFiles;
	}
}
