package ch.bbbaden.in20a.epicgamer.buurechrieg.cards;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardSuite;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardType;
import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.Language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Language> {

    private List<Card<T>> cards;

    public Deck(CardSuite<T>[] suites, CardType<T>[] types){
        fillDeck(suites, types);
    }

    private void fillDeck(CardSuite<T>[] suites, CardType<T>[] types){
        cards = new ArrayList<>();
        for(CardSuite<T> suite : suites){
            for(CardType<T> type : types){
                cards.add(new Card<T>(suite, type));
            }
        }
    }

    public int getNumberOfCards(){
        return cards.size();
    }

    public List<Card<T>> giveCards(int amount){
        shuffleCards();

        List<Card<T>> cardsToGive = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            if(getNumberOfCards() >= 0){
                cardsToGive.add(cards.remove(0));
            }
        }
        return cardsToGive;
    }

    private void shuffleCards(){
        Collections.shuffle(cards);
    }
}
