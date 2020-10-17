import java.util.*;


class Sommet {
    String nom;
    int couleur;
    boolean estSommetActif;
    ArrayList<Sommet> sommetPref;
    ArrayList<Sommet> sommetInt;


    public Sommet(String nom,int couleur, boolean estSommetActif) {
        this.nom = nom;
        this.couleur = couleur;
        this.estSommetActif = true;
        this.sommetInt = new ArrayList<Sommet>();
        this.sommetPref = new ArrayList<Sommet>();

    }

    public void afficheSommet() {
        System.out.println("Sommet : "+this.nom+" "+ this.couleur + " " + this.estSommetActif);
        System.out.println("Liste sommetInt : ");
        System.out.print("( ");
        for(Sommet s : this.sommetInt) {
            System.out.print(s + " ");
        }
        System.out.println(") ");
        System.out.print("( ");
        System.out.println("Liste sommetPref : ");
        for(Sommet s : this.sommetPref) {
            System.out.print(s + " ");

        }
        System.out.println(") ");
    }

    public String getNom() {
        return this.nom;
    }

    public int getColor() {
        return this.couleur;
    }

    public boolean getActif() {
        return this.estSommetActif;
    }
}

abstract class Arrete {
    Sommet s1;
    Sommet s2;
    boolean estArreteActif;

    public Arrete(Sommet s1,Sommet s2,boolean estArreteActif) {
        this.s1 = s1;
        this.s2 = s2;
        this.estArreteActif = true;
    }

    public void  getS1() {
        s1.afficheSommet();
    }

    public void  getS2() { s2.afficheSommet();
    }

    public boolean getEstActif() {
        return this.estArreteActif;
    }

    public void AfficheArrete() {
        System.out.println("Arrete : {"+ this.s1.nom + "," + this.s2.nom + "}");
    }
}

class ArretePref extends Arrete {
    final static int type = 1;

    public ArretePref(Sommet s1, Sommet s2, boolean estArreteActif) {
        super(s1, s2, estArreteActif);
    }
}

class ArreteInt extends Arrete {
    final static int type = 0;

    public ArreteInt(Sommet s1, Sommet s2, boolean estArreteActif) {
        super(s1, s2, estArreteActif);
    }
}

 class Graphe {
    ArrayList<Sommet> listSommets;
    ArrayList<Arrete> listArretes;

    public Graphe() {
        this.listSommets = new ArrayList<Sommet>();
        this.listArretes = new ArrayList<Arrete>();

    }

    public void getlistSommets() {
        for(Sommet s : this.listSommets) {
            System.out.print(s.getNom() + " ");
        }
        System.out.println();
    }

    public void getlistArrete() {
        for(Arrete a : this.listArretes) {
            a.AfficheArrete();
        }

    }



    public static void main(String[] args) {

        System.out.println("Je marche");


    }
}