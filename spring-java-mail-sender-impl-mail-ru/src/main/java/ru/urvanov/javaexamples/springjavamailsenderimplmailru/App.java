package ru.urvanov.javaexamples.springjavamailsenderimplmailru;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Пример отправки писем с помощью JavaMailSenderImpl через почтовый
 * сервис mail.ru. Убедитесь, что вы заменили mail.username, mail.password
 * и mail.from в src/main/resources/mail.properties на настройки своей учётной
 * записи почты.
 * 
 * @author Urvanov Fedor
 *  <p><a href="http://urvanov.ru">http://urvanov.ru</a>
 *  </p>
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {
            context.load("classpath:applicationContext.xml");
            context.refresh();
            JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
            SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);
            
            // Создаём потокобезопасную копию шаблона.
            SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
            
            //TODO: Сюда напишите свой e-mail получателя.
            mailMessage.setTo("ouhb93u4hng9hndf9@mail.ru");
            
            mailMessage.setText("Привет, товарищи. Присылаю вам письмо...");
            try {
                mailSender.send(mailMessage);
                System.out.println("Mail sended");
            } catch (MailException mailException) {
                System.out.println("Mail send failed.");
                mailException.printStackTrace();
            }
        }
    }
}
