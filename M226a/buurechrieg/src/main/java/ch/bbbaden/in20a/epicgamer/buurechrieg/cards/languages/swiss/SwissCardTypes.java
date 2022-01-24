package ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.swiss;

import ch.bbbaden.in20a.epicgamer.buurechrieg.cards.languages.CardType;

public enum SwissCardTypes implements CardType<SwissLanguage> {

    ASS("Ass", 8),
    KOENIG("König", 7),
    OBER("Ober", 6),
    UNDER("Under", 5),
    BANNER("Banner", 4),
    NUENI("Nüni", 3),
    ACHTI("Achti", 2),
    SEBNI("Sebni", 1),
    SAECHSI("Sächsi", 0);

    private final String m_name;
    private final int m_winValue;

    private SwissCardTypes(String name, int value){
        m_name = name;
        m_winValue = value;
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
    public CardType[] getAll(){
        return SwissCardTypes.values();
    }
}
