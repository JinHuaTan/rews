/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSUI;

import BUSCONTROL.MaintainTicketControl;
import BUSCONTROL.MaintainTripControl;
import BUSDOMAIN.Ticket;
import BUSDOMAIN.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class PurchaseTicketInfo extends JFrame {

    private MaintainTicketControl mtcc;
    private MaintainTripControl mtc;
    private JPanel jpHeader = new JPanel();
    private JPanel jpCombo = new JPanel();
    private JPanel jpTrip = new JPanel(new BorderLayout());
    private JPanel jpList = new JPanel(new BorderLayout());
    private JPanel jpListButton = new JPanel(new GridLayout(2, 1));
    private JPanel jpTicketInfo = new JPanel(new GridLayout(2, 3, 3, 3));

    private JButton jbtDelete = new JButton("Remove from list");
    private JButton jbtPayment = new JButton("Payment");
    private JScrollPane jsPane;
    private JComboBox jcbTrip;
    private DefaultComboBoxModel tripModel = new DefaultComboBoxModel();
    private ArrayList<Ticket> purchasedList = new ArrayList<Ticket>();
    private JTextField[] jtfInfo = new JTextField[6];

    private JList list;
    private DefaultListModel listModel;
    private Staff currentStaff;
    private Counter currentCounter;
    public PurchaseTicketInfo(Trip t,Counter ds,Staff s) {
        currentStaff=s;
        currentCounter=ds;
        mtc = new MaintainTripControl();
        mtcc = new MaintainTicketControl();
        listModel = new DefaultListModel();

        for (int i = 0; i < mtc.getAllRecord().size(); i++) {
            tripModel.addElement(mtc.getAllRecord().get(i).getTripid());
        }

        CreateButtonsInPanel(t.getTripid());

        JLabel jlHeader = new JLabel("Select Seat by Trip ");
        jlHeader.setForeground(Color.WHITE);
        jlHeader.setFont(new Font("Arial", Font.BOLD, 30));
        jcbTrip = new JComboBox(tripModel);
        jcbTrip.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    CreateButtonsInPanel((String) e.getItem());
                }
            }
        });
        jcbTrip.setSelectedItem(t.getTripid());
        jpCombo.add(jlHeader);

        jpCombo.add(jcbTrip);
        jpCombo.setBackground(Color.BLACK);

        jpHeader.setBackground(Color.BLACK);
        jpHeader.add(jpCombo);

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(100, 600));

        jbtDelete.setBackground(new Color(231, 76, 60));
        jbtDelete.setForeground(Color.WHITE);
        jbtDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listModel.size(); i++) {
                    if (!listModel.get(i).equals("")) {
                        Ticket tc = mtcc.selectRecord((String) listModel.get(i));
                        purchasedList.add(tc);
                    }
                }

            }
        });

        jbtPayment.setBackground(new Color(52, 152, 219));
        jbtPayment.setForeground(Color.WHITE);
        jbtPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listModel.size(); i++) {
                    if (!listModel.get(i).equals("")) {
                        Ticket tc = mtcc.selectRecord((String) listModel.get(i));
                        purchasedList.add(tc);
                    }
                }
                Payment f = new Payment(currentStaff,currentCounter,purchasedList);
                dispose();
            }
        });
        jbtDelete.addActionListener(new DeleteButtonListener());
        jpListButton.add(jbtDelete);
        jpListButton.add(jbtPayment);
        jpList.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jpList.setBackground(Color.WHITE);
        jpList.add(listScrollPane, BorderLayout.CENTER);
        jpList.add(jpListButton, BorderLayout.SOUTH);

        JLabel[] jlInfo = new JLabel[6];
        String[] sInfo = {"Tripid", "Seatnumber", "Availability", "Fare Per Ticket", "Depart Date", "Arrival Date"};

        for (int i = 0; i < 6; i++) {
            jlInfo[i] = new JLabel(sInfo[i]);
            jlInfo[i].setForeground(new Color(26, 188, 156));
            jlInfo[i].setFont(new Font("Arial", Font.BOLD, 15));
            jtfInfo[i] = new JTextField("-");
            jtfInfo[i].setEditable(false);
            jtfInfo[i].setBorder(null);
            jpTicketInfo.add(jlInfo[i]);
            jpTicketInfo.add(jtfInfo[i]);
        }

        jpTicketInfo.setBackground(new Color(238, 238, 238));
        jpTicketInfo.setPreferredSize(new Dimension(getWidth(), 100));
        jpTicketInfo.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        jsPane = new JScrollPane(jpTrip);
        jpTrip.setBackground(Color.WHITE);
        add(jpHeader, BorderLayout.NORTH);
        add(jsPane, BorderLayout.CENTER);
        add(jpList, BorderLayout.EAST);
        add(jpTicketInfo, BorderLayout.SOUTH);

        setTitle("GoToMalaysia - Select Seat");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public class DeleteButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            ListSelectionModel lsm = list.getSelectionModel();
            int firstSelected = lsm.getMinSelectionIndex();
            int lastSelected = lsm.getMaxSelectionIndex();
            listModel.removeRange(firstSelected, lastSelected);

            int size = listModel.size();

            if (firstSelected == listModel.getSize()) {
                firstSelected--;
            }
            list.setSelectedIndex(firstSelected);
        }
    }

    public void CreateTicketJButtons(Trip t) {
        ArrayList<Ticket> ticketList = null;
        ticketList = mtcc.retrieveBunchTickets(t);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(10, 10, 10, 10);
        try {
            JPanel jpBusSeat = new JPanel(new GridBagLayout());
            jpBusSeat.setBackground(Color.WHITE);

            switch (t.getBusid().getBustypeid().getBustypeid()) {
                case "BT0001": {
                    c.gridx = 1;
                    c.gridy = 1;
                    c.gridwidth = 5;
                    jpBusSeat.add(new JLabel("Front"), c);
                    c.gridwidth = 1;
                    int y = 2, count = 1;
                    for (int i = 0; i < ticketList.size(); i++) {
                        JButton jbt = new JButton(ticketList.get(i).getSeatid().getSeatnumber());
                        if (ticketList.get(i).getAvailability() == 1) {
                            jbt.setBackground(Color.GREEN);
                            jbt.setEnabled(false);
                        } else {
                            jbt.setBackground(Color.ORANGE);
                        }
                        jbt.setForeground(Color.WHITE);
                        String ticketid = ticketList.get(i).getTicketid();
                        Ticket ctr = ticketList.get(i);
                        jbt.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                listModel.addElement(ticketid);
                                setTextInfo(t, ctr);

                            }
                        });
                        if (count > 5) {
                            y++;
                            count = 1;
                        }
                        if (count == 3) {
                            c.gridx = count;
                            c.gridy = y;
                            jpBusSeat.add(new JLabel(" "), c);
                            count++;
                        }
                        c.gridx = count;
                        c.gridy = y;
                        count++;

                        jpBusSeat.add(jbt, c);

                    }
                    break;
                }
                case "BT0002": {
                    c.gridx = 1;
                    c.gridy = 1;
                    c.gridwidth = 5;
                    jpBusSeat.add(new JLabel("Front, 1st level"), c);
                    c.gridwidth = 1;
                    int y = 2, count = 1;
                    for (int i = 0; i < ticketList.size() / 2; i++) {
                        JButton jbt = new JButton(ticketList.get(i).getSeatid().getSeatnumber());
                        if (ticketList.get(i).getAvailability() == 1) {
                            jbt.setBackground(Color.GREEN);
                            jbt.setEnabled(false);
                        } else {
                            jbt.setBackground(Color.ORANGE);
                        }
                        jbt.setForeground(Color.WHITE);
                        String ticketid = ticketList.get(i).getTicketid();
                        Ticket tc = ticketList.get(i);

                        jbt.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                listModel.addElement(ticketid);
                                setTextInfo(t, tc);
                            }
                        });
                        if (count > 5) {
                            y++;
                            count = 1;
                        }
                        if (count == 3) {
                            c.gridx = count;
                            c.gridy = y;
                            jpBusSeat.add(new JLabel("   "), c);
                            count++;
                        }
                        c.gridx = count;
                        c.gridy = y;
                        count++;
                        jpBusSeat.add(jbt, c);
                    }
                    y++;
                    c.gridx = 1;
                    c.gridy = y;
                    c.gridwidth = 5;
                    jpBusSeat.add(new JLabel("Front, 2nd level"), c);
                    c.gridwidth = 1;
                    count = 1;
                    y++;

                    for (int i = ticketList.size() / 2; i < ticketList.size(); i++) {
                        JButton jbt = new JButton(ticketList.get(i).getSeatid().getSeatnumber());
                        if (ticketList.get(i).getAvailability() == 1) {
                            jbt.setBackground(Color.GREEN);
                            jbt.setEnabled(false);
                        } else {
                            jbt.setBackground(Color.ORANGE);
                        }
                        jbt.setForeground(Color.WHITE);
                        String ticketid = ticketList.get(i).getTicketid();
                        Ticket tc = ticketList.get(i);

                        jbt.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                listModel.addElement(ticketid);
                                setTextInfo(t, tc);
                            }
                        });
                        if (count > 5) {
                            y++;
                            count = 1;
                        }
                        if (count == 3) {
                            c.gridx = count;
                            c.gridy = y;
                            jpBusSeat.add(new JLabel("   "), c);
                            count++;
                        }
                        c.gridx = count;
                        c.gridy = y;
                        count++;
                        jpBusSeat.add(jbt, c);
                    }
                    break;
                }
                case "BT0003":
                case "BT0004": {
                    c.gridx = 1;
                    c.gridy = 1;
                    c.gridwidth = 3;
                    jpBusSeat.add(new JLabel("Front"), c);
                    c.gridwidth = 1;
                    int y = 2, count = 1;
                    for (int i = 0; i < ticketList.size(); i++) {
                        JButton jbt = new JButton(ticketList.get(i).getSeatid().getSeatnumber());
                        if (ticketList.get(i).getAvailability() == 1) {
                            jbt.setBackground(Color.GREEN);
                            jbt.setEnabled(false);
                        } else {
                            jbt.setBackground(Color.ORANGE);
                        }
                        jbt.setForeground(Color.WHITE);
                        String ticketid = ticketList.get(i).getTicketid();
                        Ticket tc = ticketList.get(i);
                        jbt.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                listModel.addElement(ticketid);
                                setTextInfo(t, tc);
                            }
                        });
                        if (count > 4) {
                            y++;
                            count = 1;
                        }
                        if (count == 3) {
                            c.gridx = count;
                            c.gridy = y;
                            jpBusSeat.add(new JLabel("   "), c);
                            count++;
                        }
                        c.gridx = count;
                        c.gridy = y;
                        count++;
                        jpBusSeat.add(jbt, c);
                    }
                    break;
                }

            }
            jpTrip.add(jpBusSeat, BorderLayout.CENTER);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Create Trip Button", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CreateButtonsInPanel(String tripid) {
        jpTrip.removeAll();
        jpTrip.revalidate();
        jpTrip.repaint();
        Trip t = mtc.selectRecord(tripid);
        CreateTicketJButtons(t);
    }

    public void setTextInfo(Trip t, Ticket tc) {
        jtfInfo[0].setText(t.getTripid());
        jtfInfo[1].setText(tc.getSeatid().getSeatnumber());
        jtfInfo[2].setText((tc.getAvailability() == 0 ? "Available" : "Booked"));
        jtfInfo[3].setText("" + t.getFareperticket());
        jtfInfo[4].setText(t.getDepartdate());
        jtfInfo[5].setText(t.getArrivaldate());

    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
