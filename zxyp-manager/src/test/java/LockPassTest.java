import org.springframework.util.DigestUtils;

/**
 * @author CH_ywx
 * @date 2023/10/18
 * @description
 */
public class LockPassTest {
    public static void main(String[] args) {
        String lockPass = DigestUtils.md5DigestAsHex("admin123".getBytes());
        System.out.println(lockPass);
    }
}
