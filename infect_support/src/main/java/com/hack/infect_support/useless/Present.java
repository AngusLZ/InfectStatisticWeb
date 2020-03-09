package com.hack.infect_support.useless;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Present{
    public void present(String[] args) throws IOException {
        String strurl = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=singlemessage&isappinstalled=0";
        URL url = new URL(strurl);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is , "utf-8"));

        String line = null;

        FileOutputStream fos = new FileOutputStream(args[2]);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw = new BufferedWriter(osw);

        while ((line=br.readLine()) != null){
            String regex = "\\\"provinceShortName\\\"\\:\\\"([\\S]{2,4})\\\"\\,\\\"currentConfirmedCount\\\"\\:\\d+\\," +
                    "\\\"confirmedCount\\\":\\d+\\,\\\"suspectedCount\\\"\\:\\d+\\,\\\"curedCount\\\"\\:\\d+\\," +
                    "\\\"deadCount\\\"\\:\\d+\\,\\\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher ma = pattern.matcher(line);

            while(ma.find()){
                String[] gots = ma.group().split(",");
                String[] province = gots[0].split(":");
                String[] currentConfirm = gots[1].split(":");
                String[] confirm = gots[2].split(":");
                String[] cure = gots[4].split(":");
                String[] dead = gots[5].split(":");

                String osName = System.getProperty("os.name");
                if (osName.startsWith("Mac os")) {
                    bw.write("省份:" + province[1] + "  现存确诊数:" + currentConfirm[1] + "  累计确诊数:" + confirm[1] +
                            "  治愈人数:" + cure[1] + "  死亡人数:" + dead[1] + "\n");
                }else if(osName.startsWith("Windows")){
                    bw.write("省份:" + province[1] + "  现存确诊数:" + currentConfirm[1] + "  累计确诊数:" + confirm[1] +
                            "  治愈人数:" + cure[1] + "  死亡人数:" + dead[1] + "\r\n");
                }else {
                    bw.write("省份:" + province[1] + "  现存确诊数:" + currentConfirm[1] + "  累计确诊数:" + confirm[1] +
                            "  治愈人数:" + cure[1] + "  死亡人数:" + dead[1] + "\r");
                }
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        bw.write("//该报告截止于: " + time);
        br.close();
        bw.close();
        osw.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        String[] arg = {"a" , "b" , "/Users/a/Desktop/a.txt"};
        new Present().present(arg);
    }
}