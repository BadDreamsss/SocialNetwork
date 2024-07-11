package sia.socialnetwork.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserFriends {

    @Id
    @PrimaryKeyJoinColumn(name = "user_id")
    private Long userId;
    @Id
    @PrimaryKeyJoinColumn(name = "user_id")
    private Long friendId;

}
