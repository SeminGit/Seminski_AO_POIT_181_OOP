package com.home.cashMachine.parser;

import com.home.cashMachine.entity.Card;
import com.home.cashMachine.entity.CashMachine;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataSaver {

    public void save(CashMachine cashMachine){
        DataReader dataReader=new DataReader();
        DataParser dataParser=new DataParser();
        List<Card> cards=dataParser.parseData(dataReader.readData());
        try(FileWriter out = new FileWriter("src/main/resources/data/cards.txt")){
            out.write(cashMachine.getBalance() + System.lineSeparator());
            for(int i=0;i<cards.size();i++){
                if(cards.get(i).getId()==cashMachine.getCurrentCard().getId())
                    cards.set(i,cashMachine.getCurrentCard());
                out.write(cards.get(i).toString() + System.lineSeparator());
            }
        }
        catch (IOException ex){
        }
    }
}
