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
public final class Cavalier extends Piece{
    
    public Cavalier(String name, int position, String couleur){
        this.nom=name;
        this.position = position;
        this.couleur = couleur;
        this.type = "Cavalier";
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
        this.deplacement.add(this.position+7);
        this.deplacement.add(this.position-7);
        this.deplacement.add(this.position+13);
        this.deplacement.add(this.position-13);
        this.deplacement.add(this.position+29);
        this.deplacement.add(this.position-29);
        this.deplacement.add(this.position+31);
        this.deplacement.add(this.position-31);
        return this.deplacement;
    }
}
