package com.paipai.function;


import com.paipai.function.pdfConvertImg.PdfConvertImgCase;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/12/2016
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class PdfConvertImg {
    public static void main(String[] args) throws IOException, InterruptedException{
//        args=new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\Ch5-1.pdf_2_2.pdf"};
        String filePath = args[0];
        PdfConvertImgCase pdfConvertImgCase = new PdfConvertImgCase(filePath);
        System.out.println(pdfConvertImgCase.process());
    }
}
