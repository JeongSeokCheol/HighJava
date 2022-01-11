package kr.or.ddit.basic;

import java.io.File;

public class FileTest03 {

	public static void main(String[] args) {

		File file1 = new File("d:/d_other/test.txt");
		System.out.println(file1.getName() + "의 크기 : " + file1.length() + "bytes");
		System.out.println("path : " + file1.getPath());
		System.out.println("absolutPath : " + file1.getAbsolutePath());
		
//		File file2 = new File(""); //현재 위치를 나타내기
		File file2 = new File("."); //현재 위치를 나타내기
		System.out.println("path : " + file2.getPath());
		System.out.println("absolutPath : " + file2.getAbsolutePath());
		
		
		
	}

}
