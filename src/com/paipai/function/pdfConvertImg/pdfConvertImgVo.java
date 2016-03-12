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
public class PdfConvertImgVo {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PdfConvertImgVo(String filePath) {
        this.filePath = filePath;
    }


    public String process() throws IOException, InterruptedException {
        File file = new File(filePath);

//        String newFilePath =filePath+"_"+new Date().getTime()+"_Pic.png";
        String newFilePath =filePath+"_"+new Date().getTime()+"_Pic.jpg";
//  第四种
        PDDocument doc = PDDocument.load(filePath);
        int pageCount = doc.getPageCount();
        System.out.println(pageCount);
        List pages = doc.getDocumentCatalog().getAllPages();
        PDPage page = (PDPage)pages.get(0);
        BufferedImage image = page.convertToImage();
        Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
        ImageWriter writer = (ImageWriter)iter.next();
        File outFile = new File(newFilePath);
        FileOutputStream out = new FileOutputStream(outFile);
        ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
        writer.setOutput(outImage);
        writer.write(new IIOImage(image,null,null));
//        for(int i=0;i<pages.size();i++){
//            PDPage page = (PDPage)pages.get(i);
//            BufferedImage image = page.convertToImage();
//            Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
//            ImageWriter writer = (ImageWriter)iter.next();
//            File outFile = new File(newFilePath);
//            FileOutputStream out = new FileOutputStream(outFile);
//            ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
//            writer.setOutput(outImage);
//            writer.write(new IIOImage(image,null,null));
//        }
        doc.close();
        System.out.println("over");

//   第三种 尝试
// 创建从中读取和向其中写入（可选）的随机访问文件流，R表示对其只是访问模式
//        RandomAccessFile rea = new RandomAccessFile(file, "r");
//
//        //将流读取到内存中，然后还映射一个PDF对象
//        FileChannel channel = rea.getChannel();
//        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,0, channel.size());
//        PDFFile pdfFile = new PDFFile(buf);
//        System.out.println(pdfFile.getNumPages());
//        PDFPage page = pdfFile.getPage(2);
//
//
//        // get the width and height for the doc at the default zoom
//        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, (int) page.getBBox()
//                .getWidth(), (int) page.getBBox().getHeight());
//
//        // generate the image
//        java.awt.Image img = page.getImage(rect.width, rect.height, // width &
//                rect, // clip rect
//                null, // null for the ImageObserver
//                true, // fill background with white
//                true // block until drawing is done
//        );
//
//        BufferedImage tag = new BufferedImage(rect.width, rect.height,
//                BufferedImage.TYPE_INT_RGB);
//
//        tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
//                null);
//        FileOutputStream out = new FileOutputStream(newFilePath); // 输出到文件流
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(tag); // JPEG编码
//        out.close();

//
//          第二种 尝试
//        int pagen = 1;
//        //File file = new File("/home/develop/xq/P9.pdf");
//        PDFFile pdffile = null;
//        // set up the PDF reading
//        try {
//            RandomAccessFile raf = new RandomAccessFile(file, "r");
//            FileChannel channel = raf.getChannel();
//            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
//                    channel.size());
//            pdffile = new PDFFile(buf);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        // print 出该 pdf 文档的页数
//        System.out.println(pdffile.getNumPages());
//
//        // 设置将第 pagen 页生成 png 图片
//        PDFPage page = pdffile.getPage(0);
//
//        // create and configure a graphics object
//        int width = (int) page.getBBox().getWidth();
//        int height = (int) page.getBBox().getHeight();
//        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = img.createGraphics();
//
//        // do the actual drawing
//        PDFRenderer renderer = new PDFRenderer(page, g2, new Rectangle(0, 0, width, height), null, Color.WHITE);
//        try {
//            page.waitForFinish();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        renderer.run();
//        g2.dispose();
//
//        try {
//            ImageIO.write(img, "png", new File(newFilePath));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//

//       第一种 尝试
//        RandomAccessFile randomAccessFile = new RandomAccessFile(pdfFile,"r");
//        FileChannel fileChannel = randomAccessFile.getChannel();
//        ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,fileChannel.size());
//        PDFFile pdfFile = null;
//        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
//        FileChannel fileChannel = randomAccessFile.getChannel();
//        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,fileChannel.size());
//        pdfFile = new PDFFile(byteBuffer);
//        PDFPage pdfPage = pdfFile.getPage(1); //设置将pdf 的第一页 生成PNG图片
//        int width = (int) pdfPage.getBBox().getWidth();
//        int height = (int) pdfPage.getBBox().getHeight();
//        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics2D = bufferedImage.createGraphics();
//        PDFRenderer renderer = new PDFRenderer(pdfPage,graphics2D,new Rectangle(0,0,width,height),null,Color.white);
//        pdfPage.waitForFinish();
//        renderer.run();
//        graphics2D.dispose();
//        String newFilePath =filePath+"_"+new Date().getTime()+"_Pic.png";
//        ImageIO.write(bufferedImage,"png",new File(newFilePath));
        return newFilePath;
    }
}
