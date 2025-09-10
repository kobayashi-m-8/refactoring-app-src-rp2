package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                    service.showAllEmployees();
                    break;
                }
                case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_NAME: {
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_NAME + "：");
                    String inputName = consoleReader.readLine();
                    service.searchEmployeeByName(inputName);
                    break;
                }
                case ConstantValue.MENU_SEARCH_EMPLOYEE_BY_DEPT_ID: {
                    System.out.print(ConstantValue.MSG_INPUT_DEPARTMENT_ID + "：");
                    String inputDeptId = consoleReader.readLine();
                    service.searchEmployeeByDeptId(inputDeptId);
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

                    service.registerEmployee(inputEmployeeName, inputGender, inputBirthday, inputDeptIdForInsert);
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

                    service.updateEmployee(inputEmployeeIdForUpdate, inputEmployeeName, inputGender, inputBirthday,
                            inputDeptId);
                    break;
                }
                case ConstantValue.MENU_DELETE_EMPLOYEE: {
                    System.out.print(ConstantValue.MSG_INPUT_EMPLOYEE_ID + "：");
                    String inputEmployeeIdForDelete = consoleReader.readLine();
                    service.deleteEmployee(inputEmployeeIdForDelete);
                    break;
                }
                }
            } while (selectedMenu != ConstantValue.MENU_EXIT);

            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);

        } catch (IOException e) {
            System.out.println("入力エラーが発生しました: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("入力されたメニュー番号が不正です。");
        } catch (Exception e) {
            System.out.println("予期せぬエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
