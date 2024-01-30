import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fonctions {
    public static final String ANSI_RESET = "\u001B[0m"; /* Couleur reset */
    public static final String ANSI_RED="\u001B[31m"; /* Couleur rouge */
    public static final String ANSI_BLUE="\033[0;34m";/* Couleur Bleu */
    public static final String ANSI_GREEN = "\033[1;32m";/* Couleur Vert */
    public static final String PION_ROUGE=ANSI_RED+" ⛂"+ANSI_RESET; /*⛂ ⛁      ATTENTION REMPLACER LES PIONS PAR 0 1 2 SI CES CARACTERES NE S'AFFICHENT PAS !!!!!!!*/ 
    public static final String PION_BLEU=ANSI_BLUE+" ⛂"+ANSI_RESET;
    public static final String DAME_ROUGE=ANSI_RED+" ⛁"+ANSI_RESET;
    public static final String DAME_BLEU=ANSI_BLUE+" ⛁"+ANSI_RESET;

    public static int[] score={20,20};
    public static int[][] deplacements={{1,-1},{1,1},{-1,-1},{-1,1}};
   
    public static Scanner sc=new Scanner(System.in);
 
    
//Fonction qui permet de lancer le jeu et de l'arreter lorsque les conditions sont réunis
    public static void jeu(int[][]plateau, int[] score){
        int joueur=1;
        int etat=0;
        boolean jeu=true;
        do {
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            
            affichePseudo(joueur);
            do{
                afficheTableau(plateau);
                etat=coupForce(plateau, joueur);
            }while(etat==1);
            
            if (joueur==1 && (etat ==0 || etat==2)) {
                joueur=2;
                
            }
            else{
                joueur=1;
            }

            if (score[0]==0) {
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println(ANSI_RED+"       _      _                   \r\n" + //
                        "      (_)    | |                  \r\n" + //
                        "__   ___  ___| |_ ___  _ __ _   _ \r\n" + //
                        "\\ \\ / / |/ __| __/ _ \\| '__| | | |\r\n" + //
                        " \\ V /| | (__| || (_) | |  | |_| |\r\n" + //
                        "  \\_/ |_|\\___|\\__\\___/|_|   \\__, |\r\n" + //
                        "                             __/ |\r\n" + //
                        "                            |___/ "+ANSI_RESET);
                jeu=false;
            }
            else if(score[1]==0){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println(ANSI_BLUE+"       _      _                   \r\n" + //
                        "      (_)    | |                  \r\n" + //
                        "__   ___  ___| |_ ___  _ __ _   _ \r\n" + //
                        "\\ \\ / / |/ __| __/ _ \\| '__| | | |\r\n" + //
                        " \\ V /| | (__| || (_) | |  | |_| |\r\n" + //
                        "  \\_/ |_|\\___|\\__\\___/|_|   \\__, |\r\n" + //
                        "                             __/ |\r\n" + //
                        "                            |___/ "+ANSI_RESET);
                jeu=false;
            }
            else if (nbDame(plateau, 1)==3 &&nbDame(plateau, 2)==1 && nbPion(plateau, 1)==0 && nbPion(plateau,2)==0){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Egalité ! ¯\\_(ツ)_/¯");
                jeu=false;
            }
            else if (nbDame(plateau, 1)==1 &&nbDame(plateau, 2)==3 && nbPion(plateau, 1)==0 && nbPion(plateau,2)==0){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Egalité ! ¯\\_(ツ)_/¯");
                jeu=false;
            }
            else if(nbDame(plateau, 1)==1 && (nbDame(plateau, 2)==2 && nbPion(plateau, 2)==1)){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Egalité ! ¯\\_(ツ)_/¯");
                jeu=false;
            }
            else if(nbDame(plateau, 2)==1 && (nbDame(plateau, 1)==2 && nbPion(plateau, 1)==1)){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Egalité ! ¯\\_(ツ)_/¯");
                jeu=false;
            }
            else if(nbDame(plateau, 1)==1 && (nbDame(plateau, 2)==1 && nbPion(plateau, 2)==2)){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Egalité ! ¯\\_(ツ)_/¯");
                jeu=false;
            }
            else if(nbDame(plateau, 2)==1 && (nbDame(plateau, 1)==1 && nbPion(plateau, 1)==2)){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Egalité ! ¯\\_(ツ)_/¯");
                jeu=false;
            }



        } while ( jeu);
    }
//Affiche le pseudo du joueur en cours
    public static void affichePseudo(int joueur){
        switch (joueur) {
            case 1:
                System.out.println("C'est à "+Menu.pseudo[0]+" de jouer !");
                System.out.println();
                System.out.println();
                break;
            case 2:
                System.out.println("C'est à "+Menu.pseudo[1]+" de jouer !");
                System.out.println();
                System.out.println();
                break;
        
            default:
                break;
        }
    }


//Renvoie le nombre de Dame d'un joueur
    public static int nbDame(int[][]plateau,int joueur){
        int nbDame=0;
        switch (joueur) {
            case 1:
                for (int i = 0; i < plateau.length; i++) {
                    for (int j = 0; j < plateau.length; j++) {
                        if(plateau[i][j]==11){
                            nbDame++;
                        }
                
                    }
                }
                break;
            case 2:
                for (int i = 0; i < plateau.length; i++) {
                    for (int j = 0; j < plateau.length; j++) {
                        if(plateau[i][j]==22){
                            nbDame++;
                        }
                
                    }
                }
                break;
            default:
                break;
        }
        return nbDame;
    }

//Renvoie le nombre de pion d'un joueur
    public static int nbPion(int[][]plateau,int joueur){
        int nPion=0;
        switch (joueur) {
            case 1:
                for (int i = 0; i < plateau.length; i++) {
                    for (int j = 0; j < plateau.length; j++) {
                        if(plateau[i][j]==1){
                            nPion++;
                        }
                
                    }
                }
                break;
            case 2:
                for (int i = 0; i < plateau.length; i++) {
                    for (int j = 0; j < plateau.length; j++) {
                        if(plateau[i][j]==2){
                            nPion++;
                        }
                
                    }
                }
                break;
            default:
                break;
        }
        return nPion;
    }

//Fonction qui permet de faire saisie et qui force le joueur lorsqu'il y a des prises obligatoire possible et si apres la prise il peut continuer a prendre 
    public static int coupForce(int[][]plateau,int joueur ){
        
        boolean peutContinuer =true;
        int etatDuDeplacement=-5;
        int x =0,y=0,xa=0,ya=0;
        String coord="";
        List<int[]> coupForcé=listeCoupForce(plateau, joueur);
        do{

        
            do{
                System.out.println("Entrez X et Y de départ comme ceci XY : ");
                coord = sc.nextLine();

                if (coord.length() == 2 && Character.isDigit(coord.charAt(0)) && Character.isDigit(coord.charAt(1))) {
                    x = Character.getNumericValue(coord.charAt(0));
                    y = Character.getNumericValue(coord.charAt(1));
                } 
                else{
                            System.out.println("Saisie incorrecte veuillez réessayer !");
                        } 
                        
            }while(!estAllie(plateau, joueur, x, y) || !peutBouger(plateau, joueur,x, y) );

            if(!coupForcé.isEmpty() ){
                
                for (int i =0;i<coupForcé.size() && peutContinuer;i++) {
                    if(coupForcé.get(i)[0]==x && coupForcé.get(i)[1]==y){
                        peutContinuer=false;
                    }

                }

                if (peutContinuer) {
                    System.out.println("Attention vous devez prendre des pions adverse !");
                    
                }
                
                
            }
        }while(peutContinuer && !coupForcé.isEmpty());
        if(estVide(plateau, x, y) || !estAllie(plateau, joueur, x, y)){
            System.out.println("Attention vous devez choisir un pion allié !");
            etatDuDeplacement=coupForce(plateau,joueur);
        }
        else{

            do{
                    System.out.println("Entrez X et Y d'arrivé comme ceci XY : ");
                    coord = sc.nextLine();

                    if (coord.length() == 2 && Character.isDigit(coord.charAt(0)) && Character.isDigit(coord.charAt(1))) {
                        xa = Character.getNumericValue(coord.charAt(0));
                        ya = Character.getNumericValue(coord.charAt(1));
                    }
                    else{
                        System.out.println("Saisie incorrecte veuillez réessayer !");
                    } 
                    
                    
                    if(estDame(plateau, joueur, x, y)){
                        etatDuDeplacement=deplacementDame(plateau, joueur, x, y, xa, ya);
                    }
                    else{
                        etatDuDeplacement= deplacementPion(plateau, joueur, x,y,xa,ya);
                    }
                    
            }while((!peutContinuer && coupForcé.isEmpty()) || etatDuDeplacement==-1 || (!peutContinuer && ((x-xa)!=2 && joueur==2 && etatDuDeplacement!=1)&& etatDuDeplacement!=2 && etatDuDeplacement!=0)||(!peutContinuer && ((x-xa)!=-2 && joueur==2 && etatDuDeplacement!=1)&& etatDuDeplacement!=2 && etatDuDeplacement!=0));
        }
        
       
        return etatDuDeplacement;
    }

//Fonction qui permet de savoir si une case contient une dame
    public static boolean estDame(int[][]plateau, int joueur, int x ,int y){
        Boolean estDame=false;
        switch (joueur) {
            case 1:
                if(plateau[x][y]==11){
                    estDame=true;
                }
                break;
            case 2:
                if(plateau[x][y]==22){
                    estDame=true;
                }
                break;
        
            default:
                break;
        }
        return estDame;
    }

    public static boolean movementValide(int[][]plateau,int x , int y) {
        return x >= 0 && x < plateau.length && y >= 0 && y < plateau[0].length;
    }


//Procédure pour calculer le nombre de pion restant pour chaque joueur

    public static void updateScore(int[][]plateau){
        score[0]=0;
        score[1]=0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if(plateau[i][j]==1 ||plateau[i][j]==11 ){
                    score[0]+=1;
                }
                else if (plateau[i][j]==2 ||plateau[i][j]==22 ){
                    score[1]+=1;
                }
            }
        }
    }


