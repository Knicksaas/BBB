package ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages;

public interface CardSuite<T extends Language> {

    public String getName();

    CardSuite<T>[] getAll();

}
