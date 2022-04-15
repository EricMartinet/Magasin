package com.martinet.metier;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * La classe Livre dérive de la classe Article
 * elle possède les attributs privés tels que Isbn, le nombre de pages et l'auteur qui est une Personne
 * elle permet de créer un Livre et d'obtenir ou de modifier ses attributs ainsi que de les afficher
 */
public class Livre extends Article{
    private BigInteger isbn;
    private Integer nombreDePages = 0;
    private Personne auteur;

    public Livre() {
        this("un livre", 0.,BigInteger.ZERO, 0, new Personne("Bouddha"));
    }

    public Livre(String designation, Double prix, BigInteger isbn, Integer nombreDePages, Personne auteur) {
        super(designation, prix);
        this.isbn = isbn;
        this.nombreDePages = nombreDePages;
        this.auteur = auteur;
    }

    public BigInteger getIsbn() {
        return isbn;
    }

    public void setIsbn(BigInteger isbn) {
        this.isbn = isbn;
    }

    public Integer getNombreDePages() {
        return nombreDePages;
    }

    public void setNombreDePages(Integer nombreDePages) {
        this.nombreDePages = nombreDePages;
    }

    public Personne getAuteur() {
        return auteur;
    }

    public void setAuteur(Personne auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        DecimalFormat deci = new DecimalFormat("0.00");
        return "Livre =>" +
                " reference : " + String.format("%07d", (long)getReference()) +
                ", isbn : " + isbn +
                ", titre : " + super.getDesignation() +
                ", prix : " + deci.format(super.getPrix()) + '$' +
                ", par : " + auteur.toString() +
                " ";
    }
}
