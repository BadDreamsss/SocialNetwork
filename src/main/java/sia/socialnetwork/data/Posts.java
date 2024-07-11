package sia.socialnetwork.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @PrimaryKeyJoinColumn(name = "user_id")
    private Long userId;
    private String content;
    @Column(name = "created_at")
    private Date createdAt;

}
