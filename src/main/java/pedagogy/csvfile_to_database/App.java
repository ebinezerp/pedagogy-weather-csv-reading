package pedagogy.csvfile_to_database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class App {
	public static void main(String[] args) {
		try {
			File file = new File("E:\\pedagogy_training\\pending\\test.csv");
			CSVReader csvReader = new CSVReader(file);
			DBOperations dbOperations = new DBOperations();
			List<String> values = null;
			while ((values = csvReader.nextRecordValues()) != null) {
				System.out.println(values);
				dbOperations.addRecord(values);
				System.out.println("added");
			}
			csvReader.close();
			Path dir = Paths.get(file.getParentFile().getParent() + "/done");
			if (!Files.exists(dir)) {
				Files.createDirectory(dir);
			}
			Path newFile = dir.resolve(file.getName());
			Files.deleteIfExists(newFile);
			Files.move(Paths.get(file.getAbsolutePath()), newFile);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
