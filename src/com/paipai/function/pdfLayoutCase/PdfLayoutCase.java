package com.paipai.function.pdfLayoutCase;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/12/2016
 * Time: 11:22 AM
 * pdf 页面 整合实体类
 * */
public class PdfLayoutCase {

    private String filePath;            //文件路径
    private int row;                    //行
    private int column;                 //列
    private Rectangle rectangle;        //纸张类型

    public Rectangle A4_crosswise = new RectangleReadOnly(842.0F,595.0F);           //A4 横向
    public Rectangle A4_vertical = new RectangleReadOnly(595.0F,842.0F);            //A4 竖向


    public PdfLayoutCase(String[] args) {
        Rectangle rectangle;
        if (args[2].trim().equals("1")){
            rectangle = A4_crosswise;
        } else {
            rectangle =A4_vertical;
        }
        this.filePath = args[0];
        this.row = Integer.valueOf(args[1].substring(0,1));
        this.column =  Integer.valueOf(args[1].substring(2,3));
        this.rectangle = rectangle;
    }

    public PdfLayoutCase() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public String toString() {
        return "PdfLayoutCase{" +
                "filePath='" + filePath + '\'' +
                ", row=" + row +
                ", column=" + column +
                ", rectangle=" + rectangle +
                '}';
    }

    public String process() throws IOException, DocumentException {
        String  newFile = filePath+"_"+row+"_"+column+".pdf";
        PdfReader reader = new PdfReader(filePath); //等待排版的文件
        Document document = new Document(rectangle, 10, 10, 40, 10);// 调整 距离上边为 40
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile)); //排版之后的文件
        PdfImportedPage pdfImportedPage ;
        Image image;
        document.open();

        PdfPTable table = new PdfPTable(column);  //定义一个 n行 column 列的 pdftable
        table.getDefaultCell().setBorder(0);      //PfdTable 边框宽度为0
        int pageNumber =  reader.getNumberOfPages();
        //将原pdf中的每页放置到 新pdf的table 中
        for (int i = 1; i < pageNumber+1; i++) {
            pdfImportedPage = writer.getImportedPage(reader,i);
            image = Image.getInstance(pdfImportedPage);
            table.addCell(image);
        }
        //补全 table 中的最后一行
        table.completeRow();
        document.add(table);
        document.close();
        return newFile;
    }

    public int getPageNumber() throws IOException, DocumentException {
        PdfReader reader = new PdfReader(filePath); //等待排版的文件
        int pageNumber =  reader.getNumberOfPages();
        return pageNumber;
    }

    /**
     * 排版文档的第一页
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public String processFirst() throws IOException, DocumentException {
        String  newFile;
        if (rectangle.equals(A4_crosswise)){
            newFile = filePath+"_"+row+"_"+column+"_1.pdf";
        }else {
            newFile = filePath+"_"+row+"_"+column+"_0.pdf";
        }
//        String  newFile = filePath+"_"+row+"_"+column+".pdf";
        PdfReader reader = new PdfReader(filePath); //等待排版的文件
        Document document = new Document(rectangle, 10, 10, 40, 10);// 调整 距离上边为 40
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile)); //排版之后的文件
        PdfImportedPage pdfImportedPage ;
        Image image;
        document.open();

        PdfPTable table = new PdfPTable(column);  //定义一个 n行 column 列的 pdftable
        table.getDefaultCell().setBorder(0);      //PfdTable 边框宽度为0
        int pageNumber =  reader.getNumberOfPages();
        //将原pdf中的每页放置到 新pdf的table 中
        for (int i = 1; i <= column * row && i<= pageNumber; i++) {
            pdfImportedPage = writer.getImportedPage(reader,i);
            image = Image.getInstance(pdfImportedPage);
            table.addCell(image);
        }
        //补全 table 中的最后一行
        table.completeRow();
        document.add(table);
        document.close();
        return newFile;
    }
}
