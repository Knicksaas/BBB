package ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.french;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardSuite;

public enum FrenchCardSuites implements CardSuite<FrenchLanguage> {

    HERZ("Herz"),
    ECKE("Ecke"),
    KREUZ("Kreuz"),
    SCHAUFEL("Schaufel");

    private final String m_name;

    private FrenchCardSuites(String name){
        m_name = name;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public CardSuite<FrenchLanguage>[] getAll() {
        return FrenchCardSuites.values();
    }
}
