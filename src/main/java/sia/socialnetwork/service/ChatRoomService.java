package sia.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.socialnetwork.data.ChatRoom;
import sia.socialnetwork.repository.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String receiverId, boolean createIfNotExists){
        return chatRoomRepository
                .findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExists) {
                        return Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId, receiverId);

                    ChatRoom senderReceiver = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .receiverId(receiverId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(receiverId)
                            .receiverId(senderId)
                            .build();
                    chatRoomRepository.save(senderReceiver);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }

}
