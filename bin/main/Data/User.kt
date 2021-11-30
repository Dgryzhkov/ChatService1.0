package Data
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    var incomingMessages: List<Message>,
    var outgoingMessages: List<Message>
) {
    override fun toString(): String {
        return "UserId = $id, $firstName $lastName"
    }
}