package TextParser.TextElements;

import java.util.ArrayList;
import java.util.List;

public class Text implements TextComponent,Cloneable {
    List<Paragraph> paragraphs=new ArrayList<Paragraph>();
    public Text(List<Paragraph> paragraphs){
        this.paragraphs=paragraphs;
    }
    public Text(){}

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }


    public void outputTextComponent() {
        for(TextComponent textComponent:paragraphs){
            textComponent.outputTextComponent();
        }
    }
    public Text clone() throws CloneNotSupportedException{
        return (Text)super.clone();
    }
    public void addParagraph(Paragraph paragraph){
        paragraphs.add(paragraph);
    }
}
