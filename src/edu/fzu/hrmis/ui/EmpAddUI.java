/**
 *
 */
package edu.fzu.hrmis.ui;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.Employee;
import edu.fzu.hrmis.exception.BlankEntryException;
import edu.fzu.hrmis.utils.SysUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 员工信息登记注册界面
 *
 * @author joeyang ong
 *
 */
public class EmpAddUI implements BaseUI {

    /* (non-Javadoc)
     * @see edu.fzu.hrmis.ui.BaseUI#setup()
     */
    @Override
    public void setup() {

        Employee emp;
        while (true) {
            emp = new Employee();
            //输出提示菜单
            System.out.println("\n\n\nAdfaith Consulting – Employee Records:\n"
                    + "======================================\n\n"
                    + "Employee Record Additions:\n"
                    + "Enter the following details of the new employee:\n"
                    + "Employee 3 digit payroll number\n" + "Phone Number\n"
                    + "Last Name\n" + "First Name\n" + "Middle Init\n" + "Dept #\n"
                    + "Job Title\n" + "Date Hired\n\n");

            emp.setPayrollNo(SysUtils.createPayrollNum());
            if (!SysUtils.searchByPayrollNum(emp.getPayrollNo()).isEmpty()) {
                System.out.println("The record of payroll number :  " + emp.getPayrollNo() + "already exist - try again");
                SysUtils.pause("\nPress Enter to continue...");
                continue;
            }
            emp.setTelephoneCode(SysUtils.createPhone());
            emp.setLastName(SysUtils.createLastName());
            emp.setFirstName(SysUtils.createFirstName());
            emp.setInitial(SysUtils.createInitial().toUpperCase());
            emp.setDeptNo(Integer.parseInt(SysUtils.createDepartNum()));
            emp.setJobTitle(SysUtils.createJobTiitle());
            try {
                emp.setHiringDate(SysUtils.SDF.parse(SysUtils.createHireDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 将信息添加到集合里面
            List<Employee> empList = EmployeeDao.loadEmps();
            empList.add(emp);
            EmployeeDao.storeRecords(EmployeeDao.DATA_FILE_PATH, empList);
            System.out.println("Record Saved ");
            System.out.print("Add another employee record? (y)es or (n)o,");
            String choice = SysUtils.getEntry();
            if (choice.equals("y"))
                continue;
            else
                break;

        }
    }


}
