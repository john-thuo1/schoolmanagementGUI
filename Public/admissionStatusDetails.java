package studentmanagementsystemq2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import static java.sql.DriverManager.getConnection;

// Class contains details entered by the student while applying. These details are shown to a given student when they
// enter their name in the textfield "Enter your name".
// The details displayed also contain the degree programme the student is likely to get in.
// This degree programme is calculated by checking the student's grade.If the student's grade fall short of the
// required grade, on the courseAssigned, "Denied" is displayed.
public class admissionStatusDetails extends JDialog {
    static newstudentdetails user;
    // Different textfields that display information entered by user during application process.
    private JTextField tfRollNo;
    private JTextField tfFullName;
    private JTextField tfEmail;
    private JTextField tfGrade;
    private JTextField tfYear;
    private JTextField tfCourseAssigned;
    private JTextField tfRegistrationNumber;
    private JButton showDetailsButton;
    // Name of the panel used
    private JPanel detailsSubmitted;

    // Before seeing his/her details, a user/student enters their name and clicks submit, then the details are displayed to him/her
    private JTextField tfEnteryourFullName;

    public admissionStatusDetails(JFrame parent) {
        // Invokes parent -> JFrame
        super(parent);
        // Title of JPanel displayed to the student
        setTitle("Admission Details");
        // Uses JPanel detailsSubmitted
        setContentPane(detailsSubmitted);
        // Sets the size of the JPanel displayed to the student
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        // showDetailsButton actionlistener
        // Once the student has entered their name, click show details button to see information submitted during application
        showDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails();
                
            }
        });

        setVisible(true);

    }

    private void showDetails() {
        {    Connection conn;

            try {
                // Setting up a textfield where student will enter their name in order to see their details displayed
                String FullName = tfEnteryourFullName.getText();
                // Establishes connection to the database table where student info entered during application is stored.
                conn = getConnection("jdbc:mysql://localhost:3306/registration", "root", "2215");
                String sql = ("SELECT RollNo,FullName, Email, Grade, CourseAssigned, Year, RegistrationNumber  FROM student_info WHERE FullName=?");
                PreparedStatement preparedstatement = conn.prepareStatement(sql);
                preparedstatement.setString(1, FullName);
                ResultSet resultSet = preparedstatement.executeQuery();

                if (resultSet.next()){
                    // Collects the student info in the database table
                    String RollNo = resultSet.getString(1);
//                    String FullName = resultSet.getString(2);
                    String Email = resultSet.getString(3);
                    String Grade = resultSet.getString(4);
                    String CourseAssigned = resultSet.getString(5);
                    String Year = resultSet.getString(6);
//                    String RegistrationNumber = resultSet.getString(7);
                    String RegistrationNumber = RollNo.concat(Year);

                    // Displays the information to the student in the textfields mentioned above.
                    tfRollNo.setText(RollNo);
                    tfFullName.setText(FullName);
                    tfEmail.setText(Email);
                    tfGrade.setText(Grade);
//                    tfCourseAssigned.setText(CourseAssigned);
                    tfYear.setText(Year);
                    tfRegistrationNumber.setText(RegistrationNumber);
                    int grades = Integer.parseInt(Grade);
                    // Establishes the course selection assigned to the student
                    if (grades> 17) {
                        CourseAssigned = "Computer Science";

                        tfCourseAssigned.setText(CourseAssigned);

                    }else if (grades>14){
                        CourseAssigned = "Global Challenges";
                        tfCourseAssigned.setText(CourseAssigned);

                    }else if (grades>11){
                        CourseAssigned = "Business Studies";
                        tfCourseAssigned.setText(CourseAssigned);

                    }else{
                        // If the student's grade is less than 12, the student is not assigned to any course and his application considered denied
                        CourseAssigned = "Denied";
                        tfCourseAssigned.setText(CourseAssigned);


                    }


                }else
                {// If the student enters a name that is innacurate or checks his/her details having not submitted them during the application,
                    // The system lets them know their info does not exist in the registration database
                    JOptionPane.showMessageDialog(null,"Student Details have not yet been entered!!");

                }


                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}


    //main method
    public static void main(String[] args) {
        admissionStatusDetails student = new admissionStatusDetails(null);
        newstudentdetails user = admissionStatusDetails.user;
    }
}
