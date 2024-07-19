package br.com.magalzim.neoforum.validation

object MessageValidation {
    private const val MESSAGE_ATTRIBUTE = "message"
    private const val TOPIC_ATTRIBUTE = "topicId"
    private const val AUTHOR_ATTRIBUTE = "authorId"

    const val MAX = 255
    const val MIN = 15
    const val MESSAGE = "Message must be between $MIN and $MAX characters"

    const val MESSAGE_EMPTY = MESSAGE_ATTRIBUTE + Validation.EMPTY
    const val MESSAGE_NULL = MESSAGE_ATTRIBUTE + Validation.NULL
    const val TOPIC_NULL = TOPIC_ATTRIBUTE + Validation.NULL
    const val AUTHOR_NULL = AUTHOR_ATTRIBUTE + Validation.NULL
    const val AUTHOR_MIN = AUTHOR_ATTRIBUTE + Validation.MIN
}