package jp.co.sss.crud.db;

import static jp.co.sss.crud.util.ConstantSQL.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースに接続するクラス
 */
public class DBManager {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		return connection;
	}

	// リファクタリング: preparedStatement → statement
	public static void close(PreparedStatement statement) throws SQLException {
		if (statement != null) {
			statement.close();
		}
	}

	public static void close(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public static void close(ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
	}
}