//Fonction permettant de savoir si un pion est libre de bouger 
    public static boolean peutBouger(int[][] plateau,int joueur,int x,int y){
        boolean peutbouger=false;
        for (int[] i :deplacements ) {
            if(movementValide(plateau, x+i[0], y+i[1])){
                if (estVide(plateau,  x+i[0], y+i[1])) {
                    peutbouger=true;
                }
                else if (verifEnnemiSimple(plateau, joueur, x+i[0], y+i[1])){
                    peutbouger=true;
                }
            }
        }

        return peutbouger;
    }


//Fonction pour créer un plateau vide
    public static int[][] creePlateau(int x, int y){
        int[][] tab=new int[x][y];
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                 
                    
                tab[ligne][col]=0;
                   
                
            }
        }
        return tab;
    }

//Procédure qui rempli le plateau avec les pions 
    public static void initPions(int[][] tab){
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                
                
                    if((ligne+col)%2!=0 && ligne <4){
    
                        tab[ligne][col]=2; 
                    
                    }
                    else if((ligne+col)%2!=0 && ligne >5){
                        
                        tab[ligne][col]=1;
                    
                    } 
            }
        }
        
    }

//Procedure qui affiche l'état actuel du plateau
    public static void afficheTableau(int[][] t){
        
        System.out.println("_"+ANSI_RED+"Y"+ANSI_RESET+" 0 1 2 3 4 5 6 7 8 9");
        System.out.println(ANSI_BLUE+"X"+ANSI_RESET+"|---------------------");
        
        for (int ligne = 0; ligne < t.length; ligne++) {

            System.out.print(ligne+"|");

            for (int col = 0; col < t.length; col++) {
                switch (t[ligne][col]) {
                    case 0:
                        if((ligne+col)%2==0){
                            System.out.print(" ■");
                        }
                        else{
                            System.out.print(ANSI_GREEN+" ■"+ANSI_RESET);
                        }
                        break;
                    case 1:
                        System.out.print(PION_BLEU);
                        break;
                    case 2:
                        System.out.print(PION_ROUGE);
                        break;
                    case 11:
                        System.out.print(DAME_BLEU);
                        break;
                    case 22:
                        System.out.print(DAME_ROUGE);
                        break;
                    
                    default:
                        
                        break;
                }

            }
            if(ligne==4){
                System.out.print("            Score Bleu  : "+ (20-score[1]));
            }
            if(ligne==5){
                System.out.print("            Score Rouge : "+ (20-score[0]));
            }
            System.out.println();
        }
        
    }

