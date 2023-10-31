import org.bouncycastle.jce.provider.BrokenJCEBlockCipher;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.util.DigestUtils;

/**
 * @author CH_ywx
 * @date 2023/10/18
 * @description
 */
public class LockPassTest {
    public static void main(String[] args) {
        /*String lockPass = DigestUtils.md5DigestAsHex("admin123".getBytes());
        System.out.println(lockPass);*/

        // PBEWithHMACSHA512AndAES_256  PBEWithMD5AndDES
/*        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("xccit_zxyp");
        String myEncryptedText = textEncryptor.encrypt("1209");
        //加密后的结果
        System.out.println(myEncryptedText);
        String plainText = textEncryptor.decrypt(myEncryptedText);
        //解密后的结果
        System.out.println(plainText);*/

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        //配置文件中配置如下的算法
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        //配置文件中配置的password
        standardPBEStringEncryptor.setPassword("xccit_zxyp");
        //要加密的文本
        String name = standardPBEStringEncryptor.encrypt("root");
        String password = standardPBEStringEncryptor.encrypt("1209");
        String redisPassword = standardPBEStringEncryptor.encrypt("1209");
        //将加密的文本写到配置文件中
        System.out.println("name=" + name);
        System.out.println("password=" + password);
        System.out.println("redisPassword=" + redisPassword);

        //要解密的文本
        String name2 = standardPBEStringEncryptor.decrypt("BP4U956lxVkoQ3rtpR8Y/A==");
        String password2 = standardPBEStringEncryptor.decrypt("EDQXW067ZXhOYCGZ29cLUQ==");
        String redisPassword2 = standardPBEStringEncryptor.decrypt("meW6k6oQtSYhxfoCQE34Iw==");
        //解密后的文本
        System.out.println("name2=" + name2);
        System.out.println("password2=" + password2);
        System.out.println("redisPassword2=" + redisPassword2);
    }
}
