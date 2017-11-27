import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vendor implements DAO<Vendor> {

    private int id;
    private String code;
    private String Name;
    private String Address;
    private String City;
    private String State;
    private String Zip;
    private String Phone;
    private String Email;
    private Boolean IsPreApproved;
    private Boolean IsActive;
    private int UpDatedByUser;

	public Vendor() {
		this.IsPreApproved=false;
		this.IsActive=true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
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

	public Boolean getIsPreApproved() {
		return IsPreApproved;
	}

	public void setIsPreApproved(Boolean isPreApproved) {
		IsPreApproved = isPreApproved;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}

	public int getUpDatedByUser() {
		return UpDatedByUser;
	}

	public void setUpDatedByUser(int upDatedByUser) {
		UpDatedByUser = upDatedByUser;
	}
	
	@Override
	public Vendor get() {
		return null;
	}
	
	@Override
	public boolean add(Vendor t) {
		String newVendor="INSERT INTO Vendor(code,Name,Address,City,State,Zip,phone,email,IsPreApproved,IsActive) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = Connect.connect();
                PreparedStatement insertStatement = con.prepareStatement(newVendor)) {
					insertStatement.setString(1,t.code);
					insertStatement.setString(2,t.Name);
					insertStatement.setString(3,t.Address);
					insertStatement.setString(4,t.City);
					insertStatement.setString(5,t.State);
					insertStatement.setString(6,t.Zip);
					insertStatement.setString(7,t.Phone);
					insertStatement.setString(8,t.Email);
					insertStatement.setInt(9, 1);
					insertStatement.setInt(10, 1);
					insertStatement.executeUpdate();
					return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Vendor t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement updateStatement = con.prepareStatement("update Vendor set code = ?, Name = ?,Address = ?,City = ?,State = ?,Zip = ?,Phone = ?,email = ?,IsPreApproved = ?,IsActive = ? Where id = ?");
			updateStatement.setString(1,t.code);
			updateStatement.setString(2,t.Name);
			updateStatement.setString(3,t.Address);
			updateStatement.setString(4,t.City);
			updateStatement.setString(5,t.State);
			updateStatement.setString(6,t.Zip);
			updateStatement.setString(7,t.Phone);
			updateStatement.setString(8,t.Email);
			if (t.IsPreApproved) {
				updateStatement.setInt(9, 0);
			} else {
				updateStatement.setInt(9, 1);
			}
			if (t.IsActive) {
				updateStatement.setInt(10, 0);
			} else {
				updateStatement.setInt(10, 1);
			}
			updateStatement.setInt(11, t.getId());
			updateStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Vendor t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("delete from Vendor where id = ?");    
			statement.setInt(1, t.getId());    
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Vendor> getAll() {
		List <Vendor> allVendors = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			Statement retrievalStatemnt = con.createStatement();
			ResultSet rs=retrievalStatemnt.executeQuery("select * from Vendor;");
			while (rs.next()) {
				Vendor thisVendor = new Vendor();
				thisVendor.id = rs.getInt("id");
				thisVendor.Name = rs.getString("name");
				thisVendor.City = rs.getString("city");
				thisVendor.State = rs.getString("state");
				thisVendor.Zip = rs.getString("zip");
				thisVendor.Phone = rs.getString("phone");
				allVendors.add(id, thisVendor);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allVendors;
	}

}
