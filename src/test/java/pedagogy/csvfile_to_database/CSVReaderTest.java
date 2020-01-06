package pedagogy.csvfile_to_database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CSVReaderTest {

	private CSVReader csvReader;

	@Before
	public void setUp() throws IOException{
		csvReader = new CSVReader(new File("E:\\pedagogy_training\\pending\\test2.csv"));
	}

	/*
	 * @Test public void testGetHeaderColumns() throws IOException { Set<String>
	 * columnHeaders = csvReader.getHeaderColumns();
	 * assertNotNull("Null object is retrieve", columnHeaders);
	 * assertEquals(columnHeaders.size(), 14); }
	 */
	@Test
	public void testGetRecordValues() {
		List<String> values = csvReader.nextRecordValues();
		assertEquals(values.size(), 14);
	}

}
