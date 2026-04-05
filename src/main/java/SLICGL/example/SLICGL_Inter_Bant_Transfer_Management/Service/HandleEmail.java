package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class HandleEmail {
    @Autowired
    private JavaMailSender mailSender;

    public void sendTemporaryPasswordEmail(String toEmail, String userId, String fName, String lName, String tempPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("edirisooriyaakila@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Your New Account Credentials");

            String emailBody = "Dear User,\n\n"
                    + "Your account has been successfully created for Daily Inter Bank Transfers Management system.\n\n"
                    + "User ID: " + userId + "\n"
                    + "User Name: " + fName + " " + lName + "\n"
                    + "Temporary Password: " + tempPassword + "\n\n"
                    + "Please login and reset your password immediately.\n\n"
                    + "Best regards,\n"
                    + "Sri Lanka Insurance Corporation Life Limited";

            message.setText(emailBody);
            mailSender.send(message);
        }catch (MailException e){
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendPasswordResetEmail(String toEmail, String userId, String fName, String lName, String tempPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("edirisooriyaakila@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Password Reset Notification");

            String emailBody = "Dear User,\n\n"
                    + "Your password has been reset successfully. Please use the temporary credentials below to log in and immediately set a new password.\n\n"
                    + "User ID: " + userId + "\n"
                    + "User Name: " + fName + " " + lName + "\n"
                    + "Temporary Password: " + tempPassword + "\n\n"
                    + "Please login and reset your password immediately.\n\n"
                    + "Best regards,\n"
                    + "Sri Lanka Insurance Corporation Life Limited";

            message.setText(emailBody);
            mailSender.send(message);
        }catch (MailException e){
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
