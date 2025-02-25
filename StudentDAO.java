package classes;

import java.util.ArrayList;
import java.util.Scanner;
class  StudentDAO extends User
{
    UserAPI Liner = new UserAPI();
    LectureDB lectureDB = new LectureDB();
    public static StudentDB db = new StudentDB();
    public static StudentDTO stuAry[] = new StudentDTO[db.id.length];
    StudentDTO stuDto = new StudentDTO();
    // SelectedLecture selectedLecture = new SelectedLecture();
    ArrayList<Lecture> lectureAry = new ArrayList<Lecture>();
    Scanner input = new Scanner(System.in);

    int stuCnt;
    int lectureCnt;
    boolean stuIdChk = false;
    int stuIdx = -1;

    public void stuSet(){
        stuCnt = stuAry.length;
        for(int idx = 0; idx < stuCnt; idx++){
            // stuAry[idx] = StudentDTO 객체 생성
            stuAry[idx] = new StudentDTO(db.id[idx], db.password[idx], db.name[idx],
                    db.phoneNumber[idx], db.email[idx], db.address[idx],
                    db.department[idx]);
        }
    }

    // 그냥 아예 ProfessorDAO의 lecture를 그대로 가져다 사용함으로써 필요없어질수도 있습니다.
    public void lectureSet(){ // ProfessorDAO의 lectureAry와 연동하도록 변경 이제 교수가 입력한 강의를 가져온다.
        if(lectureAry.isEmpty()) { // lectureAry가 비어있으면 그냥 바로 전체 다 추가.
            for(int i = 0; i < ProfessorDAO.lectureAry.size(); i++) {
                lectureAry.add(new Lecture(ProfessorDAO.lectureAry.get(i)));
            }
        } else { // lecutreAry가 비어있지 않다면 없는 부분(새로 추가한 강의)만 새로 가져와서 추가
            for(int i = lectureAry.size(); i < ProfessorDAO.lectureAry.size(); i++) {
                lectureAry.add(new Lecture(ProfessorDAO.lectureAry.get(i)));
            }
        }
    }

    void viewCurrentLecture(int studentId){		// 현재 수강 강좌 조회 메서드(사용 X)
        System.out.println("현재 수강 강의 메서드");
        System.out.println("학생 학번: " + studentId);
        // 현재 수강 강좌를 수강 신청 메서드 안에 넣어둠
    }

