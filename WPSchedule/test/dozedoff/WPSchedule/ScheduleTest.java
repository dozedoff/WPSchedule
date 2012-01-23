package dozedoff.WPSchedule;

import java.util.Date;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

public class ScheduleTest {
	Schedule schedule;

	// using diffrent years to make sure only time is compared
	Date midnight = new Date(15,0,0,0,0);
	Date sixAm = new Date(23,0,0,6,0);
	Date elevenAm = new Date(54,0,0,11,0);
	Date twoPm = new Date(2, 0, 0, 14, 0);
	Date ninePm = new Date(12, 0, 0, 21, 0);

	@Before
	public void setUp(){
		schedule = new Schedule();
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

		assertThat(schedule.isActive(midnight), is(false));
		assertThat(schedule.isActive(ninePm), is(true));
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
}