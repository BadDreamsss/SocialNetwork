package sia.socialnetwork.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sia.socialnetwork.data.UserTable;

import java.util.Date;

@Data
public class RegistrationForm {

    private final String user_name;
    private final String first_name;
    private final String last_name;
    private final String password;
    private final Date birth_date;
    private final String role;

    public UserTable toUser(BCryptPasswordEncoder passwordEncoder){
        return new UserTable(
                user_name, first_name, last_name, passwordEncoder.encode(password), birth_date, "USER"
        );
    }

}
