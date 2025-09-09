package jp.co.sss.crud.dto;

public class EmployeeDTO {

    private int empId;
    private String empName;
    private int gender;
    private String birthday;
    private String deptName;

    // getter/setter
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        String genderJa = "";
        if (this.gender == 0) {
            genderJa = "男";
        } else if (this.gender == 1) {
            genderJa = "女";
        }
        return empId + "\t" + empName + "\t" + genderJa + "\t" + birthday + "\t" + deptName;
    }
}
