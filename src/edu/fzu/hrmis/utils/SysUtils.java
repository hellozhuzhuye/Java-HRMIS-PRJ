/**
 *
 */
package edu.fzu.hrmis.utils;

import edu.fzu.hrmis.dao.EmployeeDao;
import edu.fzu.hrmis.domain.Employee;
import edu.fzu.hrmis.domain.User;
import edu.fzu.hrmis.exception.HRMISException;
import edu.fzu.hrmis.ui.UIFactory;
import edu.fzu.hrmis.ui.UIType;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 系统工具类
 *
 * @author joeyang ong
 *
 */
public class SysUtils {


    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    
    /**
     * 储存从文件读取的信息，并且实时跟对文件进行更新
     */
    public static List<Employee> recordEmployee;

 
    /**
     * 员工信息的正则表达式，用来验证用户输入信息是否符合规范
     **/
    public final static String regex_tele = "0[2-8]-[1-9][0-9]{7}";
    public final static String regex_firstname = "[A-Z a-z]*";
    public final static String regex_lastname = "[A-Z a-z]*";
    public final static String regex_initial = "[A-Za-z]";
    public final static String regex_jobTitle = "[A-Z a-z]*";
    public final static String regex_hireDate = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
    public final static String regex_payrollNum = "[0-9]{3}";
    public final static String regex_departNum = "[0-9]{2}";


    /**
     * 要求用户输入的提示信息
     */
    public final static String prompt_payrollNum_enter = "Enter  employee 3  digit  Payroll  Number  (123) : ";
    public final static String prompt_phoneNum_enter = "Enter employee phone Number(02-12345678) : ";
    public final static String prompt_lastName_enter = "Enter employee Last Name : ";
    public final static String prompt_firstName_enter = "Enter employee First Name : ";
    public final static String prompt_middleInit_enter = "Enter employee Middle Init: ";
    public final static String prompt_departNum_enter = "Enter employee Depart Number : ";
    public final static String prompt_jobTitle_enter = "Enter employee Job Title : ";
    public final static String prompt_dateHired_enter = "Enter employee Date Hired : ";


    /**
     * 用户无输入出错的提示信息
     */
    public final static String prompt_payrollNum_error_NoEnter = "No payroll number entered -try again ";
    public final static String prompt_phoneNum_error_NoEnter = "No phone number entered - try again";
    public final static String prompt_lastName_error_NoEnter = "No last name entered - try again ";
    public final static String prompt_firstName_error_NoEnter = "No first name entered - try again ";
    public final static String prompt_middleInit_error_NoEnter = "No Middle Init entered - try again ";
    public final static String prompt_departNum_error_NoEnter = "No depart number entered - try again  ";
    public final static String prompt_jobTitle_error_NoEnter = "No Job title entered - try again ";
    public final static String prompt_dateHired_error_NoEnter = "No date hired entered - try again ";
    public final static String prompt_userName_error_NoEnter = "No username entered - try again ";
    public final static String prompt_userPWD_error_NoEnter = "No password entered - try again ";
    public final static String prompt_realName_error_NoEnter = "No realname entered - try again ";
    /**
     * 用户输入信息格式出错的提示信息
     */
    public final static String prompt_payrollNum_error_WrongEnter = "Payroll number can contain only numberical characters ";
    public final static String prompt_phoneNum_error_WrongEnter = "Invalid phone number  ";
    public final static String prompt_lastName_error_WrongEnter = "Last name can contain only alphabetical characters and spaces ";
    public final static String prompt_firstName_error_WrongEnter = "First name can contain only alphabetical characters and spaces   ";
    public final static String prompt_middleInit_error_WrongEnter = "Middle Init can contain only ONE alphabetical characters and spaces  ";
    public final static String prompt_departNum_error_WrongEnter = "Depart number contain only digit with no space  ";
    public final static String prompt_jobTitle_error_WrongEnter = "Job title can contain only alphabetical characters and spaces  ";
    public final static String prompt_dateHired_error_WrongEnter = "Invalid Date Hired - try again ";
    public final static String prompt_realName_error_WrongEnter = "Real name can contain only alphabetical characters and spaces ";

    /**
     * 从控制台获得一行录入，不允许空输入
     * @return
     */
    public static String getEntry() {
        return getEntry(false);
    }


    /**
     * 暂停录入
     */
    public static void pause() {
        getEntry(true);
    }

