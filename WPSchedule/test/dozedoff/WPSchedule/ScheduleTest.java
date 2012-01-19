package dozedoff.WPSchedule;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScheduleTest {
	Schedule schedule;
	
	@Before
	public void setUp(){
		schedule = new Schedule();
	}

	@Test
	public void testAddImageGroup() {
		assertThat(schedule.addImageGroup(new ImageGroup("New Group")), is(true));
		assertThat(schedule.addImageGroup(new ImageGroup("New Group")), is(false));
		assertThat(schedule.addImageGroup(new ImageGroup("group one")), is(true));
	}

	@Test
	public void testIsActive() {
		//TODO SO article about dates
		fail("Not yet implemented");
	}


	@Test
	public void testSetTimeWindow() {
		fail("Not yet implemented");
	}
}
