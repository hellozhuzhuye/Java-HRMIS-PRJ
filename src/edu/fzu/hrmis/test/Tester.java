/**
 *
 */
package edu.fzu.hrmis.test;

import java.util.List;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.Employee;

/**
 * @author joeyang ong
 *
 */
public class Tester {

    /**
     * @param args
     */
    public static void main(String[] args) {

        
        List<Employee> empList = EmployeeDao.loadEmps();

        System.out.println(empList);

    }

}
