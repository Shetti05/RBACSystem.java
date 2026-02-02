import java.util.*;

public class RateLimiter {

    static final int LIMIT = 5;
    static final long WINDOW = 60000; // 1 minute

    static Map<String, List<Long>> users = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            request("user1");
            Thread.sleep(5000);
        }
    }

    static void request(String user) {
        long now = System.currentTimeMillis();
        users.putIfAbsent(user, new ArrayList<>());

        List<Long> timestamps = users.get(user);
        timestamps.removeIf(t -> now - t > WINDOW);

        if (timestamps.size() < LIMIT) {
            timestamps.add(now);
            System.out.println("✅ Request allowed");
        } else {
            System.out.println("❌ Rate limit exceeded");
        }
    }
}
