package eatSmartGUI;
/** The director which constructs a vm by calling
 * all the build methods for building the various sections of a vm
 * @author Ramya Bangaluru Gopalakrishna
 */
public class VMDirector{
	public VM constructVM(VMBuilder builder){
		builder.buildCardSlot();
		builder.buildCashSlot();
		builder.buildCashCoinDispense();
		builder.buildItemSection();
		builder.buildOperatorDisplay();
		builder.buildProductDispense();
		builder.buildUserDisplay();
		return builder.getVM();
		}
}