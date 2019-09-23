package com.company;

import java.util.Comparator;

/**
 * Created by student on 09.05.2019.
 */
public class CustomersByBalanceComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer a, Customer b) {
        return a.Balance < b.Balance ? -1 : (a.Balance - b.Balance) == 0 ? 0 : 1;
    }
}
