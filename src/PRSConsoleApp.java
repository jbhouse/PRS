
public class PRSConsoleApp {

	public static void main(String[] args) {
	
		System.out.println("Welcome to the PRS console");
        Boolean cont = true;
        while (cont) {
			System.out.println("Let's create a new user");
			String userName = Console.getString("Username: ");
			String firstName = Console.getString("First Name: ");
			String lastName = Console.getString("Last Name: ");
			String password = Console.getString("Password: ");
			String phoneNumber = Console.getString("Phone Number: ");
			String email = Console.getString("Email: ");
			System.out.println();
			User newUser = new User(userName, firstName, lastName, password, phoneNumber, email);
			newUser.display();
			
			Vendor newVendor = new Vendor();
			newVendor.setCode(Console.getString("Vendor code: "));
			newVendor.setName(Console.getString("Vendor name: "));
			newVendor.setAddress(Console.getString("Vendor address: "));
			newVendor.setCity(Console.getString("Vendor city: "));
			newVendor.setState(Console.getString("Vendor state: "));
			newVendor.setZip(Console.getString("Vendor zip code: "));
			newVendor.setPhone(Console.getString("Vendor phone number: "));
			newVendor.setEmail(Console.getString("Vendor email: "));
	    	System.out.println();
	    	
	    	Product newProduct = new Product(0);
	    	newProduct.setPrice(Console.getDouble("product price: "));
	    	newProduct.setPartNumber(Console.getString("part number: "));
	    	newProduct.setName(Console.getString("name: "));
	    	newProduct.setUnit(Console.getString("unit: "));
	    	newProduct.setPhotoPath(Console.getString("photo path?: "));
	        cont = Console.userWantsToContinue("Continue? (y/n): ");
	        System.out.println();
	        newProduct.display();
	        
	        Status newStatus = new Status();
	        newStatus.setDescription(Console.getString("status description: "));
	        System.out.println();
	        newStatus.display();
	        
	        PurchaseRequest newPurchaseRequest = new PurchaseRequest(0,0);
	        newPurchaseRequest.setDescription(Console.getString("description: "));
	        newPurchaseRequest.setJustification(Console.getString("justification: "));
	        newPurchaseRequest.setDeliveryMode(Console.getString("delivery mode: "));
	        newPurchaseRequest.setReasonForRejection(Console.getString("reason for rejection: "));
	        System.out.println();
	        newPurchaseRequest.display();
	        
	        System.out.println();
	        int quantity = Console.getInt("purchase request line item quantity: ");
	        PurchaseRequestLineitem newlineitem = new PurchaseRequestLineitem(0,0,quantity);
	        newlineitem.display();
	    }
	    System.out.println("Bye - Come back soon!");
	}

}
