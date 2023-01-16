package ua.clamor1s.task911.service;

import ua.clamor1s.task911.model.Card;

import java.util.List;

public interface CardService {

    void saveCard(Card card);

    Card getCard(int id);

    void updateCard(Card card);

    void deleteCard(int cardId);

    List<Card> listAll();

}
