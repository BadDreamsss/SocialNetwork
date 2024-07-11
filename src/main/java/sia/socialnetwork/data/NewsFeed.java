package sia.socialnetwork.data;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class NewsFeed {

    @OneToOne(mappedBy = "user_id")
    @Column(name = "user_id")
    private UserTable userId;

    @OneToOne(mappedBy = "postId")
    @Column(name = "post_id")
    private Posts postId;
    @Column(name = "created_at")
    private Date createdAt;

}
