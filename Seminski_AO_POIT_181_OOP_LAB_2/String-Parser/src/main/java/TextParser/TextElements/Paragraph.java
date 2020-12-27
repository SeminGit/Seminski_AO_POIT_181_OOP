package TextParser.TextElements;
import java.util.*;

public class Paragraph implements TextComponent {
    private List<Sentence> sentences=new ArrayList<Sentence>();
    public void outputTextComponent() {
        for(Sentence sentence:sentences){
            sentence.outputTextComponent();
        }
    }
    public boolean isEmpty(){
        if(sentences.size()==0){
            return true;
        }
        return  false;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void addSentence(Sentence sentence){
        sentences.add(sentence);
    }
}
