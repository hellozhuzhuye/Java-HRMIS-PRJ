/**
 * 
 */
package edu.fzu.hrmis.ui;

import java.util.ArrayList;
import java.util.List;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.User;
import edu.fzu.hrmis.utils.EncryptUtil;
import edu.fzu.hrmis.utils.SysUtils;

/**
 * @author XJX 用户添加界面
 */
public class UserAddUI implements BaseUI {

	public static final String DATA_FILE_PATH = "./src/edu/fzu/hrmis/accountAndPassword.txt";

	@Override
	public void setup() {
		User us = new User();
		int flag;
		while (true) {
			// 输出提示菜单
			System.out.println("\nPlease input UserName what you want to add:");

			String uname = SysUtils.getEntry();
			flag= SysUtils.isRightInput(uname, SysUtils.regex_lastname);
			if (flag == 0)
			{
				System.out.println(SysUtils.prompt_userName_error_NoEnter);
				continue;
			}
			if (!SysUtils.searchByUserName(uname).isEmpty()) {
				System.out.println("The UserName :  " + uname + "already exist - try again");
				SysUtils.pause("\nPress Enter to continue...");
				continue;
			}
			us.setUserNo(uname);
			while(true)
			{
				System.out.println("\nPlease input Password :");
				
				String pwd = SysUtils.getEntry();
				flag= SysUtils.isRightInput(pwd, SysUtils.regex_lastname);
				if (flag == 0)
				{
					System.out.println(SysUtils.prompt_userPWD_error_NoEnter);
					continue;
				}
				pwd = EncryptUtil.getSHA256(pwd);
				us.setUserPwd(pwd);
				break;
			}
			while(true)
			{
				System.out.println("\nPlease input RealName :");
				String real = SysUtils.getEntry();
				flag= SysUtils.isRightInput(real, SysUtils.regex_lastname);
				if (flag != 1)
				{
					if(flag == 0) {
					System.out.println(SysUtils.prompt_realName_error_NoEnter);
					continue;
					}else {
						System.out.println(SysUtils.prompt_realName_error_WrongEnter);
						continue;
						
					}
				}
				us.setRealName(real);
				break;
			}
			
			List<User> list = User.loadUsers();
			list.add(us);
			storeRecords(DATA_FILE_PATH, list);
			System.out.println("\nAdded Users Successfully!\n");
			SysUtils.pause("\nPress Enter to continue...");
			break;
		
		}

	}

	/**
	 * 将改变后的信息存进文件
	 */
	public static void storeRecords(String path, List<User> list) {
		List<String> info = new ArrayList<String>();
		for (User e : list)
			info.add(e.toString());
		EmployeeDao.writeTxt(info, path);
	}

}
