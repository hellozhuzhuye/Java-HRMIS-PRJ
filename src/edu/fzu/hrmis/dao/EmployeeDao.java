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
 * EmployeeDao ������Ա����Ϣ����ɾ�Ĳ����
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
                //�ַ��� -> Employee����
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
     * ��ð���lastname���������Employee��Ϣ����
     * @return
     */
    public Set<Employee> loadSortedEmps() {
        return new TreeSet<Employee>(EmployeeDao.loadEmps());
    }

    /**
     * ���ı�����Ϣ����ļ�
     */
    public static void storeRecords(String path,List<Employee> list) {
        List<String> info = new ArrayList<String>();
        for (Employee e : list)
            info.add(e.toString());
       writeTxt(info, path);
    }


    public static void writeTxt(List<String> list, String path) {
        //�û�����д������
        BufferedWriter writer = null;
        File file = new File(path);
        //�ж��ļ��Ƿ���ڣ������ھʹ���һ��
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            writer = new BufferedWriter(new FileWriter(file));
            //����д�����ݣ�ÿ��д��һ�к����\r\n����
            for (String e : list)
                writer.write(e + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //�رջ�����
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
