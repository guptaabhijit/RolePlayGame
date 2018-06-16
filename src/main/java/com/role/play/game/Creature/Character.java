package com.role.play.game.Creature;

public class Character {

    protected String name;
    protected int curHP;
    protected int maxHP;
    protected int strength;
    protected int hit;
    protected int concentration;
    protected int dexteriy;
    protected int defence;
    protected int experience;
    protected boolean defending = false;
    protected boolean isAlive = true;

    public String printHP(){
        return (curHP+"/"+maxHP);
    }

    public boolean isAlive(){
        return isAlive;
    }

    public int dodge(){
        return (int)(Math.random()*100) + (int)(1.5*dexteriy);
    }

    public void defend(){
        if(defending){
            defending = false;
            defence -= (int)(concentration*1.5);

        }else{
            defending = true;
            defence += (int)(concentration*1.5);
        }
    }
    public int  isHit(int damage){
        System.out.println("Damage: "+damage);
        System.out.println("DEFENCE: "+defence);
        int dif = (damage - defence);
        if(dif <= 0)
            dif = 0;
        else{
            curHP = curHP - dif;
            if (curHP < 0) curHP = 0;
        }

        if(curHP <= 0) isAlive = false;
        return dif;
    }
}
