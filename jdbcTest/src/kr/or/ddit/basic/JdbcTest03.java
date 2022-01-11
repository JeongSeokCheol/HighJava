package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
   	문제) Lprod_id값을 2개 입력 받아서 두값 중 작은 값부터 큰 값 사이의 자료를 출려하시오. 
 */


public class JdbcTest03 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Lprod_id 첫번째 값 : ");
		int num1 = scan.nextInt();
		System.out.print("Lprod_id 두번째 값 : ");
		int num2 = scan.nextInt();
		
		/*int temp = num1;
		
		if(num1 > num2) {
			num1 = num2;
			num2 = temp;
		}*/
	
		int max = Math.max(num1, num2);
		int min = Math.min(num1, num2);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JSC96",
					"java"
					);
			
			String sql = "select * from lprod "
					+ "where lprod_id >= " + min +"and lprod_id <=" + max;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("Lprod_id  : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu  : " + rs.getString("lprod_gu"));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------------");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		
	}
}
