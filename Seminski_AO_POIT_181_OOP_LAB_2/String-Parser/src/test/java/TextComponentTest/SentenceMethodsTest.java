package TextComponentTest;

import TextParser.TextElements.PunctuationMark;
import TextParser.TextElements.Sentence;
import TextParser.TextElements.Word;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SentenceMethodsTest {
    private Sentence sentenceTest=new Sentence();
    @BeforeMethod
    public void beforeWordCounterTest(){
        sentenceTest.addSentenceComponent(new Word("Hello"));
        sentenceTest.addSentenceComponent(new PunctuationMark(',',false,false));
        sentenceTest.addSentenceComponent(new Word("world"));
        sentenceTest.addSentenceComponent(new PunctuationMark('!',false,false));
    }
    @Test
    public void wordCounterTest(){
        int result=sentenceTest.wordCounter();
        Assert.assertEquals(result,2);
    }
}
