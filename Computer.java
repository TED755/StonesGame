/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.Random;

/**
 *
 * @author gekat
 */
public class Computer {
    private String name;
    public Computer(String name){
        this.name = name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public String getName(){
        return name;
    }
    
    public int takeLastStoneWinStrategy(int stone_number){
        Random rnd = new Random(System.currentTimeMillis());
        if(stone_number % 5 == 0)
            return 1 + rnd.nextInt(4);
        for(int i = 1; i < 5; i++)
            if((stone_number - i) % 5 == 0)
                return i;
        return 0;
    }
    
    public int leaveLastStoneWinStrategy(int stone_number){
        Random rnd = new Random(System.currentTimeMillis());
        if(stone_number % 5 == 1)
            return 1 + rnd.nextInt(4);
        for(int i = 1; i < 5; i++)
            if((stone_number - i) % 5 == 1)
                return i;
        return 0;
    }
    
}
