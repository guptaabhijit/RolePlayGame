package com.role.play.game.Navigation;

import com.role.play.game.Combat.Combat;
import com.role.play.game.Data.FileOperations;
import com.role.play.game.Launcher.MainMenu;
import com.role.play.game.Main;

import java.util.Scanner;

public class Movement {

    public static void enterRoom(Room startRoom) {

        Scanner key = new Scanner(System.in);
        if(!startRoom.monInRoom()){
            startRoom.generateMonster();
        }
        if(!startRoom.monInRoom())
            System.out.println("Luckily there are no monsters here\n");
        else
            System.out.println("There's a "+ startRoom.getMonster()+" in the room!\n");

        System.out.println("What will you do?");
        String input;
        input = key.nextLine();
        input = input.toLowerCase();

        switch (input){
            case "help":
            case "h":
                MainMenu.help();
                break;

            case "exit":
                FileOperations.Save(Main.player);
                System.exit(0);
                break;

            case "show":
                System.out.println(Main.player.toString());
                break;

            case "fight":
            case "f":
                if(startRoom.monInRoom())
                    Combat.comMenu(startRoom);
                break;

            case "north":
            case "n":
                watchRoomView(startRoom,"north",0,100,1);
                break;

            case "south":
            case "s":
                watchRoomView(startRoom,"south",1,0,0);
                break;

            case "east":
            case "e":
                watchRoomView(startRoom,"east",2,100,3);
                break;

            case "west":
            case "w":
                watchRoomView(startRoom,"west",3,0,2);
                break;

            case "go north":
            case "go n":
                move(0,startRoom);
                break;


            case "go south":
            case "go s":
                move(1,startRoom);
                break;

            case "go east":
            case "go e":
                move(2,startRoom);
                break;

            case "go west":
            case "go w":
                move(3,startRoom);
                break;

            default:
                enterRoom(startRoom);
                break;

        }
        enterRoom(startRoom);
    }

    private static void watchRoomView(Room room,String dir,int roomConnectionIndex,int monchance,int fromDir){

        //Since you look it creates a new room (Boring)
        try
        {
            System.out.println("To the "+ dir +" you see "+ room.getConnects()[roomConnectionIndex].getDesc()+" at:("+room.getConnects()[roomConnectionIndex].getHor()+","+room.getConnects()[roomConnectionIndex].getVert()+")"); //Then it tells you what is seen
            //System.out.println("To the "+ dir +" you see " + room.getConnects()[roomConnectionIndex].getDesc());
        }
        catch(NullPointerException npe){

            System.out.println("Inside NPE");

            Room newRoom = new Room(dir+" Room",monchance,fromDir,room);
            room.setConnect(roomConnectionIndex, newRoom); //It connects this new room to the north of the current one

            System.out.println("To the " + dir +" you see "+newRoom.getDesc()+" at:("+room.getConnects()[roomConnectionIndex].getHor()+","+room.getConnects()[roomConnectionIndex].getVert()+")"); //Then it tells you what is seen
          //  System.out.println("To the "+ dir +" you see " + newRoom.getDesc());
        }
    }
    private static void move(int to, Room curRoom) {

        if(to == 0){
            Room[] nRoom = curRoom.getConnects();
            System.out.println("You went North");

            try //checks if a room to the north exists. If not..
            {
                nRoom[0].equals(null);
            }
            catch(NullPointerException npe)
            {
                ///... It'll make a new room for you and auto-connect them.
                nRoom[0] = RoomHandling.generateRoom(1,curRoom);
            }
            enterRoom(nRoom[0]); //Then you enter this new room!
        }
        if(to==1)
        {
            Room[] sRoom = curRoom.getConnects();
            System.out.println("You went South");
            try{
                sRoom[1].equals(null);
            }catch(NullPointerException npe)
            {
                sRoom[1] = RoomHandling.generateRoom(0, curRoom);
            }
            enterRoom(sRoom[1]);
        }
        if(to==2)
        {
            Room[] eRoom = curRoom.getConnects();
            System.out.println("You went East");
            try
            {
                eRoom[2].equals(null);
            }catch(NullPointerException npe)
            {

                eRoom[2] = RoomHandling.generateRoom(3, curRoom);
            }
            enterRoom(eRoom[2]);
        }
        if(to==3)
        {
            System.out.println("You went West");
            Room[] wRoom = curRoom.getConnects();
            try
            {
                wRoom[3].equals(null);
            }catch(NullPointerException npe)
            {
                wRoom[3] = RoomHandling.generateRoom(2, curRoom);
            }
            enterRoom(wRoom[3]);
        }
    }
}
