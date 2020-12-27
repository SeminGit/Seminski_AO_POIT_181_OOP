package TextParser.TextElements;


public class Word extends SentenceComponent{
    private String word;

    public Word(String word){
        this.word=word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public void outputTextComponent() {
        System.out.print(word);
    }
}
