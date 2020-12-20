public class Main {
    private static Subtitle subtitle = new Subtitle();
    private static Service service = new Service();
    private static UserCharacter userCharacter = null;
    static Item item = new Item();

    public static synchronized void main(String[] args) {
        //게임시작 시 제목 출력
        System.err.println(subtitle.getTitle());
        //3가지 케릭터 선택지 출력
        subtitle.subtitlePrint(subtitle.getChocieCharacter());
        //케릭터 선택
        service.stateSettingCharacter();
        //유저가 선택한 케릭터를 실체화
        userCharacter = service.getuserCharacter();
        //유저의 기본정보를 출력 후 게임시작.
        subtitle.menuLinePrint(userCharacter.toString());
        //전체 아이템을 판매상인에게 맞게 초기화.
        item.settingItems();
        //사냥 or 마을이동에 대한 선택지 출력
        service.stateSettingHuntingOrTown();

    }
}

