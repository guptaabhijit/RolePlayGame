package com.role.play.game.Navigation;

import com.role.play.game.Creature.Monster;
import com.role.play.game.Launcher.MainMenu;
import com.role.play.game.Main;

import static com.role.play.game.Launcher.MainMenu.help;

public class RoomHandling {

    public static void startGame(){

        String desc = "You are about to appear in front of dungeon.";
        Room start = new Room(desc);
        MainMenu.help();
        Movement.enterRoom(start);

    }
    public static Room generateRoom(int from, Room fromR){
        Room.count++;
        Room theRoom = new Room("Navigation Room",randomNum(50),from,fromR);
        return theRoom;
    }

    public static Monster randomizeMonsters(){
        int which = (int) (Math.random()*12+1);
        int playerExperience = Main.player.getExp();
        System.out.println("which: "+which);
        if(which >= 10 || playerExperience > 4)
            return new Monster("Orc",120,randomNum(50),randomNum(50),randomNum(50),randomNum(20),randomNum(20),50);
        if((which  == 9 && which < 10)  || playerExperience > 2)
            return new Monster("Dragon",60,randomNum(20),randomNum(20),randomNum(20),randomNum(11),randomNum(8),30);

        return new Monster("Chimera",20,randomNum(10),randomNum(10),randomNum(10),randomNum(5),randomNum(11),randomNum(30));
    }
    public static int randomNum(int max)
    {
        int num = (int)(Math.random()*max);
        return num;
    }

}
