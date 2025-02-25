package classes;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

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
        dao.lectureSet();
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
                    System.out.println("## 변경 가능한 강의 목록 ##");
                    professorDao.viewMyLecture(loginProfessor);
                    System.out.print("변경하실 강의를 선택해주세요. [0. 돌아가기]: ");
                    int choiceLectureIdx;
                    choiceLectureIdx = input.nextInt();
                    input.nextLine();

                    if(choiceLectureIdx == 0){
                        professorMenu();
                    }

                    System.out.println("1. 강의명");
                    System.out.println("2. 강의장소");
                    System.out.println("3. 강의시간");
                    System.out.println("4. 강의 최대 수강 가능 인원수");
                    System.out.println("\n0. 돌아가기");

                    System.out.print("변경하실 내용을 선택해주세요. [0. 돌아가기] : ");

                    int choiceMenu = input.nextInt();
                    input.nextLine();
                    if(choiceMenu == 0){
                        professorMenu();
                    }

                    System.out.print("변경하실 값을 입력해주세요. : ");
                    String changeValue = input.nextLine();

                    professorDao.editLecture(loginProfessor, choiceLectureIdx, choiceMenu, changeValue);
                }

                if(selectLectureMenu.equals("3")){
                    System.out.println("## 삭제 가능한 강의 목록 ##");
                    professorDao.viewMyLecture(loginProfessor);
                    System.out.print("삭제하실 강의를 선택해주세요. : ");
                    int choiceLectureIdx;
                    choiceLectureIdx = input.nextInt();
                    input.nextLine();

                    professorDao.deleteLecture(loginProfessor, choiceLectureIdx);

                }

                if(selectLectureMenu.equals("4")){
                    System.out.println("## "+loginProfessor.name+" 교수님의 담당 강의 ##");
                    searchMyLectureAry = professorDao.viewMyLecture(loginProfessor);
                    System.out.println("\n0. 돌아가기");

                    int choiceLecture = -1; // 초기값 설정
                    while (true) {
                        System.out.print("상세정보를 보고싶은 강의를 선택하세요. [0 : 돌아가기] : ");
                        try {
                            choiceLecture = input.nextInt();
                            input.nextLine(); // 버퍼 비우기

                            if (choiceLecture == 0) {
                                professorMenu();
                                break; 
                            }

                            // 선택한 강의 정보 출력
                            System.out.println("담당교수 : " + searchMyLectureAry.get(choiceLecture - 1).getProfessorName());
                            System.out.println("강의명 : " + searchMyLectureAry.get(choiceLecture - 1).getCoursesName());
                            System.out.println("강의시간 : " + searchMyLectureAry.get(choiceLecture - 1).getClassTime());
                            System.out.println("수강 학생 인원 수 : " + searchMyLectureAry.get(choiceLecture - 1).getEnrolledStudent());
                            System.out.println("최대 수강 가능 인원 수 : " + searchMyLectureAry.get(choiceLecture - 1).getMaxStudent());
							if(searchMyLectureAry.get(choiceLecture - 1).studentID.isEmpty()){
								System.out.println("수강중인 학생이 없습니다.");
							}else {
								ArrayList<String> enrolledStudentName = new ArrayList<String>();
								for(int idx = 0; idx < searchMyLectureAry.get(choiceLecture - 1).studentID.size(); idx++){
									for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
										if(StudentDAO.stuAry[stuAryIdx].getId().equals(searchMyLectureAry.get(choiceLecture - 1).studentID.get(idx))){
											enrolledStudentName.add(StudentDAO.stuAry[stuAryIdx].getName());
										}
									}
								}
								System.out.println("수강 학생 명단 : " + enrolledStudentName.toString());
							}
                            break; 

                        } catch (InputMismatchException e) {
                            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                            input.nextLine(); 
							
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("유효하지 않은 강의 선택입니다. 다시 선택해주세요.");
                        }					
                    }
                }


            }

            if(vRead1.equals("2")){
                System.out.println("## "+loginProfessor.name+" 교수님의 담당 강의 ##");
				searchMyLectureAry = professorDao.viewMyLecture(loginProfessor);
                System.out.println("\n0. 돌아가기");

				int choiceLecture = -1; // 초기값 설정
                    while (true) {
                        System.out.print("성적을 관리할 과목을 선택하십시오. [0. 돌아가기] : ");
                        try {
                            choiceLecture = input.nextInt();
                            input.nextLine(); // 버퍼 비우기

                            if (choiceLecture == 0) {
                                professorMenu();
                                break; 
                            }

                            // 선택한 강의 정보 출력
							if(searchMyLectureAry.get(choiceLecture - 1).studentID.isEmpty()){
								System.out.println("수강중인 학생이 없습니다.");
							}else {
                                ArrayList<String> enrolledStudentID = new ArrayList<String>();
								ArrayList<String> enrolledStudentName = new ArrayList<String>();
								for(int idx = 0; idx < searchMyLectureAry.get(choiceLecture - 1).studentID.size(); idx++){
									for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
										if(StudentDAO.stuAry[stuAryIdx].getId().equals(searchMyLectureAry.get(choiceLecture - 1).studentID.get(idx))){
                                            enrolledStudentID.add(StudentDAO.stuAry[stuAryIdx].getId());
											enrolledStudentName.add(StudentDAO.stuAry[stuAryIdx].getName());
										}
									}
								}
								Liner.mLine('=',31);
								System.out.println("  ## "+searchMyLectureAry.get(choiceLecture - 1).coursesName+"과목 수강 학생 명단 ##");
								Liner.mLine('=',31);
								int cnt = 0;
								for(String studentName : enrolledStudentName){
									System.out.println((cnt+1) + ". "+searchMyLectureAry.get(choiceLecture - 1).studentID.get(cnt) +" "+ studentName);
                                    cnt++;
								}

								System.out.print("\n\n성적을 관리할 학생을 선택하십시오. : ");
								int choiceStudent = 0;
								choiceStudent = input.nextInt();
								input.nextLine(); // 버퍼 비우기
								
								System.out.println(enrolledStudentName.get(choiceStudent-1)+"의 성적관리를 선택하였습니다.");
								Liner.mLine('-',31);
								System.out.println("1. 성적 입력");
								System.out.println("2. 성적 수정");
								System.out.println("3. 성적 삭제");
								System.out.println("\n\n0.돌아가기");
								System.out.print("원하시는 항목을 선택하십시오. : ");

								int gradeControl = 0;
								gradeControl = input.nextInt();
								input.nextLine(); // 버퍼 비우기

								if(gradeControl == 1){
									System.out.print("부여하실 성적을 입력해주세요 : ");
									String grade = "";
									grade = input.nextLine();
									professorDao.registerGrade(enrolledStudentID.get(choiceStudent-1),searchMyLectureAry.get(choiceLecture - 1).coursesName,grade);
								}
								if(gradeControl == 2){
                                    System.out.print("수정하실 성적을 입력해주세요 : ");
                                    String grade = "";
                                    grade = input.nextLine();
                                    professorDao.editGrade(enrolledStudentID.get(choiceStudent-1),searchMyLectureAry.get(choiceLecture - 1).coursesName,grade);
								}
								if(gradeControl == 3){
                                    System.out.println("성적을 삭제하였습니다.");
									professorDao.deleteGrade(enrolledStudentID.get(choiceStudent-1),searchMyLectureAry.get(choiceLecture - 1).coursesName);
								}
								break; 
							}
                        } catch (InputMismatchException e) {
                            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                            input.nextLine(); 
							
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("유효하지 않은 강의 선택입니다. 다시 선택해주세요.");
                        }					
                    }
            }

            if(vRead1.equals("3")){
                System.out.println("## "+loginProfessor.name+" 교수님의 담당 강의 ##");
                searchMyLectureAry = professorDao.viewMyLecture(loginProfessor);
                System.out.println("\n0. 돌아가기");

                int choiceLecture = -1; // 초기값 설정
                while (true) {
                    System.out.print("출석을 관리할 과목을 선택하십시오. [0. 돌아가기] : ");
                    try {
                        choiceLecture = input.nextInt();
                        input.nextLine(); // 버퍼 비우기

                        if (choiceLecture == 0) {
                            professorMenu();
                            break;
                        }

                        // 선택한 강의 정보 출력
                        if(searchMyLectureAry.get(choiceLecture - 1).studentID.isEmpty()){
                            System.out.println("수강중인 학생이 없습니다.");
                        }else {
                            ArrayList<String> enrolledStudentID = new ArrayList<String>();
                            ArrayList<String> enrolledStudentName = new ArrayList<String>();
                            for(int idx = 0; idx < searchMyLectureAry.get(choiceLecture - 1).studentID.size(); idx++){
                                for(int stuAryIdx = 0; stuAryIdx < StudentDAO.stuAry.length; stuAryIdx++){
                                    if(StudentDAO.stuAry[stuAryIdx].getId().equals(searchMyLectureAry.get(choiceLecture - 1).studentID.get(idx))){
                                        enrolledStudentID.add(StudentDAO.stuAry[stuAryIdx].getId());
                                        enrolledStudentName.add(StudentDAO.stuAry[stuAryIdx].getName());
                                    }
                                }
                            }
                            Liner.mLine('=',31);
                            System.out.println("  ## "+searchMyLectureAry.get(choiceLecture - 1).coursesName+"과목 수강 학생 명단 ##");
                            Liner.mLine('=',31);
                            int cnt = 0;
                            for(String studentName : enrolledStudentName){
                                System.out.println((cnt+1) + ". "+searchMyLectureAry.get(choiceLecture - 1).studentID.get(cnt) +" "+ studentName);
                                cnt++;
                            }

                            System.out.print("\n\n출석을 관리할 학생을 선택하십시오. : ");
                            int choiceStudent = 0;
                            choiceStudent = input.nextInt();
                            input.nextLine(); // 버퍼 비우기

                            System.out.println(enrolledStudentName.get(choiceStudent-1)+"의 출석관리를 선택하였습니다.");
                            Liner.mLine('-',31);
                            System.out.println("1. 출석 입력");
                            System.out.println("2. 출석 수정");
                            System.out.println("3. 출석 삭제");
                            System.out.println("\n\n0.돌아가기");
                            System.out.print("원하시는 항목을 선택하십시오. : ");

                            int gradeControl = 0;
                            gradeControl = input.nextInt();
                            input.nextLine(); // 버퍼 비우기

                            if(gradeControl == 1){
                                System.out.print("현재 주차를 입력해주세요 : ");
                                int week = 0;
                                week = input.nextInt();
                                input.nextLine();

                                System.out.print("부여하실 출석 내용을 입력해주세요 : ");
                                String attendance = "";
                                attendance = input.nextLine();
                                professorDao.registerAttendance(enrolledStudentID.get(choiceStudent-1),searchMyLectureAry.get(choiceLecture - 1).coursesName,week,attendance);

                            }
                            if(gradeControl == 2){
								System.out.print("수정하실 주차를 입력해주세요 : ");
                                int week = 0;
                                week = input.nextInt();
                                input.nextLine();

                                System.out.print("수정하실 출석 내용을 입력해주세요 : ");
                                String attendance = "";
                                attendance = input.nextLine();
                                professorDao.editAttendance(enrolledStudentID.get(choiceStudent-1),searchMyLectureAry.get(choiceLecture - 1).coursesName,week,attendance);
                            }
                            if(gradeControl == 3){
								System.out.print("삭제하실 주차를 입력해주세요 : ");
                                int week = 0;
                                week = input.nextInt();
                                input.nextLine();
                                System.out.println("출석 내용을 삭제하였습니다.");
                                professorDao.deleteAttendance(enrolledStudentID.get(choiceStudent-1),searchMyLectureAry.get(choiceLecture - 1).coursesName,week);
                            }
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                        input.nextLine();

                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("유효하지 않은 선택입니다. 다시 선택해주세요.");
                    }
                }
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
        // obj.dao.lectureSet(); -> 시작할때 DB 생성시 오류 로그인한 이후에 DB 불러오도록 변경
        obj.startMenu();
    }
}
