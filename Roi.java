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
public final class Roi extends Piece{
    
    public Roi(String name, int position, String couleur){
        this.nom=name;
        this.position = position;
        this.couleur = couleur;
        this.type = "Roi";
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
        this.deplacement.add(this.position+1);
        this.deplacement.add(this.position-1);
        this.deplacement.add(this.position+10);
        this.deplacement.add(this.position-10);
        this.deplacement.add(this.position+11);
        this.deplacement.add(this.position-11);
        this.deplacement.add(this.position+9);
        this.deplacement.add(this.position-9);
        return this.deplacement;
    } 
}
