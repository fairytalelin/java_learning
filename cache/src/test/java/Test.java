import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        //Cache<String, String> cache = CacheBuilder.newBuilder().build();
        //cache.size();

        Queue<String> queue = new ArrayDeque<>();
        String str = "jjjj";
        String str1 = "xxxxxx";
        queue.add(str);
        queue.add(str1);

        queue.add(str);


        Set<String> set = new LinkedHashSet<>();

        set.add(str);
        set.add(str1);
        set.add(str);

        LinkedHashMap<String, String> map = new LinkedHashMap<>(16, .75f, true);
        map.put(str, str);
        map.put(str1, str1);
        map.put(str, str);

        map.keySet().forEach(item -> System.out.printf(item));
    }
}
