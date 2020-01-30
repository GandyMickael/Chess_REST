/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echec;

import java.util.ArrayList;
import java.util.Arrays;
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
    static ArrayList<Piece> Board = new ArrayList<Piece>();
    static String couleur_tour, status="";
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public  Partie() {
    }
    
    @PUT
    @Path("create")
    //Distribue la première couleur au joueur qui l'active. Met la partie en attente.
    @Produces(MediaType.TEXT_PLAIN)
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
    
    @PUT
    @Path("join")
    //Si un joueur rejoins une partie déjà créee, le status devient prêt et il attribue la dernière couleur.
    @Produces(MediaType.TEXT_PLAIN)
    public String join(){
        if(this.status.equals("En attente...")){
            this.status="Prêt";
            return "Vous venez de rejoindre la partie, vote couleur sera : Noir";
        }
        else{
            return this.create();
        }
    }
    
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    //Retourne le status de la partie
    public String getStatus(){
        return this.status;
    }
    
    @GET
    @Path("tour")
    @Produces(MediaType.TEXT_PLAIN)
    //Retourne le status de la partie
    public String getTour(){
        return this.couleur_tour;
    }

    /**
     * Retrieves representation of an instance of Partie.GenericResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //Crée la partie en ajoutant les pièces au plateau puis le retourne
    public String start() {
        //TODO return proper representation object
      //  Board = new ArrayList<Piece>();
        this.couleur_tour="Blanc";
        

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
        
        /*Object[] table = new Object[2];
        table[1] = this.Board;
        return "Blanc";*/
        if(this.Board.size()!=0){
            this.status = "Partie démarrée";
        }
        else{
            this.status = "Erreur, veuillez relancer la partie";
        }
        return this.status;
    }
    
    @GET
    @Path("pion/{nomP}/{col}")
    @Produces(MediaType.APPLICATION_JSON)
    //Permet de vérifier les déplacements possible pour une pièce 
    public ArrayList<MyEntier> getDeplacement(@PathParam("nomP") String pieceName, @PathParam("col") String couleurJ){
        ArrayList<MyEntier> deplacement_possible = new ArrayList<MyEntier>();
        int nb_p = Board.size();
        for(Piece piece : Board){
            if(piece.getName().equals(pieceName) && couleurJ.equals(this.couleur_tour) && piece.getCouleur().equals(couleurJ)){ //On recherche notre pièce et on vérifie la couleur du joueur
                ArrayList<Integer> deplacement = piece.calculDeplacement();
                for(int i=0;i<=deplacement.size()-1;i++){
                    deplacement_possible.add(new MyEntier(deplacement.get(i))); //On utilise des objets à cause d'un bug empêchant l'utilisation de Mediatype JSON sur les Integer
                }
            }   
        }
        return deplacement_possible; //On les retourne
    }
    
    @PUT
    @Path("/{piece}/{npos}/{colJ}")
    @Produces(MediaType.APPLICATION_JSON)
    //Permet de déplacer une pièce après plusieurs vérifications et renvoie la réponse
    public String move_piece(@PathParam("piece") String pieceName, @PathParam("npos") int newPosition, @PathParam("colJ") String couleurJ) {
        boolean destination_vide = true;
        String msg ="500 - Erreur Possible. Veuillez Réessayer.";
        for(Piece piece : this.Board){
            //On cherche notre piece, on verifie si la position selectionnée est une position possible, si le joueur peut déplacer cette pièce (bonne couleur) et si c'est bien son tour (bonne couleur)
            if(piece.getName().equals(pieceName) && piece.calculDeplacement().contains(newPosition) && couleurJ.equals(this.couleur_tour) && piece.getCouleur().equals(couleurJ)){ 
                if(check_deplacement(piece,newPosition)){  //On vérifie qu'il n'y a aucune pièce sur notre route
                    for(Piece piece_mort : this.Board){ 
                        if(piece_mort.getPosition()==newPosition ){ //On vérifie si aucune des autres pièce n'est sur notre destination
                            destination_vide=false; //Si oui, on enregistre que la destination n'est pas vide
                            if(!piece_mort.getCouleur().equals(piece.getCouleur())){ //On vérifie la couleur de la pièce
                                piece_mort.setVie(false); //Si != on la sort du jeu
                                this.Board.remove(piece_mort);  
                                piece.moveTo(newPosition);
                                String tour = this.turn_change(couleurJ);
                                if(tour.equals("end")){
                                    return "200 - Déplacement réussi. La partie est terminée.";
                                }
                                else{
                                    return "200 - Déplacement réussi. Tour au "+this.couleur_tour+".";
                                }  
                            }
                            else{ //Si == on ne peut pas se déplacer
                                return "500.1 - Échec du déplacement. Réessayez.";
                            }
                        }
                    }
                    if(destination_vide==true){ //On vérifie si on a trouver une pièce sur la destination finale
                        piece.moveTo(newPosition); //Si non, on déplace la pièce
                        String tour = this.turn_change(couleurJ);
                        if(tour.equals("end")){
                            return "200 - Déplacement réussi. La partie est terminée.";
                        }
                        else{
                            return "200 - Déplacement réussi. Tour au "+this.couleur_tour+".";                         
                        }
                    }     
                }
                else{ //S'il y a une pièce sur notre chemin, on ne bouge pas
                    return "500.2 - Échec du déplacement. Réessayez.";
                }
            }
            else{ //Si le déplacement choisie n'est pas un déplacement possible, on ne bouge pas
                msg = "500.3 - Échec du déplacement. Réessayez.";
            }
        } 
        return msg;
    }
    
    //Pas d'annotation, inutilisable par les joueurs (IN MOVE PIECE)
    //Vérifie si une pièce est sur le chemin 
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
                        if(piece_path.getPosition()==position+i*10 && position+i*10!=newpos){//Si la pièce est sur le chemin
                            verif_pion=true;//On notifie qu'elle existe
                        }
                        if(position+i*10==newpos && verif_pion==true){//Si notre destination est bien sur ce chemin et qu'une pièce a été notifiée
                            check=false;//On ne peut aller à notre destination
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
                        if(piece_path.getPosition()==position+i*9 && position+i*9!=newpos){ 
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //Retourne le plateau
    public ArrayList<Piece> getBoard() {
        //On peut rajouter à qui est le tour ici, si on ne peut pas envoyé un msg au deux joueurs sans requêtes.
        return this.Board;
    }
    
    //Pas d'annotation, inutilisable par les joueurs (IN MOVE PIECE)
    //Permet, à la fin d'un tour (après un déplacement), de modifier le joueur dont c'est le tour
    // Et de vérifier si la partie est terminée.
    public String turn_change(String couleurJ){
        int compteur_piece=0;

        if(couleurJ.equals(this.couleur_tour)){//Débogage préventif
            switch(this.couleur_tour) {//Le tour passe au joueur ayant la couleur suivante
                case "Blanc":
                    this.couleur_tour="Noir";
                break;
                case "Noir":
                    this.couleur_tour="Blanc";
                break;
            }
            //Vérification de fin de partie
            for(Piece piece : this.Board){
                if(piece.getCouleur().equals(couleurJ)){
                    compteur_piece++;
                }
            }
            if(compteur_piece==0){
                this.end_game();
                return "end";
            }
            else{
                return this.couleur_tour;
            } 
        }
        else{
            return "500 - Le joueur qui est en train de jouer n'est pas celui dont c'est le tour.";
        }
        
    }
    
    @DELETE
    //Permet de mettre fin à la partie
    public void end_game(){
        this.status="";
        this.Board.clear();
    }
}
    