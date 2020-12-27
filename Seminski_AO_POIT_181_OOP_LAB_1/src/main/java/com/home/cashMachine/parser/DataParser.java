package com.home.cashMachine.parser;

import com.home.cashMachine.entity.Card;
import com.home.cashMachine.entity.CardStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<Card> parseData(List<String> lines){
        lines.remove(0);
        List<Card> cards=new ArrayList<Card>();
        for(String line: lines){
            String[] data=line.split(" ");
            Card card=new Card(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),data[3], CardStatus.valueOf(data[4].toUpperCase()),getData(data[5]));
            cards.add(card);
        }
        return cards;
    }

    private LocalDate getData(String date){
        LocalDate localDate;
        try {
           localDate = LocalDate.parse(date);
        }
        catch (Exception ex){
            return null;
        }
        return localDate;

    }

    public int getCashMachineBalance(String line){
        return Integer.parseInt(line);
    }


}
