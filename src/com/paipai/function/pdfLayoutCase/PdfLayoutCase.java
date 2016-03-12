package com.paipai.function.pdfLayoutCase;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/12/2016
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
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
        Document document = new Document(rectangle, 10, 10, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile)); //排版之后的文件
        PdfImportedPage pdfImportedPage ;
        Image image;
        document.open();
        PdfPTable table = new PdfPTable(column);

        table.getDefaultCell().setBorder(0);
        table.setWidthPercentage(90);
        int pageNumber =  reader.getNumberOfPages();

        for (int i = 1; i < pageNumber+1; i++) {
            pdfImportedPage = writer.getImportedPage(reader,i);
            image = Image.getInstance(pdfImportedPage);
            table.addCell(image);
        }
        table.completeRow();
        document.add(table);
        document.close();
        return newFile;
    }

    public String addEmpty() throws IOException, DocumentException {
        String  newFile = filePath+"_add_empty"+".pdf";
        PdfReader reader = new PdfReader(filePath); //等待排版的文件
        PdfReader readerEmpty = new PdfReader("C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\empty.pdf"); //空白的

        Document document = new Document(rectangle, 10, 10, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile)); //排版之后的文件
        PdfImportedPage pdfImportedPage ;
        Image image;
        document.open();
        PdfPTable table = new PdfPTable(1);

        table.getDefaultCell().setBorder(0);
        table.setWidthPercentage(90);
        table.addCell(Image.getInstance(writer.getImportedPage(readerEmpty,1)));
        int pageNumber =  reader.getNumberOfPages();

        for (int i = 1; i < pageNumber+1; i++) {
            pdfImportedPage = writer.getImportedPage(reader,i);
            image = Image.getInstance(pdfImportedPage);
            table.addCell(image);
        }
        table.completeRow();
        document.add(table);
        document.close();
        return newFile;
    }
}
