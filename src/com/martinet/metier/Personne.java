package com.martinet.metier;

import java.time.LocalDate;

/**
 *
 * elle permet de créer une Personne et d'obtenir ou de modifier ses attributs ainsi que de les afficher
 */
public class Personne {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;

    public Personne(String prenom) {
        this.prenom = prenom;
        this.nom = ""; // pour éviter de gérer null
        this.dateDeNaissance = LocalDate.of(0,1,1);
    }

    public Personne(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = LocalDate.of(0,1,1);
    }

    public Personne(String prenom, String nom, LocalDate dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    @Override
    public String toString() {
        //if (nom == null)
        //    return prenom;
        //else
            return prenom +" "+ nom;
    }
}
