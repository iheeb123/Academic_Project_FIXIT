/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import service.PosteurService;
/**
 *
 * @author asus
 */
public class Offre {
    private String adresse;
    private String Date_debut;
    private String heure;
    private String description_offre;
    private String tel;
    private String etatoffre;
    private int iposteurfg;
    private String prenomposteur;
    private String nomposteur;

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription_offre() {
        return description_offre;
    }

    public void setDescription_offre(String description_offre) {
        this.description_offre = description_offre;
    }

    public void setDate_debut(String Date_debut) {
        this.Date_debut = Date_debut;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }
    

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getHeure() {
        return heure;
    }

    

    public String getDate_debut() {
        return Date_debut;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNomposteur(String nomposteur) {
        this.nomposteur = nomposteur;
    }

    public void setPrenomposteur(String prenomposteur) {
        this.prenomposteur = prenomposteur;
    }

    public String getNomposteur() {
        return nomposteur;
    }

    public String getPrenomposteur() {
        return prenomposteur;
    }
    
     public Offre(String adresse, String Date_debut , String heure ,String tel,String description_offre) {
        super();
       this.adresse = adresse;
      
       this.Date_debut = Date_debut;
       this.heure = heure;
       this.tel = tel;
        this.description_offre = description_offre;
        
    }
     public Offre(String adresse, String Date_debut , String heure ,String tel,String description_offre,Integer iposteurfg,String etatoffre,String nomposteur,String prenomposteur) {
        super();
       this.adresse = adresse;
       
       this.Date_debut = Date_debut;
       this.heure = heure;
       this.tel = tel;
       this.description_offre = description_offre;
       this.iposteurfg=iposteurfg;
       this.etatoffre=etatoffre;
       this.nomposteur=nomposteur;
       this.prenomposteur=prenomposteur;
        
    }

    public void setEtatoffre(String etatoffre) {
        this.etatoffre = etatoffre;
    }

    public void setIposteurfg(int iposteurfg) {
        this.iposteurfg = iposteurfg;
    }

    public String getEtatoffre() {
        return etatoffre;
    }

    public int getIposteurfg() {
        return iposteurfg;
    }
     
     
     
    
    
}
