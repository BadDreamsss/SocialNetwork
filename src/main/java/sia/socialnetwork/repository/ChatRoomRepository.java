package sia.socialnetwork.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sia.socialnetwork.data.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String>{

    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String receiverId);

}
