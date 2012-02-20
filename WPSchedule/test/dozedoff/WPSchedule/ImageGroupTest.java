package dozedoff.WPSchedule;

import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class ImageGroupTest {
	ImageGroup ig;
	
	ArrayList<File> testf;
	ArrayList<File> testd;
	ArrayList<File> testfsub;
	ArrayList<File> all;
	
	@Before
	public void setUp() throws Exception {
		ig = new ImageGroup("test");
		
		testf = new ArrayList<File>();
		testd = new ArrayList<File>();
		testfsub = new ArrayList<File>();
		all = new ArrayList<File>();
		
		createFiles();
	}

	@Test
	public void testValidate() throws IOException {
		verifyFiles();
		
		for(File f : all){
				ig.addFile(f);
		}

		assertThat(ig.getImages().size(), is(4));
		assertThat(ig.getImages(), hasItem(testf.get(0)));
		assertThat(ig.getImages(), hasItem(testf.get(1)));
		assertThat(ig.getImages(), hasItem(testfsub.get(0)));
		assertThat(ig.getImages(), hasItem(testfsub.get(0)));
		
		testf.get(0).delete();
		ig.validate();
		
		assertThat(ig.getImages().size(), is(3));
		assertThat(ig.getImages(), hasItem(testf.get(1)));
		assertThat(ig.getImages(), hasItem(testfsub.get(0)));
		assertThat(ig.getImages(), hasItem(testfsub.get(0)));
		
		for(File f : testfsub){
			f.delete();
		}
		
		testd.get(1).delete();
		ig.validate();
		
		assertThat(ig.getImages().size(), is(1));
		assertThat(ig.getImages(), hasItem(testf.get(1)));
	}
	
	@Test
	public void testAddFile(){
		verifyFiles();
		
		for(File file : testf){
			ig.addFile(file);
		}
		
		ig.addFile(testd.get(0));
		
		ig.addFile(null);
		
		assertThat(ig.getImages().size(), is(2));
		assertThat(ig.getImages(), hasItem(testf.get(0)));
		assertThat(ig.getImages(), hasItem(testf.get(1)));
	}

	@Test
	public void testEqualsObject() {
		assertThat(ig.equals(new ImageGroup("test")), is(true));
		assertThat(ig.equals(new ImageGroup(null)), is(false));
		assertThat(ig.equals(new ImageGroup("TEST")), is(false));
		assertThat(ig.equals(new int[3]), is(false));
	}

	@Test
	public void testGetRandomImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSequentialImage() {
		fail("Not yet implemented");
	}
	
	private void createFiles() throws IOException{
		File tmpDir = Files.createTempDirectory("ImageGroupTest").toFile();
		
		testf.add(new File(tmpDir,"file1.jpg"));
		testf.add(new File(tmpDir,"file2.png"));
		testf.add(new File(tmpDir,"file3.bmp"));
		testf.add(new File(tmpDir,"file4.txt"));
		
		testd.add(new File(tmpDir,"subDir1"));
		testd.add(new File(tmpDir,"subDir2"));
		
		File sub2 = testd.get(1);
		testfsub.add(new File(sub2,"test.jpg"));
		testfsub.add(new File(sub2, "test.png"));
		testfsub.add(new File(sub2, "test.txt"));
		
		for(File dir : testd){
			dir.mkdirs();
		}
		
		for(File file : testf){
			file.createNewFile();
		}
		
		for(File file : testfsub){
			file.createNewFile();
		}
		
		all.addAll(testf);
		all.addAll(testd);
		all.addAll(testfsub);
	}
	
	private void verifyFiles(){
		for(File file : all){
			assertTrue("File "+file.toString()+" is missing",file.exists()); // make sure all files have been created
		}
	}
}