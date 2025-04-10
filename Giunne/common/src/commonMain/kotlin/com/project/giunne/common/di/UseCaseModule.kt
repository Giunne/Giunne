package com.project.giunne.common.di

import com.project.giunne.common.domain.usecase.auth.ChangePasswordUseCase
import com.project.giunne.common.domain.usecase.auth.CheckExistIdUseCase
import com.project.giunne.common.domain.usecase.auth.LoginUseCase
import com.project.giunne.common.domain.usecase.auth.LogoutUseCase
import com.project.giunne.common.domain.usecase.auth.StudentSignupUseCase
import com.project.giunne.common.domain.usecase.auth.TeacherSignupUseCase
import com.project.giunne.common.domain.usecase.avatar.ChangeStudentPasswordUseCase
import com.project.giunne.common.domain.usecase.avatar.CreateAvatarUseCase
import com.project.giunne.common.domain.usecase.avatar.GetFriendsListUseCase
import com.project.giunne.common.domain.usecase.avatar.GetMyPointUseCase
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.domain.usecase.avatar.ModifyStudentExpUseCase
import com.project.giunne.common.domain.usecase.avatar.ModifyStudentPointUseCase
import com.project.giunne.common.domain.usecase.avatar.ResetPasswordUseCase
import com.project.giunne.common.domain.usecase.certification.GetCertificationHistory
import com.project.giunne.common.domain.usecase.certification.GetCertificationProgress
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.domain.usecase.certification.PostGradeStudentUseCase
import com.project.giunne.common.domain.usecase.certification.PostUploadFileUseCase
import com.project.giunne.common.domain.usecase.common.GetSchoolListUseCase
import com.project.giunne.common.domain.usecase.community.DeleteComment
import com.project.giunne.common.domain.usecase.community.GetCommentList
import com.project.giunne.common.domain.usecase.community.GetPostingDetail
import com.project.giunne.common.domain.usecase.community.GetPostingDetailList
import com.project.giunne.common.domain.usecase.community.GetPostingList
import com.project.giunne.common.domain.usecase.community.GetQuestTypeList
import com.project.giunne.common.domain.usecase.community.PostComment
import com.project.giunne.common.domain.usecase.community.PostCommentLike
import com.project.giunne.common.domain.usecase.community.PostCommentUnlike
import com.project.giunne.common.domain.usecase.mypage.GetAvatarInformationUseCase
import com.project.giunne.common.domain.usecase.mypage.GetInventoryItemListUseCase
import com.project.giunne.common.domain.usecase.mypage.ModifyAvatarInformationUseCase
import com.project.giunne.common.domain.usecase.mypage.PutInventoryItemUseCase
import com.project.giunne.common.domain.usecase.notice.DeleteNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.GetNoticeDetailUseCase
import com.project.giunne.common.domain.usecase.notice.GetNoticeListUseCase
import com.project.giunne.common.domain.usecase.notice.ModifyNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.PostNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.ReadNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.UnreadNoticeCountUseCase
import com.project.giunne.common.domain.usecase.roadmap.CreateRecreationUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetAllRoadMapUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetQuestCodeUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetRecreationTeacherListUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetSearchRecreationUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetSpecificStudentCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetStudentCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetTeacherCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestInfoUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestStateUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryItemListUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryMapUseCase
import com.project.giunne.common.domain.usecase.shop.GetGachaTypeUseCase
import com.project.giunne.common.domain.usecase.shop.GetPossibleItemCountUseCase
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
    single { GetRecreationTeacherListUseCase(get()) }
    single { GetAllRoadMapUseCase(get()) }
    single { GetTeacherCourseUseCase(get()) }
    single { ModifyQuestInfoUseCase(get()) }
    single { GetQuestCodeUseCase(get()) }
    single { GetStudentCourseUseCase(get()) }
    single { GetSpecificStudentCourseUseCase(get()) }
    single { ModifyQuestStateUseCase(get()) }
    single { GetInventoryItemListUseCase(get()) }
    single { PutInventoryItemUseCase(get()) }
    single { GetFriendsListUseCase(get()) }
    single { GetCertificationProgress(get()) }
    single { GetCertificationHistory(get()) }
    single { PostUploadFileUseCase(get()) }
    single { GetUploadList(get()) }
    single { GetPostingDetail(get()) }
    single { GetPostingDetailList(get()) }
    single { GetCommentList(get()) }
    single { PostComment(get()) }
    single { PostCommentLike(get()) }
    single { PostCommentUnlike(get()) }
    single { GetPostingList(get()) }
    single { GetQuestTypeList(get()) }
    single { PostGradeStudentUseCase(get()) }
    single { GetAvatarInformationUseCase(get()) }
    single { ModifyAvatarInformationUseCase(get()) }
    single { DeleteComment(get()) }
    single { CheckExistIdUseCase(get()) }
    single { GetMyPointUseCase(get()) }
    single { GetPossibleItemCountUseCase(get()) }
    single { ModifyStudentPointUseCase(get()) }
    single { ModifyStudentExpUseCase(get()) }
    single { ResetPasswordUseCase(get()) }
    single { ChangeStudentPasswordUseCase(get()) }
    single { GetNoticeListUseCase(get()) }
    single { GetNoticeDetailUseCase(get()) }
    single { ModifyNoticeUseCase(get()) }
    single { PostNoticeUseCase(get()) }
    single { DeleteNoticeUseCase(get()) }
    single { ReadNoticeUseCase(get()) }
    single { UnreadNoticeCountUseCase(get()) }
}