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
 * Ա���޸Ľ���
 * 
 * @author 82607
 *
 */
public class EmpUpdateUI implements BaseUI {

	@Override
	public void setup() {

		while (true) {
			boolean flag = false;		
			// ����Ա���Ų���Ա����Ϣ
			String keyword = SysUtils.createPayrollNum();

			// ����Ա����Ϣ
			List<Employee> e = SysUtils.searchByPayrollNum(keyword);
			SysUtils.recordEmployee = EmployeeDao.loadEmps();

			if (e.isEmpty()) {
				// û����Ҫ���ҵ�Ա����Ϣ
				System.out.print("\n\nEmployee record for " + keyword + " not found!\n");
				SysUtils.pause("\nPress Enter to continue...");
				break;
			}

			// ��ʾ���ҵ���Ա����Ϣ����ȷ���Ƿ�Ҫ�޸�
			for (Employee v : e)
				System.out.println("\n\n" + v.toString());
			System.out.print("Confirm record modification ,(y)es or (n)o : ");
			if (SysUtils.getEntry().equals("y")) { // �����޸Ĳ���
				while (true) {
					String oldno = null;
					// ������пɹ�ѡ����޸���
					System.out.print("Which do you want to modify :\n" + "======================================\n\n"
							+ "1 - Employee 3 digit payroll number\n" + "2 - Phone Number\n" + "3 - Last Name\n"
							+ "4 - First Name\n" + "5 - Middle Init\n" + "6 - Dept #\n" + "7 - Job Title\n"
							+ "8 - Date Hired\n" + "9 - Cancel\n\n" + "Your selection : ");

					// ��ȡ�û�ѡ��
					String choice = SysUtils.getEntry();

					flag = false;
					for (Employee v : e) {
						if ("1".equals(choice)) {
							// �����µ�Ա��������ɵ�
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
							// �����µĵ绰��������ɵ�
						{
							oldno = v.getPayrollNo();
							v.setTelephoneCode(SysUtils.createPhone());
						}
							

						else if ("3".equals(choice))
							// �����µ���������ɵģ�����Ӧ��û�˻����׸ı��Լ�������
						{
							v.setLastName(SysUtils.createLastName());
							oldno = v.getPayrollNo();
						}
							

						else if ("4".equals(choice))
							// �����µ���������ɵ�
						{
							v.setFirstName(SysUtils.createFirstName());
							oldno = v.getPayrollNo();
						}
							

						else if ("5".equals(choice))
							// �����µ��м�������ɵ�
						{
							v.setInitial(SysUtils.createInitial());
							oldno = v.getPayrollNo();
						}
							

						else if ("6".equals(choice))
							// �����µĹ�Ԣ������ɵ�
						{
							v.setDeptNo(Integer.parseInt(SysUtils.createDepartNum()));
							oldno = v.getPayrollNo();
						}
							

						else if ("7".equals(choice))
							// �����µĹ�����������ɵ�
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
							// �������޸�
							break;
						else {
							// ѡ���޸������
							System.out.print("Invalid code! - try again\n");
							continue;
						}
					}

					// ʹ�õ�������ɾ������ɾ��
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

					// �ж��Ƿ�����޸�
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

			// ���޸������Ϣ�����ļ���Ϣ
			EmployeeDao.storeRecords(EmployeeDao.DATA_FILE_PATH, SysUtils.recordEmployee);
			break;
		}

	}

}
