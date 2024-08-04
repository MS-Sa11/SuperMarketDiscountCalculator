package calculator.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.CategoryManager;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryManagerTest {
	private CategoryManager categoryManager;

	@BeforeEach
	void setUp() {
		categoryManager = new CategoryManager();
	}

	private double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	// Test case for category where the items are categorised
	@Test
	void testGetCategory_ValidItem() {
		assertEquals("books", categoryManager.getCategory("book"));
		assertEquals("food", categoryManager.getCategory("chocolates"));
		assertEquals("drinks", categoryManager.getCategory("wine"));
		assertEquals("clothes", categoryManager.getCategory("shirt"));
	}

	// Test case for category where the items are not categorised
	@Test
	void testGetCategory_InvalidItem() {
		assertEquals("others", categoryManager.getCategory("masala"));
		assertEquals("others", categoryManager.getCategory("perfume"));
	}

	// Test for case-insensitive matching
	@Test
	void testGetCategory_CaseInsensitive() {
		assertEquals("books", categoryManager.getCategory("BoOk"));
		assertEquals("food", categoryManager.getCategory("ChocoLaTeS"));
	}

	// Test for discount rates for non-clearance items
	@Test
	void testGetDiscountRate_NonClearance() {
		assertEquals(round(0.05), round(categoryManager.getDiscountRate("books", false)));
		assertEquals(round(0.05), round(categoryManager.getDiscountRate("food", false)));
		assertEquals(round(0.05), round(categoryManager.getDiscountRate("drinks", false)));
		assertEquals(round(0.20), round(categoryManager.getDiscountRate("clothes", false)));
		assertEquals(round(0.03), round(categoryManager.getDiscountRate("others", false)));
	}

	// Test for discount rates for clearance items
	// Calculations for clearance discounts:
	/*
	 * Books: 5% discount -> effective rate = 95% * 80% = 76% -> 1 - 0.76 = 0.24
	 */

	/*
	 * Food: 5% discount -> effective rate = 95% * 80% = 76% -> 1 - 0.76 = 0.24
	 */

	/*
	 * Drinks: 5% discount -> effective rate = 95% * 80% = 76% -> 1 - 0.76 = 0.24
	 */

	/*
	 * Clothes: 20% discount -> effective rate = 80% * 80% = 64% -> 1 - 0.64 = 0.36
	 */

	/*
	 * Others: 3% discount -> effective rate = 97% * 80% = 77.6% -> 1 - 0.776 = 0.22
	 */
	@Test
	void testGetDiscountRate_Clearance() {
		assertEquals(round(0.24), round(categoryManager.getDiscountRate("books", true)));
		assertEquals(round(0.24), round(categoryManager.getDiscountRate("food", true)));
		assertEquals(round(0.24), round(categoryManager.getDiscountRate("drinks", true)));
		assertEquals(round(0.36), round(categoryManager.getDiscountRate("clothes", true)));
		assertEquals(round(0.22), round(categoryManager.getDiscountRate("others", true)));
	}
}
