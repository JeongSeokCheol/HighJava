package kr.or.ddit.basic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		//URLConnectoin ==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		//                  (보통 URL 객체를 통새거 구할 수 있다.)
		
		//특정 서버의 정보의 파일 내용을 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		//URLConnection객체 구하기
		URLConnection urlCon = url.openConnection();
		
		//Header정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		//Header정보 출력
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		System.out.println("---------------------------------------------------------");
		
		//해당 문서의 내용을 가져오기 ( index.html문서 내용 가져오기 )
		
		//방법1 ==> URLConnection객체를 이용하는 방법
		//파일을 읽어올기 위한 스트림 객체 구하기
		/*InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		//자료를 읽어와 출력하기
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			System.out.println(str);
		}
		br.close(); //스트림 닫기
*/
		
		//방법2 ==> URL 객체의 openStream()이용하기
		InputStream is2 = url.openStream();
//		InputStreamReader isr2 = new InputStreamReader(is2,"utf-8");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2,"utf-8"));
		
		while(true) {
			String str = br2.readLine();
			if(str == null) break;
			System.out.println(str);
		}
		br2.close(); //스트림 닫기
		
	}

}
