package org.panther.study.disruptor;/*
*@author GML
*@date 2018/8/10 14:49
*/

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ImageUtil6 {

    private static Font font = new Font("微软雅黑", Font.PLAIN, 40);// 添加字体的属性设置

    /**
     * 合成图片
     * @return
     */
    public synchronized  BufferedImage compose(String sourceImgFile, String sourceQRFile, String headImgUrl,String destImgFile,ConcurrentHashMap map){

        File _file1=null;
        File _file2=null;
        File _file3=null;
        Image src1 = null;
        Image src2 = null;
        Image src3 = null;
        BufferedImage tag=null;
        Graphics g=null;
        ConcurrentHashMap<String, String> bottom = new ConcurrentHashMap<String, String>();
        ConcurrentHashMap<String, String> qr = new ConcurrentHashMap<String, String>();
        ConcurrentHashMap<String, String> logo= new ConcurrentHashMap<String, String>();


        FileOutputStream out = null;
        if(sourceImgFile!=""){
            bottom= (ConcurrentHashMap<String, String>) map.get("bottom_param");
            System.out.println("1111111111111111111111111111111111111111"+Thread.currentThread().getName()+map);

            tag = new BufferedImage(Integer.valueOf(bottom.get("bottom_width")), Integer.valueOf(bottom.get("bottom_height")),
                    BufferedImage.TYPE_INT_RGB);
            // 创建输出流

            // 绘制合成图像
            g = tag.createGraphics();
            g.setColor(Color.WHITE);// 设置笔刷白色
            System.out.println("4444444444444444444444444"+Thread.currentThread().getName()+map);
            g.fillRect(Integer.valueOf(bottom.get("bottom_x")), Integer.valueOf(bottom.get("bottom_y")), Integer.valueOf(bottom.get("bottom_width")), Integer.valueOf(bottom.get("bottom_height")));// 填充整个屏幕
            g.setColor(Color.WHITE); // 设置笔刷
            g.setColor(Color.red);
            System.out.println("333333333333333333333333333333"+Thread.currentThread().getName()+map);
        }
        try {
            if(sourceImgFile!=""){
                _file1 = new File(sourceImgFile);
                src1 = ImageIO.read(_file1);
                System.out.println("555555555555555555555555555"+Thread.currentThread().getName()+map);
                g.drawImage(src1, Integer.valueOf(bottom.get("bottom_x")), Integer.valueOf(bottom.get("bottom_y")), Integer.valueOf(bottom.get("bottom_width")), Integer.valueOf(bottom.get("bottom_height")), null); // 底图
            }
            if(sourceQRFile!=""){
                _file2 = new File(sourceQRFile);
                src2 = ImageIO.read(_file2);
                qr= (ConcurrentHashMap<String, String>) map.get("qr_param");
                System.out.println("66666666666666666666666666"+Thread.currentThread().getName()+map);
                g.drawImage(src2, Integer.valueOf(qr.get("qr_x")), Integer.valueOf(qr.get("qr_y")), Integer.valueOf(qr.get("qr_width")), Integer.valueOf(qr.get("qr_height")),null);

            }
            if(headImgUrl!=""){
                _file3 = new File(headImgUrl);
                src3 = ImageIO.read(_file3);
                logo= (ConcurrentHashMap<String, String>) map.get("logo_param");
                g.drawImage(src3, Integer.valueOf(logo.get("logo_x")), Integer.valueOf(logo.get("logo_y")), Integer.valueOf(logo.get("logo_width")), Integer.valueOf(logo.get("logo_height")),null);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("99999999999999999999999999"+Thread.currentThread().getName()+map);
        // 释放此图形的上下文以及它使用的所有系统资源。
        g.dispose();
        System.out.println("000000000000000000000000000"+map);
        return tag;
    }

    /**
     * 合成二维码图片，并且添加文字
     *  sourceImgFile 图片路径
     *  sourceQRFile 二维码路径
     *  headImgFile  公司logo
     *
     *  context 页面显示内容
     *  x 文字显示的x坐标
     *  y 文字显示的y坐标
     * @throws Exception
     */
    public synchronized void exec(ConcurrentHashMap map) throws Exception {
        System.out.println(Thread.currentThread().getName()+map);
        ConcurrentHashMap<String, String> bottom_param = new ConcurrentHashMap<String, String>();
        bottom_param= (ConcurrentHashMap<String, String>) map.get("bottom_param");
        String sourceImgFile="";
        if(bottom_param.get("isUse").equals("Y")){
            sourceImgFile=bottom_param.get("bottom_img");
        }
        ConcurrentHashMap<String, String> qr_param = new ConcurrentHashMap<String, String>();
        qr_param= (ConcurrentHashMap<String, String>) map.get("qr_param");
        String sourceQRFile="";
        if(qr_param.get("isUse").equals("Y")){
            sourceQRFile=qr_param.get("qr_img");
        }
        ConcurrentHashMap<String, String> logo_param = new ConcurrentHashMap<String, String>();
        logo_param= (ConcurrentHashMap<String, String>) map.get("logo_param");
        String headImgFile="";
        if(logo_param.get("isUse").equals("Y")){
            headImgFile=logo_param.get("logo_img");
        }
        String destImgFile= (String) map.get("poster");
        BufferedImage tag = compose(sourceImgFile, sourceQRFile, headImgFile,destImgFile,map);
        try {
            ImageIO.write(tag, "png", new File(destImgFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConcurrentHashMap<String, String> name= new ConcurrentHashMap<String, String>();
        name=(ConcurrentHashMap<String, String>) map.get("name_param");
        System.out.println("============================================================="+name);
        String isUse=name.get("isUse");
        if(isUse.equals("Y")){
            String rgb=name.get("color");
            String[] str=rgb.split(",");
            int red= Integer.parseInt(str[0]);
            int green=Integer.parseInt(str[1]);
            int blue=Integer.parseInt(str[2]);
            modifyImage(destImgFile, String.valueOf(name.get("content")), Integer.valueOf(name.get("name_x")), Integer.valueOf(name.get("name_y")), new Color(red, green, blue), new Font(name.get("fontType"), Font.BOLD, Integer.valueOf(name.get("fontSize"))));
        }
    }

    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     */
    public synchronized void modifyImage(String destImgFile, Object content, int x, int y,Color color,Font font) {
        try {
            BufferedImage img = ImageIO.read(new File(destImgFile));
            Graphics2D g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(color);// 设置字体颜色
            if (font != null)
                g.setFont(font);

            if (content != null) {
                String text = content.toString();
                g.drawString(text, x, y);
            }
            g.dispose();
            FileOutputStream out = new FileOutputStream(destImgFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(img);
            // 关闭输出流
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
