//This is the backend logic for the "Add New Students page "


public class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) throws Exception {
        // Validate the student data
        if (student.getFirstName() == null || student.getFirstName().isEmpty()
                || student.getFamilyName() == null || student.getFamilyName().isEmpty()
                || student.getDateOfBirth() == null || student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new Exception("All fields are required");
        }

        // Validate email address
        if (!student.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new Exception("Invalid email address");
        }

        // Validate date of birth
        LocalDate dob = student.getDateOfBirth();
        LocalDate today = LocalDate.now();
        Period period = Period.between(dob, today);
        if (period.getYears() < 10) {
            throw new Exception("Student must be at least 10 years old");
        }

        // Add the student to the list
        students.add(student);
    }
}
