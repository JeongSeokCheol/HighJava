package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
문제 ) 이름, 주소, 전화번호를 멤버로 갖는 Phone1클래스를 만들고
	   Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
	   (Map의 구조 => key값 : 저장할 사람의 '이름'을 사용 value : Phone1 클래스의 인스턴스)
	   
	   HashMap<String, Phone1>
	   
	    아래 메뉴의 기능을 구현하시오

- 추가조건)
1) '6. 전화번호 저장'메뉴를 추가하고 구현한다.
    (저장 파일명은 'phoneData.dat'로 한다.)
2) 프로그램이 시작될 때  저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되면 저장 후 종료되도록 한다.



실행예시) 다음 메뉴를 선택하시오.

1.전화번호 등록
2.전화번호 수정
3.전화번호 삭제
4.전화번호 검색
5.전화번호 전체출력
0.프로그램 종료
---------------------
번호입력 >> 1

새롭게 등록할 전화번호 정보를 입력하세요.
이름 >> 홍길동
전화번호 >> 010-1111-1111
주소>> 대전시 중구 대흥동

'홍길동' 전화번호 등록 완료!!!

----------------------------------

다음 메뉴를 선택하시오.

1.전화번호 등록
2.전화번호 수정
3.전화번호 삭제
4.전화번호 검색
5.전화번호 전체출력
0.프로그램 종료
---------------------
번호입력 >> 5
-----------------------------
번호	이름	전화번호	주소
-----------------------------
1	홍길동	010-1111-1111 대전시 중구 대흥동
~~~
-------------------------------------
출력완료

다음 메뉴를 선택하시오.

1.전화번호 등록
2.전화번호 수정
3.전화번호 삭제
4.전화번호 검색
5.전화번호 전체출력
0.프로그램 종료
---------------------
번호입력 >> 0

프로그램을 종료합니다.

*/
public class PhoneBookTest {
	
	private HashMap<String, phone> phoneBookMap;
	private Scanner scan;
	private String FileName = "d:/d_other/phoneData.dat";
	private boolean saveCheck = false;
	
	//생성자
	
	public PhoneBookTest() {
//		phoneBookMap = new HashMap<>();
		scan = new Scanner(System.in);
		phoneBookMap = load();
		if(phoneBookMap == null) {
			phoneBookMap = new HashMap<>();
		}
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}
	
	//프로그램이 시작되는 메서드
	public void phoneBookStart() {
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println("          전 화 관 리 프 로 그 램 ");
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				search();
				break;
			case 5:
				allSelct();
				break;
			case 6:
				save();
				break;
			case 0:
				if(saveCheck = true) {
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력 했습니다.");
				System.out.println("다시 선택해 주세요.");
				
			}
		}
	}
	
	
	//데이터를 파일로 저장하는 메서드
	private void save() {
		ObjectOutputStream oos = null;
		
		//객체를 파일에 저장하기
		try {
			//출력용 스트림 객체 생성
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FileName)));
			
			//Map객체를 저장한다
			oos.writeObject(phoneBookMap);

			System.out.println("저장이 완료되었습니다.");
			
		} catch (IOException e) {
			System.out.println("저장 실패 - " + e.getMessage());
		}finally {
			if(oos!=null) try { oos.close(); } catch(IOException e) {}
		}
		saveCheck = false;
	
	}
	
	
	//저장된 데이터를 읽어와 Map객체형으로 반환하는 메서드
	//저장된 파일이 없거나 읽기 오류일 때는 null을 반환한다.
	private HashMap<String, phone> load(){
		HashMap<String, phone> pMap = null; //읽어온 데이터가 저장될 변수
		
		File file = new File(FileName);
		
		if(!file.exists()) { //저장된 파일이 없으면 
			return null;
		}
		
		//저장된 파일이 있으면
		ObjectInputStream ois = null;
		try {
			//입력용 스트림 객체 생성
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FileName)));
			
			pMap = (HashMap<String, phone>)ois.readObject();
			
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		
		
		return pMap;
		
	}
	
	
	
	private void delete() {
		scan.nextLine();
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입역하세요");
		System.out.println("회원 이름 >> ");
		String name = scan.nextLine();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name+"씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		
		phoneBookMap.remove(name);
		System.out.println(name + "씨의 정보를 삭제합니다.");
		saveCheck = true;
		
	}
	
	private void search() {
		System.out.println();
		
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.println("회원 이름 >>");
		String name = scan.nextLine();
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		phone p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("이     름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주     소 : " + p.getAddr());
	
	}
	
	private void update() {
		scan.nextLine();
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요");
		
		System.out.println("회원 이름 >> ");
		String name = scan.nextLine();
		
		//해당 사람이 Map저장되어 있지 않으면 수정작업을 수행할 수 없다.
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine();
	
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();
		
		phoneBookMap.put(name, new phone(name, newTel, newAddr));
		saveCheck = true;
	}
	

	//메뉴를 출력하고 선택한 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println("다음 메뉴를 선택하시오.");
		System.out.println("-------------------------------");
		System.out.println(" 1.전화번호 등록");
		System.out.println(" 2.전화번호 수정");
		System.out.println(" 3.전화번호 삭제");
		System.out.println(" 4.전화번호 검색");
		System.out.println(" 5.전화번호 전체 출력");
		System.out.println(" 6.전화번호 저장");
		System.out.println(" 0.프로그램 종료");
		System.out.println("-------------------------------");
		System.out.print("번호입력 >> ");
		int num = Integer.parseInt(scan.nextLine());
		
		return num;
	}
		
	private void allSelct() {
		System.out.println();
		
		Set<String> nameSet = phoneBookMap.keySet();
		
		System.out.println("-------------------------------");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("-------------------------------");
		
		if(nameSet.size()==0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다...");
		}else {

		int cnt = 0;
		//Map에서 모든 key값들을 하나씩 가져온다.
		for(String name : nameSet) {
			cnt++;
			phone p = phoneBookMap.get(name);
			
			System.out.println(cnt + "\t" + p.getName() + "\t"+ p.getTel() + "\t" + p.getAddr());
			
			}
			System.out.println("-------------------------------");
		}
		
	}
	
	//자료를 등록하는 메서드 (이미 등록된 사람은 등록되지 않는다.)
	private void insert() {
		//Scanner객체의 next(), nextInt(), nextDouble()....등
		//nextLine()이 아닌 입력용 메서드의 특징
		// ==> 사이띄기, Tab키, Enter키를 구분문자로 분리해서 분리된 자료만 읽어간다.
		//     그래서 입력버퍼에는 구분문자들이 남는다.
		//
		// Scanner객체의 nextLine() ==> 한 줄 단위로 입력받는다.
		//                            즉, 자료를 입력하고 Enter키 까지 읽어간다.
		
		
//		scan.next();  // 입력 버퍼비우기
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >> ");
		String name = scan.nextLine();
		
		if(phoneBookMap.containsKey(name)) {
			System.out.println("동일한 사람이 이미 등록 되어 있습니다.");
		}
		
		System.out.println("전화번호 >> ");
		String tel = scan.nextLine();
		System.out.println("주소 >> ");
		String addr = scan.nextLine();
		
//		phone p = new phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name,new phone(name, tel, addr));
		
		System.out.println(name + "씨 전화번호 등록 완료!!");
		saveCheck = true;
	}
	
	

}

class phone implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4936048651018014218L;
	
	private String name;
	private String tel;
	private String addr;

	public phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

}