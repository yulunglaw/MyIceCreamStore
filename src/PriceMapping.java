import java.util.HashMap;

public class PriceMapping {

	private HashMap<String, Double> flavorMapping = new HashMap<>();
	private HashMap<String, Double> toppingMapping = new HashMap<>();

	/**
	 * This constructor will generate maps to map flavor and topping to the
	 * corresponding price
	 */
	public PriceMapping() {
		// Map to store all ice cream flavor and corresponding prices
		flavorMapping.put("Chocolate", 3.0);
		flavorMapping.put("Strawberry", 3.0);
		flavorMapping.put("Coffee", 3.0);
		// Map to store all the toppings and corresponding prices
		toppingMapping.put("Peanut", 3.0);
		toppingMapping.put("Cashew", 4.0);
		toppingMapping.put("Oreal", 5.0);
	}

	public HashMap<String, Double> getFlavorMapping() {
		return flavorMapping;
	}

	public HashMap<String, Double> getToppingMapping() {
		return toppingMapping;
	}

//	public void setFlavorMapping(String newFlavor, double newFlavorPrice) {
//		toppingMapping.put(newFlavor, newFlavorPrice);
//	}
//
//	public void setToppingMapping(String newTopping, double newToppingPrice) {
//		toppingMapping.put(newTopping, newToppingPrice);
//	}
}
