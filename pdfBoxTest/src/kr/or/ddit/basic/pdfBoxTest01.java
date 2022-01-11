package kr.or.ddit.basic;


import java.io.File; 
import java.io.IOException;
  
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage; 
import org.apache.pdfbox.pdmodel.PDPageContentStream; 
import org.apache.pdfbox.pdmodel.font.PDType1Font;
  
public class pdfBoxTest01{
   public static void main (String args[]) throws IOException {

      //PDF파일 불러오기
      File file = new File("d:/d_other/test.pdf"); //읽어올 pdf의 경로
      PDDocument document = PDDocument.load(file);
       
      //PDF파일 문서의 페이지 검색
      PDPage page = document.getPage(0);
      
      //문서에 문자를 추가하기 위한 객체 생성
      PDPageContentStream contentStream = new PDPageContentStream(document, page);
      
      //문자추가 시작
      contentStream.beginText(); 
       
      //폰트와 폰트의 크기를 설정 
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

      //줄과 문자수를 지정 
      contentStream.newLineAtOffset(25, 500);
      
      //들어갈 문자 저장
      String text = "This is the sample document and we are adding content to it.";

      //문자열 추가 
      contentStream.showText(text);      

      //문자추가 종료
      contentStream.endText();

      System.out.println("Content added");

      //스트림 닫기
      contentStream.close();

      //문서 저장
      document.save(new File("d:/d_other/test.pdf")); //새로저장할 경로

      //문서 닫기
      document.close();
   }
}