package studentmanagementsystemq2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

// Class prompts the student with a registration form.
// The details entered are used to create the student's account for future access of the applicationDashboard
public class registrationForm extends JDialog {
    // Registration Form text-fields
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPassword;
    private JTextField tfConfirmPassword;

    // Registration Form Buttons
    private JButton btnRegister;
    private JButton btnCancel;

    // Registration Form JPanel name
    private JPanel registerPanel;

    public registrationForm(JFrame parent){
        // Invokes parent -> JFrame
       super(parent) ;
       setTitle("Create a new account");
       setContentPane(registerPanel);
       setMinimumSize(new Dimension(450,474));
       setModal(true);
       setLocationRelativeTo(parent);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       // register button actionlistener calls the registerUser() method when clicked.
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();

            }
        });
        // Cancel button actionlistener destroys the JFrame window when clicked.
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // Displays the Registration JForm to the Student
        setVisible(true);

    }

    private void registerUser() {
        String FullName = tfName.getText();
        String Email = tfEmail.getText();
        String StudentPassword = String.valueOf(tfPassword.getText());
        String ConfirmPassword = String.valueOf(tfConfirmPassword.getText());

        // Checks to confirm if the student has entered all the Registration Form textfields.
        // If not, it alerts the user to enter all Fields.
        if (FullName.isEmpty() || Email.isEmpty() || StudentPassword.isEmpty() || ConfirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter All Fields", "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // For the Password Field, there are 2 fields : StudentPassword and ConfirmPassword.
        // The student is required to enter both fields and ensure they both match.
        if (!StudentPassword.equals(ConfirmPassword)) {
            JOptionPane.showMessageDialog(this, "Confirm Password Does not Match", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = adduserToDatabase(FullName, Email, StudentPassword);
        if (user != null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Failed to register User", "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }




    public newuser user;
    private newuser adduserToDatabase(String FullName, String Email, String StudentPassword) {
        newuser user = null;
        // Connects to the database and saves information entered in the Registration Form to the student_credentials table.
        {    Connection conn;

            try {
                conn = getConnection("jdbc:mysql://localhost:3306/registration", "root", "2215");
                Statement statement = conn.createStatement();
                String sql;
                sql = "INSERT INTO student_credentials(FullName,Email, StudentPassword) VALUES(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,FullName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3,StudentPassword);
                ResultSet resultSet = statement.executeQuery("select * from student_credentials");
                // Insert Row into the table
                int addedRows = preparedStatement.executeUpdate();
                if (addedRows >0){
                    user = new newuser();
                    user.FullName = FullName;
                    user.Email = Email;
                    user.StudentPassword = StudentPassword;
                }
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static void main(String[] args) {
       registrationForm  student = new registrationForm(null);
       newuser user = student.user;
       if (user != null){
           // Lets the student know their information has been entered in the database
           System.out.println("Successful Registration of : " + user.FullName);

       }else{
           // Displayed If student cancels the Registration Process
           System.out.println("Registration Cancelled");
       }
    }

}
