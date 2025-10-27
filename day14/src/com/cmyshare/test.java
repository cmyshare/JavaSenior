package com.cmyshare;

import org.omg.CORBA.UserException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2024/7/11 14:53
 * @desc
 */
public class test {
    public static void main(String[] args) {
        //Set<Long> getMaterialsFileIdsAll = new HashSet<>();
        //System.out.println(getMaterialsFileIdsAll.stream().collect(Collectors.toList()));
        //System.out.println(new ArrayList<>(getMaterialsFileIdsAll));
        //
        //String wirelessDesc = "<p class=\"ql-align-center\"><strong>尺码推荐表</strong></p><p class=\"ql-align-center\"><img src=\"https://img.alicdn.com/imgextra/i2/477653753/O1CN01hjCR3k1davPVpO09C_!!477653753.jpg\"></p>";
        //wirelessDesc=wirelessDesc.replace("class=\"ql-align-center\"", "style=\"text-align: center;\"");
        //System.out.println(wirelessDesc);
        //
        //try {
        //    //http://chaoo.ltd:9000/chaoo-cds-bucket/2023/11/14/3fd2f6c5149f421083c98c1841da47e1.png
        //    String url="https://chaoo.ltd:9000/chaoo-cds-bucket/2023/11/14/3fd2f6c5149f421083c98c1841da47e1.png";
        //    //HTTP客户端，获取输入流、图片宽高
        //    URL urlNew = new URL(url);
        //    HttpURLConnection urlConnection = (HttpURLConnection) urlNew.openConnection();
        //    InputStream inputStream = urlConnection.getInputStream();
        //
        //    //店铺商品待优化
        //    BufferedImage bufferedImage = ImageIO.read(new URL(url));
        //    int width = bufferedImage.getWidth();
        //    int height = bufferedImage.getHeight();
        //
        //    System.out.println(inputStream+":"+width+height);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //
        //List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //List<Integer> integers1 = integers.subList(0, 3);
        //System.out.println(integers1);
        ////近几天就
        //List<Integer> integers2 = integers.subList(integers.size()-3, integers.size());
        //System.out.println(integers2);

        //String countriesNumber="PH";
        //String skuId="1095-BLACK-M";
        //String[] splitAll = skuId.split("\\*");
        ////国家固定字符/印花片 CP/Y000009
        //String s0 = splitAll[0];
        ///** 取出印花片 Y000009 菲律宾:"/" 泰国:"-" **/
        //String[] splitAll1 = "PH".equals(countriesNumber) ?s0.split("/"):s0.split("-");
        //String printedCode = splitAll1[1];
        ////判断去首字母的印花片数字位数，4位带上国家前缀、6位不处理
        //String printedsub = printedCode.substring(1);
        //if (4 == printedsub.length()) {
        //    //印花片加国家前缀，THY000009
        //    printedCode = countriesNumber + printedCode;
        //}
        //
        ////光板-颜色-尺码 KU-77220-Wine red-2XL、KU77220-Wine red-2XL
        //String s1 = splitAll[1];
        //String[] split = s1.split("-");
        ////取出spu前缀
        //String prefixEn = split[0];
        ////取出spu其余
        //String spuNumberOther = 4 == split.length ? split[1] : "";


        String s1="P3061";
        //去除前缀英文信息
        String printingCategoryNew = s1.substring(1);
        System.out.println(printingCategoryNew);
    }
}
