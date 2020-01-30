/**
 * 
 */
package edu.fzu.hrmis.ui;

import edu.fzu.hrmis.domain.*;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.utils.SysUtils;

/**
 * 员工修改界面
 * 
 * @author 82607
 *
 */
public class EmpUpdateUI implements BaseUI {

	@Override
	public void setup() {

		while (true) {
			boolean flag = false;		
			// 输入员工号查找员工信息
			String keyword = SysUtils.createPayrollNum();

			// 查找员工信息
			List<Employee> e = SysUtils.searchByPayrollNum(keyword);
			SysUtils.recordEmployee = EmployeeDao.loadEmps();

			if (e.isEmpty()) {
				// 没有所要查找的员工信息
				System.out.print("\n\nEmployee record for " + keyword + " not found!\n");
				SysUtils.pause("\nPress Enter to continue...");
				break;
			}

			// 显示所找到的员工信息，并确认是否要修改
			for (Employee v : e)
				System.out.println("\n\n" + v.toString());
			System.out.print("Confirm record modification ,(y)es or (n)o : ");
			if (SysUtils.getEntry().equals("y")) { // 进入修改部分
				while (true) {
					String oldno = null;
					// 输出所有可供选择的修改项
					System.out.print("Which do you want to modify :\n" + "======================================\n\n"
							+ "1 - Employee 3 digit payroll number\n" + "2 - Phone Number\n" + "3 - Last Name\n"
							+ "4 - First Name\n" + "5 - Middle Init\n" + "6 - Dept #\n" + "7 - Job Title\n"
							+ "8 - Date Hired\n" + "9 - Cancel\n\n" + "Your selection : ");

					// 获取用户选择
					String choice = SysUtils.getEntry();

					flag = false;
					for (Employee v : e) {
						if ("1".equals(choice)) {
							// 输入新的员工号替代旧的
							choice = SysUtils.createPayrollNum();
							
							oldno = v.getPayrollNo();
							if (SysUtils.searchByPayrollNum(choice).isEmpty()) {
								v.setPayrollNo(choice);

							}

							else {
								flag = true;
								System.out.println("\n\nThe payroll number : " + choice + "  already exist ");
							}
						} else if ("2".equals(choice))
							// 输入新的电话号码替代旧的
						{
							oldno = v.getPayrollNo();
							v.setTelephoneCode(SysUtils.createPhone());
						}
							

						else if ("3".equals(choice))
							// 输入新的姓氏替代旧的，不过应该没人会轻易改变自己的姓氏
						{
							v.setLastName(SysUtils.createLastName());
							oldno = v.getPayrollNo();
						}
							

						else if ("4".equals(choice))
							// 输入新的名字替代旧的
						{
							v.setFirstName(SysUtils.createFirstName());
							oldno = v.getPayrollNo();
						}
							

						else if ("5".equals(choice))
							// 输入新的中间名替代旧的
						{
							v.setInitial(SysUtils.createInitial());
							oldno = v.getPayrollNo();
						}
							

						else if ("6".equals(choice))
							// 输入新的公寓号替代旧的
						{
							v.setDeptNo(Integer.parseInt(SysUtils.createDepartNum()));
							oldno = v.getPayrollNo();
						}
							

						else if ("7".equals(choice))
							// 输入新的工作名称替代旧的
						{
							v.setJobTitle(SysUtils.createJobTiitle());
							oldno = v.getPayrollNo();
						}
							

						else if ("8".equals(choice))
							try {
								oldno = v.getPayrollNo();
								v.setHiringDate(SysUtils.SDF.parse(SysUtils.createHireDate()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if ("9".equals(choice))
							// 不进行修改
							break;
						else {
							// 选择修改项错误
							System.out.print("Invalid code! - try again\n");
							continue;
						}
					}

					// 使用迭代器的删除方法删除
					Iterator<Employee> iterator = SysUtils.recordEmployee.iterator();
					while (iterator.hasNext()) {
						Employee ep = iterator.next();
						if (ep.getPayrollNo().equals(oldno)) {
							iterator.remove();

						}
					}

					for (Employee v : e) {
						SysUtils.recordEmployee.add(v);
					}

					// 判断是否继续修改
					if (!flag) {
						System.out.print("\nModified success:\n");
						System.out.print(e.toString());
					}

					System.out.print("\n\nmodify another infomation?(y)es or (n)o :");
					if (!SysUtils.getEntry().equals("y")) {
						System.out.println("Modify end and Save successfully.");
						SysUtils.pause("\nPress Enter to continue...");
						break;
					}
				}

			}

			// 用修改完的信息更新文件信息
			EmployeeDao.storeRecords(EmployeeDao.DATA_FILE_PATH, SysUtils.recordEmployee);
			break;
		}

	}

}
