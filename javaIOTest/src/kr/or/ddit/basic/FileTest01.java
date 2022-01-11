package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		//file객체 만들기 연습
		
		//1. new file(String파일 또는 경로);
		//     ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의
		//         구분문자는 '/'를 사용하서나 '\'를 사용할 수 있다.
		
		File file1 = new File("d:/D_Other/test.txt"); // 구분문자를 '/'로 사용
		File file2 = new File("d:\\D_Other\\test.txt"); //구분문자를 '\'로사용
		
		System.out.println("파일명 : " + file1.getName());
		System.out.println("path : " + file2.getPath());
		System.out.println("디렉토리 여부 : " + file1.isDirectory());
		System.out.println("파일 여부 : " + file1.isFile());
		System.out.println("-----------------------------------");
		
		File file3 = new File("d:/d_other");
		System.out.println("파일명 : " + file3.getName());
		System.out.println("path : " + file3.getPath());
		System.out.println("디렉토리 여부 : " + file3.isDirectory());
		System.out.println("파일 여부 : " + file3.isFile());
		System.out.println("-----------------------------------");
	
		File file4 = new File("d:/testtesttest");
		System.out.println("파일명 : " + file4.getName());
		System.out.println("path : " + file4.getPath());
		System.out.println("디렉토리 여부 : " + file4.isDirectory());
		System.out.println("파일 여부 : " + file4.isFile());
		System.out.println("-----------------------------------");
		
		// 2. new File(File parent, String child)
		//     ==> 'parent' 디렉토리 안에 있는 'child'를 갖는다.
		File file5 = new File(file3, "test.txt");
		System.out.println("파일명 : " + file5.getName());
		System.out.println("path : " + file5.getPath());
		System.out.println("디렉토리 여부 : " + file5.isDirectory());
		System.out.println("파일 여부 : " + file5.isFile());
		System.out.println("-----------------------------------");
		
		// 3. new File(String parent, String child)
		File file6 = new File("d:/d_other", "test.txt");
		System.out.println("파일명 : " + file6.getName());
		System.out.println("path : " + file6.getPath());
		System.out.println("디렉토리 여부 : " + file6.isDirectory());
		System.out.println("파일 여부 : " + file6.isFile());
		System.out.println("-----------------------------------");
		
		
		
		
	}
}
