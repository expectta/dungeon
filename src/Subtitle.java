public class Subtitle {
    /*
    문자,그림 변환 사이트 : https://wepplication.github.io/tools/asciiArtGen/

    이미지 사용 : https://pixabay.com/ko/service/faq/
     (사용자는 어떠한 허가 요청이나 지불 없이 상업적/비상업적 목적으로 이미지를 복사,
       수정, 배포 및 이용할 수 있습니다 .pixabay 출처자료)
    */
    private String title = "      _                                          \n" +
            "    | |                                         \n" +
            "  __| | _   _  _ __    __ _   ___   ___   _ __  \n" +
            " / _` || | | || '_ \\  / _` | / _ \\ / _ \\ | '_ \\ \n" +
            "| (_| || |_| || | | || (_| ||  __/| (_) || | | |\n" +
            " \\__,_| \\__,_||_| |_| \\__, | \\___| \\___/ |_| |_|\n" +
            "                       __/ |                    \n" +
            "                      |___/     ";
    private String gameDescription = "4개의 던전의 보스를 모두 잡아야 게임이 끝난다.\n" +
            "유저 케릭터는 레벨이 오르면 모든 스탯이 오르며, 몬스터 또한 유저 케릭터와 같이 레벨이 상승하고 스탯이 상승한다.\n" +
            "몬스터는 유저와 싸울때 자동으로 공격한다." +
            "대장장이마을, 가죽장인마을, 포션마을 세가지가 있으며 각각 무기, 방어구, 물약을 구매 할 수있다.\n" +
            "유저 케릭터는 아이템 착용/해제가 가능하고 스킬을 사용 할 수 있다.\n";

    private String skullImage = "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$=!==@@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-....*@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@~.....~@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@;......~@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,......:@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$,.....,$@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$!-.......*@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@;~~-.........,=@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-,,,..............!@@\n" +
            "@@@##@@@@@@@@@@@@@@@@####...........-~~~........*@\n" +
            "@#:..-=$@@@@@@@@====!........,,,-~~~;;;~~~~,,,,-~@\n" +
            "@:.....~!#*!!!!;.........,,,,~~:***=#@@=;~~~~~~~!@\n" +
            "#........~............---~~!$$$#@@@@@@@@#$!~~~~!@@\n" +
            "#.................---~~!#@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@=............~~~:::@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@$-......,,-~;;;*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@*.....,~~**#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@!.....-~$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@!....,~~@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@!...,~~~@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@!,,,~~;@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@$:~~~!$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@@#$$$#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      ";
    private String fireImage = "\n" +
            "\n" +
            "                              \n" +
            "                              \n" +
            "             @                \n" +
            "             @@               \n" +
            "             @@               \n" +
            "             @@@              \n" +
            "             @@@              \n" +
            "           @ @@@@ .           \n" +
            "           @@@@@@ @           \n" +
            "           @@@@@@:@@          \n" +
            "           @@@!@@@@@          \n" +
            "        @  @@@ @@@@@          \n" +
            "        @@ @@@@@@@@@=         \n" +
            "         @@@@@@@@@@@@ @       \n" +
            "         @@@@@@@@@@@@@@       \n" +
            "        @@@@@@@@@@@@@@@       \n" +
            "      @ @@@@@@@@@@@@@@@       \n" +
            "       @@@@@@@@@ @@@@@@       \n" +
            "       @@@@@@@@@ @@@@@@       \n" +
            "       @@@@@@@@@ @@@@@@@      \n" +
            "       @@@@@@ @@ @ @@@@@      \n" +
            "       @@@@@@ @ @ !@@@@@      \n" +
            "       @@@@ @   # @@@@@@      \n" +
            "       @@@@ @      @@@@       \n" +
            "       @@@@  @     @@@@       \n" +
            "       =@@@       @@@@        \n" +
            "        @@@@     @@@@,        \n" +
            "          @@@  @@@@@          \n" +
            "         @@@@@@@@@@@@         \n" +
            "                              \n";
    private String snowImage= "@@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$@@\n" +
            "@=;                                  ;=@\n" +
            "$;                                    ;$\n" +
            "$                                      $\n" +
            "$                                      $\n" +
            "$                  --                  $\n" +
            "$                 ,$$,                 $\n" +
            "$                 -##-                 $\n" +
            "$                 -##-                 $\n" +
            "$              ,#=~##~=#.              $\n" +
            "$           .. !########: -.           $\n" +
            "$       ,  ,*;  ;!####!; .$*.  ,       $\n" +
            "$      :*: ~##,   ;##;   ,#$~-;*:      $\n" +
            "$      $#$!=##,   -##-   ,#$*=##$      $\n" +
            "$      .######, =!-##-!  ,######.      $\n" +
            "$        ,####* ##$##$#  #####,        $\n" +
            "$        ,#############*###$##,        $\n" +
            "$       *##$==$###$==$###$;~$##        $\n" +
            "$       ;*!~  =###*..*###=  ~!!        $\n" +
            "$           ,~$##$~  ~$##=-            $\n" +
            "$        ,  ,~$##$~  ~$##$-            $\n" +
            "$       ,=!~  =###$!!$###=  ~!*        $\n" +
            "$       ~##$==$##########$==$##        $\n" +
            "$        ,#############$######,        $\n" +
            "$        ,####! ##$##$#  $####,        $\n" +
            "$      .######, =!-##-!  ,######.      $\n" +
            "$      $#$!=##,   -##-   ,#$**$#$      $\n" +
            "$      :;: :##,  :;##;.  ,#$~.:;:      $\n" +
            "$          ,*;  ;#####*;  ;*.          $\n" +
            "$           .. !########  .,           $\n" +
            "$              ,$,~##~-$               $\n" +
            "$                 -##-                 $\n" +
            "$                 -##-                 $\n" +
            "$                 ,$$,                 $\n" +
            "$                  --                  $\n" +
            "$                                      $\n" +
            "$                                      $\n" +
            "$;                                    ;$\n" +
            "@=;                                  ;$@\n" +
            "@@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$@@";
    private String windImage = "                                                                     \n" +
            "                               !!!                                              \n" +
            "                             :@@@@@;                                            \n" +
            "                            :@@!;*@@                                            \n" +
            "                   --.      @@,   :@;                                           \n" +
            "                 ,@@@@:    .@*    ,@@                                           \n" +
            "                 @@#@@@.   ,@-     ;                                            \n" +
            "                :@$  =@:    @*                                                  \n" +
            "                ;@,  .@,    @@                                                  \n" +
            "                :@*         *@=                                                 \n" +
            "                .@@:  .      #@#;;;!!;!!!!!!!!!:;;;;;;;;;;;:;;                  \n" +
            "                 ;@@@@@@@@#   !@@#@@@@@@@@@@@@@##@@@@@@@@@@$@@-                 \n" +
            "                  ~=@#@@@@#  -,-!$===$$======$*$===$=*=$$=$*=*                  \n" +
            "                  -   - .,-  - - ., - .,., -   ,. -   -..,- -                   \n" +
            "                  .-- - .,-:#@#@@#@@@@@#$@@#@@@##@@@$###@@@*                    \n" +
            "                    , - .:@$@@#@##@@@@$###@#@@##@@@@=#####@~-                   \n" +
            "                  --- - :@#=~- .- ,- -- .. ---  -  --  -- -  -                  \n" +
            "                       -@@-          ~******************-                       \n" +
            "                       =@~ .!!!-   ~@@@@@@@@@@@@@@@@@@@@#                       \n" +
            "                       @# -@@@@@!  @@!;;;;;;;;;;;;;;;;;;-                       \n" +
            "                      :@! #@* ;@@-.@@                                           \n" +
            "                      ;@!  ,   :@* @@~.                                         \n" +
            "                      ~@!       @$ ,@@=                                         \n" +
            "                       @@      ,@=   ,                                          \n" +
            "                       !@=     $@!                                              \n" +
            "                        #@#!.:$@$                                               \n" +
            "                        .*@@@@@=.                                               \n" +
            "                          ,;!;-                                                 \n" +
            "                                                              ";
    private String chocieCharacter = "케릭터를 정해주세요. ";
    private String selectedItem = "원하는 아이템 번호를 입력하세요 : ";
    private String inputUserName = "원하시는 케릭터 이름을 입력 해 주세요 : ";
    private String undeadDungeon  = "Tip : 언데드 몬스터가 출현하는 장소입니다.";
    private String waterDungeon  = "Tip : 물계열 몬스터가 출현는 장소입니다.";
    private String windDungeon  = "Tip : 바람계열 몬스터가 출현는 장소입니다.";
    private String fireDungeon  = "Tip : 불계열 몬스터가 출현는 장소입니다.";
    private String pickCharacter = "[1]전사 [2]요정 [3]법사 [4]게임설명";
    private String huntingOrTown = "[1]사냥가기 [2]마을가기 [3]케릭상태확인 [4]게임종료";
    private String userCharacterState = "[1]아이템사용/삭제 [2]착용아이템보기 [3]뒤로가기 ";
    private String monsterMeet =  "[1]공격하기 [2]스킬쓰기 [3]아이템사용하기 [4]도망가기";
    private String searchMonster = "[1]몬스터찾기 [2]케릭상태확인 [3]마을가기 [4]사냥터바꾸기";
    private String goTown = "[1]대장장이마을 [2]가죽장인마을 [3]포션마을 [4]사냥가기 [5]케릭상태확인 [6]뒤로가기";
    private String goHuntingPlace = "[1]해골던전 [2]얼음던전 [3]바람던전 [4]불던전 [5]마을가기 [6]케릭상태확인 [7]게임종료" ;

    public String getGoHunting() {
        return goHunting;
    }

    private String goHunting = "[1]해골던전 [2]얼음던전 [3]바람던전 [4]불던전 " ;
    private String armorTrader = " Tip : 몬스터의 레벨은 유저케릭터와 비슷하기 때문에 방어력에 신경쓰세요. ";
    private String potionTradeer = "Tip : 전투 중간중간 체력을 잘 확인하세요. 전투중 위험하면 도망칠 수 있어요.";
    private String weaponTrader = "Tip : 보스몬스터는 체력이 많아요. 무기를 꼭 구입해서 착용하세요";
    //사용자에게 보여지는 선택지를 구별하기 위한 선 그리기 함수
    public void menuLinePrint(String scene) {
        System.out.println("────────────────────────────────────────────────────────────────────");
        System.out.println(scene);
        System.out.println("────────────────────────────────────────────────────────────────────");
    }
    //선택지를 선택하는 함수
    public void subtitlePrint(String sub){
        System.out.println(sub);
    }
    /*--------------------------------getter & setter----------------------------------*/
    public String getSkullImage() {
        return skullImage;
    }
    public String getFireImage() {
        return fireImage;
    }
    public String getSnowImage() {
        return snowImage;
    }
    public String getWindImage() {
        return windImage;
    }
    public String getselectedItem() {
        return selectedItem;
    }
    public String getMonsterMeet() {
        return monsterMeet;
    }
    public String getUserCharacterState() {
        return userCharacterState;
    }
    public String getSearchMonster() {
        return searchMonster;
    }
    public String getGoHuntingPlace() {
        return goHuntingPlace;
    }
    public String getGoTown() {
        return goTown;
    }
    public String getHuntingOrTown() {
        return huntingOrTown;
    }
    public String getPickCharacter() {
        return pickCharacter;
    }
    public String getInputUserName() {
        return inputUserName;
    }
    public String getChocieCharacter() {
        return chocieCharacter;
    }
    public String getGameDescription() {
        return gameDescription;
    }
    public String getTitle() {
        return title;
    }
    public String getArmorTrader() {
        return armorTrader;
    }
    public String getWeaponTrader() {
        return weaponTrader;
    }
    public String getPotionTradeer() {
        return potionTradeer;
    }
    public String getUndeadDungeon() {
        return undeadDungeon;
    }
    public String getWaterDungeon() {
        return waterDungeon;
    }
    public String getWindDungeon() {
        return windDungeon;
    }
    public String getFireDungeon() {
        return fireDungeon;
    }

}
