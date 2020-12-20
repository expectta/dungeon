import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//아이템을 모두 실체화 하고 마을별 판매되는 아이템을 List에추가하는 클래스
public class Item  {
    //아이템이름, 효과, 가격, 계열
    static Items club = new Items("몽둥이", 3, 200 );
    static Items sword = new Items("대검", 11, 500 );
    static Items twoHnadSword = new Items("양손검", 22, 1000 );
    static Items bow = new Items("낡은활", 5, 200 );
    static Items crossBow = new Items("석궁", 15, 500);
    static Items huningBow = new Items("헌팅보우", 25, 1000 );
    static Items bead = new Items("낡은구슬", 4, 200 );
    static Items silverBead = new Items("은빛구슬", 13, 500 );
    static Items goldBead = new Items("금빛구슬", 26, 1000 );
    static Items shabbyClothes = new Items("허름한옷", 5, 200  );
    static Items silkClothes = new Items("비단옷", 14, 800  );
    static Items gloves = new Items("장갑", 3, 300  );
    static Items highBoots = new Items("부츠", 7, 800 );
    static Items HpPotion = new Items("체력물약", 10, 100);
    static Items MpPotion = new Items("마나물약", 10, 100  );
    static Service service = new Service();
    static ArrayList<Items> weaponTrader = new ArrayList<Items>();
    static ArrayList<Items> armorTrader = new ArrayList<Items>();
    static ArrayList<Items> potionTrader = new ArrayList<Items>();
    //TODO 몬스터가 드랍 할 아이템을 랜덤으로 무기,방어구,물약  ArrayList에서 몬스터 가방에 추가.
    public  static void randomItem(ArrayList<Items> item) {
        Random random = new Random();
        int randomNumber = 0;
        randomNumber = random.nextInt(item.size());
    }

    public static void settingItems() {
        //무기상점의 무기 리스트 초기화
        if(weaponTrader.size() == 0) {
            weaponTrader.add(club);
            weaponTrader.add(sword);
            weaponTrader.add(twoHnadSword);
            weaponTrader.add(bow);
            weaponTrader.add(crossBow);
            weaponTrader.add(huningBow);
            weaponTrader.add(bead);
            weaponTrader.add(silverBead);
            weaponTrader.add(goldBead);
        }
        //무기상점의 무기 리스트 초기화
        if(armorTrader.size() == 0) {
            armorTrader.add(shabbyClothes);
            armorTrader.add(silkClothes);
            armorTrader.add(gloves);
            armorTrader.add(highBoots);
        }
        //무기상점의 무기 리스트 초기화
        if(potionTrader.size() ==0){
            potionTrader.add(HpPotion);
            potionTrader.add(MpPotion);
        }
    }
    //각 마을별 판매하는 아이템이 다르기때문에 파라미터를 마을 특성에 맞는 아이템 리스트로 지정.
    public void printItemList(ArrayList<Items> List) {
        int selectedItem = 0;
        int inputValue = 0;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("──────────────────────────────────────────────────────────────");
        for (int i = 0; i < List.size(); i++) {
            System.out.print(i + "=" + List.get(i) + "\n");
        }
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("현재 보유중인 돈 : " + service.getuserCharacter().money + "[Gold]");
        while (loop) {
            System.out.println("아이템을 구매 하시겠습니까 [1]네 [2]아니요.");
            inputValue = service.inputValueCheck(1,2);
            if (inputValue == 1) {
                System.out.print("아이템 번호를 입력하세요 : ");
            } else if (inputValue == 2) {
                loop = false;
                break;
            }
            //아이템 목록의 범위를 넘어갈 경우 Error 발생. 다시 입력을 유도함.
            //-1 = 아이템 목록이 0 부터 시작하기 때문에 -1이 없을 경우 범위  error 발생한다.
            selectedItem = service.inputValueCheck(0,List.size()-1);
            System.out.printf("구입하시겠습니까? [1]구입 [2]취소\n", List.get(inputValue));
            inputValue = service.inputValueCheck(1,2);
            switch (inputValue) {
                case 1://아이템 구입
                    if (service.getuserCharacter().money >= List.get(selectedItem).getPrice()) {
                        service.getuserCharacter().userInventory.add(List.get(selectedItem));
                        System.out.println("아이템을 구매 했습니다.");
                        service.getuserCharacter().money -= List.get(selectedItem).getPrice();
                        System.out.println("현재 보유중인 돈 : " + service.getuserCharacter().money + "[Gold]");
                        System.out.println("계속 구매 하시겠습니까? [1] 네 [2]나가기");
                        inputValue = service.inputValueCheck(1,2);
                        if (inputValue == 1) {
                            break;
                        } else if (inputValue == 2) {
                            loop = false;
                        }
                    } else if (service.getuserCharacter().money < List.get(inputValue).getPrice()) {
                        System.out.println("돈이 부족합니다.");
                        System.out.println("현재 보유중인 돈 : " + service.getuserCharacter().money + "[Gold]");
                    }
                    break;
                case 2://취소
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
    /*--------------------------------getter & setter----------------------------------*/
    public Items getHpPotion() {
        return HpPotion;
    }

    public Items getMpPotion() {
        return MpPotion;
    }
    public ArrayList<Items> getWeaponTrader() {
        return weaponTrader;
    }

    public ArrayList<Items> getArmorTrader() {
        return armorTrader;
    }

    public ArrayList<Items> getPotionTrader() {
        return potionTrader;
    }
}
//아이템 개별적으로 능력치를 부여하기위한 클래스
class Items {
    String name;
     int stat;
     int price;


    @Override
    public String toString() {
        return "이름 = " + name + " = " + stat + "  가격 = " + price ;
    }

    public Items(String name, int stat, int price) {
        this.name = name;
        this.stat = stat;
        this.price = price;
    }

    /*--------------------------------getter & setter----------------------------------*/
    public  String getName() {
        return this.name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  int getStat() {
        return stat;
    }

    public  void setStat(int stat) {
        this.stat = stat;
    }

    public  int getPrice() {
        return price;
    }

    public  void setPrice(int price) {
        this.price = price;
    }

}
