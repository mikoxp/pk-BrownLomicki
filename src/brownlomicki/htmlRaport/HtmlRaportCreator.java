/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki.htmlRaport;

import brownlomicki.BrownLomicki;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author user
 */
public class HtmlRaportCreator {

    private final BrownLomicki brownLomicki;
    private PrintWriter printWriter;

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

    private void writing() {
        String htmlBegin = "<html>\n<head>\n <title>%s</title>\n</head>\n<body>";
        String htmlEnd = "</body>\n</html>";
        printWriter.printf(htmlBegin, new Date().toString());
        
        printWriter.printf(htmlEnd);
    }
}
