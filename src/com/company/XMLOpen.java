package com.company;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLOpen {
    private String FileName;

    // конструктор
    public XMLOpen(String vFileName, String vTarget) {
        try {
            File file = new File(vFileName);
            Document doc = openXMLFile(file); // открытие файла
            // корневой элемент
            Element root = doc.getRootElement();
            //System.out.println(root.getName());
            // получение тега по имени
            Element attributed = root.getChild(vTarget);
            if (attributed != null) {
                //System.out.println(attributed.getName());
                // получение списка аттрибутов тега
                List <Attribute> as = attributed.getAttributes();
                for (Attribute a : as) { System.out.println(a.getName() + " = " + a.getValue()); }
            }
            // получение текста заключенного в теги
            String s = root.getChild(vTarget).getText();
            System.out.println(s);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
