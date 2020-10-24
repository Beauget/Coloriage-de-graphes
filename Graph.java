import java.util.*;

/* Beauget Denis et Hebiret Hayaat - Coloriage de graphes Compilation et Interprétation */
/* Utilisation : javac *.java => java Graphe */

class Sommet {
    String nom;
    int couleur;
    boolean estSommetActif;
    ArrayList<Sommet> sommetPref;
    ArrayList<Sommet> sommetInf;
    

    public Sommet() {}
    
    public Sommet(String nom) {
        this.nom = nom;
        this.couleur = -2;
        this.estSommetActif = true;
        this.sommetInf = new ArrayList<Sommet>();
        this.sommetPref = new ArrayList<Sommet>();

    }
    
    public Sommet(String nom,boolean estSommetActif) {
        this.nom = nom;
        this.couleur = -2;
        this.estSommetActif = estSommetActif;
        this.sommetInf = new ArrayList<Sommet>();
        this.sommetPref = new ArrayList<Sommet>();

    }
    
    public String toString() {
    	String rslt = new String();
    	
    	rslt+= ("Sommet : "+this.nom+" "+ this.couleur + " " + this.estSommetActif+ "\n");
        rslt+=("Liste sommetInf : ( ");
        
        for(Sommet s : this.sommetInf) {rslt+=(s.nom + " , ");}
        rslt+=(") \n");

        rslt+=("Liste sommetPref : ( ");

        for(Sommet s : this.sommetPref) {rslt+=(s.nom + " , ");}
        rslt+=(") \n");
        
        return rslt;
    }
    
    public String toString2() {
    	String rslt = (this.nom + " " + "Actif : " + this.estSommetActif );
    	return rslt;
    }
    
    public String getNom() {return this.nom;}

    public int getColor() {return this.couleur;}

    public boolean getActif() {return this.estSommetActif;}
    
    public ArrayList<Sommet> getSommetPref() {return this.sommetPref;}
    public ArrayList<Sommet> getSommetInf(){return this.sommetInf;}
    
    public void addSommetPref(Sommet s) {this.sommetPref.add(s);}
    public void addSommetInf(Sommet s) {this.sommetInf.add(s);}
 

    public void noMoreActive() {this.estSommetActif = false;}

    //verifie les voisins pref ET actif
    public int getDegree() {
        int cpt = 0;
       for(Sommet s : this.sommetPref) {
           if(s.estSommetActif) {
               cpt++;
           }}
        return cpt;}
    
    public boolean equals(Sommet s) {
    	return this.nom.equals(s.getNom());
    } 
  
}


abstract class Arrete {
    Sommet s1;
    Sommet s2;
    boolean estArreteActif;
    
    public Arrete() {};

    public Arrete(Sommet s1,Sommet s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.estArreteActif = true;
        }
    
    public Arrete(Sommet s1,Sommet s2, boolean bool) {
        this.s1 = s1;
        this.s2 = s2;
        this.estArreteActif = bool;
        }

    public Sommet  getS1() {return this.s1;}

    public Sommet  getS2() { return this.s2;}
    
    public String  toStringS1() {return s1.toString();}

    public String  toStringS2() { return s2.toString();}

    public boolean getEstActif() {return this.estArreteActif;}

    public String toString() {
        String rslt = ("Arrete : {"+ this.s1.nom + "," + this.s2.nom + "}" + " estActif : " + this.getEstActif());
        return rslt;
    }
    
    public boolean equals(Arrete a) {
        if (this.s1.equals(a.getS1()) & this.s2.equals(a.getS2()))
            return true;
        
        if (this.s2.equals(a.getS1()) & this.s1.equals(a.getS2()))
            return true;
        
        else 
            return false;
                    
    }
}



class ArretePref extends Arrete {
    final static int type = 1;

    public ArretePref(Sommet s1, Sommet s2) {
        super(s1, s2);
    }    
    
    public ArretePref(Sommet s1, Sommet s2, boolean estArreteActif) {
        super(s1, s2, estArreteActif);
    }
}



class ArreteInf extends Arrete {
    final static int type = 0;
    
    public ArreteInf(Sommet s1, Sommet s2) {
        super(s1, s2);
    }

    public ArreteInf(Sommet s1, Sommet s2, boolean estArreteActif) {
        super(s1, s2, estArreteActif);
    }
    
}



class Graphe {
   ArrayList<Sommet> listSommets;
   ArrayList<Arrete> listArretes;

   public Graphe() {
       this.listSommets = new ArrayList<Sommet>();
       this.listArretes = new ArrayList<Arrete>();}

   public void getlistSommets() {
       String rslt = new String();
       
       for(Sommet s : this.listSommets) {rslt+=s.toString2()+" , ";}
       rslt+="\n";
       System.out.print(rslt);
   }

