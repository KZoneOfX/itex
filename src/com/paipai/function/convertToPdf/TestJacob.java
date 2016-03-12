package com.paipai.function.convertToPdf;


public class TestJacob {
	public static void main(String[]args) throws Exception{
		args =new String[]{"C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\production\\itex\\com\\paipai\\demo_ppt.ppt"};
		System.out.println(args[0]);
		args[0]=JacobPDFConverter.convert2PDF(args[0]);
		System.out.println("end");
	}
}
