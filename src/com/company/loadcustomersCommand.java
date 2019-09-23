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
public class loadcustomersCommand  implements AbstractCommand{
    String FileName = "BankCustomers.xml";
    Customer tCustomer;
    Bank tBank;

    public loadcustomersCommand(Bank vBank, String args[]) {
        this.tBank = vBank;

        if (args.length > 1) { FileName = args[1];}

    }

    @Override
    public boolean execute() {
        //new XMLOpen(FileName, "customer");
        //tBank.addcustomer(vCustomer);
        try {
            File file = new File(FileName);
            Document doc = openXMLFile(file); // открытие файла
            // корневой элемент
            Element root = doc.getRootElement();

            // получение всех тегов customer
            List<Element> list = root.getChildren("customer");
            // проходим по каждому тегу и читаем аттрибуты и их значения
            for (int i = 0; i < list.size(); i++) {
                tCustomer  = new Customer();
                Element node = (Element) list.get(i);
                // получение списка аттрибутов тега
                List <Attribute> as = node.getAttributes();
                // проходим по всем аттрибутам
                for (Attribute a : as) {
                    if (a.getName().equals("UUID")) {
                        tCustomer.id = a.getValue();
                    }
                    if (a.getName().equals("Name")) {
                        tCustomer.Name = a.getValue();
                    }
                    if (a.getName().equals("Balance")) {
                        tCustomer.Balance = a.getDoubleValue();
                    }
                }
                tBank.addcustomer(tCustomer);
                System.out.println("Add Customer: id=" + tCustomer.id + ", name=" + tCustomer.Name + ", balance=" + Double.toString(tCustomer.Balance));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    // открытие xml файла
    private Document openXMLFile(File file) throws Exception {
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader rd = new FileReader(file);
            return parser.build(rd);
        } catch(Exception ex) {
            throw new Exception("Не удалось открыть файл!  "+ file);
        }
    }
}