package com.zlcd.qrcode.util;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class QrcodeTest {
    public static void main(String[] args){
        boolean[][] booleans =null;
        String content = "今天是个好日子";
        booleans = getBooleanBycoent(content);
        int v =6;
        int width = 67 + 12 * (v - 1);
        int height = 67 + 12 * (v - 1);
        String path = "D:\\zlcd\\test\\new.jpg";
        File file = new File(path);
        // 1.以文件的形式存在 2.不存在创建出来  3.时刻变化的（录屏） 捕捉
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        // 获取绘图的对象
        Graphics2D gg = bufferedImage.createGraphics();

        // 设置背景色
        gg.setBackground(Color.white);
        gg.fillRect(0,0,width,height);

        // 设置前景色  画笔的颜色
        gg.setColor(Color.black);

        //偏移量
        int pixoff = 2;
        // 二维 循环 将所有的内容填充
        for(int i = 0,bl = booleans.length; i < bl;i++){
            for (int j = 0; j < bl;j++){
                if (booleans[i][j])
                gg.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
            }
        }


        gg.dispose();
        bufferedImage.flush();
        try {
            ImageIO.write(bufferedImage,"jpg",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean[][] getBooleanBycoent(String content){
        boolean[][] booleans =null;
        Qrcode qrcode = new Qrcode();
        // 设置编码方式
        qrcode.setQrcodeEncodeMode('B');
        // 容错率 L7 M15 Q25 R30
        qrcode.setQrcodeErrorCorrect('R');
        // 版本1--40
        qrcode.setQrcodeVersion(6);
        try {
            booleans = qrcode.calQrcode(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return booleans;
    }
}
