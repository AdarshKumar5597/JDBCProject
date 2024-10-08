import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        /*
        * 1. import packages
        * 2. load and register
        * 3. create connection
        * 4. create statement
        * 5. execute statement
        * 6. process the results
        * 7. close
        * */

        // Load the driver. Driver is basically the class. And for loading the class, we use Class.forName method.
        Class.forName("org.postgresql.Driver");

        // create a connection
        String url = "jdbc:postgresql://localhost:5432/demo";
        String password = "5597";
        String user = "postgres";
        Connection connection = null;

        try {
            // Get a connection using DriverManager
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            // Perform database operations...
            Statement st = connection.createStatement();

            //----------------------------READ OPERATION-------------------------------------------
//            ResultSet rs = st.executeQuery("select * from public.\"Student\" where sid = 1;");
//            boolean exist = rs.next();
//            System.out.println("Data exist: " + exist);
//            if (exist) {
//                System.out.println("The detail of the student is: Name: " + rs.getString("sname") + ", Sid: " + rs.getString("sid") + ", Marks: " + rs.getString("marks"));
//            }

            //----------------------------READ OPERATION-------------------------------------------
//            ResultSet rs = st.executeQuery("select * from public.\"Student\";");
//            while (rs.next()) {
//                System.out.println("Name: "+rs.getString("sname")+", Sid: "+rs.getInt("sid")+", Marks: "+rs.getInt("marks"));
//            }

            //----------------------------INSERT OPERATION-------------------------------------------
//            boolean rs1 = st.execute("insert into public.\"Student\"(sid, sname, marks) values(5, 'Test', 45);");
//            if (!rs1) {
//                System.out.println("Data inserted successfully.");
//            }

            //----------------------------UPDATE OPERATION-------------------------------------------
//            boolean rs2 = st.execute("update public.\"Student\" set sname = 'Himanshu' where sid = 3;");
//            if (!rs2) {
//                System.out.println("Data updated successfully.");
//            }
//            ResultSet rs3 = st.executeQuery("select * from public.\"Student\";");
//            while (rs3.next()) {
//                System.out.println("Name: "+rs3.getString("sname")+", Sid: "+rs3.getInt("sid")+", Marks: "+rs3.getInt("marks"));
//            }

            //-----------------DELETE OPERATION-------------------------------------------------
//            boolean rs4 = st.execute("delete from public.\"Student\" where sname = 'Test';");
//            if (!rs4) {
//                System.out.println("Data deleted successfully.");
//            }
//            ResultSet rs5 = st.executeQuery("select * from public.\"Student\";");
//            while (rs5.next()) {
//                System.out.println("Name: "+rs5.getString("sname")+", Sid: "+rs5.getInt("sid")+", Marks: "+rs5.getInt("marks"));
//            }


            //--------------PREPARED STATEMENT---------------------------------------------
            int sid = 69;
            String name = "Ritik";
            int marks = 69;

            String sqlQuery = "insert into public.\"Student\"(sid, sname, marks) values(?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, sid);
            ps.setString(2, name);
            ps.setInt(3, marks);
            boolean result = ps.execute();
            if(!result) {
                System.out.println("Data inserted successfully...");
            }
            String fetchSqlQuery = "select * from public.\"Student\";";
            ResultSet rs6 = st.executeQuery(fetchSqlQuery);
            while (rs6.next()) {
                System.out.println("Name: "+rs6.getString("sname")+", Sid: "+rs6.getInt("sid")+", Marks: "+rs6.getInt("marks"));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error connecting database");
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Close the connection
                    System.out.println("Connection closed Successfully");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Error in closing database connection");
                }
            }
        }
    }
}