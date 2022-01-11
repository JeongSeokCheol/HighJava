package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
public class pdfBoxTest02{

   public static void main(String args[]) throws IOException {

      //pdf파일 불러오기
      File file = new File("d:/d_other/test.pdf");
      PDDocument document = PDDocument.load(file);

      //Stripper()메서드로 PDF파일 읽어올 수 있다.
      PDFTextStripper pdfStripper = new PDFTextStripper();

      //읽어온 데이터를 변수에 저장
      String text = pdfStripper.getText(document);
      System.out.println(text);

      //문서 닫기
      document.close();

   }
}