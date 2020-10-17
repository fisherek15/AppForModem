/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Adrian
 */
public class Conversion {

    private static final List<String> hexChars = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F"));

    public static boolean hexValidation(String text) {

        String[] characters = text.split("");
        int isParity = characters.length % 2;
        if (characters.length > 0 && isParity == 0) {
            for (String _char : characters) {
                if (!hexChars.contains(_char)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static byte[] stringToBytes(String text) {

        String[] characters = text.split("");
        String hex;
        byte[] dataTable = new byte[characters.length / 2];
        int num;
        int byteNum = 0;

        for (int i = 0; i < characters.length; i += 2) {
            hex = (characters[i] + characters[i + 1]);
            num = Integer.parseInt(hex, 16);
            dataTable[byteNum++] = (byte) num;
        }
        return dataTable;
    }

    public static void sendToBinaryFile(byte[] data) {

        FileOutputStream fos;
        try {
            fos = new FileOutputStream("out");
            fos.write(data);
            fos.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexTab = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexTab[j * 2] = hexArray[v >>> 4];
            hexTab[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexTab);
    }

    // zamienia avr bajt 'a' na java int bez znaku (odpowiednik w języku Java typowi AVR uint_8)
    public static int unsignedToBytes(byte a) {
        int b = (a & 0xFF);
        return b;
    }

}
