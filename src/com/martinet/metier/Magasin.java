package com.martinet.metier;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe magasin qui permet d'emmagasiner les articles dans une Liste
 * elle permet de créer une liste et d'obtenir ou de modifier cette liste
 * de remplir le magasin avec des articles dans son constructeurs
 * d'ajouter un article au magasin
 * d'afficher la liste des Dvd par réalisateur, d'afficher la liste des articles
 * d'afficher le nombre d'articles du magasin
 * de modifier les caractéristiques d'un article
 * de vider tous les articles du magasin
 */
public class Magasin {
    private List<Article> articles;

    /**
     * Crée une Liste d'articles
     * et remplit cette liste
     */
    public Magasin(){
        this.articles = new ArrayList<>();
        remplirMagasin();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * Remplit la liste d'articles
     * et affiche le nombre d'articles
     */
    public void remplirMagasin(){
        Personne jackK = new Personne("Jack","Kornfield");
        Article livre0 = new Livre("Après l'extase, la lessive", 16.55, new BigInteger(
                "9780123456789"), 158, jackK);
        Article livre1 = new Livre("Bouddha", 18.45, new BigInteger(
                "9780199956888"), 356, jackK);
        Personne despentes = new Personne("Virginie", "Despentes");
        Article livre2 = new Livre("King Kong theorie", 20.99, new BigInteger("9781234567890"),
                356, despentes );
        Article livre3 = new Livre("Les jolies choses", 12.92, new BigInteger("9781434343433"),
                184, despentes );
        Article livre4 = new Livre("En l'absence des hommes", 11.44, new BigInteger("978000007877"),
                168, new Personne("Philippe","Besson") );
        Personne dreamworks = new Personne("Dreamworks");
        Article dvd1 = new Dvd("Over the hedge", 2.50, Duration.ofMinutes(90), dreamworks);
        Personne tarantino = new Personne("Quentin", "Tarantino");
        Article dvd2 = new Dvd("Kill Bill", 15d,Duration.ofMinutes(360), tarantino);
        Personne almodovar = new Personne("Pedro", "Almodovar");
        Article dvd3 = new Dvd("Todo sobre mi madre", 155.99, Duration.ofMinutes(125), almodovar);
        Article dvd4 = new Dvd("Atame", 1.94, Duration.ofMinutes(490), almodovar);
        Article dvd5 = new Dvd("chicken run", 10d, Duration.ofMinutes(115), dreamworks);
        Article dvd6 = new Dvd("Kill Bill 2", 6.75, Duration.ofMinutes(690), tarantino);
        Article dvd7 = new Dvd ("La folle histoire de l'espace", 1.99, Duration.ofMinutes(188),
                new Personne("Mel", "Brooks"));
        this.articles.add(livre0);
        this.articles.add(dvd1);
        this.articles.add(dvd2);
        this.articles.add(dvd3);
        this.articles.add(dvd4);
        this.articles.add(livre1);
        this.articles.add((Article) new Dvd("Kung fu Panda", 100d, Duration.ofMinutes(136), dreamworks));
        this.articles.add(livre2);
        this.articles.add(livre3);
        this.articles.add(dvd5);
        this.articles.add(dvd6);
        this.articles.add(livre4);
        this.articles.add(dvd7);

        afficherNombreArticles();
    }

    /**
     * Méthode qui ajoute au Magasin un article
     * @param article
     */
    public void ajouterArticle(Article article){
        this.articles.add(article);
        System.out.println("Ajout de :");
        System.out.println(article);
    }

    /**
     * Méthode qui affiche la liste des Dvd par réalisateur
     * @param realisateur : Personne saisie
     */
    public void afficherListeParRealisateur(Personne realisateur){
        int compteur = 0;
        for (Article art:articles
             ) {
            if (art instanceof Dvd) {
                if ((((Dvd) art).getRealisateur().getPrenom()).equalsIgnoreCase(realisateur.getPrenom())
                && ((Dvd) art).getRealisateur().getNom().equalsIgnoreCase(realisateur.getNom())) {
                    System.out.println(art);
                    compteur++;
                }
            }
        }
        if (compteur == 0){
            System.out.println("Il n'y a pas de Dvd du réalisateur " +realisateur.getPrenom()+" "+realisateur.getNom()+
                    " dans le magasin");
        }
    }

    /**
     * Méthode qui permet de supprimer un Article par Auteur
     * @param auteur : est un auteur pour les Livres, ou un réalisateur pour les Dvd
     */
    public void supprimerArticlesParAuteur(Personne auteur){
        int compteur =0;
        for (Iterator it = articles.iterator(); it.hasNext(); ) {
            Article art = (Article) it.next();
            if (art instanceof Dvd ) {
                if ( ((Dvd) art).getRealisateur().getPrenom().equalsIgnoreCase(auteur.getPrenom())
                && ((Dvd) art).getRealisateur().getNom().equalsIgnoreCase(auteur.getNom()) ) {
                    System.out.println("Suppression de :"+art);
                    it.remove();
                    compteur++;
                }
            }
            if (art instanceof Livre ) {
                if ( ((Livre) art).getAuteur().getPrenom().equalsIgnoreCase(auteur.getPrenom())
                        && ((Livre) art).getAuteur().getNom().equalsIgnoreCase(auteur.getNom()) ) {
                    System.out.println("Suppression de :"+art);
                    it.remove();
                    compteur++;
                }
            }
        }
        if (compteur == 0) {
            System.out.println("Il n'y a pas d'article de " + auteur.getPrenom() + " " + auteur.getNom() +
                            " dans le magasin");
        } else {
            System.out.println(compteur+" article(s) supprimé(s)");
        }
    }
    public List<Integer> afficherArticlesParAuteur(Personne auteur){
        List<Integer> listeReferences = new ArrayList<>();
        //boolean existe = false;
        System.out.println("Liste des articles de "+auteur+" :");
        for (Iterator it = articles.iterator(); it.hasNext(); ) {
            Article art = (Article) it.next();
            if (art instanceof Dvd ) {
                if ( ((Dvd) art).getRealisateur().getPrenom().equalsIgnoreCase(auteur.getPrenom())
                        && ((Dvd) art).getRealisateur().getNom().equalsIgnoreCase(auteur.getNom()) ) {
                    System.out.println(art);
                    //existe = true;
                    listeReferences.add(art.getReference());
                }
            }
            if (art instanceof Livre ) {
                if ( ((Livre) art).getAuteur().getPrenom().equalsIgnoreCase(auteur.getPrenom())
                        && ((Livre) art).getAuteur().getNom().equalsIgnoreCase(auteur.getNom()) ) {
                    System.out.println(art);
                    listeReferences.add(art.getReference());
                    //existe = true;
                }
            }
        }
        if (listeReferences.isEmpty()) {
            System.out.println("Il n'y a pas d'article de " + auteur.getPrenom() + " " + auteur.getNom() +
                    " dans le magasin");
        }
        return listeReferences;
    }
    /**
     * Méthode qui affiche la liste de tous les articles du Magasin
     */
    public void afficherListeTousArticles(){
        if (articles.isEmpty())
            System.out.println("La liste des articles est vide");
        else {
            for (Article article : articles
            ) {
                System.out.println(article);
            }
            System.out.println();
            afficherNombreArticles();
        }
    }

    /**
     * Méthode qui affiche un message avec le nombre d'articles du magasin
     */
    public void afficherNombreArticles(){
        System.out.println("Magasin remplis de : "+articles.size()+ " articles");
    }

    /**
     * Méthode qui affiche le détail de l'article sélectionné
     * @param reference : numero de reference de l'Article
     * @return si l'article existe (booleen)
     */
   public boolean afficherArticle(String message, int reference){
       boolean articleExiste = false;
       for (int i = 0; i<this.articles.size() && !articleExiste; i++) {
            if (articles.get(i).getReference().intValue() == reference) {
                System.out.println(message);
                System.out.println(articles.get(i).toString());
                articleExiste = true;
            }
       }
       if (reference != 0 && !articleExiste){
           System.out.println("Il n'y a pas d'article ayant comme numéro de référence : "+reference);
       }
       return articleExiste;
   }

    /**
     *
     * @param reference
     * @return
     */
    public boolean supprimerArticle(int reference){
        boolean trouve = false;
        for (Iterator it = articles.iterator(); it.hasNext() && !trouve; ) {
            Article art = (Article) it.next();
            if (art.getReference().intValue() == reference) {
                System.out.println("Suppression de l'article :");
                System.out.println(art);
                it.remove();
                trouve = true;
            }
        }
        if (reference != 0 && !trouve){
            System.out.println("Il n'y a pas d'article ayant comme numéro de référence : "+reference);
        }
        return trouve;
    }
    /**
     * Méthode qui modifie la désignation d'un article
     * @param reference numéro unique de l'article
     */
    public void modifierArticle(int reference, String nouvelleDesignation){
        Iterator it = this.articles.iterator();
        boolean trouve = false;
        while (it.hasNext() && !trouve){
            Article art = (Article) it.next();
            if (art.getReference() == reference){
                art.setDesignation(nouvelleDesignation);
                trouve = true;
            }
        }
    }
    public void modifierArticle(int reference, Double prix){
        Iterator it = this.articles.iterator();
        boolean trouve = false;
        while (it.hasNext() && !trouve){
            Article art = (Article) it.next();
            if (art.getReference() == reference){
                art.setPrix(prix);
                trouve = true;
            }
        }
    }

    public void modifierArticle(int reference, Personne nouvellePersonne) {
        Iterator it = this.articles.iterator();
        boolean trouve = false;
        while (it.hasNext() && !trouve){
            Article art = (Article) it.next();
            if (art.getReference() == reference){
                trouve = true;
                if (art instanceof Dvd){
                    ((Dvd) art).setRealisateur(nouvellePersonne);
                }
                if (art instanceof Livre){
                    ((Livre) art).setAuteur(nouvellePersonne);
                }
            }
        }
    }
    public void modifierArticle(int refArticleAModifier, BigInteger nouvelIsbn) {
        Iterator it = this.articles.iterator();
        boolean trouve = false;
        while (it.hasNext() && !trouve){
            Article art = (Article) it.next();
            if (art.getReference() == refArticleAModifier) {
                trouve = true;
                if (art instanceof Livre) {
                    ((Livre) art).setIsbn(nouvelIsbn);
                } else {
                    System.out.println("Un Dvd n'a pas d'Isbn...");
                }
            }
        }
    }
    /**
     * Méthode qui permet de vider entièrement le magasin
     */
    public void viderMagasin(){
        //Iterator iterArticles = articles.iterator();
        //while (iterArticles.hasNext()){
        //    Article art = (Article) iterArticles.next();
        //    iterArticles.remove();
        //}
                                       // ou plus simplement :
        articles.clear();
        System.out.println("Vous avez supprimé tous les articles du magasin");
    }



}
