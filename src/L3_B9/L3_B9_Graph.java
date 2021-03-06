/**
 * L3_B9_Graph.java
 */

package L3_B9;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class L3_B9_Graph {
    private int nombreDeSommets;
    private int nombredArcs;
    private ArrayList<L3_B9_Arc> list = new ArrayList<L3_B9_Arc>();
    private int[][] matriceAdjacence;
    private int[][] matriceValeurs;

    public void setNombreDeSommets(int nombreDeSommets) {
        this.nombreDeSommets = nombreDeSommets;
    }

    public void setNombredArcs(int nombredArcs) {
        this.nombredArcs = nombredArcs;
    }

    public int getNombreDeSommets() {
        return nombreDeSommets;
    }

    public int getNombredArcs() {
        return nombredArcs;
    }

    private void setMatriceAdjacence() {
        matriceAdjacence = new int[nombreDeSommets][nombreDeSommets];
        for (L3_B9_Arc a : list) {
            matriceAdjacence[a.getOrigine()][a.getDestination()] = 1;
        }
    }

    public void printMatriceAdjacence() {
        setMatriceAdjacence();

        System.out.println("Matrice d'adjacence");
        for (int i = 0; i < nombreDeSommets; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int i = 0; i < nombreDeSommets; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < nombreDeSommets; j++) {
                System.out.print(matriceAdjacence[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void setMatriceValeurs() {
        matriceValeurs = new int[nombreDeSommets][nombreDeSommets];
        for (int i = 0; i < nombreDeSommets; i++)
            for (int j = 0; j < nombreDeSommets; j++)
                matriceValeurs[i][j] = -1;

        for (L3_B9_Arc a : list) {
            matriceValeurs[a.getOrigine()][a.getDestination()] = a.getValeur();
        }
    }

    public void printMatriceValeurs() {
        setMatriceValeurs();

        System.out.println("Matrice des valeurs");
        for (int i = 0; i < nombreDeSommets; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int i = 0; i < nombreDeSommets; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < nombreDeSommets; j++) {
                if (matriceValeurs[i][j] == -1) {
                    System.out.print("*\t");
                } else {
                    System.out.print(matriceValeurs[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String output = "";
        output += nombreDeSommets + " sommets" + "\n"
                + nombredArcs + " arcs";
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.print("Quel graphe voulez-vous traiter ? (0 pour quitter) ");
            choix = sc.nextInt();
            readGraph(choix);
        } while (choix != 0);
    }

    public static void readGraph(int number) {
        String pathname = "L3-B9-" + number + ".txt";

        try {
            // Open file
            File f = new File(pathname);
            Scanner sc = new Scanner(f);

            // Declare a graph
            L3_B9_Graph g1 = new L3_B9_Graph();
            g1.setNombreDeSommets(sc.nextInt());
            g1.setNombredArcs(sc.nextInt());

            // Output
            System.out.println("* Lecture du graphe sur fichier");
            System.out.println(g1.toString());

            // Go through the file
            while (sc.hasNextInt()) {
                int origine = sc.nextInt();
                int destination =  sc.nextInt();
                int valeur = sc.nextInt();
                System.out.println(origine + " -> " + destination + " = " + valeur);
                g1.list.add(new L3_B9_Arc(origine, destination, valeur));
            }

            // Affichage de la matrice d'adjacence
            System.out.println("* Représentation du graphe sous forme matricielle");
            g1.printMatriceAdjacence();

            // Affichage de la matrice des valeurs
            System.out.println();
            g1.printMatriceValeurs();

            // Détection de circuit


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}