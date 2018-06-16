package com.role.play.game.Data;

import com.role.play.game.Creature.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileOperations {

    public static void Save(Player player){

        String strFile = player.getName().toLowerCase() + ".stats";
        String newLine = System.getProperty("line.separator");
        int[] stats = player.getStatLine();
        try{
            FileWriter fileWriter = new FileWriter(strFile);
            fileWriter.write(player.getName()+newLine);
            for(int i=1;i<stats.length;i++){
                fileWriter.write(stats[i]+newLine);
            }
            fileWriter.close();
            System.out.println("File Saved.");
        }
        catch(Exception e){
            System.err.println("Error : "+e.getMessage());
        }
    }
    public static Player Load(){
        Scanner input = new Scanner(System.in);
        Player player;
        String strFile,charName;
        int[] stats = new int[9];
        String cmd = "";
        System.out.println("Enter your old character's name:");
        strFile = input.nextLine();
        cmd = strFile.toLowerCase();
        strFile = strFile.toLowerCase() + ".stats";

        File file = new File(strFile);
        if (file.exists()){
            System.out.println("Loading...");


            try{
                Scanner Read = new Scanner(file);
                int i;
                charName = Read.nextLine();
                for(i=1; i < 9; i++)
                {
                    stats[i]= Read.nextInt();
                }
                player = new Player(charName, stats, "Load");
                System.out.println(player.toString());
                System.out.println("File loaded Successfully.");
                System.out.println("\n");
                return player;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

        }
        else if(cmd.equals(".."))
        {
            System.out.println("Returning to menu.");
            return null;
          //  mainMenu.Menu();
        }
        else
        {
            System.out.println("That character does not exist");
            Load();
        }
        return new Player();
    }
}
