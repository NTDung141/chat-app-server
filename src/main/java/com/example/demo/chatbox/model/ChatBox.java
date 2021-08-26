package com.example.demo.chatbox.model;

import com.example.demo.message.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "chatbox")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBox {
    private String id;
    private List<String> userIdList;
    private String chatBoxName;
    private Message lastMessage;
}
