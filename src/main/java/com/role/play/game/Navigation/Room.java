package com.role.play.game.Navigation;

import com.role.play.game.Creature.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Room {

    private int vert;
    private int hor;
    private Room[] connections = new Room[4];
    private boolean monInRoom = false;
    private int monChance;
    private Monster roomMon;
    private String desc;

    public static int count = 0;
    public static ArrayList<Room> roomList = new ArrayList<>();

    public Room(String desc){
        this.desc = desc;
        monChance = 0;
        vert = 0; hor = 0;
        roomMon = null;
        Arrays.setAll(connections, i -> null);
        roomList.add(this);
    }

    public Room(String desc,Monster mon){
        this.desc = desc;
        monChance = 0;
        roomMon = mon;
        monInRoom = true;
        vert = 0; hor = 0;
        Arrays.setAll(connections, i -> null);
        roomList.add(this);
    }

    public Room(String about, int mons, int fromDir, Room from)
    {
        this.desc = about;
        monChance = mons;
        monInRoom = false;
        //generateMonster();


        if(fromDir == 0)
        {
            vert = (from.getVert()-1);
            hor = from.getHor();
        }
        if(fromDir == 1)
        {
            vert = (from.getVert()+1);
            hor = from.getHor();
        }

        if(fromDir == 2)
        {
            hor = (from.getHor()-1);
            vert = from.getVert();
        }
        if(fromDir == 3)
        {
            hor = (from.getHor()+1);
            vert = from.getVert();
        }
        connections[fromDir] = from;

        //Checks for neighbouring rooms
        for(int i=0; i < roomList.size(); i++)
        {
            Room temp = roomList.get(i); //temp room to be compared

            //check above
            if(temp.getHor() == hor && temp.getVert() == vert+1)
            {
                connections[0] = temp;
            }
            //Checks below
            if(temp.getHor() == hor && temp.getVert() == vert-1)
            {
                connections[1] = temp;
            }
            //checks east
            if(temp.getVert() == vert && temp.getHor() == hor+1)
            {
                connections[2] = temp;
            }
            //checks west
            if(temp.getVert() == vert && temp.getVert() == hor-1)
            {
                connections[3] = temp;
            }
        }

        roomList.add(this);

        //Prints out a list of all the rooms if in debug mode
        /*System.out.println("ROOMLISTINGS");
        for(int i=0; i < roomList.size(); i++)
        {
            System.out.println(roomList.get(i).getHor()+","+roomList.get(i).getVert());
        }
        System.out.println("~~~~~~~~~~~");*/

    }


    public Room[] getConnects()
    {
        return connections;
    }
    public String getDesc()
    {
        return desc;
    }
    public Monster getMonster()
    {
        return roomMon;
    }

    public int getVert()
    {
        return vert;
    }
    public int getHor()
    {
        return hor;
    }
    public boolean monInRoom()
    {
        return monInRoom;
    }
    public void setConnect(int to,Room roomTo)
    {
        connections[to] = roomTo;
    }

    public void addMonster(Monster mon){
        monInRoom = true;
        roomMon = mon;
    }
    public void killMonster(){
        monInRoom = false;
        roomMon = null;
        System.out.println("Monster is Killed");
    }

    public void generateMonster(){
        int ran = (int) (Math.random() * 100);
       // System.out.println("Monster Chance: "+ran+":"+monChance);
        if(!monInRoom){
            if(ran<=monChance){
                Monster mon = RoomHandling.randomizeMonsters();
                addMonster(mon);
            }
            else{
                roomMon = null;
            }
        }
    }
}

