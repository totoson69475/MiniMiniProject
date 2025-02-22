package classes;

import java.util.ArrayList;

class  ProfessorDB
{
    /*
    String[][] Professor = {
        {"2012345", "12345","김교수", "010-1234-5678", "kim@naver.com","서울시 노원구","컴퓨터소프트웨어학과","총합30, 출근30","C,Java,Python"},
        {"2023456", "23456","강교수", "010-2345-6789", "kang@naver.com","서울시 노원구","컴퓨터소프트웨어학과","총합30, 출근30","C#,JavaScript,DataBase"},
        {"2034567", "34567","김교수", "010-3456-7890", "hong@naver.com","서울시 노원구","컴퓨터소프트웨어학과","총합30, 출근30","C++,JavaSpring,캡스톤디자인"}
    };
    */
    ArrayList<ProfessorDTO> Professor = new ArrayList<ProfessorDTO>();

    public void professorDBSet(){
        Professor.add(new ProfessorDTO("2012345", "12345","김교수", "010-1234-5678", "kim@naver.com","서울시 노원구","컴퓨터소프트웨어학과","총합30, 출근30","C,Java,Python"));
        Professor.add(new ProfessorDTO("2023456", "23456","강교수", "010-2345-6789", "kang@naver.com","서울시 노원구","컴퓨터소프트웨어학과","총합30, 출근30","C#,JavaScript,DataBase"));
        Professor.add(new ProfessorDTO("2034567", "34567","김교수", "010-3456-7890", "hong@naver.com","서울시 노원구","컴퓨터소프트웨어학과","총합30, 출근30","C++,JavaSpring,캡스톤디자인"));
    }

    //int id, String name, String phoneNumber, String email, String address, String department, String attendance, String subject
}
