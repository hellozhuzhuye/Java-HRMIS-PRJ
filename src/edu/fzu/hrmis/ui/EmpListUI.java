/**
 *
 */
package edu.fzu.hrmis.ui;

import java.util.List;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.Employee;
import edu.fzu.hrmis.utils.SysUtils;

/**
 *
 * 员工列表界面
 *
 * @author joeyang ong
 *
 */
public class EmpListUI implements BaseUI {

    /* (non-Javadoc)
     * @see edu.fzu.hrmis.ui.BaseUI#setup()
     */
    @Override
    public void setup() {

        
        List<Employee> empList = EmployeeDao.loadEmps();

        for (Employee emp : empList)
            emp.printOut();

        SysUtils.pause("\nPress Enter to continue...");

    }

}
