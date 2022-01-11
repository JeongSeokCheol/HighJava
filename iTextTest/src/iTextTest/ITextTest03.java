package iTextTest;


import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class ITextTest03 {

	public static void main(String[] args) throws Exception {
	      //PDF 경로 설정
	      String dest = "d:/d_other/sample.pdf";       
	      PdfWriter writer = new PdfWriter(dest);              
	   
	      //PDF문서 생성      
	      PdfDocument pdf = new PdfDocument(writer);              
	   
	      //새로운 PDF페이지 만들기      
	      Document document = new Document(pdf);              
	   
	      //머리글 추가      
	      Paragraph paragraph = new Paragraph("Tutorials Point provides the following tutorials");
	      
	      //리스트형식 만들기
	      List list = new List();  
	      
	      //리스트에 저장될 글 추가      
	      list.add("Java");       
	      list.add("JavaFX");      
	      list.add("Apache Tika");                 
	      
	      //머리글을 PDF문서에 추가       
	      document.add(paragraph);                    
	     
	      //리스트를 PDF문서에 추가      
	      document.add(list);
	      
	      //새로운 테이블 생성      
	      float [] pointColumnWidths = {150F, 150F};   
	      Table table = new Table(pointColumnWidths);    
	      
	      // 테이블에 요소 추가    
	      table.addCell("java");   
	      table.addCell("Oracle");       
	      table.addCell("HTML");   
	      
	      //PDF문서에 테이블 삽입       
	      document.add(table);          
	      
	      //문서 닫기      
	      document.close();              
	      System.out.println("List added");    
	   
	}

}
