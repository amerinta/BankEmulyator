package com.company;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by student on 10.05.2019.
 */
public class loadtransactionsCommand  implements AbstractCommand{
    String FileName = "BankCustomers.xml";
    Transaction tTransaction;
    Bank tBank;

    public loadtransactionsCommand(Bank vBank, String args[]) {
        this.tBank = vBank;

        if (args.length > 1) { FileName = args[1];}

    }

    @Override
    public boolean execute() {

        try {
            File file = new File(FileName);
            Document doc = openXMLFile(file); // �������� �����
            // �������� �������
            Element root = doc.getRootElement();

            // ��������� ���� ����� transaction
            List<Element> list = root.getChildren("transaction");
            // �������� �� ������� ���� � ������ ��������� � �� ��������
            for (int i = 0; i < list.size(); i++) {
                tTransaction  = new Transaction();
                Element node = (Element) list.get(i);
                // ��������� ������ ���������� ����
                List <Attribute> as = node.getAttributes();
                // �������� �� ���� ����������
                for (Attribute a : as) {
                    if (a.getName().equals("UUID")) {
                        tTransaction.id = a.getValue();
                    }
                    if (a.getName().equals("UUIDFrom")) {
                        tTransaction.idFrom = a.getValue();
                    }
                    if (a.getName().equals("UUIDTo")) {
                        tTransaction.idTo = a.getValue();
                    }
                    if (a.getName().equals("Value")) {
                        tTransaction.sum= a.getDoubleValue();
                    }
                    if (a.getName().equals("Delay")) {
                        tTransaction.delay = a.getIntValue();
                    }
                }
                tBank.addTransaction(tTransaction);
                System.out.println("Add Transaction: id=" + tTransaction.id + ", from=" + tTransaction.idFrom +
                        ", to=" + tTransaction.idTo + ", value=" + Double.toString(tTransaction.sum) + ", delay=" + Integer.toString(tTransaction.delay));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    // �������� xml �����
    private Document openXMLFile(File file) throws Exception {
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader rd = new FileReader(file);
            return parser.build(rd);
        } catch(Exception ex) {
            throw new Exception("�� ������� ������� ����!  "+ file);
        }
    }
}