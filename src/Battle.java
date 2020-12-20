import javax.print.MultiDocPrintService;
import java.util.Scanner;

public class Battle extends Thread {
    static Service service = new Service();
    static Monster selectMonster = new UndeadMonster();//원래는 null
    BossMonster bossMonster = null;
    BackGroundMusic backGroundMusic;
    Subtitle subtitle = new Subtitle();
//    AutoAttack autoAttack = null;
    Item item = new Item();
    static int weaponItem = 0;
    static int armorItem =0 ;
    MonsterThread monsterThread;

    //유저가 장착중인 아이템이 무기인지 방어구인지 판단하는 함수.
    public void userItem(){
        for(int i =0; i< service.getuserCharacter().getWearingItem().size(); i++){
            //유저가 착용한 아이템이 무기일경우
            if(item.getWeaponTrader().contains(service.getuserCharacter().getWearingItem().get(i))){
                weaponItem += service.getuserCharacter().getWearingItem().get(i).stat;
                //유저가 착용한 아이템이 방어구 일경우.
            }else if(item.getArmorTrader().contains(service.getuserCharacter().getWearingItem().get(i))){
                armorItem += service.getuserCharacter().getWearingItem().get(i).stat;
            }
        }
    }
    //각 던전별로 배경음악을 따로 재생하는 함수.
    //파라메터는 던전과 같은 이름의 MP3파일의 이름.(project 내 Music 폴더에 MP3파일있음)
    public void musicSwitch(String musicName) {
        //게임 시작할때 main 클래스에서 재생한 mainBGM을 종료시킨다.
        if(backGroundMusic != null){
            backGroundMusic.close();
        }
        //동일한 쓰레드를 두번 start()하는것이 불가능 하기 때문에 객체를 다시 실체화 해서 배경음악을 바꾸고 재생.
        backGroundMusic = new BackGroundMusic();
        backGroundMusic.music(musicName + ".MP3");
        backGroundMusic.start();
    }
    //각 던전별로 등장하는 몬스터의 속성을 나누는 함수
    // 해골던전 = undeadMonster  얼음던전=WaterMonster  바람던전=WindMonster  불던전=fireMonster
    public void checkMonster() {
        //유저 케릭터의 레벨이 10,20,30,40이 아닐때 일반몬스터 소환.
        if (service.getuserCharacter().getLevel() != 10 ||
                service.getuserCharacter().getLevel() != 20 ||
                service.getuserCharacter().getLevel() != 30 ||
                service.getuserCharacter().getLevel() != 40) {
            //1 = 해골던전 2 = 얼음던전 3 = 바람던전 4 = 불던전
            //사용자가 입력한 던전에 입장할 때 마다 musicSwitch 함수로 해당던전의 배경음악을 바꾸는 함수
            if (service.gethuntingPlaceNumber() == 1) {
                selectMonster = new UndeadMonster();
                musicSwitch("undeadDungeon");
            } else if (service.gethuntingPlaceNumber() == 2) {
                selectMonster = new WaterMonster();
                musicSwitch("waterDungeon");
            } else if (service.gethuntingPlaceNumber() == 3) {
                selectMonster = new WindMonster();
                musicSwitch("windDungeon");
            } else if (service.gethuntingPlaceNumber() == 4) {
                selectMonster = new FireMonster();
                musicSwitch("fireDungeon");
            }
        }
        //유저의 레벨이 10,20,30,40일때 보습몬스터 소환
        if (service.getuserCharacter().getLevel() == 10 ||
                service.getuserCharacter().getLevel() == 20 ||
                service.getuserCharacter().getLevel() == 30 ||
                service.getuserCharacter().getLevel() == 40) {
            bossMonster = new BossMonster();
            battle(bossMonster);
        } else
            battle(selectMonster);
    }
    //결투시 몬스터와 유저 상태를 보여주는 함수.
    public  void  monsterAndUserState(Monster monster) {

        //몬스터의 레벨, 공격력, 방어력, HP, MP를 출력
        System.out.println(monster.toString());
        //유저의 레벨,공격력,HP,MP,방어력을 출력
        System.out.println("[" + service.getuserCharacter().name + "]\n" +
                "레벨=" + service.getuserCharacter().getLevel() + "" +
                "  공격력=" + service.getuserCharacter().getPower() + "" +
                "  HP=" + service.getuserCharacter().getHitPoint() + "" +
                "  MP=" + service.getuserCharacter().getMagicPoint()+"" +
                "  방어력=" + service.getuserCharacter().getdefense());
        //[1]공격하기 [2]스킬쓰기 [3]아이템사용하기 [4]도망가기 출력
        subtitle.menuLinePrint(subtitle.getMonsterMeet());
    }
    //유저케릭터와 몬스터의 결투 함수
    public synchronized void battle(Monster monster) {
        notify();
        Thread threadUser = new Thread(service.getuserCharacter());
        monsterThread = new MonsterThread(monster);
        //몬스터 결정 후 Character 클래스 공격 쓰레드 몬스터로 지정.
        int inputValue =0;
        boolean choiceLoop = true;
        //몬스터가 가지고있는 랜덤 아이템 초기화
        Item.randomItem(Item.weaponTrader);
        Item.randomItem(Item.armorTrader);
        Item.randomItem(Item.potionTrader);
        System.out.println("몬스터를 만났습니다.");
        //유저 케릭터의 레벨에 맞춰 생성되는 몬스터의 레벨이 달라짐. 레벨이 높을수록 능력치가 높아짐.
//        autoAttack = new AutoAttack();
        //몬스터의 레벨은 항상 유저의 레벨과 동일하게 유지 될 수 있도록 현재유저의 레벨을 체크하고
        //그에따른 몬스터의 레벨을 동일 레벨로 지정 하여 몬스터의 능력치를 조정함.
        monster.stateSetting(service.getuserCharacter());
        monsterThread.start();
        while (choiceLoop) {
            if (service.getuserCharacter().hitPoint <= 0) {
                monsterThread.interrupt();
                System.out.println("케릭터가 부활하면 HP,MP는 회복되지 않습니다. [1]확인 [2]게임종료");
                inputValue = service.inputValueCheck(1,2);
                if (inputValue == 1) {
                    //TODO 사망했을때 부활시켜줘야 함.
                    choiceLoop = false;
                } else if (inputValue == 2) {
                    System.exit(0);
                    choiceLoop = false;
                    break;
                }
            }
            //몬스터와 유저의 상태를 출력함.
            monsterAndUserState(monster);
            //사용자가 선택지에 대한 입력값을 체크하는 함수
            inputValue = service.inputValueCheck(1,4);
            if(service.getuserCharacter().getHitPoint() <=0){
                break;
            }


            switch (inputValue) {
                case 1://일반공격
                    //공격력과 방어력의 관계 : 일반공격power-(방어력/4) = 피해를 입는 수치.
                    //TODO 일반공격 시작하면 몬스터는 공격 시간마다 공격 해온다.

                    break;
                case 2://스킬사용
                    int skillNumber = 0;
                    Skill skill = new Skill();
                    //케릭터가 보유한 Skill List를 출력
                    System.out.println("──────────────────────────────────────────────────────────────");
                    for (int i = 0; i < service.getuserCharacter().getSkillBook().size(); i++) {
                        System.out.print(i + " = " + service.getuserCharacter().getSkillBook().get(i));
                    }
                    System.out.println("──────────────────────────────────────────────────────────────");
                    System.out.println("스킬 번호를 입력하세요 : ");
                    skillNumber = service.inputValueCheck(0,service.getuserCharacter().getSkillBook().size());
                    //사용하려는 스킬종류에 따라 적용범위가 달라진다.
                    //힐을 사용할 경우 유저의 HP를 상승시킴.
                    if (service.getuserCharacter().getMagicPoint() >=
                            service.getuserCharacter().getSkillBook().get(skillNumber).getMagicPointExpenditure() &&
                            service.getuserCharacter().getSkillBook().get(skillNumber) == skill.getHeal()) {
                        service.getuserCharacter().hitPoint += skill.getHeal().getDamage();
                        //유저의 회복량이 최대 HP를 넘기면 최대 HP로 대입.
                        if (service.getuserCharacter().hitPoint > service.getuserCharacter().getMaxHitPoint()) {
                            service.getuserCharacter().setHitPoint(service.getuserCharacter().getMaxHitPoint());
                            System.out.println("유저의 HP 가득찼습니다.");
                        } else
                            System.out.println(service.getuserCharacter().getSkillBook().get(skillNumber).name
                                    + "을 사용하여 유저의 HP를" +
                                    service.getuserCharacter().getSkillBook().get(skillNumber).getDamage()
                                    + "만큼 상승 시켰습니다.");
                        //스킬사용시 유저의 MP를 감소시킴.
                        service.getuserCharacter().magicPoint -= service.getuserCharacter().getSkillBook().get(skillNumber).magicPointExpenditure;
                        System.out.printf("유저의 MP가 %d남았습니다.\n", service.getuserCharacter().magicPoint);
                        break;
                        //보호막 스킬은 방어력을 상승시킴.
                    } else if (service.getuserCharacter().getMagicPoint() >=
                            service.getuserCharacter().getSkillBook().get(skillNumber).getMagicPointExpenditure() &&
                            service.getuserCharacter().getSkillBook().get(skillNumber) == skill.getGuard()) {
                        //유저가 방어 스킬을 사용할 경우 본인의 방어력에 스킬의 방어력만큼 상승하고 스킬의 지속시간은 5초
                        //스킬의 발동 및 일정 시간 뒤 해제하는 쓰레드.
                        Thread skillRunTimeeGuard = new SkillRunTimeCount(service.getuserCharacter().getSkillBook().get(skillNumber).name);
                        skillRunTimeeGuard.start();
                        System.out.println(service.getuserCharacter().getSkillBook().get(skillNumber).name
                                + "을 사용하여 유저의 방어력을" +
                                service.getuserCharacter().getSkillBook().get(skillNumber).getProtection()
                                + "만큼 상승 시켰습니다. 지속시간 : 30초");
                        service.getuserCharacter().magicPoint -= service.getuserCharacter().getSkillBook().get(skillNumber).magicPointExpenditure;
                        System.out.printf("유저의 MP가 %d남았습니다.\n", service.getuserCharacter().magicPoint);
                        break;
                        //무기강화 스킬은 공격력을 상승시킨다.
                    } else if (service.getuserCharacter().getMagicPoint() >=
                            service.getuserCharacter().getSkillBook().get(skillNumber).getMagicPointExpenditure() &&
                            service.getuserCharacter().getSkillBook().get(skillNumber) == skill.getBlessWeapon()) {
                        Thread skillRunTimeDamage = new SkillRunTimeCount(service.getuserCharacter().getSkillBook().get(skillNumber).name);
                        skillRunTimeDamage.start();
                        System.out.println(service.getuserCharacter().getSkillBook().get(skillNumber).name
                                + "을 사용하여 유저의 공격력을" +
                                service.getuserCharacter().getSkillBook().get(skillNumber).getDamage()
                                + "만큼 상승 시켰습니다. 지속시간 : 20초");
                        service.getuserCharacter().magicPoint -= service.getuserCharacter().getSkillBook().get(skillNumber).magicPointExpenditure;
                        System.out.printf("유저의 MP가 %d남았습니다.\n", service.getuserCharacter().magicPoint);

                        //그외 공격 스킬은 몬스터에게 데미지를 입힘.
                    }else if (service.getuserCharacter().getMagicPoint() >=
                        service.getuserCharacter().getSkillBook().get(skillNumber).getMagicPointExpenditure() &&
                        service.getuserCharacter().getSkillBook().get(skillNumber) == skill.getStun()) {
                    System.out.println(service.getuserCharacter().getSkillBook().get(skillNumber).name
                            + "을 사용하여 monster에게 " +
                            service.getuserCharacter().getSkillBook().get(skillNumber).getDamage()
                            + "만큼 피해를 입g.");
                    System.out.println("스턴이 걸렸다.");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3초기지났다");

                }else if (service.getuserCharacter().getMagicPoint() >=
                            service.getuserCharacter().getSkillBook().get(skillNumber).getMagicPointExpenditure() &&
                            service.getuserCharacter().getSkillBook().get(skillNumber) != skill.getHeal() &&
                            service.getuserCharacter().getSkillBook().get(skillNumber) != skill.getGuard()) {
                        monster.hitPoint -= service.getuserCharacter().getSkillBook().get(skillNumber).getDamage();
                        System.out.println(service.getuserCharacter().getSkillBook().get(skillNumber).name
                                + "을 사용하여 monster에게 " +
                                service.getuserCharacter().getSkillBook().get(skillNumber).getDamage()
                                + "만큼 피해를 입혔습니다.");
                        service.getuserCharacter().magicPoint -= service.getuserCharacter().getSkillBook().get(skillNumber).magicPointExpenditure;
                        System.out.printf("유저의 MP가 %d남았습니다.\n", service.getuserCharacter().magicPoint);
                        //스킬 사용시 몬스터가 죽을때.
                        if (monster.hitPoint <= 0) {
                            System.out.printf("몬스터가 죽었습니다\n");
                            service.getuserCharacter().userInventory.addAll(monster.getMonsterInventory());
                            monster.getMonsterInventory().clear();
                            service.getuserCharacter().money += monster.getMoney();
                            System.out.printf("몬스터" +
                                    "에게 %d[Gold]를 얻었습니다.\n", monster.getMoney());
                            System.out.printf("몬스터에게서 %d경험치를 얻었습니다. \n", monster.getExperiencePoint());
                            userItem();

                            service.getuserCharacter().setExperiencePoint(monster.getExperiencePoint());
                            choiceLoop = false;
                        }
                        //유저의 MP가 없을경우 스킬을 사용할 수 없음.
                    } else if (service.getuserCharacter().getMagicPoint() <=
                            service.getuserCharacter().getSkillBook().get(skillNumber).getMagicPointExpenditure())
                        System.out.println("마나가부족하여 스킬을 사용할 수 없습니다.");
                    break;
                case 3://상태창
                    break;
                case 4://도망
                    System.out.println("도망쳤습니다..");
                    monsterThread.interrupt();
                    choiceLoop = false;
                    break;
                default:
                    break;
            }
        }
        backGroundMusic.close();
    }
    public synchronized void actionBattle(){
        notifyAll();
        Character user;
        Service service;
        Scanner scanner = new Scanner(System.in);
        boolean isLoop=true;
        int inputValue = 0;
        System.out.println("1.공격 2. 스킬. 3아이템 사용 4.도망");
        inputValue = scanner.nextInt();
        if (inputValue == 1) {
            selectMonster.hitPoint -= Service.getuserCharacter().attack() - (selectMonster.getdefense() / 4);
            System.out.printf("monster에게 %d피해를 입혔습니다.\n", Service.getuserCharacter().attack() - (selectMonster.getdefense() / 4));
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (selectMonster.hitPoint <= 0) {
                //몬스터가의 HP가 0이 됐을경우 자동공격 쓰레드 종료.
                System.out.printf("몬스터가 죽었습니다\n");
                //몬스터의 HP가 0보다 적을 경우 무조건 0으로 셋팅.
                selectMonster.setHitPoint(0);
                Service.getuserCharacter().userInventory.addAll(selectMonster.getMonsterInventory());
                selectMonster.getMonsterInventory().clear();
                Service.getuserCharacter().money += selectMonster.getMoney();
                System.out.printf("몬스터에게 %d[Gold]를 얻었습니다.\n", selectMonster.getMoney());
                System.out.printf("몬스터에게서 %d경험치를 얻었습니다. \n", selectMonster.getExperiencePoint());
                Service.getuserCharacter().setExperiencePoint(selectMonster.getExperiencePoint());
            }
        }
        monsterAndUserState(selectMonster);

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getWeaponItem() {
        return weaponItem;
    }
    public static Monster getSelectMonster() {
        return selectMonster;
    }
    public static int getArmorItem() {return armorItem;}
}
class ThreaCohice extends Thread {
    Battle battle;

