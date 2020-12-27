package TextParser.Parser;

import TextParser.TextElements.*;
import org.apache.logging.log4j.LogManager;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Parser {
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Parser.class.getName());
    private final static String FILENAME="Text.txt";
    private final static Pattern SENTENCE_PATTERNS=Pattern.compile("\\w+[,|\\s|.|?|!|;|:|'|\"|>|)|}]|[.]\\s+\\w+|\t\\w+|\\W\\s|['|\"|<|(|{]\\w+|\\w+-\\w+[,|\\s|.|?|!|;|:|'|\"|>|)|}]");
    private List<String> lines = new ArrayList<String>();
    private void getTextFromFile(){
        try{
            BufferedReader in = new BufferedReader(new FileReader(FILENAME));
            String line =null;
            while ((line= in.readLine())!=null)
            {
                lines.add(line);
            }
        }
        catch (Exception exc ){
            if(exc instanceof FileNotFoundException){
                log.error("FileNotFoundException in getTextFromFile");
            }
            if(exc instanceof IOException){
                log.error("IOException in getTextFromFile");
            }
        }

    }
    public Text TextParser(){
        getTextFromFile();
        List<Paragraph> paragraphs=new ArrayList<Paragraph>();
        Paragraph paragraph=new Paragraph();
        Sentence sentence=new Sentence();
        for(String line:lines){
            sentence.addSentenceComponent(new PunctuationMark('\n',false,false));
            if(isParagraph(line)){
                if(!paragraph.isEmpty()) {
                    paragraphs.add(paragraph);
                }
                paragraph=new Paragraph();
                sentence.addSentenceComponent(new PunctuationMark('\t',false,false));
            }
            Matcher matcher = SENTENCE_PATTERNS.matcher(line);
            while(matcher.find()){
                int start=matcher.start();
                int end=matcher.end();
                sentence.getSentenceComponents().addAll(subStringChecker(line.substring(start,end)));
                if(hasTheSentenceEnded(sentence.getSentenceComponents())){
                    paragraph.addSentence(sentence);
                    sentence=new Sentence();
                }
            }
        }
        paragraphs.add(paragraph);
        Text text=new Text(paragraphs);
        return  text;
    }
    private List<SentenceComponent> subStringChecker(String foundString){
        List<SentenceComponent> sentenceComponents=new ArrayList<SentenceComponent>();
        if(Pattern.matches("\\w+[,|\\s|.|?|!|;|:|'|\"|>|)|}]",foundString)){
            sentenceComponents.add(new Word(foundString.substring(0, foundString.length() - 1)));
            if (foundString.charAt(foundString.length() - 1) != ' ')
                sentenceComponents.add(new PunctuationMark(foundString.charAt(foundString.length()-1),false,false));
        }
        if(Pattern.matches("[.]\\s\\w+",foundString)){
            sentenceComponents.add(new Word(foundString.substring(2,foundString.length())));
        }
        if(Pattern.matches("['|\"|<|(|{]\\w+",foundString)){
            sentenceComponents.add(new PunctuationMark(foundString.charAt(0),true,false));
            sentenceComponents.add(new Word(foundString.substring(1, foundString.length())));
        }
        if(Pattern.matches("\\W\\s",foundString)){
            sentenceComponents.add(new PunctuationMark(foundString.charAt(0),false,true));
        }
        if(Pattern.matches("\t\\w+",foundString)){
            sentenceComponents.add(new Word(foundString.substring(1,foundString.length())));
        }
        if(Pattern.matches("\\w+-\\w+[,|\\s|.|?|!|;|:|'|\"|>|)|}]",foundString)){
            sentenceComponents.add(new Word(foundString.substring(0, foundString.length() - 1)));
            if (!Character.isWhitespace(foundString.charAt(foundString.length() - 1)))
                sentenceComponents.add(new PunctuationMark(foundString.charAt(foundString.length() - 1), false, false));
        }
        return  sentenceComponents;
    }
    private boolean isParagraph(String line){
        if(line.charAt(0) =='\t')return  true;
        return false;
    }
    private boolean hasTheSentenceEnded(List<SentenceComponent> sentence){
        if(sentence.get(sentence.size()-1) instanceof PunctuationMark &&((PunctuationMark)sentence.get(sentence.size()-1)).getSymbol()=='.' ){
            return true;
        }
        return  false;
    }


}
