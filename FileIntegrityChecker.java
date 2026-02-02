import java.io.*;
import java.security.*;
import java.util.*;

public class FileIntegrityChecker {

    static Map<String, String> hashStore = new HashMap<>();

    public static void main(String[] args) throws Exception {
        File folder = new File("data");
        scanFiles(folder);

        System.out.println("Initial scan completed.");
        System.out.println("Re-run program to detect changes.");
    }

    static void scanFiles(File folder) throws Exception {
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                String hash = generateHash(file);
                String name = file.getName();

                if (hashStore.containsKey(name) && 
                    !hashStore.get(name).equals(hash)) {
                    System.out.println("⚠ File modified: " + name);
                }

                hashStore.put(name, hash);
            }
        }
    }

    static String generateHash(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int n;

        while ((n = fis.read(buffer)) != -1) {
            digest.update(buffer, 0, n);
        }
        fis.close();

        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
