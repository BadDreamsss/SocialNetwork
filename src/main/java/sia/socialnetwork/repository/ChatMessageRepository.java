package sia.socialnetwork.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sia.socialnetwork.data.ChatMessage;
import sia.socialnetwork.data.MessageStatus;

import java.util.List;

@EnableMongoRepositories
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String>{

    long countBySenderIdAndReceiverIdAndStatus(String senderId, String receiverId,MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

}
