package Services

import Data.Message
import Data.User

class ChatService {
    private var chats: MutableMap<List<Int>, MutableList<Message>> = mutableMapOf()
    private var usersData = mutableListOf<User>()
    private var lastId = 0

    fun addUserToData(user: User): Boolean {
        return if (usersData.contains(user)) {
            println("Пользователь с таким Id уже существует!!")
            false
        } else {
            usersData.plusAssign(user)
            true
        }
    }

//    fun addMessage(message: Message): Int {
//        val key: List<Int> = listOf(message.senderId, message.recipientId)
//        val newMessage = message.copy(id = lastId++)
//
//        if (!chats.containsKey(key) && !chats.containsKey(key.reversed())) {
//            chats[key] = mutableListOf(newMessage)
//        } else {
//            chats.forEach { (k, v) ->
//                if (k.contains(message.senderId) && k.contains(message.recipientId)) {
//                    chats[k] = v.plusElement(newMessage) as MutableList<Message>
//                }
//            }
//        }
//        return chats.size
//    }
//
    fun addMessage(message: Message): Int {
        val key = listOf(message.senderId, message.recipientId).sorted()
        chats.getOrPut(key) { mutableListOf() } += message.copy(id = lastId++)
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

        chats.forEach { (_, value) ->
            val count = 1
            chatList[count] = value
        }
        return chatList
    }

//    fun getMessagesFromChat(chatId: List<Int>, lastMessageId: Int, numberOfMessages: Int): List<Message> {
//        var chatMessageList = listOf<Message>()
//        chats.forEach { (key: List<Int>, value: List<Message>) ->
//            var count = 0
//            if (key == chatId) {
//                chatMessageList =
//                    value.filter { it.id >= count }.subList(count, numberOfMessages)
//
//                chatMessageList.forEach { message ->
//                    value[value.indexOf(message)] = message.copy(id=lastMessageId,readStatus = true)
//                }
//            }
//        }
//        return chatMessageList.asSequence().toList()
//    }

    //ошибка в Build
    fun getMessagesFromChat(chatId: List<Int>, lastMessageId: Int, numberOfMessages: Int): List<Message>  {
        val key = listOf(chatId, numberOfMessages).sortedBy { lastMessageId }
        chats.getOrPut(key as List<Int>){ mutableListOf() } += Message(id = lastMessageId, readStatus=true, dateTime ="", text = "", senderId = 0, recipientId = 0)
        return listOf()
    }





    fun getUnreadChats(userId: Int): MutableMap<List<Int>, MutableList<Message>> {
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
        return chats
    }


}


