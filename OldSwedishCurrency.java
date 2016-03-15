/**
 * This class implements an old Swedish currency and allows it to be normalized.
 * @author Francesco Valela
 * @version 1.0
 */
public class OldSwedishCurrency {

	//Instance Variables
	private int riksdaler;
	private int skilling;
	private int runstycken;
	
	/**
	 * This method creates an object of type OldSwedishCurrency.
	 */
	public OldSwedishCurrency () {
		riksdaler = 0;
		skilling = 0;
		runstycken = 0;
	}
	/**
	 * This method constructs a new object with set values for each parameter, while also making sure it isn't negative. 
	 * @param riksdaler Initial value for Riksdaler
	 * @param skilling Initial value for Skilling
	 * @param runstycken Initial value for Runstycken
	 */
	public OldSwedishCurrency (int riksdaler, int skilling, int runstycken) {
		if (riksdaler < 0 || skilling < 0 || runstycken < 0) {
			riksdaler = 0;
			skilling = 0;
			runstycken = 0;
		}
		else { 
			this.riksdaler = riksdaler;
			this.skilling = skilling;
			this.runstycken = runstycken;
			normalize();
		}
	}
	/**
	 * This method creates a clone of an already declared OldSwedishCurrency object.
	 * @param osc Initial values of Riksdaler, Skilling and Runstycken
	 */
	public OldSwedishCurrency (OldSwedishCurrency osc) {
		riksdaler = osc.riksdaler;
		skilling = osc.skilling;
		runstycken = osc.runstycken;
	}
	
	/**
	 * This method Optimizes the object to the most amount of Riksdalers and Skillings as possible.
	 */
	private void normalize () {
		while (runstycken >= 16) {
			runstycken -= 16;
			skilling++;
		}
		while (skilling >= 48) {
			skilling -= 48;
			riksdaler++;
		}
	}
	
	/**
	 * This returns the current value of Riksdaler.
	 * @return This objects value of Riksdaler
	 */
	public int getRiksdaler () {
		return riksdaler;
	}
	/**
	 * This returns the current value of Skilling.
	 * @return This objects value of Skilling
	 */
	public int getSkilling () {
		return skilling;
	}
	/**
	 * This returns the current value of Runstycken.
	 * @return This objects value of Runstycken
	 */
	public int getRunstycken () {
		return runstycken;
	}
	
	/**
	 * This sets the value of Riksdaler.
	 * @param riksdaler The new value of Riksdaler
	 */
	public void setRiksdaler (int riksdaler) {
		this.riksdaler = riksdaler;
		normalize();
	}
	/**
	 * This sets the value of Skilling.
	 * @param skilling The new value of Skilling
	 */
	public void setSkilling (int skilling) {
		this.skilling = skilling;
		normalize();
	}
	/**
	 * This sets the value of Runstycken.
	 * @param runstycken The new value of Runstycken
	 */
	public void setRunstycken (int runstycken) {
		this.runstycken = runstycken;
		normalize();
	}
	
	/**
	 * This method overrides the equals method and checks to see if the passing and calling object are equal.
	 * @param osc The passing object you want to check equality to the calling object
	 * @return If both objects are equal
	 */
	public boolean equals (OldSwedishCurrency osc) {
		return (riksdaler == osc.riksdaler && skilling == osc.skilling && runstycken == osc.runstycken);
	}
	
	/**
	 * This method overrides the compareTo method and checks to see if the passing object is higher, lower or equal to the calling object.
	 * @param osc The passing object you want to check against calling object.
	 * @return An int showing if the passing object is higher, lower or equal to calling object
	 */
	public int compareTo (OldSwedishCurrency osc) {
		if ((riksdaler > osc.riksdaler) 
			|| (riksdaler == osc.riksdaler && skilling > osc.skilling)
			|| (riksdaler == osc.riksdaler && skilling == osc.skilling &&runstycken > osc.runstycken))
			return 1;
		else if (riksdaler == osc.riksdaler && skilling == osc.skilling && runstycken == osc.runstycken)
			return 0;
		else
			return -1;
	}
	
	/**
	 * This method overrides the toString method and puts the calling object's values in a string.
	 */
	public String toString () {
		return (riksdaler + " riksdaler, " + skilling + " skilling, " + runstycken + " runstycken.");
	}
	
	/**
	 * This method creates an object where it's values are the sum of the calling and passing object.
	 * @param osc The passing object you wish to add to the calling object
	 * @return A new object with values summed by the passing and calling object
	 */
	public OldSwedishCurrency add (OldSwedishCurrency osc) {
		OldSwedishCurrency newOSC = new OldSwedishCurrency((riksdaler + osc.riksdaler), (skilling + osc.skilling), (runstycken + osc.runstycken));
		return newOSC;
	}
	
	/**
	 * This method creates an object where it's values are the difference of the passing object from the calling object.
	 * @param osc The passing object you wish to subtract from the calling object
	 * @return A new object with values being the difference between the passing object and calling object
	 */
	public OldSwedishCurrency subtract (OldSwedishCurrency osc) {
		if (riksdaler < osc.riksdaler || skilling < osc.skilling || runstycken < osc.runstycken) {
			OldSwedishCurrency newOSC = new OldSwedishCurrency(0, 0, 0);
			return newOSC;
		}
		else {
			OldSwedishCurrency newOSC = new OldSwedishCurrency(riksdaler - osc.riksdaler, skilling - osc.skilling, runstycken - osc.runstycken);
			return newOSC;
		}
	}
	
	/**
	 * This method converts all an objects values into the Runstycken currency. 
	 * @return An object with only Runstycken values
	 */
	public int convertToRunstycken () {
		skilling += (riksdaler*48);
		riksdaler = 0;
		runstycken += (skilling*16);
		skilling = 0;
		return runstycken;
	}
	
	/**
	 * This method creates a new object with a set Runstycken currency and normalizes it.
	 * @param runstycken The initial amount of Runstycken currency
	 * @return A new object with a normalized currency
	 */
	public static OldSwedishCurrency convertFromRunstycken (int runstycken) {
		OldSwedishCurrency osc = new OldSwedishCurrency(0, 0, runstycken);
		osc.normalize();
		return osc;
	}
}