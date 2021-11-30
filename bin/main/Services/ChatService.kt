package Services

import Data.Message
import Data.User

class ChatService {
    private var chats: MutableMap<List<Int>, MutableList<Message>> = mutableMapOf()
    private var usersData = mutableListOf<User>()
    private var lastId = 1

    fun addUserToData(user: User): Boolean {
        return if (usersData.contains(user)) {
            println("Пользователь с таким Id уже существует!!")
            false
        } else {
            usersData.plusAssign(user)
            true
        }
    }

    fun addMessage(message: Message): Int {
        val key: List<Int> = listOf(message.senderId, message.recipientId)
        val newMessage = message.copy(id = lastId++)

        if (!chats.containsKey(key) && !chats.containsKey(key.reversed())) {
            chats[key] = mutableListOf(newMessage)
        } else {
            chats.forEach { (k, v) ->
                if (k.contains(message.senderId) && k.contains(message.recipientId)) {
                    chats[k] = v.plusElement(newMessage) as MutableList<Message>
                }
            }
        }
        return chats.size
    }

    fun deleteMessage(messageId: Int): Boolean {
        val externalIterator = chats.iterator()
        externalIterator.forEach { entry ->
            val interiorIterator = entry.value.iterator()
            interiorIterator.forEach { message: Message ->
                if (message.id == messageId) {
                    val n = entry.value.filterNot { it.id == messageId } as MutableList
                    chats[entry.key] = n
                    if (entry.value.isEmpty()) {
                        externalIterator.remove()
                    }
                    return true
                }
            }
        }
        println("Сообщения с таким ID не найдено!!")
        return false
    }

    fun deleteChatById(chatId: List<Int>): Boolean {
        val iterator = chats.iterator()
        iterator.forEach {
            if (it.key == chatId || it.key == chatId.reversed()) {
                iterator.remove()
                return true
            }
        }
        println("Чат с данным Id не найден!!")
        return false
    }

    fun editingMessage(updatedMessage: Message): Boolean {
        chats.forEach { (_: List<Int>, value: MutableList<Message>) ->
            value.forEach { message: Message ->
                if (message.id == updatedMessage.id) {
                    val newMessage = message.copy(
                        dateTime = updatedMessage.dateTime,
                        text = updatedMessage.text,
                        readStatus = true
                    )
                    value[value.indexOf(message)] = newMessage
                    return true
                }
            }
        }
        println("Сообщения с таким ID не найдено!!")
        return false
    }

    fun getChatList(): MutableMap<Int, MutableList<Message>> {
        val chatList = mutableMapOf<Int, MutableList<Message>>()
        var count = 1
        chats.forEach { (_, value) ->
            chatList[count] = value
            count += 1
        }
        return chatList
    }

    fun getMessagesFromChat(chatId: List<Int>, lastMessageId: Int, numberOfMessages: Int): List<Message> {
        var chatMessageList = listOf<Message>()
        chats.forEach { (key: List<Int>, value: List<Message>) ->
            if (key == chatId) {
                chatMessageList =
                    value.filter { it.id >= lastMessageId }.subList(0, numberOfMessages)

                chatMessageList.forEach { message ->
                    value[value.indexOf(message)] = message.copy(readStatus = true)
                }
            }
        }
        return chatMessageList
    }

    fun getUnreadChats(userId: Int): MutableList<List<Message>> {
        val unreadChatList = mutableListOf<List<Message>>()

        chats.forEach { (key, value) ->
            if (key.contains(userId)) {
                val newList = value.filter { it.recipientId == userId && !it.readStatus }
                unreadChatList.plusAssign(newList)
            }
        }

        val iterator = unreadChatList.iterator()
        iterator.forEach {
            if (it.isEmpty()) {
                iterator.remove()
            }
        }
        return unreadChatList
    }
}