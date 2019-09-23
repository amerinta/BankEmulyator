package com.company;

import java.util.Comparator;

/**
 * Created by student on 08.05.2019.
 */
public class DelayComparator  implements Comparator<Transaction> {
        @Override
        public int compare(Transaction a, Transaction b) {
            return a.delay < b.delay ? -1 : a.delay == b.delay ? 0 : 1;
        }
}
