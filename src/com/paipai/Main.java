package com.paipai;

import com.itextpdf.text.DocumentException;
import com.paipai.function.connectMysql.PdfPageNumberUpdateCase;
import com.paipai.function.convertToPdf.FileUtils;
import com.paipai.function.convertToPdf.JacobPDFConverter;
import com.paipai.function.pdfConvertImg.PdfConvertImgCase;
import com.paipai.function.pdfLayoutCase.PdfLayoutCase;
import com.paipai.function.writeTxt.WriteTxtCase;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/14/2016
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 * 1*1 0
 * 1*2 1
 * 2*2 1
 * 3*3 1
 */
public class Main {

    public static void main(String[] args) throws IOException, DocumentException, InterruptedException, SQLException {
//        args = new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\artifacts\\demo\\demo_pdf.pdf","cb87c1df669284fd95838209d201144d"};
        String file_name = args[0];
        String file_id = args[1];
        args = new String[]{args[0],"1*1","1"};
        System.out.println(args[0]);
        String  suffix= FileUtils.getFileSufix(file_name);
        System.out.println("suffix:"+suffix);
		file_name= JacobPDFConverter.convert2PDF(file_name); //获得 pdf 文件

        System.out.println("convert success"+file_name);
        args[0]= file_name;
        int pageNumber =  new PdfLayoutCase(args).getPageNumber();
        //更新文件页数 进入数据库中  yunPrint
        new PdfPageNumberUpdateCase(file_id,pageNumber).update_pdf_file_page();
//        将文件页数 写到文件中page.txt 。
//        new WriteTxtCase().writeTxt(file_name,pageNumber);
        if(suffix.equals("pdf")){
            process(new String[]{args[0],"1*1","0"});
            process(new String[]{args[0],"1*1","1"});
            process(new String[]{args[0],"1*2","1"});
            process(new String[]{args[0],"2*1","0"});
            process(new String[]{args[0],"2*2","1"});
            process(new String[]{args[0],"3*3","1"});
            System.out.println("PDF not need to convert!");
        }else if(suffix.equals("doc")||suffix.equals("docx")||suffix.equals("txt")){
            process(new String[]{args[0],"1*1","0"});
            process(new String[]{args[0],"1*2","1"});
        }else if(suffix.equals("ppt")||suffix.equals("pptx")){
            process(new String[]{args[0],"1*1","1"});
            process(new String[]{args[0],"2*1","0"});
            process(new String[]{args[0],"2*2","1"});
            process(new String[]{args[0],"3*3","1"});
        }else if(suffix.equals("xls")||suffix.equals("xlsx")){
            System.out.println("xls\t xlsx");
        }else{
            System.out.println("error file suffix");
        }
        System.out.println("end");
    }

    static void process(String[] args) throws IOException, InterruptedException, DocumentException {
        PdfLayoutCase pdfLayoutCase = new PdfLayoutCase(args);
        String layout_file_path = pdfLayoutCase.processFirst();
        System.out.println(layout_file_path);
        PdfConvertImgCase pdfConvertImgCase = new PdfConvertImgCase(layout_file_path);
        System.out.println(pdfConvertImgCase.process());
    }
}
