/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echec;

/**
 *
 * @author Mickael
 */
public class Fou extends Piece{
    
    public Fou(String name, int position, String couleur){
        this.nom=name;
        this.position = position;
        this.couleur = couleur;
        this.type = "Fou";
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
        this.calculDeplacement();
    }
    
    public void calculDeplacement(){
        this.deplacement.clear();
        for(int i=1; i<=8; i++){
            this.deplacement.add(this.position+i*11);
            this.deplacement.add(this.position-i*11);
            this.deplacement.add(this.position+i*9);
            this.deplacement.add(this.position-i*9);
        }
    }   
}
