public class CourseDAO {
    private Connection conn;

    public CourseDAO() {
    String url = "jdbc:mysql://localhost:3306/mydb";
    String username = "root";
    String password = "mypassword";

    conn = DriverManager.getConnection(url, username, password);

    }

    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, course.getName());

        // Execute the statement
        statement.executeUpdate();
    }

    public List<Course> getAllCourses() throws SQLException {
        String sql = "SELECT * FROM courses";
        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery(sql);

        List<Course> courses = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            courses.add(new Course(id, name));
        }

        return courses;
    }

    public void deleteCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, courseId);

        statement.executeUpdate();
    }
}
