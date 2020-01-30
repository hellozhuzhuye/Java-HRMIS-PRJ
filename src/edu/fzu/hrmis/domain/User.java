package edu.fzu.hrmis.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * �����û�
 *
 * @author joeyang ong
 */
public class User extends ValueObject {
    /**
     * �ļ�·��
     */
	public static final String DATA_FILE_PATH="./src/edu/fzu/hrmis/accountAndPassword.txt";
    /**
     * �û����
     */
    private String userNo;

    /**
     * �û�����
     */
    private String userPwd;

    /**
     * ��ʵ����
     */
    private String realName;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * ��ȡ�û���Ϣ�ַ���������֮���γ��û�����
     *
     * @param str ��Ϣ�ַ���
     *            User.getEmployeeFromStr("admin:92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e:Tester"):
     * @return
     */
    public static User getUserFromStr(String str) {

        String[] sections = str.split("\\:");
        User us = new User();

        us.setUserNo(sections[0]);
        us.setUserPwd(sections[1]);
        us.setRealName(sections[2]);
              
        return us;

    }
    public static List<User> loadUsers() {

        BufferedReader reader = null;
        String entry = null;
        List<User> userList = new ArrayList<User>();

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(DATA_FILE_PATH)));
            while ((entry = reader.readLine()) != null) {
//				System.out.println(entry);
                //�ַ��� -> User����
                userList.add(getUserFromStr(entry));
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

        return userList;

    }
    
    @Override
    public String toString() {
        return String.format("%s:%s:%s", this.userNo
                , this.userPwd
                , this.realName);
    }

}