//fonction qui permet de déterminer si une case est vide
    public static boolean estVide(int[][] plateau, int x, int y){
        boolean estVide=false;
        if(plateau[x][y] == 0){
            estVide=true;
        }
        return estVide;
    }

//Fonction qui permet de savoir si un pion ennemi peut etre pris   
    public static boolean peutPrendre(int[][] plateau,int joueur,int xDepart ,int yDepart ,int xPrise,int yPrise){
        boolean peutPrendre=false;
        int x =xPrise+( xPrise-xDepart),y= yPrise+(yPrise-yDepart);


        if(movementValide(plateau, x, y)){
            if(estVide(plateau, x, y)){
                peutPrendre=true;
            }
        }

        return peutPrendre;

    }   
//Procedure qui vérifie si un pion peut devenir une dame
    public static void verifPassageDame(int[][] plateau,int pion,int x, int y){
        switch (pion) {
            case 1:
                if (x==0 && plateau[x][y]==1) {
                    plateau[x][y]=11;
                }
                break;
            case 2:
                if (x==9 && plateau[x][y]==2) {
                    plateau[x][y]=22;
                }
                break;
            default:
                break;
        }
    }

//Procedure permetant le déplacement d'un pion ou d'une dame et verifie par la meme occasion si celui peut devenir une dame
    public static void deplacer(int[][] plateau, int xDepart , int yDepart,int xArrive,int yArrive, boolean enDeplacement){
        updateScore(plateau);
        int pion = plateau[xDepart][yDepart];
        plateau[xDepart][yDepart]=0;
        plateau[xArrive][yArrive]=pion;
        if(!enDeplacement){
            verifPassageDame(plateau, pion, xArrive, yArrive);
        }
        
    }


    
