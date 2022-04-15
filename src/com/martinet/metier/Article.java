package com.martinet.metier;

/**
 * La classe article permet de créer un article
 */
public class Article {
    /**
     * le numéro de référence est unique grace au compteur incrémenté à chaque ajout d'article
      */
    private Integer reference = 0;
    private static Integer compteurReference = 100; // incrémenté à chaque ajout d'article
    private String designation;
    private Double prix;

    /**
     * Crée un article par défaut
     */
    public Article() {
        this("article inconnu", 0.);
    }

    /**
     * Créé un article avec :
     * @param designation
     * @param prix
     */
    public Article(String designation, Double prix) {
        this.reference = ++compteurReference;
        this.designation = designation;
        this.prix = prix;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public static Integer getCompteurReference() {
        return compteurReference;
    }

    public static void setCompteurReference(Integer compteurReference) {
        Article.compteurReference = compteurReference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Article{" +
                "Numero de reference=" + reference +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                '}';
    }
}
