import java.util.Scanner;

public class HouseholdGoodsDriver {
	
	private static final String correctPW = "comp249";
	private static Scanner keyboard = new Scanner(System.in);
	private static int maxItems = 100;
	private static int itemRemain;
	private static HouseholdGoods[] goods = new HouseholdGoods[maxItems];
	
	public static void main (String[] args) {
		
		//Welcome message
		System.out.println("\n------------------------------------------------------------");
		System.out.println("     Welcome to The Real Stuff's Household Goods Store");		
		System.out.println("------------------------------------------------------------");
		
		int menuChoice;
		boolean mainMenu = false;
		
		//Prompts user for a menu item 
				do {
					do {
						do {
						System.out.println("\nWhat would you like to do?");
						System.out.println("\t1. Enter a new item in inventory (password required)");
						System.out.println("\t2. Change information of an item in inventory (password required)");
						System.out.println("\t3. Display all items of a specific type");
						System.out.println("\t4. Display all items under a certain price");
						System.out.println("\t5. Statistics on your inventory");
						System.out.println("\t6. Quit");
						System.out.print("Please enter your choice --> ");
						menuChoice = keyboard.nextInt();
						} while (menuChoice < 1 || menuChoice > 6);
						
						if (menuChoice == 1 || menuChoice == 2) {
							if (checkPW() == false)
								mainMenu = true;
						}
					} while (mainMenu);
					
					switch (menuChoice) {
					
						case 1:
							option1();
							break;
						case 2:
							option2();
							continue;
						case 3:
							option3();
							continue;
						case 4:
							option4();
							continue;
						case 5:
							option5();
							continue;
						case 6:
							option6();
					} 
				} while (menuChoice != 6);
	}
	// Check password
	public static boolean checkPW() {
		
		String userPW;
		int attemptCountPW = 0;
		
		do {
			System.out.print("What's the password?: ");
			userPW = keyboard.next();
			
			// confirms correct password
			if (userPW.equals(correctPW))
				return true;
			else
				attemptCountPW++;
		} while (attemptCountPW < 3);
		return false;
	}
	
	// Option 1: checks if enough space and add another item
	public static void option1() { 
		
		itemRemain = maxItems - HouseholdGoods.getGoodsCount();
		
		// adds objects to array and sees if user put valid number of pizzas
		if (itemRemain < 1) { 	
			System.out.print("Sorry, there's no more room for any other items!");
		}
		else {
				goods[maxItems-itemRemain] = createItem();
		}
	}
	
	// Method to create item
	public static HouseholdGoods createItem() {
		
		// Create object
		HouseholdGoods item = new HouseholdGoods();
		
		String description;

		System.out.print("\nWhat is the type? (Electronics/Appliance/Furniture): ");
		item.setType(keyboard.next().toLowerCase());
		keyboard.nextLine();
		System.out.print("\nWhat is the description?: ");
		description = keyboard.nextLine();
		item.setDescription(description);
		
		System.out.print("\nWhat's the price? (riksdaler skilling runstycken) ");
		item.setPrice(keyboard.nextInt(), keyboard.nextInt(), keyboard.nextInt());
		
		return item;	
	}
		
	// Option 2: Update a certain item in the array
	public static void option2() {
		int nbItemUpdate;
		
		do {
			System.out.print("Which item number do you want to update? ");
			nbItemUpdate = keyboard.nextInt()-1;
			
			if ((nbItemUpdate > goods.length) || (goods[nbItemUpdate] == null)) {
				System.out.print("Sorry, there is no item there!\nDo you want to enter another item number or quit this operation and go back to main menu? (enter/quit): ");
				String enterOrQuit = keyboard.next().toLowerCase();
				if (enterOrQuit.equals("quit")) {
					return;
				}
			}
		} while (nbItemUpdate > goods.length);	
		
		int option2Menu = 0;						// variable for user input for menu below
		
		do {
			
			//Displaying item number + current information
			itemDetails(nbItemUpdate);
			
			// Menu to switch information on the order
			System.out.println("\nWhat would you like to change?");
			System.out.println("\t1. Type");
			System.out.println("\t2. Description");
			System.out.println("\t3. Price");
			System.out.println("\t4. Quit");

			
			// validates choice is between 1-4
			do {
			System.out.print("Please enter your choice > ");
			option2Menu = keyboard.nextInt();
			keyboard.nextLine();
			} while (option2Menu < 1 || option2Menu > 4);
					
			switch (option2Menu) {
			
				case 1:
					System.out.print("Enter the new type (Electronics/Appliance/Furniture): ");
					goods[nbItemUpdate].setType(keyboard.next().toLowerCase());
					continue;
				case 2:
					System.out.print("Enter the new Description: ");
					goods[nbItemUpdate].setDescription(keyboard.nextLine());
					continue;
				case 3:
					System.out.print("\nWhat's the price? (riksdaler skilling runstycken ");
					goods[nbItemUpdate].setPrice(keyboard.nextInt(), keyboard.nextInt(), keyboard.nextInt());
					continue;
				case 4:
					break;
			}
		} while (option2Menu != 4);
	}
	
