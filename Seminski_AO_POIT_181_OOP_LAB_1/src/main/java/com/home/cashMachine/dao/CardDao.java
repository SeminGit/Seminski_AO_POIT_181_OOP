package com.home.cashMachine.dao;

import com.home.cashMachine.entity.Card;
import com.home.cashMachine.parser.DataParser;
import com.home.cashMachine.parser.DataReader;

import java.util.List;

public class CardDao {
    private List<Card> cards;
    private int cashMachineBalance;

    public CardDao(){
        DataReader dataReader=new DataReader();
        DataParser dataParser=new DataParser();
        List<String> lines=dataReader.readData();
        cashMachineBalance=dataParser.getCashMachineBalance(lines.get(0));
        cards=dataParser.parseData(lines);

    }

    public Card getCardByCardNumber(String cardNumber){
        for(Card card:cards){
            if(card.getCarNumber().equals(cardNumber))
                return card;
        }
        return null;
    }

    public int getCashMachineBalance(){
        return cashMachineBalance;
    }
}
