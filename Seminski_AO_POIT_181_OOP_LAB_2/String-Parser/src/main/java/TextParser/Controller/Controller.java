package TextParser.Controller;

import TextParser.Parser.Parser;
import TextParser.Service.Service;
import TextParser.TextElements.Sentence;
import TextParser.TextElements.Text;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Controller.class.getName());
    private Text text;
    private Service textService=new Service();
    private Parser textParser=new Parser();
    public Controller(){
        text=textParser.TextParser();
        log.info("Controller was created and text was parsed");

    }

    public Text getText() {
        return text;
    }

    public void printAllSentenceInAscedingOrder(){
        try {
            List<Sentence> sortedSentences = textService.findAllSentenceInAscedingOrder(text);
            for (Sentence sentence : sortedSentences) {
                System.out.println();
                sentence.outputTextComponent();
            }
        }catch (Exception e){
            log.error("Method printAllSentenceInAscedingOrder was failed,Stack trace: "+e.getStackTrace());
        }
        log.info("Method printAllSentenceInAscedingOrder was completed");
    }
    public void deleteAllWordsStartingFromConsonantLenghtN(){
        try {
            Scanner in=new Scanner(System.in);
            System.out.println("Input word's lenght that should be deleted : ");
            final int LENGHT=in.nextInt();
            Text newText=textService.deleteAllWordsStartingFromConsonantLenghtN(text,LENGHT);
            newText.outputTextComponent();
        }
        catch (Exception e){
            log.error("Method deleteAllWordsStartingFromConsonantLenghtN was failed,Stack trace: "+e.getStackTrace());
        }
        log.info("Method deleteAllWordsStartingFromConsonantLenghtN was completed");
    }
}
