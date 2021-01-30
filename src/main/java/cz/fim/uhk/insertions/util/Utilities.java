package cz.fim.uhk.insertions.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public  class Utilities {
    public static JavaMailSender javaMailSender;

    /**
     * Get Date of today
     * @return Returns only Date without time
     */
    public  static Date getCurrentDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
    }

    public static Date getExpiredDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH,1);
        return cal.getTime();
    }

    static JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("insertionshorjir@gmail.com");
        mailSender.setPassword("insertionsHORJIR123");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public static void sendEmail() {
        javaMailSender = getJavaMailSender();
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("insertionshorjir@gmail.com");
        msg.setTo("jirasek.jiri32@gmail.com", "blackbenderr@gmail.com", "xsouda666x@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
    }
}
