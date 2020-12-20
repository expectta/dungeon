import java.util.ArrayList;

//최상위 부모클래스
public class Character {
    int level;
    int power;
    int defense;
    int hitPoint;
    int magicPoint;
    int money;
    public Character() {
    }
    public Character(int level, int power, int defense, int hitPoint, int magicPoint, int money) {
        this.level = level;
        this.power = power;
        this.defense = defense;
        this.hitPoint = hitPoint;
        this.magicPoint = magicPoint;
        this.money = money;
    }
    public int attack() {
        return this.power;
    }
    public int skillAttack() {
        return this.power;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
            this.power = power;
    }
    public int getdefense() {
        return defense;
    }
    public void setdefense(int defense) {
            this.defense = defense;
            }
    public int getHitPoint() {
        return hitPoint;
    }
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }
    public int getMagicPoint() {
        return magicPoint;
    }
    public void setMagicPoint(int magicPoint) {
        this.magicPoint = magicPoint;
    }
}
//유저 케릭터
//몬스터 케릭터와 싸울때 상호작용이 가능하도록 쓰레드 구현.
class UserCharacter extends Character implements Runnable {
    String name;
    int maxHitPoint;
    int maxMagicPoint;
    int experiencePoint;
    ArrayList<Items> userInventory;
    ArrayList<Items> wearingItem;
    ArrayList<Skill> skillBook;
    Battle battle;
    Service service = new Service();
    Knight knight ;
    @Override
    public void run() {
    }

    @Override
    public int attack() {
        return super.attack();
    }
    @Override
    public int skillAttack() {
        return super.skillAttack();
    }

