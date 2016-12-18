/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSUI;

import BUSDOMAIN.*;
import BUSCONTROL.*;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class Login extends JFrame {

    private JScrollPane jsPane;
    private JLabel[] jlSide = new JLabel[2];
    private JButton jbtLogin;
    private JButton jbtForgot;
    private Image imgBg = null;
    private JTextField jtfUserName = new JTextField();
    private JPasswordField jpfPassword = new JPasswordField();
    private MaintainStaffControl stfControl = new MaintainStaffControl();
    int count = 0;

    public Login() {
        try {
            imgBg = ImageIO.read(this.getClass().getResource("../images/background/bg32.jpg"));;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Image of login cannot be displayed.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        JPanel jpbg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension d = getSize();
                Graphics2D g2 = (Graphics2D) g;
                g2.fill(new Rectangle(0, 0, d.width, d.height));
                g.drawImage(imgBg, 0, 0, d.width, d.height, null);
            }
        };
        jsPane = new JScrollPane(jpbg);
        jpbg.setBorder(BorderFactory.createEmptyBorder(140, 200, 120, 200));

        JPanel jpLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(150, 150);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
                graphics.setColor(Color.WHITE);
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border}
            }
        };
        jpLogin.setOpaque(true);
        jpLogin.setBackground(new Color(0, 0, 0, 0));
        jpLogin.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        jpLogin.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        ImageIcon imgIcon = null;
        ImageIcon busImg = null;
        try {
            imgIcon = new ImageIcon(ImageIO.read(getClass().getResource("../images/busIcon.png")));
            busImg = new ImageIcon((imgIcon.getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
        } catch (Exception ioe) {
            JOptionPane.showMessageDialog(null, "Image of bus icon cannot be displayed.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        JLabel busLabel = new JLabel(busImg);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 0;
        jpLogin.add(busLabel, c);

        ImageIcon usernameIcon = null;
        ImageIcon usernameImg = null;
        try {
            usernameIcon = new ImageIcon(ImageIO.read(getClass().getResource("../images/username.png")));
            usernameImg = new ImageIcon((usernameIcon.getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        } catch (Exception ioe) {
            JOptionPane.showMessageDialog(null, "Image of username icon cannot be displayed.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        JLabel usernameLabel = new JLabel(usernameImg);
        usernameLabel.setLabelFor(jtfUserName);
        c.insets = new Insets(10, 10, 10, 10);
        c.ipady = 20;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        jpLogin.add(usernameLabel, c);

        jtfUserName.setUI(new PlaceholderUI("Username", true));
        jtfUserName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jtfUserName.setFont(new Font("Arial", Font.PLAIN, 12));
        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 1;
        jpLogin.add(jtfUserName, c);

        ImageIcon pwIcon = null;
        ImageIcon pwImg = null;
        try {
            pwIcon = new ImageIcon(ImageIO.read(getClass().getResource("../images/password.png")));
            pwImg = new ImageIcon((pwIcon.getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        } catch (Exception ioe) {
            JOptionPane.showMessageDialog(null, "Image of password icon cannot be displayed.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        JLabel pwLabel = new JLabel(pwImg);
        usernameLabel.setLabelFor(jpfPassword);
        c.ipady = 20;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        jpLogin.add(pwLabel, c);

        jpfPassword.setUI(new PasswordHolderUI("Password"));
        jpfPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 2;
        jpLogin.add(jpfPassword, c);

        jbtForgot = new JButton("Forgot Password");
        c.ipady = 10;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 3;
        jpLogin.add(jbtForgot, c);

        jbtLogin = new JButton("Sign in");
        c.insets = new Insets(10, 10, 10, 10);
        c.ipady = 10;
        c.gridx = 3;
        c.gridwidth = 1;
        c.gridy = 3;
        jpLogin.add(jbtLogin, c);

        jbtForgot.setForeground(Color.WHITE);
        jbtForgot.setBackground(new Color (66,165,245));

        jbtLogin.setForeground(Color.WHITE);
        jbtLogin.setBackground(new Color(255, 102, 0));

        //Program code
        jbtLogin.addActionListener(new SignInListener());
        jbtForgot.addActionListener(new ForgotPasswordListener());
        //
        jpbg.add(jpLogin);
        add(jsPane);
        setTitle("GoToMalaysia - Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class SignInListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String username = jtfUserName.getText();
                Staff stf = stfControl.selectRecordbyUsername(username);

                if (stf != null && jpfPassword.getText().equals(stf.getPassword())) {
                    dispose();
//                    ChooseCounter f = new ChooseCounter(stf);
                    count = 0;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid user ID or password", "Staff Not Found", JOptionPane.ERROR_MESSAGE);
                    jpfPassword.setText("");
                    closeProgram();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid user ID or password", "Staff Not Found", JOptionPane.ERROR_MESSAGE);
                closeProgram();
            }
        }
    }

    private class ForgotPasswordListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new StaffForgetPassword();
            
        }
    }

    public void closeProgram() {

        count++;
        if (count == 3) {
            JOptionPane.showMessageDialog(null, "You've typed the invalid username or password for 3 times!!!", "Closing Program...", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Login panel = new Login();

    }

}
