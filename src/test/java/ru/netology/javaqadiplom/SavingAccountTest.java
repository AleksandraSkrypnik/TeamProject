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
            new SavingAccount(2_000, -1_000, 10_000, 5);
        });
    }

    @Test
    public void testConstructorInitialBalanceBelowMinBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(900, 1_000, 10_000, 5);
        });
    }

    @Test
    public void testConstructorInitialBalanceAboveMaxBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(11_000, 1_000, 10_000, 5);
        });
    }

    @Test
    public void testConstructorMinBalanceGreaterThanMaxBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 11_000, 10_000, 5);
        });
    }

    @Test
    public void testPayValidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.pay(400);
        int expected = 1_600;
        int actual = account.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testPayInvalidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.pay(1_100);
        int expected = 2_000;
        int actual = account.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddValidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.add(4_000);
        int expected = 6_000;
        int actual = account.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddInvalidAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.add(9_000);
        int expected = 2_000;
        int actual = account.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testYearChange() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        int expected = 100; // 2000 * 5 / 100
        int actual = account.yearChange();
        assertEquals(expected, actual);
    }
}

