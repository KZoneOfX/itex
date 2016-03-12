package com.paipai.function.convertToPdf;



import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;


public class JacobPDFConverter{
	private static final int wdFormatPDF = 17;
	private static final int xlTypePDF = 0;
	private static final int ppSaveAsPDF = 32;
	private static final int msoTrue = -1;
	private static final int msofalse = 0;
	
	public static void convert2PDF(String inputFile, String pdfFile) {
		String suffix = FileUtils.getFileSufix(inputFile);
		File file = new File(inputFile);
		if(!file.exists()){
			System.out.println("æ–‡ä»¶ä¸�å­˜åœ¨ï¼�");
			return;
		}
		if(suffix.equals("pdf")){
			System.out.println("PDF not need to convert!");
			pdfFile = inputFile;
			return ;
		}
		if(suffix.equals("doc")||suffix.equals("docx")||suffix.equals("txt")){
			word2PDF(inputFile,pdfFile);
		}else if(suffix.equals("ppt")||suffix.equals("pptx")){
			ppt2PDF(inputFile,pdfFile);
		}else if(suffix.equals("xls")||suffix.equals("xlsx")){
			excel2PDF(inputFile,pdfFile);
		}else{
			System.out.println("æ–‡ä»¶æ ¼å¼�ä¸�æ�?¯æŒ�è½¬æ�¢!");
		}
	}

	public static String convert2PDF(String inputFile) {
		String pdfFile = FileUtils.getFilePrefix(inputFile)+".pdf";
		convert2PDF(inputFile,pdfFile);
		return pdfFile;
	}
	
	public static void word2PDF(String inputFile,String pdfFile){
		//æ‰“å¼€wordåº�?ç�?¨ç¨‹åº�
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		//è®¾ç½®wordä¸�å�¯è§�
		app.setProperty("Visible", false);
		//èŽ·å¾—wordä¸­æ‰€æœ‰æ‰“å¼€çš„æ–‡æ¡£,è¿�?å›žDocumentså¯¹è±¡
		Dispatch docs = app.getProperty("Documents").toDispatch();
		//è°ƒç�?¨Documentså¯¹è±¡ä¸­Openæ–¹æ³•æ‰“å¼€æ–‡æ¡£ï¼Œå¹¶è¿�?å›žæ‰“å¼€çš„æ–‡æ¡£å¯¹è±¡Document
		Dispatch doc = Dispatch.call(docs,
									"Open",
									inputFile,
									false,
									true
									).toDispatch();
		//è°ƒç�?¨Documentå¯¹è±¡çš„SaveAsæ–¹æ³•ï¼Œå°†æ–‡æ¡£ä¿�å­˜ä¸ºpdfæ ¼å¼�
		/*
		Dispatch.call(doc,
					"SaveAs",
					pdfFile,
					wdFormatPDF		//wordä¿�å­˜ä¸ºpdfæ ¼å¼�å®�ï¼Œå€¼ä¸º17
					);
					*/
		Dispatch.call(doc,
				"ExportAsFixedFormat",
				pdfFile,
				wdFormatPDF		//wordä¿�å­˜ä¸ºpdfæ ¼å¼�å®�ï¼Œå€¼ä¸º17
				);
		//å…³é—­æ–‡æ¡£
		Dispatch.call(doc, "Close",false);
		//å…³é—­wordåº�?ç�?¨ç¨‹åº�
		app.invoke("Quit", 0);
		
	}
	public static void excel2PDF(String inputFile,String pdfFile){
		ActiveXComponent app = new ActiveXComponent("Excel.Application");
		app.setProperty("Visible", false);
		Dispatch excels = app.getProperty("Workbooks").toDispatch();
		Dispatch excel = Dispatch.call(excels,
									"Open",
									inputFile,
									false,
									true
									).toDispatch();
		Dispatch.call(excel,
					"ExportAsFixedFormat",
					xlTypePDF,		
					pdfFile
					);
		Dispatch.call(excel, "Close",false);
		app.invoke("Quit");
		
		
	}
	public static void ppt2PDF(String inputFile,String pdfFile){
		
		ActiveXComponent app = new ActiveXComponent("PowerPoint.Application");
		//app.setProperty("Visible", msofalse);
		Dispatch ppts = app.getProperty("Presentations").toDispatch();
		
		Dispatch ppt = Dispatch.call(ppts,
									"Open",
									inputFile,
									true,//ReadOnly
									true,//UntitledæŒ‡å®šæ–‡ä»¶æ˜¯å�¦æœ‰æ ‡é¢˜
									false//WithWindowæŒ‡å®šæ–‡ä»¶æ˜¯å�¦å�¯è§�
									).toDispatch();
		
		Dispatch.call(ppt,
					"SaveAs",
					pdfFile,
					ppSaveAsPDF	
					);
				
		Dispatch.call(ppt, "Close");
		
		app.invoke("Quit");
		
	}

}
