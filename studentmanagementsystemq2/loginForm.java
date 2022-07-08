package studentmanagementsystemq2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

// This class lets a student with an already existing account to login to their applicationDashboard by entering their email and password.

// Once in the dashboard, the programme displays "Welcome to your ALU application StudentName"
// The class prompts the student with a login form
public class loginForm extends JDialog{
    // Log in Form text fields
    private JTextField tfEmail;
    private JTextField tfStudentPassword;

    // Buttons in the Login Form
    private JButton btnok;
    private JButton btncancel;

    // Name of the log in JPanel
    private JPanel loginpanel;

    public loginForm(JFrame parent){
        // Invokes the parent -> JFrame
        super(parent) ;

        // Title of the JPanel
        setTitle("login");
        setContentPane(loginpanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // ok button action listener calls the getAuthenticatedUser method when clicked.
        btnok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = tfEmail.getText();
                String StudentPassword = String.valueOf(tfStudentPassword.getText());
                user = getAuthenticatedUser(Email, StudentPassword);
                if (user !=null){
                    dispose();
                }else{

                    // Error messagebox displayed if the student enters the wrong password or email.
                    JOptionPane.showMessageDialog(loginForm.this,
                            "Email or Password Invalid",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // cancel button action listener collapses the Login Form JPanel by calling dispose() method when clicked.
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        // Displays the JPanel
        setVisible(true);

    }

    public newuser user;
    private newuser getAuthenticatedUser(String Email, String StudentPassword) {
        newuser user = null;
        {    Connection conn;

            try {
                // Connects to the database
                conn = getConnection("jdbc:mysql://localhost:3306/registration", "root", "2215");
                Statement statement = conn.createStatement();
                // Checks the email,password entered by the student with the Email and Password in the database for the student

                String sql = "SELECT * FROM student_credentials WHERE Email=? AND StudentPassword=?";
                PreparedStatement preparedstatement = conn.prepareStatement(sql);
                preparedstatement.setString(1, Email);
                preparedstatement.setString(2,StudentPassword);
                ResultSet resultSet = preparedstatement.executeQuery();

                if (resultSet.next()){
                    user = new newuser();
                    user.FullName = resultSet.getString("FullName");
                    user.Email = resultSet.getString("Email");
                    user.StudentPassword = resultSet.getString("StudentPassword");
                    user.StudentID = resultSet.getString("StudentID");

                }


                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Returns student details saved in the student_credentials table entered while registering.
        return user;
    }





    public static void main(String[] args) {
       loginForm loginstudent = new loginForm(null);
       newuser user = loginstudent.user;
       // If the student successfully logs in, his/her registration information is displayed in the terminal.
        if (user != null){
            System.out.println("Successful Login of : " + user.FullName);
            System.out.println("       Email: " + user.Email);
            System.out.println("       Student Password:" + user.StudentPassword);
            System.out.println("       Student ID:" + user.StudentID);
        }else{
            // If the student cancels the log in process, he/she is alerted about it.
            System.out.println("Login Cancelled");
        }
    }
}
