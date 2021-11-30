import Data.Message
import Data.User
import Services.ChatService
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatServiceTest {

    @Test
    fun addUserToData() {
        // arrange
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val chatService = ChatService()

        // act
        val result = chatService.addUserToData(elena)

        // assert
        assertTrue(result)
    }

    @Test
    fun addUserToData_False() {
        // arrange
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val anotherLena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val chatService = ChatService()

        // act
        chatService.addUserToData(elena)
        val result = chatService.addUserToData(anotherLena)

        // assert
        assertFalse(result)
    }

    @Test
    fun addMessage() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        val result = chatService.addMessage(message1)

        //assert
        assertEquals(result, 1)
    }

    @Test
    fun addMessage_SecondChat() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val petr = User(
            id = 3,
            firstName = "Петр",
            lastName = "Петров",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )
        val message3 = Message(
            dateTime = formatted,
            text = "three",
            senderId = 1,
            recipientId = 3
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addUserToData(petr)
        chatService.addMessage(message1)
        val result = chatService.addMessage(message3)

        //assert
        assertEquals(result, 2)
    }

    @Test
    fun deleteMessage_True() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.deleteMessage(0)

        //assert
        assertTrue(result)
    }

    @Test
    fun deleteMessage_False() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.deleteMessage(5)

        //assert
        assertFalse(result)
    }

    @Test
    fun deleteChatById_True() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val chatKey = listOf(1, 2)
        val result = chatService.deleteChatById(chatKey)

        //assert
        assertTrue(result)
    }

    @Test
    fun deleteChatById_False() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val chatKey = listOf(5, 2)
        val result = chatService.deleteChatById(chatKey)

        //assert
        assertFalse(result)
    }

    @Test
    fun editingMessage_True() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )
        val updatedMessage = Message(
            id = 0,
            dateTime = formatted,
            text = "qvaqvaqva",
            senderId = 1,
            recipientId = 2
        )

        // act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.editingMessage(updatedMessage)

        // assert
        assertTrue(result)
    }

    @Test
    fun editingMessage_False() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )
        val updatedMessage = Message(
            id = 5,
            dateTime = formatted,
            text = "UPDATED!!!",
            senderId = 1,
            recipientId = 2
        )

        // act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.editingMessage(updatedMessage)

        // assert
        assertFalse(result)
    }

    @Test
    fun getChatList() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )

        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.getChatList()

        // assert
        assertEquals(result, mutableMapOf(Pair((1), mutableListOf(message1))))
    }

    @Test
    fun getMessagesFromChat() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        var chatMessageList = listOf<Message>()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            id=0,
            dateTime = formatted,
            text = "one",
            readStatus = false,
            senderId = 1,
            recipientId = 2
        )
        //act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.getMessagesFromChat(listOf(),0,0)

        // assert
        assertEquals(result, chatMessageList)
    }

    @Test
    fun getUnreadChats() {
        // arrange
        val time: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formatted: String = time.format(formatter)
        val chatService = ChatService()
        val ivan = User(
            id = 1,
            firstName = "Иван",
            lastName = "Иванов",
            emptyList(),
            emptyList()
        )
        val elena = User(
            id = 2,
            firstName = "Елена",
            lastName = "Ивановна",
            emptyList(),
            emptyList()
        )
        val message1 = Message(
            dateTime = formatted,
            text = "one",
            senderId = 1,
            recipientId = 2
        )
        // act
        chatService.addUserToData(ivan)
        chatService.addUserToData(elena)
        chatService.addMessage(message1)
        val result = chatService.getUnreadChats(1)

        // assert
        assertTrue(result.size == 1)
    }
}