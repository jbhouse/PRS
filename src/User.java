import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class User implements DAO<User> {
	
	private int id;
	private String UserName;
	private String Password;
	private String FirstName;
	private String LastName;
	private String Phone;
	private String Email;
	private Boolean IsReviewer;
	private Boolean IsAdmin;
	private Boolean IsActive;
	private int UpdatedByUser;
	private Timestamp DateUpdated;

	User() {}

	public void createNew() {
		this.UserName = Console.getString("Enter your username: ");
		this.Password = Console.getString("Choose a password: ");
		this.FirstName = Console.getString("Enter your first name: ");
		this.LastName = Console.getString("Enter your last name: ");
		this.Phone = Console.getString("Enter your phone number: ");
		this.Email = Console.getString("Enter your email address: ");
	}
	
	public static List<User> getAllUsers() {
		List <User> allUsers = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			Statement retrievalStatemnt = con.createStatement();
			ResultSet rs=retrievalStatemnt.executeQuery("select * from User;");
			while (rs.next()) {
				User thisUser = new User();
				thisUser.id = rs.getInt("id");
				thisUser.UserName = rs.getString("username");
				thisUser.Password = rs.getString("password");
				thisUser.FirstName = rs.getString("firstname");
				thisUser.LastName = rs.getString("lastname");
				thisUser.Phone = rs.getString("phone");
				thisUser.Email = rs.getString("email");
				thisUser.DateUpdated = rs.getTimestamp("dateupdated");
				allUsers.add(thisUser);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}

	@Override
	public User get() {
		return null;
	}
	
	@Override
	public List<User> getAll() {
		List <User> allUsers = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			Statement retrievalStatemnt = con.createStatement();
			ResultSet rs=retrievalStatemnt.executeQuery("select * from User;");
			while (rs.next()) {
				User thisUser = new User();
				thisUser.id = rs.getInt("id");
				thisUser.UserName = rs.getString("username");
				thisUser.Password = rs.getString("password");
				thisUser.FirstName = rs.getString("firstname");
				thisUser.LastName = rs.getString("lastname");
				thisUser.Phone = rs.getString("phone");
				thisUser.Email = rs.getString("email");
				allUsers.add(id, thisUser);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Boolean getIsReviewer() {
		return IsReviewer;
	}

	public void setIsReviewer(Boolean isReviewer) {
		IsReviewer = isReviewer;
	}

	public Boolean getIsAdmin() {
		return IsAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		IsAdmin = isAdmin;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}

	public int getUpdatedByUser() {
		return UpdatedByUser;
	}

	public void setUpdatedByUser(int updatedByUser) {
		UpdatedByUser = updatedByUser;
	}
	
	@Override
	public boolean add(User t) {
		String newUser="INSERT INTO User(username,password,firstname,lastname,phone,email,datecreated,dateupdated,isreviewer,isadmin) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = Connect.connect();
                PreparedStatement insertStatement = con.prepareStatement(newUser, Statement.RETURN_GENERATED_KEYS)) {
					insertStatement.setString(1,t.UserName);
					insertStatement.setString(2,t.Password);
					insertStatement.setString(3,t.FirstName);
					insertStatement.setString(4,t.LastName);
					insertStatement.setString(5,t.Phone);
					insertStatement.setString(6,t.Email);
					insertStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
					insertStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
					insertStatement.setInt(9, 0);
					insertStatement.setInt(10, 0);
					insertStatement.executeUpdate();
					ResultSet rs = insertStatement.getGeneratedKeys();
					rs.next();
					id = rs.getInt(1);
					return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(User t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("update User set UserName = ?, Password = ?, FirstName = ?, LastName = ?, Phone= ?, Email = ?, dateupdated = ? Where id = ?");
			statement.setString(1, t.getUserName());
			statement.setString(2, t.getPassword());
			statement.setString(3, t.getFirstName());
			statement.setString(4, t.getLastName());
			statement.setString(5, t.getPhone());
			statement.setString(6, t.getEmail());
			statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			statement.setInt(8, t.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(User t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("delete from User where id = ?");    
			statement.setInt(1, t.getId());    
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Timestamp getDateUpdated() {
		return DateUpdated;
	}

	public void setDateUpdated(Timestamp dateUpdated) {
		DateUpdated = dateUpdated;
	}
	
}
