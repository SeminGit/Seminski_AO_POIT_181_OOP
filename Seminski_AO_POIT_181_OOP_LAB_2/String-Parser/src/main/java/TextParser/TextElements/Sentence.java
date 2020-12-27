package TextParser.TextElements;

import java.util.*;


public class Sentence extends SentenceComponent  {
    private List<SentenceComponent> sentenceComponents=new ArrayList<SentenceComponent>();

    public  List<SentenceComponent> getSentenceComponents(){
        return  sentenceComponents;
    }

    private boolean isPunctuationMark(int i){
        if(sentenceComponents.get(i) instanceof Word && (sentenceComponents.get(i+1) instanceof PunctuationMark && !((PunctuationMark) sentenceComponents.get(i+1)).getIsOpening() && !((PunctuationMark) sentenceComponents.get(i+1)).getIsOther()))
            return true;
        return false;
    }

    private  boolean isPunctuationMarkStarting(int i,boolean flag){
        if(sentenceComponents.get(i)instanceof PunctuationMark &&((PunctuationMark) sentenceComponents.get(i)).getIsOpening() && flag==false )
            return true;
        return  false;
    }

    public void addSentenceComponent(SentenceComponent sentenceComponent){
        sentenceComponents.add(sentenceComponent);
    }

    @Override
    public void outputTextComponent() {
        boolean isPunctuationMarkStarted=false;
        for(int i=0;i<sentenceComponents.size()-1;i++){
            System.out.print(" ");
            if(isPunctuationMarkStarted){System.out.print("\b");isPunctuationMarkStarted=false;}
            sentenceComponents.get(i).outputTextComponent();
            if(isPunctuationMark(i)){
                sentenceComponents.get(i+1).outputTextComponent();
                i++;continue;
            }
            if(isPunctuationMarkStarting(i,isPunctuationMarkStarted))
                isPunctuationMarkStarted=true;
        }
    }
    public int wordCounter(){
        int count=0;
        for(SentenceComponent sentenceComponent:sentenceComponents){
            if(sentenceComponent instanceof Word) count++;
        }
        return count;
    }
    public void  newLineDeleter(){
       for(int i=0;i<sentenceComponents.size();i++){
           if(sentenceComponents.get(i) instanceof PunctuationMark &&((PunctuationMark) sentenceComponents.get(i)).isSpecialSymbol()){
               sentenceComponents.remove(i);
               i--;
           }
       }
    }
}
