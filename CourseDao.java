public class CourseDao {
    private Connection conn;
    
    public CourseDao() {
     // Initialize the database connection
    String url = "jdbc:mysql://localhost:3306/mydb";
    String username = "root";
    String password = "mypassword";

conn = DriverManager.getConnection(url, username, password);
        conn = /* your code to establish connection to the database */;
    }
    
    // Method to add a new course to the database
    public boolean addCourse(Course course) {
        try {
            // Prepare the SQL query
            String sql = "INSERT INTO courses (course_name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            // Set the parameters for the query
            statement.setString(1, course.getCourseName());
            
            // Execute the query
            int rowsInserted = statement.executeUpdate();
            
            // Return true if the course was added successfully
            return (rowsInserted > 0);
        } catch (SQLException ex) {
            // Handle any exceptions that occur during the database operation
            ex.printStackTrace();
            return false;
        }
    }
}
