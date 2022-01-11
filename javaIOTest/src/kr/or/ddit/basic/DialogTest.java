package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;
import java.nio.channels.SelectableChannel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		//SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();
		
		//선택할 파일의 확장자 설정
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word File", "docx", "doc");
		FileNameExtensionFilter img= new FileNameExtensionFilter("Image File", new String[] {"jpg", "png", "gif"});
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text File", "txt");
		
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(txt);
		
		chooser.setFileFilter(txt); //확장자 목록 중에 기본적으로 선택될 확장자 지정
		
		// Dialog창이 나타낼 기본 경로 설정
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		//int result = chooser.showOpenDialog(new Panel()); // 열기용 창
		int result = chooser.showSaveDialog(new Panel());
		
		// '저장' 또는 '열기' 버튼을 눌렀을 경우
		if(result == JFileChooser.APPROVE_OPTION) {
			// 선택한 파일 정보 가져오기
			File slectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + slectedFile.getAbsolutePath());
		}
		
		
	}

}
