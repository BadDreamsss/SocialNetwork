package sia.socialnetwork.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import sia.socialnetwork.data.ChatMessage;
import sia.socialnetwork.data.MessageStatus;
import sia.socialnetwork.exception.ResourceNotFoundException;
import sia.socialnetwork.repository.ChatMessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private MongoOperations mongoOperations;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String receiverId) {
        return repository.countBySenderIdAndReceiverIdAndStatus(
                senderId, receiverId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
        var chatId = chatRoomService.getChatId(senderId, receiverId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, receiverId, MessageStatus.DELIVERED);
        }

        return messages;

    }

    public ChatMessage findById(String id){
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void updateStatuses(String senderId, String receiverId, MessageStatus status) {
        Query query = new Query(
                Criteria
                        .where("senderId").is(senderId)
                        .and("receiverId").is(receiverId));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }

}
