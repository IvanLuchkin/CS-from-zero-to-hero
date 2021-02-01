package cinema.security.util;

public interface PasswordEncoder {
    String getHash(String password, byte[] salt);
}
