package ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.swiss;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardSuite;

public enum SwissCardSuites implements CardSuite<SwissLanguage> {

    ROSE("Rose"),
    EICHLE("Eichle"),
    SCHAELLE("Schälle"),
    SCHILTE("Schilte");

    private final String m_name;

    private SwissCardSuites(String name){
        m_name = name;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public CardSuite[] getAll(){
        return SwissCardSuites.values();
    }
}
