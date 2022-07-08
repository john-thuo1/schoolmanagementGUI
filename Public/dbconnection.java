package studentmanagementsystemq2;

import java.sql.*;

// Class dbconnection establishes connection to the table student_info. It displays the various degree programmes,
// students partaking in those degrees and their information in a table implemented in the class degrees
// This class is only used in the degrees class and nowhere else.
public class dbconnection {

    public  String[][] my_db_select() {
        // Holds data from the table student_info. This data is used to output information mentioned above
        // in the class degrees.

        String[][] data = new String[4][5]; // [rows][columns]

        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","2215");
            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery("SELECT * FROM student_info LIMIT 0,4");
            // Looping to store result in returning array data
            int i=0;
            while(rs.next())  {
                for(int j=0;j<5;j++) {
                    //System.out.print(rs.getString(j+1));
                    data[i][j]=rs.getString(j+1);
                }
                i=i+1;
            }

            con.close();
        }catch(Exception e){
            e.printStackTrace();}

        // Returns array data in the student_info table of database registration
        return data;
    }
}