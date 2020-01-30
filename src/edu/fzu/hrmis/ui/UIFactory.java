/**
 *
 */
package edu.fzu.hrmis.ui;

/**
 *
 * UI部件工厂
 * @author joeyang ong
 *
 */
public class UIFactory {

    private static final UIFactory FACTORY = new UIFactory();

    public static UIFactory getInstance() {
        return FACTORY;
    }

    private UIFactory() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 获得UI部件
     * @param type
     * @return
     */
    public BaseUI getUI(UIType type) {

        BaseUI componentUI = null;

        if (type.equals(UIType.MainMenuUI))
            return new MainMenuUI();
        else if (type.equals(UIType.EmpListUI))
            return new EmpListUI();
        else if (type.equals(UIType.EmpSortedListUI))
            return new EmpSortedListUI();
        else if (type.equals(UIType.EmpSimpleListUI))
            return new EmpSimpleListUI();
        else if (type.equals(UIType.EmpSimpleSortedListUI))
            return new EmpSimpleSortedListUI();
        else if (type.equals(UIType.EmpAddUI))
            return new EmpAddUI();
        else if (type.equals(UIType.EmpSearchedListUI))
            return new EmpSearchedUI();
        else if (type.equals(UIType.EmpDeleteUI))
            return new EmpDeleteUI();
        else if (type.equals(UIType.EmpUpdateUI))
            return new EmpUpdateUI();
        else if (type.equals(UIType.UserAddUI))
            return new UserAddUI();

        return componentUI;

    }

}
