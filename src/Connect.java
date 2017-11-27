import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	public Connect() {}
	
	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/teststone","root","0ok9ij8uh");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
