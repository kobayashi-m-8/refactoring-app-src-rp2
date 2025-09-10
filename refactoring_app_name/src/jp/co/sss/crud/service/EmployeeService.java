package jp.co.sss.crud.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.dto.EmployeeDTO;
import jp.co.sss.crud.util.ConstantValue;

public class EmployeeService {

    // 社員全件表示
    public void showAllEmployees() {
        try {
            List<EmployeeDTO> employees = DBController.findAllEmployees();
            printEmployeeList(employees);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("社員一覧の取得に失敗しました: " + e.getMessage());
        }
    }

    // 社員名検索
    public void searchEmployeeByName(String name) {
        try {
            List<EmployeeDTO> employees = DBController.findEmployeeByName(name);
            printEmployeeList(employees);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("社員検索に失敗しました: " + e.getMessage());
        }
    }

    // 部署ID検索
    public void searchEmployeeByDeptId(String deptId) {
        try {
            List<EmployeeDTO> employees = DBController.findEmployeeByDeptId(deptId);
            printEmployeeList(employees);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("部署検索に失敗しました: " + e.getMessage());
        }
    }

    // 社員登録
    public void registerEmployee(String name, String gender, String birthday, String deptId) {
        try {
            DBController.insertEmployee(name, gender, birthday, deptId);
            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            System.out.println("社員登録に失敗しました: " + e.getMessage());
        }
    }

    // 社員更新
    public void updateEmployee(String empId, String name, String gender, String birthday, String deptId) {
        try {
            DBController.updateEmployee(empId, name, gender, birthday, deptId);
            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            System.out.println("社員更新に失敗しました: " + e.getMessage());
        }
    }

    // 社員削除
    public void deleteEmployee(String empId) {
        try {
            DBController.deleteEmployee(empId);
            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("社員削除に失敗しました: " + e.getMessage());
        }
    }

    // 共通：社員一覧表示
    private void printEmployeeList(List<EmployeeDTO> employees) {
        if (employees.isEmpty()) {
            System.out.println("該当する社員は存在しません。");
            return;
        }
        for (EmployeeDTO emp : employees) {
            System.out.println(
                emp.getEmpId() + " | " +
                emp.getEmpName() + " | " +
                (emp.getGender() == 0 ? "男" : "女") + " | " +
                emp.getBirthday() + " | " +
                emp.getDeptName()
            );
        }
    }
}
