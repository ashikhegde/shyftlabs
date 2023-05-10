public class Result {
    private Connection conn; 
    
    public Result() {
    String url = "jdbc:mysql://localhost:3306/mydb";
    String username = "root";
    String password = "mypassword";

    conn = DriverManager.getConnection(url, username, password);
    }
    
    public void addResult(String courseName, String studentName, String score) {

        String sql = "INSERT INTO results (course_name, student_name, score) VALUES (?, ?, ?)";
        
        try {
            // create a prepared statement with the SQL query
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            // set the values for the prepared statement
            stmt.setString(1, courseName);
            stmt.setString(2, studentName);
            stmt.setString(3, score);
            
            // execute the SQL query
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("New result added for " + studentName + " in " + courseName + ".");
            } else {
                System.out.println("Error adding new result.");
            }
            
            // close the prepared statement
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public void display() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT course_name, student_name, score FROM results");

            System.out.println("Course\t\tStudent\t\tScore");
            System.out.println("---------------------------------------");
            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String studentName = rs.getString("student_name");
                String score = rs.getString("score");
                System.out.println(courseName + "\t" + studentName + "\t" + score);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error displaying results: " + e.getMessage());
        }
    }
}
