package server.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс хэширование данных из строки в SHA-256
 */
public class StringToHashUtil {

    /**
     * Массив байтов в Hex строку
     *
     * @param bytes массив байтов
     * @return Hex строка
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * Хэширование данных в SHA-256
     *
     * @param data строка для хэширования
     * @return Hex строка
     */
    public static String convert(String data) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return toHexString(digest.digest(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}