	//option 3: prompt user for the type of item she is interested in and displays information for all items of that size
	public static void option3() {
		
		boolean flag = false;
		String typeWanted;
		
		do {
			System.out.print("What item type are you interested in seeing displayed?: ");
			typeWanted = keyboard.next().toLowerCase();
			if (typeWanted.equals("electronics") || typeWanted.equals("appliance") || typeWanted.equals("furniture"))
				flag = true;
		} while(!flag);
		
		for (int i=0;i<goods.length;i++) {
			if (goods[i] == null)
				break;
			else if (goods[i].getType().equals(typeWanted)) {
				itemDetails(i);
				System.out.println("");
			}
		}
	}
	
	//option 4: prompt user for the type of item she is interested in and displays information for all items of that size
	public static void option4() {
		
		System.out.print("What item price are you interested in seeing displayed? (Riksdaler Skilling Runstycken): ");
		OldSwedishCurrency priceWanted = new OldSwedishCurrency(keyboard.nextInt(), keyboard.nextInt(), keyboard.nextInt());
		
		for (int i=0;i<goods.length;i++) {
			if (goods[i] == null)
				break;
			else if (goods[i].getPrice().compareTo(priceWanted) == -1) { // POTENTIAL || WITH EQUALS
				itemDetails(i);
				System.out.println();
			}
		}
	}
	
	//option 5: prompts user to choose what she wants displayed from a menu of assorted details
	public static void option5() {
		
		int option3Menu;	// variable to choose a menu item
		
		do {
			// Menu to see information about the orders
			System.out.println("\nWhat information would you like?");
			System.out.println("\t1. Cost and details of cheapest item");
			System.out.println("\t2. Cost and detauls of most costly item");
			System.out.println("\t3. Number of items of each type");
			System.out.println("\t4. Average cost of items in inventory");
			System.out.println("\t5. Quit");
			
			// validates choice is between 1-5
			do {
				System.out.print("Enter your choice > ");
				option3Menu = keyboard.nextInt();
			} while (option3Menu < 1 || option3Menu > 5);
			
			switch (option3Menu) {
				
				case 1:
					itemDetails(lowestPrice());
					continue;
				case 2:
					itemDetails(highestPrice());
					continue;
				case 3:
					System.out.println("\nThe number of items: " + numberOfItemsOfSize());
					continue;
				case 4:
					System.out.print("\nThe average cost of items in inventory is: " + averageCost());	
					continue;
				case 5:
					break;
				}
		} while (option3Menu != 5);
	}
	
	//step 3: checks average cost of items in inventory
	public static String averageCost() {
		double sumR = 0;
		double sumS = 0;
		double sumRu = 0;
		int lastIndex = 0;
		
		for (int i = 0; i < goods.length; i++) {
			if (goods[i] == null) {
				lastIndex = (i-1);
				break;
			}
			else {
				sumR += goods[i].getPrice().getRiksdaler();
				sumS += goods[i].getPrice().getSkilling();
				sumRu += goods[i].getPrice().getRunstycken();
			}
		}
		
		return ((sumR/lastIndex) + " riksdaler, " + (sumS/lastIndex) + " skilling, " + (sumRu/lastIndex) + " runstycken.");
	}
	
	// step 4: finds and returns lowest priced item
	public static int lowestPrice() {
		int lowestIndex = 0;
		
		for (int i = 0; i < goods.length; i++) {
			if (goods[i] == null)
				break;
			else if (goods[i].getPrice().compareTo(goods[lowestIndex].getPrice()) == -1) {
				lowestIndex = i;
			}
		}
		return lowestIndex;
	}
	
	// step 5: finds and returns the index of the item with the highest price in the array
	public static int highestPrice() {
		int highestIndex = 0;
		
		for (int i = 0; i < goods.length; i++) {
			if (goods[i] == null)
				break;
			else if (goods[i].getPrice().compareTo(goods[highestIndex].getPrice()) == 1) {
				highestIndex = i;
			}
		}
		return highestIndex;
	}
	
	//step 6: returns the number of items of each size
	public static String numberOfItemsOfSize() {
		int nbElec = 0;
		int nbApp = 0;
		int nbFurn = 0;
		
		for (int i = 0; i < goods.length; i++) {
			if (goods[i] == null)
				break;
			if (goods[i].getType().toLowerCase().equals("electronics"))
				nbElec++;
			if (goods[i].getType().toLowerCase().equals("electronics"))
				nbApp++;
			if (goods[i].getType().toLowerCase().equals("electronics"))
				nbFurn++;
		}
		return (nbElec + " electronics, " + nbApp + " appliances, " + nbFurn + " furniture.");
	}
	
	//displaying item number + current information
	public static void itemDetails(int index) {
		System.out.println("\tItem #: " + index);
		System.out.println("Type: " + goods[index].getType());
		System.out.println("Description: " + goods[index].getDescription());
		System.out.println("Price: " + goods[index].getPrice());
	}
	
	// option 6: displays a closing method and ends the driver
	public static void option6() {
		System.out.print("Now exiting -- Have a nice day!");
		System.exit(0);
	}
}
