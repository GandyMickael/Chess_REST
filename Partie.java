/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echec;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;

/**
 * REST Web Service
 *
 * @author Mickael
 */
@Path("echec")
public class Partie {
    static ArrayList<Piece> Board;
    String couleur_tour, status="";
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public  Partie() {
    }
    
    public String create(){
        if(this.status.equals("")){
            this.status="En attente...";
            String msg = this.status + "\nVoici votre couleur : Blanc.";
            return msg;
        }
        else{
            return "Une autre partie est en cours. "
                    + "\nVeuillez la rejoindre ou attendre qu'elle se termine avant d'en lancer une nouvelle";
        }
    }
    
    public String join(){
        if(this.status.equals("En attente...")){
            this.status="Prêt";
            return "Vous venez de rejoindre la partie, vote couleur sera : Noir";
        }
        else{
            return this.create();
        }
    }
    
    public String getStatus(){
        return this.status;
    }

    /**
     * Retrieves representation of an instance of Partie.GenericResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Object[] start() {
        //TODO return proper representation object
        Board = new ArrayList<Piece>();
        this.couleur_tour="Blanc";
        this.status = "En jeu.";

        //CREATION DES PIECES
        //BLANC
        Pion pionb1 = new Pion("PB1", 12, "Blanc");
        Pion pionb2 = new Pion("PB2", 22, "Blanc");
        Pion pionb3 = new Pion("PB3", 32, "Blanc");
        Pion pionb4 = new Pion("PB4", 42, "Blanc");
        Pion pionb5 = new Pion("PB5", 52, "Blanc");
        Pion pionb6 = new Pion("PB6", 62, "Blanc");
        Pion pionb7 = new Pion("PB7", 72, "Blanc");
        Pion pionb8 = new Pion("PB8", 82, "Blanc");
        
        Tour tourb1 = new Tour("TB1",11,"Blanc");
        Cavalier cavalierb1 = new Cavalier("CB1",21,"Blanc");
        Fou foub1 = new Fou("FB1",31,"Blanc");
        Reine reineb = new Reine("REB",41,"Blanc");
        Roi roib = new Roi("RB",51,"Blanc");
        Fou foub2 = new Fou("FB2",61,"Blanc");
        Cavalier cavalierb2 = new Cavalier("CB2",71,"Blanc");
        Tour tourb2 = new Tour("TB2",81,"Blanc");
        
        //NOIR
        Pion pionn1 = new Pion("PN1", 17, "Noir");
        Pion pionn2 = new Pion("PN2", 27, "Noir");
        Pion pionn3 = new Pion("PN3", 37, "Noir");
        Pion pionn4 = new Pion("PN4", 47, "Noir");
        Pion pionn5 = new Pion("PN5", 57, "Noir");
        Pion pionn6 = new Pion("PN6", 67, "Noir");
        Pion pionn7 = new Pion("PN7", 77, "Noir");
        Pion pionn8 = new Pion("PN8", 87, "Noir");
        
        Tour tourn1 = new Tour("TN1",18,"Noir");
        Cavalier cavaliern1 = new Cavalier("CN1",28,"Noir");
        Fou foun1 = new Fou("FN1",38,"Noir");
        Reine reinen = new Reine("REN",48,"Noir");
        Roi roin = new Roi("RN",58,"Noir");
        Fou foun2 = new Fou("FN2",68,"Noir");
        Cavalier cavaliern2 = new Cavalier("CN2",78,"Noir");
        Tour tourn2 = new Tour("TN2",88,"Noir");
        
        //AJOUT DES PIECES AU BOARD
        this.Board.add(pionb1);
        this.Board.add(pionb2);
        this.Board.add(pionb3);
        this.Board.add(pionb4);
        this.Board.add(pionb5);
        this.Board.add(pionb6);
        this.Board.add(pionb7);
        this.Board.add(pionb8);
        
        this.Board.add(tourb1);
        this.Board.add(cavalierb1);
        this.Board.add(foub1);
        this.Board.add(reineb);
        this.Board.add(roib);
        this.Board.add(foub2);
        this.Board.add(cavalierb2);
        this.Board.add(tourb2);
        
        this.Board.add(pionn1);
        this.Board.add(pionn2);
        this.Board.add(pionn3);
        this.Board.add(pionn4);
        this.Board.add(pionn5);
        this.Board.add(pionn6);
        this.Board.add(pionn7);
        this.Board.add(pionn8);
        
        this.Board.add(tourn1);
        this.Board.add(cavaliern1);
        this.Board.add(foun1);
        this.Board.add(reinen);
        this.Board.add(roin);
        this.Board.add(foun2);
        this.Board.add(cavaliern2);
        this.Board.add(tourn2);
        
        Object[] table = new Object[2];
        table[1] = this.Board;
        table[2] = "Blanc";
        return table;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)

    public ArrayList<Piece> getBoard() {
        return this.Board;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Object[] move_piece(String pieceName, int newPosition) {
        boolean destination_vide = true;
        Object[] table = new Object[2];
        
        for(Piece piece : this.Board){
            if(piece.getName().equals(pieceName) && piece.getDeplacement().contains(newPosition)){ //On cherche notre piece et on verifie si la position selectionnée est une position possible
                if(check_deplacement(piece,newPosition)){  //On vérifie qu'il n'y a aucune pièce sur notre route
                    for(Piece piece_mort : this.Board){ 
                        if(piece_mort.getPosition()==newPosition ){ //On vérifie si aucune des autres pièce n'est sur notre destination
                            destination_vide=false; //Si oui, on enregistre que la destination n'est pas vide
                            if(!piece_mort.getCouleur().equals(piece.getCouleur())){ //On vérifie la couleur de la pièce
                                piece_mort.setVie(false); //Si != on la sort du jeu
                                this.Board.remove(piece_mort);  
                                piece.moveTo(newPosition);
                                table[1] = this.Board;
                                table[2] = "200 - Déplacement réussi";
                            }
                            else{ //Si == on ne peut pas se déplacer
                                table[1] = this.Board;
                                table[2] = "400 - Échec du déplacement";
                            }
                        }
                    }
                    if(destination_vide==true){ //On vérifie si on a trouver une pièce sur la destination finale
                        piece.moveTo(newPosition); //Si non, on déplace la pièce
                        table[1] = this.Board;
                        table[2] = "200 - Déplacement réussi";
                    }     
                }
                else{ //S'il y a une pièce sur notre chemin, on ne bouge pas
                    table[1] = this.Board;
                    table[2] = "400 - Échec du déplacement";
                }
            }
            else{ //Si le déplacement choisie n'est pas un déplacement possible, on ne bouge pas
                table[1] = this.Board;
                table[2] = "400 - Échec du déplacement";
            }
        } 
        

        return table;
    }
    
    public boolean check_deplacement(Piece piece, int newpos){
        boolean verif_pion=false;
        boolean check = true; //Signifie qu'il n'y a rien sur le chemin 
        int position = piece.getPosition();
        switch(piece.getType()) {
            case "Pion":
                // code block
                if(piece.getCouleur().equals("Blanc")){
                    //+1
                    
                    for(Piece piece_path : this.Board){
                        if(piece_path.getPosition()==position+1){
                           check=false;
                        }
                    }
                }
                else{
                    //-1
                    for(Piece piece_path : this.Board){
                        if(piece_path.getPosition()==position-1){
                           check=false;
                        }
                    }
                }
            break;
            case "Tour":
                // code block
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i*10 && position+i*10!=newpos){
                            verif_pion=true;
                        }
                        if(position+i*10==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i && position+i!=newpos){
                            verif_pion=true;
                        }
                        if(position+i==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i*10 && position-i*10!=newpos){
                            verif_pion=true;
                        }
                        if(position-i*10==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i && position-i!=newpos){
                            verif_pion=true;
                        }
                        if(position-i==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
            break;
            case "Fou":
                // verification de la diagonale haut-droite
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i*11 && position+i*11!=newpos){
                            verif_pion=true;
                        }
                        if(position+i*11==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                // verification de la diagonale haut-gauche
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i*9 && position+i*9!=newpos){ //Si la pièce est sur le chemin
                            verif_pion=true; //On notifie qu'elle existe
                        }
                        if(position+i*9==newpos && verif_pion==true){ //Si notre destination est bien sur ce chemin et qu'une pièce a été notifiée
                            check=false; //On ne peut aller à notre destination
                        }
                    }
                }
                // verification de la diagonale bas-gauche
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i*11 && position-i*11!=newpos){
                             verif_pion=true;
                        }
                        if(position-i*11==newpos && verif_pion==true){ 
                            check=false;
                        }
                    }
                }
                // verification de la diagonale bas-droite
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i*9 && position-i*9!=newpos){
                             verif_pion=true;
                        }
                        if(position-i*9==newpos && verif_pion==true){ 
                            check=false;
                        }
                    }
                }
                
            break;
            case "Reine":
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i*10 && position+i*10!=newpos){
                            verif_pion=true;
                        }
                        if(position+i*10==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i && position+i!=newpos){
                            verif_pion=true;
                        }
                        if(position+i==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i*10 && position-i*10!=newpos){
                            verif_pion=true;
                        }
                        if(position-i*10==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i && position-i!=newpos){
                            verif_pion=true;
                        }
                        if(position-i==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i*11 && position+i*11!=newpos){
                            verif_pion=true;
                        }
                        if(position+i*11==newpos && verif_pion==true){
                            check=false;
                        }
                    }
                }
                // verification de la diagonale haut-gauche
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position+i*9 && position+i*9!=newpos){ //Si la pièce est sur le chemin
                            verif_pion=true; //On notifie qu'elle existe
                        }
                        if(position+i*9==newpos && verif_pion==true){ //Si notre destination est bien sur ce chemin et qu'une pièce a été notifiée
                            check=false; //On ne peut aller à notre destination
                        }
                    }
                }
                // verification de la diagonale bas-gauche
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i*11 && position-i*11!=newpos){
                            check=false;
                        }
                        if(position-i*11==newpos && verif_pion==true){ 
                            check=false;
                        }
                    }
                }
                // verification de la diagonale bas-droite
                verif_pion=false;
                for(Piece piece_path : this.Board){
                    for(int i=0;i<=7;i++){
                        if(piece_path.getPosition()==position-i*9 && position-i*9!=newpos){
                            check=false;
                        }
                        if(position-i*9==newpos && verif_pion==true){ 
                            check=false;
                        }
                    }
                }
            break;
            
            default:
                // code block
        }

        return check;
    }
}
    