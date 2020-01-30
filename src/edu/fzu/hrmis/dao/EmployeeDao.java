/**
 *
 */
package edu.fzu.hrmis.dao;

import java.io.*;
import java.util.*;

import edu.fzu.hrmis.domain.Employee;

/**
 *
 * DAO: Data Access Object 
 * EmployeeDao 将负责员工信息的增删改查操作
 *
 * @author joeyang ong
 *
 */
public class EmployeeDao {

	public static final String DATA_FILE_PATH = "./src/edu/fzu/hrmis/records.txt";

    public static List<Employee> loadEmps() {

        BufferedReader reader = null;
        String entry = null;
        List<Employee> empList = new ArrayList<Employee>();

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(DATA_FILE_PATH)));
            while ((entry = reader.readLine()) != null) {
//				System.out.println(entry);
                //字符串 -> Employee对象？
                empList.add(Employee.getEmployeeFromStr(entry));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return empList;

    }

    /**
     * 获得按照lastname属性排序的Employee信息序列
     * @return
     */
    public Set<Employee> loadSortedEmps() {
        return new TreeSet<Employee>(EmployeeDao.loadEmps());
    }

    /**
     * 将改变后的信息存进文件
     */
    public static void storeRecords(String path,List<Employee> list) {
        List<String> info = new ArrayList<String>();
        for (Employee e : list)
            info.add(e.toString());
       writeTxt(info, path);
    }


    public static void writeTxt(List<String> list, String path) {
        //用缓冲流写入数据
        BufferedWriter writer = null;
        File file = new File(path);
        //判断文件是否存在，不存在就创建一个
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            writer = new BufferedWriter(new FileWriter(file));
            //逐行写入数据，每次写入一行后加上\r\n换行
            for (String e : list)
                writer.write(e + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭缓冲流
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
