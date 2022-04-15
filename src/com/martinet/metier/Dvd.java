package com.martinet.metier;

import java.text.DecimalFormat;
import java.time.Duration;

/**
 * La classe Dvd dérive de la Classe Article
 * elle se caractérise en plus par une durée et une Personne (réalisateur)
 * On conviendra qu'elle ne possède pas d'ISBN
 * mais elle possède bien un identifiant unique reference hérité de Article
 * elle permet de créer un Livre et d'obtenir ou de modifier ses attributs ainsi que de les afficher
 */
public class Dvd extends Article{
    private Duration duree;
    private Personne realisateur;

    /**
     * Constructeur par défaut
     * les attributs sont initialisés pour ne pas à avoir à gérer des nulls
     */
    public Dvd(){
        super();
        this.duree = Duration.ZERO;
        this.realisateur = new Personne("Anonymous");
    }

    /**
     * Créé un Dvd avec les paramètres :
     * @param designation le titre
     * @param prix $$$
     * @param realisateur qui est une personne
     */
    public Dvd(String designation, Double prix, Personne realisateur) {
        super(designation, prix);
        this.realisateur = realisateur;
        this.duree = Duration.ZERO;
    }

    /**
     * Constructeur complet avec :
     * @param designation le titre
     * @param prix $$$
     * @param duree qui est un objet de type Duration
     * @param realisateur qui est un objet de Personne
     */
    public Dvd(String designation, Double prix, Duration duree, Personne realisateur) {
        super(designation, prix);
        this.duree = duree;
        this.realisateur = realisateur;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    public Personne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
    }

    @Override
    public String toString() {
        DecimalFormat deci = new DecimalFormat("0.00");
        return "Dvd   =>" +
                " reference : " + String.format("%07d", (long)getReference()) +
                ", titre : " + super.getDesignation() +
                ", prix : " + deci.format(super.getPrix()) + '$' +
                ", par : " + realisateur.toString();
    }
}
