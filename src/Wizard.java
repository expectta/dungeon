import java.util.ArrayList;

public class Wizard extends UserCharacter{
    //레벨이 10이 됐을경우 영원히 최대마나를 10 증가 시킨다.
    int recoveryValue = 10;
    @Override
    public String toString() {
        return "\n[Wizard]\n" +
                " 이름 =" + name +
                "\n 레벨 =" + level +
                "\n 공격력=" + power +
                "\n 방어력=" + defense  +
                "\n HP=" + hitPoint + "/" +maxHitPoint +
                "\n MP=" + magicPoint +"/" +maxMagicPoint +
                "\n 경험치=" + experiencePoint +
                "\n 돈=" + money +  "[Gold]"+
                "\n 가방= " + userInventory.size() + " 개" +
                "\n 착용한아이템= " + wearingItem.size() +" 개 착용중 " +
                "\n 스킬=" + skillBook.size() + "  개 보유중"
                ;
    }

    public Wizard(String name) {
        super(1, 7, 5, 30,50,
                  1000, name, 30,50,
                0, new ArrayList<Items>(), new ArrayList<Items>(),
                new ArrayList<Skill>());
    }
    @Override
    public int attack() {return super.attack();}
    @Override
    public int skillAttack() {return super.skillAttack();}
    public static void useItem(){
    System.out.println("dd");
    }
    public void actionRecoveryValue(){
        super.setMaxMagicPoint(recoveryValue);
    }
    public int getRecoveryValue() {
        return recoveryValue;
    }
}