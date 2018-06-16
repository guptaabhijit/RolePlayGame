package com.role.play.game.Combat;

import com.role.play.game.Creature.Monster;
import com.role.play.game.Main;
import com.role.play.game.Navigation.Movement;
import com.role.play.game.Navigation.Room;

import java.util.Scanner;

public class Combat {

    public static void comMenu(Room comRoom) {


        Scanner key = new Scanner(System.in);
        int input = 0;
        Monster mon = comRoom.getMonster();
        do {
            try {
                System.out.println("There's a " + mon.getName() + "(HP:" + mon.printHP() + ") in the room! What will you do?");
                System.out.println("1) Attack the monster!");
                System.out.println("2) Defend against the monter");
                System.out.println("3) RUN AWAY!");

                input = key.nextInt();
            }catch(Exception e){
                System.out.println("Please enter valid option");
            }
            key.nextLine();
        }while(!(input>=1 && input<=3));
        if(input == 1){
            attack(mon);
            if(mon.isAlive()){
                monTurn(mon);
            }
        }
        if(input == 2){

            System.out.println("Current DEF: "+ Main.player.getDefence());
            Main.player.defend(); //Increase Def
            System.out.println("DEF after defending:"+ Main.player.getDefence());
            System.out.println("MONSTERS DO STUFF"); //Get attacked
            monTurn(mon);
            Main.player.defend(); //Lower Def again.
            System.out.println("DEF is lowered after monster's turn: "+ Main.player.getDefence());
        }
        if(input == 3){
            System.out.println("You run away!!!!");
            Movement.enterRoom(comRoom);
        }


        if(!mon.isAlive()){
            System.out.println("You killed the monster!");
            //Increase player's experience.
            Main.player.increaseExp(1);
            comRoom.killMonster();
        }
        if (mon.isAlive()){
            comMenu(comRoom);
        }
    }

    private static void monTurn(Monster mon) {
        if(mon.isAlive()){
            int whatToDo = (int)(Math.random()*100);
            if(mon.isDefending()) // STOP defending if defending;
                mon.defend();

            if(mon.willAttack(whatToDo) && !mon.dying())
                monAttack(mon);
            else{
                mon.defend();
                System.out.println("----- DEFENSE MODE by Monster----");
            }


        }
    }

    private static void monAttack(Monster mon) {

        System.out.println("The "+mon.getName()+" attacks you!");
        int mhit = mon.hit();
        int pDodge = Main.player.dodge();
        if(mhit >= pDodge)
        {
            int dmg = Main.player.isHit(mon.getStr());
            System.out.println("It hits for "+dmg+" damage!");
            System.out.println(Main.player.getName()+" HP: "+ Main.player.printHP());
        }
        else
        {
            System.out.println("... but it misses!");
        }
    }

    private static void attack(Monster mon) {

        System.out.println("You attack the "+mon.getName()+"!");
        int pHit = Main.player.hit();
        int mDodge = mon.dodge();

        System.out.println("Player hit : "+pHit+" vs Dodge: "+mDodge);

        if(pHit >= mDodge){
            int damg = 0;
            if(pHit >= 2*mDodge){
                damg = mon.isHit(2* Main.player.getDmg() + pHit);
                System.out.println("-----CRICITCAL-------");
            }
            else{
                damg = mon.isHit(Main.player.getDmg());
            }
            System.out.println("You hit Monster for "+damg + " points of damage!");
        }else{
            System.out.println("You miss!! ");
        }
    }
}
