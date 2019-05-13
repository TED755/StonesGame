/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author gekat
 */
public class Player {
    private String name;
    
    public Player(String _name){
        name = _name;
    }
    public void setName(String newName){
        name = newName;
    }
    
    public String getName(){
        return name;
    }
}
