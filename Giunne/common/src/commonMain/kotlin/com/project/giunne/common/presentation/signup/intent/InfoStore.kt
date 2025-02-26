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
import com.project.giunne.common.presentation.signup.state.InfoState
import com.project.giunne.common.util.Define
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
                Define.authInfo = response
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
                Define.authInfo = response
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