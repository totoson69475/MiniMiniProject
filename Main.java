package classes;

import java.util.Scanner;
import java.util.ArrayList;

class  Main
{
    boolean validateChk = false;
    boolean studentIdChk = false;
    boolean professorIdChk = false;
    int studentIdx = -1;
    int loginProfessorIdx;

    StudentDAO dao = new StudentDAO();
    int studentId;	// 학번(StudentDAO에 학생 정보 전달용)
    ProfessorDAO professorDao = new ProfessorDAO();

    ProfessorDTO loginProfessor;

    ArrayList<Lecture> searchMyLectureAry = new ArrayList<Lecture>();

    UserAPI Liner = new UserAPI();
    Scanner input = new Scanner(System.in);

    void startMenu(){
        loginProfessorIdx = -1;
        while(true){
            Liner.mLine('=',31);
            System.out.println("## 인덕대 종합 학사 관리 시스템 ##");
            Liner.mLine('=',31);
            System.out.println("1. 학생 로그인");
            System.out.println("2. 교수 로그인");
            System.out.println("\n\nQ.종료");
            Liner.mLine('-',31);
            System.out.print("\t ^ 메뉴를 선택 하세요 : ");

            String vRead1;
            validateChk = false;

            vRead1 = input.nextLine();

            inputValidate(vRead1);

            if(validateChk) continue;

            if(vRead1.equals("1")){
                studentLogin();
            }

            if(vRead1.equals("2")){
                professorLogin();
            }

            if(vRead1.equalsIgnoreCase("q")){
                System.out.println("시스템을 종료합니다.");
                System.exit(0);
            }
        }
    }

    void studentLogin(){
        System.out.println("\t ^ 학생 로그인 진행");
        while(true){
            System.out.print("학번을 입력해주세요 [Q : 취소] : ");

            String ID;
            validateChk = false;

            ID = input.nextLine();

            if(ID.equalsIgnoreCase("q")){
                System.out.println("로그인을 취소합니다.");
                return;
            }

			/*
			inputValidate(vRead1);

			if(validateChk) continue;
			*/

            System.out.print("비밀번호를 입력해주세요 : ");
            String PWD;
            PWD = input.nextLine();

            // 조건 분기
            for(int idx = 0; idx < dao.stuAry.length; idx++){
                if(dao.stuAry[idx].getId().equals(ID) && dao.stuAry[idx].getPassword().equals(PWD)){
                    System.out.println("로그인 성공!");
                    System.out.println("환영합니다 " + dao.stuAry[idx].getName() + "님!");
                    studentId = Integer.parseInt(ID);		// 로그인 성공 시 입력한 학번 int형으로 저장
                    studentMenu();
                }
            }
            System.out.println("로그인 실패");
        }
    }

    void professorLogin(){
        System.out.println("\t ^ 교수 로그인 진행");

        while(true){
            System.out.print("사번을 입력해주세요 [Q : 취소] : ");

            String ID;
            validateChk = false;

            ID = input.nextLine();

            if(ID.equalsIgnoreCase("q")){
                System.out.println("로그인을 취소합니다.");
                return;
            }

            System.out.print("비밀번호를 입력해주세요 : ");
            String PWD;
            PWD = input.nextLine();

            for(int idx = 0; idx < professorDao.professorAry.size(); idx++){
                if(professorDao.professorAry.get(idx).getId().equals(ID) && professorDao.professorAry.get(idx).getPassword().equals(PWD)){
                    System.out.println("로그인 성공!");
                    System.out.println("환영합니다 " + professorDao.professorAry.get(idx).getName() + "님!");
                    loginProfessor = professorDao.professorAry.get(idx);
                    loginProfessorIdx = idx;
                    professorMenu();
                }
            }
            System.out.println("로그인 실패");
        }
    }

    void studentMenu(){
        while(true){
            Liner.mLine('=',31);
            System.out.println("  ## 학생 학사 관리 시스템 ##  ");
            Liner.mLine('=',31);
            System.out.println("1. 수강 신청");
            System.out.println("2. 성적 조회");
            System.out.println("3. 출결 조회");
            System.out.println("4. 개인정보 조회");
            System.out.println("5. 개인정보 수정");
            System.out.println("\n\nQ.로그아웃");
            Liner.mLine('-',31);
            System.out.print("\t ^ 메뉴를 선택 하세요 : ");

            String vRead1;
            validateChk = false;

            vRead1 = input.nextLine();

            if(vRead1.equalsIgnoreCase("q")){
                System.out.println("로그아웃 합니다.");
                startMenu();
            }

            inputValidate(vRead1);

            if(validateChk) continue;

            if(vRead1.equals("1")){
                System.out.println("수강 신청 시스템 실행");
                dao.signUpLecture(studentId);
            }

            if(vRead1.equals("2")){
                dao.viewGrade(studentId);
            }
            if(vRead1.equals("3")){
                dao.viewAttendance(studentId);
            }
            if(vRead1.equals("4")){
                dao.viewUserInfo(studentId);
            }
            if(vRead1.equals("5")){
                dao.editUserInfo(studentId);
            }
        }
    }

