package classes;

import java.util.Scanner;

class ProfessorDTO extends User
{
    String subject;
    Scanner input = new Scanner(System.in);

    public ProfessorDTO(String id, String password,String name, String phoneNumber, String email, String address, String department, String attendance, String subject){
        super(id, password, name, phoneNumber, email, address, department, attendance);
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }

    public void viewUserInfo(){
        System.out.println("ID : " + this.getId());
        System.out.println("비밀번호 : " + this.getPassword());
        System.out.println("이름 : " + this.getName());
        System.out.println("전화번호 : " + this.getPhoneNumber());
        System.out.println("이메일 : " + this.getEmail());
        System.out.println("주소 : " + this.getAddress());
        System.out.println("학과 : " + this.getDepartment());
        System.out.println("출석현황 : " + this.getAttendance());
        System.out.println("담당과목 : " + this.getSubject());
    }

    public void editUserInfo(){
        System.out.println("01. 비밀번호");
        System.out.println("02. 이름");
        System.out.println("03. 전화번호");
        System.out.println("04. 이메일");
        System.out.println("05. 주소");
        System.out.println("06. 학과");
        System.out.print("수정하실 개인정보를 선택하세요. : ");

        String userInput = "";
        String changeData = "";
        userInput = input.nextLine();
        // validate 추가

        if(userInput.equals("1")){
            System.out.print("변경하실 비밀번호를 입력해주세요. : ");
            changeData = input.nextLine();
            this.setPassword(changeData);
            System.out.println("비밀번호 변경 완료!.");
        }

        if(userInput.equals("2")){
            System.out.print("변경하실 이름을 입력해주세요. : ");
            changeData = input.nextLine();
            this.setName(changeData);
            System.out.println("이름 변경 완료!.");
        }

        if(userInput.equals("3")){
            System.out.print("변경하실 전화번호를 입력해주세요. : ");
            changeData = input.nextLine();
            this.setPhoneNumber(changeData);
            System.out.println("전화번호 변경 완료!.");
        }

        if(userInput.equals("4")){
            System.out.print("변경하실 이메일을 입력해주세요. : ");
            changeData = input.nextLine();
            this.setEmail(changeData);
            System.out.println("이메일 변경 완료!.");
        }

        if(userInput.equals("5")){
            System.out.print("변경하실 주소를 입력해주세요. : ");
            changeData = input.nextLine();
            this.setAddress(changeData);
            System.out.println("주소 변경 완료!.");
        }

        if(userInput.equals("6")){
            System.out.print("변경하실 학과를 입력해주세요. : ");
            changeData = input.nextLine();
            this.setDepartment(changeData);
            System.out.println("학과 변경 완료!.");
        }

    }





}
