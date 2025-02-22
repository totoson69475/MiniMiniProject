package classes;
class LectureAttendance extends Lecture
{
    String courseName;
    String attendance;

    LectureAttendance(String courseName, String attendance) {
        this.courseName = courseName;
        this.attendance = attendance;
    }
    LectureAttendance(String courseName){
        this.courseName = courseName;
    }
    LectureAttendance(){}



    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getAttendance() {
        return attendance;
    }
}
