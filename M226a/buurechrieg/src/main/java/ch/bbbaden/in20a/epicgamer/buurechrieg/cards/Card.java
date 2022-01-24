package ch.bbbaden.in20a.epicgamer.buurechrieg.cards;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardSuite;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardType;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.Language;

public class Card<T extends Language> {

    private CardSuite<T> m_suite;
    private CardType<T> m_type;

    public Card(CardSuite<T> suite, CardType<T> value){
        m_suite = suite;
        m_type = value;
    }

    @Override
    public String toString() {
        return m_suite.getName() + " " + m_type.getName();
    }

    public CardSuite<T> getSuite() {
        return m_suite;
    }

    public CardType<T> getType() {
        return m_type;
    }

    public int getWinValue(){
        return getType().getWinValue();
    }
}
