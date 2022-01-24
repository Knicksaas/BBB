package ch.bbbaden.in20a.epicgamer.buurechrieg;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.Card;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.Deck;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardLanguage;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.Language;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.swiss.SwissCardTypes;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.swiss.SwissCardSuites;
import ch.bbbaden.in20a.epicgamer.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buurechrieg {

    private final static Logger LOG = Logger.getLogger(Buurechrieg.class.getName());

    private List<Card> m_cardsOnTable;

    public void play(){
        m_cardsOnTable = new ArrayList<>();
        CsvWriter writer = new CsvWriter();
        Deck deck = getDeckByLanguageType(CardLanguage.SWISS);
        Player p1 = new Player(deck.giveCards(deck.getNumberOfCards()/2));
        Player p2 = new Player(deck.giveCards(deck.getNumberOfCards()));

        while (p1.hasCards() && p2.hasCards()){
            Card cardP1 = p1.getNextCard();
            Card cardP2 = p2.getNextCard();
            m_cardsOnTable.add(cardP1);
            m_cardsOnTable.add(cardP2);

            System.out.println("Player 1 (having " + p1.getCardAmount() + " cards) lays " + cardP1);
            System.out.println("Player 2 (having " + p2.getCardAmount() + " cards) lays " + cardP2);

            writer.writeLine(p1.getCardAmount() + ", " + p2.getCardAmount());

            int compare = compareCards(cardP1, cardP2);

            if(compare > 0){
                System.out.println("Player 1 wins " + m_cardsOnTable);
                p1.winCards(m_cardsOnTable);
            } else if(compare < 0){
                System.out.println("Player 2 wins " + m_cardsOnTable);
                p2.winCards(m_cardsOnTable);
            } else{
                System.out.println("Both are equal, continuing...");

                if(p1.hasCards() || p2.hasCards()){
                    continue;
                }

                cardP1 = p1.getNextCard();
                cardP2 = p2.getNextCard();
                m_cardsOnTable.add(cardP1);
                m_cardsOnTable.add(cardP2);

                System.out.println("Player 1 (having " + p1.getCardAmount() + " cards) has to lay a hidden card...");
                System.out.println("Player 2 (having " + p2.getCardAmount() + " cards) has to lay a hidden card...");

            }
        }
        if(!p1.hasCards() && !p2.hasCards()){
            System.out.println("Draw!");
        } else if(p1.hasCards()){
            System.out.println("Player 1 won");
        } else {
            System.out.println("Player 2 won");
        }
    }

    private Deck getDeckByLanguageType(CardLanguage language){
        switch (language){
            case SWISS -> {
                return new Deck(SwissCardSuites.values(), SwissCardTypes.values());
            }
        }
        return null;
    }

    /**
     * Compares two cards from the same language with each other
     *
     * @param cardA first card
     * @param cardB second card
     * @param <T> language type of the cards
     * @return +1 when the first card is higher, -1 when the second card is higher
     *          and 0, when both are equal
     */
    private <T extends Language> int compareCards(Card<T> cardA, Card<T> cardB){
        return Integer.compare(cardA.getWinValue(), cardB.getWinValue());
    }
}
