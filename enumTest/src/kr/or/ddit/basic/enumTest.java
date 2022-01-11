package kr.or.ddit.basic;
/*
 
enum(열거형) ==> 서로 관련있는 상수들의 집합을 나타낸다.
           ==> 클래스처럼 보이게하는 상수
           ==> 열거형의적성은 일반 클래스처럼 독입된 java파일에 만들수 있고,
                            하나의 java파일에 클래스처럼 만들수 있고,
                            또 클래스 안에 내부 클래스처럼 만들수 있다.
 		       
  - 열거형의 속성 및 메서드
    * name()    ==> 열거형 상수의 이름문자열로 반환한다
    * ordinal() ==> 열거형 상수가 정의된 순서값(index값)을 반환한다. (0부터 시작)
    * valueOd("열거형상수명")  ==> 저장된 열거형에서 "열거형상수명"과 일치하는
                                                           열거형 상수를 반환한다.
    * 열거형이름.상수명  ==> valueOf()메서드와 같다.
    * 열거형이름.values()  ==> 모든 상수들을 배열로 반환한다.
   
  - 열거형 선언하기
 	  방법1)
 	  enum 열거형이름 {상수명1, 상수명2, 상수명3,....}
 	  
 	 방법2)
 	  enum 열거형이름 {
 	  	상수명1(값들...),
 	  	상수명2(값들...),
 	  	...
 	  	상수명n(값들...);
 	  	
 	  	//'값들'이 저장될 변수들 선언...
 	  	private 자료형이름 변수명;
 	  	...
 	  	
 	  	//열거형의 생성자를 작성한다.
 	  	//열거형의 생성자는 '열거형상수'에  '값들'을 셋팅하는 역활을 수행한다.
 	  	//열거형의 생성자의 접근 제한자는 묵시적으로 private이다.
 	  	
 	  	//메개변수에 주어지는 '변수명'들은 '값들'과 개수가 같고,
 	  	//각각의 '값들'과 자료형이 맞아야 한다.
 	  	private 열거형이름(자료형이름 변수명, ...){
 	  		위에 선언된 '값들'이 저장될 변수에 대한 초기화 작업을 수행한다.
 	  		...
 	  	}
 	  	
 	  	//위에 선언된 '값들'이 저장될 변수를 외부에서 사용할 수 있도록
 	  	//getter메서드를 작성한다.
 	  	...
 	  	

 	  }
 	  
 	  
 */
public class enumTest {
	public enum Color {RED, GREEN, BLUE}
	public enum Count {ONE, TWO, THREE}
	
	public enum Season{
		봄("3월부터 5월까지",1), // 상수명(값들..)형식의 선언
		여름("6월부터 8월까지",2),
		가을("9월부터 11월까지",3),
		겨울("12월부터 2월까지",4);
		
		//값들이 저장될 변수 선언
		private String span;
		private int num;
		
		//생성자
		private Season(String months, int data) {
			span = months;
			num = data;
			
		}
		
		//getter메서드 작성
		public String getSpan() {
			return span;
		}
		public int getNum() {
			return num;
		}
		
	}

	public static void main(String[] args) {
		/*
		System.out.println("RED : " + ConstTest.RED);
		System.out.println("Three : " + ConstTest.THREE);
		
		if(ConstTest.RED == ConstTest.ONE) {
			System.out.println(".........");
		}else {
			System.out.println("&&&&&&&&&&&&&&&");
		}
		*/
		
		Color mycol = Color.valueOf("GREEN"); //Color.GREEN와 같다
		Count mycnt = Count.ONE; // Count.valueOf와 같다.
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		System.out.println();
		System.out.println("mycol의 ordinal : " + mycol.ordinal());
		System.out.println("mycnt의 ordinal : " + mycnt.ordinal());
		
		/*
		//서로 다른 종류의 열거형 끼리의 비교는 불가하다.
		if(Color.RED == Count.ONE) {
			System.out.println("...");
		}
		*/
		
		if(mycol == Color.GREEN) {
			System.out.println("같다");
		}
		
		// switch문에서 열거형 비교하기 
		// ==> case문에 지정하는 값은 열거형이름을 생략한 상수명만 기술한다.
		switch(mycnt) {
		case ONE:
			System.out.println("ONE입니다"); break;
		case TWO:
			System.out.println("ONE입니다"); break;
		case THREE:
			System.out.println("ONE입니다"); break;
		
		}
		System.out.println("----------------------------------");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("num : " + ss.getNum());
		System.out.println();
		
		System.out.println("----------------------------------");

		//열거형의 모든 상수들을 차례로 가져와서 출력하기
		for(Season time : Season.values()) {
			System.out.println(time.name() + " == " + time +" --> "+ time.getNum()+ "번째 상수값 : " + time.getSpan());
		}
		
	}

}
