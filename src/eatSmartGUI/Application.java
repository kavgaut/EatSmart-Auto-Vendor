package eatSmartGUI;

import javax.swing.JFrame;

public class Application{
	
	public static MainPageGUI mainFrame;
	
	/**
	 * This section is a simulation of a USER choosing to approach a VM to use it
	 */
		public static void main(String args[]) {
			String VMID = "VM3";
			VMDirector director = new VMDirector();
			VMBuilder builder = new FoodVMBuilder(VMID);
			VM vm = director.constructVM(builder);
			
			//Create an instance of the MainGUI page
			mainFrame = new MainPageGUI(vm);
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("******************" + vm.getVMId() + "***********" + vm.getFoodItemSection().getItemIdList(VMID).toString());
		
		
		/**
		 * This section is a simulation of The EatSmart Company management installing Monitoring stations 
		 * and adding vending machines to each monitoring station's purview
		 */
			EatSmartManagement manage = new EatSmartManagement();
		
			/**
			 * Instantiation of all the Jpanel pages required Member Non member and Admin
			 * These are the concrete collegues objects in the mediator pattern
			 */
			
			
			
			
		}
}