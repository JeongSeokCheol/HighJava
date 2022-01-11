package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

/*
   Lprod테이블에 새로운 데이터를 추가해보자.
   
   Lprod_Gu의 Lprod_Nm은 사용자로부터 직접 입력 받아서 처리하고,
   Lprod_Id는 현재의 Lprod_Id값 중에서 제일 큰 값보다 1크게 저장한다.
   
    입력받은 Lprod_Gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
   
   
select nvl(max(lprod_id),0) from lprod;

select count(*) from lprod where lprod_gu='P202';


-Statement 와 PreparedStatemnet의 차이점




 */


public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JSC96","java");
			
			conn = DBUtil.getConnection();
	
			while(true) {
				
				String sql1 = "select nvl(max(lprod_id),0) from lprod";
				stmt1 = conn.createStatement();
				rs1 = stmt1.executeQuery(sql1);
				
				int lprodId = 0;
				
				if(rs1.next()) {
					lprodId = rs1.getInt(1);
					
					//컬럼의 alias가 없으면 식이 '컬럼명' 역활을 하낟.
					//lprodId = rs1.getInt("nvl(max(lprod_id),0)");
					
				
					//lprodId = rs1.getInt(별칭);	 //컬럼의 alias를 지정해서 선택
				}
				lprodId++;
				System.out.println(lprodId);
				
				
				
				System.out.println("상품 분류 코드 입력 : ");
				String lprodGu = scan.next();
				String sql2 = "select count(*) from lprod where lprod_gu= '" + lprodGu +"'";
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sql2);
				rs2.next();
				
				
				
				if(rs2.getInt(1) > 0) {
					System.out.println("상품 분류 코드 값 " + lprodGu + " 이 중복 됩니다. 다시 입력하세요");
					System.out.println();

				}else {
					System.out.println("상품 분류명 : ");
					String lprodNm = scan.next();
					
					String sql3 = "insert into lprod values(?,?,?)";
					pstmt = conn.prepareStatement(sql3);
					
					pstmt.setInt(1, lprodId);
					pstmt.setString(2, lprodGu);
					pstmt.setString(3, lprodNm);
					int cnt = pstmt.executeUpdate();
					System.out.println("반환값 : " + cnt);
					System.out.println("내용을 저장하였습니다.");
					
					return;
				}
				
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}finally {
			if(stmt1!=null)try {stmt1.close();}catch(SQLException e){}
			if(stmt2!=null)try {stmt2.close();}catch(SQLException e){}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e){}
			if(conn!=null)try {conn.close();}catch(SQLException e){}
			if(rs1!=null)try {rs1.close();}catch(SQLException e){}
			if(rs2!=null)try {rs2.close();}catch(SQLException e){}
		}
		

	}

}
