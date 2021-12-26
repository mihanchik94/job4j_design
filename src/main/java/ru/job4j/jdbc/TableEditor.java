package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException {
        Class.forName("db.driver");
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.login"), properties.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        operation(String.format("create table %s if not exists", tableName));
    }

    public void dropTable(String tableName) {
        operation(String.format("drop table %s if exists", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        operation(String.format("alter table %s add %s %s", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        operation(String.format("alter table %s drop column %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        operation(String.format("alter table %s rename column %s on %s", tableName, columnName, newColumnName));
    }

    public void operation(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        String rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        String header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        StringJoiner buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (Statement statement = connection.createStatement()) {
            ResultSet selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            ResultSetMetaData metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
