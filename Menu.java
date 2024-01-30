import java.util.Scanner;

class Menu {
    public static String[] pseudo = new String[2];
    public static void gMenu() {
        Scanner scanner = new Scanner(System.in);
        String choix="";
        int choix2=0;
        

        do {
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1. Nouvelle partie");
            System.out.println("2. Règles");
            System.out.println("3. Quitter");
            System.out.println("==========================");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextLine();
            choix2 = Character.getNumericValue(choix.charAt(0));
            System.out.println();
            System.out.println();


            switch (choix2) {
                case 1:
                    System.out.println("Lancement d'une nouvelle partie contre un joueur...");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    do{
                    System.out.println("Entrez le pseudo du joueur Bleu :");
                    pseudo[0]=scanner.nextLine();
                    System.out.println("Entrez le pseudo du joueur Rouge :");
                    pseudo[1]=scanner.nextLine();
                    System.out.println();
                    System.out.println();
                    }while(pseudo[0].length()==0 || pseudo[1].length()==0);
                    appelJeu();
                    break;

                case 2:
                    System.out.println("Affichage des règles...");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    afficherRegles();
                    break;

                case 3:
                    System.out.println("Deja ? :-( ... Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
            
        } while (choix.length() !=1 || !Character.isDigit(choix.charAt(0)) || choix2!=3);

        scanner.close();
    }

    public static int[][] plateauprisePionConsécutif={
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,2,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,2,0,2,0,2,0,0},
        {0,0,1,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}
    };
    public static int[][] plateaupriseDameConsécutif={
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,2,0,2,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,2,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,11,0,2,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}
    };
    
    public static int[][] plateauRougeGagne={
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,2,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}
    };

    public static int[][] plateauPassageDameBleu={
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,2,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}
    };

    public static int[][] plateauPassageDameRouge={
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,2,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}
    };

    public static void appelJeu(){
        int[][] plateau;
        plateau =Fonctions.creePlateau(10,10);
        Fonctions.initPions(plateau);
        Fonctions.jeu(plateau, Fonctions.score);
    }


    public static void afficherRegles() {
        System.out.println("Règles du jeu de dames :");
        System.out.println();
        
        System.out.println(" 1| Le jeu se joue sur un plateau de cases noires et blanches.");
        System.out.println(" 2| Chaque joueur commence avec 20 pions placés sur les cases noires de sa rangée la plus proche.");
        System.out.println(" 3| Les pions se déplacent en diagonale d'une case à la fois.");
        System.out.println(" 4| Si un pion atteint la dernière rangée de l'adversaire, il devient une dame.");
        System.out.println(" 5| Un pion qui a été touché doit obligatoirement être joué.");
        System.out.println(" 6| Les dames peuvent se déplacer en diagonale sur plusieurs cases.");
        System.out.println(" 7| Le but du jeu est de capturer tous les pions de l'adversaire.");
        System.out.println(" 8| On capture un pion en sautant par-dessus lui, et on le retire du plateau.");
        System.out.println(" 9| Si une capture est possible, le joueur doit la faire.");
        System.out.println("10| Le joueur qui capture tous les pions de l'adversaire gagne la partie.");
        System.out.println("11| Si un joueur ne peut pas effectuer de mouvement, il perd la partie.");

        System.out.println();
        System.out.println();
        System.out.println();
    }

}