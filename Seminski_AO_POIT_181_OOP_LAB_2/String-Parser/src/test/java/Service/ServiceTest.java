package Service;

import TextParser.Service.Service;
import TextParser.Service.SortType.SortByWordAmount;
import TextParser.TextElements.*;
import org.testng.Assert;
import org.testng.annotations.*;



public class ServiceTest {
    Service serviceTest=new Service();
    Text textTest=new Text();
   private Sentence sentence1=new Sentence();
   private Sentence sentence2=new Sentence();
    @BeforeTest
    public void beforeSortByWordTest(){
        sentence1.addSentenceComponent(new Word("Hi"));sentence1.addSentenceComponent(new Word("world"));sentence1.addSentenceComponent(new PunctuationMark('.',false,false));
        sentence2.addSentenceComponent(new Word("Hi"));sentence2.addSentenceComponent(new PunctuationMark('.',false,false));
    }

    @Test
    public void SortByWordTest(){
        SortByWordAmount sortbyWordAmount=new SortByWordAmount();
        int result=sortbyWordAmount.compare(sentence1,sentence2);
        Assert.assertEquals(result,-1);
    }

    @BeforeMethod
    public void beforeDeleteAllWordsStartingFromConsonantLenghtNTest(){
        final int LENGHT=4;
        Paragraph paragraph1=new Paragraph();
        paragraph1.addSentence(sentence1);
        textTest.addParagraph(paragraph1);


    }
    @Test
    public void deleteAllWordsStartingFromConsonantLenghtNTest(){
        final int LENGHT=5;
        textTest=serviceTest.deleteAllWordsStartingFromConsonantLenghtN(textTest,LENGHT);
        int result=textTest.getParagraphs().get(0).getSentences().get(0).wordCounter();
        Assert.assertEquals(result,1);
    }



}
