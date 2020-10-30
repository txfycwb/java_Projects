package jdbc;
import java.sql.*;
import static jdbc.TestJDBC.*;
/**
 * 数据库事务具有ACID特性：
	Atomicity：原子性
	Consistency：一致性
	Isolation：隔离性
	Durability：持久性
 * @author guo
 * 如果要设定事务的隔离级别，可以使用如下代码：
	设定隔离级别为READ COMMITTED:
	conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
 */
public class TestTransaction {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			conn = openConnection();
		    // 关闭自动提交:
		    conn.setAutoCommit(false);
		    // 执行多条SQL语句:
		    insert(); update(); 
		    // 提交事务:
		    conn.commit();
		} catch (SQLException e) {
		    // 回滚事务:
		    conn.rollback();
		} finally {
		    conn.setAutoCommit(true);
		    conn.close();
		}
	}

	private static Connection openConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection(JDBC_URL,JDBC_USER,PASSWORD);
	}
	
}
