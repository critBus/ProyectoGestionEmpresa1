/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.util.Base64;
import java.util.Random;
import java.util.stream.IntStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Rene2
 */
public abstract class Encriptar {
    public static String encriptar(String strClearText)throws Exception{
         return Base64.getEncoder().encodeToString(strClearText.getBytes("utf-8"));
//        Random r=new Random();
//        
//        String strKey="keyKrito@Leyen";
//        
//        
//        String strData="";
//        SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
//        Cipher cipher = Cipher.getInstance("Blowfish");
//        cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
//        byte[] encrypted = cipher.doFinal(strClearText.getBytes());
//        strData = new String(encrypted);
//        return strData;
    }
    
    
    
}
