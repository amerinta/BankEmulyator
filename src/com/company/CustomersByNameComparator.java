package com.company;

import java.util.Comparator;

/**
 * Created by student on 09.05.2019.
 */
public class CustomersByNameComparator  implements Comparator<Customer> {
    @Override
    public int compare(Customer a, Customer b) {
        String str_a = a.Name;
        String str_b = b.Name;
        return str_a.compareToIgnoreCase(str_b);
    }
}