public class JdbcStudentDAO implements StudentDAO {

    private Connection conn;

    public JdbcStudentDAO() {
        // Initialize database connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
                String email = rs.getString("email");
                Student student = new Student(firstName, lastName, dateOfBirth, email);
                students.add(student);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return students;
    }

    public void addStudent(Student student) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (first_name, last_name, date_of_birth, email) VALUES (?, ?, ?, ?)");
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            stmt.setString(4, student.getEmail());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteStudent(Student student) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE email = ?");
            stmt.setString(1, student.getEmail());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
