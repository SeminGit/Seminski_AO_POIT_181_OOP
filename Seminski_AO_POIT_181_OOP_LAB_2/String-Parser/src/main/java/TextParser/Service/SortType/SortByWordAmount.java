package TextParser.Service.SortType;

import TextParser.TextElements.Sentence;


import java.util.Comparator;

public class SortByWordAmount implements Comparator<Sentence> {
    public int compare(Sentence temp, Sentence temp1) {
        if( temp.wordCounter()< temp1.wordCounter())
            return 1;
        else if(temp.wordCounter() > temp1.wordCounter())
            return -1;
        else
            return 1;
    }
}
