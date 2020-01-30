/**
 * 
 */
package edu.fzu.hrmis.ui;

import java.util.Iterator;
import java.util.List;


import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.Employee;
import edu.fzu.hrmis.utils.SysUtils;

/**
 * 员工删除界面
 * @author 82607
 *
 */
public class EmpDeleteUI implements BaseUI {

	@Override
	public void setup() {
		
		while (true) {			
			
            //输出菜单
            System.out.println("Adfaith Consulting - Employee Records : \n"
                    + "============================================\n\n\n"
                    + "Empoyee Record Deletion : \n\n");

            //输入员工号查找员工信息
            String keyword = SysUtils.createPayrollNum();
            
          
            //查找员工信息
            List<Employee> e = SysUtils.searchByPayrollNum(keyword);
            SysUtils.recordEmployee = EmployeeDao.loadEmps();
            
            
            
            if (e.isEmpty()) {
                //没有所要查找的员工信息
                System.out.print("\n\nEmployee record for " + keyword + " not found!\n");
                SysUtils.pause("\nPress Enter to continue...");
                break;
            }
            
            //显示所找到的员工信息，并确认是否要删除
            for (Employee v : e)
                System.out.println("\n\n" + v.toString());
            System.out.print("Confirm record deletion ,(y)es or (n)o : ");

            if (SysUtils.getEntry().equals("y")) {
                
            	//删除所有找到的员工信息           	
                for (Employee v : e)
                {
                	//使用迭代器的删除方法删除
                	Iterator<Employee> iterator = SysUtils.recordEmployee.iterator();  
                    while (iterator.hasNext()) {  
                    	Employee ep = iterator.next();  
                        if (ep.getPayrollNo().equals(v.getPayrollNo())) {  
                            iterator.remove();  
                        }  
                    }
          
                }  
                
                System.out.println("Record deleted\n\n\n");
            } else //取消删除
                System.out.println("Deletion cancel");

            //判断是否继续删除
            System.out.print("Delete another?(y)es or (n)o : ");
            if (!SysUtils.getEntry().trim().equals("y"))
                break;
        }
        //更新文件信息记录

		EmployeeDao.storeRecords(EmployeeDao.DATA_FILE_PATH,SysUtils.recordEmployee);

	}

}
