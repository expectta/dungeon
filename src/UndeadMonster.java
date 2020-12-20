import java.util.ArrayList;

public class UndeadMonster extends Monster {
    @Override
    public String toString() {
        return "\n[UndeadMonster]\n" +
                "레벨=" + level +
                "  공격력=" + power +
                "  HP=" + hitPoint +
                "  MP=" + magicPoint +
                "  경험치=" + experiencePoint +
                "  속성='" + attribute + '\'';
    }
    public UndeadMonster() {
        super(1, 3, 5, 20,
                10, 100, 20,
                "언데드", new ArrayList<Items>());
    }
    /*--------------------------------getter & setter----------------------------------*/
    @Override
    public int attack() {return super.attack();}
    @Override
    public int skillAttack() {return super.skillAttack();}
    @Override
    public int getExperiencePoint() {return super.getExperiencePoint();}




}
