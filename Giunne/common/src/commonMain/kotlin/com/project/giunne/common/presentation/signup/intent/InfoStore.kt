package com.project.giunne.common.presentation.signup.intent

import androidx.compose.runtime.mutableStateOf
import com.project.giunne.common.presentation.signup.state.InfoState
import kotlinx.coroutines.CoroutineScope

class InfoStore(
    val scope: CoroutineScope
) {
    val infoState = mutableStateOf(InfoState())

    fun onIdTextChanged(
        text: String
    ) {
        setState {
            copy(idText = text)
        }
    }

    fun onPassTextChanged(
        text: String
    ) {
        setState {
            copy(passText = text)
        }
    }

    fun onPassConfTextChanged(
        text: String
    ) {
        setState {
            copy(passConfText = text)
        }
    }

    fun onCodeTextChanged(
        text: String
    ) {
        setState {
            copy(codeText = text)
        }
    }

    fun onClickSchoolSearchButton() {
        setState { copy(schoolSearchDialog = true) }
    }

    fun onSchoolSelected(
        schoolName: String,
    ) {
        setState { copy(schoolText = schoolName) }
    }

    fun dismissSchoolSearchDialog() {
        setState { copy(schoolSearchDialog = false) }
    }

    private inline fun setState(crossinline block: InfoState.() -> InfoState) {
        infoState.value = block.invoke(infoState.value)
    }
}