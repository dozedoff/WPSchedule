package dozedoff.WPSchedule;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;

public class ScheduleTest {
	Schedule schedule;

	// using diffrent years to make sure only time is compared
	Date midnight = new Date(15,0,0,0,0);
	Date sixAm = new Date(23,0,0,6,0);
	Date elevenAm = new Date(54,0,0,11,0);
	Date twoPm = new Date(2, 0, 0, 14, 0);
	Date ninePm = new Date(12, 0, 0, 21, 0);

	// mock ImageGroup for get...Image tests
	ImageGroup ig1,ig2,ig3;
	ArrayList<File> fileList1, fileList2, fileList3;
	File[] files;
	
	@Before
	public void setUp() throws Exception{
		schedule = new Schedule();
	}
	
	private ArrayList<File> generateFilenames(String filename, int numOfFiles){
		ArrayList<File> files = new ArrayList<File>(numOfFiles);
		for(int i = 0; i<numOfFiles; i++){
			files.add(new File(filename+"_"+i+".txt"));
		}
		
		return files;
	}

	@Test
	public void testAddImageGroup() {
		assertThat(schedule.addImageGroup(new ImageGroup("New Group")), is(true));
		assertThat(schedule.addImageGroup(new ImageGroup("New Group")), is(false));
		assertThat(schedule.addImageGroup(new ImageGroup("group one")), is(true));

		assertThat(schedule.getImageGroups(), hasItem(new ImageGroup("New Group")));
		assertThat(schedule.getImageGroups(), hasItem(new ImageGroup("group one")));
	}

	@Test
	public void testIsActive() {
		assertThat(schedule.isActive(midnight), is(false));
		assertThat(schedule.isActive(ninePm), is(false));

		schedule.setEnabled(true);

		assertThat(schedule.isActive(sixAm), is(false));
		assertThat(schedule.isActive(elevenAm), is(true));
		assertThat(schedule.isActive(twoPm), is(true));
		assertThat(schedule.isActive(ninePm), is(true));
		assertThat(schedule.isActive(midnight), is(false));
	}


	@Test
	public void testSetTimeWindow() {
		schedule.setEnabled(true);
		// test default time window
		assertThat(schedule.isActive(midnight), is(false));
		assertThat(schedule.isActive(ninePm), is(true));

		schedule.setTimeWindow(new Date(0,0,0,13,0), new Date(0, 0, 0, 14, 0));

		assertThat(schedule.isActive(midnight), is(false));
		assertThat(schedule.isActive(sixAm ), is(false));
		assertThat(schedule.isActive(elevenAm), is(false));
		assertThat(schedule.isActive(twoPm), is(true));
		assertThat(schedule.isActive(ninePm), is(false));

		// test for incorrect time window definition
		schedule.setTimeWindow(new Date(0,0,0,13,0), new Date(0,0,0,6,0));

		assertThat(schedule.getStartTime(), is(new Date(0,0,0,6,0)));

		// another test to make sure only hour and minutes are comapred
		schedule.setTimeWindow(new Date(2,0,0,3,0), new Date(0,0,0,6,0));

		assertThat(schedule.getStartTime(), is(new Date(0,0,0,3,0)));
		assertThat(schedule.getEndTime(), is(new Date(0,0,0,6,0)));
	}
	
	@Test
	public void testGetSequentialImage(){
		imageGetSetUp();
		
		ArrayList<File> recieved = new ArrayList<File>(15);
		ArrayList<File> allFiles = new ArrayList<File>();
		ArrayList<ArrayList<File>> lists = new ArrayList<ArrayList<File>>();
		
		lists.add(fileList1);
		lists.add(fileList2);
		lists.add(fileList3);
		
		allFiles.addAll(fileList1);
		allFiles.addAll(fileList2);
		allFiles.addAll(fileList3);
		
		for(int i=0; i<(allFiles.size()+20); i++ ){
			File file = schedule.getImage();
			
			assertNotNull(file);
			
			if(!recieved.contains(file))
				recieved.add(file);
		}
		
		assertThat(recieved.size(), is(allFiles.size()));
		File[] compare = new File[allFiles.size()];
		allFiles.toArray(compare);
		
		assertThat(recieved,hasItems(compare));
		
		fail("test not finished yet");
	}
	
	@Test
	public void testGetRndomImage(){
		imageGetSetUp();
		fail("not implemented yet");
	}

	private void imageGetSetUp(){
		ig1  = mock(ImageGroup.class);
		ig2  = mock(ImageGroup.class);
		ig3  = mock(ImageGroup.class);
		
		fileList1 = generateFilenames("ig1", 5);
		fileList2 = generateFilenames("ig2", 4);
		fileList3 = generateFilenames("ig3", 6);
		
		when(ig1.getImages()).thenReturn(fileList1);
		when(ig1.getGroupname()).thenReturn("ig1");
		
		when(ig2.getImages()).thenReturn(fileList2);
		when(ig2.getGroupname()).thenReturn("ig2");
		
		when(ig3.getImages()).thenReturn(fileList3);
		when(ig3.getGroupname()).thenReturn("ig3");
		
		schedule = new Schedule();
		schedule.addImageGroup(ig1);
		schedule.addImageGroup(ig2);
		schedule.addImageGroup(ig3);
	}
}