package com.martinet.main;

import com.martinet.metier.*;
import jdk.jshell.execution.Util;

import java.math.BigInteger;
import java.util.*;

/**
 * Classe qui permet de tester la gestion d'articles (livres, Dvd) en créant un magasin et en affichant un menu avec plusieurs choix
 * (affichage, ajout, suppression, modification)
 */
public class Application {

    public static void main(String[] args) {
        Magasin articles = new Magasin();
        int choix = -1;
        do {
            Utilitaire.afficherMenu();
            choix = Utilitaire.lireSaisieInt();
            switch (choix) {
                case 1:
                    articles.afficherListeTousArticles();
                    break;
                case 2:
                    articles.afficherListeParRealisateur(Utilitaire.lireSaisiePersonne("du réalisateur"));
                    break;
                case 3:
                    Dvd dvd = Utilitaire.lireSaisieDvd();
                    articles.ajouterArticle(dvd);
                    break;
                case 4:
                    Personne auteur = Utilitaire.lireSaisiePersonne("de l'auteur");
                    Map<Integer, Article> mapReferencesASupprimer = articles.afficherArticlesParAuteur(auteur);
                    boolean premierAffichageMenu = true;
                    if (!mapReferencesASupprimer.isEmpty()){
                        String choixSupp ="";
                        do {
                            if (!premierAffichageMenu)
                                Utilitaire.afficherMap(mapReferencesASupprimer);
                            Utilitaire.afficherListeChoixSuppression(auteur);
                            choixSupp = Utilitaire.lireSaisieStringSupp();
                            if (choixSupp.equalsIgnoreCase("T")){
                                if (Utilitaire.lireSaisieConfirmation("êtes-vous sûr de vouloir supprimer tous les " +
                                        "articles de "+auteur+" ?")) {
                                    articles.supprimerTousArticlesParAuteur(auteur);
                                }
                            } else {
                                if (!choixSupp.equals("0")) {
                                    int ref = Integer.parseInt(choixSupp);
                                    boolean articleSupprime = false;
                                    boolean confirmSuppression = false;
                                    if (mapReferencesASupprimer.containsKey(ref)){
                                        confirmSuppression = Utilitaire.lireSaisieConfirmation("êtes-vous sûr " +
                                                "de vouloir supprimer " +
                                                "l'article de numero de reference "+ref+"?");
                                        if (confirmSuppression) {
                                            articleSupprime = articles.supprimerArticle(ref);
                                            // https://stackoverflow.com/questions/1884889/iterating-over-and-removing-from-a-map
                                            mapReferencesASupprimer.keySet().removeIf(key->key==ref);
                                        } else {
                                            System.out.println("*** Suppression annulée ***");
                                        }
                                    } else {
                                        Utilitaire.afficherMessageErreurSupp(ref);
                                    }
                                }
                            }
                            premierAffichageMenu = false;
                        }
                        while ( !choixSupp.equalsIgnoreCase("0") &&
                                !choixSupp.equalsIgnoreCase("T") && !mapReferencesASupprimer.isEmpty() );
                        if (mapReferencesASupprimer.isEmpty()){
                            System.out.println("*** Il n'y a plus d'articles de "+auteur+" dans le magasin");
                        }
                    }
                    break;
                case 5:
                    articles.afficherListeTousArticles();
                    int refArticleAModifier = Utilitaire.lireSaisieModdifierArticle();
                    Article articleAModifier = articles.getArticle(refArticleAModifier);
                    Utilitaire.afficherArticle("Détail de l'article à modifier :", articleAModifier);
                    if (articleAModifier != null){
                        int choixModif = 0;
                        do {
                            Utilitaire.afficherListeChoixModification(articleAModifier);
                            choixModif = Utilitaire.lireSaisieInt();
                            switch (choixModif) {
                                case 1:
                                    String nouvelleDesignation =
                                            Utilitaire.lireSaisieStringObligatoire(" la nouvelle designation");
                                    if (Utilitaire.lireSaisieConfirmation("Êtes vous sûr de  modifier la désignation "+
                                            articleAModifier.getDesignation()+" pour : "+nouvelleDesignation+" ?")) {
                                        articleAModifier.setDesignation(nouvelleDesignation);
                                    }
                                    break;
                                case 2:
                                    Double nouveauPrix = Utilitaire.lireSaisieDouble("le nouveau prix : ");
                                    if (Utilitaire.lireSaisieConfirmation("Êtes vous sûr de  modifier la prix "+
                                            articleAModifier.getPrix()+" pour : "+nouveauPrix+" ?")) {
                                        articleAModifier.setPrix(nouveauPrix);
                                    }
                                    break;
                                case 3:
                                    Personne nouvellePersonne =
                                            Utilitaire.lireSaisiePersonne(" à modifier : ");
                                    if (Utilitaire.lireSaisieConfirmation("Êtes vous sûr de  modifier le créateur "+
                                            "pour : "+nouvellePersonne+" ?")) {
                                        if (articleAModifier instanceof Dvd) {
                                            ((Dvd) articleAModifier).setRealisateur(nouvellePersonne);
                                        }
                                        if (articleAModifier instanceof Livre) {
                                            ((Livre) articleAModifier).setAuteur(nouvellePersonne);
                                        }
                                    }
                                    break;
                                case 4:
                                    if (articleAModifier instanceof Livre) {
                                        boolean saisieValide = false;
                                        BigInteger nouvelIsbn = null;
                                        while (!saisieValide) {
                                            nouvelIsbn = Utilitaire.lireSaisieBigInteger("un nouvel Isbn");
                                            try {
                                                saisieValide = Utilitaire.checkIsbn(nouvelIsbn);
                                            } catch (IsbnException ie) {
                                                System.out.println("Erreur de type : " + ie);
                                            }
                                        }
                                        ((Livre) articleAModifier).setIsbn(nouvelIsbn);
                                    } else {
                                        System.out.println("choix 4 invalide");
                                    }
                                    break;
                                case 0:
                                    System.out.println("retour au menu initial");
                                    break;
                                default:
                                    System.out.println("choix invalide");
                                    break;
                            }
                            Utilitaire.afficherArticle("Détail de l'article à modifier :", articleAModifier);
                        } while (choixModif != 0);
                    }                     break;
                case 6:
                    if (Utilitaire.lireSaisieConfirmation("êtes-vous sûr que vous voulez supprimer " +
                            "tous les articles du magasin?"))
                        articles.viderMagasin();
                    break;
                case 0:
                    Utilitaire.afficheMessageAuRevoir();
                    System.exit(0);
                    break;
                default:
                        System.out.println("Choix " + choix + " invalide");
            }
        }
        while (choix!=0);
    }
}