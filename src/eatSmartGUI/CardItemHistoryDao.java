package eatSmartGUI;

import java.util.List;

public interface CardItemHistoryDao {
	public List<CardItemHistory> getAllCardIds();
    public CardItemHistory getCardId(int cardId);
    public void insertCard(CardItemHistory cardInfo);
    public void updateCard(CardItemHistory cardInfo);
    public void deleteCard(CardItemHistory cardInfo);
}
