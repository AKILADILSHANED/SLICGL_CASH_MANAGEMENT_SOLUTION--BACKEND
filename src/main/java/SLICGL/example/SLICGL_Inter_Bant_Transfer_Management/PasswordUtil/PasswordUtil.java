package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.PasswordUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean verifyPassword(String plainPassword, String encryptedPassword) {
        return encoder.matches(plainPassword, encryptedPassword);
    }
}
