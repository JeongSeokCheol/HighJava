package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

public class JdbcTest06 {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new JdbcTest06().memberStart();
	}
	
	// 시작 메서드
	public void memberStart() {
		System.out.println();
		System.out.println("=========================");
		System.out.println("    회 원 관 리 프 로 그 램");
		System.out.println("=========================");
		System.out.println();
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :	// 추가 
					insertMember(); break;  
				case 2 :	// 삭제 
					deleteMember(); break;
				case 3 :	// 수정 
					updateMember(); break;
				case 4 :	// 전체 자료 출력
					displayMember();
					break;
				case 5:
					updateMember2();
					break;
				case 0 :
					System.out.println("프로그램을 종료합니다.");
					return;
				default : System.out.println("작업 번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}
	}
	
	// MVC패턴, 수정작업을 원하는 항목1개만 수정할 수 있도록 한다.
	
	//회원정보를 수전하는 메서드 ==>원하는 항목만 수정

	
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0) { // 해당 회원이 없으면...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정작업을 종료합니다.");
			return;
		}
		
		String updateField = null; // 수정할 항목(컬럼명)이 저장될 변수
		String updateData = null; // 수정할 항목에 저장될 값이 저장될 변수
		String updateTitle = null; //수정할 항목의 제목이 저장될 변수
		
		int num; // 수정할 할목의 선택 번호가 저장될 변수
		
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println(" 1.비밀번호  2.사원이름  3.전화번호  4.회원주소");
			System.out.println("----------------------------------------------");
			System.out.println("수정할 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num) {
				case 1: updateField = "mem_pass"; updateTitle="비밀번호"; break;
				case 2: updateField = "mem_name"; updateTitle="회원이름"; break;
				case 3: updateField = "mem_tel"; updateTitle="전화번호"; break;
				case 4: updateField = "mem_addr"; updateTitle="회원주소"; break;
				default:
					System.out.println("수정할 항목을 잘못 선택했습니다. 다시 선택 하세요.");
					
			
			}
			
			
		}while(num < 1|| num > 4 );
		
		System.out.println();
		scan.nextLine();//버퍼비우기
		
		System.out.println("새로운" + updateTitle + " >> ");
		updateData = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " + updateField + " = ? where mem_id = ?";  
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("수정작업 성공!!!");
			}else {
				System.out.println("수정작업 실패~~~");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			disConnect();
		}
		
	}

	// 회원 정보를 수정하는 메서드 ==> 전체항목 수정
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0) { // 해당 회원이 없으면...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정작업을 종료합니다.");
			return;
		}
		
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newMemPass = scan.next();
		
		System.out.print("새로운 회원이름 >> ");
		String newMemName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String newMemTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 회원주소 >> ");
		String newMemAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set "
					+ " mem_pass=?, mem_name=?, mem_tel=?, mem_addr=?"
					+ " where mem_id=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMemPass);
			pstmt.setString(2, newMemName);
			pstmt.setString(3, newMemTel);
			pstmt.setString(4, newMemAddr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 정보 수정 완료!!!");
			}else {
				System.out.println(memId + "회원 정보 수정 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("회원ID가 " + memId + "인 회원 삭제 성공!!!");
			}else {
				System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	
	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		int count = 0;
		String memId = null;  // 회원ID가 저장될 변수 선언
		
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();
			count = getMemberCount(memId);
			if(count>0) {
				System.out.println(memId + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}
			
		}while(count>0);
		
		System.out.print("비밀번호 >> ");
		String memPass = scan.next();
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();   // 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember"
					+ "(mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ " values( ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("새로운 회원 등록 완료!!!");
			}else {
				System.out.println("회원 등록 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	// 매개변수로 지정한 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return count;
	}
	
	
	
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() {
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println(" ID    비밀번호      이 름       전화번호       주 소");
		System.out.println("-------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println(memId + "   " + memPass + "    " + memName
						+ "    " + memTel + "    " + memAddr);
			}
			System.out.println("-------------------------------------------");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	// 사용했던 자원을 반납하는 메서드
	private void disConnect() {
		if(rs!=null) try {rs.close();} catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
		if(conn!=null) try {conn.close();} catch(SQLException e) {}
	}
	
	
	// 메뉴을 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("=== 작업 선택 ===");
	    System.out.println("   1. 자료 추가 ");
	    System.out.println("   2. 자료 삭제");
	    System.out.println("   3. 자료 수정");
	    System.out.println("   4. 전체 자료 출력");
	    System.out.println("   5. 항목 수정");
	    System.out.println("   0. 작업 끝.");
	    System.out.println("-------------------");
	    System.out.print("작업 번호 >> ");
	    int num = scan.nextInt();
	    
	    return num;
	}
	

}











