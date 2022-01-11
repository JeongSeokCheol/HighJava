package kr.or.ddit.basic;

public class GenericTest {

	public static void main(String[] args) {
		NonGeneric non1 = new NonGeneric();
		non1.setValue("가나다라");
		
		NonGeneric non2 = new NonGeneric();
		non2.setValue(100);
		
		String ret1 = (String)non1.getValue();
		System.out.println("문자열 데이터 : " + ret1);
		
		int ret2 = (int)non2.getValue();
		System.out.println("숫자형 데이터 : " + ret2);
		System.out.println("-----------------------------------");
		
		MyGeneric<String> gen1 = new MyGeneric();
		gen1.setValue("안녕하세요");
		
		MyGeneric<Integer> gen2 = new MyGeneric();
		gen2.setValue(200);
		
		String getTest1 = gen1.getValue();
		System.out.println("제네릭의 문자열 데이터 : " + getTest1);

		int getTest2 = gen2.getValue();
		System.out.println("제네릭의 숫자형 데이터 : " + getTest2);
		
		int test = (int)non1.getValue();
		System.out.println("변환값 test = " + test);
		
//		int test2 = gen1.getValue();
		
		
	}

}

/*
 제네릭 클래스를 만드는 방법
 
 형식)
 class 클래스명<제네릭타입글자>{
 	제네릭타입글자 변수명; //변수를 선언할때 제네릭을 사용할 경우
 	...
 	
 	제네릭타입글자 메서드명(){ //메서드의 반환값으로 제네릭을 사용할 경우
 	 	....
 	 	return 값;
 	}
 	
 	void 메서드명(제네릭타입글자 변수명){ //메서드의 매개변수에 제네릭을 사용할 경우
 		...
 
 	}
 	
 	
 	
 }
 
 */

class MyGeneric<T>{
	private T value; // 변수 선언에 사용
	
	public T getValue() { //메서드의 반환값에 사용
		return value;
		
	}
	
	public void setValue(T value) { // 메서드의 매개변수 사용
		this.value = value;
	}
	
	
	
	
}


class NonGeneric{
	private Object value;

	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}