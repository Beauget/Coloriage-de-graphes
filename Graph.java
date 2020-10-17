import java.util.*;


class Sommet {
    String nom;
    int couleur;
    boolean estSommetActif;
    ArrayList<Sommet> sommetPref;
    ArrayList<Sommet> sommetInf;


    public Sommet(String nom,boolean estSommetActif) {
        this.nom = nom;
        this.couleur = 0;
        this.estSommetActif = true;
        this.sommetInf = new ArrayList<Sommet>();
        this.sommetPref = new ArrayList<Sommet>();

    }

    public Sommet() {
    }

    public void afficheSommet() {
        System.out.println("Sommet : "+this.nom+" "+ this.couleur + " " + this.estSommetActif);
        System.out.println("Liste sommetInf : ");
        System.out.print("( ");
        for(Sommet s : this.sommetInf) {
            System.out.print(s.nom + " ");
        }
        System.out.println(") ");
        System.out.print("( ");
        System.out.println("Liste sommetPref : ");
        for(Sommet s : this.sommetPref) {
            System.out.print(s.nom + " ");

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

    public void addSommetPref(Sommet s) {
        this.sommetPref.add(s);
    }

    public void addSommetInf(Sommet s) {
        this.sommetInf.add(s);
    }

    public void noMoreActive() {
        this.estSommetActif = false;
    }

    public int getDegree() {
        int cpt = 0;
       for(Sommet s : this.sommetPref) {
           if(s.estSommetActif) {
               cpt++;
           }
       }
        return cpt;
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

class ArreteInf extends Arrete {
    final static int type = 0;

    public ArreteInf(Sommet s1, Sommet s2, boolean estArreteActif) {
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

    public Sommet minDegree(int k) {
        int res = k;
        Sommet temp = new Sommet();
        for(Sommet s : this.listSommets) {
            if(s.getDegree() < res) {
                res = s.getDegree();
                temp = s;
            }
        }
        if(res == k ) {
            System.out.println("Besoin de spill !");
        }
        return temp;
    }

    public void chaiting(int k) {
         Sommet aTraiter = this.minDegree(k);

         for(Sommet s : this.listSommets) {
             if(s == aTraiter) {

                 //Le sommet est plus actif
                 s.noMoreActive();

                 //On enlève ce sommet des différentes listes
                 for(Sommet x : this.listSommets) {
                     if(x.sommetPref.contains(s)) {
                         x.sommetPref.remove(s);
                     }
                     if(!s.sommetInf.isEmpty()) {
                         x.sommetInf.remove(s);
                     }
                 }
                 for(Arrete z : this.listArretes) {
                     if(z.s1.getNom() == s.nom || z.s2.getNom() == s.getNom()) {
                         z.estArreteActif = false;
                     }
                 }

                 //On lui attribue une couleur
                 for(Sommet i : s.sommetPref) {
                     s.couleur = 1 + (int)(Math.random() * ((k - 1) + 1));
                     if(i.couleur == s.couleur) {
                         s.couleur = 1 + (int) (Math.random() * ((k - 1) + 1));
                     }
                 }

             }
        }



    }



    public static void main(String[] args) {

        //Création du premier graphe de test (vue en cours)

        //Sommet
        Sommet x = new Sommet("x",true);
        Sommet y = new Sommet("y",true);
        Sommet z = new Sommet("z",true);
        Sommet u = new Sommet("u",true);
        Sommet t = new Sommet("t",true);
        Sommet v = new Sommet("v",true);

        //ArretePref
        ArretePref XY = new ArretePref(x,y,true);
        ArretePref XV = new ArretePref(x,v,true);
        ArretePref XU = new ArretePref(x,u,true);

        ArretePref YT = new ArretePref(y,t,true);
        ArretePref YU = new ArretePref(y,u,true);

        ArretePref ZV = new ArretePref(z,v,true);

        ArretePref VT = new ArretePref(v,t,true);

        //ArreteInf
        ArreteInf TU = new ArreteInf(t,u,true);

        //SommetPref
        x.addSommetPref(v);
        x.addSommetPref(y);
        x.addSommetPref(u);

        y.addSommetPref(x);
        y.addSommetPref(t);
        y.addSommetPref(u);

        z.addSommetPref(v);

        u.addSommetPref(x);
        u.addSommetPref(y);

        t.addSommetPref(y);
        t.addSommetPref(v);

        v.addSommetPref(z);
        v.addSommetPref(x);
        v.addSommetPref(t);

        //SommetInf
        u.addSommetInf(t);
        t.addSommetInf(u);


        Graphe TEST1 = new Graphe();

        int k = 3;

        TEST1.listSommets.add(x);
        TEST1.listSommets.add(y);
        TEST1.listSommets.add(z);
        TEST1.listSommets.add(u);
        TEST1.listSommets.add(t);
        TEST1.listSommets.add(v);

        TEST1.listArretes.add(XY);
        TEST1.listArretes.add(XV);
        TEST1.listArretes.add(XU);
        TEST1.listArretes.add(YT);
        TEST1.listArretes.add(YU);
        TEST1.listArretes.add(ZV);
        TEST1.listArretes.add(TU);
        TEST1.listArretes.add(VT);


        TEST1.chaiting(k);
        z.afficheSommet();
        System.out.println(ZV.getEstActif());



    }
}