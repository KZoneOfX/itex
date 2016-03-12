package com.paipai.function.pdfConvertImg;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/12/2016
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class PdfConvertImgCase {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PdfConvertImgCase(String filePath) {
        this.filePath = filePath;
    }


    public String process() throws IOException, InterruptedException {
        String newFilePath =filePath+"_"+new Date().getTime()+"_Pic.jpg";

        //载入要转换的pdf
        PDDocument doc = PDDocument.load(filePath);
        //获取文档的全部页面
        List pages = doc.getDocumentCatalog().getAllPages();
        //获取pdf 的第一页
        PDPage page = (PDPage)pages.get(0);
        //pdf 的准变
        BufferedImage image = page.convertToImage();
        Iterator iterator = ImageIO.getImageWritersBySuffix("jpg");
        ImageWriter writer = (ImageWriter)iterator.next();

        //图片写入
        File outFile = new File(newFilePath);
        FileOutputStream out = new FileOutputStream(outFile);
        ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
        writer.setOutput(outImage);
        writer.write(new IIOImage(image,null,null));
        doc.close();

        return newFilePath;
    }
}
