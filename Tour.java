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
public class Tour extends Piece{
    
    public Tour(String name, int position, String couleur){
        this.nom=name;
        this.position = position;
        this.couleur = couleur;
        this.type = "Tour";
        this.deplacement.clear();
        this.vie = true;
        for(int i=1; i<=8; i++){
            this.deplacement.add(this.position+i);
            this.deplacement.add(this.position+i*10);
            this.deplacement.add(this.position-i);
            this.deplacement.add(this.position-i*10);
        }
    }
    
    /**
     *
     * @param newPosition
     */
    @Override
    public void moveTo(int newPosition){
        this.position=newPosition;
        deplacement.clear();
        for(int i=1; i<=8; i++){
            this.deplacement.add(this.position+i);
            this.deplacement.add(this.position+i*10);
            this.deplacement.add(this.position-i);
            this.deplacement.add(this.position-i*10);
        }
    }
}
