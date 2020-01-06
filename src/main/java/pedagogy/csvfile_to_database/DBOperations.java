package pedagogy.csvfile_to_database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DBOperations {

	private Connection connection;

	public DBOperations() throws SQLException {
		this.connection = new DBConnection().openConnection();
	}

	public boolean addRecord(List<String> values) throws SQLException, ParseException {

		System.out.println(values.size());
		// String columnsString =
		// recordWithHeader.keySet().stream().collect(Collectors.joining(","));
		String queryParameters = String.join(",", Collections.nCopies(values.size(), "?"));
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into weather values(" + queryParameters + ",'Delhi')");

		for (int index = 1; index <= values.size(); index++) {
			setValues(preparedStatement, values.get(index - 1), index);
		}
		
		if (preparedStatement.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}

	}

	private void setValues(PreparedStatement preparedStatement, String value, Integer index)
			throws SQLException, ParseException {

		if (getDateType(value) == Date.class) {
			java.util.Date date = new SimpleDateFormat("yyyyMMdd-hh:mm").parse(value);
			preparedStatement
					.setDate(index, new Date(date.getYear(), date.getMonth(), date.getDay()));
		}

		if (getDateType(value) == Integer.class) {
			preparedStatement.setInt(index, Integer.parseInt(value));
		} else if (getDateType(value) == Float.class) {
			preparedStatement.setFloat(index, Float.parseFloat(value));
		} else if (getDateType(value) == Double.class) {
			preparedStatement.setDouble(index, Double.parseDouble(value));
		} else if (getDateType(value) == Boolean.class) {
			preparedStatement.setBoolean(index, Boolean.parseBoolean(value));
		} else if (getDateType(value) == String.class) {
			preparedStatement.setString(index, value);
		}

	}

	private Class getDateType(String value) {

		if (isDate(value)) {
			return Date.class;
		} else if (isInteger(value)) {
			return Integer.class;
		} else if (isFloat(value)) {
			return Float.class;
		} else if (isDouble(value)) {
			return Double.class;
		} else if (isBoolean(value)) {
			return Boolean.class;
		}
		return String.class;

	}

	private boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isDate(String value) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-hh:mm");
			simpleDateFormat.parse(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isBoolean(String value) {
		try {
			Boolean.parseBoolean(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