//Fonction renvoyant une liste d'ennemie qui peuvent etre pris
    public static List<int[]> listeEnnemie(int[][] plateau,int joueur, int x , int y){
        List<int[]> listeEnnemi=new ArrayList<int[]>(); 
        int[] a= new int[2];       
        for (int[] i :deplacements ) {
            if(movementValide(plateau, x+i[0], y+i[1])){
                if(verifEnnemiSimple(plateau, joueur, x+i[0], y+i[1])){
                    if(peutPrendre(plateau, joueur, x, y, x+i[0], y+i[1])){
                        a[0]=x+i[0];
                        a[1]=y+i[1];
                        listeEnnemi.add(a);
                    }
                }
            }
        }



        return listeEnnemi;
    }
    public static List<int[]> listeEnnemieDame(int[][] plateau,int joueur, int x, int y) {
        

        boolean existeEnnemi=false;
        List<int[]> listeEnnemi=new ArrayList<int[]>(); 
        int[] a= new int[2]; 
        // Diagonale haut-gauche
        for (int ligne = x - 1, colonne = y - 1; ligne >= 0 && colonne >= 0 && !existeEnnemi; ligne--, colonne--) {
            
            if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne) && (movementValide(plateau, ligne-1, colonne-1)&& estVide(plateau,ligne-1,colonne-1) )){
                
                existeEnnemi =true;
                a[0]=ligne;
                a[1]=colonne;
                listeEnnemi.add(a);
                
            } 
        }

        // Diagonale haut-droite
        for (int ligne = x - 1, colonne = y + 1; ligne >= 0 && colonne < plateau[0].length && !existeEnnemi; ligne--, colonne++) {
            
            if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne) && (movementValide(plateau, ligne-1, colonne+1)&& estVide(plateau,ligne-1,colonne+1) )){
                
                existeEnnemi =true;
                a[0]=ligne;
                a[1]=colonne;
                listeEnnemi.add(a);
                
            }
            
        }

        // Diagonale bas-gauche
        for (int ligne = x + 1, colonne = y - 1; ligne < plateau.length && colonne >= 0 && !existeEnnemi; ligne++, colonne--) {
            if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne) && (movementValide(plateau, ligne+1, colonne-1)&& estVide(plateau,ligne+1,colonne-1) )){

            
                existeEnnemi =true;
                a[0]=ligne;
                a[1]=colonne;
                listeEnnemi.add(a);
                
            }
           
        }

        // Diagonale bas-droite
        for (int ligne = x + 1, colonne = y + 1; ligne < plateau.length && colonne < plateau[0].length && !existeEnnemi; ligne++, colonne++) {
            
           if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne)&& (movementValide(plateau, ligne+1, colonne+1)&& estVide(plateau,ligne+1,colonne+1) )){
                
                existeEnnemi =true;
                a[0]=ligne;
                a[1]=colonne;
                listeEnnemi.add(a);
                
            }
            
        }

        return listeEnnemi;
    }
   
