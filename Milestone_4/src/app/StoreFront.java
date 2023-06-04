package app;

import storefront.*;
import product.*;
import store.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * StoreFront class, driver
 * @author migg_
 *
 */
public class StoreFront {

	/**
	 * Main method
	 * @param args Default parameter
	 */
    public static void main(String[] args) {
		Store myStore = new Store(); // Create new store
		InventoryManager inventory = new InventoryManager();
		ArrayList<Product> inventoryList = new ArrayList<Product>();
		ArrayList<ComparableProduct> sortedList = new ArrayList<ComparableProduct>();
		ArrayList<Product> receipt = new ArrayList<Product>(); // Create a receipt
		
		// Create armor and add it to the inventory list
		Product armor1 = new Armor("Light Armor", "Light armor with low resistance.", 5.99, 7, 30);
		Product armor2 = new Armor("Heavy Armor", "Heavy armor with high resistance.", 9.99, 3, 50);
		inventoryList.add(armor1);
		inventoryList.add(armor2);
		
		// Create weapons and add them to the inventory list
		Product weapon1 = new Weapon("Sword", "Close range with high damage.", 3.99, 5, 35);
		Product weapon2 = new Weapon("Bow", "Long range weapon with low damage.", 7.99, 2, 12);
		inventoryList.add(weapon1);
		inventoryList.add(weapon2);

		// Create weapons and add them to the inventory list
		Product health1 = new Health("Small Health", "Small amount of health.", 5.00, 12, 15);
		Product health2 = new Health("Large Health", "Large amount of health.", 15.00, 6, 45);
		inventoryList.add(health1);
		inventoryList.add(health2);
		
		// Add inventory to sortList
		inventoryList.forEach(p ->{
			ComparableProduct newProduct = new ComparableProduct(p.getName(), p.getDescription(), p.getPrice(), p.getQuantity());
			sortedList.add(newProduct);
		});
		
		// Sort sortList 
		Collections.sort(sortedList);
		// Add sorted products to the sorted inventory
		// Loop through the sortedList
		for(int s = 0; s < sortedList.size(); s++) {
			// Loop through the inventoryList
			for(int i = 0; i < inventoryList.size(); i++) {
				if(sortedList.get(s).getName().compareTo(inventoryList.get(i).getName()) == 0) {
					// Add matching inventory item to the sorted inventory
					inventory.addToInventory(inventoryList.get(i));
				}
			}
		}
		// Add sorted inventory to the store inventory
		myStore.setInventory(inventory);
		
		System.out.println("WELCOME TO THE EQUIPMENT STORE!");
		System.out.println();
		
		// Show the inventory
		myStore.getInventory().showInventory();
		
		// Ask user what they would like to purchase
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter -1 at any time to end.");
		System.out.print("Enter the product you want to purchase: ");
		String product = input.nextLine();
		String addProduct = "";
		System.out.println();
		
		if(product.compareTo("-1") != 0) {
		    System.out.print("Enter the quantity you want to purchase: ");
		    String quantity = input.nextLine();
		    
		    System.out.println();
		    
		    if(quantity.compareTo("-1") != 0) {
				while(product.compareTo("-1") != 0 && quantity.compareTo("-1") != 0) {
				    // AddProduct value
					if (addProduct.toUpperCase().compareTo("N") != 0) {
						// Add product to shopping cart
						boolean addToCart = myStore.getShoppingCart().addToCart(product, 
								Integer.parseInt(quantity), myStore.getInventory());
						if (addToCart) {
							System.out.println("Product was added to the shopping cart");
							System.out.println();
						}
						else if (!addToCart){
							System.out.println("Product was not added to the shopping cart");
							System.out.println();
						}
				    }
				    
				    // Ask user if they would like to check-out
					myStore.getShoppingCart().showShoppingCart();
				    System.out.print("Would you like to check-out? (Y/N) ");
				    String checkOut = input.nextLine();
				    if(checkOut.toUpperCase().compareTo("Y") == 0) {
						System.out.println();
						receipt = myStore.purchase();
						
						if(receipt != null) {
						    break;
						}
				    }
				    else if (checkOut.compareTo("-1") == 0) {
					break;
				    }
				    
				    System.out.println();
				    
				    // Ask user if they would like to remove an item
				    System.out.print("Would you like to remove a product? (Y/N) ");
				    String remove = input.nextLine();
				    if(remove.toUpperCase().compareTo("Y") == 0) {
						System.out.println();
						System.out.print("Enter the name of the product: ");
						product = input.nextLine();
						System.out.println();
						System.out.print("Enter the quantity: ");
						quantity = input.nextLine();
						myStore.getShoppingCart().removeFromCart(product, Integer.parseInt(quantity));
				    }
				    else if (remove.compareTo("-1") == 0) {
				    	break;
				    }
				    
				    System.out.println();
				    
				    // Ask user if they would like to add another product
				    System.out.print("Would you like to add another product? (Y/N) ");
				    addProduct = input.nextLine();
				    System.out.println();
				    if(addProduct.toUpperCase().compareTo("Y") == 0) {
						System.out.print("Enter the product you want to purchase: ");
						product = input.nextLine();
						System.out.println();
						System.out.print("Enter the quantity you want to purchase: ");
						quantity = input.nextLine();
						System.out.println();
				    }
				    else if (addProduct.compareTo("-1") == 0) {
				    	break;
				    }
				}
		    }
		}
		
		if (receipt != null && receipt.size() > 0) {
			System.out.println();
			System.out.println("RECEIPT");
			receipt.forEach(r -> {
				System.out.println("Product: " + r.getName() + " Qty: " + r.getQuantity());
			});
			
			System.out.println();
			
		    // The user if they want to cancel their purchase
		    System.out.print("Would you like to cancel your purchase? (Y/N) ");
		    String cancel = input.nextLine();
		    if (cancel.toUpperCase().compareTo("Y") == 0) {
		    	myStore.cancel(receipt);
		    }
		}
		
		System.out.println();
		System.out.println("Goodbye.");
		input.close();
    }

}