    void professorMenu(){
        while(true){
            Liner.mLine('=',31);
            System.out.println(" ## 인덕대 학사 관리 시스템 ##  ");
            Liner.mLine('=',31);
            System.out.println("1. 강의 관리");
            System.out.println("2. 성적 관리");
            System.out.println("3. 출결 관리");
            System.out.println("4. 개인정보 조회");
            System.out.println("5. 개인정보 수정");
            System.out.println("\n\nQ.로그아웃");
            Liner.mLine('-',31);
            System.out.print("\t ^ 메뉴를 선택 하세요 : ");

            String vRead1;
            validateChk = false;

            vRead1 = input.nextLine();

            if(vRead1.equalsIgnoreCase("q")){
                System.out.println("로그아웃 합니다.");
                startMenu();
            }

            inputValidate(vRead1);

            if(validateChk) continue;

            if(vRead1.equals("1")){
                Liner.mLine('=',31);
                System.out.println("    ## 강의 관리 시스템 ##  ");
                Liner.mLine('=',31);
                System.out.println("1. 강의 생성");
                System.out.println("2. 강의 수정");
                System.out.println("3. 강의 삭제");
                System.out.println("4. 내 강의 목록 조회");
                System.out.println("\n\nQ.돌아가기");
                Liner.mLine('-',31);
                System.out.print("메뉴를 선택 하세요 : ");

                String selectLectureMenu;
                selectLectureMenu = input.nextLine();

                if(selectLectureMenu.equals("1")){
                    String coursesName;
                    String classRoom;
                    String classTime;
                    String enrolledStudent;
                    int maxStudent;

                    System.out.print("생성하실 강의 이름을 입력해 주세요 : ");
                    coursesName = input.nextLine();
                    System.out.print("\n사용하실 강의실 위치를 입력해 주세요 : ");
                    classRoom = input.nextLine();
                    System.out.print("\n생성하실 강의 수업시간을 입력해 주세요 : ");
                    classTime = input.nextLine();
                    System.out.print("\n생성하실 강의 최대 수강가능 인원 수를 입력해 주세요 : ");
                    maxStudent = input.nextInt();
                    input.nextLine();

                    professorDao.registerLecture(loginProfessor,coursesName
                            ,classRoom, classTime, maxStudent);
                }

                if(selectLectureMenu.equals("2")){
                    System.out.print("## 변경 가능한 강의 목록 ##");
                    professorDao.viewMyLecture(loginProfessor);
                    System.out.print("변경하실 강의를 선택해주세요. : ");
                    int choiceLectureIdx;
                    choiceLectureIdx = input.nextInt();
                    input.nextLine();

                    System.out.println("변경하실 내용을 선택해주세요.");
                    System.out.println("1. 강의명");
                    System.out.println("2. 강의장소");
                    System.out.println("3. 강의시간");
                    System.out.println("4. 강의 최대 수강 가능 인원수");

                    int choiceMenu = input.nextInt();
                    input.nextLine();

                    System.out.print("변경하실 값을 입력해주세요. : ");
                    String changeValue = input.nextLine();

                    professorDao.editLecture(loginProfessor, choiceLectureIdx, choiceMenu, changeValue);
                }

                if(selectLectureMenu.equals("3")){}
                if(selectLectureMenu.equals("4")){
                    System.out.println("## "+loginProfessor.name+" 교수님의 담당 강의 ##");
                    searchMyLectureAry = professorDao.viewMyLecture(loginProfessor);
                    System.out.print("상세정보를 보고싶은 강의를 선택하세요. [Q : 돌아가기] : ");
                    int choiceLecture = input.nextInt();
                    input.nextLine();
                    if((char)choiceLecture == 81 || (char)choiceLecture == 113){
                        System.out.println("돌아가기");
                    }

                    System.out.println("담당교수 : " + searchMyLectureAry.get(choiceLecture-1).getProfessorName());
                    System.out.println("강의명 : " + searchMyLectureAry.get(choiceLecture-1).getCoursesName());
                    System.out.println("강의시간 : " + searchMyLectureAry.get(choiceLecture-1).getClassTime());
                    System.out.println("수강 학생 명단 : " + searchMyLectureAry.get(choiceLecture-1).getEnrolledStudent());
                    System.out.println("최대 수강 가능 인원 수 : " + searchMyLectureAry.get(choiceLecture-1).getMaxStudent());



                }


            }

            if(vRead1.equals("2")){
                System.out.println("성적 관리 실행");
                System.out.println("성적을 관리할 과목을 선택하십시오.");
                System.out.println("성적을 관리할 학생을 선택하십시오.");
                System.out.println("01. 성적 입력");
                System.out.println("02. 성적 수정");
                System.out.println("03. 성적 삭제");
                System.out.println("\n\nQ.돌아가기");
            }
            if(vRead1.equals("3")){
                System.out.println("출결 조회 실행");
                System.out.println("출결을 관리할 과목을 선택하십시오.");
                System.out.println("출결을 관리할 학생을 선택하십시오.");
                System.out.println("01. 출결 입력");
                System.out.println("02. 출결 수정");
                System.out.println("03. 출결 삭제");
                System.out.println("\n\nQ.돌아가기");
            }
            if(vRead1.equals("4")){
                loginProfessor.viewUserInfo();
            }
            if(vRead1.equals("5")){
                loginProfessor.editUserInfo();
            }
        }
    }

    void inputValidate(String _userInput){

        if(_userInput.isEmpty()){
            System.out.println("\t ^ 값을 입력하신 후 엔터를 눌러 주세요.");
            validateChk = true;
            return;
        }

        if(1 < _userInput.length()){
            System.out.println("^ 하나만 입력 하세요\n\n\n");
            validateChk = true;
            return;
        }
		
		/*
		if(_userInput.charAt(0) < 49 || 52 < _userInput.charAt(0)){
			System.out.println("^ 1 ~ 4 사이의 메뉴번호 선택 하세요\n\n\n");
			validateChk = true;
			return;
		}
		*/

    }
    public static void main(String[] args)
    {
        Main obj = new Main();
        obj.dao.stuSet();
        obj.dao.lectureSet();
        obj.startMenu();
    }
}
