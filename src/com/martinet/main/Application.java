package com.martinet.main;

import com.martinet.metier.*;
import jdk.jshell.execution.Util;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

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
                   // boolean articlesExistent = articles.afficherArticlesParAuteur(auteur);
                    List<Integer> listeReferencesASupprimer = articles.afficherArticlesParAuteur(auteur);
                    if (!listeReferencesASupprimer.isEmpty()){
                        String choixSupp ="";
                        do {
                            Utilitaire.afficherListeChoixSuppression(auteur);
                            choixSupp = Utilitaire.lireSaisieStringSupp();
                            if (choixSupp.equalsIgnoreCase("T")){
                                if (Utilitaire.lireSaisieConfirmation("êtes-vous sûr que vouloir supprimer tous les " +
                                        "articles de "+auteur+" ?")) {
                                    articles.supprimerArticlesParAuteur(auteur);
                                }
                            } else {
                                if (!choixSupp.equals("0")) {
                                    int ref = Integer.parseInt(choixSupp);
                                    Iterator it = listeReferencesASupprimer.iterator();
                                    boolean articleSupprime = false;
                                    while (it.hasNext()) {
                                        Integer referenceASupp = (Integer) it.next();
                                        if (referenceASupp == ref) {
                                            if (Utilitaire.lireSaisieConfirmation("êtes-vous sûr que vouloir supprimer " +
                                                    "l'article de numero de reference "+ref+"?")) {
                                                articles.supprimerArticle(ref);
                                                it.remove();
                                                articleSupprime = true;
                                            }
                                        }
                                    }
                                    if ( !articleSupprime && !listeReferencesASupprimer.isEmpty()) {
                                        Utilitaire.afficherMessageErreurSupp(ref);
                                    }
                                }
                            }
                        }
                        while ( !choixSupp.equalsIgnoreCase("0") &&
                                !choixSupp.equalsIgnoreCase("T") && !listeReferencesASupprimer.isEmpty() );
                    }
                    break;
                case 6:
                    if (Utilitaire.lireSaisieConfirmation("êtes-vous sûr que vous voulez supprimer " +
                            "tous les articles du magasin?"))
                        articles.viderMagasin();
                    break;
                case 5:
                    int refArticleAModifier = Utilitaire.lireSaisieModdifierArticle();
                    boolean articleExiste = articles.afficherArticle("Article à modifier :", refArticleAModifier);
                    if (articleExiste) {
                        int choixModif = 0;
                        do {
                            Utilitaire.afficherListeChoixModification();
                            choixModif = Utilitaire.lireSaisieInt();
                            switch (choixModif) {
                                case 1:
                                    String nouvelleDesignation =
                                            Utilitaire.lireSaisieStringObligatoire(" la nouvelle designation");
                                    articles.modifierArticle(refArticleAModifier, nouvelleDesignation);
                                    break;
                                case 2:
                                    Double nouveauPrix = Utilitaire.lireSaisieDouble("le nouveau prix : ");
                                    articles.modifierArticle(refArticleAModifier, nouveauPrix);
                                                                        break;
                                case 3:
                                    Personne nouvellePersonne =
                                            Utilitaire.lireSaisiePersonne(" à modifier : ");
                                    articles.modifierArticle(refArticleAModifier, nouvellePersonne);
                                    break;
                                case 4:
                                    boolean saisieValide = false;
                                    while (!saisieValide) {
                                        BigInteger nouvelIsbn = Utilitaire.lireSaisieBigInteger("un nouvel Isbn");
                                        try {
                                            saisieValide = Utilitaire.checkIsbn(nouvelIsbn);
                                        } catch (IsbnException ie) {
                                            System.out.println("Erreur de type : "+ie);
                                        }
                                        articles.modifierArticle(refArticleAModifier, nouvelIsbn);
                                    }
                                case 0:
                                    System.out.println("retour au menu initial");
                                    break;
                                default:
                                    System.out.println("choix invalide");
                                    break;
                            }
                            articles.afficherArticle("Article à modifier", refArticleAModifier);
                        } while (choixModif != 0);
                    }
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