public class Skill {
    /*protection: 보호,보호물
    Expenditure:지출,비용,경비
    */
    String name;
    int damage;
    int protection;
    int magicPointExpenditure;
    String type;
    /*heal = 힐 , 치유
    firePrick = 찌르기
    iceArrow = 얼음화살
    windStorm = 바람폭풍
    earthQuake = 지진
    guard = 보호
    <<계열별에 따른 상성>>
    언데드 < 신성 , 물 < 땅, 바람 < 번개, 불 < 물
    */
    static Skill heal = new Skill("힐",10,0,10);
    static Skill firePrick = new Skill("찌르기",20,0,10);
    static Skill iceArrow = new Skill("얼음화살",20,0,10);
    static Skill windStorm = new Skill("폭풍",35,0,10);
    static Skill earthQuake = new Skill("지진",35,0,10);
    static Skill guard = new Skill("보호",0,10,5);
    static Skill blessWeapon = new Skill("무기강화",10,0,5);



    static Skill stun = new Skill("스턴", 10,0,5);


    public Skill() {
    }
    public Skill(String name, int damage, int protection, int magicPointExpenditure) {
        this.name = name;
        this.damage = damage;
        this.protection = protection;
        this.magicPointExpenditure = magicPointExpenditure;

    }
    /*--------------------------------getter & setter----------------------------------*/
    @Override
    public String toString() {
        return
                "  [이름=" + name + " ]" +
                "        공격력=" + damage +
                "        방어력=" + protection +
                "        마나소모=" + magicPointExpenditure +
                 "\n" ;
    }
    public static Skill getStun() {
        return stun;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getMagicPointExpenditure() {
        return magicPointExpenditure;
    }

    public void setMagicPointExpenditure(int magicPointExpenditure) {
        this.magicPointExpenditure = magicPointExpenditure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Skill getHeal() {
        return heal;
    }

    public static Skill getFirePrick() {
        return firePrick;
    }

    public static Skill getIceArrow() {
        return iceArrow;
    }

    public static Skill getWindStorm() {
        return windStorm;
    }

    public static Skill getGuard() {
        return guard;
    }
    public static Skill getEarthQuake() {
        return earthQuake;
    }
    public static Skill getBlessWeapon() {
        return blessWeapon;
    }
}
