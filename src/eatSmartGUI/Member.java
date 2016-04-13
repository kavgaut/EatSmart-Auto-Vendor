package eatSmartGUI;

public class Member implements MemberType{

	public Card card;
	
	public Member(){
		card =  new Card();
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
