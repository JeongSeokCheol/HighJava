package kr.or.ddit.basic;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
public class pdfBoxTest04{
  
   public static void main(String args[]) throws Exception {
      
      File file = new File("d:/d_other/test.pdf");
      PDDocument document = PDDocument.load(file);
   
      //액세스 권한 주기
      AccessPermission ap = new AccessPermission();         

      //StandardProtectionPolicy 객체 생성 (암호, 사용자암호 , 액세스권한)
      StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);

      //암호의 키 길이 설정
      spp.setEncryptionKeyLength(128);

      //액세스 권한 설정
      spp.setPermissions(ap);

      //문서보호를 위한 잠금
      document.protect(spp);

      
      System.out.println("Document encrypted");

      
      document.save("d:/d_other/test.pdf");
    
      document.close();

   }
}