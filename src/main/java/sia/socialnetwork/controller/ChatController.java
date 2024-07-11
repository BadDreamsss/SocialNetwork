package sia.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sia.socialnetwork.data.ChatMessage;
import sia.socialnetwork.data.ChatNotification;
import sia.socialnetwork.service.ChatMessageService;
import sia.socialnetwork.service.ChatRoomService;

@Controller
public class ChatController {

    @Autowired(required = false) private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessagesService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getReceiverId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessagesService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(), "/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()
        ));
    }

    @GetMapping("/messages/{senderId}/{receiverId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String receiverId
    ) {
        return ResponseEntity
                .ok(chatMessagesService.countNewMessages(senderId, receiverId));
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<?> findChatMessages(
            @PathVariable String senderId,
            @PathVariable String receiverId
    ) {
        return ResponseEntity
                .ok(chatMessagesService.findChatMessages(senderId, receiverId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(
            @PathVariable String id
    ) {
        return ResponseEntity
                .ok(chatMessagesService.findById(id));
    }

}
