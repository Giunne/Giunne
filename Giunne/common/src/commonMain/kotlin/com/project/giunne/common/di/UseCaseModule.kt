package com.project.giunne.common.di

import com.project.giunne.common.domain.usecase.auth.ChangePasswordUseCase
import com.project.giunne.common.domain.usecase.auth.LoginUseCase
import com.project.giunne.common.domain.usecase.auth.LogoutUseCase
import com.project.giunne.common.domain.usecase.auth.StudentSignupUseCase
import com.project.giunne.common.domain.usecase.auth.TeacherSignupUseCase
import com.project.giunne.common.domain.usecase.avatar.CreateAvatarUseCase
import com.project.giunne.common.domain.usecase.avatar.GetFriendsListUseCase
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.domain.usecase.certification.GetCertificationHistory
import com.project.giunne.common.domain.usecase.certification.GetCertificationProgress
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.domain.usecase.common.GetSchoolListUseCase
import com.project.giunne.common.domain.usecase.community.GetPostingDetail
import com.project.giunne.common.domain.usecase.community.GetPostingDetailList
import com.project.giunne.common.domain.usecase.mypage.GetInventoryItemListUseCase
import com.project.giunne.common.domain.usecase.mypage.PutInventoryItemUseCase
import com.project.giunne.common.domain.usecase.roadmap.CreateRecreationUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetAllRoadMapUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetQuestCodeUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetSearchRecreationUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetStudentCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetTeacherCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestInfoUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestStateUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryItemListUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryMapUseCase
import com.project.giunne.common.domain.usecase.shop.GetGachaTypeUseCase
import com.project.giunne.common.domain.usecase.shop.PostGachaUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    single { GetCategoryMapUseCase(get()) }
    single { GetCategoryItemListUseCase(get()) }
    single { GetGachaTypeUseCase(get()) }
    single { PostGachaUseCase(get()) }
    single { LoginUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { StudentSignupUseCase(get()) }
    single { TeacherSignupUseCase(get()) }
    single { ChangePasswordUseCase(get()) }
    single { GetSchoolListUseCase(get()) }
    single { CreateRecreationUseCase(get()) }
    single { GetSearchRecreationUseCase(get()) }
    single { CreateAvatarUseCase(get()) }
    single { LoginRecreationUseCase(get()) }
    single { GetUserAvatarListUseCase(get()) }
    single { GetAllRoadMapUseCase(get()) }
    single { GetTeacherCourseUseCase(get()) }
    single { ModifyQuestInfoUseCase(get()) }
    single { GetQuestCodeUseCase(get()) }
    single { GetStudentCourseUseCase(get()) }
    single { ModifyQuestStateUseCase(get()) }
    single { GetInventoryItemListUseCase(get()) }
    single { PutInventoryItemUseCase(get()) }
    single { GetFriendsListUseCase(get()) }
    single { GetCertificationProgress(get()) }
    single { GetCertificationHistory(get()) }
    single { GetUploadList(get()) }
    single { GetPostingDetail(get()) }
    single { GetPostingDetailList(get()) }
}