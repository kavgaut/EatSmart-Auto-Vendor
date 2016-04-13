package eatSmartGUI;

public class MemberTypeFactory {
	
 public MemberType createMemberType(String userType){
 
	 MemberType memberType = null;
      if(userType.equalsIgnoreCase("Member")){
    	 memberType = new Member();
      } else if(userType.equalsIgnoreCase("Non-Member")){
    	 memberType = new NonMember();
      }
      return memberType;
   }
}