/*Verifie chaque pion et renvoie leurs coordonées si ils peuvent prendre un pion renvoie une liste vide si il n'y pas de prise possible */
    public static List<int[]> listeCoupForce(int[][]plateau,int joueur){
        List<int[]> listeCoup=new ArrayList<int[]>();
        for (int x = 0; x < plateau.length; x++) {
            for (int y = 0; y < plateau[x].length; y++) {
                if(estAllie(plateau, joueur, x, y) ){
                    if(estDame(plateau, joueur, x, y)){
                        if(!listeEnnemieDame(plateau, joueur, x, y).isEmpty()){
                            int[] coordonnee={x,y};
                            listeCoup.add(coordonnee);
                        }
                    }
                    else{
                        if( !listeEnnemie(plateau, joueur, x, y).isEmpty()){
                        
                            int[] coordonnee={x,y};
                            listeCoup.add(coordonnee);

                        }
                    }
                }
            }
            
        
           
        }

        return listeCoup;
    }
//Fonction qui verifie si sur une case se trouve un pion ennemie
    public static boolean verifEnnemiSimple(int[][] plateau,int joueur, int x , int y){
        boolean existeEnnemi=false;
        switch (joueur) {
            
            case 1:
                if(plateau[x][y]==2 || plateau[x][y]==22 ){
                    existeEnnemi=true;
                }
                break;
            case 2:
                if(plateau[x][y]==1 || plateau[x][y]==11 ){
                    existeEnnemi=true; 
                }
                break;
            default:
                break;
        }

        return existeEnnemi;
    }


//Fonction qui verifie si dans les diagonales de la case d'arrivé se trouvent des ennemies    
    public static boolean verifEnnemi(int[][] plateau,int joueur, int x , int y){
        

        boolean existeEnnemi=false;
        // Diagonale haut-gauche
        
            
            if((movementValide(plateau, x-1, y-1) &&verifEnnemiSimple(plateau, joueur, x-1, y-1) && !estVide(plateau,x-1, y-1) && peutPrendre(plateau,joueur,x,y,x-1, y-1) )){
                
                existeEnnemi =true;
                
            } 
        

        // Diagonale haut-droite
        
            
            if((movementValide(plateau, x-1, y+1)&& verifEnnemiSimple(plateau, joueur, x-1, y+1) && !estVide(plateau,x-1, y+1) &&  peutPrendre(plateau,joueur,x,y,x-1, y+1) )){
                
                existeEnnemi =true;
                
            }
            
        

        // Diagonale bas-gauche
       
            if((movementValide(plateau, x+1, y-1)&& verifEnnemiSimple(plateau, joueur, x+1, y-1) && !estVide(plateau,x+1, y-1) &&  peutPrendre(plateau,joueur,x,y,x+1, y-1) )){

            
                existeEnnemi =true;
                
            }
           
        

        // Diagonale bas-droite
        
            
           if((movementValide(plateau, x+1, y+1)&&verifEnnemiSimple(plateau, joueur, x+1, y+1) && !estVide(plateau,x+1, y+1)&&  peutPrendre(plateau,joueur,x,y,x+1, y+1) )){
                
                existeEnnemi =true;
                
            }
            
        
        return existeEnnemi;
        
    }

    
    
