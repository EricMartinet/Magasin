package com.martinet.metier;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * La Classe Utilitaire regroupe des méthodes statiques pour afficher des messages, saisir et lire des données
 * utiles pour l'Application de gestion de Magasin qui contient des Articles (Livre, Dvd)
 */
public class Utilitaire {

    public static void afficherMenu(){
        System.out.println();
        System.out.println("MENU");
        System.out.println("====");
        System.out.println("1. Afficher la liste de tous les articles");
        System.out.println("2. Afficher la liste des Dvd d'un realisateur");
        System.out.println("3. Ajouter un dvd");
        System.out.println("4. Supprimer un article en fonction d'un auteur ou d'un réalisateur : " +
                "fonctionne pour un livre ou un Dvd !!!");
        System.out.println("5. Modifier un article : fonctionne pour un livre ou un Dvd !!!");
        System.out.println("6. Supprimer toute la liste");
        System.out.println("0. Quitter");
        System.out.println("Entrez votre choix (1, 2, 3, 4, 5, 6, 0 pour quitter): ");
    }

    /**
     * Méthode permettant de lire la saisie d'un entier depuis la console
     * Si l'utilisateur saisi un caractère, un message d'erreur et la méthode est à nouveau appelée
     * @return l'entier saisi
     */
    public static int lireSaisieInt(){
        Scanner saisie = new Scanner(System.in);
        int choix;
        try {
            choix = saisie.nextInt();
        } catch (InputMismatchException ime){
            System.out.println("Veuillez saisir un entier : ");
            choix = lireSaisieInt(); // boucle récursive :)
        }
        return choix;
    }
    public static BigInteger lireSaisieBigInteger(String libelle){
        System.out.println("Saisissez "+libelle);
        Scanner saisie = new Scanner(System.in);
        String chiffreString = saisie.nextLine();
        BigInteger isbn = BigInteger.ZERO;
        try {
            isbn = BigInteger.valueOf(Long.parseLong(chiffreString));
        }
        catch (NumberFormatException nfe){
            System.out.println("Vous avez saisi : "+chiffreString);
            System.out.println("Ressaisissez-vous et si vous avez bien saisi, saisissez un nombre SVP :");
            isbn = lireSaisieBigInteger("le nouvel Isbn");
        }
        catch (Exception e){
            System.out.println("La dernière saisie génère une erreur de type "+e);
        }
        return isbn;
    }
    public static boolean checkIsbn(BigInteger isbn) throws IsbnException{
       boolean isbnValide = false;
        BigInteger isbnMini = new BigInteger("1000000000000");
        BigInteger isbnMaxi = new BigInteger("9999999999999");
       if ( isbn.compareTo(isbnMaxi) == 1 || isbn.compareTo(isbnMini) == -1) {
           throw new IsbnException("Un isbn possède exactement 13 caractères de long");
       } else if (!isbn.toString().startsWith("978")){
        throw new IsbnException("Un isbn commence toujours par 978");
        } else {
            isbnValide = true;
        }
        return isbnValide;
    }

    /**
     * Méthode permettant de saisir un nombre réel avec point ou virgule comme séparateur de décimal
     * Gère les mauvaises saisies alphabétique par récursivité
     * @return un nombre Double
     */
    public static Double lireSaisieDouble(String champ){
        System.out.println("Entrez "+champ);
        Scanner saisie = new Scanner(System.in);
        // pour s'assurer que la console prend les décimaux à point comme à virgule
        String chiffreString = saisie.next();
        Double chiffreDouble = Double.valueOf(0);
        try{
            chiffreDouble = Double.parseDouble(chiffreString);
        }
        catch (NumberFormatException nfe){
            System.out.println("Vous avez saisi : "+chiffreString);
            System.out.println("Ressaisissez-vous et si vous avez bien saisi, saisissez un nombre SVP :");
            chiffreDouble = lireSaisieDouble(champ); // recursive
        }
        if (chiffreString.contains(",")) {
            String chiffremod = chiffreString.replace(",", ".");
            chiffreDouble = Double.parseDouble(chiffremod);
        }
        return chiffreDouble;
    }

    /**
     * Méthode qui permet l'affichage d'un message puis la saisie d'un
     * @param champ chaine de caractère optionnel
     * @return la lecture de la chaine de caractere saisie
     */
    public static String lireSaisieStringOption(String champ){
        Scanner saisie = new Scanner(System.in);
        System.out.println("Entrez "+champ+" : ");
        return saisie.nextLine();
    }

