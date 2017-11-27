import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseRequestLineitem implements DAO<PurchaseRequestLineitem> {

	private int id;
	private int PurchaseRequestID;
	private int ProductID;
    private int Quantity;
    private Boolean IsActive;
    private int UpdatedByUser;
    
    public PurchaseRequestLineitem() {}
    
	public PurchaseRequestLineitem(int PurchaseRequestID, int ProductID, int Quantity) {
		this.PurchaseRequestID=PurchaseRequestID;
		this.ProductID=ProductID;
		this.Quantity=Quantity;
	}
	
	public static PurchaseRequestLineitem getSpecificLineItem(int lineItemID) {
		PurchaseRequestLineitem thisItem = new PurchaseRequestLineitem();
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("select * from PurchaseRequestLineitem where id = ?");    
			statement.setInt(1, lineItemID);    
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				thisItem.id = lineItemID;
				thisItem.PurchaseRequestID = rs.getInt("PurchaseRequestID");
				thisItem.ProductID = rs.getInt("ProductID");
				thisItem.Quantity = rs.getInt("Quantity");
				return thisItem;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return thisItem;
	}
	
	public static List<PurchaseRequestLineitem> selectByPurchaseRequest(int prID){
		List<PurchaseRequestLineitem> relevantItems = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("select * from PurchaseRequestLineItem where PurchaseRequestID = ?");    
			statement.setInt(1, prID);    
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				PurchaseRequestLineitem thisLineItem = new PurchaseRequestLineitem();
				thisLineItem.id = rs.getInt("id");
				thisLineItem.PurchaseRequestID = prID;
				thisLineItem.ProductID = rs.getInt("productid");
				thisLineItem.Quantity = rs.getInt("quantity");
				relevantItems.add(thisLineItem);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return relevantItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseRequestID() {
		return PurchaseRequestID;
	}

	public void setPurchaseRequestID(int purchaseRequestID) {
		PurchaseRequestID = purchaseRequestID;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
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
	public PurchaseRequestLineitem get() {
		return null;
	}

	@Override
	public List<PurchaseRequestLineitem> getAll() {
		return null;
	}

	@Override
	public boolean add(PurchaseRequestLineitem t) {
//		i haven't tested this, so no promises
		String newPRLI="INSERT INTO PurchaseRequestLineItem(PurchaseRequestID,ProductID,Quantity,IsActive) VALUES(?,?,?,?)";
		try (Connection con = Connect.connect();
                PreparedStatement insertStatement = con.prepareStatement(newPRLI)) {
					insertStatement.setInt(1,t.PurchaseRequestID);
					insertStatement.setInt(2,t.ProductID);
					insertStatement.setInt(3, t.Quantity);
					insertStatement.setBoolean(4, true);
					insertStatement.executeUpdate();
					return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(PurchaseRequestLineitem t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("update PurchaseRequestLineitem set ProductID = ?, quantity = ? Where id = ?");
			statement.setInt(1, t.getProductID());
			statement.setInt(2, t.getQuantity());
			statement.setInt(3, t.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(PurchaseRequestLineitem t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("delete from PurchaseRequestLineitem where id = ?");    
			statement.setInt(1, t.getId());    
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
