package classes;

import java.util.ArrayList;

class LectureAttendance extends Lecture
{
    String courseName;
    ArrayList<String> attendance = new ArrayList<String>(15);

    LectureAttendance(String courseName, String attendance) {
        this.courseName = courseName;
		for(int idx = 0; idx < 15; idx++){
			this.attendance.add(attendance);
		}
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

    public void setAttendance(int week, String attendance) {
        this.attendance.set(week, attendance);
    }

    public ArrayList<String> getAttendance() {
        return attendance;
    }
}
