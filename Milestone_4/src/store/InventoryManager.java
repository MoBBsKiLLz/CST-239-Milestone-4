package store;

import java.util.ArrayList;
import product.*;
import storefront.Product;
/**
 * Inventory manager class to manage store inventory
 * @author migg_
 *
 */
public class InventoryManager {
	/**
	 * Inventory as an ArrayList of type Product
	 */
	private ArrayList<Product> inventory;
	
	/**
	 * Default constructor that creates an empty ArrayList of type Product for inventory
	 */
	public InventoryManager() {
		this.inventory = new ArrayList<Product>();
	}
	
	/**
	 * Constructor with one parameter
	 * @param inventory Inventory parameter as an ArrayList of Product type
	 */
	public InventoryManager(ArrayList<Product> inventory) {
		this.inventory = inventory;
	}
	
	/**
     * Returns the Inventory of the store
     * @return Returns the Inventory as an ArrayList
     */
	public ArrayList<Product> getInventory() {
		return this.inventory;
	}
	
    /**
     * Sets the Inventory of a product taking a parameter and casts the array wildcard to a product
     * @param inventory Inventory parameter as an ArrayList
     */
	public void setInventory(ArrayList<Product> inventory) {
		this.inventory = (inventory);
	}
	
	/**
	 * Adds a product to the inventory, if it's in the list already it will increase the quantity.
	 * @param product Product as type Product
	 * @return Returns boolean value if the product was added
	 */
    public boolean addToInventory(Product product) {
    	// Check if inventory is empty
		if(inventory.size() > 0) {
			// Loop through inventory
			for(int i = 0; i < inventory.size(); i++) {
				if(inventory.get(i).getName().compareTo(product.getName()) == 0) {
					inventory.get(i).setQuantity(inventory.get(i).getQuantity() + product.getQuantity());
					return true;
				} 
			}
		}
			
		return inventory.add(product);
	}
    
    /**
     * Removes a product from inventory or decreases the quantity
     * @param productName Product Name parameter as a String
     * @param quantity Quantity parameter as an int
     * @return Returns boolean value if the product was removed from the inventory
     */
    public boolean removeFromInventory(String productName, int quantity) {
    	if (inventory.size() > 0) {
    		// Loop through inventory
			for(int i = 0; i < inventory.size(); i++) {
			    Product currentProduct = inventory.get(i);
			    // Check for product name match
			    if(currentProduct.getName().compareTo(productName) == 0) {
			    	// Check quantity
					if(currentProduct.getQuantity() == quantity || currentProduct.getQuantity() < quantity) {
						// Remove the product from the shopping cart
					    inventory.remove(i);
					    System.out.println();
					    System.out.println("Product removed from inventory.");
					    return true;
					}
					else if (currentProduct.getQuantity() > quantity) {
						// Decrease inventory product quantity
					    currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					    System.out.println();
					    System.out.println("Product removed from inventory.");
					    return true;
					}
			    }
			}
    	}
    	else {
    		System.out.println();
    		System.out.println("Inventory is empty.");
    		return false;
    	}
    	
    	System.out.println();
    	System.out.println("Product was not found.");
    	return false;
    }
	
	/**
     * Void method that prints the inventory
     */
    public void showInventory() {
    	System.out.println("INVENTORY");
		System.out.println();
		
    	// Loop through inventory
    	this.getInventory().forEach(i -> {
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
		    
		    System.out.println();
		});
    	
    }
}
