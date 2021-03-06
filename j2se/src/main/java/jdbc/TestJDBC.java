package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//!!!!!小心注入攻击
public class TestJDBC {
	static String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false";
	static String JDBC_USER = "learnjdbc";
	static String PASSWORD = "learnpassword";
	
	public static void main(String[] args) throws SQLException {
		// 建立与数据库的Connection连接
        // 这里需要提供：
        // 数据库所处于的ip:127.0.0.1 (本机)
        // 数据库的端口号： 3306 （mysql专用端口号）
        // 数据库名称 learnjdbc
        // 编码方式 UTF-8
        // 账号 learnjdbc
        // 密码 learnpassword
		insert();
		//update();
		query();
		
		
			
	}
	
	static void query() throws SQLException{
		try (Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,PASSWORD)){
            //statement有注入攻击的危险，使用Java对数据库进行操作时，
			//必须使用PreparedStatement，严禁任何通过参数拼字符串的代码！
//			try(Statement stmt = conn.createStatement()){
//            	try(ResultSet rs = stmt.executeQuery("SELECT id,grade,name,gender FROM students WHERE gender=1")){
//            		while(rs.next()) {
//            			//注意按照查找顺序来,索引从一开始
//            			long id = rs.getLong(1);
//            			long grade = rs.getLong(2);
//            			String name = rs.getString(3);
//            			int gender = rs.getInt(4);
//            			System.out.println(id+" "+grade+""+name+" "+gender);
//            		}
//            	}
//            }
			System.out.println();
			try(PreparedStatement ps = conn.prepareStatement("SELECT id,grade,name,gender FROM students WHERE gender=? And grade<?")){
				ps.setObject(1, 1);
				ps.setObject(2,3);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
            			//使用string的列名要易读，且不容易出错。
						long id = rs.getLong("id");
		                long grade = rs.getLong("grade");
		                String name = rs.getString("name");
		                String gender = rs.getString("gender");
            			System.out.println(id+" "+grade+""+name+" "+gender);
            		}
				}
			}
		}
	}

	static void update() throws SQLException{
		try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,PASSWORD);
			PreparedStatement ps = conn.prepareStatement("UPDATE students SET name = ? WHERE id = ?")){
			ps.setObject(1,"Alice");
			ps.setObject(2,14);
			System.out.println(ps.executeUpdate());
		}
		
	}
	/**
	 * SQL数据库对SQL语句相同，但只有参数不同的若干语句可以作为batch执行，即批量执行，
	 * 这种操作有特别优化，速度远远快于循环执行每个SQL。
	 * @throws SQLException
	 */
	static void insert() throws SQLException{
		//要获取自增主键，不能先插入，再查询。因为两条SQL执行期间可能有别的程序也插入了同一个表。
		//获取自增主键的正确写法是在创建PreparedStatement的时候，指定一个RETURN_GENERATED_KEYS标志位，表示JDBC驱动必须返回插入的自增主键。
		try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
			"INSERT INTO students(grade,name,gender,score) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){
			ps.setObject(1,2);
			ps.setObject(2,"Bob");
			ps.setObject(3,1);
			ps.setObject(4,90);
			System.out.println(ps.executeUpdate());
			try(ResultSet rs = ps.getGeneratedKeys()){
				while(rs.next()) {
					System.out.println("增加主键ID为："+rs.getInt(1));
				}
			}
			Student[] students = new Student[] {new Student("a",1,1,99),new Student("b",1,1,98),};
			for(Student s:students) {
				ps.setInt(1,s.grade);
				ps.setString(2,s.name);
				ps.setInt(3,s.gender);
				ps.setInt(4,s.score);
				ps.addBatch();
			}
			int[] ns = ps.executeBatch();
		}
	}
	
	static class Student{
		String name;
		int grade;
		int gender;
		int score;
		public Student(String name, int grade, int gender, int score) {
			super();
			this.name = name;
			this.grade = grade;
			this.gender = gender;
			this.score = score;
		}
	}
}
