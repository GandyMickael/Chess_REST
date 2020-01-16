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
public class Pion extends Piece{
    
    public Pion(String name, int position, String couleur){
        this.nom=name;
        this.position = position;
        this.couleur = couleur;
        this.type = "Pion";
        this.vie = true;
        this.deplacement.clear();
        this.deplacement.add(this.position+1);
        this.deplacement.add(this.position+2);
    }
    
    /**
     *
     * @param newPosition
     */
    @Override
    public void moveTo(int newPosition){
        this.position=newPosition;
        deplacement.clear();
        if(this.couleur.equals("Blanc")){
            deplacement.add(this.position+1);
            Partie.Board.forEach((piece) -> {
                if(piece.getCouleur()!=this.getCouleur()){
                    if(piece.position==this.position+11 || piece.position==this.position+9){
                        deplacement.add(piece.position);
                    }  
                }
            });
        }
        else{
            deplacement.add(this.position-1);
            Partie.Board.forEach((piece) -> {
                if(piece.getCouleur()!=this.getCouleur()){
                    if(piece.position==this.position-11 || piece.position==this.position-9){
                        deplacement.add(piece.position);
                    }  
                }
            });
        }
        
    }
}
