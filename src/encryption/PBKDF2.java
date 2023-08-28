package encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import beans.MemberBean;

public class PBKDF2 {
    
    MemberBean bean;
    
    public PBKDF2(String password) {
        // 랜덤한 salt 값을 생성합니다.
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        
        // PBKDF2 알고리즘을 사용하여 비밀번호를 암호화하여 저장합니다.
        try {
            String hashedPassword = hashPassword(password, salt);
            bean.setHashedPassword(hashedPassword);
            bean.setSalt(HexUtils.bytesToHex(salt));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        	e.printStackTrace();
        }
    }
    
    private String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        int keyLength = 256;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return HexUtils.bytesToHex(hash);
    }
    
}