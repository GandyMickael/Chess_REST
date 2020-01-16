/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echec;

import java.util.ArrayList;

/**
 *
 * @author Mickael
 */
public final class Reine extends Piece{
    
    public Reine(String name, int position, String couleur){
        this.nom=name;
        this.position = position;
        this.couleur = couleur;
        this.type = "Reine";
        this.vie = true;
        this.calculDeplacement();
    }
    
    /**
     *
     * @param newPosition
     */
    @Override
    public void moveTo(int newPosition){
        this.position=newPosition;
    }
    
    @Override
    public ArrayList<Integer> calculDeplacement(){
        this.deplacement.clear();
        for(int i=1; i<=8; i++){
            this.deplacement.add(this.position+i);
            this.deplacement.add(this.position-i);
            this.deplacement.add(this.position+i*10);
            this.deplacement.add(this.position-i*10);
            this.deplacement.add(this.position+i*11);
            this.deplacement.add(this.position-i*11);
            this.deplacement.add(this.position+i*9);
            this.deplacement.add(this.position-i*9);
        }
        return this.deplacement;
    } 
}
