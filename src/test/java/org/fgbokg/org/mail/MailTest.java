package org.fgbokg.org.mail;

import org.fgbokg.org.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送的测试类
 */
public class MailTest extends BaseTest {

    /** 注入对象 */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送普通邮件
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        // 创建SimpleMailMessage对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置是谁要发送
        mailMessage.setFrom("522610972@qq.com");
        // 设置发送对象
        // mailMessage.setTo("sunfulingq@163.com");
        mailMessage.setTo("1494598731@qq.com");
        // 设置需要发送的信息，只能发送纯文本
        mailMessage.setSubject("牛逼大学通知书");// 设置标题
        mailMessage.setText("由于你很吊，所以被屌爆了牛逼大学所录取!");// 设置内容
        // 设置到邮箱
        mailSender.send(mailMessage);
    }

    @Test
    public void test2() throws Exception {
        // 创建复杂邮箱
        MimeMessage message = mailSender.createMimeMessage();
        // 创建一个工具类，该工具类向空邮件中写内容
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

        mimeMessageHelper.setFrom("522610972@qq.com");
        mimeMessageHelper.setTo("sunfulingq@163.com");
        // mimeMessageHelper.setTo("1494598731@qq.com");
        mimeMessageHelper.setSubject("青楼");
        mimeMessageHelper.setText("<a href='http://115.159.217.249:8888//group1/M00/00/3C/rBEAA1_wWpaAN9HlAAPBFWM68W4646.jpg'>点我呀，有惊喜</a>" ,true);
        mimeMessageHelper.addAttachment("小姐姐.jpg", new File("F:\\壁纸\\1\\2030296.jpg"));
        mailSender.send(message);

    }
}
