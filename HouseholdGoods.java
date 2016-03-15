// ------------------------------------------------------- 
// Assignment 1
// Written by: Francesco Valela 26669883
// For COMP 249 Section S – Winter 2016
// --------------------------------------------------------

/**
 * 
 * @author Francesco Valela
 * @version 1.0
 */
public class HouseholdGoods {
	//Instance variables
	private static int goodsCount;
	private String type;
	private String description;
	private OldSwedishCurrency price;
	
	/**
	 * This method creates an object of type HouseholdGoods.
	 */
	public HouseholdGoods() {
		description = null;
		price = new OldSwedishCurrency();
		goodsCount++;
	}
	
	/**
	 * This method creates an object of type HouseholdGoods with set values for each parameter.
	 * @param type Initial type
	 * @param description Initial description of item
	 * @param price Initial price which is a clone object of type OldSwedishCurrency
	 */
	public HouseholdGoods(String type, String description, OldSwedishCurrency price) {
		this.type = type;
		this.description = description;
		this.price = new OldSwedishCurrency(price);
		goodsCount++;
	}
	
	/**
	 * This method allows access to the type of calling object.
	 * @return The object's type
	 */
	public String getType() {
		return type;
	}
	/**
	 * This method sets the value of the calling object's type.
	 * @param type The new value of type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * This method allows access to the description of calling object.
	 * @return The object's description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * This method sets the value of the calling object's description.
	 * @param description The new value of description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * This method allows access to the price of calling object.
	 * @return The object price of class OldSwedishCurrency
	 */
	public OldSwedishCurrency getPrice() {
		return price;
	}
	/**
	 * This method sets the value of each variables associated with the object price of class OldSwedishCurrency.
	 * @param riksdaler Initial value of Riksdaler
	 * @param skilling Initial value of Skilling
	 * @param runstycken Initial value of Runstycken
	 */
	public void setPrice(int riksdaler, int skilling, int runstycken) {
		price.setRiksdaler(riksdaler);
		price.setSkilling(skilling);
		price.setRunstycken(runstycken);
	}	
	/**
	 * This method allows access to the static variable goodsCount.
	 * @return The value of goodsCount
	 */
	public static int getGoodsCount() {
		return goodsCount;
	}
	
	/**
	 * This method overrides the equals method and compares two objects to see if their values are equal.
	 * @param hhg The object being compared to the calling object
	 * @return If they are equal or not
	 */
	public boolean equals (HouseholdGoods hhg) {
		return (type.equals(hhg.type) && description.equals(hhg.description) && price.equals(hhg.price));
	}
	
	/**
	 * This method overrides the toString method and allows the calling object's values to be put into a string.
	 * @return The calling object's values in a string format
	 */
	public String toString () {
		return "type: " + type + "\nDescription: " + description + "\nPrice: " + price;
	}
}

