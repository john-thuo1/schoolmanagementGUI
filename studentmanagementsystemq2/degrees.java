package studentmanagementsystemq2;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


// This class displays information related to the degrees/courses being offered at ALU and
// the students who have been assigned to those courses.
// To see the full table, run this class.
public class degrees extends JFrame {


    private javax.swing.JTable jt1;

    public degrees(ApplicationDashboard applicationDashboard) {

    }

   // Main method
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    degrees frame = new degrees();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public degrees() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 400, 600, 605);
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 366, 107);
        contentPanel.add(scrollPane);
        dbconnection user=new dbconnection();

        // Data to collect about a given student from the database.This information is displayed in a table.
        // From this data, a student can tell the courses being offered and the other students about to do them.
        String[] column= {"RollNo","FullName",  "Email", "Grade","CourseAssigned"};

        jt1 = new javax.swing.JTable(user.my_db_select(),column);
        scrollPane.setViewportView(jt1);
    }
}
