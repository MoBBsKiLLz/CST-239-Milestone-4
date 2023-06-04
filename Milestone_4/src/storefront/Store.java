package storefront;

import java.util.ArrayList;
import store.*;

/**
 * Store class with inventory and shoppingCart instances
 * @author migg_
 *
 */
public class Store {
	/**
	 * Fields include inventory and shoppingCart as new their respective types
	 */
    InventoryManager inventory;
    ShoppingCart shoppingCart;
    
    
    /**
     * Store default constructor that sets fields as the respective objects
     */
    public Store(){
		shoppingCart = new ShoppingCart();
		inventory = new InventoryManager();
    }
    
    /**
     * Store constructor that takes inventory and shoppingCart parameters
     * @param inventory Inventory parameter of type InventoryManager
     * @param shoppingCart ShoppingCart parameter of type ShoppingCart
     */
    public Store(InventoryManager inventory, ShoppingCart shoppingCart) {
    	this.inventory = inventory;
		this.shoppingCart = shoppingCart;
    }
    
    /**
     * Returns the inventory instance
     * @return Returns the inventory as type InventoryManager
     */
    public InventoryManager getInventory() {
		return inventory;
	}

    /**
     * Sets the inventory taking one parameter
     * @param inventory Inventory parameter as type InventoryManager
     */
	public void setInventory(InventoryManager inventory) {
		this.inventory = inventory;
	}

	/**
     * Returns the shopping cart instance
     * @return Returns the shopping cart as type ShoppingCart
     */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
     * Sets the shopping cart taking one parameter
     * @param shoppingCart Shopping Cart parameter as type ShoppingCart
     */
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	/**
     * Completes the purchase of a product by removing it from the inventory
     * @return Returns a receipt as an ArrayList
     */
    public ArrayList<Product> purchase() {
		ArrayList<Product> receipt = new ArrayList<Product>();
		Product currentSC;
		Product currentInv;
		
		ArrayList<Product> shoppingCartList = this.shoppingCart.getShoppingCart();
		ArrayList<Product> inventoryList = this.inventory.getInventory();
		
		if(shoppingCartList.size() > 0) {
			// Add products in shopping cart to receipt
			shoppingCartList.forEach(cart ->{
				receipt.add(cart);
			});
			
			// Loop through shoppingCart
			for(int s = 0; s < shoppingCartList.size(); s++) {
				currentSC = shoppingCartList.get(s);
				// Loop through inventory
				for(int i = 0; i < inventoryList.size(); i++) {
					currentInv = inventoryList.get(i);
					// Check if product name matches
					if(currentSC.getName().compareTo(currentInv.getName()) == 0) {
						// Set the new inventory product quantity based on the quantity being purchased
						currentInv.setQuantity(currentInv.getQuantity() - currentSC.getQuantity());
					}
				}
			}
			
			// Clear the shopping cart
			this.getShoppingCart().emptyShoppingCart();
			
			System.out.println("Purchase successful!");
			
			return receipt;
		}
		else {
			System.out.println("Shopping cart is empty.");
			return null;
		}
	
	}
    
    /**
     * Cancels the purchase of a product taking a parameter
     * @param receipt Receipt parameter as a ArrayList
     * @return Returns true or false
     */
    public boolean cancel(ArrayList<Product> receipt) {
		Product currentRec;
		Product currentInv;
		boolean result = false;
		
		ArrayList<Product> inventoryList = this.inventory.getInventory();
		
		// Loop through receipt
		for(int r = 0; r < receipt.size(); r++) {
			currentRec = receipt.get(r);
			// Loop through inventory
			for(int i = 0; i < inventoryList.size(); i++) {
				currentInv = inventoryList.get(i);
				// Check if product name matches
				if(currentRec.getName().compareTo(currentInv.getName())  == 0) {
					// Set the new inventory product quantity based on the quantity being purchased
					currentInv.setQuantity(currentInv.getQuantity() + currentRec.getQuantity());
					result = true;
				}
			}
		}
		
		return result;
	}
}
