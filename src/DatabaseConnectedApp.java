//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

public class DatabaseConnectedApp {

	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		List<Product> allProducts = Product.getAllproducts();
		List<User> allUsers = User.getAllUsers();
		User ourUser = null;
		String login = Console.getString("Log in or sign up? (l/s): ");
		if (login.equals("l")) {
			ourUser = attemptLogin(allUsers);
//			System.out.println(ourUser.getDateUpdated());
		} else {
			ourUser = new User();
			ourUser.createNew();
			allUsers.add(ourUser);
			ourUser.add(ourUser);
//			System.out.println(ourUser.getId());
		}
		
		Vendor myVendor = new Vendor();
		List<Vendor> allVendors = myVendor.getAll();

		String requestType = Console.getString("create new purchase request or update an old one? (c/u): ");
		List<PurchaseRequest> userRequests = PurchaseRequest.getUserRequests(ourUser.getId());
		PurchaseRequest ourRequest = null;
		if (requestType.equals("u")) {
			for(PurchaseRequest pr : userRequests) {
				System.out.println(pr.getId()+" "+pr.getDescription()+" "+pr.getJustification()+" "+pr.getDeliveryMode()+" "+pr.getDateNeeded());
			}
			int prID = Console.getInt("please enter the id of the purchaserequest you would like to update: ");
			ourRequest = PurchaseRequest.getSpecificPurchaseRequest(prID);
		} else {
			ourRequest = new PurchaseRequest(ourUser.getId(), 1);
			ourRequest.add(ourRequest);
		}
		System.out.println("the chosen purchase request: "+ourRequest.getDescription()+" "+ourRequest.getJustification()+" "+ourRequest.getDeliveryMode()+" "+ourRequest.getDateNeeded());
		System.out.println("Line items for this purchase request");
		List<PurchaseRequestLineitem> associatedLineItems = PurchaseRequestLineitem.selectByPurchaseRequest(ourRequest.getId());
		for(PurchaseRequestLineitem prli : associatedLineItems) {
			Product thisProduct = Product.getSpecificProduct(prli.getProductID());
			System.out.println(prli.getQuantity()+" "+thisProduct.getName());
		}
		
		String prChoices = Console.getString("Would you like to update a line item, delete a line item, or add a new line item? (u/d/a):");
		PurchaseRequestLineitem newLineItem = null;
		if (prChoices.equals("a")) {
			System.out.println("Let's add a line item to this purchase request");
			System.out.println("\nProducts\n");
			for(Product p : allProducts) {
				System.out.println(p.getId()+" "+p.getName()+" "+p.getPrice()+" "+p.getPartNumber());
			}
			int productID = Console.getInt("Select an object by its id: ");
			int quantity = Console.getInt("how many do you need?: ");
			newLineItem = new PurchaseRequestLineitem(ourRequest.getId(), productID, quantity);
			newLineItem.add(newLineItem);
		} else if (prChoices.equals("u")) {
			newLineItem = PurchaseRequestLineitem.getSpecificLineItem(Console.getInt("Select a line item by its id: "));
			if (Console.getString("would you like to update the quantity?: ").equals("y")) {
				newLineItem.setQuantity(Console.getInt("new quantity: "));
			}
			if (Console.getString("would you like to change it to another product?: ").equals("y")) {
				newLineItem.setProductID(Console.getInt("New products id: "));
			}
			newLineItem.update(newLineItem);
		} else if (prChoices.equals("d")) {
			newLineItem = PurchaseRequestLineitem.getSpecificLineItem(Console.getInt("Select a line item by its id: "));
			newLineItem.delete(newLineItem);
		}
	}
	
	public static User attemptLogin(List<User> allUsers) {
		User currentUser = null;
		String uname = Console.getString("Enter your username: ");
		String pword = Console.getString("Enter your password: ");
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getUserName().equals(uname)&&allUsers.get(i).getPassword().equals(pword)) {
				return allUsers.get(i);
			}
		}
		return currentUser;
	}

}
