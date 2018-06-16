package com.role.play.game.Creature;

public class Player extends Character {



    public Player(String pName, int[] stat){
        this.name = pName;
        this.experience = 0;
        this.concentration = stat[1];
        maxHP = setHP(concentration);
        curHP = maxHP;
        strength = stat[0];
        hit = stat[3];
        dexteriy = stat[2];
        defence = stat[4];

    }
    public Player(String pName, int[] stats, String load){
        name = pName;
        experience = stats[1];
        curHP = stats[2];
        maxHP = stats[3];
        strength = stats[4];
        concentration = stats[5];
        dexteriy = stats[6];
        hit = stats[7];
        defence = stats[8];
    }

    public Player(){
        System.out.println("Decoy Player");
    }

    public int hit(){
        if(experience<5)
            return (int)(Math.random()*100)+(2*hit);
        if (experience >=5 && experience<=10)
            return (int)(Math.random()*100)+(4*hit);

        return (int)(Math.random()*100)+(10*hit);
    }

    public int getDmg() {
        return strength;
    }

    public void increaseExp(int increaseExp){
        this.experience += increaseExp;
    }

    public int getExp() {
        return experience;
    }

    public String getName(){
        return name;
    }

    //Adds bonus HP based on con.
    public int setHP(int HP)
    {
        int total=0;
        total = HP + (int)(concentration/4);
        return total;
    }


    public int[] getStatLine(){
        int [] statline = new int[9];
        statline[1] = experience;
        statline[2] = curHP;
        statline[3] = maxHP;
        statline[4] = strength;
        statline[5] = dexteriy;
        statline[6] = concentration;
        statline[7] = hit;
        statline[8] = defence;

        return statline;
    }

    public int getDefence() {
        return defence;
    }

    public String toString(){
        return "Name: "+name+"\n HP: "+curHP+"/"+maxHP+"\n STRENTH: "+strength+
                "\n CONCENTRATION: "+concentration+"\n DEXTERITY: "+dexteriy+"\n HIT: "+hit+"\n DEFENCE: "+defence+"\n EXPERIENCE: "+experience;
    }
}
