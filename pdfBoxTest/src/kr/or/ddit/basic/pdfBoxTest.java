package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public class pdfBoxTest {

	public static void main(String[] args) throws IOException {
		 //새로운 PDF문서 생성
	      PDDocument document = new PDDocument();

	      //빈 페이지의 PDF파일 생성
	      PDPage blankPage = new PDPage();
	       
	      //PDF파일에 빈페이지 추가
	      document.addPage( blankPage );
	 
	      //저장 경로 지정
	      document.save("d:/d_other/test.pdf");

	      System.out.println("Properties added successfully ");
	       
	      //Closing the document
	      document.close();
	}

}
