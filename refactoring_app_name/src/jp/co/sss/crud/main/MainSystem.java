package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.co.sss.crud.exception.SystemException;
import jp.co.sss.crud.service.EmployeeService;
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
        EmployeeService service = new EmployeeService();

        try {
            int selectedMenu = 0;
            do {
                // メニューの表示
                System.out.println("=== 社員管理システム ===");
                System.out.print(ConstantValue.MSG_MENU);

                String selectedMenuStr = consoleReader.readLine();
                selectedMenu = Integer.parseInt(selectedMenuStr);

                switch (selectedMenu) {
                case ConstantValue.MENU_SHOW_ALL_EMPLOYEES:
                    service.showAllEmployees();
                    break;

                case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_NAME:
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
                    String inputName = consoleReader.readLine();
                    service.searchEmployeeByName(inputName);
                    break;

                case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_DEPT_ID:
                    System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
                    String inputDeptId = consoleReader.readLine();
                    service.searchEmployeeByDeptId(inputDeptId);
                    break;

                case ConstantValue.MENU_REGISTER_EMPLOYEE:
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
                    String name = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_GENDER + "：");
                    String gender = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_BIRTHDAY + "：");
                    String birthday = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
                    String deptId = consoleReader.readLine();

                    service.registerEmployee(name, gender, birthday, deptId);
                    break;

                case ConstantValue.MENU_UPDATE_EMPLOYEE:
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
                    String updateId = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
                    String updateName = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_GENDER + "：");
                    String updateGender = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_BIRTHDAY + "：");
                    String updateBirthday = consoleReader.readLine();
                    System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
                    String updateDept = consoleReader.readLine();

                    service.updateEmployee(updateId, updateName, updateGender, updateBirthday, updateDept);
                    break;

                case ConstantValue.MENU_DELETE_EMPLOYEE:
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
                    String deleteId = consoleReader.readLine();
                    service.deleteEmployee(deleteId);
                    break;
                }
            } while (selectedMenu != ConstantValue.MENU_EXIT);

            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);

        } catch (IOException e) {
            System.err.println("入力エラーが発生しました: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("入力されたメニュー番号が不正です。");
        } catch (SystemException e) {
            System.err.println("システムエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
