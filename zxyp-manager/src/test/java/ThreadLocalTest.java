import io.xccit.zxyp.entity.system.SysUser;
import io.xccit.zxyp.utils.AuthContextUtil;
import org.junit.jupiter.api.Test;

/**
 * @author CH_ywx
 * @date 2023/10/17
 * @description
 */
public class ThreadLocalTest{
    public static void main(String[] args) {
        SysUser obj = AuthContextUtil.getObj();
        System.out.println(obj);
    }
}
