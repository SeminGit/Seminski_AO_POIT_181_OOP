package Parser;

import TextParser.Parser.Parser;
import TextParser.TextElements.Text;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ParserTest {
    @Test
    public void testDeserialize(){
        Parser parserTest=new Parser();
        Text textTest=parserTest.TextParser();
        Assert.assertTrue(textTest.getParagraphs().size()!=0);

    }
}