    public ThreaCohice(Battle battle) {
        this.battle = battle;
    }
    @Override
    public void run() {
        System.out.println("여기는 배틀 쓰레드");
        while(true){
            battle.battle(new UndeadMonster());
        }
    }
}
class ThreadUser extends Thread{
    Character user ;
    Battle battle;
    public ThreadUser(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void run() {
        System.out.println("여기는 유저 쓰레드");
        while(true){
            battle.actionBattle();
        }
    }
}
//몬스터가 자동으로 유저케릭터를 공격하는 쓰레드
//class UserChraracterThread extends Thread{
//    Character user;
//    Monster monster;
//    Battle battle;
//    Service service;
//    Scanner scanner = new Scanner(System.in);
//    boolean isLoop=true;
//    public UserChraracterThread(Character user) {
//        this.user = user;
//    }
//
//    @Override
//    public void run() {
//        int inputValue = 0;
//        System.out.println("1.공격 2. 스킬. 3아이템 사용 4.도망");
//        inputValue = scanner.nextInt();
//        while(isLoop) {
//            if (inputValue == 1) {
//
//                monster.hitPoint -= service.getuserCharacter().attack() - (monster.getdefense() / 4);
//                System.out.printf("monster에게 %d피해를 입혔습니다.\n", service.getuserCharacter().attack() - (monster.getdefense() / 4));
//
//                if (monster.hitPoint <= 0) {
//                    //몬스터가의 HP가 0이 됐을경우 자동공격 쓰레드 종료.
//                    System.out.printf("몬스터가 죽었습니다\n");
//                    //몬스터의 HP가 0보다 적을 경우 무조건 0으로 셋팅.
//                    monster.setHitPoint(0);
//                    Service.getuserCharacter().userInventory.addAll(monster.getMonsterInventory());
//                    monster.getMonsterInventory().clear();
//                    Service.getuserCharacter().money += monster.getMoney();
//                    System.out.printf("몬스터에게 %d[Gold]를 얻었습니다.\n", monster.getMoney());
//                    System.out.printf("몬스터에게서 %d경험치를 얻었습니다. \n", monster.getExperiencePoint());
//                    Service.getuserCharacter().setExperiencePoint(monster.getExperiencePoint());
//                }
//            }
//            battle.monsterAndUserState(monster);
//        }
//    }
//}
class MonsterThread extends Thread{
    Monster monster;
    Battle battle;
    Service service;
    int normalAttackSpeed = 2;
    int stun = 10;
    public MonsterThread(Monster monster) {
        this.monster = monster;
    }
    @Override
    public void run() {
        service = new Service();
        battle = new Battle();
        int timer = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                sleep(1000);
                timer += 1;
                //3초마다 몬스터가 유저케릭터를 공격할 수 있도록 설정.
                if (timer % normalAttackSpeed == 0) {
                    //3초마다 일반공격으로 유저케릭터를 공격함.
                    Service.getuserCharacter().hitPoint -= battle.selectMonster.attack() - (Service.getuserCharacter().getdefense() / 4);
                    System.err.printf("monster로부터 %d피해를 받았습니다.\n", battle.selectMonster.getPower() - Service.getuserCharacter().getdefense() / 4);
                    if (Service.getuserCharacter().getHitPoint() <= 0) {
                        //유저 케릭터의 체력이 음수로 표현되지 않도록 죽고나면 0으로 초기화
                        Service.getuserCharacter().setHitPoint(0);
                        System.err.println("유저케릭터가 사망했습니다.");
                        System.err.println("부활하시겠습니까? [1]부활 [2]게임종료");
                        break;
                    }
                }
            }
            sleep(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("몬스터 공격 쓰레드 종료");
        }
    }
}
//class AutoAttack extends Thread {
//    Battle battle;
//    Service service;
//    int normalAttackSpeed = 2;
//    int stun = 10;
//    @Override
//    //몬스터의 자동공격 함수
//    public void run() {
//        service = new Service();
//        battle = new Battle();
//        int timer = 0;
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                sleep(1000);
//                timer += 1;
//                //3초마다 몬스터가 유저케릭터를 공격할 수 있도록 설정.
//                if (timer % normalAttackSpeed == 0) {
//                    //3초마다 일반공격으로 유저케릭터를 공격함.
//                    Service.getuserCharacter().hitPoint -= battle.selectMonster.attack() - (Service.getuserCharacter().getdefense() / 4);
//                    System.err.printf("monster로부터 %d피해를 받았습니다.\n", battle.selectMonster.getPower() - Service.getuserCharacter().getdefense() / 4);
//                    if (Service.getuserCharacter().getHitPoint() <= 0) {
//                        //유저 케릭터의 체력이 음수로 표현되지 않도록 죽고나면 0으로 초기화
//                        Service.getuserCharacter().setHitPoint(0);
//                        System.err.println("유저케릭터가 사망했습니다.");
//                        System.err.println("부활하시겠습니까? [1]부활 [2]게임종료");
//                        break;
//                    }
//                }
//            }
//            sleep(200);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            System.out.println("몬스터 공격 쓰레드 종료");
//        }
//    }
//}

//유저가 사용하는 스킬에 대한 상태유지 쓰레드
class SkillRunTimeCount extends Thread {
    static Service service = new Service();
    Skill skill = new Skill();
    //방어스킬의 지속시간.
    static int  protectionRunningTime = 30;
    //무기강화 스킬의 지속시간.
    static int blessWeaponRunningTime = 30;
    String skillName = " ";
    public SkillRunTimeCount(String skillName) {
        this.skillName = skillName;
    }
    @Override
    public void run() {
        int timer = 0;
        try {//상태유지 스킬은 방어스킬과 무기강화스킬 두가지가 있다.
            if (skillName == skill.getGuard().name) {
                //유저가 선택한 스킬이 유저케릭터의 방어력을 상승시킴
                service.getuserCharacter().defense += skill.getGuard().getProtection();
            } else if (skillName == skill.getBlessWeapon().name) {
                //유저가 선택한 스킬이 유저케릭터의 공격력을 상승시킴
                service.getuserCharacter().power += skill.getBlessWeapon().getDamage();
            }
            //1초마다 timer를 1씩 증가 시키며 각 스킬의 지속시간이 됐을 경우 스킬의 효과를 해제
            while (!Thread.currentThread().isInterrupted()) {
                sleep(1000);
                timer += 1;
                //스킬타이머가 30일 경우 방어스킬 해제
                if (skillName == skill.getGuard().name && timer == protectionRunningTime) {
                    service.getuserCharacter().defense -= skill.getGuard().getProtection();
                    System.err.println("보호스킬 효과가 사라집니다.");
                    break;
                    //스킬타이머가 20일 경우 무기스킬 해제
                } else if (skillName == skill.getBlessWeapon().name && timer == blessWeaponRunningTime) {
                    service.getuserCharacter().power -= skill.getBlessWeapon().getDamage();
                    System.err.println("무기강화 효과가 사라집니다.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("스킬 효과 쓰레드 죽음");
        }

    }
}
