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
 * Ա��ɾ������
 * @author 82607
 *
 */
public class EmpDeleteUI implements BaseUI {

	@Override
	public void setup() {
		
		while (true) {			
			
            //����˵�
            System.out.println("Adfaith Consulting - Employee Records : \n"
                    + "============================================\n\n\n"
                    + "Empoyee Record Deletion : \n\n");

            //����Ա���Ų���Ա����Ϣ
            String keyword = SysUtils.createPayrollNum();
            
          
            //����Ա����Ϣ
            List<Employee> e = SysUtils.searchByPayrollNum(keyword);
            SysUtils.recordEmployee = EmployeeDao.loadEmps();
            
            
            
            if (e.isEmpty()) {
                //û����Ҫ���ҵ�Ա����Ϣ
                System.out.print("\n\nEmployee record for " + keyword + " not found!\n");
                SysUtils.pause("\nPress Enter to continue...");
                break;
            }
            
            //��ʾ���ҵ���Ա����Ϣ����ȷ���Ƿ�Ҫɾ��
            for (Employee v : e)
                System.out.println("\n\n" + v.toString());
            System.out.print("Confirm record deletion ,(y)es or (n)o : ");

            if (SysUtils.getEntry().equals("y")) {
                
            	//ɾ�������ҵ���Ա����Ϣ           	
                for (Employee v : e)
                {
                	//ʹ�õ�������ɾ������ɾ��
                	Iterator<Employee> iterator = SysUtils.recordEmployee.iterator();  
                    while (iterator.hasNext()) {  
                    	Employee ep = iterator.next();  
                        if (ep.getPayrollNo().equals(v.getPayrollNo())) {  
                            iterator.remove();  
                        }  
                    }
          
                }  
                
                System.out.println("Record deleted\n\n\n");
            } else //ȡ��ɾ��
                System.out.println("Deletion cancel");

            //�ж��Ƿ����ɾ��
            System.out.print("Delete another?(y)es or (n)o : ");
            if (!SysUtils.getEntry().trim().equals("y"))
                break;
        }
        //�����ļ���Ϣ��¼

		EmployeeDao.storeRecords(EmployeeDao.DATA_FILE_PATH,SysUtils.recordEmployee);

	}

}