    public UserCharacter() {
    }
    //전투중에 유저가 몬스터로부터 획득한 경험치가 일정이상 쌓이면 Level을 자동으로 상승시키는 함수.
    //setLevel 함수를 호출 시켜 렙업에 따른 능력치 상승.
    //Battle에서 몬스터 처치 후 경험치를 얻고 호출함.
        public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint += experiencePoint;
        if (this.experiencePoint >= 20 * (super.getLevel() * super.getLevel())) {
            setLevel(1);
        }
    }
    @Override
    //케릭 레벨업 시 능력치 증가.
    public void setLevel(int level) {
        knight= new Knight();
        super.setLevel(super.level += level);
        System.out.println("\n축하합니다!!! 레벨이 상승했습니다!!\n레벨 up =" + (super.level));
        super.setPower(super.power += level + 2);
        System.out.println("공격력 up =" + (super.power += level + 2));
        super.setdefense(super.defense += level + 2);
        System.out.println("방어력 up =" + (super.defense += level + 2));
        this.setMaxHitPoint(this.maxHitPoint += level + 10);
        System.out.println("HP up =" + (this.maxHitPoint += level +7));
        this.setMaxMagicPoint(this.maxMagicPoint += level + 5);
        System.out.println("마나 up =" + (this.maxMagicPoint += level + 5));

        //instanceof 객체타입을 확인하는 연산자.형변환이 가능한지 해당 여부를 true,false 리턴.
        //부모객체인지 자식개체인지 확인하는데 사용
        //service.getuserCharacter() instanceof knight는 자신으로 true
        //유저케릭터가 레벨이 10이 됐을때 고유 케릭별로 고유 능력치를 향상시킨다.
        if(super.level == 2 && service.getuserCharacter() instanceof Knight){
            //전사는 레벨이 10이 됐을때 공격력 5가 향상된다.
            ((Knight) service.getuserCharacter()).actionPowerUpValue();
            System.out.printf("케릭터의 공격력을 %d상승시킵니다.",((Knight) service.getuserCharacter()).getPowerUpValue());
        }else if(super.level == 2 && service.getuserCharacter() instanceof Elf){
            //요정은 레벨이 10이 됐을때 방어력이 5가 상승한다.
            ((Elf) service.getuserCharacter()).actionEvasionValue();
            System.out.printf("케릭터의 방어력을 %d상승시킵니다.",((Elf) service.getuserCharacter()).getEvasionValue());
        }else if(super.level == 2 && service.getuserCharacter() instanceof Wizard){
            ((Wizard) service.getuserCharacter()).actionRecoveryValue();
            System.out.printf("케릭터의 최대마나를 %d상승시킵니다.",((Wizard) service.getuserCharacter()).getRecoveryValue());
        }
    }

    public UserCharacter(int level, int power, int defense, int hitPoint, int magicPoint, int money, String name, int maxHitPoint, int maxMagicPoint, int experiencePoint, ArrayList<Items> userInventory, ArrayList<Items> wearingItem, ArrayList<Skill> skillBook) {
        super(level, power, defense, hitPoint, magicPoint, money);
        this.name = name;
        this.maxHitPoint = maxHitPoint;
        this.maxMagicPoint = maxMagicPoint;
        this.experiencePoint = experiencePoint;
        this.userInventory = userInventory;
        this.wearingItem = wearingItem;
        this.skillBook = skillBook;
    }

    /*--------------------------------getter & setter----------------------------------*/

    public int getMaxMagicPoint() {
        return maxMagicPoint;
    }

    public void setMaxMagicPoint(int maxMagicPoint) {
        this.maxMagicPoint = maxMagicPoint;
    }

    public int getMaxHitPoint() {
        return maxHitPoint;
    }

    public void setMaxHitPoint(int maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
    }
    @Override
    public int getLevel() {
        return super.getLevel();
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    @Override
    public int getPower() {
        return super.getPower();
    }

    @Override
    public void setPower(int power) {
        super.setPower(power);
    }

    @Override
    public int getdefense() {
        return super.getdefense();
    }

    @Override
    public void setdefense(int defense) {
        super.setdefense(defense);
    }

    public ArrayList<Items> getUserInventory() {
        return userInventory;
    }
    public void setUserInventory(ArrayList<Items> userInventory) {
        this.userInventory = userInventory;
    }
    public ArrayList<Items> getWearingItem() {
        return wearingItem;
    }
    public void setWearingItem(ArrayList<Items> wearingItem) {
        this.wearingItem = wearingItem;
    }
    public ArrayList<Skill> getSkillBook() {
        return skillBook;
    }
    public void setSkillBook(ArrayList<Skill> skillBook) {
        this.skillBook = skillBook;
    }
    //TODO 몬스터 클래승와 상호작용이 필요함.

}
//몬스터 클래스
class Monster extends Character implements Runnable{
    @Override
    public void run() {

    }

    int experiencePoint;
    String attribute;
    Item item = new Item();
    ArrayList<Items> monsterInventory;

    public Monster() {
    }
    //유저의 레벨과 같은 레벨로 셋팅하면서 몬스터의 능력치 도 상승시킴.
    //Battle class에서 몬스터를 만나고 바로 호출됨.
    //TODO 공격력과 방어력이 아이템착용한 효과에 중복되서 숫자가 치솟는다..수정필요
    public void stateSetting(Character userLevel) {
        super.setLevel(super.level = userLevel.getLevel());
        super.setPower(super.power += userLevel.getLevel() + 4);
        super.setdefense(super.defense += userLevel.getLevel() + 5);
        super.setHitPoint(super.hitPoint += userLevel.getLevel() + 10);
        super.setMagicPoint(super.magicPoint += userLevel.getLevel() + 4);
        this.experiencePoint += (userLevel.getLevel() + 10);
        super.setMoney(super.money += (userLevel.getLevel()+100)*2 );
    }
    public Monster(int level, int power, int defense, int hitPoint, int magicPoint, int money) {
        super(level, power, defense, hitPoint, magicPoint, money);
    }
    public Monster(int level, int power, int defense, int hitPoint, int magicPoint, int money, int experiencePoint, String attribute,  ArrayList<Items> monsterInventory) {
        super(level, power, defense, hitPoint, magicPoint, money);
        this.experiencePoint = experiencePoint;
        this.attribute = attribute;
        this.monsterInventory = monsterInventory;
    }
    /*--------------------------------getter & setter----------------------------------*/
    @Override
    public int getPower() {
        return super.getPower();
    }
    @Override
    public void setPower(int power) {
        super.setPower(power);
    }

