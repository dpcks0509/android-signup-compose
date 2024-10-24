package nextstep.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.component.HeaderText
import nextstep.signup.ui.component.SignUpInformationTextField
import nextstep.signup.ui.component.SubmitButton
import nextstep.signup.ui.model.SignUp

@Composable
fun SignUpScreen() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(R.string.sign_up_completed)

    var signUp by rememberSaveable { mutableStateOf(SignUp()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { contentPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(top = 84.dp))

            HeaderText(
                modifier =
                    Modifier.padding(
                        start = 28.dp,
                        end = 28.dp,
                    ),
                text = stringResource(R.string.sign_up_header),
            )

            Column(
                modifier =
                    Modifier.padding(
                        start = 28.dp,
                        end = 28.dp,
                        top = 42.dp,
                        bottom = 42.dp,
                    ),
            ) {
                SignUpInformationTextField(
                    modifier =
                        Modifier.fillMaxWidth(),
                    label = stringResource(R.string.username_label),
                    value = signUp.userName.text,
                    onValueChange = { value ->
                        signUp = signUp.copy(userName = signUp.userName.copy(text = value))
                    },
                    isValid = signUp.userName.isValid(),
                    errorMessage = signUp.userName.errorMessage(),
                )

                SignUpInformationTextField(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 36.dp),
                    label = stringResource(R.string.email_label),
                    value = signUp.email.text,
                    onValueChange = { value ->
                        signUp = signUp.copy(email = signUp.email.copy(text = value))
                    },
                    isValid = signUp.email.isValid(),
                    errorMessage = signUp.email.errorMessage(),
                    keyboardType = KeyboardType.Email,
                )

                SignUpInformationTextField(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 36.dp),
                    label = stringResource(R.string.password_label),
                    value = signUp.password.text,
                    onValueChange = { value ->
                        signUp = signUp.copy(password = signUp.password.copy(text = value))
                        signUp =
                            signUp.copy(
                                passwordConfirm =
                                    signUp.passwordConfirm.copy(
                                        passwordText = value,
                                    ),
                            )
                    },
                    isValid = signUp.password.isValid(),
                    errorMessage = signUp.password.errorMessage(),
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                )

                SignUpInformationTextField(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 36.dp),
                    label = stringResource(R.string.password_confirm_label),
                    value = signUp.passwordConfirm.text,
                    onValueChange = { value ->
                        signUp =
                            signUp.copy(
                                passwordConfirm =
                                    signUp.passwordConfirm.copy(
                                        text = value,
                                    ),
                            )
                    },
                    isValid = signUp.passwordConfirm.isValid(),
                    errorMessage = signUp.passwordConfirm.errorMessage(),
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                )
            }

            SubmitButton(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 32.dp, end = 32.dp),
                text = stringResource(R.string.sign_up_button_label),
                onclick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = snackbarMessage,
                            duration = SnackbarDuration.Short,
                        )
                    }
                },
                enabled = signUp.isEligible(),
            )

            Spacer(modifier = Modifier.padding(bottom = 28.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SignUpScreen()
}
