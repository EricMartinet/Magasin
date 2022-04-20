package com.martinet.metier;

/**
 * Classe de gestion d'erreur de saisie d'Isbn héritée de la classe Exception
 * affiche le message général suivant dans le constructeur par defaut ou un message distinct en parametre
 */
public class IsbnException extends Exception{
    public IsbnException() {
        System.out.println("Un Isbn doit posséder exactement 13 caractères numériques et toujours débuter par 978");
    }

    public IsbnException(String message) {
        super(message);
    }
}
