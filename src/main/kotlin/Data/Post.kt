package Data

import Enum.PostType


data class Post(
    val id: Long,
    val type: PostType,
    val content: String,
    val likes: Int=0

)

