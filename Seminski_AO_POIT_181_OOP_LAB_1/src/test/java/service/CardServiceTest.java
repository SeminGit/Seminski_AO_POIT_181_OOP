package service;

import com.home.cashMachine.entity.Card;
import com.home.cashMachine.entity.CardStatus;
import com.home.cashMachine.entity.CashMachine;
import com.home.cashMachine.exception.WithdrawException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class CardServiceTest {
    private CashMachine cashMachine;
    private final int WITHDRAW_1=7000;
    private final int WITHDRAW_2=11000;
    private final int DEPOSIT=1000;
    @BeforeTest
    public void beforeMethod(){
        Card testedCard=new Card(13,"0000-0000-0000-0000",10000,"0000", CardStatus.BLOCKED, LocalDate.parse("2020-10-19"));
        cashMachine=new CashMachine(testedCard,9000);
    }

    @Test
    public void withdrawTest1() throws WithdrawException {
       Assert.assertTrue(cashMachine.withdrawFromCard(WITHDRAW_1));
    }

    @Test
    public void withdrawTest2(){
        try{
            Assert.assertFalse(cashMachine.withdrawFromCard(WITHDRAW_2));
        }
        catch (WithdrawException ex){
            Assert.assertEquals(ex.getMessage(),"Not enough money in yours balance");
        }
    }

    @Test
    public void depositTest(){
        cashMachine.deposit2Card(DEPOSIT);
        Assert.assertEquals(11000,cashMachine.getCardBalance());
    }

    @Test
    public void tryToUnblockCard(){
        cashMachine.tryToUnblockCard();
        Assert.assertFalse(cashMachine.getCurrentCard().isBlocked());

    }



}
