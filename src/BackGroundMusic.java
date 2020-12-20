import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/*
http://www.javazoom.net/index.shtm
Dev. leader : jlayer@javazoom.net
[Involved in : decoder, converter, simple player, mpeg spi, jlayerme, advanced player]
Contributors :
Matthew McGowan : javalayer@revival.force9.co.uk
Mat, 28 years old, comes from Norway. His hobbies are eating and sleeping :-).
[Involved in : decoder, converter, simple player]
Matthias Pfisterer : matthias.pfisterer@web.de
Matthias, 30 years old, comes from Germany. He works as a software engineer. He launched the "Tritonus" project (http://www.tritonus.org) and he contributed to "Java Sound Examples".
[Involved in : mpeg spi]
Michael Scheerer : m_scheerer@web.de
Michael, 32 years old, comes from Germany too. He's a programmer of a small german company. He's also interested in mathematics, physics, astrophysics, and others sciences stuff.
[Involved in : decoder]
Daniel Szabo : szd713@hotmail.com
Daniel, 29 years old, comes from Hungria. He works as a software engineer. He makes a special study of game programming and algorithm optimization.
[Involved in : decoder]
Micah Spears : micah@usa.com
Micah, 21 years old, comes from USA. He works in software development. His hobbies are drinking and jet skiing. He's also involved in Marquee XML-RPC open source project.
[Involved in : jlayerme]
Paul Santon : wanto_@hotmail.com
Paul, 33 years old, comes from Sydney/Australia. He works as Software Engineer. To learn more about him check out his homepage.
[Involved in : avanced player]
JZOOM의 JLayer 1.0.1 버전
라이센스 : LGPL (첨부 TXT파일 참조)
*/
//TODO 라이브러리란 무엇인가? 신뢰 할 수 있는가? 라이센스는 어떻게 되는가?

/*
https://docs.oracle.com/javase/8/docs/technotes/guides/concurrency/threadPrimitiveDeprecation.html
오라클 기술노트  쓰레드 설명 문서
 */

//게임의 배경음악을 담당하는 클래스
public class BackGroundMusic extends Thread{
    String musicName = " ";
    Player player;
    FileInputStream file;
    BufferedInputStream bufferedInputStream;
    //재생할 음악을 셋팅하는 함수.
    public void music(String name){
        musicName = name;
        //음악파일 name은 함수 호출시 변경 할 수 있도록 파라메타를 사용
        //배경음악은 게임이 처음 시작할때 Music 폴더의 mainBGM이라는 음악 폴더로
        //지정 되어있고 게임안에 4개의 던전으로 이동할 때 마다 다른 음악이 재생 될수 있도록 함.
        //Music 폴더는 현재 project안에 Music임
        try {
            file = new FileInputStream("./Music/" + name);
            bufferedInputStream = new BufferedInputStream(file);
            player = new Player(bufferedInputStream);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    //셋팅된 음악을 재생하는 함수.
    public void run() {
        //while문 조건에 현재 쓰래드의 상태를 isInterrupted(boolean)으로 사용해서
        //차후 쓰래드 종료가 필요할때 close()함수를 통해 쓰래드를 종료 시킴.
        try {
            //무한반복을 위해 현재 쓰레드의 인터럽트 상태를 체크 함. 진행중일때 ture, 중단이면 false.
            while((!Thread.currentThread().isInterrupted())) {
                //FileInputStream을  file에 대입하는 문장이 없이는 반복이 이루어 지지 않음.
                file = new FileInputStream("./Music/" + musicName);
                bufferedInputStream = new BufferedInputStream(file);
                player = new Player(bufferedInputStream);
                player.play();
                //해당 쓰레드를 중단시키기 위해 잠시 sleep상태로 빠지게 한 후 interrupt를 호출하여 쓰레드를 종료 할 수
                //있게 설정 함.
                // sleep이 없을경우 쓰레드는 종료되지 않음.
                sleep(200);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("음악쓰레드 죽음.");
        }
    }
    //음악 플레이를 종료시키는 함수
    public void close(){
        //무한으로 재생되는 음악을 close()함수로 재생을 종료시킨다.
        player.close();
        System.out.println("클로즈 실행됨.");
        //배경음악 재생 쓰레드를 중단시키는 함수 호출.
        this.interrupt();
        System.out.println("인터럽트 됨");

    }
}

