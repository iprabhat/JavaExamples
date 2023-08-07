package com.demo.hackerrank;

import java.util.Arrays;
import java.util.Comparator;

class Player{
    String name;
    int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }
}

class Checker implements Comparator<Player>{
    @Override
    public int compare(Player o1, Player o2) {
        if(o1.score > o2.score) {return -1;}
        else if(o1.score < o2.score) {return 1;}
        else {
            return o1.name.compareTo(o2.name);
        }
    }
}
public class JavaComparator {

    public static void main(String[] args) {
        Player[] player = new Player[5];
        player[0] = new Player("amy", 100);
        player[1] = new Player("david", 100);
        player[2] = new Player("heraldo", 50);
        player[3] = new Player("aakansha", 75);
        player[4] = new Player("aleksa", 150);
        Checker checker = new Checker();
        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}
