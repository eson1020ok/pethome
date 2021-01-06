package org.fgbokg.basic.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class SendMsgUtil {
    //本站用户名uid
    private static final String UID = "蓝色迷鹿";
    //短信秘钥
    private static final String KEY = "d41d8cd98f00b204e980";
    public static void sendMsg(String phone,String content){
        PostMethod post = null;

        try {
            //创建Http客户端
            HttpClient client = new HttpClient();
            //创建post请求
            post = new PostMethod("http://utf8.api.smschinese.cn/");
            //添加请求头信息
            post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
            //请求体参数
            NameValuePair[] data ={
                    new NameValuePair("Uid", UID),
                    new NameValuePair("Key", KEY),
                    new NameValuePair("smsMob",phone),
                    new NameValuePair("smsText",content)};
            //设置请求体
            post.setRequestBody(data);
            //执行post请求
            client.executeMethod(post);
            //获取响应头
            Header[] headers = post.getResponseHeaders();
            //获取状态码
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:"+statusCode);
            //循环打印响应头信息
            for(Header h : headers)
            {
                System.out.println(h.toString());
            }
            //获取短信服务器那边响应的内容
            String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
            System.out.println(result); //打印返回消息状态

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (post != null) {
                post.releaseConnection();
            }
        }

    }
}
