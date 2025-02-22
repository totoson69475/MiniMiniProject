package classes;
import java.util.*;
public class  StudentDB
{
    // 학번
    public String[] id = {"101", "102", "103", "104", "105"};
    // 비밀번호
    String[] password = {"101", "102", "103", "104", "105"};
    // 학생 이름
    String name[] = {"짱구", "철수", "유리", "훈이", "맹구"};
    // 학생 전화번호
    String phoneNumber[] = {"010-1111-1111", "010-2222-2222", "010-3333-3333",
            "010-4444-4444", "010-5555-5555"};
    // 이메일
    String email[] = {"abc123@naver.com", "abc123@naver.com", "abc123@naver.com",
            "abc123@naver.com", "abc123@naver.com"};
    // 주소
    String address[] = {"서울", "경기", "인천", "경상", "전라"};
    // 소속
    String department[] = {"기계공학과", "생명공학과", "체육교육과", "시각디자인과", "철학과"};


    // 출결
    //String attendance[] = {"지각", "출석", "출석", "결석", "결석"};
    // 성적
    //String grades[] = {"C", "A+", "B+", "F", "A+"};

    public ArrayList<String> attendance = new ArrayList<>();
    public ArrayList<String> grades = new ArrayList<>();

    StudentDB(){
        this.attendance.add("출석");
        this.grades.add("A");
        this.grades.add("B");
        this.grades.add("C");
    }
}

/*

 */