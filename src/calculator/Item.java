package calculator;

public class Item {
	private final String name;
	private final double price;
	private final boolean isClearance;
	private final int quantity;

	public Item(String name, double price, boolean isClearance, int quantity) {
		this.name = name;
		this.price = price;
		this.isClearance = isClearance;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean isClearance() {
		return isClearance;
	}

	public int getQuantity() {
		return quantity;
	}
}