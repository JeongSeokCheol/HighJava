package kr.or.ddit.basic;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class pdfBoxTest03{

   public static void main(String args[]) throws Exception {
	   
      //File불러오기
      File file = new File("d:/d_other/test.pdf");
      PDDocument doc = PDDocument.load(file);
        
      //복사할 페이지 설정
      PDPage page = doc.getPage(0);
       
      //이미지 삽입을 위한 PDImageXObject 인스턴스화
      PDImageXObject pdImage = PDImageXObject.createFromFile("d:/d_other/국화.jpg",doc);
       
      //삽입할 문서와 페이지 지정
      PDPageContentStream contents = new PDPageContentStream(doc, page);

      //이미지의 크기 지정 (가로 ,세로)
      contents.drawImage(pdImage, 50, 50);

      System.out.println("Image inserted");
      
      //인스턴스 클래스 닫기
      contents.close();		
		
      //저장할 경로 설정
      doc.save("d:/d_other/test.pdf");
            
      //문서 닫기
      doc.close();
     
   }
}