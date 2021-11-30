package Data
data class Message(
    val id: Int = 0,
    val dateTime: String,
    val text: String?,
    val readStatus: Boolean = false,
    val senderId: Int,
    val recipientId: Int
)