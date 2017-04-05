package dbGUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;


public class GUI extends JFrame {

    private JPanel contentPane;
    private JTextField lastNameTextField;
    private JButton btnSearch;
    private JButton addUser;
    private JScrollPane scrollPane;
    private JTable table;
    static GUI frame;

    JDialog frame2;
    JTextField textName;
    JTextField textSurname;
    JTextField textAge;
    JTextField textCity;

    private DAO dao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Creating frame
    public GUI() throws Exception {

        // create the DAO
        try {
            dao = new DAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Users Search App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(panel2,BorderLayout.SOUTH);

        JLabel lblEnterLastName = new JLabel("Enter surname");
        panel.add(lblEnterLastName);

        lastNameTextField = new JTextField();
        panel.add(lastNameTextField);
        lastNameTextField.setColumns(10);

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String surname = lastNameTextField.getText();
                    ArrayList<User> tempUsers = null;

                    if (surname.isEmpty()) {
                        tempUsers = dao.getAllUsers();
                        //System.out.println(dao.getAllUsers());
                    } else{
                        tempUsers = dao.searchUsers(surname);
                        //System.out.println(dao.searchUsers(surname));
                    }

                    MyTableModel myTableModel = new MyTableModel(tempUsers);
                    table.setModel(myTableModel);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addUser = new JButton("Add User");
        panel2.add(addUser);
        addUser.addActionListener(new addUserListener());

        panel.add(btnSearch);

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new MyTableModel(dao.getAllUsers()));


    }

    private class addUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createDialogWindow();
        }
    }

    private void createDialogWindow(){
        frame2 = new JDialog(frame,"Add User", Dialog.ModalityType.APPLICATION_MODAL);
        frame2.setSize(400,300);
        frame2.setLocationRelativeTo(null);
        JPanel alterPanel = new JPanel();
        JPanel alterPanel2 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) alterPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        JLabel labelName = new JLabel("Enter name");
        JLabel labelSurname = new JLabel("Enter surname");
        JLabel labelAge = new JLabel("Enter age");
        JLabel labelCity = new JLabel("Enter city");
        textName = new JTextField(25);
        textSurname = new JTextField(23);
        textAge = new JTextField(26);
        textCity = new JTextField(26);

        alterPanel.add(labelName);
        alterPanel.add(textName);
        alterPanel.add(labelSurname);
        alterPanel.add(textSurname);
        alterPanel.add(labelAge);
        alterPanel.add(textAge);
        alterPanel.add(labelCity);
        alterPanel.add(textCity);

        JButton acceptButton = new JButton("Accept");
        alterPanel2.add(acceptButton);

        acceptButton.addActionListener(new AcceptButtonListener());

        frame2.getContentPane().add(BorderLayout.SOUTH,alterPanel2);
        frame2.getContentPane().add(BorderLayout.CENTER,alterPanel);
        frame2.setVisible(true);
    }

    private class AcceptButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String infoName = textName.getText();
            String infoSurname = textSurname.getText();
            String infoAge = textAge.getText();
            int intInfoAge = Integer.valueOf(infoAge);
            String infoCity = textCity.getText();

            if (infoName.isEmpty() || infoSurname.isEmpty() || infoAge.isEmpty() || infoCity.isEmpty()){
                JOptionPane.showMessageDialog(frame2,"Вы должны заполнить все строки!");
            } else{
                try {
                    dao.addUsers(infoName,infoSurname,intInfoAge, infoCity);
                    frame2.dispose();
                    table.setModel(new MyTableModel(dao.getAllUsers()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
