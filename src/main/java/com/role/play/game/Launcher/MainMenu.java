package com.role.play.game.Launcher;

import com.role.play.game.Creature.Player;
import com.role.play.game.Data.FileOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainMenu {



    public static void help(){
        System.out.println("------------------------------HELP--------------------------------");
        System.out.println("Commands: go [n,s,e,w] <- go in the direction");
        System.out.println("go [north,south,east,west] <- Same as above!");
        System.out.println("[n,e,s,w,north,south,east,west] <- Look in the direction specified.");
        System.out.println("show <- show the player's details");
        System.out.println("exit <- Exit and will save the Game");
        System.out.println("------------------------------END---------------------------------");
    }

    public static Player Menu() {
        System.out.println("===============================================");
        System.out.println("        Welcome to the Role-Play-Game:");
        System.out.println("===============================================");
        System.out.println("                    by Abhijit                 ");

        while(true){
            Player player = getPlayer();

            if(player != null){
                return player;
            }
        }
    }

    public static Player getPlayer() {
        int input;

        Player player=null;
        boolean keep=false;
        Scanner inMenu = new Scanner(System.in);
        try {

            System.out.println("MAIN MENU");
            System.out.println("1) New Character");
            System.out.println("2) Load Character");

            input = inMenu.nextInt();

            if (input == 1) {

                while (!keep) //Loops until they say yes
                {
                    player = createChar();
                    System.out.println("Here is your character: ");
                    System.out.println(player.toString()); //Displays the character sheet
                    System.out.println("Keep this character?");
                    System.out.println("1) Yes");
                    System.out.println("2) No");
                    input = 0;
                    input = inMenu.nextInt();
                    if (input == 1) {
                        keep = true;//Save
                        FileOperations.Save(player);
                        return player;
                    }

                }
            }
            if (input == 2) {
                player = FileOperations.Load();
                return player;
            }
        }
        catch(Exception e){
            System.out.println("Please enter valid option");
            return null;
        }
        return null;
    }

    private static Player createChar() {

        String name;
        int[] stats = genStats();
        Scanner in = new Scanner(System.in);
        Player player = null;

        System.out.println("\n");
        System.out.println("Enter your character name:");
        name = in.nextLine();
        if (name.equals("..")){
            System.out.println("Returning to Menu.");
            MainMenu.Menu();
        }
        else{
            player = new Player(name,stats);
        }
        return player;
    }

    public static int[] genStats(){
        int[] stats = new int[5];
        Arrays.setAll(stats,i -> (int)(Math.random()*18)+1);
        return stats;
    }
}