   public void getlistArrete() {
       for(Arrete a : this.listArretes) { System.out.println(a.toString());}
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
   
   public void ajoutSommet(Sommet s) {
       this.listSommets.add(s);
       
       for(Sommet x : s.getSommetInf()) { //ajoute les sommetsInf dans la liste
           if(this.listSommets.contains(x)==false){listSommets.add(x);}}
       
       for(Sommet x : s.getSommetPref()) {//ajoute les sommetsPref dans la liste
           if(this.listSommets.contains(x)==false){listSommets.add(x);}} 
       
       for(Sommet x : s.getSommetInf()) { //ajoute les ArretesInf dans la liste
           Arrete a = new ArreteInf(s,x);
           Arrete b = new ArreteInf(x,s);
           if((this.listArretes.contains(a) & this.listArretes.contains(b)) ==false){listArretes.add(a);}}
       
       for(Sommet x : s.getSommetPref()) {//ajoute les ArrtesPref dans la liste
           Arrete a = new ArretePref(s,x);
           Arrete b = new ArretePref(x,s);
           if((this.listArretes.contains(a) & this.listArretes.contains(b)) ==false){listArretes.add(a);}}
       
       
    }
   
   public void desactiverArretes(Sommet s) {
       for (Arrete z : this.listArretes) {
           if (z.s1.getNom() == s.nom || z.s2.getNom() == s.getNom()) {
               z.estArreteActif = false;
                                        }
       }
   }
   
    public void colorisationSommet(Sommet s , ArrayList<Integer>  listCouleur,int k ) {
        for(Sommet x : s.sommetPref) {
            if(x.getActif()) {
                listCouleur.add(x.getColor());
                for(int i = 0; i < k; i++) {
                    if(!(listCouleur.contains(i))) {
                        s.couleur = i;
                        i = k; //on force la sortie de la boucle
            }}}}
            //Si le sommet n'a pas de voisin actif
            if (listCouleur.isEmpty()){
                Random rand = new Random();
                s.couleur = rand.nextInt(k);;
            }
            //si le sommet = -2 et que la listeCouleur n'est pas vide
            if (s.getColor()==-2)
                s.couleur = -1;
     }

    public Sommet premierSommetActif() {
        for(Sommet x : this.listSommets) {
            if(x.getActif()) 
                return x;
            }
        return null;
     }


   public void colorier(int k) {
       if (nbSommetActif() > 1) {
           if (Sommettraiter(k) != null) {
               Sommet s = Sommettraiter(k);
               s.noMoreActive();//Le sommet est plus actif
               this.desactiverArretes(s); // On désactive ses arrêtes
               
               this.colorier(k);
               
               //PHASE DE RECOLORISATION
               s.estSommetActif = true;
               ArrayList<Integer>  listCouleur = new ArrayList<Integer>();
               this.colorisationSommet(s, listCouleur,k); //affecte une couleur au sommet qui est différente de ses voisins
           }
           
          else {
               Sommet s = this.premierSommetActif();
               s.noMoreActive();
               this.desactiverArretes(s);
               
               this.colorier(k);
               
               s.estSommetActif = true;
               ArrayList<Integer>  listCouleur = new ArrayList<Integer>();
               this.colorisationSommet(s, listCouleur, k);
               
               if(s.couleur == -2) {
                           s.couleur = -1;
                       }
               }}

               
           
        else {
            Sommet s = this.premierSommetActif();
            Random rand = new Random();
            s.couleur = rand.nextInt(k);
       }
   }

public static void main(String[] args) {

        //Création du premier graphe de test (vue en cours)

        //Sommet
        Sommet x = new Sommet("x");
        Sommet y = new Sommet("y");
        Sommet z = new Sommet("z");
        Sommet u = new Sommet("u");
        Sommet t = new Sommet("t");
        Sommet v = new Sommet("v");

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

        

        TEST1.listSommets.add(x);
        TEST1.listSommets.add(y);
        TEST1.listSommets.add(z);
        TEST1.listSommets.add(u);
        TEST1.listSommets.add(t);
        TEST1.listSommets.add(v);



        Sommet e = new Sommet("e",true);
        Sommet f = new Sommet("f",true);
        Sommet g = new Sommet("g",true);
        Sommet h = new Sommet("h",true);


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

	int k = 3;

        
	System.out.println("Il y a ici 2 graphes tester, le premier graphe assez simple et l'autre un graphe en étoile, modifier la variable k (nombre de couleur) pour observer les resultats");
	System.out.println("Nombre de couleurs disponible (spill = -1) : " + k);
	System.out.println();


	TEST1.colorier(k);
	System.out.println("Graphe 1 : ");
	System.out.println(" ");
        for(Sommet s : TEST1.listSommets) {
            System.out.println("Couleur sommet : " + s.nom + " " + s.couleur);
        }
	System.out.println();
        
        TEST2.colorier(k);
	System.out.println("Graphe 2 : ");
	System.out.println();
        for(Sommet s : TEST2.listSommets) {
            System.out.println("Couleur sommet : " + s.nom + " " + s.couleur);
        }


    }
}
   
