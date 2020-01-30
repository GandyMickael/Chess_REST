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
public class Piece {
    protected String nom;
    protected String couleur;
    protected int position;
    protected String type;
    protected ArrayList<Integer> deplacement = new ArrayList<Integer>();
    protected boolean vie;
    public Piece(){
    }
    
    public String getName(){
        return this.nom;
    }
    
    
    public void setPosition(int newPosition){
        this.position=newPosition;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public void setCouleur(String col){
        this.couleur=col;
    }
    
    public String getCouleur(){
        return this.couleur;
    }
    
    public void setDeplacement(ArrayList<Integer> newDep){
        this.deplacement=newDep;
    }
    
    public ArrayList<Integer> getDeplacement(){
        return this.deplacement;
    }
    
    public void moveTo(int newPosition){
        this.position=newPosition;
    }
    
    public boolean getVie(){
        return this.vie;
    }
    public void setVie(boolean etat){
        this.vie=etat;
    }
    
    public ArrayList<Integer> verifDeplacement(ArrayList<Integer> deplacement){
    
        return deplacement;  
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public ArrayList<Integer> calculDeplacement(){ return deplacement;}
}
