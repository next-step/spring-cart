package cart.auth.authentication;

public class SecurityContextHolder {
    private static final ThreadLocal<Auth> contextHolder;

    static {
        contextHolder = new ThreadLocal<>();
    }

    public static Auth getContext() {
        return contextHolder.get();
    }

    public static void setContext(Auth auth) {
        contextHolder.set(auth);
    }

    public static void clearContext() {
        contextHolder.remove();
    }

}
