package classes;
class LectureGrade extends Lecture
{
    String courseName;
    String grade;

    LectureGrade(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }
    LectureGrade(String courseName){
        this.courseName = courseName;
    }
    LectureGrade(){}



    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}
