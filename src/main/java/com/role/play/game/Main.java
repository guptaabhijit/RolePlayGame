package com.role.play.game;

import com.role.play.game.Creature.Player;
import com.role.play.game.Launcher.MainMenu;
import com.role.play.game.Navigation.RoomHandling;

public class Main {
    public static Player player;

    public static void main(String[] args){
        System.out.println("Starting game ...");
        player = MainMenu.Menu();
        RoomHandling.startGame();
        System.out.println("END OF THE GAME SO FAR");
    }
}
