package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void testConstructorValidParameters() {
        int initialBalance = 2_000;
        int minBalance = 1_000;
        int maxBalance = 10_000;
        int rate = 5;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int expected = initialBalance;
        int actual = account.getBalance();
        assertEquals(expected, actual);

        expected = minBalance;
        actual = account.getMinBalance();
        assertEquals(expected, actual);

        expected = maxBalance;
        actual = account.getMaxBalance();
        assertEquals(expected, actual);

        expected = rate;
        actual = account.getRate();
        assertEquals(expected, actual);
    }

    @Test
    public void testConstructorInvalidRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 1_000, 10_000, -1);
        });
    }

    @Test
    public void testConstructorInvalidMinBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, -500, 10_000, 5);
        });
    }

    @Test
    public void testConstructorInitialBalanceBelowMinBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(400, 1_000, 10_000, 5);
        });
    }

    @Test
    public void testConstructorInitialBalanceAboveMaxBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(16_000, 1_000, 10_000, 5);
        });
    }

    @Test
    public void testConstructorMinBalanceGreaterThanMaxBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 16_000, 10_000, 5);
        });
    }

    @Test
    public void testPayValidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        boolean result = account.pay(400);
        int expected = 600;
        int actual = account.getBalance();
        assertEquals(expected, actual);
        assertEquals(true, result);
    }

    @Test
    public void testPayInvalidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        boolean result = account.pay(2_300);
        int expected = 2_000;
        int actual = account.getBalance();
        assertEquals(expected, actual);
        assertEquals(false, result);
    }

    @Test
    public void testAddValidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        boolean result = account.add(400);
        int expected = 2_400;
        int actual = account.getBalance();
        assertEquals(expected, actual);
        assertEquals(true, result);
    }

    @Test
    public void testAddInvalidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        boolean result = account.add(-600);
        int expected = 2_000;
        int actual = account.getBalance();
        assertEquals(expected, actual);
        assertEquals(false, result);
    }

    @Test
    public void testYearChange() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        int expected = 100; // 2_000 * 5 / 100
        int actual = account.yearChange();
        assertEquals(expected, actual);
    }
}

