package homework;
import java.util.Random;
public class test3 {
    public static void main(String[] args) {
        Player zhangSan = new Player("ZhangSan");
        Player liSi = new Player("LiSi");
        Player wangWu = new Player("WangWu");
        Game game = new Game();
        System.out.println("Game.run Test");
        game.setFirstPlayer(zhangSan);
        game.setSecondPlayer(liSi);
        game.run(5);
        System.out.println("\nPlayer.reset test");
        System.out.println("before reset:");
        System.out.println("victoryTimes :"+liSi.getVictoryTimes());
        System.out.println("gameTimes :"+liSi.getGameTimes());
        liSi.reset();
        System.out.println("after reset:");
        System.out.println("victoryTimes :"+liSi.getVictoryTimes());
        System.out.println("gameTimes :"+liSi.getGameTimes());
        System.out.println("\nGame.setFirstPlayer test");
        game.setFirstPlayer(wangWu); 
        game.run(5);
    }
} 
class Player{
    String name;
    double victoryTimes; 
    double gameTimes;
    Player(String name_){
        name = name_;
        victoryTimes = 0;
        gameTimes = 0;
    }
    String getName(){
        return name;
    }
    double getVictoryTimes(){
        return victoryTimes;
    }
    double getGameTimes(){
        return gameTimes;
    }
    void reset(){
        victoryTimes = 0;
        gameTimes = 0;
    }
    void recordGame(boolean isVictory){
        if(isVictory == true){
            victoryTimes++;
        }
        gameTimes++;
    }
    int chooseShape(){
        Random r = new Random();
        return r.nextInt(3);       
    }
    void showMetrics(){
        System.out.println(name + ":" + (victoryTimes/gameTimes));
    }
}
class Game{
    Player firstPlayer;
    Player secondPlayer;
    void setFirstPlayer(Player first){
        firstPlayer = first;
    }
    void setSecondPlayer(Player second){
        secondPlayer = second;
    }
    Player getFirstPlayer(){
        return firstPlayer;
    }
    Player getSecondPlayer(){
        return secondPlayer;
    }
    void run(int n){
        for(int i = 0;i < n;i++){
            int x1 = firstPlayer.chooseShape();
            int x2 = secondPlayer.chooseShape();
            if(x1 == 0){
                System.out.print(firstPlayer.getName()+":rock  ");
            }
            else if(x1 == 1){
                System.out.print(firstPlayer.getName()+":paper  ");
            }
            else if(x1 == 2){
                System.out.print(firstPlayer.getName()+":scissors  ");
            }
            if(x2 == 0){
                System.out.print(secondPlayer.getName()+":rock  ");
            }
            else if(x2 == 1){
                System.out.print(secondPlayer.getName()+":paper  ");
            }
            else if(x2 == 2){
                System.out.print(secondPlayer.getName()+":scissors  ");
            }
            if(x1 == x2){
                System.out.println("result:Draw");
                firstPlayer.recordGame(false);
                secondPlayer.recordGame(false);
            }
            if((x1-x2) == 1 || (x1-x2) == -2){
                System.out.println("result:"+firstPlayer.getName()+" wins");
                firstPlayer.recordGame(true);
                secondPlayer.recordGame(false);
            }
            if((x2-x1) == 1 || (x2-x1) == -2){
                System.out.println("result:"+secondPlayer.getName()+" wins");
                firstPlayer.recordGame(false);
                secondPlayer.recordGame(true);
            }  
        }
        firstPlayer.showMetrics();
        secondPlayer.showMetrics();
    }
}


