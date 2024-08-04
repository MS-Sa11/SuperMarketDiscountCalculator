package calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManager {

	private final Map<String, String> itemToCategoryMap;
	private final Map<String, Double> categoryDiscounts;

	public CategoryManager() {
		itemToCategoryMap = new HashMap<>();
		categoryDiscounts = new HashMap<>();

		// Initialize categories and their corresponding items.
		addCategory("books", List.of("book", "note"));
		addCategory("food", List.of("chocolates", "pizza", "Meals"));
		addCategory("drinks", List.of("wine", "cola", "fanta"));
		addCategory("clothes", List.of("shirt", "dress", "hoodie", "tshirt"));

		// Initialize category discounts
		categoryDiscounts.put("books", 0.05); // -Books5%
		categoryDiscounts.put("food", 0.05); // -Food 5%
		categoryDiscounts.put("drinks", 0.05); // -Drinks 5%
		categoryDiscounts.put("clothes", 0.20); // -Clothes 20%
		categoryDiscounts.put("others", 0.03); // -Other 3%
	}

	// Traverse the items and mapping the category for getting discounts
	private void addCategory(String category, List<String> items) {
		for (String item : items) {
			itemToCategoryMap.put(item.toLowerCase(), category);
		}
	}

	// Traverse the items and getting the category
	public String getCategory(String itemName) {
		for (String key : itemToCategoryMap.keySet()) {
			if (itemName.toLowerCase().contains(key)) {
				return itemToCategoryMap.get(key);
			}
		}
		return "others";
	}

	// Getting discount rate based on the category
	public double getDiscountRate(String category, boolean isClearance) {
		double discountRate = categoryDiscounts.getOrDefault(category, 0.03);
		if (isClearance) {
			double effDiscount = (1 - discountRate) * (1 - 0.20);
			return 1 - effDiscount;
		}
		return discountRate;
	}
}