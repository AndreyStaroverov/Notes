package notes.severstal.service;

import notes.severstal.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordHashAndSalt {

    private static final Logger log = LoggerFactory.getLogger(PasswordHashAndSalt.class);

    public String hashedAndSalt(String password) {
        log.info("Хэширования пароля пользователя");
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User checkPassword(User user) {
        try {
            if (BCrypt.checkpw(user.getPassword(), user.getPassword())) {
                log.info(String.format("Проверка пароля 'success' пользователя с логином %s" , user.getName()));
                return user;
            } else {
                log.info(String.format("Проверка пароля 'denied' пользователя с логином %s" , user.getName()));
                return null;
            }
        } catch (NullPointerException e) {
            log.debug(String.format("NullPointer exc. при проверке " +
                    "пароля пользователя с логином %s" , user.getName()));
            return null;
        }
    }
}
