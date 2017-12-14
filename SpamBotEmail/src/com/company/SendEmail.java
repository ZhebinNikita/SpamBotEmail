package com.company;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



public class SendEmail implements Runnable  { // New Task for Thread

    public static int countMessages = 0;


    private int countRepeat = 0;
    private long delay = 0;



    public SendEmail(int countRepeat, long delay)
    {
        this.countRepeat = countRepeat;
        this.delay = delay;
    }



    @Override
    public void run() {

        try {
            for (int i = 0; i < countRepeat; i++) {

                sendSimpleMessage(EmailData.login, EmailData.password, EmailData.address, EmailData.address,
                        EmailData.content, EmailData.subject, EmailData.smtpPort, EmailData.smtpHost);

                TimeUnit.MILLISECONDS.sleep(delay); // 4 sek

                countMessages++;

                System.out.println("\nMessage " + (i+1) + " sent");
            }
        } catch (Exception ex) {
            System.err.println("Interrupted");
        }


    }







    static final String ENCODING = "UTF-8";

    public static void sendSimpleMessage(String login, String password, String from, String to, String content,
                                         String subject, String smtpPort, String smtpHost)
            throws MessagingException, UnsupportedEncodingException {
        Authenticator auth = new MyAuthenticator(login, password);

        Properties props = System.getProperties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);
        Session session = Session.getDefaultInstance(props, auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);
    }







}
