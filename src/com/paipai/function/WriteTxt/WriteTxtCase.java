package com.paipai.function.writeTxt;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/16/2016
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class WriteTxtCase {
    public WriteTxtCase() {
    }

    public void writeTxt(String file_name,int pageNumber) throws IOException {
//        File file = new File("page.txt");  //测试地址
        File file = new File("C:\\Users\\Administrator\\Desktop\\Apache24\\bin\\page.txt"); //服务器地址
//        File file = new File("C:\\Users\\Administrator\\Desktop\\mysite\\page.txt"); //服务器地址
//        System.out.println("page.txt path:"+"C:\\Users\\Administrator\\Desktop\\mysite\\page.txt");
        if (!file.exists()){
            file.createNewFile();
//            System.out.println("create page.txt");
        }
        String oldString= "";
        String subString ;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((subString = bufferedReader.readLine())!=null){
            oldString +=  subString+"\n";
        }
        bufferedReader.close();
        oldString += file_name+":"+pageNumber+"\n";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(oldString);
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        new WriteTxtCase().writeTxt("C:\\Users\\Xiaoke Zhang\\OneDrive\\Code\\[2016]\\IdeaProjects\\itex\\out\\1\\itex\\com\\paipai\\demo_ppt.ppt",15);
    }


}
