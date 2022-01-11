package kr.or.ddit.basic;

public class StringTest {
	
	public static void main(String[] args) {
		String str1 = "안녕하세요";
		String str2 = new String("안녕하세요");
		String str3 = new String("안녕하세요");
		String str4 = "안녕하세요";
		
		String str5 = "안녕" + "하세요";
		String str6 = "안녕" + new String("하세요");
		
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		
		System.out.println(str2 == str3);
		
		System.out.println(str1 == str4);
		System.out.println(str1 == str4);
		System.out.println(str5 == str4);
		
		
		
	}
}
