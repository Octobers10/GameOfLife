package life;
import java.util.*;
import java.lang.*;

public class Universe {
    int [][] states;
    final int size;
    int generation=1;
    int alive=0;
    public Universe (int size){
        this.size = size;
        states = new int[size][size];
        Random rand = new Random();
        for (int r = 0; r < size; ++r) {
            for (int c = 0; c < size; ++c) {
                this.states[r][c] = rand.nextInt(2);
                this.alive +=this.states[r][c];
            }
        }
    }

    private int countNeighbor(int x, int y){
        int count = 0;
        for (int r = -1; r <= 1; ++r){
            for (int c = -1; c <= 1; ++c){
                if ((r==0) && (c==0)){
                    continue;
                }
                count += states[(x+r+size)%size][(y+c+size)%size];
            }
        }
        return count;
    }
    public void generate(){
        this.alive = 0;
        int[][] nextGen = new int[size][size];
        for (int r = 0; r < size; ++r) {
            for (int c = 0; c < size; ++c) {
                int numNeigh = countNeighbor(r, c);
                if ((numNeigh > 3) || (numNeigh < 2)) {
                    nextGen[r][c] = 0;
                } else if (numNeigh == 3){
                    nextGen[r][c] = 1;
                } else {
                    nextGen[r][c] = states[r][c];
                }
                this.alive += nextGen[r][c];
            }
        }
            states = nextGen;
            ++this.generation;
    }


    public void printUniverse(){
        System.out.printf("Generation #%d\n", generation);
        System.out.printf("Alive: %d\n", alive);
        for (int r = 0; r < this.size; ++r) {
            for (int c = 0; c < this.size; ++c) {
                if (this.states[r][c] == 1){
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}