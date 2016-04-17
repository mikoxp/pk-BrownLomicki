/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki.htmlRaport;

import brownlomicki.BrownLomicki;
import brownlomicki.Period;
import brownlomicki.Product;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author user
 */
public class HtmlRaportCreator {

    private final BrownLomicki brownLomicki;
    private PrintWriter printWriter;
    String stringSchema1="<p>%s</p>\n";
    String stringSchema2="<h3>%s</h2>\n";

    public HtmlRaportCreator(BrownLomicki brownLomicki) {
        this.brownLomicki = brownLomicki;
    }

    public boolean createRaport(String folderName) {
        File folder = new File(folderName);
        File file = new File(folder + "/index.html");
        try {
            folder.mkdir();
            printWriter = new PrintWriter(file);
            writing();
            printWriter.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private void writeStartOrder(){
        printWriter.println("<h1>Dane wejściowe</h1>");
        printWriter.println("<h2>Czasy pracy na maszynach<h2>");
        for(Product p:brownLomicki.getStartList()){
            printWriter.printf(stringSchema2,p.getIdName());
            printWriter.printf(stringSchema1,Arrays.toString(p.getTimeInMachines()));
        }
    }
    private void writeSizeWork(){
         printWriter.println("<h1>Rozmiar zadania</h1>");
         printWriter.printf(stringSchema1,"Liczba Produktów: "+brownLomicki.getNumberOfProduct());
         printWriter.printf(stringSchema1,"Liczba Maszyn: "+brownLomicki.getNumberOfMachine());
         
    }
    private  void writeOptimalOrder(){
        int i;
        printWriter.println("<h1><b>Optymalna kolejność i okresy pracy na maszynach</b></h1>");
        for(Product p:brownLomicki.getOptimalOrder()){
            printWriter.printf(stringSchema2,"Nazwa Produktu: "+p.getIdName());
            i=1;
            for(Period period:p.getPeriodWorks()){
                printWriter.printf(stringSchema1,"Maszyna "+i+": "+period.toString());
                i++;
            }
        }
        printWriter.printf(stringSchema2, "Całkowity czas wykonywania zestawu produktów: "+brownLomicki.getTotalCost());
        
    }
    private void writing() {
        String htmlBegin = "<html>\n<head>\n <title>%s</title>\n<meta charset=\"UTF-8\">\n</head>\n<body>";
        String htmlEnd = "</body>\n</html>";
        printWriter.printf(htmlBegin, new Date().toString());
        writeStartOrder();
        writeSizeWork();
        writeOptimalOrder();
        printWriter.printf(htmlEnd);
    }
}
