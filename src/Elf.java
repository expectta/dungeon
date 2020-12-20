import java.util.ArrayList;

public class Elf extends UserCharacter {

    //레벨이 10이 됐을경우 방어력을 영원히 5 증가 시킨다.
    int evasionValue =5 ;
       @Override
    public String toString() {
        return "\n[Elf]\n" +
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
                "\n 스킬=" + skillBook.size() + "  개 보유중";
    }
    public Elf(String name) {
        super(1, 8, 4,40,
                30, 1000, name, 40,30,
                0, new ArrayList<Items>(), new ArrayList<Items>(),
                new ArrayList<Skill>());
    }
    public int getEvasionValue() {
        return evasionValue;
    }
    @Override
    public int attack() {return super.attack();}
    @Override
    public int skillAttack() {return super.skillAttack();}
    public void actionEvasionValue(){
           super.setdefense(evasionValue);
    }
    }

