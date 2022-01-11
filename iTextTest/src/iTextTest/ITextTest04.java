package iTextTest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class ITextTest04 {

	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		  //PDF경로 설정      
	      String dest = "d:/d_other/sample.pdf";       
	      PdfWriter writer = new PdfWriter(dest);        
	      
	      //PDF문서 생성       
	      PdfDocument pdf = new PdfDocument(writer);              
	      
	      //새로운 PDF페이지 만들기      
	      Document document = new Document(pdf);              
	      
	      //이미지 불러와서 데이터 형식으로 변환      
	      String imFile = "d:/d_other/국화.jpg";       
	      ImageData data = ImageDataFactory.create(imFile);              
	      
	      //데이터화 된 이미지를 이미지로 변환
	      Image image = new Image(data);                        
	      
	      //변환된 이미지를 PDF문서에 추가
	      document.add(image);              
	      
	            
	      document.close();              
	      
	      System.out.println("Image added");   

	}

}