//Procedure pour prendre un ennemie 
    public static void priseEnnemi(int[][] plateau,int x,int y){
        plateau[x][y]=0;
        updateScore(plateau);
    }

//Fonction qui renvoie vrai si la prise est une prise en arriere
    public static boolean estPriseArriere(int[][] plateau, int joueur, int x,int y, int xa, int ya){
        boolean estPriseArriere=false;
        
        switch (joueur) {
            case 1:
                if((xa-x)>0){
                    estPriseArriere=true;
                }
                break;
            case 2:
                
                if((xa-x)<0){
                    estPriseArriere=true;
                }
                break;
            default:
                break;
        }




        return estPriseArriere;
    }


/*Renvoie 0 si le déplacement est fini, 1 si le déplacement peut continuer (rafle) ,2 si la prise est en arriere et -1 si le déplacement est impossible*/
    public static int deplacementPion(int[][] plateau,int joueur,int xDepart,int yDepart, int xArrive , int yArrive){
        
        int nbCase=Math.abs(xArrive - xDepart);
        int etatDeplacement=-2;  

        if(joueur==1){
            
                switch (nbCase) {
                    case 1:
                        if(verifEnnemi(plateau, joueur, xDepart, yDepart)){
                            System.out.println("Attention vous devez prendre un pion !");
                            etatDeplacement=-1;
                        }
                        else{
                            if(estVide(plateau, xArrive, yArrive)){
                                if ((xArrive==xDepart-1 && yArrive==yDepart-1)||(xArrive==xDepart-1 && yArrive==yDepart+1)) {//verifier que le déplacement est en diagonale de 1
                                    deplacer(plateau,xDepart,yDepart,xArrive,yArrive,false);
                                    etatDeplacement=0;
                                }
                                else{
                                    System.out.println("Attention le déplacement n'est pas en diagonale !");
                                    etatDeplacement=-1;

                                }
                            }
                            else{
                                System.out.println("Attention la case d'arrivé n'est pas vide !");
                                etatDeplacement=-1;
                            }
                        }
                        break;
                    

                    case 2:
                        
                        int xEnnemi= xDepart+(xArrive-xDepart)/2 , yEnnemi=yDepart+(yArrive-yDepart)/2;
                        if(verifEnnemiSimple(plateau, joueur, xEnnemi, yEnnemi)){
                            if(peutPrendre(plateau, joueur, xDepart, yDepart,xEnnemi , yEnnemi)){
                                priseEnnemi(plateau, xEnnemi, yEnnemi);
                                
                                
                        
                                if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,true);
                                    afficheTableau(plateau);
                                    System.out.println("Vous pouvez continuer de prendre des pions !");
                                    etatDeplacement=1;
                                    
                                }
                                else if(estPriseArriere(plateau, joueur, xDepart, yDepart, xArrive, yArrive)){
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,false);
                                    etatDeplacement=2;
                                }
                                else{
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,false);
                                    etatDeplacement=0;
                                }
                                
                            }
                        }
                        else{
                            System.out.println("Attention il n'y pas de pion ennemie a prendre !");
                            etatDeplacement =-1;
                        }
                        
                        break;
                        
                    default:
                        
                        break;
                }
                
                
                          
        }
        else if(joueur==2){

            
                    switch (nbCase) {
                        case 1:
                            if(verifEnnemi(plateau, joueur, xDepart, yDepart)){
                                System.out.println("Attention vous devez prendre un pion !");
                                etatDeplacement=-1;
                            }
                            else{
                                if(estVide(plateau, xArrive, yArrive)){
                                    if ((xArrive==xDepart+1 && yArrive==yDepart-1)||(xArrive==xDepart+1 && yArrive==yDepart+1)) {//verifier que le déplacement est en diagonale de 1
                                        deplacer(plateau,xDepart,yDepart,xArrive,yArrive,false);
                                        etatDeplacement=0;
                                    }
                                    else{
                                        System.out.println("Attention le déplacement n'est pas en diagonale !");
                                        etatDeplacement=-1;

                                    }
                                }
                                else{
                                    System.out.println("Attention la case d'arrivé n'est pas vide !");
                                    etatDeplacement=-1;
                                }
                            }
                            break;
                        

                        case 2:
                        
                        int xEnnemi= xDepart+(xArrive-xDepart)/2 , yEnnemi=yDepart+(yArrive-yDepart)/2;
                        if(verifEnnemiSimple(plateau, joueur, xEnnemi, yEnnemi)){
                            if(peutPrendre(plateau, joueur, xDepart, yDepart,xEnnemi , yEnnemi)){
                                priseEnnemi(plateau, xEnnemi, yEnnemi);
                                
                                
                        
                                if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,true);
                                    afficheTableau(plateau);
                                    System.out.println("Vous pouvez continuer de prendre des pions !");
                                    etatDeplacement=1;
                                    
                                }
                                else if(estPriseArriere(plateau, joueur, xDepart, yDepart, xArrive, yArrive)){
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,false);
                                    etatDeplacement=2;
                                }
                                else{
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,false);
                                    etatDeplacement=0;
                                }
                                
                            }
                        }
                        else{
                            System.out.println("Attention il n'y pas de pion ennemie a prendre !");
                            etatDeplacement =-1;
                        }
                        
                        break;
                        
                        default:
                            
                            break;
                    }
                    
                    
                                
                }
        
        return etatDeplacement;
    }



