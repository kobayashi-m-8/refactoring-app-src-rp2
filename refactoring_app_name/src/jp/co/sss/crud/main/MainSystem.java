package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.dto.EmployeeDTO;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員情報管理システム開始クラス
 * 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 */
	public static void main(String[] args) {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		int selectedMenu = 0;

		try {
			do {
				// メニューの表示
				System.out.println("=== 社員管理システム ===");
				System.out.print(ConstantValue.MSG_MENU);

				String selectedMenuStr = consoleReader.readLine();
				selectedMenu = Integer.parseInt(selectedMenuStr);

				switch (selectedMenu) {
				case ConstantValue.MENU_SHOW_ALL_EMPLOYEES: {
					List<EmployeeDTO> employees = DBController.findAllEmployees();
					for (EmployeeDTO emp : employees) {
						System.out.println(emp); // DTOのtoString()で出力
					}
					break;
				}
				case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_NAME: {
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
					String inputName = consoleReader.readLine();
					List<EmployeeDTO> employees = DBController.findEmployeeByName(inputName);
					for (EmployeeDTO emp : employees) {
						System.out.println(emp);
					}
					break;
				}
				case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_DEPT_ID: {
					System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
					String inputDeptId = consoleReader.readLine();
					List<EmployeeDTO> employees = DBController.findEmployeeByDeptId(inputDeptId);
					for (EmployeeDTO emp : employees) {
						System.out.println(emp);
					}
					break;
				}
				case ConstantValue.MENU_REGISTER_EMPLOYEE: {
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
					String inputEmployeeName = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_GENDER + "：");
					String inputGender = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_BIRTHDAY + "：");
					String inputBirthday = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
					String inputDeptIdForInsert = consoleReader.readLine();

					DBController.insertEmployee(inputEmployeeName, inputGender, inputBirthday, inputDeptIdForInsert);
					System.out.println("社員を登録しました");
					break;
				}
				case ConstantValue.MENU_UPDATE_EMPLOYEE: {
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
					String inputEmployeeIdForUpdate = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
					String inputEmployeeName = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_GENDER + "：");
					String inputGender = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_BIRTHDAY + "：");
					String inputBirthday = consoleReader.readLine();
					System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
					String inputDeptId = consoleReader.readLine();

					DBController.updateEmployee(inputEmployeeIdForUpdate, inputEmployeeName, inputGender, inputBirthday,
							inputDeptId);
					System.out.println("社員情報を更新しました");
					break;
				}
				case ConstantValue.MENU_DELETE_EMPLOYEE: {
					System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
					String inputEmployeeIdForDelete = consoleReader.readLine();
					DBController.deleteEmployee(inputEmployeeIdForDelete);
					System.out.println("社員を削除しました");
					break;
				}
				}
			} while (selectedMenu != ConstantValue.MENU_EXIT);

			System.out.println(ConstantValue.MSG_PROCESS_FINISHED);

		} catch (IOException | SQLException | ClassNotFoundException | ParseException e) {
			e.printStackTrace();
		}
	}
}
