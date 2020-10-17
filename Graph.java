import java.util.*;


public class Sommet {
    String nom;
    int couleur;
    boolean estSommetActif;
    Arraylist<Sommet> sommetPref;
    Arraylist<Sommet> sommetInt;


    public Sommet(String nom,int couleur, boolean estSommetActif) {
        this.nom = nom;
        this.couleur = couleur;
        this.estSommetActif = true;
        this.sommetInt = new Arraylist<Sommet>;
        this.sommetPref = new Arraylist<Sommet>;

    }

    public void afficheSommet() {
        System.out.println("Sommet : "+this.nom+" "+ this.couleur + " " + this.estSommetActif);
        System.out.println("Liste sommetInt : ");
        System.out.print("( ");
        for(Sommet s : this.sommetInt) {
            System.out.print(this.s + " ");
        }
        System.out.println(") ");
        System.out.print("( ");
        System.out.println("Liste sommetPref : ");
        for(Sommet s : this.sommetPref) {
            System.out.print(this.s + " ");

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

public abstract class Arrete {
    Sommet s1;
    Sommet s2;
    boolean estArreteActif;

    public Arrete(Sommet s1,Sommet s2,boolean estArreteActif) {
        this.s1 = s1;
        this.s2 = s2;
        this.estArreteActif = true;
    }

    public Sommet getS1() {
        return s1.afficheSommet();
    }

    public Sommet getS2() {
        return s2.afficheSommet();
    }

    public boolean getEstActif() {
        return this.estArreteActif;
    }

    public void AfficheArrete() {
        System.out.println("Arrete : {"+ this.s1.nom + "," + this.s2.nom + "}");
    }
}

public class ArretePref extends Arrete {
    final static int type = 1;
}

public class ArreteInt extends Arrete {
    final static int type = 0;
}

public class Graphe {
    Arraylist<Sommet> listSommets;
    Arraylist<Arrete> listArretes;

    public Graphe() {
        this.listSommets = new Arraylist<Sommet>;
        this.listArretes = new Arraylist<Arrete>;

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