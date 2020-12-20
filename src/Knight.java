import java.util.ArrayList;
//전사 케릭터 클래스
public class Knight extends UserCharacter {
    //레벨이 10이 됐을경우 공격력을 영원히 5 증가 시킨다.
    int powerUpValue = 5;

    public int getPowerUpValue() {
        return powerUpValue;
    }

    public Knight() {
    }

    @Override
    public String toString() {
        return "\n[Knight]\n" +
                " 이름 =" + name +
                "\n 레벨 =" + level +
                "\n 공격력=" + power +
                "\n 방어력=" + defense  +
                "\n HP=" + hitPoint +"/" +maxHitPoint +
                "\n MP=" + magicPoint +"/" +maxMagicPoint +
                "\n 경험치=" + experiencePoint +
                "\n 돈=" + money +  "[Gold]"+
                "\n 가방= " + userInventory.size() + " 개" +
                "\n 착용한아이템= " + wearingItem.size() +" 개 착용중 " +
                "\n 스킬=" + skillBook.size() + "개 보유중";
    }
    //전사케릭터의 초기화 함수.
    public Knight(String name) {
        super(1, 10, 5, 50,
                20,  1000, name, 50,20,
                0, new ArrayList<Items>(), new ArrayList<Items>(),
                new ArrayList<Skill>());
    }
    /*--------------------------------getter & setter----------------------------------*/
    @Override
    public int attack() {return super.attack();}
    @Override
    public int skillAttack() {
        return super.skillAttack();
    }
    public void actionPowerUpValue(){
        super.power += this.powerUpValue;
    }
   }
