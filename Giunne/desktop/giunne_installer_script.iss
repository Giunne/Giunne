[Setup]
; 설치 프로그램 기본 설정
AppName=GiunnePC
AppVersion=1.0.0
AppVerName=GiunnePC
DefaultDirName={pf64}\GiunnePC
DefaultGroupName=GiunnePC
OutputDir=.
OutputBaseFilename=GiunnePC-1.0.0-Installer
Compression=lzma2/ultra
SolidCompression=yes
ArchitecturesInstallIn64BitMode=x64
SetupIconFile=.\giunne_icon.ico

[Files]
; uro dot 설치
Source: ".\build\compose\binaries\main-release\app\Giunne\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[UninstallDelete]
Type: files; Name: "{app}\*"

[Icons]
; 설치 후 바탕화면 및 시작 메뉴에 아이콘 추가
Name: "{group}\GiunnePC"; Filename: "{app}\GiunnePC.exe"; IconFilename: "{app}\giunne_icon.ico"
Name: "{userdesktop}\GiunnePC"; Filename: "{app}\GiunnePC.exe"; IconFilename: "{app}\giunne_icon.ico"

[Run]
; 설치 완료 후 자동 실행
Filename: "{app}\GiunnePC.exe"; Description: "Launch UroPC"; Flags: nowait postinstall skipifsilent