package Crawler;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public interface Logger{
    void log(String status, Student student);
}
class ConsoleLogger implements Logger
{

    @Override
    public void log(String status, Student student) {
        System.out.println(status + " " + student.getMark() + " " + student.getName() + " " + student.getLastname() + " " + student.getAge());
    }
}
class MailLogger implements Logger{

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final String FROM = "java8project@gmail.com";
    private static final String PASSWORD = "stackoverflow";
    private static final String TO = "java8project@gmail.com";

    @Override
    public void log(String status, Student student) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");

        Session mailSession = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(mailSession);

        try
        {
            message.setSubject(status);
            message.setContent(student.getMark() + " " + student.getName() + " " + student.getLastname() + " " + student.getAge(), "text/plain; charset=ISO-8859-2");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            Transport transport = mailSession.getTransport();
            transport.connect(HOST, PORT, FROM, PASSWORD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
