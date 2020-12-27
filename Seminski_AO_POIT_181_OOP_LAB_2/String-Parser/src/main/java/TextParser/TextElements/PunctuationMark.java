package TextParser.TextElements;

public class PunctuationMark extends SentenceComponent {
    private char symbol;
    private boolean isOpening;
    private boolean isOther;
    public PunctuationMark(char symbol,boolean pair,boolean isOther){
        this.symbol=symbol;
        this.isOpening=pair;
        this.isOther=isOther;
    }
    public char getSymbol(){
        return  symbol;
    }

    public boolean getIsOpening() {
        return isOpening;
    }

    public boolean getIsOther() {
        return isOther;
    }

    @Override
    public void outputTextComponent() {
        System.out.print(symbol);
    }

    public boolean isSpecialSymbol(){
        if(symbol=='\t' || symbol=='\n')
            return  true;
        return false;
    }
}
