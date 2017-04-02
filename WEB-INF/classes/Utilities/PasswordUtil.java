/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.*;
/**
 *
 * @author saiku
 */
public class PasswordUtil {
    public static String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray=md.digest();
        StringBuilder sb=new StringBuilder(mdArray.length*2);
        for(byte b:mdArray){
            int v=b&0xff;
            if(v<16)
                sb.append('0');
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();        
    }
    
    
    public static String getSalt(){
        Random r=new SecureRandom();
        byte[] saltBytes=new byte[32];
        r.nextBytes(saltBytes);
        return getBase64(saltBytes);
        
    }
    
    
    public static String hashAndSaltPassword(String email, String password) throws NoSuchAlgorithmException{
        String salt=getSalt();        
        String spass=hashPassword(password+salt);
        
        return spass;
    }
    
    

    public static String getBase64(byte[] data)
    {
        char[] tbl = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
            'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f',
            'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
            'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/' };

        StringBuilder buffer = new StringBuilder();
        int pad = 0;
        for (int i = 0; i < data.length; i += 3) {

            int b = ((data[i] & 0xFF) << 16) & 0xFFFFFF;
            if (i + 1 < data.length) {
                b |= (data[i+1] & 0xFF) << 8;
            } else {
                pad++;
            }
            if (i + 2 < data.length) {
                b |= (data[i+2] & 0xFF);
            } else {
                pad++;
            }

            for (int j = 0; j < 4 - pad; j++) {
                int c = (b & 0xFC0000) >> 18;
                buffer.append(tbl[c]);
                b <<= 6;
            }
        }
        for (int j = 0; j < pad; j++) {
            buffer.append("=");
        }

        return buffer.toString();
    }


    public static boolean checkPasswordStrength(String password){
        if(password==null&&password.trim().isEmpty())
            return false;
        else{
            String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*+-=;:,./\\?])(?=\\S+$).{8,}$";
            return password.matches(pattern);
            }        
    }
    
}
