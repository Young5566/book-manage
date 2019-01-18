import java.util.UUID;
public class test {
    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
