package nextstep.signup.ui.model

abstract class
SignUpInformation {
    abstract val text: String

    abstract fun isValid(): Boolean

    abstract fun errorMessage(): String
}
