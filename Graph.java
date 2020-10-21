import java.util.*;


class Sommet {
    String nom;
    int couleur;
    boolean estSommetActif;
    ArrayList<Sommet> sommetPref;
    ArrayList<Sommet> sommetInf;


    public Sommet(String nom,boolean estSommetActif) {
        this.nom = nom;
        this.couleur = -2;
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
        System.out.println("Arrete : {"+ this.s1.nom + "," + this.s2.nom + "}" + " estActif : " + this.getEstActif());
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
            System.out.print(s.getNom() + " " + "Actif : " + s.getActif() + " ");
        }
        System.out.println();
    }

    public void getlistArrete() {
        for(Arrete a : this.listArretes) {
            a.AfficheArrete();
        }

    }
//Fonction qui récupère le premier sommet avec un degrés < k ET qui est encore actif
    public Sommet Sommettraiter(int k) {
        for(Sommet s : this.listSommets) {
            if(s.getDegree() < k && s.getActif() == true) {
                return s;
            }
        }
        return null;
    }

    public int nbSommetActif() {
        int cpt = 0;
        for(Sommet s : this.listSommets) {
            if(s.getActif()) {
                cpt++;
            }
        }
        return cpt;
    }



    public void colorier(int k) {
        if (nbSommetActif() > 1) {
            if (Sommettraiter(k) != null) {
                Sommet s = Sommettraiter(k);
                        //Le sommet est plus actif
                        s.noMoreActive();
                        // On désactive ses arrêtes
                        for (Arrete z : this.listArretes) {
                            if (z.s1.getNom() == s.nom || z.s2.getNom() == s.getNom()) {
                                z.estArreteActif = false;
                    }
                }
                this.colorier(k);
                        //IL FAUT METTRE LA COULEUR LA
                s.estSommetActif = true;
                ArrayList<Integer>  listCouleur = new ArrayList<Integer>();
                for(Sommet x : s.sommetPref) {
                    if(x.getActif()) {
                        listCouleur.add(x.getColor());
                        System.out.println(listCouleur);
                        for(int i = 0; i < k; i++) {
                            if(!(listCouleur.contains(i))) {
                                s.couleur = i;
                                System.out.println(s.nom + " " + s.sommetPref);
                                i = k;
                            }
                        }
                    }
                }
            }
            else {
                Sommet s = new Sommet();
                for(Sommet x : this.listSommets) {
                    if(x.getActif()) {
                        s = x;
                    }
                }
                //Le sommet est plus actif
                s.noMoreActive();
                // On désactive ses arrêtes
                for (Arrete z : this.listArretes) {
                    if (z.s1.getNom() == s.nom || z.s2.getNom() == s.getNom()) {
                        z.estArreteActif = false;
                    }
                }
                this.colorier(k);
                s.estSommetActif = true;
                ArrayList<Integer>  listCouleur = new ArrayList<Integer>();
                for(Sommet x : s.sommetPref) {
                    if(x.getActif()) {
                        listCouleur.add(x.getColor());
                        System.out.println(listCouleur);
                        System.out.println(listCouleur);
                        for(int i = 0; i < k; i++) {
                            if(!(listCouleur.contains(i))) {
                                s.couleur = i;
                                System.out.println(s.nom + " " + s.sommetPref);
                                i = k;
                            }
                        }
                        if(s.couleur == -2) {
                            s.couleur = -1;
                        }
                    }

                }
            }
        } else {
            for(Sommet x : this.listSommets) {
                x.afficheSommet();
                if(x.getActif()) {
                    x.couleur = 0;
                }
            }
            System.out.println("g finit");
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

        int k = 1;

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


        Sommet e = new Sommet("e",true);
        Sommet f = new Sommet("f",true);
        Sommet g = new Sommet("g",true);
        Sommet h = new Sommet("h",true);

        ArretePref EF = new ArretePref(e, f, true);
        ArretePref EH = new ArretePref(e, h, true);
        ArretePref FG = new ArretePref(f, g, true);
        ArretePref GH = new ArretePref(g, h, true);

        e.addSommetPref(f);
        e.addSommetPref(h);

        f.addSommetPref(g);
        f.addSommetPref(e);

        g.addSommetPref(h);
        g.addSommetPref(f);

        h.addSommetPref(e);
        h.addSommetPref(g);




        Graphe TEST2 = new Graphe();

        TEST2.listSommets.add(e);
        TEST2.listSommets.add(f);
        TEST2.listSommets.add(g);
        TEST2.listSommets.add(h);

        TEST2.listArretes.add(EF);
        TEST2.listArretes.add(EH);
        TEST2.listArretes.add(FG);
        TEST2.listArretes.add(GH);


        TEST1.colorier(k);
        TEST1.getlistArrete();
        TEST1.getlistSommets();
        for(Sommet s : TEST1.listSommets) {
            System.out.println("Couleur sommet : " + s.nom + " " + s.couleur);
        }

        TEST2.colorier(k);
        TEST2.getlistArrete();
        TEST2.getlistSommets();
        for(Sommet s : TEST2.listSommets) {
            System.out.println("Couleur sommet : " + s.nom + " " + s.couleur);
        }


    }
}