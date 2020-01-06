package pedagogy.csvfile_to_database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {

	private BufferedReader bufferedReader;
	private File file;
	private String header;

	public CSVReader(File file) throws IOException {
		this.file = file;
		bufferedReader = new BufferedReader(new FileReader(file));
		header = bufferedReader.readLine();
	}

	private String readNextRecord() {
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			return null;
		}

	}

	public List<String> getHeaderColumns() throws IOException {

		Function<String, String> truncatedString = s -> {
			s = s.trim();
			if (s.startsWith("_")) {
				return s.substring(1, s.length());
			} else {
				return s;
			}
		};
		
		return Stream
				.of(header.split(",", 1))
				.filter(columnHeader -> columnHeader.length() > 0)
				.map(truncatedString)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<String> nextRecordValues() {
		String record = readNextRecord();
		if (Optional.ofNullable(record).isPresent()) {
			return createValuesList(record);
		} else {
			return null;
		}
	}

	private List<String> createValuesList(String record) {
		return Arrays
				.asList(record.split(",", -1))
				.stream()
				.map(value -> value.trim())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void close() throws IOException {
		bufferedReader.close();
	}

}
