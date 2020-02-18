package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoefficientsServiceTest {

    @Test
    void calcEPC() {
        double a = 459, b = 15000;
        double res = CoefficientsService.getInstance().calcEPC(b,a);
        assertEquals(32.680, res);
    }

    @Test
    void calcMoneyFor1Share() {
        double a = 459, b = 15000, c = 400;
        double res = CoefficientsService.getInstance().calcMoneyFor1Share(b,c,a);
        assertEquals(33.551, res);
    }

    @Test
    void calcCapitalizedProfit() {
        double a = 18, b = 19;
        double res = CoefficientsService.getInstance().calcCapitalizedProfit(a,b);
        assertEquals(0.056, res);
    }

    @Test
    void calcDividendIncome() {
        double a = 18, b = 1;
        double res = CoefficientsService.getInstance().calcDividendIncome(b,a);
        assertEquals(0.056, res);
    }

    @Test
    void calcCommonStockReturn() {
        double a = 19, b = 18,c = 1;
        double res = CoefficientsService.getInstance().calcCommonStockReturn(b,a,c);
        assertEquals(0.112, res);
    }

    @Test
    void calcMultipleProfit() {
        double b = 18.5, a = 32.680;
        double res = CoefficientsService.getInstance().calcMultipleProfit(b,a);
        assertEquals(0.566, res);
    }
}