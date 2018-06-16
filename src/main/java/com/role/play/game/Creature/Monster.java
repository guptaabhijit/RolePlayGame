package com.role.play.game.Creature;

public class Monster extends Character {

    private int attChance;

    public Monster(String mName, int HP, int mStr, int mDef, int mHit, int mCon, int mDex, int att)
    {
        name = mName;
        experience = 0;
        concentration = mCon;
        maxHP = setHP(HP);
        curHP = maxHP;
        strength = mStr;
        hit = mHit;
        dexteriy = mDex;
        defence = mDef;
        attChance = att;

    }

    //Adds bonus HP based on con.
    public int setHP(int HP)
    {
        int total=0;
        total = HP + (int)(concentration/4);
        return total;
    }


    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return name;
    }

    public int hit(){
        return hit;
    }
    public int getStr(){
        return strength;
    }
    public boolean willAttack(int check)
    {
        return (attChance >= check);
    }

    public boolean dying(){
        return (curHP <= (maxHP/4));
    }

    public boolean isDefending() {
        return defending;
    }
}
