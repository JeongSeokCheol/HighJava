package iTextTest;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ITextTest02 {

	public static void main(String[] args) throws FileNotFoundException {
		  //PDF 경로 설정       
	      String dest = "d:/d_other/sample.pdf";       
	      PdfWriter writer = new PdfWriter(dest);           
	      
	      //PDF파일 생성      
	      PdfDocument pdf = new PdfDocument(writer);              
	      
	      //새로운 PDF페이지 만들기
	      Document document = new Document(pdf);              
	      String para1 = "추가할 글씨1";
	      
	      String para2 = "추가할 글씨2";
	    		  
	      //글씨 추가       
	      Paragraph paragraph1 = new Paragraph(para1);             
	      Paragraph paragraph2 = new Paragraph(para2);              
	      
	      //문서화에 글씨 추가     
	      document.add(paragraph1);       
	      document.add(paragraph2);           
	      
	           
	      document.close();             
	      System.out.println("Paragraph added");    

	}

}
