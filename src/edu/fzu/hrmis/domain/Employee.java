package edu.fzu.hrmis.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 员工
 *
 * @author joeyang ong
 */
public class Employee extends ValueObject implements Comparable<Employee> {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * 员工工号
     */
    private String payrollNo;

    /**
     * 电话号码
     */
    private String telephoneCode;

    /**
     * 名
     */
    private String lastName;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 号
     */
    private String initial;

    /**
     * 部门编号
     */
    private int deptNo;

    /**
     * 职务
     */
    private String jobTitle;

    /**
     * 入司日期
     */
    private Date hiringDate;

    public String getPayrollNo() {
        return payrollNo;
    }

    public void setPayrollNo(String payrollNo) {
        this.payrollNo = payrollNo;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    /**
     * 读取员工信息字符串，解析之后，形成员工对象
     *
     * @param str 信息字符串
     *            Employee.getEmployeeFromStr("111:02-98781999:Williams:Nick:T:35:Computer Officer:14-10-2000"):
     * @return
     */
    public static Employee getEmployeeFromStr(String str) {

        String[] sections = str.split("\\:");
        Employee emp = new Employee();

        emp.setPayrollNo(sections[0]);
        emp.setTelephoneCode(sections[1]);
        emp.setLastName(sections[2]);
        emp.setFirstName(sections[3]);
        emp.setInitial(sections[4]);
        emp.setDeptNo(Integer.parseInt(sections[5]));
        emp.setJobTitle(sections[6]);
        try {
            emp.setHiringDate(SDF.parse(sections[7]));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return emp;

    }

    /**
     * 雇员信息输出
     */
    public void printOut() {
        System.out.println(this);
    }


    /**
     * 雇员信息的格式化输出
     */
    public void printFormattedOut() {

        String str = String.format("%-15s%-10s%-4s%-5s%-13s%d %-20s%-12s", this.lastName
                , this.firstName
                , this.initial
                , this.payrollNo
                , this.telephoneCode
                , this.deptNo
                , this.jobTitle
                , SDF.format(this.hiringDate));
        System.out.println(str);

    }

    /**
     * 简单格式打印输出
     */
    public void simplePrintOut() {
        System.out.println(String.format("%s,%s,%s", this.lastName, this.firstName, this.telephoneCode));
    }


    public void simpleFormattedPrintOut() {
        System.out.println(String.format("%-15s%-12s%-14s", this.lastName, this.firstName, this.telephoneCode));
    }


    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s:%s:%d:%s:%s", this.payrollNo
                , this.telephoneCode
                , this.lastName
                , this.firstName
                , this.initial
                , this.deptNo
                , this.jobTitle
                , SDF.format(this.hiringDate));
    }

    @Override
    public int compareTo(Employee otherEmp) {
        return this.lastName.compareTo(otherEmp.lastName);
    }


}
