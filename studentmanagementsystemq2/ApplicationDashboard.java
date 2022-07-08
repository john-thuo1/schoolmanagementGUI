package studentmanagementsystemq2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


// This is the homepage of the ALU application system. To run the whole studentmanagementsystem, run this class.
// If it is a student's first time, they are prompted to register and then log in to the system.
// If not, the student is required to login by entering their email address and password.
// In here, there are 4 main parts;
// Enter Details - when pressed, it asks the student for fullname, grade, year, email to be considered for admission
// Admission Status - when pressed, it displays information entered by student during application in Enter Details.
//                  - A student must first enter their name on Enter Your Name textfield and then press Show Details button
//Degree Programmes - When pressed, it displays a table with Students assigned to different courses.
//                  - To see the full table, run degrees class in the SWING UI Form degrees.
// Last part is the Sign Out- when clicked, it signs out the student out of the application.
//                          - A student must log in again the next time they visit.
// If a student wants to re-register their account again, they can press the button "Register Account Again".
// "The register account again" has not been thoroughly tested but its primary purpose is to show registration process.


public class ApplicationDashboard extends JFrame{
    // panel name
    private JPanel dashboardpanel;
    private JLabel lbAdmin;

    // Buttons to represent the various applicationDashboard options highlighted above.
    private JButton btnRegister;
    private JButton btnDetails;
    private JButton btnAdmission;
    private JButton btndegreeprogrammes;
    private JButton btnSignOut;

    public ApplicationDashboard(){
        setTitle("Dashboard");
        setContentPane(dashboardpanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(3200, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boolean hasRegistredUsers = connectToDatabase();
        if (hasRegistredUsers) {
            //show Login form
            loginForm studentlogin = new loginForm(this);
            newuser user = studentlogin.user;

            if (user != null) {
                lbAdmin.setText("Welcome to your ALU Application  " + user.FullName);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
        else {
            //show Registration form
            registrationForm registerStudent = new registrationForm(this);
            newuser user = registerStudent.user;

            if (user != null) {
                lbAdmin.setText("User: " + user.FullName);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
        // register again button action listener
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationForm registerStudent = new registrationForm(ApplicationDashboard.this);
                newuser user = registerStudent.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(ApplicationDashboard.this,
                            "New user: " + user.FullName,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        // Enter Details actionlistener
        btnDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterStudentDetailsForm captureStudentDetails = new enterStudentDetailsForm(ApplicationDashboard.this);
                newstudentdetails user = captureStudentDetails.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(ApplicationDashboard.this,
                            "Details Added Successfully: " + user.FullName,
                            "Successful Details",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        // Admission status button actionlistener
        btnAdmission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admissionStatusDetails Studentinfo = new admissionStatusDetails(ApplicationDashboard.this);
                newstudentdetails user = Studentinfo.user;
            }
        });

        // Degree Programmes button actionlistener
        btndegreeprogrammes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                degrees VDegrees = new degrees(ApplicationDashboard.this);

            }
        });
        // Sign out button actionlistener. When student clicks on it, they are signed out.
        btnSignOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Method to connect to the database
    private boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        try{
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "2215");
            Statement statement = conn.createStatement();
//            statement.close();
//            conn.close();

           //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) Student_credentials");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = false;
                }
            }

            statement.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }

    public static void main(String[] args) {
        //Initializing object of the class ApplicationDashboard in order to test its functionality.

        ApplicationDashboard myForm = new ApplicationDashboard();
    }
}




