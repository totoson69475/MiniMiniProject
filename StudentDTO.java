package classes;

import java.util.ArrayList;
class  StudentDTO extends User
{
    LectureDB lectureDb = new LectureDB();

    int year;
    ArrayList<Lecture> courses = new ArrayList<>();					// 수강 과목
    public ArrayList<LectureGrade> grade = new ArrayList<>();					// 과목 + 성적
    ArrayList<LectureAttendance> attendance = new ArrayList<>();		// 과목 + 출석

    public StudentDTO(String id, String password, String name, String phoneNumber,
                      String email, String address, String department){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.department = department;

        // this.attendance = attendance;
        // this.grades = grades;
    };
    public StudentDTO(){};

    // 수강 강의 추가 메서드
    public void addCourses(Lecture lecture) {
        courses.add(lecture);
        grade.add(new LectureGrade(lecture.getCoursesName(), "미입력"));// 임시 성적 추가
		/*
        for(int idx = 0; idx < 15; idx++){
            attendance.add(new LectureAttendance(lecture.getCoursesName(),"결석"));			// 임시 출결 추가
        }
		*/
		attendance.add(new LectureAttendance(lecture.getCoursesName(),"결석"));
    }

    // 수강 강의 삭제 메서드(사용 X)
    public void removeCourses(Lecture lecture) {
        courses.remove(lecture);
    }

    // 수강 중인 모든 강의 가져오기(사용 X)
    public ArrayList<Lecture> getCourses() {
        return courses;
    }

    // 수강 중인 강의 출력
    public void printCourses() {
        if(courses.isEmpty()){
            System.out.println("수강 중인 강의가 없습니다.");
        } else {
            for (Lecture lecture : courses) {
                System.out.println("- " + lecture.getCoursesName());
            }
        }
    }

    // 강의 성적 추가(수정?)
    public void addGrade(String courseName, String grades) {
        boolean found = false;
        for (LectureGrade temp : grade) {
            if (temp.getCourseName().equals(courseName)) {
                temp.setGrade(grades);  // 기존 강의 성적(P로 지정) 업데이트
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("강의가 없습니다!");
        }
    }

    // 강의 성적 출력
    public void printGrade() {
        if (grade.isEmpty()) {
            System.out.println("입력된 성적이 없습니다.");
        } else {
            for (LectureGrade temp : grade) {
                System.out.println("강의: " + temp.getCourseName() + " | 성적: " + temp.getGrade());
            }
        }
    }

    // 강의 출석 추가(수정?)
    public void addAttendance(String courseName, int week, String _attendance) {
        boolean found = false;
        for (LectureAttendance temp : attendance) {
            if (temp.getCourseName().equals(courseName)) {
                temp.setAttendance(week-1, _attendance);  // 기존 강의 출결(P로 지정) 업데이트
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("강의가 없습니다!");
        }
    }

    // 강의 출석 출력
	/*
    public void printAttendance() {
        if (attendance.isEmpty()) {
            System.out.println("입력된 출결이 없습니다.");
        } else {
			int maxCourseNameLength = 0;
			for(LectureAttendance temp : attendance){
				maxCourseNameLength = Math.max(maxCourseNameLength, temp.getCourseName().length());
			}
            for (LectureAttendance temp : attendance) {
                //System.out.println("강의: " + temp.getCourseName() + " | 출석: " + temp.getAttendance());
				System.out.print("강의: " + temp.getCourseName());
				for(int spaceLength = temp.getCourseName().length(); spaceLength < maxCourseNameLength; spaceLength++){
					System.out.print(" ");
				}
				System.out.print(" | 출석: [");
				for(int idx = 0; idx < temp.attendance.size(); idx++){
					System.out.print(idx+1 + "주차: " + temp.attendance.get(idx));
					if(idx != 14) System.out.print(", ");
				}
				System.out.println("]");
            }
        }
    }
	*/
	public void printAttendance() {
		if (attendance.isEmpty()) {
			System.out.println("입력된 출결이 없습니다.");
		} else {
			for (LectureAttendance temp : attendance) {
				System.out.println("강의명 : " + temp.getCourseName());
				int cnt = 0;
				for (int i = 0; i < temp.getAttendance().size(); i++) {
					System.out.print((i + 1) + "주차: [" + temp.getAttendance().get(i) + "]  ");
					cnt++;
					if( cnt == 5 || cnt == 10){
						System.out.println();	// 5주차, 10주차에서 줄바꿈
					}
				}
				System.out.println("\n"); // 줄바꿈
			}
			System.out.println();
		}
	}

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public String getDepartment(){
        return department;
    }
	/*
    public void setAttendance(String attendance){
        this.attendance = attendance;
    }
    public String getAttendance(){
        return attendance;
    }

	public void setGrades(ArrayList<String> grades){
        this.grades = grades;
    }
    public ArrayList<String> getGrades(){
        return grades;
    }
	*/
}