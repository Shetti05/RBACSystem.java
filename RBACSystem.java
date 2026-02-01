import java.util.*;

enum Role {
    ADMIN, MANAGER, EMPLOYEE
}

public class RBACSystem {

    static Map<Role, Set<String>> permissions = new HashMap<>();

    public static void main(String[] args) {

        permissions.put(Role.ADMIN, Set.of("READ", "WRITE", "DELETE"));
        permissions.put(Role.MANAGER, Set.of("READ", "WRITE"));
        permissions.put(Role.EMPLOYEE, Set.of("READ"));

        checkAccess("EMPLOYEE", "DELETE");
        checkAccess("MANAGER", "WRITE");
        checkAccess("ADMIN", "DELETE");
    }

    static void checkAccess(String role, String action) {
        Role userRole = Role.valueOf(role);
        if (permissions.get(userRole).contains(action)) {
            System.out.println("✅ Access Granted");
        } else {
            System.out.println("❌ Access Denied");
        }
    }
}
