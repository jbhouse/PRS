import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseRequest implements DAO<PurchaseRequest> {

	private int id;
	private int UserID;
	private int StatusID;
	private String Description;
    private String Justification;
    private String DeliveryMode;
    private String ReasonForRejection;
	private String DateNeeded;
    private Boolean IsActive;
    
	public PurchaseRequest() {}
	
	public PurchaseRequest(int userid, int statusid) {
		this.UserID=userid;
		this.StatusID=statusid;
		this.Description = Console.getString("what do you need?: ");
		this.Justification = Console.getString("why do you need this thing?: ");
		this.DeliveryMode = Console.getString("delivery mode? i guess?: ");
		this.DateNeeded = Console.getString("when do you need it by?: ");
	}

    public static PurchaseRequest getSpecificPurchaseRequest(int prID) {
		PurchaseRequest thisRequest = new PurchaseRequest();
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("select * from PurchaseRequest where id = ?");    
			statement.setInt(1, prID);    
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				thisRequest.id = rs.getInt("id");
				thisRequest.UserID = rs.getInt("userid");
				thisRequest.StatusID = rs.getInt("statusid");
				thisRequest.Description = rs.getString("Description");
				thisRequest.Justification = rs.getString("Justification");
				thisRequest.DeliveryMode = rs.getString("DeliveryMode");
				thisRequest.DateNeeded = rs.getString("dateneeded");
				return thisRequest;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisRequest;
    }

	public static List<PurchaseRequest> getUserRequests(int userID) {
		List <PurchaseRequest> allRequests = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("select * from PurchaseRequest where UserID = ?");    
			statement.setInt(1, userID);    
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				PurchaseRequest thisRequest = new PurchaseRequest();
				thisRequest.id = rs.getInt("id");
				thisRequest.UserID = rs.getInt("userid");
				thisRequest.StatusID = rs.getInt("statusid");
				thisRequest.Description = rs.getString("Description");
				thisRequest.Justification = rs.getString("Justification");
				thisRequest.DeliveryMode = rs.getString("DeliveryMode");
				thisRequest.DateNeeded = rs.getString("dateneeded");
				allRequests.add(thisRequest);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allRequests;
	}
	
	@Override
	public List<PurchaseRequest> getAll() {
		List <PurchaseRequest> allRequests = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			Statement retrievalStatemnt = con.createStatement();
			ResultSet rs=retrievalStatemnt.executeQuery("select * from PurchaseRequest;");
			while (rs.next()) {
				PurchaseRequest thisRequest = new PurchaseRequest();
				thisRequest.id = rs.getInt("id");
				thisRequest.UserID = rs.getInt("userid");
				thisRequest.StatusID = rs.getInt("statusid");
				thisRequest.Description = rs.getString("Description");
				thisRequest.Justification = rs.getString("Justification");
				thisRequest.DeliveryMode = rs.getString("DeliveryMode");
				allRequests.add(thisRequest);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allRequests;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getStatusID() {
		return StatusID;
	}

	public void setStatusID(int statusID) {
		StatusID = statusID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getJustification() {
		return Justification;
	}

	public void setJustification(String justification) {
		Justification = justification;
	}

	public String getDeliveryMode() {
		return DeliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		DeliveryMode = deliveryMode;
	}

	public String getReasonForRejection() {
		return ReasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		ReasonForRejection = reasonForRejection;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}
	
    public String getDateNeeded() {
		return DateNeeded;
	}

	public void setDateNeeded(String dateNeeded) {
		DateNeeded = dateNeeded;
	}
	
	@Override
	public PurchaseRequest get() {
		return null;
	}

	@Override
	public boolean add(PurchaseRequest t) {
//		untested code
		String newPR = "INSERT INTO PurchaseRequest(UserID,StatusID,Description,Justification,DeliveryMode,SubmittedDate,DateNeeded) VALUES(?,?,?,?,?,?,?)";
		try (Connection con = Connect.connect();
                PreparedStatement insertStatement = con.prepareStatement(newPR, Statement.RETURN_GENERATED_KEYS)) {
					insertStatement.setInt(1,t.getUserID());
					insertStatement.setInt(2,t.getStatusID());
					insertStatement.setString(3, t.Description);
					insertStatement.setString(4, t.Justification);
					insertStatement.setString(5, t.DeliveryMode);
					insertStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
					insertStatement.setDate(7, Date.valueOf(LocalDate.parse(this.getDateNeeded())));
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
	public boolean update(PurchaseRequest t) {
//		untested code
		try (Connection con = Connect.connect();
                PreparedStatement updateStatement = con.prepareStatement("UPDATE PurchaseRequest set UserID=?,StatusID=?,Description=?,Justification=?,DeliveryMode=?,SubmittedDate=?,DateNeeded=? Where id = ?")) {
					updateStatement.setInt(1,t.getUserID());
					updateStatement.setInt(2,t.getStatusID());
					updateStatement.setString(3, t.Description);
					updateStatement.setString(4, t.Justification);
					updateStatement.setString(5, t.DeliveryMode);
					updateStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
					updateStatement.setDate(7, Date.valueOf(LocalDate.parse(this.getDateNeeded())));
					updateStatement.setInt(8, t.getId());
					updateStatement.executeUpdate();
					return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(PurchaseRequest t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("delete from PurchaseRequest where id = ?");    
			statement.setInt(1, t.getId());    
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
