package ua.clamor1s.task911.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.clamor1s.task911.model.Card;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CardDao {

    @Autowired
    private EntityManager entityManager;

    public Card findCardById(int id) {
        return entityManager.find(Card.class, id);
    }


    public List<Card> listAll() {
        return entityManager.createQuery("SELECT c from Card c", Card.class).getResultList();
    }

    public void saveCard(Card card) {
        entityManager.persist(card);
    }

    public void updateCard(Card card) {
        entityManager.merge(card);
    }

    public void deleteCard(int cardId) {
        int isSuccess = entityManager.createQuery("DELETE FROM Card c WHERE c.cardId=:id")
                .setParameter("id", cardId)
                .executeUpdate();
        if (isSuccess == 0) {
            throw new RuntimeException("Troubles with deleting.");
        }
    }

}
