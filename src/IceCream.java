public class IceCream {

	private double toppingPrice;
	private double flavorPrice;
	private String topping;
	private String flavor;
	private double totalPrice;

	/**
	 * Constructor. Each ice cream must have exactly one topping and one flavor
	 * selected
	 * 
	 * @param topping
	 * @param toppingPrice
	 * @param flavor
	 * @param flavorPrice
	 */
	public IceCream(String flavor, double flavorPrice, String topping, double toppingPrice) {
		this.flavor = flavor;
		this.flavorPrice = flavorPrice;
		this.topping = topping;
		this.toppingPrice = toppingPrice;
		totalPrice = toppingPrice + flavorPrice;
	}

	public double getToppingPrice() {
		return toppingPrice;
	}

	public double getFlavorPrice() {
		return flavorPrice;
	}

	public String getTopping() {
		return topping;
	}

	public String getFlavor() {
		return flavor;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

}
