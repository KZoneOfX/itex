package com.paipai.function;


import com.itextpdf.text.DocumentException;
import com.paipai.function.pdfConvertImg.PdfConvertImgVo;
import com.paipai.function.pdfLayoutCase.PdfLayoutCase;
import org.jpedal.exception.PdfException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/12/2016
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class PdfConvertPng {
    public static void main(String[] args) throws IOException, InterruptedException, PdfException, DocumentException {
//        args=new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\ppt_demo2.pdf_2_2.pdf","1*1","1"};
        args=new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\Ch3-2.pdf_3_3.pdf","1*1","1"};
//        args=new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\Ch3-2.pdf","3*3","1"};
        String filePath = args[0];
//        PdfLayoutCase pdfLayoutCase = new PdfLayoutCase(args);
//        System.out.println(pdfLayoutCase.process());

//        String  filePath = pdfLayoutCase.addEmpty();

        PdfConvertImgVo pdfConvertImgVo = new PdfConvertImgVo(filePath);
        System.out.println(pdfConvertImgVo.process());
//        System.out.println(pdfConvertImgVo.deal());


    }
}
