package ua.clamor1s.task911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.clamor1s.task911.dao.CardDao;
import ua.clamor1s.task911.model.Card;

import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao dao;


    @Override
    public void saveCard(Card card) {
        dao.saveCard(card);
    }

    @Transactional(readOnly = true)
    @Override
    public Card getCard(int id) {
        return dao.findCardById(id);
    }

    @Override
    public void updateCard(Card card) {
        dao.updateCard(card);
    }

    @Override
    public void deleteCard(int cardId) {
        dao.deleteCard(cardId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Card> listAll() {
        return dao.listAll();
    }
}
