package eatSmartGUI;

public class NonMember implements MemberType{
	
	public Card card;
	
	public NonMember(){
		card = new Card();
	}
	
	@Override
	public void getMemberType() {
		
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public Card getCard() {
		return card;
	}

}
