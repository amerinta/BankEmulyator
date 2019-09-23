package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.support.AbstractXMLOutputProcessor;
import org.jdom2.output.support.FormatStack;
import org.jdom2.output.support.XMLOutputProcessor;

public class XMLMake {
        
    private String FileName;

    /**
     * Конструктор для списка клиентов
     * @param vFileName Имя файла
     * @param vCustomers Список клиентов
     */
    public XMLMake(String vFileName, ArrayList<Customer> vCustomers) {
       this.FileName = vFileName;

        // инициализация корневого элемента lom
        Element root = new Element("Customers");

        // создание документа с которым предстоит работать
        Document doc = new Document(root);

        for (Customer iCustomer: vCustomers) {
            // формирование элемента attributed
            Element customer = new Element("customer");
            // формирование для элемента attributed обычный атрибута a1 со значением атрибут1
            customer.setAttribute("UUID", iCustomer.id);
            // формирование для элемента attributed атрибута с пространством имен а2 со значением атрибут2
            customer.setAttribute("Name", iCustomer.Name);
            // формирование для элемента attributed атрибута с пространством имен а2 со значением атрибут2
            customer.setAttribute("Balance", Double.toString(iCustomer.Balance));
            // добавление элемента attributed в подчинение элемента general
            root.addContent(customer);
        }

        try {
            if (makeXMLFile(doc, FileName)) { // вывод документа в файл
            System.out.println("Customers stored in file " + FileName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Problem stored in file " + FileName);
        }
    }

    /**
     * Конструктор для списка транзакций
     * @param vFileName Имя файла
     * @param vTransactions Список транзакций
     */
    public XMLMake(String vFileName, ArrayDeque<Transaction> vTransactions) {
        this.FileName = vFileName;

        // инициализация корневого элемента lom
        Element root = new Element("Transactions");

        // создание документа с которым предстоит работать
        Document doc = new Document(root);

        for (Transaction iTransaction: vTransactions) {
            // формирование элемента attributed
            Element transaction = new Element("transaction");
            // формирование для элемента attributed обычный атрибута a1 со значением атрибут1
            transaction.setAttribute("UUID", iTransaction.id);
            // формирование для элемента attributed атрибута с пространством имен а2 со значением атрибут2
            transaction.setAttribute("UUIDFrom", iTransaction.idFrom);
            // формирование для элемента attributed атрибута с пространством имен а2 со значением атрибут2
            transaction.setAttribute("UUIDTo", iTransaction.idTo);
            // формирование для элемента attributed атрибута с пространством имен а2 со значением атрибут2
            transaction.setAttribute("Value", Double.toString(iTransaction.sum));
            // формирование для элемента attributed атрибута с пространством имен а2 со значением атрибут2
            transaction.setAttribute("Delay", Integer.toString(iTransaction.delay));
            // добавление элемента attributed в подчинение элемента general
            root.addContent(transaction);
        }

        try {
            if (makeXMLFile(doc, FileName)) { // вывод документа в файл
                System.out.println("Stored in file " + FileName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Problem stored in file " + FileName);
        }
    }
    // запись xml в файл
    private boolean makeXMLFile(Document doc, String vFileName) throws Exception {
        try {
            File file = new File (vFileName);
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());            
            outputter.setXMLOutputProcessor(new XMLProcessor().setStandalone(true, "yes"));
            outputter.output(doc, new FileWriter(file));
            return true; 
        } catch(Exception ex) { 
            throw new Exception("Не удалось создать файл!"); 
        }
    }
}

// кастомный xml процессор
final class XMLProcessor extends AbstractXMLOutputProcessor implements XMLOutputProcessor {
        
    private boolean saloneWrite = false; // флаг вывода standalone
    private String saloneValue = "yes";  // значение выводимое в standalone

    public XMLProcessor setStandalone(Boolean saloneWrite, String saloneValue) {
        this.saloneWrite = saloneWrite;
        this.saloneValue = saloneValue;
        return this;
    }

    @Override
    protected void printDeclaration(final Writer out, 
                                    final FormatStack fstack) throws IOException {
        if (fstack.isOmitDeclaration()) { return; }
        write(out, "<?xml version=\"1.0\"");
        if (!fstack.isOmitEncoding()) {
            write(out, " encoding=\"" + fstack.getEncoding() + "\"");
        }
        if (saloneWrite) {
            write(out, " standalone=\""+ saloneValue +"\"");
        }
        write(out, "?>");
        write(out, fstack.getLineSeparator());
    }

    public String escapeAttributeEntities(String str, Format format) {
        StringWriter sw = new StringWriter();
        try {
            super.attributeEscapedEntitiesFilter(sw, new FormatStack(format), str);
        } catch (IOException e) {
            // пропуск IOException...
        }
        return sw.toString();
    }

    public final String escapeElementEntities(final String str, final Format format) {
        return Format.escapeText(
            format.getEscapeStrategy(),
            format.getLineSeparator(), 
            str
        );
    }
}
