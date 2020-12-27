package TextParser.Service;

import TextParser.Service.SortType.SortByWordAmount;
import TextParser.TextElements.*;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Service.class.getName());
    public List<Sentence> findAllSentenceInAscedingOrder(Text text){
        List<Sentence> allSentences=returnListOfSentences(text);
        Collections.sort(allSentences, new SortByWordAmount());
        return  allSentences;
    }
    private List<Sentence> returnListOfSentences(Text text){
        List<Sentence> allSentences=new ArrayList<Sentence>();
        for(int i=0;i<text.getParagraphs().size();i++){
            for(int j=0;j<text.getParagraphs().get(i).getSentences().size();j++){
                allSentences.add(text.getParagraphs().get(i).getSentences().get(j));
                allSentences.get(allSentences.size()-1).newLineDeleter();
            }
        }
        return allSentences;
    }
    public Text deleteAllWordsStartingFromConsonantLenghtN(Text text,final int LENGHT){
        Text textClone=new Text();
       try{
           textClone=text.clone();
       }
       catch (CloneNotSupportedException e){
            log.error("CloneNotSupportedException");
       }
       log.info("Clone was made succesfully");
        for(int i=0;i<textClone.getParagraphs().size();i++){
            for(int j=0;j<textClone.getParagraphs().get(i).getSentences().size();j++){
                for(int k=0;k<textClone.getParagraphs().get(i).getSentences().get(j).getSentenceComponents().size();k++){
                    SentenceComponent tmp=textClone.getParagraphs().get(i).getSentences().get(j).getSentenceComponents().get(k);
                    if(isWordLengthOfN(tmp,LENGHT)&& isWordStartingFromConsonant(tmp)){
                       textClone.getParagraphs().get(i).getSentences().get(j).getSentenceComponents().remove(k);
                       k--;
                    }
                }
            }
        }
        return  textClone;

    }
    private boolean isWordStartingFromConsonant(SentenceComponent sentenceComponent) {
        if(!(sentenceComponent instanceof Word))
            return  false;
        char firstLetter=((Word) sentenceComponent).getWord().charAt(0);
        return "AEIOUaeiou".indexOf(firstLetter) == -1;
    }

    private boolean isWordLengthOfN(SentenceComponent sentenceComponent,final int LENGHT ){
        if(sentenceComponent instanceof Word && ((Word) sentenceComponent).getWord().length()==LENGHT)
            return true;
        return  false;
    }

}
