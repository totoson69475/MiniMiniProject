package classes;

import java.util.ArrayList;

class ProfessorDAO
{
    private ProfessorDB professorDB = new ProfessorDB();
    public ArrayList<ProfessorDTO> professorAry = new ArrayList<ProfessorDTO>();
    public static ArrayList<Lecture> lectureAry = new ArrayList<Lecture>();

    ProfessorDAO(){ professorSet(); }

    public void professorSet(){
        professorDB.professorDBSet();
        for(int idx = 0; idx < professorDB.Professor.size(); idx++){
            professorAry.add(professorDB.Professor.get(idx));
        }
    }

    //----------------------------------강의 생성,수정,삭제----------------------------------
    public void registerLecture(ProfessorDTO professorDTO, String coursesName,
                                String classRoom, String classTime, int maxStudent){
        Lecture createLecture = new Lecture(professorDTO.id, professorDTO.name,
                coursesName, classRoom, classTime, maxStudent);
        lectureAry.add(createLecture);
		professorDTO.subject.add(coursesName);
    }
    public void editLecture(ProfessorDTO professorDTO, int choiceLectureIdx, int choiceMenu, String changeValue){
        ArrayList<Lecture> MyLectureAry = new ArrayList<Lecture>();
        MyLectureAry = viewMyLecture(professorDTO);

        if(choiceMenu == 1){ // 강의명
			for(int idx = 0; idx < professorDTO.subject.size(); idx++){
				if(professorDTO.subject.get(idx).equals(MyLectureAry.get(choiceLectureIdx-1).getCoursesName())){
					professorDTO.subject.set(idx, changeValue);
				}
			}
			for(int idx = 0; idx < StudentDAO.stuAry.length; idx++){
				for(int coursesCount = 0; coursesCount < StudentDAO.stuAry[idx].courses.size(); coursesCount++){
					if(StudentDAO.stuAry[idx].courses.get(coursesCount).getCoursesName().equals(MyLectureAry.get(choiceLectureIdx-1).getCoursesName())){
						StudentDAO.stuAry[idx].grade.get(coursesCount).setCourseName(changeValue);
						StudentDAO.stuAry[idx].attendance.get(coursesCount).setCourseName(changeValue);
					}
				}
			}
            MyLectureAry.get(choiceLectureIdx-1).setCoursesName(changeValue);
        }
        if(choiceMenu == 2){ // 강의장소
            MyLectureAry.get(choiceLectureIdx-1).setClassRoom(changeValue);
        }
        if(choiceMenu == 3){ // 강의시간
            MyLectureAry.get(choiceLectureIdx-1).setClassTime(changeValue);
        }
        if(choiceMenu == 4){ // 강의 최대 수강 가능 인원수
            MyLectureAry.get(choiceLectureIdx-1).setMaxStudent(Integer.parseInt(changeValue));
        }

    }

    public void deleteLecture(ProfessorDTO professorDTO, int choiceLectureIdx){
        ArrayList<Lecture> removeLectureList = new ArrayList<Lecture>();
        removeLectureList = viewMyLecture(professorDTO);
        System.out.println(choiceLectureIdx + ". " + removeLectureList.get(choiceLectureIdx-1).coursesName + "강의를 삭제했습니다.");

		for(int idx = 0; idx < professorDTO.subject.size(); idx++){
				if(professorDTO.subject.get(idx).equals(removeLectureList.get(choiceLectureIdx-1).getCoursesName())){
					professorDTO.subject.remove(idx);
				}
		}
        for(int idx = 0; idx < lectureAry.size(); idx++){
            if(lectureAry.get(idx).coursesName.equals(removeLectureList.get(choiceLectureIdx-1).coursesName)){
                lectureAry.remove(idx);
            }
        }
    }

    public ArrayList<Lecture> viewMyLecture(ProfessorDTO professorDTO){
        int cnt = 1;
        ArrayList<Lecture> searchMyLectureAry = new ArrayList<Lecture>();

        for(int idx = 0; idx < lectureAry.size(); idx++){
            if(lectureAry.get(idx).professorID.equals(professorDTO.id)){
                searchMyLectureAry.add(lectureAry.get(idx));
            }
        }
		
        for(Lecture myLecture : searchMyLectureAry){
            System.out.println(cnt + ". " + myLecture.coursesName);
            cnt++;
        }
		System.out.println();
		
        return searchMyLectureAry;
    }
//----------------------------------강의 생성,수정,삭제----------------------------------


    //----------------------------------성적 생성,수정,삭제----------------------------------
    public void registerGrade(String studentID, String courseName, String grades){
        for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
            if(StudentDAO.stuAry[stuAryIdx].getId().equals(studentID)) {
                StudentDAO.stuAry[stuAryIdx].addGrade(courseName, grades);
            }
        }
	}
    public void editGrade(String studentID, String courseName, String grades){
        for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++) {
            if (StudentDAO.stuAry[stuAryIdx].getId().equals(studentID)) {
                StudentDAO.stuAry[stuAryIdx].addGrade(courseName, grades);
            }
        }
    }
    public void deleteGrade(String studentID, String courseName){
        for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++) {
            if (StudentDAO.stuAry[stuAryIdx].getId().equals(studentID)) {
                StudentDAO.stuAry[stuAryIdx].addGrade(courseName, "미입력");
            }
        }
    }
//----------------------------------성적 생성,수정,삭제----------------------------------


    //----------------------------------출결 생성,수정,삭제----------------------------------
    public void registerAttendance(String studentID, String courseName, int week, String attendance){
        for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
            if(StudentDAO.stuAry[stuAryIdx].getId().equals(studentID)) {
                StudentDAO.stuAry[stuAryIdx].addAttendance(courseName, week, attendance);
            }
        }
    }
    public void editAttendance(String studentID, String courseName, int week, String attendance){
		for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
            if(StudentDAO.stuAry[stuAryIdx].getId().equals(studentID)) {
                StudentDAO.stuAry[stuAryIdx].addAttendance(courseName, week, attendance);
            }
        }
	}
    public void deleteAttendance(String studentID, String courseName, int week){
		for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
            if(StudentDAO.stuAry[stuAryIdx].getId().equals(studentID)) {
                StudentDAO.stuAry[stuAryIdx].addAttendance(courseName, week, "결석");
            }
        }
	}
//----------------------------------출결 생성,수정,삭제----------------------------------
}
