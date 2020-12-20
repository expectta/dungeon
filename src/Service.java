import java.util.Scanner;

public class Service {
    private static Subtitle subtitle = new Subtitle();
    Battle battle ;
    private static Item item = new Item();
    //던전을 입장하기 전 4개의 던전을 식별 할 수있도록 변수선언
    //해골던전 = 1 , 얼음던전 = 2, 바람던전 =3, 불던전 =4
    private static int huntingPlaceNumber = 0;
    private static UserCharacter userCharacter = null;
    BackGroundMusic backGroundMusic ;

    //사용자가 키보드 입력 시 선택지 번호의 범위가 아닐경우 에러 발생하는 함수
    //fristNumber = 선택지의 맨 처음 숫자, lastNumber = 선택지의 마지막 숫자.
    public int inputValueCheck(int fristNumber, int lastNumber){
        int inputValue = 0;
        boolean isLoop = true;
        Scanner scanner = new Scanner(System.in);
        //사용자의 입력값이 숫자가 아닐경우 무한으로 되묻는다.
        while(isLoop){
            //hasNextInt()는 숫자 = ture, 문자 = fasle
            //사용자의 입력값이 문자라면 입력오류를 출력하고 지속적으로 숫자를 입력받기 위함.
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.err.print("입력오류.  \n재 입력 :");
            }
            //정상적으로 숫자를 입력받으면 inputValue에 대입.
            inputValue = scanner.nextInt();
            //선택지의 첫번째 숫자 frist, 마지막 숫자 lsat를 벗어날 경우 error 발생.
            if( inputValue < fristNumber || inputValue > lastNumber){
                System.err.print("입력오류.  \n재 입력 :");
            //입력값이 선택지 범위 안으로 들어올 경우 무한루푸를 종료하고 사용자의 입력값을 리턴.
            }else if(inputValue >= fristNumber || inputValue <= lastNumber){
                isLoop = false;
                break;
            }
        }
        return inputValue;
    }
    //[1]아이템사용/삭제 [2]장비착용/해제 [3]뒤로가기
    //위 선택지에 대한 함수
    public void selectItem(UserCharacter user) {
        int inputValue = 0;
        int selectedItem = 0;
        int disarmingItem = 0;
        boolean choiceLoop = true;
        while (choiceLoop) {
            //유저케릭터의 상태 정보를 보여줌.
            System.out.println(user.toString());
            System.out.println("──────────────────────────────────────────────────────────────");
            for (int i = 0; i < user.userInventory.size(); i++) {
                System.out.println(i + " = " + user.userInventory.get(i).getName());
            }
            if(user.userInventory.size() == 0){
                System.out.println("보유중인 아이템이 없습니다.");
            }
            System.out.println("──────────────────────────────────────────────────────────────");
            subtitle.menuLinePrint(subtitle.getUserCharacterState());
            inputValue = inputValueCheck(1,3);
            switch (inputValue) {
                case 1://아이템사용/삭제
                    System.out.println("아이템 번호를 입력하세요 : ");
                    if(user.userInventory.size() == 0 ){
                        System.out.println("아이템이 없습니다.");
                        break;
                    }
                    selectedItem = inputValueCheck(0,user.userInventory.size());
                    System.out.printf("%s 아이템을 사용하시겠습니까? [1]네 [2]아니요", user.userInventory.get(selectedItem).getName());
                    inputValue = inputValueCheck(0,user.userInventory.size());
                    if (inputValue == 1) {
                      //무기 착용 시 유저 공격력 상승
                        if (item.getWeaponTrader().contains(user.userInventory.get(selectedItem))) {
                            user.power+=user.userInventory.get(selectedItem).getStat();
                            System.out.printf("%s을 착용했습니다. \n", user.userInventory.get(selectedItem).getName());
                            //아이템 착용 WearingItemList에 사용할 아이템 추가
                            user.getWearingItem().add(user.userInventory.get(selectedItem));
                            //아이템을 사용하면 유저 케릭터의 공격력 추가
                            user.userInventory.remove(user.userInventory.get(selectedItem));
                            break;
                            //방어구 착용 시 유저 방어력 상승.
                        } else if (item.getArmorTrader().contains(user.userInventory.get(selectedItem))) {
                            user.defense+= user.userInventory.get(selectedItem).getStat();
                            System.out.printf("%s을 착용했습니다. \n", user.userInventory.get(selectedItem).getName());
                            user.getWearingItem().add(user.userInventory.get(selectedItem));
                            user.userInventory.remove(user.userInventory.get(selectedItem));
                            break;
                            //물약류는 사용시 적용 후 삭제.
                        } else if (item.getPotionTrader().contains(user.userInventory.get(selectedItem))) {
                            if (user.userInventory.get(selectedItem) == item.getHpPotion()) {
                                user.hitPoint += user.userInventory.get(selectedItem).getStat();
                                //유저의 회복량이 최대 HP를 넘기면 최대 HP로 대입.
                                if(user.hitPoint > user.getMaxHitPoint()){
                                    user.setHitPoint(user.getMaxHitPoint());
                                    System.out.printf("체력물약을 사용했습니다.\n" );
                                    System.out.println("유저의 HP가 가득찼습니다.");
                                    user.userInventory.remove(user.userInventory.get(selectedItem));
                                }else {
                                    user.userInventory.remove(user.userInventory.get(selectedItem));
                                    System.out.printf("체력물약을 사용했습니다.\n" );
                                }
                                break;
                            } else if (user.userInventory.get(selectedItem) == item.getMpPotion()) {
                                user.magicPoint += user.userInventory.get(selectedItem).getStat();
                                if(user.getMagicPoint() > user.getMaxMagicPoint()){
                                    user.setMagicPoint(user.getMaxMagicPoint());
                                    System.out.printf("마나물약을 사용했습니다.\n" );
                                    System.out.println("유저의 HP가 가득찼습니다.");
                                    user.userInventory.remove(user.userInventory.get(selectedItem));
                                }else {
                                    user.userInventory.remove(user.userInventory.get(selectedItem));
                                    System.out.printf("마나물약을 사용했습니다.\n");
                                }
                                break;
                            }
                        }
                    } else if (inputValue == 2) {
                        break;
                    }
                    break;
                case 2://착용아이템보기
                    System.out.print("착용한 아이템\n");
                    System.out.println("──────────────────────────────────────────────────────────────");
                    //유저케릭터가 착용중인 아이템 리스트를 보여줌
                    for(int i = 0 ; i < userCharacter.getWearingItem().size() ; i++){
                        System.out.print( i + " = "+ userCharacter.getWearingItem().get(i)+ "\n");
                    }
                    System.out.println("──────────────────────────────────────────────────────────────");
                    System.out.println("\n아이템을 선택하시겠습니까? : [1]네 [2]아니요.");
                    //사용자가 선택지에 대한 입력값을 체크하는 함수
                    inputValue = inputValueCheck(0,2);
                    if(inputValue == 1){
                    System.out.println("아이템 번호를 입력하세요 : ");
                        disarmingItem = inputValueCheck(0,userCharacter.getWearingItem().size());
                        System.out.println("선택한 아이템을 해제하시겠습니까? [1]네 [2]아니요");
                        //사용자가 선택지에 대한 입력값을 체크하는 함수
                        inputValue =inputValueCheck(0,1);
                        if(inputValue ==1){//유저가 착용중인 아이템 제거 후 인벤토리로 이동.
                            user.userInventory.add(user.getWearingItem().get(disarmingItem));
                            //사용자가 해제하려는 아이템이 무기인지, 방어구인지를 판단해서 파워,방어력 감소
                            if(item.getWeaponTrader().contains(user.getWearingItem().get(disarmingItem))){
                                userCharacter.power-=userCharacter.getWearingItem().get(disarmingItem).stat;
                            }else if(item.getArmorTrader().contains(user.getWearingItem().get(disarmingItem))){
                                userCharacter.defense-=userCharacter.getWearingItem().get(disarmingItem).stat;
                            }
                            //remove로 삭제하고 해당 인덱스로 접근시 이미 리스트 안에는 값이 사라지고 밀려있는 상태.
                            System.out.printf("%s을 해제했습니다. \n", user.getWearingItem().get(disarmingItem).getName());
                            user.getWearingItem().remove(user.getWearingItem().get(disarmingItem));
                        }else if(inputValue ==2){
                            break;
                        }
                    }else if(inputValue ==2){
                        break;
                    }
                    break;
                case 3://스킬쓰기
                    stateSettingGoToTown();
                break;
                case 4://뒤로가기
                    stateSettingGoToTown();
                    break;
                default:
                    break;
            }
            selectedItem = 0;
           disarmingItem = 0;
        }
    }
    //[1]몬스터찾기 [2]내 상태창 확인 [3]가방확인  [4]마을가기 [5]사냥터바꾸기
    //위 선택지에 대한 함수
    public void stateSettingLookingAround() {
        int inputValue = 0;
        boolean choiceLoop = true;
        while (choiceLoop) {
            //선택지에 대한 정보를 출력함
            subtitle.menuLinePrint(subtitle.getSearchMonster());
            //사용자가 선택지에 대한 입력값을 체크하는 함수
            inputValue = inputValueCheck(1,4);
            switch (inputValue) {
                case 1://몬스터찾기
                    //배경음악 종료 후 몬스터를 찾을 경우 음악이 바뀜.
                    battle = new Battle();
                    Battle btle = new Battle();
                    ThreaCohice choice = new ThreaCohice(btle);
                    ThreadUser user = new ThreadUser(btle);

//                    battle.checkMonster();
                    choice.start();
                    user.start();
                    backGroundMusic.close();
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //4개의 던전 중 현재 던전을 확인하고 해당 던전에 맞는 몬스터를 생성
//                    backGroundMusic = new BackGroundMusic();
//                    //몬스터를 잡거나 도망쳤을 경우 배경음악은 다시 메인배경음악으로 전환.
//                    backGroundMusic.music("mainBGM.MP3");
//                    backGroundMusic.start();
                    break;
                case 2://케릭상태확인
                    selectItem(userCharacter);
                    break;
                case 3://마을가기
                    stateSettingGoToTown();
                    break;
                case 4://사냥터바꾸기
                    stateSettingchanageHuntingPlace();
                    break;
                default:
                    break;
            }
        }
    }
    //[[1]해골던전 [2]얼음던전 [3]바람던전 [4]불던전 [5]마을가기 [6]케릭상태확인 [7]게임종료
    //위 선택지에 대한 함수
    public void stateSettingchanageHuntingPlace() {
        int inputValue = 0;
        boolean choiceLoop = true;
        while (choiceLoop) {
            //선택지에대한 정보를 출력함.
            subtitle.menuLinePrint(subtitle.getGoHuntingPlace());
            //사용자가 선택지에 대한 입력값을 체크하는 함수
            inputValue = inputValueCheck(1,7);
            switch (inputValue) {
                case 1://해골던전
                    System.out.println("-해골던전-\n" + subtitle.getUndeadDungeon());
                    //해골 이미지를 출력함.
                    System.out.println(subtitle.getSkullImage());
                    huntingPlaceNumber = 1;
                    stateSettingLookingAround();
                    choiceLoop = false;
                    break;
                case 2://얼음던전
                    System.out.println("-얼음던전-\n" +subtitle.getWaterDungeon());
                    //얼음 이미지를 출력함.
                    System.out.println(subtitle.getSnowImage());
                    //사냥터마다 출현하는 몬스터의 계열을 선별해서 등장 시킬 수 있도록 사냥터에 숫자를 대입하고
                    //해당 숫자와 일치하는 계열의 몬스터를 등장시킴.
                    huntingPlaceNumber = 2;
                    stateSettingLookingAround();
                    choiceLoop = false;
                    break;
                case 3://바람던전
                    System.out.println("-바람던전-\n" + subtitle.getWindDungeon());
                    //바람 이미지를 출력함.
                    System.out.println(subtitle.getWindImage());
                    //사냥터마다 출현하는 몬스터의 계열을 선별해서 등장 시킬 수 있도록 사냥터에 숫자를 대입하고
                    //해당 숫자와 일치하는 계열의 몬스터를 등장시킴.
                    huntingPlaceNumber = 3;
                    stateSettingLookingAround();
                    choiceLoop = false;
                    break;
                case 4://불던전
                    System.out.println("-불던전-\n" + subtitle.getFireDungeon());
                    //불 이미지를 출력함
                    System.out.println( subtitle.getFireImage());
                    //사냥터마다 출현하는 몬스터의 계열을 선별해서 등장 시킬 수 있도록 사냥터에 숫자를 대입하고
                    //해당 숫자와 일치하는 계열의 몬스터를 등장시킴.
                    huntingPlaceNumber = 4;
                    stateSettingLookingAround();
                    choiceLoop = false;
                    break;
                case 5://마을가기
                    stateSettingGoToTown();
                    choiceLoop = false;
                    break;
                case 6://케릭상태확인.
                    selectItem(userCharacter);
                    choiceLoop = false;
                    break;
                case 7://게임종료
                    System.exit(0);
                    choiceLoop = false;
                    break;
                default:
                    break;
            }
        }
    }
    //[1]대장장이마을 [2]가죽장인마을 [3]포션마을 [4]사냥가기 [5]케릭상태확인 [6]뒤로가기
    //선택지에 대한 함수
    public void stateSettingGoToTown() {
        int inputValue = 0;
        boolean choiceLoop = true;
        while (choiceLoop) {
            subtitle.menuLinePrint(subtitle.getGoTown());
            inputValue = inputValueCheck(1,6);
            switch (inputValue) {
                case 1://대장장이마을
                    System.out.println("-대장장이마을-\n");
                    //선택지를 출력하고 아이템 리스르틑 출력함.
                    System.out.println(subtitle.getWeaponTrader());
                    item.printItemList(item.getWeaponTrader());
                    break;
                case 2://가죽장인마을
                    System.out.println("-가죽장인마을-\n");
                    System.out.println(subtitle.getArmorTrader());
                    item.printItemList(item.getArmorTrader());
                    break;
                case 3://포션마을
                    System.out.println("-포션마을-\n" +
                            "Tip : 레벨 10을 달성하면 포션마을에서 축복을 받을 수 있어요.!");
                    System.out.println(subtitle.getPotionTradeer());
                    item.printItemList(item.getPotionTrader());
                    break;
                case 4://사냥가기
                    stateSettingchanageHuntingPlace();
                    break;
                case 5://케릭상태확인.
                    selectItem(userCharacter);
                case 6://뒤로가기
                    choiceLoop = false;
                    break;
                default:
                    break;
            }
        }
    }
    //[1]사냥가기 [2]마을가기 [3]케릭상태확인 [4]게임종료
    //선택지에 대한 함수
    public void stateSettingHuntingOrTown() {
            int inputValue = 0;
            boolean choiceLoop = true;
            while (choiceLoop) {
                subtitle.menuLinePrint(subtitle.getHuntingOrTown());
                inputValue = inputValueCheck(1,4);
                switch (inputValue) {
                    case 1://사냥가기
                        subtitle.menuLinePrint(subtitle.getGoHunting());
                        inputValue = inputValueCheck(1,4);
                        if (inputValue == 1) {
                            System.out.println("-해골던전-\n" + subtitle.getUndeadDungeon()+subtitle.getSkullImage());
                            huntingPlaceNumber = 1;
                            stateSettingLookingAround();
                            choiceLoop = false;
                            break;
                        } else if (inputValue == 2) {
                            System.out.println("-얼음던전-\n" + subtitle.getWaterDungeon() + subtitle.getSnowImage());
                            huntingPlaceNumber = 2;
                            stateSettingLookingAround();
                            choiceLoop = false;
                            break;
                        } else if (inputValue == 3) {
                            System.out.println("-바람던전-\n" + subtitle.getWindDungeon() + subtitle.getWindImage());
                            huntingPlaceNumber = 3;
                            stateSettingLookingAround();
                            choiceLoop = false;
                            break;
                        } else if (inputValue == 4) {
                            System.out.println("-불던전-\n" + subtitle.getFireDungeon() + subtitle.getFireImage());
                            huntingPlaceNumber = 4;
                            stateSettingLookingAround();
                            choiceLoop = false;
                            break;
                        }
                        break;
                    case 2://마을가기
                        subtitle.menuLinePrint(subtitle.getGoTown());

                        inputValue = inputValueCheck(1,4);
                        if (inputValue == 1) {
                            System.out.println("-대장장이마을-\n");
                            System.out.println(subtitle.getWeaponTrader());
                            item.printItemList(item.getWeaponTrader());

                            break;
                        } else if (inputValue == 2) {
                            System.out.println("-가죽장인마을-\n");
                            item.printItemList(item.getArmorTrader());
                            subtitle.menuLinePrint(subtitle.getArmorTrader());
                            break;
                        } else if (inputValue == 3) {
                           System.out.println("-포션마을-\n" +
                                    "Tip : 레벨 10을 달성하면 좋은일이 생길거에요!");
                            System.out.println(subtitle.getPotionTradeer());
                            item.printItemList(item.getPotionTrader());
                            break;
                        } else if (inputValue == 4) {
                            stateSettingchanageHuntingPlace();
                            choiceLoop = false;
                            break;
                        }
                    case 3://케릭상태확인
                        selectItem(userCharacter);
                        choiceLoop = false;
                        break;
                    case 4://게임종료

                        System.exit(0);
                        choiceLoop = false;
                        break;
                    default:
                        break;
                }
            }
    }
    //게임시작 시 케릭 선택.
    //[1]전사 [2]요정 [3]법사 [4]게임설명
    public void stateSettingCharacter() {
        Skill skill = new Skill();
        Scanner scanner = new Scanner(System.in);
        //배경음악 설정 후 재생.
        backGroundMusic = new BackGroundMusic();
        //배경음악을 선택해서 셋팅
        backGroundMusic.music("mainBGM.MP3");
        //배경음악 시작 쓰레드
        backGroundMusic.start();
        int inputValue = 0;
        boolean choiceLoop = true;
        String characterName = " ";
        while (choiceLoop) {
            //선택지에 대한 이미지 출력
            subtitle.menuLinePrint(subtitle.getPickCharacter());
            //선택지에 대한 사용자 입력값을 체크하는 함수.
            inputValue = inputValueCheck(1,4);
            switch (inputValue) {
                case 1://유저의 케릭터 선택. 전사
                    System.out.println("전사를 선택하셨습니다. [1]계속 진행 [2]바꾸기");
                    inputValue = inputValueCheck(1,2);
                    if (inputValue == 1) {
                        System.out.println("케릭터 이름을 입력 해 주세요 : ");
                        characterName = scanner.nextLine();
                        System.out.printf("%s 이름으로 정하시겠습니까? [1]네 [2]다시", characterName);

                        inputValue = inputValueCheck(1,2);
                        if (inputValue == 1) {
                            System.out.printf("반갑습니다. %s전사님!\n", characterName);
                            //유저의 선택에 따라 케릭터 실체화
                            userCharacter = new Knight(characterName);
                            //케릭터별 기본 스킬 초기화
                            userCharacter.getSkillBook().add(skill.getHeal());
                            userCharacter.getSkillBook().add(skill.getFirePrick());
                            userCharacter.getSkillBook().add(skill.getGuard());
                            userCharacter.getSkillBook().add(skill.getBlessWeapon());
                            userCharacter.getSkillBook().add(skill.getStun());
                            choiceLoop = false;
                            break;
                        } else if (inputValue == 2) {
                            break;
                        }
                    } else if (inputValue == 2)
                        break;
                case 2://유저의 케릭터 선택. 요정
                    System.out.println("요정을 선택하셨습니다. [1]계속 진행 [2]바꾸기");
                    inputValue = inputValueCheck(1,2);
                    if (inputValue == 1) {
                        System.out.println("케릭터 이름을 입력 해 주세요 : ");
                        characterName = scanner.nextLine();
                        System.out.printf("%s 이름으로 정하시겠습니까? [1]네 [2]다시", characterName);
                        inputValue = inputValueCheck(1,2);
                        if (inputValue == 1) {
                            System.out.printf("반갑습니다. %s요정님!\n", characterName);
                            //케릭터 최종 선택이 완료되면 요정 클래스를 실체화 하고 요정이 가지는 고유 스킬을 케릭터
                            //skillBook에 추가한다.
                            userCharacter = new Elf(characterName);
                            userCharacter.getSkillBook().add(skill.getHeal());
                            userCharacter.getSkillBook().add(skill.getIceArrow());
                            userCharacter.getSkillBook().add(skill.getGuard());
                            userCharacter.getSkillBook().add(skill.getBlessWeapon());
                            userCharacter.getSkillBook().add(skill.getStun());
                            choiceLoop = false;
                            break;
                        } else if (inputValue == 2) {
                            break;
                        }
                    } else if (inputValue == 2)
                        break;
                case 3://유저의 케릭터 선택. 마법사
                    System.out.println("마법사를 선택하셨습니다. [1]계속 진행 [2]바꾸기");
                    inputValue = inputValueCheck(1,2);
                    if (inputValue == 1) {
                        System.out.println("케릭터 이름을 입력 해 주세요 : ");
                        characterName = scanner.nextLine();
                        System.out.printf("%s 이름으로 정하시겠습니까? [1]네 [2]다시", characterName);
                        inputValue = inputValueCheck(1,2);
                        if (inputValue == 1) {
                            System.out.printf("반갑습니다. %s마법사님!\n", characterName);
                            userCharacter = new Wizard(characterName);
                            //케릭터 최종 선택이 완료되면 요정 클래스를 실체화 하고 마법사가 가지는 고유 스킬을 케릭터
                            //skillBook에 추가한다.
                            userCharacter.getSkillBook().add(skill.getHeal());
                            userCharacter.getSkillBook().add(skill.getWindStorm());
                            userCharacter.getSkillBook().add(skill.getEarthQuake());
                            userCharacter.getSkillBook().add(skill.getGuard());
                            userCharacter.getSkillBook().add(skill.getBlessWeapon());
                            userCharacter.getSkillBook().add(skill.getStun());
                            choiceLoop = false;
                            break;
                        } else if (inputValue == 2) {
                            break;
                        }
                    } else if (inputValue == 2)
                        break;
                case 4://게임설명.
                    System.out.println(subtitle.getGameDescription());
                        break;
                default:
                    break;
            }
        }
    }
    public int gethuntingPlaceNumber() {return huntingPlaceNumber;}
    public static UserCharacter getuserCharacter() {return userCharacter;}

}