    /**
     * 带提示的暂停
     * @param promptStr
     */
    public static void pause(String promptStr) {
        System.out.print(promptStr);
        pause();
    }

    /**
     * 从控制台上获得一行录入
     * @param allowBlank  是否允许空输入(暂停)
     * @return
     */
    private static String getEntry(boolean allowBlank) {

        BufferedReader reader = null;
        String entry = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            entry = reader.readLine();
//            if (!allowBlank && isBlankStr(entry))
//                throw new BlankEntryException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return entry;

    }

    /**
     * 字符串空串检测
     * @param str
     * @return
     */
    public static boolean isBlankStr(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 执行UI部件
     * @param type
     */
    public static void runUI(UIType type) {
        UIFactory.getInstance().getUI(type).setup();
    }

    /**
     * 资源检测
     */
    public static void checkResource() {

        File file = new File("./src/edu/fzu/hrmis/records.txt");

        if (!file.exists())
            throw new HRMISException("Required file – records, does not exist.");

    }
    
    /**
     * 账号密码检测
     */
    public static void checkPassword() {

    	//检查账号密码文件是否存在
        File file = new File(User.DATA_FILE_PATH);
        if (!file.exists())
            throw new HRMISException("Required file – accountAndPassword, does not exist.");
        
        //检查用户名是否存在
        while(true)
        {
        	
            System.out.print("Please enter your user name :");
            
            String userstr=SysUtils.getEntry();   
            if (isBlankStr(userstr))
            {
            	System.out.print("No UserName entered – try again...\n");
            	continue;
            }
            List<User> list = searchByUserName(userstr);

            // 检查是否为空
            if (list.isEmpty()) 
            {
            	//用户名不存在
                System.out.print("\nUser - " + userstr + " - not found – try again…");
                SysUtils.pause("\nPress Enter to continue...");
                continue;
                
            } else 
            {
            	while(true) 
            	{
            		//用户名存在，需要输入密码
            		System.out.print("Please enter your password :");
                	String pwdstr=SysUtils.getEntry();
                	if (isBlankStr(pwdstr))
                    {
                    	System.out.print("No Password entered – try again...\n");
                    	continue;
                    }
                    String encrypwd=EncryptUtil.getSHA256(pwdstr);
                    List<User> plist = searchByUserPassword(encrypwd);
                 // 检查是否为空
                    if (plist.isEmpty()) 
                    {
                    	//密码错误
                        System.out.print("\n\nSorry, the account with this keycode was not found.");
                        SysUtils.pause("\nPress Enter to continue...");
                        continue;
                    } 
                    else
                    {
                    	//密码正确
                    	System.out.print("\nLogin successfully！");
                    	SysUtils.pause("\nPress Enter to continue...");
                        break;
                    }
            	}
                
                
                
            }
            break;
        }
        
        
        

    }
    
    
    /**
     * 判断用户输入员工信息格式是否正确方法
     */
    public static int isRightInput(String last, String format) {
        if (last.trim().equals("")) return 0;//输入为空
        if (last.trim().matches(format)) return 1;//输入正确
        return -1;//输入格式错误
    }

    public static String createPhone() {
        String phone;
        int flag;
        while (true) {
            System.out.print(prompt_phoneNum_enter);
            phone = getEntry();
            flag = isRightInput(phone, regex_tele);
            if (flag == -1) {
                System.out.println(prompt_phoneNum_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_phoneNum_error_NoEnter);
                continue;
            }
            break;
        }
        return phone;
    }

    //要求用户输入一个姓氏，并判断输入是否正确
    public static String createLastName() {
        String last;
        int flag;
        while (true) {
            System.out.print(prompt_lastName_enter);
            last = getEntry();
            flag = isRightInput(last, regex_lastname);
            if (flag == -1) {
                System.out.println(prompt_lastName_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_lastName_error_NoEnter);
                continue;
            }
            break;
        }
        return last;
    }

    //要求用户输入一个名字，并判断输入是否正确
    public static String createFirstName() {
        String first;
        int flag;
        while (true) {
            System.out.print(prompt_firstName_enter);
            first = getEntry();
            flag = isRightInput(first, regex_firstname);
            if (flag == -1) {
                System.out.println(prompt_firstName_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_firstName_error_NoEnter);
                continue;
            }
            break;
        }
        return first;
    }

    //要求用户输入一个员工号，并判断输入是否正确
    public static String createPayrollNum() {
        String pay;
        int flag;
        while (true) {
            System.out.print(prompt_payrollNum_enter);
            pay = getEntry();
            flag = isRightInput(pay, regex_payrollNum);
            if (flag == -1) {
                System.out.println(prompt_payrollNum_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_payrollNum_error_NoEnter);
                continue;
            }
            break;
        }
        return pay;
    }

    //要求用户输入一个雇用日期，并判断输入是否正确
    public static String createHireDate() {
        String date;
        int flag;
        while (true) {
            System.out.print(prompt_dateHired_enter);
            //输入日期
            date = getEntry();

            //判断输入的日期格式是否出错
            flag = isRightInput(date, regex_hireDate);

            if (flag == -1) {//格式出错
                System.out.println(prompt_dateHired_error_WrongEnter);
                continue;
            } else if (flag == 0) {//没有输入
                System.out.println(prompt_dateHired_error_NoEnter);
                continue;
            } else if (!isLegalDate(date)) {//格式出错
                System.out.println(prompt_dateHired_error_WrongEnter);
                continue;
            }
            //没有错误，跳出循环
            break;
        }
        return date;
    }

    //要求用户输入一个工作名称，并判断输入是否正确
    public static String createJobTiitle() {
        String title;
        int flag;
        while (true) {
            System.out.print(prompt_jobTitle_enter);
            title = getEntry();
            flag = isRightInput(title, regex_jobTitle);
            if (flag == -1) {
                System.out.println(prompt_jobTitle_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_jobTitle_error_NoEnter);
                continue;
            }
            break;
        }
        return title;
    }

    //要求用户输入一个公寓号，并判断输入是否正确
    public static String createDepartNum() {
        String partNum;
        int flag;
        while (true) {
            System.out.print(prompt_departNum_enter);
            partNum = getEntry();
            flag = isRightInput(partNum, regex_departNum);
            if (flag == -1) {
                System.out.println(prompt_departNum_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_departNum_error_NoEnter);
                continue;
            }
            break;
        }
        return partNum;
    }

    //要求用户输入一个中间名，并判断输入是否正确
    public static String createInitial() {
        String init;
        int flag;
        while (true) {
            System.out.print(prompt_middleInit_enter);
            init = getEntry();
            flag = isRightInput(init, regex_initial);
            if (flag == -1) {
                System.out.println(prompt_middleInit_error_WrongEnter);
                continue;
            } else if (flag == 0) {
                System.out.println(prompt_middleInit_error_NoEnter);
                continue;
            }
            break;
        }
        return init;
    }

    public static boolean isLegalDate(String date) {
        if (date.equals("")) return false;
        int[] perMon = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year, month, day;
        String[] hireDate = date.split("-");
        day = Integer.parseInt(hireDate[0]);
        month = Integer.parseInt(hireDate[1]);
        year = Integer.parseInt(hireDate[2]);
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            perMon[2] = 29;
        else perMon[2] = 28;
        if (month > 12 | day > perMon[month] | year < 1996)
            return false;
        return true;
    }

    public static List<Employee> searchByPayrollNum(String pay) {
        if (pay == null) return null;       
        List<Employee> empList = EmployeeDao.loadEmps();
        List<Employee> list = new ArrayList<Employee>();
        for (Employee e : empList)
            if (pay.equals(e.getPayrollNo()))
                list.add(e);
        return list;
    }

    public static List<Employee> searchByLastName(String entry) {
        List<Employee> empList = EmployeeDao.loadEmps();
        String str = null;
        List<Employee> list = new ArrayList<Employee>();
        for (Employee empItem :
                empList) {
            str = empItem.getLastName();
            if (entry.toLowerCase().equals(str.toLowerCase())) {
                list.add(empItem);
            }
        }
        return list;

    }
	


    public static List<User> searchByUserPassword(String pwd) {
        if (pwd == null) return null;       
        List<User> userList = User.loadUsers();
        List<User> list = new ArrayList<User>();
        for (User e : userList)
            if (pwd.equals(e.getUserPwd()))
                list.add(e);
        return list;
    }
    
    public static List<User> searchByUserName(String pwd) {
        if (pwd == null) return null;       
        List<User> userList = User.loadUsers();
        List<User> list = new ArrayList<User>();
        for (User e : userList)
            if (pwd.equals(e.getUserNo()))
                list.add(e);
        return list;
    }
	
}
