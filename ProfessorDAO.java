package classes;

import java.util.ArrayList;

class ProfessorDAO
{
    private ProfessorDB professorDB = new ProfessorDB();
    public ArrayList<ProfessorDTO> professorAry = new ArrayList<ProfessorDTO>();
    public ArrayList<Lecture> lectureAry = new ArrayList<Lecture>();

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
    }
    public void editLecture(ProfessorDTO professorDTO, int choiceLectureIdx, int choiceMenu, String changeValue){
        ArrayList<Lecture> MyLectureAry = new ArrayList<Lecture>();
        MyLectureAry = viewMyLecture(professorDTO);

        if(choiceMenu == 1){ // 강의명
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

    public void deleteLecture(int professorId, String courseName){}

    public ArrayList<Lecture> viewMyLecture(ProfessorDTO professorDTO){
        ArrayList<Lecture> searchMyLectureAry = new ArrayList<Lecture>();
        int cnt = 1;

        for(int idx = 0; idx < lectureAry.size(); idx++){
            if(lectureAry.get(idx).professorID.equals(professorDTO.id)){
                searchMyLectureAry.add(lectureAry.get(idx));
            }
        }

        for(Lecture myLecture : searchMyLectureAry){
            System.out.println(cnt + ". " + myLecture.coursesName);
            cnt++;
        }
        return searchMyLectureAry;
    }
//----------------------------------강의 생성,수정,삭제----------------------------------


    //----------------------------------성적 생성,수정,삭제----------------------------------
    public void registerGrade(int professorId){}
    public void editGrade(int professorId, int studentId, String grades){}
    public void deleteGrade(int professorId, int studentId, String grades){}
//----------------------------------성적 생성,수정,삭제----------------------------------


    //----------------------------------출결 생성,수정,삭제----------------------------------
    public void registerAttendance(int professorId, int studentId){}
    public void editAttendance(int professorId, int studentId, String attendance){}
    public void deleteAttendance(int professorId, int studentId, String attendance){}
//----------------------------------출결 생성,수정,삭제----------------------------------
}
