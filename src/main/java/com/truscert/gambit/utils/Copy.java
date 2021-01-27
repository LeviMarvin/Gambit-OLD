package com.truscert.gambit.utils;

import java.io.*;

public class Copy {
    public static void copy(String f1, String f2) throws IOException {
        File file1 = new File(f1);
        /*     File file2 = new File(f2);     */

        File[] flist=file1.listFiles();
        for (File f : flist) {
            if(f.isFile()){
                copyFile2(f.getPath(),f2+"/"+f.getName()); //调用复制文件的方法
                //System.out.println("原路径["+f.getPath()+"] 被复制路径["+f2+"/"+f.getName()+"]");
            }else if(f.isDirectory()){
                copyFile1(f.getPath(),f2+"/"+f.getName()); //调用复制文件夹的方法
                //System.out.println("原路径["+f.getPath()+"] 被复制路径["+f2+"/"+f.getName()+"]");
            }
        }
    }

    /**
     * 复制文件夹
     * @throws IOException
     */
    public static void copyFile1(String f1,String f2) throws IOException{
        //创建文件夹
        File file=new File(f2);
        if(!file.exists()){
            file.mkdirs();
        }
        copy(f1,f2);
    }

    /**
     * 复制文件
     * @throws IOException
     */
    public static void copyFile2(String f1, String f2) throws IOException {
        try {
            InputStream input =new FileInputStream(f1);
            OutputStream output=new FileOutputStream(f2);
            byte[] bt=new byte[1024];
            if((input!=null)&&(output!=null)){
                while((input.read(bt))!=(-1)){
                    output.write(bt,0,bt.length);
                }
            }
            input.close();
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
