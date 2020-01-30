package edu.fzu.hrmis.ui;


import edu.fzu.hrmis.domain.Employee;
import edu.fzu.hrmis.utils.SysUtils;


import java.util.List;

public class EmpSearchedUI implements BaseUI {
    @Override
    public void setup() {
        int flag;
        while (true) {
            System.out.print("Enter keyword:");
            String entry = SysUtils.getEntry();
            flag = SysUtils.isRightInput(entry, SysUtils.regex_lastname);
            if (flag == 0) {
                System.out.print("\nNo keyword entered – try again…\n");
                continue;
            }
            // 查找LastName
            List<Employee> list = SysUtils.searchByLastName(entry);

            // 检查是否为空

            if (list.isEmpty()) {
                System.out.print("\n\nKeyword - " + entry + " - not found");
                SysUtils.pause("\nPress Enter to continue...");
                break;
            } else {
                // 输出信息
                System.out.println("\n");
                for (Employee e : list)
                    System.out.println(e.toString());
                SysUtils.pause("\nPress Enter to continue...");
                break;
            }
        }
    }

  
}
