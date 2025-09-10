package jp.co.sss.crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dto.EmployeeDTO;
import jp.co.sss.crud.exception.SystemException;
import jp.co.sss.crud.util.ConstantSQL;

/**
 * DB操作処理用のクラス
 */
public class DBController {

    private DBController() {
    }

    // 社員全件検索
    public static List<EmployeeDTO> findAllEmployees() throws SystemException {
        List<EmployeeDTO> employees = new ArrayList<>();

        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmpId(result.getInt("emp_id"));
                employee.setEmpName(result.getString("emp_name"));
                employee.setGender(result.getInt("gender"));
                employee.setBirthday(result.getString("birthday"));
                employee.setDeptName(result.getString("dept_name"));
                employees.add(employee);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SystemException("社員情報の全件取得に失敗しました。", e);
        }
        return employees;
    }

    // 社員名検索
    public static List<EmployeeDTO> findEmployeeByName(String searchName) throws SystemException {
        List<EmployeeDTO> employees = new ArrayList<>();

        String sql = ConstantSQL.SQL_SELECT_BASIC + ConstantSQL.SQL_SELECT_BY_EMP_NAME;

        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + searchName + "%");
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setEmpId(result.getInt("emp_id"));
                    employee.setEmpName(result.getString("emp_name"));
                    employee.setGender(result.getInt("gender"));
                    employee.setBirthday(result.getString("birthday"));
                    employee.setDeptName(result.getString("dept_name"));
                    employees.add(employee);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SystemException("社員情報の名前検索に失敗しました。", e);
        }
        return employees;
    }

    // 部署ID検索
    public static List<EmployeeDTO> findEmployeeByDeptId(String deptId) throws SystemException {
        List<EmployeeDTO> employees = new ArrayList<>();

        String sql = ConstantSQL.SQL_SELECT_BASIC + ConstantSQL.SQL_SELECT_BY_DEPT_ID;

        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, Integer.parseInt(deptId));
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setEmpId(result.getInt("emp_id"));
                    employee.setEmpName(result.getString("emp_name"));
                    employee.setGender(result.getInt("gender"));
                    employee.setBirthday(result.getString("birthday"));
                    employee.setDeptName(result.getString("dept_name"));
                    employees.add(employee);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SystemException("社員情報の部署検索に失敗しました。", e);
        }
        return employees;
    }

    // 社員登録
    public static void insertEmployee(String employeeName, String gender, String birthday, String deptId)
            throws SystemException {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantSQL.SQL_INSERT)) {

            statement.setString(1, employeeName);
            statement.setInt(2, Integer.parseInt(gender));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            statement.setObject(3, sdf.parse(birthday), Types.DATE);
            statement.setInt(4, Integer.parseInt(deptId));

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            throw new SystemException("社員の登録に失敗しました。", e);
        }
    }

    // 社員更新
    public static void updateEmployee(String empId, String employeeName, String gender, String birthday, String deptId)
            throws SystemException {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantSQL.SQL_UPDATE)) {

            statement.setString(1, employeeName);
            statement.setInt(2, Integer.parseInt(gender));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            statement.setObject(3, sdf.parse(birthday), Types.DATE);
            statement.setInt(4, Integer.parseInt(deptId));
            statement.setInt(5, Integer.parseInt(empId));

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            throw new SystemException("社員の更新に失敗しました。", e);
        }
    }

    // 社員削除
    public static void deleteEmployee(String empId) throws SystemException {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantSQL.SQL_DELETE)) {

            statement.setInt(1, Integer.parseInt(empId));
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SystemException("社員の削除に失敗しました。", e);
        }
    }
}
