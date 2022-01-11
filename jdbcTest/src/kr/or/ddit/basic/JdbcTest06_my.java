package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

/*
 
 MYMEMBER테이블을 이용하여 회원을 관리하는 프로그램을 작성하시오.

아래 메뉴의 기능을 모두 구현하시오. (CRUD기능 구현하기)

메뉴예시)
     === 작업 선택 ===
       1. 자료 추가 
       2. 자료 삭제
       3. 자료 수정
       4. 전체 자료 출력
       0. 작업 끝.
   ---------------------------

처리조건)
  1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
  2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
  3) 자료 수정에서 '회원ID'는 변경되지 않는다.
 
 */
public class JdbcTest06_my {
	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06_my().start();
	}

	private void start() {
		while(true) {
		System.out.println("=== 작업 선택 ===");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 0. 작업끝");
		System.out.println("-----------------");
		System.out.print("메뉴 선택 : ");
		int input = scan.nextInt();
		
		switch(input) {
		case 1:
			insert();
			break;
		case 2:
			delete();
			break;
		case 3:
			correction();
			break;
		case 4:
			list();
			break;
		case 0:
			System.out.println("작업을 종료 합니다.");
			System.exit(0);
		default:
			System.out.println("잘못 입력 하셨습니다.");
			break;
				
		}
		
		}
		
	}

	private void list() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("ID\t비밀번호\t이름\t전화번호\t주소");
		System.out.println("---------------------------------------------------------");
		
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
				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
				System.out.println("---------------------------------------------------------");
				
			}
			return;
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
	}

	private void correction() {
		try {
			conn = DBUtil.getConnection();
			String id = "";
			
			while(true) {
				System.out.println("수정할 ID : ");
				id = scan.next();
				
				String sql = "select count(mem_id) from mymember where mem_id = " + id;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				
				int count = rs.getInt(1);
				
				if(count == 0) {
					System.out.println(id + "회원은 없습니다. 다시 확인 바랍니다.");
					System.out.println();
				}else {
					
					System.out.println("=== 변경할 정보 ===");
					System.out.println(" 1. 비밀번호");
					System.out.println(" 2. 이름");
					System.out.println(" 3. 전화번호");
					System.out.println(" 4. 주소");
					System.out.println("-----------------");
					System.out.print("메뉴 입력 : ");
					int input = scan.nextInt();
					
					switch(input) {
					case 1:
						System.out.println("변경할 비밀번호 : ");
						String pass = scan.next();
						
						String sql1 = "update mymember set mem_pass = ?"
								+ "    where mem_id = ?";
						pstmt = conn.prepareStatement(sql1);
						pstmt.setString(1, pass);
						pstmt.setString(2, id);
						pstmt.executeUpdate();
						System.out.println("수정되었습니다.");
						break;
					case 2:
						System.out.println("변경할 회원의 이름 : ");
						String name = scan.next();
						
						String sql2 = "update mymember set mem_pass = ?"
								+ "    where mem_id = ?";
						pstmt = conn.prepareStatement(sql2);
						pstmt.setString(1, name);
						pstmt.setString(2, id);
						pstmt.executeUpdate();
						System.out.println("수정되었습니다.");
						break;
					case 3:
						System.out.println("변경할 전화번호 : ");
						String tel = scan.next();
						
						String sql3 = "update mymember set mem_pass = ?"
								+ "    where mem_id = ?";
						pstmt = conn.prepareStatement(sql3);
						pstmt.setString(1, tel);
						pstmt.setString(2, id);
						pstmt.executeUpdate();
						System.out.println("수정되었습니다.");
						break;
					case 4:
						System.out.println("변경할 주소 : ");
						String addr = scan.next();
						
						String sql4 = "update mymember set mem_pass = ?"
								+ "    where mem_id = ?";
						pstmt = conn.prepareStatement(sql4);
						pstmt.setString(1, addr);
						pstmt.setString(2, id);
						pstmt.executeUpdate();
						System.out.println("수정되었습니다.");
						break;
				}
					return;

			}
			
			
			
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	private void delete() {
		try {
			conn = DBUtil.getConnection();
			String id = "";
			
			while(true) {
				System.out.println("삭제할 ID : ");
				id = scan.next();
				
				String sql = "select count(mem_id) from mymember where mem_id = " + id;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				
				int count = rs.getInt(1);
				
				if(count == 0) {
					System.out.println(id + "회원은 없습니다. 다시 확인 바랍니다.");
					System.out.println();
				}else {
					String sql1 = "delete from mymember where mem_id = ?";
					
					pstmt = conn.prepareStatement(sql1);
					
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					
					System.out.println(id + "의 회원을 삭제 하였습니다.");
					
					return;
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
	}

	private void insert() {
		try {
			conn = DBUtil.getConnection();
			String id = "";
			
			while(true) {
				System.out.println("회원 ID: ");
				id = scan.next();
				
				String sql = "select count(mem_id) from mymember"
						+ " where mem_id = " + id;
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				int count = rs.getInt(1);
				
				if(count > 0) {
					System.out.println("회원의 ID " + id + "가 중복됩니다.");
					System.out.println("다시 입력해 주세요");
					System.out.println();
				}else {
					
					System.out.println("회원 비밀번호 : ");
					String password = scan.next();
					
					System.out.println("이름 : ");
					String name = scan.next();
					
					System.out.println("전화 번호 :");
					String tel = scan.next();
					
					System.out.println("주소 : ");
					String addr = scan.next();
					
					String sql1 = "insert into mymember values (?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql1);
					
					pstmt.setString(1, id);
					pstmt.setString(2, name);
					pstmt.setString(3, password);
					pstmt.setString(4, tel);
					pstmt.setString(5, addr);
					
				    pstmt.executeUpdate();
					
					System.out.println("내용을 저장하였습니다.");
					
					
					return;
				}
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		
	}
	
	
	
	
	
	
	
}
