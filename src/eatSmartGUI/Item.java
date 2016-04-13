package eatSmartGUI;
import java.util.Date;

public abstract class Item implements Cloneable{
	private int itemCode;
	private String itemName;
	private String itemType;
	private int calValue;
	private String ingrdntInfo;
	private String sugarVal;
	private String carbVal;
	private String cholestrolVal;
	private String totFatVal;
	private String proteinVal;
	private String glutenFree;
	private String lowFat;
	private String lowSugar;
	private double itemPrice;
	private Date createdDate;
	
	/** To set the item code
	 * @param itemId: item code
	 */
	public void setItemCode(int itemId){
		this.itemCode = itemId;
	}
	
	/** To get the item code
	 * @return: item code
	 */
	public int getItemCode(){
		return itemCode;
	}
	
	/** To the item name
	 * @param itemName: name of the item
	 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	
	/** to get the item name
	 * @return: item name
	 */
	public String getItemName(){
		return itemName;
	}
	
	/** to set the item type
	 * @param itemType: type of the item
	 */
	public void setItemType(String itemType){
		this.itemType = itemType;
	}
	
	/** to get the item type
	 * @return: item type
	 */
	public String getItemType(){
		return itemType;
	}
	
	/** To set the caorie information of the itmm
	 * @param calValue: calorie value
	 */
	public void setCalValue(int calValue){
		this.calValue = calValue;
	}
	
	/** To get the calorie information of an item
	 * @return: calorie value
	 */
	public int getCalValue(){
		return calValue;
	}
	
	/** To set the ingredient information of an item
	 * @param ingInfo: ingredient information
	 */
	public void setIngredientInfo(String ingInfo){
		this.ingrdntInfo = ingInfo;
	}
	
	/** To get the ingredient information of the item
	 * @return: ingredient information
	 */
	public String getIngredientInfo(){
		return ingrdntInfo;
	}
	
	/** To set the sugar content of an item
	 * @param sugarVal: sugar content 
	 */
	public void setSugarValue(String sugarVal){
		this.sugarVal= sugarVal;
	}
	
	/** To get the sugar content of an item
	 * @return: sugar content
	 */
	public String getSugarVal(){
		return sugarVal;
	}
	
	/** to set the carbohydrate content of an item
	 * @param carbVal: carbohydrate content
	 */
	public void setCarbohydrateValue(String carbVal){
		this.carbVal= carbVal;
	}
	
	/** to get the carbohydrate content of an item
	 * @return: carbohydrate content
	 */
	public String getCarbohydrateValue(){
		return carbVal;
	}
	
	/** To set the cholesterol content of an item
	 * @param cholesVal: cholesterol content
	 */
	public void setCholestrolValue(String cholesVal){
		this.cholestrolVal= cholesVal;
	}
	
	/** To get the cholesterol content of an item
	 * @return: cholesterol content
	 */
	public String getCholestrolValue(){
		return cholestrolVal;
	}
	
	/** To set the total fat content of an item
	 * @param totFatVal: fat conent
	 */
	public void setTotalFatValue(String totFatVal){
		this.totFatVal= totFatVal;
	}
	
	/** To get the total fat content of an item
	 * @return: fatcontent
	 */
	public String getTotalFatValue(){
		return totFatVal;
	}
	
	/** To set the protein content of an item
	 * @param proteinVal: protein content
	 */
	public void setProteinValue(String proteinVal){
		this.proteinVal= proteinVal;
	}
	
	/** To get the protein content of an item
	 * @return: protein content
	 */
	public String getProteinValue(){
		return proteinVal;
	}
	
	/** To set the glutenfree value of an item
	 * @param glutenFreeVal: gluten free value
	 */
	public void setGlutenFree(String glutenFreeVal){
		this.glutenFree = glutenFreeVal;
	}
	
	/** To get the gluten free value of an item
	 * @return: gluten free value
	 */
	public String getGlutenFree(){
		return glutenFree;
	}
	
	/** To set the lowsugar value of an item
	 * @param lowSugar: lowsugar value
	 */
	public void setLowSugar(String lowSugar){
		this.lowSugar = lowSugar;
	}
	
	/** To get the low sugar value of an item 
	 * @return: low sugar value
	 */
	public String getLowSugar(){
		return lowSugar;
	}
	

	/** To set the lowfat value of an item
	 * @param lowSugar: lowfat value
	 */
	public void setLowFat(String lowFat){
		this.lowFat = lowFat;
	}
	
	/** To get the lowfat value of an item 
	 * @return: lowfat value
	 */
	public String getLowFat(){
		return lowFat;
	}
	
	/** To set the price value of an item
	 * @param itemPrice: prive value
	 */
	public void setItemPrice(double itemPrice){
		this.itemPrice = itemPrice;
	}
	
	/** To get the price value of an item
	 * @return: prive value
	 */
	public double getItemPrice(){
		return itemPrice;
	}
	
	/** To set the date when an item was created
	 * @param createdDate: item creation date
	 */
	public void setItemCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	/** To get the date when an item was created
	 * @return: item creation date
	 */
	public Date getItemCreatedDate(){
		return createdDate;
	}
	
	public Object clone() {
		Object clone =  null;
		try{
			clone = super.clone();
			
		}catch(CloneNotSupportedException c){
			c.getMessage();
		}
		return clone;
	}
	
	public void showItem(){
		
	}
}