//Fonction qui vérifie si un pion est un pion allié   
    public static boolean estAllie(int[][] plateau,int joueur,int x ,int y){
        boolean estAllie=false;

        if(joueur==1){
            if(plateau[x][y]==1 ||plateau[x][y]==11  ){
                estAllie=true;
            }
        }
        else{
            if(plateau[x][y]==2 ||plateau[x][y]==22  ){
                estAllie=true;
            }
        }

        return estAllie;
    }

/*Renvoie 0 si le déplacement de la dame est fini, 1 si le déplacement peut continuer (rafle) et -1 si le déplacement est impossible */
    public static int deplacementDame(int[][] plateau,int joueur, int xd ,int yd, int xa, int ya)
    {
        int deplacementX = xa-xd,deplacementY=ya-yd;
        int gaucheDroite=0,hautBas=0;
        int etat=-2;
        int x=xd,y=yd;
        boolean unePriseAeteFaite=false;
        boolean deplacementPeutContinuer=true;
        
        if(estVide(plateau, xa, ya) ){
                if(deplacementX<0){
                    hautBas =-1;
                    do{
                        x--;
                        if(deplacementY<0){
                            y--;
                            gaucheDroite=-1;
                        }
                        else{
                            y++;
                            gaucheDroite=1;
                        }
                        
                        

                        if (!estAllie(plateau, joueur, x, y) && !estVide(plateau, x, y) && !peutPrendre(plateau, joueur, x-hautBas, y-gaucheDroite, x, y) ){
                            deplacementPeutContinuer=false;
                            System.out.println("Attention il est impossible de faire ce coup !");
                            etat=-1;
                        }
                        else if(!estAllie(plateau, joueur, x, y) && !estVide(plateau, x, y) && peutPrendre(plateau, joueur, x-hautBas, y-gaucheDroite, x, y)){
                            priseEnnemi(plateau, x, y);
                            unePriseAeteFaite=true;
                            
                        }
                        
                        
                    }while(x!=xa && deplacementPeutContinuer);
                    if(deplacementPeutContinuer){
                        
                        if(verifEnnemiDame(plateau, joueur, xd, yd) && !unePriseAeteFaite){
                            etat=-1;
                            System.out.println("Attention vous devez prendre un pion !");
                            
                        }
                        else if(verifEnnemiDame(plateau, joueur, xa, ya) && unePriseAeteFaite){
                            deplacer(plateau, xd, yd, xa, ya,false);
                            etat=1;
                            System.out.println("Vous pouvez continuer de prendre des pions !");
                                                   }
                        else{
                            deplacer(plateau, xd, yd, xa, ya,false);
                            etat=0;
                            
                        }
                    }
                }
                else{
                    hautBas=1;
                    do{
                        x++;
                        if(deplacementY<0){
                            y--;
                            gaucheDroite=-1;
                        }
                        else{
                            y++;
                            gaucheDroite=1;
                        }
                       
                        if (!estAllie(plateau, joueur, x, y) && !estVide(plateau, x, y) && !peutPrendre(plateau, joueur, x-hautBas, y-gaucheDroite, x, y) ){
                            deplacementPeutContinuer=false;
                            System.out.println("Attention il est impossible de faire ce coup !");
                            etat=-1;
                        }
                        else if(!estAllie(plateau, joueur, x, y) && !estVide(plateau, x, y) && peutPrendre(plateau, joueur, x-hautBas, y-gaucheDroite, x, y)){
                            priseEnnemi(plateau, x, y);
                            unePriseAeteFaite=true;
                            
                        }
                        
                        
                    }while(x!=xa && deplacementPeutContinuer);
                    if(deplacementPeutContinuer){
                        
                        if(verifEnnemiDame(plateau, joueur, xd, yd) && !unePriseAeteFaite){
                            
                            etat=-1;
                            System.out.println("Attention vous devez prendre un pion !");
                            
                        }
                        else if(verifEnnemiDame(plateau, joueur, xa, ya) && unePriseAeteFaite){
                            
                            deplacer(plateau, xd, yd, xa, ya,false);
                            etat=1;
                            System.out.println("Vous pouvez continuer de prendre des pions !");
                                                   }
                        else{
                            deplacer(plateau, xd, yd, xa, ya,false);
                            etat=0;
                            
                        }
                    }
                }
        }
        else{
            System.out.println("Attention déplacement impossible !");
            etat=-1;
        }

        return etat;
    }
   
