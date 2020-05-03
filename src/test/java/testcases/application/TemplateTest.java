package testcases.application;

import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateTest {
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }



    @Test
    public void f1() throws IOException, InterruptedException {
        String regx="date\\((.*?)\\)";//正则表达式
        Pattern p=Pattern.compile(regx);
        String t="to_char(date(XXXX)-1,'%Y%m%d')to_char(date(YYYY)-1,'%Y%m%d')";//原字符串
        Matcher m=p.matcher(t);
        while(m.find()){
            String s=m.group(1);//括号里的内容
            String s1=m.group();//需要替换的字符串
            t=t.replace(s1,"to_date("+s+",'yymmdd')");//替换
        }
        System.out.println(t);
    }


    @Test
    public void f2() throws IOException, InterruptedException {
        String regx="updateTime:(\\d{3})";//正则表达式
        Pattern p=Pattern.compile(regx);
        String t="yzwf:9,updateTime:123,jyyc:6yzwf:9,updateTime:456,jyyc:6";//原字符串
        Matcher m=p.matcher(t);
        while(m.find()){
            String s=m.group(1);//括号里的内容
            String s1=m.group();//需要替换的字符串
            t=t.replace(s1,"updateTime:555");//替换
        }
        System.out.println(t);
    }


    @Test
    public void f3() throws IOException, InterruptedException {
        String regx="\"updateTime\":(.*?)}";//正则表达式
        Pattern p=Pattern.compile(regx);
        String old="\"updateNote\":\"迁出原因\",\"updateTime\":1513860858000},\"history\":[{\"applyId\"";//原字符串
        String t=old;
        Matcher m=p.matcher(t);
        while(m.find()){
            String s=m.group(1);//括号里的内容
            String s1=m.group();//需要替换的字符串
            t=t.replace(s1,"\"updateTime\":\"973872000000\"}");//替换
        }
        System.out.println(t);
    }//


    @Test
    public void f4() throws IOException, InterruptedException {
        String regx="\"id\":(.*?)";//正则表达式
        Pattern p=Pattern.compile(regx);
        String old="\"id\":666";//原字符串
        String t=old;//原字符串
        Matcher m=p.matcher(t);

        while(m.find()){
            String s=m.group(1);//括号里的内容
            String s1=m.group();//需要替换的字符串
            t=t.replace(s1,"777");//替换
        }

        String news=t;
        System.out.println(news);
    }//



    @Test
    public void f5() throws IOException, InterruptedException {
        String res;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        res = String.valueOf(now.getTime());
    }//
}
