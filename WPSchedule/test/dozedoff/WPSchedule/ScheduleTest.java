package dozedoff.WPSchedule;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ScheduleTest {
	Schedule schedule;
	ImageGroup mockImageGroupA, mockImageGroupB;
	
	@Before
	public void setUp(){
		schedule = new Schedule();
		mockImageGroupA = spy(new ImageGroup());
		mockImageGroupB = spy(new ImageGroup());
	}

	@Test
	public void testAddGroup() {
		
	}

	@Test
	public void testIsActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEnabled() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEnabled() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRandomOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRandomOrder() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetStartTime() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetEndTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTimeWindow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetImageGroups() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddImageGroup() {
		fail("Not yet implemented");
	}
}
