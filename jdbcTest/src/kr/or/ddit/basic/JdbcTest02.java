package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
         문제) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 Lprod_id가 큰 자료를 출력하시오. 
 
 */

public class JdbcTest02 {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
//		System.out.println("Lrod_id값 입력 : ");
//		int num = scan.nextInt();
		
		System.out.println("Lrod_NM값 입력 : ");
		String nm = scan.nextLine();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JSC96",
					"java"
					);
			
//			String sql = "select * from lprod where lprod_id > " + num;
			String sql = "select * from lprod where lprod_NM = '" + nm +"'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("Lprod_id  : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu  : " + rs.getString("lprod_gu"));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------------");
			}
				
			
			
		}catch (SQLException e) {
			// TODO: handle exception
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		

	}

}
