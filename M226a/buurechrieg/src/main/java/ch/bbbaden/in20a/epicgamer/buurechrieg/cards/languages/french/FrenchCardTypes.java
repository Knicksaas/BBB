package ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.french;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardType;

public enum FrenchCardTypes implements CardType<FrenchLanguage> {

    ASS("Ass", 8),
    KOENIG("König", 7),
    DAME("Dame", 6),
    BUBE("Bube", 5),
    ZEHN("Zehn", 4),
    NEUN("Neun", 3),
    ACHT("Acht", 2),
    SIEBEN("Sieben", 1),
    SECHS("Sechs", 0);

    private final String m_name;
    private final int m_winValue;

    FrenchCardTypes(String name, int winValue){
        m_name = name;
        m_winValue = winValue;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public int getWinValue() {
        return m_winValue;
    }

    @Override
    public CardType[] getAll() {
        return FrenchCardTypes.values();
    }
}
