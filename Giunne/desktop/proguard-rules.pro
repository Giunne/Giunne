-allowaccessmodification                # 접근 제한자를 수정해 최적화를 허용 (private -> public 등)
-mergeinterfacesaggressively            # 인터페이스를 병합하여 클래스 파일 크기 최적화
-useuniqueclassmembernames              # 난독화된 멤버 이름을 클래스별로 고유하게 생성
-keepattributes Signature,*Annotation*  # 제네릭(Signature)과 애노테이션(*Annotation*) 정보를 유지
-keepdirectories                        # 빈 디렉토리 구조를 유지
-verbose                                # ProGuard 실행 시 상세 로그 출력
-ignorewarnings                         # 경고 발생 시 무시하고 계속 실행

# 중요한 클래스 보존
-keepclassmembers class kotlinx.serialization.** { *; }
-keep class org.jetbrains.compose.** { *; }
-keep class androidx.compose.** { *; }
-keep class kotlin.** { *; }

-keep class com.sun.** { *; }
-keep class org.koin.** { *; }
-keep class com.arkivanov.decompose.** { *; }
-keep class com.arkivanov.essenty.** { *; }
-keep class kotlinx.coroutines.** { *; }
-keep class java.awt.** { *; }
-keep class java.net.** { *; }
-keep class io.ktor.** { *; }
-keep class de.jensklingenberg.ktorfit.** { *; }
-keep class org.hildan.krossbow.** { *; }
-keep class aidot.uro.client.app.** { *; }
-keep class aidot.uro.client.data.** { *; }