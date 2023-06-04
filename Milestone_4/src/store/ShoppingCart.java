package store;

import java.util.ArrayList;
import product.*;
import storefront.Product;
/**
 * ShoppingCart class to manager store shopping cart
 * @author migg_
 *
 */
public class ShoppingCart {
	/**
	 * ShoppingCart as an ArrayList of type Product
	 */
	private ArrayList<Product> shoppingCart;
	
	/**
	 * Default constructor that creates an empty ArrayList of type Product for shopping cart
	 */
	public ShoppingCart() {
		this.shoppingCart = new ArrayList<Product>();
	}
	
	/**
	 * Constructor with one parameter
	 * @param shoppingCart Shopping Cart parameter as an ArrayList of Product type
	 */
	public ShoppingCart(ArrayList<Product> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	/**
     * Returns the ShoppingCart of a product
     * @return Returns the ShoppingCart as an ArrayList
     */
    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Sets the ShoppingCart of a product taking a parameter
     * @param shoppingCart ShoppingCart parameter as a ArrayList
     */
    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    /**
     * Adds a product to the ShoppingCart taking two parameters
     * @param productName ProductName parameter as a String
     * @param quantity Quantity parameter as an int
     * @param inventoryManager InventoryManager parameter as a InventoryManager type
     * @return Returns true or false
     */
    public boolean addToCart(String productName, int quantity, InventoryManager inventoryManager) {
		// Get the inventory list
    	ArrayList<Product>inventory = inventoryManager.getInventory();
    	
    	// Loop through inventory
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().compareTo(productName) == 0) {
				if(inventory.get(i).getQuantity() >= quantity) {
					if(shoppingCart.size() > 0) {
						// Loop through shoppingCart
						for(int s = 0; s < shoppingCart.size(); s++) {
							if(shoppingCart.get(s).getName().compareTo(productName) == 0) {
								shoppingCart.get(s).setQuantity(shoppingCart.get(s).getQuantity() + quantity);
								return true;
							} 
						}
						
						// Add a new product if the product wasn't in the shopping cart
						Product newProduct = new Product();
						newProduct.setName(productName);
						newProduct.setQuantity(quantity);
						newProduct.setPrice(inventory.get(i).getPrice());
						newProduct.setDescription(inventory.get(i).getDescription());
						shoppingCart.add(newProduct);
						return true;
					}
					else {
						// Create a new product if the shopping cart was empty
						Product newProduct = new Product();
						newProduct.setName(productName);
						newProduct.setQuantity(quantity);
						newProduct.setPrice(inventory.get(i).getPrice());
						newProduct.setDescription(inventory.get(i).getDescription());
						shoppingCart.add(newProduct);
						return true;
					}
				}
				else {
					System.out.println("The quantity entered exceeds the armor inventory quantity of " + 
						inventory.get(i).getQuantity() + ".");
					System.out.println();
				}
			}
		}
		
		return false;
	}
    
    /**
     * Removes a product from the ShoppingCart
     * @param productName ProductName parameter as a String
     * @param quantity Quantity parameter as an int
     * @return Returns true or false
     */
    public boolean removeFromCart(String productName, int quantity) {
    	if (shoppingCart.size() > 0) {
    		// Loop through shoppingCart
			for(int s = 0; s < shoppingCart.size(); s++) {
			    Product currentProduct = shoppingCart.get(s);
			    // Check for product name match
			    if(currentProduct.getName().compareTo(productName) == 0) {
			    	// Check quantity
					if(currentProduct.getQuantity() == quantity || currentProduct.getQuantity() < quantity) {
						// Remove the product from the shopping cart
					    shoppingCart.remove(s);
					    System.out.println();
					    System.out.println("Item removed from shopping cart.");
					    return true;
					}
					else if (currentProduct.getQuantity() > quantity) {
						// Decrease shopping cart product quantity
					    currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					    System.out.println();
					    System.out.println("Item removed from shopping cart.");
					    return true;
					}
			    }
			}
    	}
    	else {
    		System.out.println();
    		System.out.println("Shopping cart is empty.");
    		return false;
    	}
    	
    	System.out.println();
    	System.out.println("Product was not found.");
    	return false;
    }
    
    /**
     * Void method that prints the shopping cart
     */
    public void showShoppingCart() {
    	System.out.println("SHOPPING CART");
		System.out.println();
		
    	// Loop through inventory
    	this.getShoppingCart().forEach(i -> {
    		// Print product details
		    System.out.println("Product: " + i.getName());
		    System.out.println("Description: " + i.getDescription());
		    System.out.println("Price: " + i.getPrice());
		    System.out.println("Qty: " + i.getQuantity());
		    // Check for instance each subclass
		    if (i instanceof Armor) {
		    	System.out.println("Resistance: " + ((Armor) i).getResistance());
		    }
		    else if (i instanceof Weapon) {
		    	System.out.println("Damage: " + ((Weapon) i).getDamage());
		    }
		    else if (i instanceof Health) {
		    	System.out.println("Amount: " + ((Health) i).getAmount());
		    }
		});
    	
    	System.out.println();
    }
    
    /**
     * Void method to empty the shopping cart
     */
    public void emptyShoppingCart() {
    	this.shoppingCart.clear();
    }
}