//Fonction qui verifie chaque diagonale de la dame et renvoie vrai si il y a un ennemi 

    public static Boolean verifEnnemiDame(int[][] plateau,int joueur, int x, int y) {
        

        boolean existeEnnemi=false;

        // Diagonale haut-gauche
        for (int ligne = x - 1, colonne = y - 1; ligne >= 0 && colonne >= 0 && !existeEnnemi; ligne--, colonne--) {
            
            if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne) && (movementValide(plateau, ligne-1, colonne-1)&& estVide(plateau,ligne-1,colonne-1) )){
                
                existeEnnemi =true;
                
            } 
        }

        // Diagonale haut-droite
        for (int ligne = x - 1, colonne = y + 1; ligne >= 0 && colonne < plateau[0].length && !existeEnnemi; ligne--, colonne++) {
            
            if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne) && (movementValide(plateau, ligne-1, colonne+1)&& estVide(plateau,ligne-1,colonne+1) )){
                
                existeEnnemi =true;
                
            }
            
        }

        // Diagonale bas-gauche
        for (int ligne = x + 1, colonne = y - 1; ligne < plateau.length && colonne >= 0 && !existeEnnemi; ligne++, colonne--) {
            if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne) && (movementValide(plateau, ligne+1, colonne-1)&& estVide(plateau,ligne+1,colonne-1) )){

            
                existeEnnemi =true;
                
            }
           
        }

        // Diagonale bas-droite
        for (int ligne = x + 1, colonne = y + 1; ligne < plateau.length && colonne < plateau[0].length && !existeEnnemi; ligne++, colonne++) {
            
           if(verifEnnemiSimple(plateau, joueur, ligne, colonne) && !estVide(plateau,ligne,colonne)&& (movementValide(plateau, ligne+1, colonne+1)&& estVide(plateau,ligne+1,colonne+1) )){
                
                existeEnnemi =true;
                
            }
            
        }

        return existeEnnemi;
    }

    
}
