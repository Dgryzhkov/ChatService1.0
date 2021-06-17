import Data.Message
import Services.ChatService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val time: LocalDateTime = LocalDateTime.now()
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    val formatted: String = time.format(formatter)
    val chatService = ChatService()



    val message1 = Message(
        dateTime = formatted,
        text = "one",
        senderId = 1,
        recipientId = 2
    )
    val message2 = Message(
        dateTime = formatted,
        text = "two",
        senderId = 1,
        recipientId = 4
    )
    val message3 = Message(
        dateTime = formatted,
        text = "three",
        senderId = 1,
        recipientId = 3
    )
    val message4 = Message(
        dateTime = formatted,
        text = "four",
        senderId = 3,
        recipientId = 1
    )
    val message5 = Message(
        dateTime = formatted,
        text = "five",
        senderId = 2,
        recipientId = 1
    )
    val message6 = Message(
        dateTime = formatted,
        text = "six",
        senderId = 2,
        recipientId = 1
    )
    val message7 = Message(
        dateTime = formatted,
        text = "seven",
        senderId = 2,
        recipientId = 1
    )
    val message8 = Message(
        dateTime = formatted,
        text = "eight",
        senderId = 2,
        recipientId = 3
    )
    val message9 = Message(
        dateTime = formatted,
        text = "nine",
        senderId = 2,
        recipientId = 1
    )
    val message10 = Message(
        dateTime = formatted,
        text = "ten",
        senderId = 4,
        recipientId = 2
    )
    val message11 = Message(
        dateTime = formatted,
        text = "eleven",
        senderId = 1,
        recipientId = 2
    )
    val updatedMessage = Message(
        id = 1,
        dateTime = formatted,
        text = "qoqoqoqoqo",
        senderId = 1,
        recipientId = 2
    )

    chatService.addUserToData(ivan)
    chatService.addUserToData(elena)
    chatService.addUserToData(petr)
    chatService.addUserToData(alex)

    chatService.addMessage(message1)
    chatService.addMessage(message2)
    chatService.addMessage(message3)
    chatService.addMessage(message4)
    chatService.addMessage(message5)
    chatService.addMessage(message6)
    chatService.addMessage(message7)
    chatService.addMessage(message8)
    chatService.addMessage(message9)
    chatService.addMessage(message10)
    chatService.addMessage(message11)

    chatService.editingMessage(updatedMessage)
    chatService.getUnreadChats(1)
    chatService.deleteMessage(8)
    chatService.deleteChatById(listOf(3, 1))
    chatService.getMessagesFromChat(chatId = listOf(1, 2), 5, 2)
}