package calculator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiscountCalculatorController {

	private static final CategoryManager categoryManager = new CategoryManager();

	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		int numItems=getNumberOfItems(scanner);
		List<Item> items = new ArrayList<>();
		for(int i=0; i<numItems; i++) {
			System.out.println("Enter item name for Product: "+(i+1));
			boolean validInput=false;
			Item item=null;
			while(!validInput) {
				item = getItemDetails(scanner);
				if (item != null) {
					validInput = true;
				} else {
					System.out.println("Please re-enter the details for Product " + (i + 1) + ":");
				}
			}
			items.add(item);
		}
		generateReceipt(items);
	}

	//FIRST line in the input will be the number of items in the shopping basket.
	private static int getNumberOfItems(Scanner scanner) {
		int numOfItems=0;
		while(true) {
			try {
				System.out.println("Enter the number of items shopped: ");
				numOfItems= Integer.parseInt(scanner.nextLine());
				//Check whether number of items entered is negative or not valid
				if(numOfItems<0) {
					throw new NumberFormatException();
				}
				break;
			}catch(NumberFormatException e){
				System.out.println("Please enter a valid integer for the number of items.");
			}
		}
		return numOfItems;
	}

	//Getting quantity, name of the product, price, and check for clearance
	private static Item getItemDetails(Scanner scanner) {
		while(true) {
			try {
				System.out.println("Enter the Item details in the format of (Example: '1 book at 14.49'): ");
				System.out.println("Please include clearance in the item name if the product is in clearance category");
				String input = scanner.nextLine();

				//Splitting strings to get Quantity
				int qntyIndex = input.indexOf(' ');
				if(qntyIndex==-1) {
					System.out.println("Invalid input Format");
				}
				int quantity=Integer.parseInt(input.substring(0, qntyIndex));
				if (quantity <= 0) {
					System.out.println("Enter a valid quantity");
					return null;
				}

				//Splitting strings to get Item name
				String remaining = input.substring(qntyIndex + 1);
				int nameIndex=remaining.indexOf(" at ");
				if(nameIndex==-1) {
					System.out.println("Invalid input Format");
					return null;
				}
				String name = remaining.substring(0, nameIndex).trim();

				//Splitting strings to get Price 
				double price = Double.parseDouble(remaining.substring(nameIndex + 4).trim());
				if (price < 0) {
					System.out.println("Enter a valid price");
					return null;
				}

				//Checking whether shopped product is in Clearance category
				boolean isClearance = name.toLowerCase().contains("clearance");

				return new Item(name, price, isClearance, quantity);

			}catch(NumberFormatException e){
				System.out.println("Please enter valid numbers for quantity and price.");
			}
		}
	}

	//Generating receipt
	private static void generateReceipt(List<Item> items){
		System.out.println();
		System.out.println("!!!-----------------------Your Bill-----------------------!!!");
		double totalCost=0.0;
		double totalSavings=0.0;
		for(Item item: items) {
			String category = categoryManager.getCategory(item.getName());
			double discountRate = categoryManager.getDiscountRate(category, item.isClearance());
			double discountedPrice = item.getPrice() * (1 - discountRate);
			totalCost += discountedPrice * item.getQuantity();
			totalSavings += (item.getPrice() - discountedPrice) * item.getQuantity();
			String output = item.getQuantity() + " " + item.getName() + " at " + String.format("%.2f", discountedPrice);
			System.out.println(output);
		}
		System.out.printf("Total cost: %.2f%n", totalCost);
		System.out.printf("You saved: %.2f%n", totalSavings);
		System.out.println("!!!-----------------------Thank You for Shopping With Us-----------------------!!!");
	}
}