import java.util.HashMap;
import java.util.Map;

public class SynchronizedTest {
    private Map<String, String> maps = new HashMap<>();

    void change() {
        synchronized (maps) {
            maps.put("name", "claylin");
        }
    }
}
