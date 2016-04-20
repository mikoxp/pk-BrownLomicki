/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki.htmlRaport;

import brownlomicki.BrownLomicki;
import brownlomicki.Period;
import brownlomicki.Product;
import gui.GanttDiagram;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Michał Oleś
 */
public class HtmlRaportCreator {

    private final BrownLomicki brownLomicki;
    private GanttDiagram ganttDiagram;
    private PrintWriter printWriter;
    String stringSchema1 = "<p>%s</p>\n";
    String stringSchema2 = "<h3>%s</h2>\n";

    public HtmlRaportCreator(BrownLomicki brownLomicki) {
        this.brownLomicki = brownLomicki;
    }

    /**
     *
     * @param folderName nazwa folderu
     * @return czy się udało
     */
    public boolean createRaport(String folderName) {
        File folder = new File(folderName);
        File file = new File(folder + "/index.html");
        ganttDiagram = new GanttDiagram("", brownLomicki.getOptimalOrder());

        try {
            folder.mkdir();
            ganttDiagram.saveGanttDiagramToFile(folder + "/gantt_diagram.png");
            printWriter = new PrintWriter(file, "UTF-8");
            writing();
            printWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            return false;
        }
        try {

            String htmlFilePath = folder + "/index.html"; // path to your new file
            File htmlFile = new File(htmlFilePath);
            Desktop.getDesktop().browse(htmlFile.toURI());
            Desktop.getDesktop().open(htmlFile);
        } catch (Exception e) {
        }
        return true;
    }

    /**
     * wpisywanie danych poczatkowych
     */
    private void writeStartOrder() {
        printWriter.println("<h1>Input data</h1>");
        printWriter.println("<h2>Time working on machines<h2>");
        for (Product p : brownLomicki.getStartList()) {
            printWriter.printf(stringSchema2, p.getIdName());
            printWriter.printf(stringSchema1, Arrays.toString(p.getTimeInMachines()));
        }
    }

    /**
     * wpisywanie danych o rozmiarze zadania
     */
    private void writeSizeWork() {
        printWriter.println("<h1>Task Size</h1>");
        printWriter.printf(stringSchema1, "Number of Product: " + brownLomicki.getNumberOfProduct());
        printWriter.printf(stringSchema1, "Number of Machines: " + brownLomicki.getNumberOfMachine());

    }

    /**
     * wpisywanie optymalnej kolejniosci
     */
    private void writeOptimalOrder() {
        int i;
        printWriter.println("<h1><b>Optimal Order and works periods</b></h1>");
        for (Product p : brownLomicki.getOptimalOrder()) {
            printWriter.printf(stringSchema2, "Product Name: " + p.getIdName());
            i = 1;
            for (Period period : p.getPeriodWorks()) {
                printWriter.printf(stringSchema1, "Machine " + i + ": " + period.toString());
                i++;
            }
        }
        printWriter.printf(stringSchema2, "Total time: " + brownLomicki.getTotalCost());

    }

    /**
     * tworzenie htmla
     */
    private void writing() {
        String htmlBegin = "<html>\n<head>\n <title>%s</title>\n<meta charset=\"UTF-8\">\n</head>\n<body>";
        String htmlEnd = "</body>\n</html>";
        printWriter.printf(htmlBegin, new Date().toString());
        writeStartOrder();
        writeSizeWork();
        writeOptimalOrder();
        printWriter.printf("<img src=\"gantt_diagram.png\">\n");
        printWriter.printf(htmlEnd);
    }
}
