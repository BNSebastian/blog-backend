package freevoice.shared;

import freevoice.core.user.Role;
import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    @Autowired
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }

    private void seedUsers() {
        UserEntity admin = new UserEntity();
        admin.setFirstname("seb");
        admin.setLastname("cheneb");
        admin.setEmail("seb@cheneb.com");
        admin.setPassword("$2a$10$r9ueA6pt.1oLoJRAoOXTYuG7akG9gpCDkKyNHdab2MbF3U8cOdjy2");
        admin.setRole(Role.ADMIN);

        userRepository.saveAll(List.of(admin));
    }
}
