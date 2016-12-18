package BUSUI;

import BUSCONTROL.MaintainDslocationControl;
import BUSCONTROL.MaintainStaffControl;
import BUSDOMAIN.Dslocation;
import BUSDOMAIN.Staff;
import java.awt.*;
import java.awt.Event.*;
import javax.swing.*;

public class ViewLocation extends JFrame {

    private JScrollPane jinfo;
    private JPanel jpinfo = new JPanel();

    public ViewLocation(String id) {
        MaintainDslocationControl mdc = new MaintainDslocationControl();
        Dslocation Dslocation = mdc.selectRecord(id);
        jpinfo.add(new JLabel(Dslocation.toString()));
        jinfo = new JScrollPane(jpinfo);
        add(jinfo);
        jpinfo.setBackground(new Color(250, 250, 250));
        setTitle("GoToMalaysia - Location Information");
        setSize(370, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ViewLocation frame = new ViewLocation("D00006");

    }

}
