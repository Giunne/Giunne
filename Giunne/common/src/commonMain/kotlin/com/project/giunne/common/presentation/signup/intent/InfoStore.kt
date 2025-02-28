package com.project.giunne.common.presentation.signup.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.request.PageNationRequest
import com.project.giunne.common.data.remote.request.StudentSignupRequest
import com.project.giunne.common.data.remote.request.TeacherSignupRequest
import com.project.giunne.common.data.remote.response.SchoolInfo
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.auth.StudentSignupUseCase
import com.project.giunne.common.domain.usecase.auth.TeacherSignupUseCase
import com.project.giunne.common.domain.usecase.common.GetSchoolListUseCase
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_STUDENT
import com.project.giunne.common.presentation.signup.state.InfoState
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.isValidPassword
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class InfoStore(
    private val getSchoolListUseCase: GetSchoolListUseCase = KoinJavaComponent.get(GetSchoolListUseCase::class.java),
    private val teacherSignupUseCase: TeacherSignupUseCase = KoinJavaComponent.get(TeacherSignupUseCase::class.java),
    private val studentSignupUseCase: StudentSignupUseCase = KoinJavaComponent.get(StudentSignupUseCase::class.java),
): BaseStore<InfoState>(InfoState()) {
    fun callTeacherSignup(
        teacherSignupRequest: TeacherSignupRequest
    ) {
        scope.launch {
            setState { copy(loading = true) }

            runCatching {
                teacherSignupUseCase.invoke(teacherSignupRequest)
            }.onSuccess { response ->
                setState {
                    copy(
                        signupSuccessDialog = true,
                        loading = false
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun callStudentSignup(
        studentSignupRequest: StudentSignupRequest
    ) {
        scope.launch {
            setState { copy(loading = true) }

            runCatching {
                studentSignupUseCase.invoke(studentSignupRequest)
            }.onSuccess { response ->
                setState {
                    copy(
                        signupSuccessDialog = true,
                        loading = false
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun callSchoolList(
        searchText: String,
        pageIndex: Int,
        pageSize: Int
    ) {
        scope.launch {
            setState { copy(loading = true) }

            runCatching {
                getSchoolListUseCase.invoke(
                    schoolNm = searchText,
                    pageNationRequest = PageNationRequest(
                        pageIndex = pageIndex,
                        pageSize = pageSize,
                        sortProperty = "create_time",
                        sortDirection = "DESC"
                    ),
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        schoolList = response.data,
                        pageNationInfo = response.paginationInfo
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun checkSignUpValidate(
        signupType: String,
        onSuccess: () -> Unit
    ) {
        when {
            uiState.value.idText.isEmpty() -> {
                setState { copy(signupValidate = "아이디를 입력해야 합니다.") }
            }
            uiState.value.passText.isEmpty() -> {
                setState { copy(signupValidate = "비밀번호를 입력해야 합니다.") }
            }
            uiState.value.passConfText.isEmpty() -> {
                setState { copy(signupValidate = "비밀번호 확인을 입력해야 합니다.") }
            }
            uiState.value.codeText.isEmpty() && signupType == TYPE_STUDENT -> {
                setState { copy(signupValidate = "인증코드를 입력해야 합니다.") }
            }
            uiState.value.schoolInfo.schoolNm.isEmpty() -> {
                setState { copy(signupValidate = "학교가 선택되어야 합니다.") }
            }
            !uiState.value.passText.isValidPassword() -> {
                setState { copy(signupValidate = "비밀번호 형식이 잘못되었습니다.\n(영어, 숫자를 포함한 8자리)") }
            }
            uiState.value.passText != uiState.value.passConfText -> {
                setState { copy(signupValidate = "비밀번호, 비밀번호 확인이 서로 다릅니다.") }
            }
            else -> { onSuccess() }
        }
    }

    fun dismissInValidateDialog() {
        setState { copy(signupValidate = null) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

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
        schoolInfo: SchoolInfo,
    ) {
        setState { copy(schoolInfo = schoolInfo) }
    }

    fun dismissSchoolSearchDialog() {
        setState { copy(schoolSearchDialog = false) }
    }

    fun dismissSignupSuccessDialog() {
        setState { copy(signupSuccessDialog = false) }
    }
}