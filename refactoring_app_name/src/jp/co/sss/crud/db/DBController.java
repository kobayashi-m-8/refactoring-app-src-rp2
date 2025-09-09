package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

/**
 * DB操作処理用のクラス
 */
public class DBController {

	private DBController() {
	}

	// リファクタリング: find → findAllEmployees
	public static void findAllEmployees() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = DBManager.getConnection();
			statement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
			result = statement.executeQuery();

			if (!result.isBeforeFirst()) {
				System.out.println("該当者はいませんでした");
				return;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (result.next()) {
				System.out.print(result.getString("emp_id") + "\t");
				System.out.print(result.getString("emp_name") + "\t");

				// リファクタリング: gender → genderCode
				int genderCode = Integer.parseInt(result.getString("gender"));
				if (genderCode == 0) {
					System.out.print("回答なし" + "\t");
				} else if (genderCode == 1) {
					System.out.print("男性" + "\t");
				} else if (genderCode == 2) {
					System.out.print("女性" + "\t");
				} else if (genderCode == 9) {
					System.out.print("その他" + "\t");
				}

				System.out.print(result.getString("birthday") + "\t");
				System.out.println(result.getString("dept_name"));
			}
			System.out.println("");
		} finally {
			DBManager.close(result);
			DBManager.close(statement);
			DBManager.close(connection);
		}
	}

	// リファクタリング: findB → findEmployeeByName
	public static void findEmployeeByName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		String searchName = consoleReader.readLine();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = DBManager.getConnection();
			String sql = ConstantSQL.SQL_SELECT_BASIC + ConstantSQL.SQL_SELECT_BY_EMP_NAME;
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + searchName + "%");
			result = statement.executeQuery();

			if (!result.isBeforeFirst()) {
				System.out.println("該当者はいませんでした");
				return;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (result.next()) {
				System.out.print(result.getString("emp_id") + "\t");
				System.out.print(result.getString("emp_name") + "\t");

				int genderCode = Integer.parseInt(result.getString("gender"));
				if (genderCode == 0) {
					System.out.print("回答なし");
				} else if (genderCode == 1) {
					System.out.print("男性");
				} else if (genderCode == 2) {
					System.out.print("女性");
				} else if (genderCode == 9) {
					System.out.print("その他");
				}

				System.out.print("\t" + result.getString("birthday") + "\t");
				System.out.println(result.getString("dept_name"));
			}
			System.out.println("");
		} finally {
			DBManager.close(result);
			DBManager.close(statement);
			DBManager.close(connection);
		}
	}

	// リファクタリング: findC → findEmployeeByDeptId
	public static void findEmployeeByDeptId(String deptId) throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = DBManager.getConnection();
			String sql = ConstantSQL.SQL_SELECT_BASIC + ConstantSQL.SQL_SELECT_BY_DEPT_ID;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(deptId));
			result = statement.executeQuery();

			if (!result.isBeforeFirst()) {
				System.out.println("該当者はいませんでした");
				return;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (result.next()) {
				System.out.print(result.getString("emp_id") + "\t");
				System.out.print(result.getString("emp_name") + "\t");

				int genderCode = Integer.parseInt(result.getString("gender"));
				if (genderCode == 0) {
					System.out.print("回答なし");
				} else if (genderCode == 1) {
					System.out.print("男性");
				} else if (genderCode == 2) {
					System.out.print("女性");
				} else if (genderCode == 9) {
					System.out.print("その他");
				}

				System.out.print("\t" + result.getString("birthday") + "\t");

				int deptCode = Integer.parseInt(result.getString("dept_id"));
				if (deptCode == 1) {
					System.out.println("営業部");
				} else if (deptCode == 2) {
					System.out.println("経理部");
				} else if (deptCode == 3) {
					System.out.println("総務部");
				}
			}
			System.out.println("");
		} finally {
			DBManager.close(result);
			DBManager.close(statement);
			DBManager.close(connection);
		}
	}

	// リファクタリング: insert → insertEmployee
	public static void insertEmployee(String employeeName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBManager.getConnection();
			statement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			statement.setString(1, employeeName);
			statement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			statement.setObject(3, sdf.parse(birthday), Types.DATE);
			statement.setInt(4, Integer.parseInt(deptId));

			statement.executeUpdate();
			System.out.println("社員情報を登録しました");
		} finally {
			DBManager.close(statement);
			DBManager.close(connection);
		}
	}

	// リファクタリング: update → updateEmployee
	public static void updateEmployee(String empId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement statement = null;
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			connection = DBManager.getConnection();
			statement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
			String employeeName = consoleReader.readLine();

			System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_GENDER + "：");
			String gender = consoleReader.readLine();

			System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_BIRTHDAY + "：");
			String birthday = consoleReader.readLine();

			System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
			String deptId = consoleReader.readLine();

			statement.setString(1, employeeName);
			statement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			statement.setObject(3, sdf.parse(birthday), Types.DATE);
			statement.setInt(4, Integer.parseInt(deptId));
			statement.setInt(5, Integer.parseInt(empId));

			statement.executeUpdate();

		} finally {
			DBManager.close(statement);
			DBManager.close(connection);
		}
	}

	// リファクタリング: delete → deleteEmployee
	public static void deleteEmployee() {
		Connection connection = null;
		PreparedStatement statement = null;
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			connection = DBManager.getConnection();
			String inputEmployeeId = consoleReader.readLine();

			statement = connection.prepareStatement(ConstantSQL.SQL_DELETE);
			statement.setInt(1, Integer.parseInt(inputEmployeeId));
			statement.executeUpdate();

			System.out.println("社員情報を削除しました");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(statement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
