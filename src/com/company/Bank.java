package com.company;

import java.util.*;

/**
 * Created by student on 08.05.2019.
 */
public class Bank {
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private ArrayDeque<Transaction> transactions = new ArrayDeque<Transaction>();

    /**
     * Добавление клиента
     * @param vCustomer объект клиента Customer
     * @return Возвращает добавленный объект Customer
     */
    public Customer addcustomer(Customer vCustomer) {
        customers.add(vCustomer);
        return customers.get(customers.indexOf(vCustomer));
    }

    /**
     * Удаление клиента
     * @param vId Идентификатор клиента в формате UUID
     * @return Возвращает удаленного клиента Customer
     */
    public Customer removeCustomer(String vId) {
        int indexCustomer = -1;
        Customer rCustomer = null;
        try {
            for (Customer iCustomer : customers) {
                if (iCustomer.id.equals(vId)) {
                    indexCustomer = customers.indexOf(iCustomer);
                    break;
                }
            }
            rCustomer = customers.remove(indexCustomer);
        } catch (IndexOutOfBoundsException exp) {
            System.out.print(exp.getMessage());
        }
        return rCustomer;
    }

    public ArrayList sort(Comparator vComparator){
        return new ArrayList();
    }

    /**
     * Получение списка клиентов, отсортированных  по имени,
     * или список клиентов с одинаковым именем
     * @param vName Имя клиента, опционально
     * @return Возвращает список всех клиентов, если vName = "", иначе по vName
     */
    public ArrayList getListCustomersByName(String vName){
        ArrayList<Customer> ListCustomersByName = new ArrayList<Customer>();
        if (vName.equals("")){
            ListCustomersByName = customers;
            Collections.sort(ListCustomersByName, new CustomersByNameComparator());
        } else {
            for (Customer iCustomer : customers) {
                if (iCustomer.Name.equals(vName)) {
                    ListCustomersByName.add(iCustomer);
                }
            }
        }
        return ListCustomersByName;
    }

    /**
     * Получение списка клиентов, отсортированных по балансу
     * @return Возвращает список клиентов
     */
    public ArrayList getListCustomersByBalance(){
        ArrayList<Customer> ListCustomersByBalance = new ArrayList<Customer>();
        ListCustomersByBalance = customers;
        Collections.sort(ListCustomersByBalance, new CustomersByBalanceComparator());
        return ListCustomersByBalance;
    }

    /**
     * Получение клиента Customer по идентификатору
     * @param vId Идентификатор клиента в формате UUID
     * @return Возвращает клиента Customer
     */
    public Customer getCustomerById(String vId){
        Customer rCustomerById = null;
        for (Customer iCustomer : customers) {
            if (iCustomer.id.equals(vId)) {
                rCustomerById = iCustomer;
                break;
            }
        }
        return rCustomerById;
    }

    /**
     * Добавление транзакции в очередь и сортировка
     * @param vTransaction Транзакция
     * @return Возвращает true если добавление удалось, false - если не удалось
     * @throws CustomerNotFoundException Несуществующие клиенты
     */
    public boolean addTransaction (Transaction vTransaction) throws CustomerNotFoundException {
        boolean bCustomerFrom = false;
        boolean bCustomerTo = false;
        boolean result = false;
        ArrayList<Transaction> transactionsforsort = new ArrayList<Transaction>();

        for (Customer iCustomer : customers) {
            if (iCustomer.id.equals(vTransaction.idFrom)) {
                bCustomerFrom = true;
                break;
            }
        }
        for (Customer iCustomer : customers) {
            if (iCustomer.id.equals(vTransaction.idTo)) {
                bCustomerTo = true;
                break;
            }
        }

        if (bCustomerFrom & bCustomerTo) {
            result = transactions.add(vTransaction);
            if (result){
                for (Transaction iTransaction : transactions) {
                    transactionsforsort.add(iTransaction);
                }
                Collections.sort(transactionsforsort, new DelayComparator());
                transactions.clear();
                for (Transaction iTransaction : transactionsforsort) {
                    transactions.add(iTransaction);
                }
            }
            } else {
                throw new CustomerNotFoundException ("Указаны несуществующие клиенты или клиент");
            }

        return result;
    }

    /** Получение очереди транзакций
     *@return Возвращает объект ArrayDeque очередь транзакций
     */
    public ArrayDeque getTransactions(){
        return transactions;
    }
}
