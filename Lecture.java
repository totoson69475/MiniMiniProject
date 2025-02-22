package classes;

class Lecture
{
    //교수 ID
    public String professorID;
    // 교수 이름
    public String professorName;

    public String coursesName;
    public String classRoom;
    public String classTime;
    public int enrolledStudent;
    public int maxStudent;

    Lecture(String professorID, String professorName, String coursesName, String classRoom,
            String classTime, int enrolledStudent, int maxStudent){
        this.professorID = professorID;
        this.professorName = professorName;
        this.coursesName = coursesName;
        this.classRoom = classRoom;
        this.classTime = classTime;
        this.enrolledStudent = enrolledStudent;
        this.maxStudent = maxStudent;
    }
    Lecture(String professorID, String professorName, String coursesName, String classRoom,
            String classTime, int maxStudent){
        this.professorID = professorID;
        this.professorName = professorName;
        this.coursesName = coursesName;
        this.classRoom = classRoom;
        this.classTime = classTime;
        this.maxStudent = maxStudent;

    }
    public Lecture(){};

    public String getProfessorID() {
        return professorID;
    }

    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getEnrolledStudent() {
        return enrolledStudent;
    }

    public void setEnrolledStudent(int enrolledStudent) {
        this.enrolledStudent = enrolledStudent;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }
}
