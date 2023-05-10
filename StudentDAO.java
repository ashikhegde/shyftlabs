public interface StudentDAO {
    List<Student> getAllStudents();
    void addStudent(Student student);
    void deleteStudent(Student student);
}
