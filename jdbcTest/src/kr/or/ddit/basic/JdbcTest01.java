package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC(Java DataBase Connectivity) => DB자료를 Java로 처리해주는 라이브러리

public class JdbcTest01 {
/*
   - 데이터베이스 처리 순서
   
   1. 드라이버 로딩 ==> 라이브러리를 사용할 수 잇도록 메모리로 읽어 들이는 작업
       (JDBC API 버전 4이상에서는 getConnection()메서드에서 자동으로 로딩해주기 때문에
       	생략할 수 있다.(그렇지만 생략하지 않고 사용할 예정))
       Class.forName("oracle.jdbc.driver.OracleDriver");
       
   2. DB에 접속하기 ==> 접속이 완료되면 Connection객체가 반환된다.
      DriverManager.getConnection()메서드를 이용한다.
   
   3. 질의 ==> SQL문장을 DB서버로 보내서 결과를 얻어온다.
      (Statement객체 또는 PreparedStatement 객체를 이용하여 작업한다.)
      
   4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
       1) SQL문이 select문일 경우 : select문의 실행 결과가 ResultSet객체에 저장되어 반환된다.
       2) SQL문이 select문이 아닐 경우(insert, update, delete 등) : 
                SQL문의 실행 결과가 정수값이 반환된다.(정수값은 보통 실행에 성공한 레코드 수를 말한다.)
   
   5. 사용했던 자원을 반납한다. ==> close()메서드를 이용한다.
       
   
   
 */
	
	
	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결 ==> Connection객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JSC96",
					"java");
			
			//3. 질의
			//3-1) SQL문 작성
			String sql = "select * from lprod";
			
			//3-1) Statement 객체 또는 PreparedStatement 객체 생성 ==> Connection객체 이용
			stmt = conn.createStatement();
			
			//3-3) SQL문을 서버로 전송해서 실행하고, 싱행한 결과를 얻어온다.
			//     (실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.)
			rs = stmt.executeQuery(sql);
			
			//4. 결과 처리
			System.out.println("=== 쿼리문 처리 결과===");
			
			//rs.next()  ==> rs객체의 데이터를 가리키는 포인터를 다음번째 레코드로 이동시키고
			//               그곳에 데이터가 있으면 true, 그렇지 않으면 false를 반환한다. 
			while(rs.next()) {
				//현재 rs객체의 포인터가 가리키는 곳을 자료를 가져오는 법
				//형식1) rs.get자료형이름("컬럼명"); ==> 컬럼명은 대소문자 구분없이 사용가능
				//형식2) rs.get자료형이름(컬럼번호); ==> 컬럼번호는 1부터 시작
				//형식3) rs.get자료형이름("컬럼의 alias명");
				System.out.println("Lprod_id  : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu  : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------------");
				
			}
			
					
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//5. 사용했던 자원을 반납한다.
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}
