package com.company;

import java.util.*;

/**
 * Created by student on 08.05.2019.
 */
public class Bank {
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private ArrayDeque<Transaction> transactions = new ArrayDeque<Transaction>();

    /**
     * ���������� �������
     * @param vCustomer ������ ������� Customer
     * @return ���������� ����������� ������ Customer
     */
    public Customer addcustomer(Customer vCustomer) {
        customers.add(vCustomer);
        return customers.get(customers.indexOf(vCustomer));
    }

    /**
     * �������� �������
     * @param vId ������������� ������� � ������� UUID
     * @return ���������� ���������� ������� Customer
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
     * ��������� ������ ��������, ���������������  �� �����,
     * ��� ������ �������� � ���������� ������
     * @param vName ��� �������, �����������
     * @return ���������� ������ ���� ��������, ���� vName = "", ����� �� vName
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
     * ��������� ������ ��������, ��������������� �� �������
     * @return ���������� ������ ��������
     */
    public ArrayList getListCustomersByBalance(){
        ArrayList<Customer> ListCustomersByBalance = new ArrayList<Customer>();
        ListCustomersByBalance = customers;
        Collections.sort(ListCustomersByBalance, new CustomersByBalanceComparator());
        return ListCustomersByBalance;
    }

    /**
     * ��������� ������� Customer �� ��������������
     * @param vId ������������� ������� � ������� UUID
     * @return ���������� ������� Customer
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
     * ���������� ���������� � ������� � ����������
     * @param vTransaction ����������
     * @return ���������� true ���� ���������� �������, false - ���� �� �������
     * @throws CustomerNotFoundException �������������� �������
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
                throw new CustomerNotFoundException ("������� �������������� ������� ��� ������");
            }

        return result;
    }

    /** ��������� ������� ����������
     *@return ���������� ������ ArrayDeque ������� ����������
     */
    public ArrayDeque getTransactions(){
        return transactions;
    }
}
