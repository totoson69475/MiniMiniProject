package classes;

class  LectureDB
{
    //ArrayList<LectureDTO> lectureAry = new ArrayList<LectureDTO>();
    //교수 ID
    String professorID;
    // 교수 이름
    String professorName;
    String coursesName[] = {"데이터베이스", "생물1", "대학수학", "심리철학", "채플"};
    String classRoom[] = {"인관 401호", "은봉관 502호", "은봉관 510호", "덕관 203호", "대강당"};
    String classTime[] = {"3", "3", "3", "2", "2"};
    int[] enrolledStudent = {0, 0, 0, 0, 0};
    int[] maxStudent = {5, 5, 5, 5, 5};
/*
	for(int i = 0; i < coursesName.length; i++){
		lectureAry.add(new LectureDTO(coursesName[i], classRoom[i],
			classTime[i], enrolledStudent[i], maxStudent[i]));
		}
		*/

}
