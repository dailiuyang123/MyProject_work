package com.daily.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by json on 2018/4/12.
 * Describe: 借助Google提供的ZXing Core工具包，使用Java语言实现二维码的生成和解析
 */
public class ErWeiMaUtil {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    //
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
    //写成文件
    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }
    //写成流
    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @param width  宽
     * @param height 高
     * @param format 图片格式
     * @return 生成的二维码图片路径
     * @throws Exception
     */
    private static String generateQRCode(String text, int width, int height, String format) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        String pathName = "D:\\new.png";
        File outputFile = new File(pathName);
        ErWeiMaUtil.writeToFile(bitMatrix, format, outputFile);
        return pathName;
    }

    //生成二维码
    public static void main(String[] args) {
        try {
            generateQRCode("{name:'admin'}",200,200,"png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    /**
    *
    * 作者  json
    * 时间  2018/4/12 11:35
    * 描述 生成二维码
    *
     * data :生成的信息
    **/
    public void createErWeiMa(Map data){





    }


}
