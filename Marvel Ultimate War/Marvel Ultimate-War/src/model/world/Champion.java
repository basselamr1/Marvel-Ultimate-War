package model.world;

import java.awt.Point;
import java.util.ArrayList;

import model.abilities.Ability;
import model.effects.Effect;

public class Champion{
    private String name;
    private int maxHP;
    private int currentHP;
    private int mana;
    private int maxActionPointsPerTurn;
    private int currentActionPoints;
    private int attackRange;
    private int attackDamage;
    private int speed;
    private ArrayList<Ability> abilities;
    private ArrayList<Effect> appliedEffects;
    private Condition condition;
    private Point location;
    public Champion(String name, int maxHP, int mana, int maxActions, int speed, int attackRange,
    int attackDamage){
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.mana = mana;
        this.maxActionPointsPerTurn = maxActions;
        this.currentActionPoints = maxActions;
        this.speed = speed;
        this.attackRange = attackRange;
        this.attackDamage = attackDamage;
        this.abilities = new ArrayList<Ability>();
        this.appliedEffects = new ArrayList<Effect>();
        this.condition = Condition.ACTIVE;
    }
    public String getName(){
        return name;
    }
    public int getMaxHP(){
        return maxHP;
    }
    public int getCurrentHP(){
        return currentHP;
    }
    public void setCurrentHP(int hp){
        if(hp < 0){
            currentHP = 0;
        }
        else if(hp > maxHP){
            currentHP = maxHP;
        }
        else{
            currentHP = hp;
        }
    }
    public int getMana(){
        return mana;
    }
    public void setMana(int mana){
        this.mana = mana;
    }
    public int getMaxActionPointsPerTurn(){
        return maxActionPointsPerTurn;
    }
    public void setMaxActionPointsPerTurn(int maxActions){
        this.maxActionPointsPerTurn = maxActions;
    }
    public int getCurrentActionPoints(){
        return currentActionPoints;
    }
    public void setCurrentActionPoints(int currentActions){
        if(currentActions>maxActionPointsPerTurn){
        	currentActionPoints=maxActionPointsPerTurn;
        }
        else if(currentActions<0){
            currentActionPoints = 0;
        }
        else{
            this.currentActionPoints = currentActions;
        }
    }
    public int getAttackRange(){
        return attackRange;
    }
    public int getAttackDamage(){
        return attackDamage;
    }
    public void setAttackDamage(int attackDamage){
        this.attackDamage = attackDamage;
    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        if(speed < 0){
            this.speed = 0;
        }
        else{
        this.speed = speed;
        }
    }
    public ArrayList<Ability> getAbilities(){
        return abilities;
    }
    public ArrayList<Effect> getAppliedEffects(){
        return appliedEffects;
    }
    public Condition getCondition(){
        return condition;
    }
    public void setCondition(Condition condition){
        this.condition = condition;
    }
    public Point getLocation(){
        return location;
    }
    public void setLocation(Point location){
        this.location = location;
    }
	
	

}