package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員情報管理システム開始クラス
 * 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author 
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 */
	public static void main(String[] args) {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		// リファクタリング: menuNo → selectedMenu
		int selectedMenu = 0;

		try {
			do {
				// メニューの表示
				System.out.println("=== 社員管理システム ===");
				System.out.print(ConstantValue.MSG_MENU);

				// リファクタリング: menuNoStr → selectedMenuStr
				String selectedMenuStr = consoleReader.readLine();
				selectedMenu = Integer.parseInt(selectedMenuStr);

				// 機能の呼出
				switch (selectedMenu) {
				case ConstantValue.MENU_SHOW_ALL_EMPLOYEES:
					DBController.findAllEmployees();
					break;

				case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_NAME:
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
					DBController.findEmployeeByName();
					break;

				case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_DEPT_ID:
					System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
					String inputDeptId = consoleReader.readLine();
					DBController.findEmployeeByDeptId(inputDeptId);
					break;

				case ConstantValue.MENU_REGISTER_EMPLOYEE:
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
					String inputEmployeeName = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_GENDER + "：");
					String inputGender = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_BIRTHDAY + "：");
					String inputBirthday = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
					String inputDeptIdForInsert = consoleReader.readLine();

					DBController.insertEmployee(inputEmployeeName, inputGender, inputBirthday, inputDeptIdForInsert);
					break;

				case ConstantValue.MENU_UPDATE_EMPLOYEE:
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
					String inputEmployeeIdForUpdate = consoleReader.readLine();
					DBController.updateEmployee(inputEmployeeIdForUpdate);
					System.out.println("社員情報を更新しました");
					break;

				case ConstantValue.MENU_DELETE_EMPLOYEE:
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
					DBController.deleteEmployee();
					break;
				}
			} while (selectedMenu != ConstantValue.MENU_EXIT);

			System.out.println(ConstantValue.MSG_PROCESS_FINISHED);

		} catch (IOException | SQLException | ClassNotFoundException | ParseException e) {
			e.printStackTrace();
		}
	}
}
