package assignment.jdbcEample;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;

public class ResultSetMapper<T> {
	public T mapRersultSetToObject(ResultSet rs, Class<T> outputClass) {
		T obj = null;
		try {
			obj = (T) outputClass.newInstance();
			if (rs != null) {
				if (outputClass.isAnnotationPresent(Entity.class)) {
					ResultSetMetaData meta = rs.getMetaData();
					Field[] fields = outputClass.getDeclaredFields();
					for (int i = 0; i < meta.getColumnCount(); i++) {
						String type = meta.getColumnTypeName(i + 1);
						String columnName = meta.getColumnName(i + 1);
						System.out.println("columnValue: " + type);

						for (Field field : fields) {
							field.setAccessible(true);
							if (field.isAnnotationPresent(Column.class)) {
								Column column = field
										.getAnnotation(Column.class);
								if (column.name().equalsIgnoreCase(columnName)) {
									try {
										if (type.equals("BIGINT")) {
											field.set(obj,
													rs.getLong(columnName));
										} else if (type.equalsIgnoreCase("DECIMAL")
												|| type.equalsIgnoreCase("NUMERIC")) {
											field.set(obj, rs
													.getDouble(columnName));
										} else if (type
												.equalsIgnoreCase("VARCHAR")) {
											field.set(obj,
													rs.getString(columnName));
										} else if (type.equalsIgnoreCase("INT")) {
											field.set(obj,
													rs.getInt(columnName));
										}
									} catch (IllegalAccessException e) {
										System.out
												.println("Cannot access the fields");
										e.printStackTrace();
										System.exit(2);
									} catch (SQLException e) {
										System.out
												.println("Unable to select data from db");
										e.printStackTrace();
										System.exit(1);
									}

								}
							}

						}
					}

				} else {
					System.out
							.println("The ouput class does not have the entity annotation!");
				}
			} else {
				System.out.println("ResultSet is null");
				return null;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return obj;

	}
}