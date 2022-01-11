package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileCopyTest {

/*
   문제) 
     'd:/d_other'폴더에 있는 '국화.jpg'파일을
     'd:/d_other/연습용' 폴더에 '복사본_국화.jpg'로 복사하는 프로그램을 작성하시오.
 */
	public static void main(String[] args) {
		File f = new File("d:/d_other","국화.jpg");
		
		if(!f.exists()) {
			System.out.println(f.getName() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중단합니다.");
		}
		
		try {
			
			//파일을 입출력할 스트림객체 생성
			FileInputStream fi = new FileInputStream(f);
			
			FileOutputStream nf = new FileOutputStream("d:/d_other/연습용/복사본_국화.jpg");
			
			//버퍼용 입출력 스트림 객체 생성
			BufferedInputStream bis = new BufferedInputStream(fi);
			BufferedOutputStream bos = new BufferedOutputStream(nf);
			
			System.out.println("복사 작업 시작");
//			int c;
//			while((c = fi.read()) != -1) {
//				nf.write(c);
//			}
			
			int data;
			while((data = bis.read() ) != -1) {
				bos.write(data);
			}
			
			bos.flush();
			
//			byte[] arr = new byte[1024];
//			int len = 0;
//			while((len = fi.read(arr)) > 0) {
//				nf.write(arr, 0, len);
//			}
			
			fi.close();
			nf.close();
			
			System.out.println("복사 작업 끝");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
