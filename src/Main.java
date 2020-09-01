package life;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Universe uni = new Universe(size);
        GameDisplay game = new GameDisplay(uni);
        game.run();
        while (true){
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
                Thread.sleep(game.getSpeed());
            }
            catch (IOException | InterruptedException e) {break;}
            while (!game.isStarted()){
                Thread.sleep(2000);
            }

            uni.generate();
            game.update(uni);

        }
    }
}

