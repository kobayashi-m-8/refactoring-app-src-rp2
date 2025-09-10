package jp.co.sss.crud.service;

import java.util.List;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.dto.EmployeeDTO;
import jp.co.sss.crud.exception.SystemException;
import jp.co.sss.crud.util.ConstantValue;

public class EmployeeService {

    // 社員全件表示
    public void showAllEmployees() throws SystemException {
        try {
            List<EmployeeDTO> employees = DBController.findAllEmployees();
            printEmployeeList(employees);
        } catch (Exception e) {
            throw new SystemException("社員一覧の取得に失敗しました。", e);
        }
    }

    // 社員名検索
    public void searchEmployeeByName(String name) throws SystemException {
        try {
            List<EmployeeDTO> employees = DBController.findEmployeeByName(name);
            printEmployeeList(employees);
        } catch (Exception e) {
            throw new SystemException("社員検索に失敗しました。", e);
        }
    }

    // 部署ID検索
    public void searchEmployeeByDeptId(String deptId) throws SystemException {
        try {
            List<EmployeeDTO> employees = DBController.findEmployeeByDeptId(deptId);
            printEmployeeList(employees);
        } catch (Exception e) {
            throw new SystemException("部署検索に失敗しました。", e);
        }
    }

    // 社員登録
    public void registerEmployee(String name, String gender, String birthday, String deptId) throws SystemException {
        try {
            DBController.insertEmployee(name, gender, birthday, deptId);
            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);
        } catch (Exception e) {
            throw new SystemException("社員登録に失敗しました。", e);
        }
    }

    // 社員更新
    public void updateEmployee(String empId, String name, String gender, String birthday, String deptId)
            throws SystemException {
        try {
            DBController.updateEmployee(empId, name, gender, birthday, deptId);
            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);
        } catch (Exception e) {
            throw new SystemException("社員更新に失敗しました。", e);
        }
    }

    // 社員削除
    public void deleteEmployee(String empId) throws SystemException {
        try {
            DBController.deleteEmployee(empId);
            System.out.println(ConstantValue.MSG_PROCESS_FINISHED);
        } catch (Exception e) {
            throw new SystemException("社員削除に失敗しました。", e);
        }
    }

    // 共通：社員一覧表示
    private void printEmployeeList(List<EmployeeDTO> employees) {
        if (employees == null || employees.isEmpty()) {
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
