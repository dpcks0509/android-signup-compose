package nextstep.signup.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUp(
    val userName: UserName = UserName(),
    val email: Email = Email(),
    val password: Password = Password(),
    val passwordConfirm: PasswordConfirm = PasswordConfirm(),
) : Parcelable {
    fun isEligible(): Boolean {
        val information = listOf(userName, email, password, passwordConfirm)
        return information.all { info ->
            info.text.isNotEmpty() && info.isValid()
        }
    }
}
