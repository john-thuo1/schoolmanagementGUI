package studentmanagementsystemq2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.DriverManager.getConnection;


// The class generates a form for student to enter information about their name, grade, email and year of study(2020, 2021).
// Information from this form is used to consider the student for admission to a particular course and the school.
//The class has been used in the ApplicationDashboard class.
public class enterStudentDetailsForm extends JDialog{
    private JTextField tfFullName;
    private JTextField tfGrade;
    private JTextField tfYear;
    private JButton submitButton;
    private JButton Cancelbutton;
    private JTextField tfEmail;
    private JPanel enterStudentDetailsPanel;

    public enterStudentDetailsForm(JFrame parent){
        // Invokes parent -> JFrame
        super(parent) ;
        // Title Displayed on the enterStudentDetailsForm on the JPanel
        setTitle("Enter your Details for Application");
        setContentPane(enterStudentDetailsPanel);
        //Sets the size of the form
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       // Once the student has entered their details, they click on submit.
        // This actionlistener calls on submitUserDetails() when clicked.
        //The method called checks to see if all the details are entered and saves them to the database.
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitUserDetails();

            }
        });
        // If the student decides not to submit their details, they can just cancel them with cancel actionlistener button
        Cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);

    }

    // Method to submit student details to the Database.Before submission, it checks to see if all required student details have been entered.
    //If there are missing fields, it alerts the student.
    private void submitUserDetails() {
        String FullName = tfFullName.getText();
        String Email = tfEmail.getText();
        String Grade = String.valueOf(tfGrade.getText());
        String Year = String.valueOf(tfYear.getText());
        if (FullName.isEmpty() || Email.isEmpty() || Grade.isEmpty() || Year.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter All Fields", "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = adduserDetailsToDatabase(FullName, Email, Grade, Year);
        if (user != null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Failed to add student details", "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }




    public newstudentdetails user;
    // Establishes connection to the database.
    private newstudentdetails adduserDetailsToDatabase(String FullName, String Email, String Grade,String Year) {
        newstudentdetails user = null;
        {    Connection conn;

            try {
                conn = getConnection("jdbc:mysql://localhost:3306/registration", "root", "2215");
                Statement statement = conn.createStatement();
                String sql;

                sql = "INSERT INTO student_info(FullName,Email,Grade,Year) VALUES(?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,FullName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3,Grade);
                preparedStatement.setString(4,Year);
                ResultSet resultSet = statement.executeQuery("select * from student_info");
                // Insert Row into the table
                int addedRows = preparedStatement.executeUpdate();
                if (addedRows >0){
                    user = new newstudentdetails();
                    user.FullName = FullName;
                    user.Email = Email;
                    user.Grade = Grade;
                    user.Year = Year;
                }
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }


    // Main method.
    // It lets the student know his/her details have been submitted successfully , if submitted.
    // If student cancels the submission, it lets them know in the terminal that the process has been cancelled.
    public static void main(String[] args) {
        enterStudentDetailsForm  student = new enterStudentDetailsForm(null);
        newstudentdetails user = student.user;
        if (user != null){

            System.out.println("Details of : " + user.FullName + " have been added successfully!");

        }else{
            System.out.println("Process Cancelled");
        }
    }

}
