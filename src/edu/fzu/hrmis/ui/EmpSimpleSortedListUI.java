 /**
 *
 */
package edu.fzu.hrmis.ui;

import java.util.Set;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.Employee;
import edu.fzu.hrmis.utils.SysUtils;

/**
 *
 * 员工信息简略列表界面(排序)
 *
 * @author joeyang ong
 *
 */
public class EmpSimpleSortedListUI implements BaseUI {

    /* (non-Javadoc)
     * @see edu.fzu.hrmis.ui.BaseUI#setup()
     */
    @Override
    public void setup() {

        EmployeeDao empDao = new EmployeeDao();
        Set<Employee> empSet = empDao.loadSortedEmps();

        for (Employee emp : empSet)
            emp.simpleFormattedPrintOut();
        
        SysUtils.pause("\nPress Enter to continue...");
    }

}
