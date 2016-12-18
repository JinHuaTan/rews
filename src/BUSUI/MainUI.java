/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSUI;

import BUSDOMAIN.Counter;
import BUSDOMAIN.Staff;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class MainUI extends JFrame {

    private JPanel jpHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel jpBody = new JPanel(new GridLayout(2, 2, 10, 10));
    private JPanel jpTicket = new JPanel();
    private JPanel jpBus = new JPanel();
    private JPanel jpStaff = new JPanel();
    private JPanel jpReport = new JPanel();

    private JPanel jpContent = new JPanel(new BorderLayout());
    private JScrollPane JSPane;
    private JScrollPane jsContent;
    private JPanel jpBg = new JPanel(new BorderLayout());

    public MainUI(Counter ds, Staff s) {
        ImageIcon icon = createImageIcon("../images/GreyIcon/dashboard.png", 30, 30);
        JButton j = new JButton(" Dashboard", icon);
        j.setAlignmentX(LEFT_ALIGNMENT);
        j.setBackground(Color.WHITE);
        j.setForeground(new Color(117, 117, 117));
        j.setBorder(null);
        j.setFont(new Font("Arial", Font.BOLD, 30));
        jpHeader.add(j);

        jpTicket.setLayout(new GridBagLayout());
        jpBus.setLayout(new GridBagLayout());
        jpStaff.setLayout(new GridBagLayout());
        jpReport.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //tickets panel
        ImageIcon ticketIcon = createImageIcon("../images/tickets.png", 180, 180);
        c.insets = new Insets(10, 10, 10, 10);
        ImageIcon ticketJbt = createImageIcon("../images/ticket-jbt.png", 50, 50);
        JButton jbtTPurchase = new JButton("Ticket Info / Purchasing", ticketJbt) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20);
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
        jbtTPurchase.setForeground(Color.WHITE);
        jbtTPurchase.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        labelLayout(jbtTPurchase);
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        jpTicket.add(jbtTPurchase, c);
        ImageIcon ChangeJbt = createImageIcon("../images/changeCancel.png", 50, 50);
        JButton jbtTChange = new JButton("Refund / Cancellation of ticket", ChangeJbt) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20);
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
        jbtTChange.setForeground(Color.WHITE);
        jbtTChange.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        labelLayout(jbtTChange);
        c.gridx = 1;
        c.gridy = 2;

        jpTicket.add(jbtTChange, c);
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 2;
        jpTicket.add(new JLabel(ticketIcon), c);
        jpBody.add(jpTicket);

        //busses
        GridBagConstraints d = new GridBagConstraints();
        d.fill = GridBagConstraints.HORIZONTAL;
        ImageIcon busIcon = createImageIcon("../images/busses.png", 240, 180);
        d.insets = new Insets(10, 30, 10, 10);
        d.gridx = 1;
        d.gridy = 1;
        d.gridheight = 3;
        jpBus.add(new JLabel(busIcon), d);

        //bus button
        ImageIcon jbtBusI = createImageIcon("../images/bus.png", 50, 50);
        JButton jbtBus = new JButton("Bus", jbtBusI) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
                graphics.setColor(Color.BLACK);
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border}
            }
        };
        jbtBus.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        labelLayout(jbtBus);
        d.gridx = 2;
        d.gridy = 1;
        d.gridwidth = 2;
        d.gridheight = 1;

        jpBus.add(jbtBus, d);

        //trip button
        ImageIcon jbtTripI = createImageIcon("../images/trip.png", 50, 50);
        JButton jbtTrip = new JButton("Trip", jbtTripI) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(160, 160);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
                graphics.setColor(Color.BLACK);
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border}
            }
        };
        jbtTrip.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        labelLayout(jbtTrip);
        d.gridwidth = 1;
        d.gridx = 2;
        d.gridy = 2;
        jpBus.add(jbtTrip, d);

        //route button
        ImageIcon jbtRouteI = createImageIcon("../images/route.png", 50, 50);
        JButton jbtRoute = new JButton("Route", jbtRouteI) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(160, 160);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
                graphics.setColor(Color.BLACK);
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border}
            }
        };
        jbtRoute.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        labelLayout(jbtRoute);
        d.gridx = 3;
        d.gridy = 2;
        jpBus.add(jbtRoute, d);
        jpBody.add(jpBus);

        //staff
        ImageIcon staffIcon = createImageIcon("../images/staffs.png", 240, 160);
        c.insets = new Insets(10, 10, 10, 20);
        ImageIcon staffJbt = createImageIcon("../images/staffInfo.png", 50, 50);
        JButton jbtStaff = new JButton("Staff Info", staffJbt) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(180, 180);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
                graphics.setColor((new Color(41, 128, 185)));
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border}
            }
        };
        jbtStaff.setForeground((new Color(41, 128, 185)));
        jbtStaff.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        labelLayout(jbtStaff);
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        jpStaff.add(jbtStaff, c);

        //security panel
        ImageIcon securityJbt = createImageIcon("../images/security.png", 50, 50);
        JButton jbtSecurity = new JButton("P&C Info", securityJbt) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(180, 180);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
                graphics.setColor((new Color(41, 128, 185)));
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border}
            }
        };
        jbtSecurity.setForeground((new Color(41, 128, 185)));
        jbtSecurity.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        labelLayout(jbtSecurity);
        c.gridx = 2;
        c.gridy = 2;

        jpStaff.add(jbtSecurity, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 2;
        jpStaff.add(new JLabel(staffIcon), c);
        jpBody.add(jpStaff);

        //report
        GridBagConstraints e = new GridBagConstraints();
        e.fill = GridBagConstraints.HORIZONTAL;
        ImageIcon reportIcon = createImageIcon("../images/report.png", 180, 180);
        e.insets = new Insets(5, 20, 5, 10);
        e.gridx = 1;
        e.gridy = 1;
        e.gridheight = 3;
        jpReport.add(new JLabel(reportIcon), e);

        //Transaction button
        ImageIcon jbtTransI = createImageIcon("../images/trans.png", 50, 50);
        JButton jbtTransaction = new JButton("Transaction Report", jbtTransI) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20);
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
        jbtTransaction.setForeground(Color.WHITE);
        jbtTransaction.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        labelLayoutReport(jbtTransaction);
        e.gridx = 2;
        e.gridy = 1;
        e.gridheight = 1;

        jpReport.add(jbtTransaction, e);

        //Exception button
        ImageIcon jbtExpI = createImageIcon("../images/exception.png", 50, 50);
        JButton jbtException = new JButton("Exception Report", jbtExpI) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20);
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
        jbtException.setForeground(Color.WHITE);
        jbtException.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        labelLayoutReport(jbtException);
        e.gridx = 2;
        e.gridy = 2;
        jpReport.add(jbtException, e);

        //Summary Report button
        ImageIcon jbtSumI = createImageIcon("../images/summary.png", 50, 50);
        JButton jbtSummary = new JButton("Summary Report", jbtSumI) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20);
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
        jbtSummary.setForeground(Color.WHITE);
        jbtSummary.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        labelLayoutReport(jbtSummary);
        e.gridx = 2;
        e.gridy = 3;

        jpReport.add(jbtSummary, e);
        jpBody.add(jpReport);

        jbtTPurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                TicketTripInfo f = new TicketTripInfo(ds, s);
                dispose();
            }
        });

        jbtTChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    RefundCancelMain f = new RefundCancelMain(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        jbtBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    CRUDBus f = new CRUDBus(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        jbtRoute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    CRUDRoute f = new CRUDRoute(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtTrip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    CRUDTrip f = new CRUDTrip(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    StaffCRUD f = new StaffCRUD(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSecurity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                StaffPNCMain f = new StaffPNCMain(ds, s);
                dispose();
            }
        });

        jbtException.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    ExceptionReportSelection f = new ExceptionReportSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    TransactioReportSelection f = new TransactioReportSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    SummaryReportSelection f = new SummaryReportSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jpTicket.setBackground(new Color(41, 182, 246));
        jpBus.setBackground(new Color(9, 184, 62));
        jpStaff.setBackground(new Color(255, 235, 59));
        jpReport.setBackground(new Color(229, 45, 39));
        jpBody.setOpaque(false);
        jpBody.setBackground(new Color(0, 0, 0, 0));
        jpHeader.setOpaque(false);
        jpHeader.setBackground(new Color(0, 0, 0, 0));
        jpHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        jpBody.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        jpContent.add(jpHeader, BorderLayout.NORTH);
        jpContent.add(jpBody, BorderLayout.CENTER);
        jpContent.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, new Color(245, 245, 245)));
        jpContent.setBackground(Color.WHITE);
        jsContent = new JScrollPane(jpContent);

        designPanel(ds, s);
        add(jpBg);
        setTitle("GoToMalaysia - MainUI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void designPanel(Counter ds, Staff s) {
        JPanel jpHeader = new JPanel(new BorderLayout(100, 10));
        JPanel jpLogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpDate = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel jpHeader2 = new JPanel(new GridLayout(2, 1));
        String[] navList = {"Welcome, " + s.getStaffname(), ds.getDestid().getDestl(), "Back To Counter", "Log Out"};
        String[] navImg = {"person", "counter", "back", "logout"};
        JButton[] jbtNav = new JButton[navList.length];
        JPanel jpNav = new JPanel(new GridLayout(1, navList.length));
        String[] sideList = {"Dashboard", "Ticket Management", "Ticket Info/Purchasing", "Refund/ Cancellation of Ticket",
            "Transport Management", "Bus",
            "Route", "Trip", "Staff Management", "Staff Info", "P & C Info", "Report Management",
            "Exception Report", "Transaction Report", "Summary Report"};
        String[] sideImg = {"dashboard", "ticketmgt", "ticketinfo", "refund", "transportmgt", "bus", "route", "trip",
            "staffmgt", "staffinfo", "pnc", "reportmgt", "exception", "trans", "summary"};
        JButton[] jbtSide = new JButton[sideList.length];
        JPanel jpSide = new JPanel(new GridLayout(sideList.length, 1));
        JPanel jpWest = new JPanel(new BorderLayout());
        ImageIcon hideImg = createImageIcon("../images/arrowToRight.png", 20, 20);
        JButton jbtHide = new JButton(hideImg);

        ImageIcon busImg = createImageIcon("../images/busIcon.png", 100, 100);
        JLabel busLabel = new JLabel(busImg);
        JLabel nameLabel = new JLabel("GoToMalaysia");
        nameLabel.setForeground(new Color(117, 117, 117));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 35));
        jpLogo.add(busLabel);
        jpLogo.add(nameLabel);
        jpLogo.setBackground(Color.WHITE);
        jpHeader.setBackground(Color.WHITE);
        jpHeader.add(jpLogo, BorderLayout.WEST);
        jpHeader.setPreferredSize(new Dimension(getWidth(), 100));

        jpNav.setBackground(Color.WHITE);
        for (int i = 0; i < navList.length; i++) {
            ImageIcon imgNav = createImageIcon("../images/Mainui/" + navImg[i] + ".png", 18, 18);
            jbtNav[i] = new JButton(navList[i], imgNav);
            jbtNav[i].setPreferredSize(new Dimension(40, 50));
            jbtNav[i].setForeground(Color.WHITE);
            jbtNav[i].setBackground(new Color(8, 8, 8));
            jbtNav[i].setFont(new Font("Arial", Font.BOLD, 12));
            jpNav.add(jbtNav[i]);
        }

        jbtNav[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ChooseCounter f = new ChooseCounter(s);
                dispose();
            }
        });

        jbtNav[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                dispose();
            }
        });

        jpHeader2.add(jpNav);
        SimpleDateFormat sdf = new SimpleDateFormat("EE, dd MMMMM yyyy");
        Date now = new Date();
        JLabel l2 = new JLabel("Today's Date - " + sdf.format(now));
        jpDate.add(l2);
        jpDate.setBackground(Color.WHITE);
        jpHeader2.add(jpDate);
        jpHeader.add(jpHeader2, BorderLayout.CENTER);

        jpSide.setBackground(new Color(52, 73, 94));

        for (int i = 0; i < sideList.length; i++) {
            ImageIcon img = createImageIcon("../images/Mainui/" + sideImg[i] + ".png", 18, 18);
            jbtSide[i] = new JButton(sideList[i], img);
            jbtSide[i].setBackground(new Color(34, 34, 34));
            jbtSide[i].setForeground(new Color(125, 125, 131));
            jbtSide[i].setFont(new Font("Arial", Font.BOLD, 12));
            jbtSide[i].setHorizontalAlignment(SwingConstants.LEFT);
            jpSide.add(jbtSide[i]);
        }

        jbtSide[0].setBackground(new Color(0, 119, 181));
        jbtSide[0].setForeground(Color.WHITE);
        jbtSide[1].setEnabled(false);
        jbtSide[1].setBackground(new Color(8, 8, 8));
        jbtSide[1].setForeground(Color.WHITE);
        jbtSide[4].setEnabled(false);
        jbtSide[4].setBackground(new Color(8, 8, 8));
        jbtSide[4].setForeground(Color.WHITE);
        jbtSide[8].setEnabled(false);
        jbtSide[8].setBackground(new Color(8, 8, 8));
        jbtSide[8].setForeground(Color.WHITE);
        jbtSide[11].setEnabled(false);
        jbtSide[11].setBackground(new Color(8, 8, 8));
        jbtSide[11].setForeground(Color.WHITE);
        jbtHide.setBackground(new Color(8, 8, 8));
        jbtHide.setForeground(Color.WHITE);
        jbtHide.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        jbtHide.setHorizontalAlignment(SwingConstants.RIGHT);
        jpWest.setBackground(new Color(8, 8, 8));
        jpSide.setVisible(false);
        jbtHide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jpSide.isVisible()) {
                    jpSide.setVisible(false);
                    ImageIcon showImg = createImageIcon("../images/arrowToRight.png", 20, 20);
                    jbtHide.setIcon(showImg);

                } else {
                    jpSide.setVisible(true);
                    ImageIcon shideImg = createImageIcon("../images/arrowToLeft.png", 20, 20);
                    jbtHide.setIcon(shideImg);
                }
            }
        });

        jbtSide[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainUI f = new MainUI(ds, s);
                dispose();
            }
        });

        jbtSide[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                TicketTripInfo f = new TicketTripInfo(ds, s);
                dispose();
            }
        });

        jbtSide[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    RefundCancelMain f = new RefundCancelMain(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSide[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                 if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    CRUDBus f = new CRUDBus(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSide[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    CRUDRoute f = new CRUDRoute(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });

        jbtSide[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.getJobposition().getPositionid().equals("P00003") || s.getJobposition().getPositionid().equals("P00001")) {
//                    CRUDTrip f = new CRUDTrip(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });

        jbtSide[9].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    StaffCRUD f = new StaffCRUD(ds, s);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSide[10].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                StaffPNCMain f = new StaffPNCMain(ds, s);
                dispose();

            }
        });

        jbtSide[12].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    TransactioReportSelection f = new TransactioReportSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSide[13].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    ExceptionReportSelection f = new ExceptionReportSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jbtSide[14].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s.getJobposition().getPositionid().equals("P00003")) {
//                    SummaryReportSelection f = new SummaryReportSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "You are not authorise to access this information.", "Not Authorise User", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        jpWest.add(jbtHide, BorderLayout.NORTH);
        jpWest.add(jpSide, BorderLayout.CENTER);
        jpBg.add(jpHeader, BorderLayout.NORTH);
        jpBg.add(jpWest, BorderLayout.WEST);
        jpBg.add(jsContent, BorderLayout.CENTER);
    }

    public static ImageIcon createImageIcon(String path, int w, int h) {
        ImageIcon img = null;
        ImageIcon icon = null;
        java.net.URL imgURL = MainUI.class.getResource(path);

        try {
            img = new ImageIcon(MainUI.class.getResource(path));
            icon = new ImageIcon((img.getImage()).getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH));
            return icon;
        } catch (Exception ioe) {
            JOptionPane.showMessageDialog(null, "Image of " + path + " cannot be displayed.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void labelLayout(JButton jbt) {
        jbt.setBackground(new Color(255, 255, 255, 5));
        jbt.setHorizontalAlignment(SwingConstants.CENTER);
        jbt.setVerticalAlignment(SwingConstants.TOP);
        jbt.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbt.setHorizontalTextPosition(SwingConstants.CENTER);
        jbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbt.setBackground(new Color(255, 255, 255, 100));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbt.setBackground(new Color(255, 255, 255, 5));

            }
        });
        jbt.setOpaque(false);
    }

    public static void labelLayoutReport(JButton jbt) {
        jbt.setBackground(new Color(255, 255, 255, 5));
        jbt.setHorizontalAlignment(SwingConstants.LEFT);
        jbt.setVerticalAlignment(SwingConstants.CENTER);
        jbt.setVerticalTextPosition(SwingConstants.CENTER);
        jbt.setHorizontalTextPosition(SwingConstants.RIGHT);
        jbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbt.setBackground(new Color(255, 255, 255, 100));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbt.setBackground(new Color(255, 255, 255, 5));

            }
        });
        jbt.setOpaque(false);
    }

    public static void main(String[] args) {

    }

}
