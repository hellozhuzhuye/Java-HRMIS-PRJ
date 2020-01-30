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
 * Ա����Ϣ�����б����
 *
 * @author joeyang ong
 *
 */
public class EmpSimpleListUI implements BaseUI {

    /* (non-Javadoc)
     * @see edu.fzu.hrmis.ui.BaseUI#setup()
     */
    @Override
    public void setup() {

        
        List<Employee> empList = EmployeeDao.loadEmps();

        for (Employee emp : empList)
            emp.simplePrintOut();

        SysUtils.pause("\nPress Enter to continue...");

    }

}
