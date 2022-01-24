package ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages;

public interface CardType<T extends Language> {

    public String getName();

    public int getWinValue();

    public CardType[] getAll();
}
