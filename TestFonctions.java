import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestFonctions {

    @Test
    public void testPeutPrendreDameVersHaut() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 1,3 , 1, 4,2);

        assertTrue(resultat);
    }

    @Test
    public void testPeutPrendreDameVersBas() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 2, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 2, 4, 2, 3, 1);

        assertTrue(resultat);
    }

    @Test
    public void testPeutPrendreDameVersGauche() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 2, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 1, 3, 2, 2, 3);

        assertTrue(resultat);
    }

    @Test
    public void testPeutPrendreDameVersDroite() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 1, 2, 2, 3, 1);

        assertTrue(resultat);
    }

    @Test
    public void testVerifEnnemiDame() {
        // Créer un plateau de test avec des pièces
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };

        // Le joueur 1 est en train de jouer
        int joueur = 1;

        // Coordonnées d'une dame du joueur 1
        int x = 3;
        int y = 2;

        // Appeler la méthode pour vérifier la présence d'ennemis dans les diagonales
        boolean existeEnnemi = Fonctions.verifEnnemiDame(plateau, joueur, x, y);

        // Vérifier que la méthode renvoie true car il y a un ennemi dans la diagonale haut-gauche
        assertTrue(existeEnnemi);
    }

    @Test
    public void testVerifEnnemiDamePasDennemi() {
        // Créer un plateau de test sans ennemis
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };

        // Le joueur 1 est en train de jouer
        int joueur = 1;

        // Coordonnées d'une dame du joueur 1
        int x = 3;
        int y = 2;

        // Appeler la méthode pour vérifier l'absence d'ennemis dans les diagonales
        boolean existeEnnemi = Fonctions.verifEnnemiDame(plateau, joueur, x, y);

        // Vérifier que la méthode renvoie false car il n'y a pas d'ennemi dans les diagonales
        assertFalse(existeEnnemi);
    }
    
    @Test
    public void testNbDameJoueur1() {
        // Créer un plateau de test avec des dames pour le joueur 1
        int[][] plateau = {
            {11, 0, 11, 0, 0},
            {0, 11, 0, 0, 0},
            {0, 0, 0, 11, 0},
            {0, 0, 0, 0, 11},
            {0, 0, 0, 0, 0}
        };

        // Le joueur 1 est en train de jouer
        int joueur = 1;

        // Appeler la méthode pour obtenir le nombre de dames du joueur 1
        int nbDame = Fonctions.nbDame(plateau, joueur);

        // Vérifier que le nombre de dames est correct
        assertEquals(5, nbDame);
    }

    @Test
    public void testNbDameJoueur2() {
        // Créer un plateau de test avec des dames pour le joueur 2
        int[][] plateau = {
            {22, 0, 0, 0, 22},
            {0, 22, 0, 22, 0},
            {0, 0, 22, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 22, 0, 22, 0}
        };

        // Le joueur 2 est en train de jouer
        int joueur = 2;

        // Appeler la méthode pour obtenir le nombre de dames du joueur 2
        int nbDame = Fonctions.nbDame(plateau, joueur);

        // Vérifier que le nombre de dames est correct
        assertEquals(7, nbDame);
    }

    @Test
    public void testNbDameJoueurInvalide() {
        // Créer un plateau de test sans dames
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        // Joueur invalide (ni 1 ni 2)
        int joueur = 3;

        // Appeler la méthode pour obtenir le nombre de dames (joueur invalide)
        int nbDame = Fonctions.nbDame(plateau, joueur);

        // Vérifier que le nombre de dames est correct (devrait être 0 pour joueur invalide)
        assertEquals(0, nbDame);
    }

    @Test
    public void testListeEnnemie() {
        // Créer un plateau de test avec des ennemis potentiels pour le joueur 1
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };

        // Le joueur 1 est en train de jouer
        int joueur = 1;

        // Coordonnées d'une pièce du joueur 1
        int x = 3;
        int y = 3;

        // Appeler la méthode pour obtenir la liste des ennemis potentiels
        List<int[]> listeEnnemi = Fonctions.listeEnnemie(plateau, joueur, x, y);

        // Créer une liste de coordonnées d'ennemis potentiels attendue
        List<int[]> expectedListeEnnemi = Arrays.asList();

        // Vérifier si la liste d'ennemis obtenue est égale à la liste attendue
        assertEquals(expectedListeEnnemi.size(), listeEnnemi.size());
        for (int i = 0; i < expectedListeEnnemi.size(); i++) {
            assertArrayEquals(expectedListeEnnemi.get(i), listeEnnemi.get(i));
        }
    }

    @Test
    public void testListeEnnemiePasDenemi() {
        // Créer un plateau de test sans ennemis potentiels
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        // Le joueur 1 est en train de jouer
        int joueur = 1;

        // Coordonnées d'une pièce du joueur 1
        int x = 3;
        int y = 3;

        // Appeler la méthode pour obtenir la liste des ennemis potentiels
        List<int[]> listeEnnemi = Fonctions.listeEnnemie(plateau, joueur, x, y);

        // Vérifier que la liste d'ennemis est vide car il n'y a pas d'ennemis potentiels
        assertTrue(listeEnnemi.isEmpty());
    }

}