import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Status implements DAO<Status> {

	private int id;
	private int updaterID;
	private String description;
//	private int urgency;
	private int UpdatedByUser;

	Status() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUpdatedByUser() {
		return UpdatedByUser;
	}

	public void setUpdatedByUser(int updatedByUser) {
		UpdatedByUser = updatedByUser;
	}
	
	@Override
	public Status get() {
		return null;
	}

	@Override
	public List<Status> getAll() {
		return null;
	}

	@Override
	public boolean add(Status t) {
		String newStatus="INSERT INTO Status(Status,UpdatedByUser) VALUES(?,?)";
		try (Connection con = Connect.connect();
                PreparedStatement insertStatement = con.prepareStatement(newStatus)) {
					insertStatement.setString(1,t.description);
					insertStatement.setInt(2,t.updaterID);
					insertStatement.executeUpdate();
					return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Status t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("update Status set Description = ?, UpdatedByUser = ? Where id = ?");
			statement.setString(1, t.getDescription());
			statement.setInt(2, t.getUpdaterID());
			statement.setInt(3, t.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Status t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("delete from Status where id = ?");    
			statement.setInt(1, t.getId());    
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getUpdaterID() {
		return updaterID;
	}

	public void setUpdaterID(int updaterID) {
		this.updaterID = updaterID;
	}

}
