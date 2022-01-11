package iTextTest;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;

public class ITextTest01 {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException {
		// 저장공간 설정       
	      String dest = "d:/d_other/sample.pdf";       
	      PdfWriter writer = new PdfWriter(dest); 
	   
	      // Pdf문서 생성      
	      PdfDocument pdfDoc = new PdfDocument(writer);         
	   
	      // 빈페이지 추가
	      pdfDoc.addNewPage();               
	        
	      Document document = new Document(pdfDoc);               
	      
	      document.close();              
	      System.out.println("PDF Created");
	}

}
