package ua.clamor1s.task911.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.clamor1s.task911.dto.*;
import ua.clamor1s.task911.model.Card;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardDao {

    @Autowired
    private EntityManager entityManager;

    public CardDetailsDto findCardById(int id) {
        Card card = entityManager.find(Card.class, id);
        return CardDetailsDto.builder()
                .cardId(card.getCardId())
                .name(card.getName())
                .surname(card.getSurname())
                .code(card.getCode())
                .cvv(card.getCvv())
                .creationDate(card.getCreationDate())
                .build();
    }


    public CardAllDetailsDto listAll() {
        return new CardAllDetailsDto(entityManager.createQuery("SELECT c from Card c", Card.class).getResultList());
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

    public CardAllInfoDto search(CardQueryDto query) {
        List<Card> cards = entityManager.createQuery("SELECT c from Card c WHERE " +
                " (:name IS NULL OR c.name=:name) AND (:surname IS NULL OR c.surname=:surname) AND" +
                " (:code IS NULL OR c.code=:code) AND (:cvv IS NULL OR c.cvv=:cvv) AND" +
                " (:date IS NULL OR c.creationDate=:date)", Card.class)
                .setParameter("name", query.getName())
                .setParameter("surname", query.getSurname())
                .setParameter("code", query.getCode())
                .setParameter("cvv", query.getCvv())
                .setParameter("date", query.getCreationDate())
                .getResultList();
        return new CardAllInfoDto(listCard2ListInfo(cards));
    }

    private List<CardInfoDto> listCard2ListInfo(List<Card> cards) {
        List<CardInfoDto> dtos = new ArrayList<>();
        for (Card card : cards) {
            dtos.add(card2InfoDto(card));
        }
        return dtos;
    }

    private CardInfoDto card2InfoDto(Card card) {
        return CardInfoDto.builder()
                .cardId(card.getCardId())
                .name(card.getName())
                .surname(card.getSurname())
                .build();
    }

}
