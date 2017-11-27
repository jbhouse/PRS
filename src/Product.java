import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Product implements DAO<Product> {

	private int id;
	private int VendorID;
	private double price;
	private String PartNumber;
    private String Name;
    private String Unit;
    private String PhotoPath;
    private Boolean IsActive;
    
    public static Product getSpecificProduct(int productID) {
    	Product thisProduct = new Product();
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("select * from Product where id = ?");    
			statement.setInt(1, productID);    
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				thisProduct.id = rs.getInt("id");
				thisProduct.VendorID = rs.getInt("vendorid");
				thisProduct.price = rs.getDouble("price");
				thisProduct.PartNumber = rs.getString("partnumber");
				thisProduct.Name = rs.getString("name");
				thisProduct.Unit = rs.getString("unit");
				thisProduct.PhotoPath = rs.getString("photopath");
				return thisProduct;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return thisProduct;
    }
    
	public static List<Product> getAllproducts() {
		List <Product> allProducts = new ArrayList<>();
		try {
			Connection con = Connect.connect();
			Statement retrievalStatemnt = con.createStatement();
			ResultSet rs=retrievalStatemnt.executeQuery("select * from Product;");
			while (rs.next()) {
				Product thisProduct = new Product(rs.getInt("vendorid"));
				thisProduct.id = rs.getInt("id");
				thisProduct.price = rs.getDouble("price");
				thisProduct.PartNumber = rs.getString("PartNumber");
				thisProduct.Name = rs.getString("name");
				thisProduct.Unit = rs.getString("unit");
				thisProduct.PhotoPath = rs.getString("PhotoPath");
				allProducts.add(thisProduct);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allProducts;
	}
	
	public Product() {}

	public Product(int vendorid) {
		this.VendorID=vendorid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVendorID() {
		return VendorID;
	}

	public void setVendorID(int vendorID) {
		VendorID = vendorID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getPhotoPath() {
		return PhotoPath;
	}

	public void setPhotoPath(String photoPath) {
		PhotoPath = photoPath;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}
	
	@Override
	public Product get() {
		return null;
	}

	@Override
	public List<Product> getAll() {
		return null;
	}

	@Override
	public boolean add(Product t) {
		String newProduct="INSERT INTO Product(VendorID,price,PartNumber,Name,Unit,PhotoPath,IsActive) VALUES(?,?,?,?,?,?,?)";
		try (Connection con = Connect.connect();
                PreparedStatement insertStatement = con.prepareStatement(newProduct)) {
					insertStatement.setInt(1,t.VendorID);
					insertStatement.setDouble(2,t.price);
					insertStatement.setString(3, t.PartNumber);
					insertStatement.setString(4, t.Name);
					insertStatement.setString(5, t.Unit);
					insertStatement.setString(6, t.PhotoPath);
					insertStatement.setBoolean(7, true);
					insertStatement.executeUpdate();
					return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Product t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("update Product set VendorID = ?, price = ?, Partnumber = ?, Name = ?, Unit = ?, PhotoPath = ?, IsActive = ? Where id = ?");
			statement.setInt(1, t.getVendorID());
			statement.setDouble(2, t.getPrice());
			statement.setString(3, t.getPartNumber());
			statement.setString(4, t.getName());
			statement.setString(5, t.getUnit());
			statement.setString(6, t.getPhotoPath());
			if (t.IsActive) {
				statement.setInt(7, 0);
			} else {
				statement.setInt(7, 1);
			}
			statement.setInt(8, t.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Product t) {
		try {
			Connection con = Connect.connect();
			PreparedStatement statement = con.prepareStatement("delete from Product where id = ?");    
			statement.setInt(1, t.getId());    
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
