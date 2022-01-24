package ch.bbbaden.in20a.epicgamer.buurechrieg;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> m_cards;

    public Player(){
        m_cards = new ArrayList<>();
    }

    public Player(List<Card> cards){
        m_cards = cards;
    }

    public Card getNextCard(){
        return m_cards.remove(0);
    }

    public void winCard(Card card){
        m_cards.add(card);
    }

    public void winCards(List<Card> cards){
        for (Card card : cards){
            winCard(card);
        }
        cards.clear();
    }

    public int getCardAmount(){
        return m_cards.size();
    }

    public boolean hasCards(){
        return !m_cards.isEmpty();
    }
}