    void signUpLecture(int studentId){			// 강의 수강 신청 메서드
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){		// 매개변수 studentId를 통해 학생 index 알아낸 후 findIndex에 저장
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        Liner.mLine('-', 31);
        System.out.println("수강 신청 화면");
        Liner.mLine('-', 31);
        StudentDTO stuDto = stuAry[findIndex];
        System.out.println(stuAry[findIndex].getName() + "의 현재 수강 중인 강의: ");
        stuDto.printCourses();					// 현재 수강 중인 강의 출력
        Liner.mLine('-', 31);
        System.out.println("수강하고자 하는 강의 번호를 고르세요.[뒤로가기: Q ]");
        while(true){
            System.out.println("     <현재 강의 목록>     ");
            for (int i = 0; i < ProfessorDAO.lectureAry.size(); i++) {
                System.out.println("강의 번호" + (i + 1) + ") " + ProfessorDAO.lectureAry.get(i).getCoursesName());
            }
            System.out.printf("선택한 강의: ");
            String userInput = input.nextLine();
            if(userInput.equalsIgnoreCase("Q")){
                System.out.println("이전 화면으로 돌아갑니다.");
                break;
            } try {
                Integer.parseInt(userInput); // 입력이 숫자가 아니면 예외 발생
                if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > ProfessorDAO.lectureAry.size()){		// 입력한 숫자가 1보다 작거나 강의 번호보다 많은 경우
                    Liner.mLine('#', 31);
                    System.out.println("범위에서 벗어난 번호입니다. 재입력하세요.");
                    Liner.mLine('#', 31);
                } else {
                    int tempInt = Integer.parseInt(userInput) - 1;                                  // 선택한 번호에서 1을 감소하여 index값 추출
                    Lecture selectLecture = ProfessorDAO.lectureAry.get(tempInt);                             // selectLecture에 선택한 index값 강의 정보 저장
                    if(selectLecture.getEnrolledStudent() >= selectLecture.getMaxStudent()){			// selectLecture의 정원이 다 찬 경우 강의 재선택
                        Liner.mLine('#', 31);
                        System.out.println("강의 정원이 다 찼습니다.");
                        System.out.println("다른 강의를 선택하세요.");
                        Liner.mLine('#', 31);
                    } else {																			// selectLecture 강의에 정원이 아직 안 찬 경우
                        stuDto.addCourses(selectLecture);                                               // StudentDTO의 수강 강의 정보(Lectures) 배열에 추가
                        selectLecture.setEnrolledStudent(selectLecture.getEnrolledStudent() + 1);       // 선택한 강의 수강 학생 수 1 증가
						selectLecture.studentID.add(stuDto.getId());

						/*
						for(LectureDTO lecture : lectureAry){   // 학생 수 증가 정상 작동 여부 확인
							System.out.println(lecture.getCoursesName() + " 강의의 현재 학생 수: " + lecture.getEnrolledStudent());
						}
						*/
                        break;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("숫자가 아닌 문자가 입력되었습니다.");
                System.out.printf("다시 입력하세요[뒤로가기 :Q] : ");
            }
        }
    }

    void viewGrade(int studentId){				// 성적 조회 메서드
        System.out.println("학생 학번: " + studentId);
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        StudentDTO stuDto = stuAry[findIndex];
        // 학생 정보 중에서 교수님이 입력해준 성적을 출력해야 함
        int cnt = 0;
        System.out.println("강의별 성적을 출력합니다");
        // System.out.println(stuDto.courses.size());
        // System.out.println("강의 이름    -    성적");
        stuDto.printGrade();

    }

    void viewAttendance(int studentId){			// 출결 조회 메서드
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        StudentDTO stuDto = stuAry[findIndex];
        System.out.println("강의별 출석을 출력합니다\n");
        // System.out.println(stuDto.courses.size());
        // System.out.println("강의 이름    -    성적");
        stuDto.printAttendance();

    }
    // =========================================================== Test ===========================================================
    void test11(int studentId){
        System.out.println("수강강좌 확인 후 성적 입력");
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){		// 매개변수 studentId를 통해 학생 index 알아낸 후 findIndex에 저장
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        StudentDTO stuDto = stuAry[findIndex];
        System.out.println(stuAry[findIndex].getName() + "의 현재 수강 중인 강의: ");
        stuDto.printCourses();
        System.out.println("강좌 이름 입력: ");
        String _courseName = input.nextLine();
        System.out.println("성적 입력: ");
        String _grade = input.nextLine();
        stuDto.addGrade(_courseName, _grade);
    }
    // =========================================================== Test ===========================================================

    void test22(int studentId){
        System.out.println("수강강좌 확인 후 성적 입력");
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){		// 매개변수 studentId를 통해 학생 index 알아낸 후 findIndex에 저장
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        StudentDTO stuDto = stuAry[findIndex];
        System.out.println(stuAry[findIndex].getName() + "의 현재 수강 중인 강의: ");
        stuDto.printCourses();
        System.out.println("강좌 이름 입력: ");
        String _courseName = input.nextLine();
        System.out.println("출결 입력: ");
        String _attendance = input.nextLine();
        stuDto.addAttendance(_courseName,1,_attendance);
    }


    public void viewUserInfo(int studentId){
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){				// 매개변수 studentId를 통해 학생 index 알아낸 후 findIndex에 저장
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        if(findIndex == -1){							// 학생 정보가 없는 경우(findIndex값을 찾지 못했을 때)
            System.out.println("학생 정보가 없습니다");
        } else {										// 학생 정보가 있는 경우(findIndex 값을 찾았을 때)
            System.out.println("┌──────────학생 정보───────────┐");
            System.out.printf("│ - 학    번: %s              │\n", stuAry[findIndex].getId());
            System.out.printf("│ - 이    름: %s             │\n", stuAry[findIndex].getName());
            System.out.printf("│ - 전화번호: %s    │\n", stuAry[findIndex].getPhoneNumber());
            System.out.printf("│ - 이 메 일: %s │\n", stuAry[findIndex].getEmail());
            System.out.printf("│ - 주    소: %s             │\n", stuAry[findIndex].getAddress());
            System.out.printf("│ - 학    과: %s       │\n", stuAry[findIndex].getDepartment());
            System.out.println("└──────────────────────────────┘");
        }

    }


    public void editUserInfo(int studentId){
        int findIndex = -1;
        for(int i = 0; i < stuCnt; i++){				// 매개변수 studentId를 통해 학생 index 알아낸 후 findIndex에 저장
            if(Integer.parseInt(stuAry[i].getId()) == studentId){
                findIndex = i;
                break;
            }
        }
        System.out.println("수정하려는 정보의 번호를 입력하세요");
        System.out.println("1) 이름");
        System.out.println("2) 전화번호");
        System.out.println("3) 이메일");
        System.out.println("4) 주소");
        System.out.printf("입력[뒤로가기: Q] : ");
        while(true){
            String userInput = input.nextLine();
            if(userInput.equalsIgnoreCase("Q")){
                System.out.println("이전 화면으로 돌아갑니다.");
                break;
            } try {
                Integer.parseInt(userInput);
                if(Integer.parseInt(userInput) > 4 || Integer.parseInt(userInput) < 1){
                    System.out.println("범위에서 벗어난 숫자입니다.");
                    System.out.printf("다시 입력하세요[뒤로가기: Q] : ");
                    continue;
                } else{
                    switch(Integer.parseInt(userInput)){
                        case 1:
                            System.out.println("현재 이름: " + stuAry[findIndex].getName());
                            System.out.printf("수정할 이름을 입력하세요: ");
                            String newName = input.nextLine();
                            stuAry[findIndex].setName(newName);
                            System.out.println("수정된 이름: " + stuAry[findIndex].getName());
                            break;
                        case 2:
                            System.out.println("현재 전화번호: " + stuAry[findIndex].getPhoneNumber());
                            System.out.printf("수정할 전화번호를 입력하세요: ");
                            String newPhoneNumber = input.nextLine();
                            stuAry[findIndex].setPhoneNumber(newPhoneNumber);
                            System.out.println("수정된 전화번호: " + stuAry[findIndex].getPhoneNumber());
                            break;
                        case 3:
                            System.out.println("현재 이메일: " + stuAry[findIndex].getEmail());
                            System.out.printf("수정할 이메일을 입력하세요: ");
                            String newEmail = input.nextLine();
                            stuAry[findIndex].setEmail(newEmail);
                            System.out.println("수정된 이메일: " + stuAry[findIndex].getEmail());
                            break;
                        case 4:
                            System.out.println("현재 주소: " + stuAry[findIndex].getAddress());
                            System.out.printf("수정할 주소를 입력하세요: ");
                            String newAddress = input.nextLine();
                            stuAry[findIndex].setAddress(newAddress);
                            System.out.println("수정된 주소: " + stuAry[findIndex].getAddress());
                            break;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자가 아닌 문자가 입력되었습니다.");
                System.out.printf("다시 입력하세요[뒤로가기 :Q] : ");
            }
        }



    }

}