    /**
     * Méthode qui lit la saisie du choix de suppression et boucle en récursivité tant que la saisie
     * n'est ni "T", ni un entier
     * @return
     */
    public static String lireSaisieStringSupp(){
        Scanner saisie = new Scanner(System.in);
        String choixString =saisie.nextLine();
        int choixNum = 0;
        boolean choixValide = false;
        while (!choixValide) {
            if (choixString.equalsIgnoreCase("T")){
                choixValide = true;
            } else{
                try {
                    choixValide = true;
                    choixNum = Integer.parseInt(choixString);
                } catch (InputMismatchException ime){
                    System.out.println("Vous avez saisi : "+choixString);
                    System.out.println("Saisisser T pour tout supprimer ou un numéro de référence entier :");
                    choixValide = false;
                    choixString = lireSaisieStringSupp();
                }
                catch (NumberFormatException nfe){
                    System.out.println("Vous avez saisi : "+choixString);
                    System.out.println("Saisisser T pour tout supprimer ou un numéro de référence entier :");
                    choixValide = false;
                    choixString = lireSaisieStringSupp();
                }
            }
        }
        return choixString;
    }
    /**
     * Méthode qui permet l'affichage d'un message puis la saisie d'un
     * @param champ chaine de caractère obligatoire
     * @return la lecture de la chaine de caractere saisie
     */
    public static String lireSaisieStringObligatoire(String champ){
        Scanner saisie = new Scanner(System.in);
        System.out.println("Entrez "+champ+" : ");
        String mot = saisie.nextLine();
        while (mot == ""){
            System.out.println(champ+" obligatoire ===> ");
            mot = saisie.nextLine();
        }
        return mot;
    }

    /**
     * Méthode qui permet d'afficher les messages de saisie et de lire les saisie des données pour créer
     * avec son titre, son prix et son réalisateur un Dvd
     * @return le Dvd
     */
    public static Dvd lireSaisieDvd(){
        System.out.println("=== Donnees d'un Dvd ===");
        Personne real = lireSaisiePersonne("du realisateur");
        String titre = Utilitaire.lireSaisieStringObligatoire("titre du Dvd");
        Double prix = Utilitaire.lireSaisieDouble("le prix du Dvd :");
        System.out.println("un Dvd n'a pas d'isbn :D ");
        Dvd dvd = new Dvd(titre, prix, real);
        return dvd;
    }

    /**
     * Méthode qui permet d'appeler la méthode qui affiche le message et lit la saisie du prénom obligatoire
     * et d'appeler la méthode qui affiche le message de saisie et lit la saisie du nom optionnelle
     * @param typePersonne pour différencier si on demande un auteur ou un réalisateur
     * @return la personne (l'auteur ou le réalisateur)
     */
    public static Personne lireSaisiePersonne(String typePersonne) {
        String prenomReal = Utilitaire.lireSaisieStringObligatoire("le prénom "+typePersonne);
        String nomReal = Utilitaire.lireSaisieStringOption("le nom "+typePersonne+" (ou appuyez sur enter)");
        return new Personne(prenomReal, nomReal);
    }

    /**
     * Méthode qui affiche et lit la confirmation de suppression de tous les articles du magasin
     * @return le booleen de confirmation
     */
    public static boolean lireSaisieConfirmation(String message){
        Scanner saisie = new Scanner(System.in);
        System.out.println(message);
        System.out.println("Si oui tapez \"o\" ou \"Oui\", pour annuler saisissez une autre lettre");
        String confirm = saisie.nextLine();
        if (confirm.equalsIgnoreCase("o") || confirm.equalsIgnoreCase("oui")){
            return true;
        }
        return false;
    }

    /**
     * Méthode qui affiche le message de saisi du numéro de l'article et le retourne
     * @return numéro de référence de l'article
     */
    public static int lireSaisieModdifierArticle(){
        System.out.println("Saisissez le numéro de référence de l'article à modifier (ou 0 pour annuler):");
        return lireSaisieInt();
    }
    /**
     * Méthode qui affiche les choix dans le sous menu de Suppression d'un article
     */
    public static void afficherListeChoixSuppression(Personne createur) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("SOUS-MENU de Suppression d'un article de :"+createur);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Saisir le numero de référence de l'article à supprimer");
        System.out.println("Pour supprimer tous les articles de l'auteur taper T");
        System.out.println("Pour revenir au menu principal taper 0");
        System.out.println("Saisissez votre choix : ");
    }
    /**
     * Méthode qui affiche les choix des champs à modifier
     */
    public static void afficherListeChoixModification() {
        System.out.println("----------------------------------------");
        System.out.println("SOUS-MENU de Modification d'un article :");
        System.out.println("----------------------------------------");
        System.out.println("1. Modifier le titre");
        System.out.println("2. Modifier le prix");
        System.out.println("3. Modifier l'auteur ou le réalisateur");
        System.out.println("4. Modifier l'isbn du livre tapez 4");
        System.out.println("0. Menu principal");
        System.out.println("Saisissez 1, 2, 3, 4 ou 0 pour revenir au menu principal :");
    }

    /**
     * Méthode qui affiche le message de clotûre
     */
    public static void afficheMessageAuRevoir() {
        System.out.println("J'espère que vous avez eu du plaisir à jouer dans notre magasin");
        System.out.println("Bye là !");
    }

    public static void afficherMessageErreurSupp(int ref) {
        System.out.println("L'article avec reference "+ref+" ne fait pas partie de" +
                "la liste des articles de l'auteur");
    }

}
