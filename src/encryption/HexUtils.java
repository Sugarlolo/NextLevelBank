package encryption;

import java.math.BigInteger;

public class HexUtils {
    
    public static String bytesToHex(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);
        return bigInt.toString(16);
    }
    
    public static void main(String[] args) {
        byte[] bytes = {10, 15, 127};
        String hexString = bytesToHex(bytes);
        System.out.println(hexString); // Output: "a0f7f"
    }
}