    @Override
    public int getdefense() {
        return super.getdefense();
    }

    @Override
    public void setdefense(int defense) {
        super.setdefense(defense);
    }

    @Override
    public int getHitPoint() {
        return super.getHitPoint();
    }

    @Override
    public void setHitPoint(int hitPoint) {
        super.setHitPoint(hitPoint);
    }

    @Override
    public int getMagicPoint() {
        return super.getMagicPoint();
    }

    @Override
    public void setMagicPoint(int magicPoint) {
        super.setMagicPoint(magicPoint);
    }

    @Override
    public int attack() {
        return super.attack();
    }

    @Override
    public int skillAttack() {
        return super.skillAttack();
    }

    public int getExperiencePoint() {

        return experiencePoint;
    }
    public ArrayList<Items> getMonsterInventory() {
        return monsterInventory;
    }
    @Override
    public int getLevel() {
        return super.getLevel();
    }
    @Override
    public void setLevel(int level) {
        super.setLevel(level);
    }

}
//보스몬스터
class BossMonster extends Monster{
    @Override
    public String toString() {
        return "\n[BossMonster]\n" +
                "  레벨=" + level +" 공격력=" + power +" 방어력=" + defense +" HP=" + hitPoint +
                " MP=" + magicPoint +
                " 경험치=" + experiencePoint ;
    }

    public BossMonster() {
        super(1, 20, 5,
                30, 20,
                500, 100,
                "없음",new ArrayList<Items>() );
    }
    //유저의 레벨이 10,20,30,40이 될때마다 소환되며 레벨은 유저보다 5가 높은상태로 실체화된다.
    //TODO 유저의 레벨이 오를때마다 치솟는 유저의 공격력과, HP , MP문제로 보스가 한방이다.. 유저케릭 능력치 상승 수정필요.
    @Override
    public void stateSetting(Character userLevel) {
            super.setLevel(super.level = (userLevel.getLevel()+8));
            super.setPower(super.power += (userLevel.getLevel()+5) * 2);
            super.setdefense(super.defense += (userLevel.getLevel()+5) * 2);
            super.setHitPoint(super.hitPoint += (userLevel.getLevel()+5) * 8);
            super.setMagicPoint(super.magicPoint += (userLevel.getLevel()+5) * 3);
            this.experiencePoint += (userLevel.getLevel()+5) * 10;
            super.setMoney(super.money += (userLevel.getLevel()+5+100)*2 );
        }
    /*--------------------------------getter & setter----------------------------------*/
    @Override
    public int getPower() {
        return super.getPower();
    }

    @Override
    public void setPower(int power) {
        super.setPower(power);
    }

    @Override
    public int getdefense() {
        return super.getdefense();
    }

    @Override
    public void setdefense(int defense) {
        super.setdefense(defense);
    }

    @Override
    public int getHitPoint() {
        return super.getHitPoint();
    }

    @Override
    public void setHitPoint(int hitPoint) {
        super.setHitPoint(hitPoint);
    }

    @Override
    public int getMagicPoint() {
        return super.getMagicPoint();
    }

    @Override
    public void setMagicPoint(int magicPoint) {
        super.setMagicPoint(magicPoint);
    }

    @Override
    public int attack() {
        return super.attack();
    }

    @Override
    public int skillAttack() {
        return super.skillAttack();
    }

    @Override
    public int getExperiencePoint() {
        return super.getExperiencePoint();
    }

    @Override
    public ArrayList<Items> getMonsterInventory() {
        return super.getMonsterInventory();
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
    }
}
