package com.paipai.function;

import com.itextpdf.text.DocumentException;
import com.paipai.function.pdfLayoutCase.PdfLayoutCase;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/11/2016
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 * java -jar example.jar example.pdf n*m q  (表示将pdf排版成 每行n张,每列m张,q=1表示横向,q=0 表示纵向;)
 * 1.1 1.2 1.4 1.9
 */
public class PdfLayout {

    public static void main(String[] args) throws IOException, DocumentException {

//        args=new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\ppt_demo2.pdf_2_2.pdf","1*1","1"};
//        args=new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\ppt_demo2.pdf","3*3","1"};
        PdfLayoutCase pdfLayoutCase = new PdfLayoutCase(args);
        System.out.println(pdfLayoutCase.process());


    }

}
