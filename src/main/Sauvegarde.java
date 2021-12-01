package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jeu.Joueur;
import jeu.Plateau;
import utilitaire.Fonctions;

public class Sauvegarde {

	private Joueur joueur1;
	private Joueur joueur2;

	private final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd - hh-mm-ss");
	private final Date DEBUT = new Date();

	// les dossiers en majuscules
	private final String SAUVEGARDE = "./sauvegarde/";
	private final String HISTORIQUE = SAUVEGARDE + "historique/";
	private final String JOUEURS = SAUVEGARDE + "joueurs/";

	// les fichiers en minuscules
	private final String liste = HISTORIQUE + "  liste.txt";
	private static String fichierJoueur1;
	private static String fichierJoueur2;
	private String fichierHistorique;

	private final String EXTENSION = ".txt";

	public Sauvegarde() {

		// si le dossier n'existe pas il le crée
		new File(SAUVEGARDE).mkdir();
		new File(HISTORIQUE).mkdir();
		new File(JOUEURS).mkdir();

	}

	public void init(Plateau plateau) {
		this.joueur1 = plateau.getJoueurs()[0];
		this.joueur2 = plateau.getJoueurs()[1];

		Sauvegarde.fichierJoueur1 = JOUEURS + joueur1.getName() + EXTENSION;
		Sauvegarde.fichierJoueur2 = JOUEURS + joueur2.getName() + EXTENSION;
		this.fichierHistorique = HISTORIQUE + joueur1.getName() + "_vs_" + joueur2.getName();

		try {
			// si le fichier n'existe pas il le crée
			new File(liste).createNewFile();
			new File(fichierJoueur1).createNewFile();
			new File(fichierJoueur2).createNewFile();

			int partie = 1;
			// si le fichier existe déjà alors augmente le compteur
			while (!new File(fichierHistorique + "_partie_" + partie + EXTENSION).createNewFile()) {
				partie++;
			}
			fichierHistorique = fichierHistorique + "_partie_" + partie + EXTENSION;

			// ajoute une date à l'historique avec l'heure
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(liste, true)));
			out.println(joueur1.getName() + " vs " + joueur2.getName() + "     le " + DATE.format(DEBUT));
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			verifierFichier(fichierJoueur1);
			verifierFichier(fichierJoueur2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	private void verifierFichier(String fichier) throws IOException {

		// le fichier n'as que 4 champs
		// pour l'instant
		final int taille = 4;
		String[] tab = new String[taille];

		BufferedReader br = new BufferedReader(new FileReader(fichier));
		String str;
		int i = 0;

		// récupérer les lignes du fichier
		while ((str = br.readLine()) != null && i < taille) {
			tab[i] = str;
			i++;
		}

		// efface le fichier
		new FileWriter(fichier);

		// System.out.println(tab[0]);
		// System.out.println(tab[1]);
		// System.out.println(tab[2]);
		// System.out.println(tab[3]);

		// vérifie le contenus récupéré et le corrige si besoin
		if (tab[0] == null || !tab[0].contains("nombre de parties      : ")) {
			tab[0] = "nombre de parties      : 0";
		}
		if (tab[1] == null || !tab[1].contains("nombre de victoires    : ")) {
			tab[1] = "nombre de victoires    : 0";
		}
		if (tab[2] == null || !tab[2].contains("nombre de defaites     : ")) {
			tab[2] = "nombre de defaites     : 0";
		}
		if (tab[3] == null || !tab[3].contains("nombre de matchs nuls  : ")) {
			tab[3] = "nombre de matchs nuls  : 0";
		}

		br.close();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fichier, true)));

		// ajoute les lignes dans le fichier
		for (int j = 0; j < tab.length - 1; j++) {
			out.println(tab[j]);
		}
		out.print(tab[tab.length - 1]);
		out.close();

	}
	
	public static void gagner(Joueur joueur, Joueur joueurAdverse) throws Exception {

		victoire(joueur);
		defaite(joueurAdverse);

	}

	public static void abandon(Joueur joueur, Joueur joueurAdverse) throws Exception {

		defaite(joueur);
		victoire(joueurAdverse);

	}

	public static void nul(Joueur joueur, Joueur joueurAdverse) throws Exception {
		egalite(joueur);
		egalite(joueurAdverse);

	}

	public static void victoire(Joueur joueur) throws Exception {

		ArrayList<String> result;
		String fichier;
		if (fichierJoueur1.contains(joueur.getName())) {
			fichier = fichierJoueur1;
		} else {
			fichier = fichierJoueur2;
		}

		result = recuperation(fichier);
		
		//System.out.println(result);

		remplacerJoueur(fichier, result, new int[] { 1, 1, 0, 0 });

	}

	private static void defaite(Joueur joueur) throws Exception {
		ArrayList<String> result;
		String fichier;
		if (fichierJoueur1.contains(joueur.getName())) {
			fichier = fichierJoueur1;
		} else {
			fichier = fichierJoueur2;
		}

		result = recuperation(fichier);

		remplacerJoueur(fichier, result, new int[] { 1, 0, 1, 0 });

	}

	private static void egalite(Joueur joueur) throws Exception {
		ArrayList<String> result;
		String fichier;
		if (fichierJoueur1.contains(joueur.getName())) {
			fichier = fichierJoueur1;
		} else {
			fichier = fichierJoueur2;
		}

		result = recuperation(fichier);

		remplacerJoueur(fichier, result, new int[] { 1, 0, 0, 1 });

	}

	private static ArrayList<String> recuperation(String fichier) throws Exception {
		ArrayList<String> result = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(fichier));
		String str;
		while ((str = br.readLine()) != null) {
			result.add(str);
		}
		br.close();
		return result;
	}

	@SuppressWarnings("resource")
	private static void remplacerJoueur(String fichier, ArrayList<String> result, int[] ajout) throws Exception {
		// efface le fichier
		new FileWriter(fichier);

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fichier, true)));

		for (int i = 0; i < result.size(); i++) {
			
			if (i == result.size() - 1) {

				out.print(Fonctions.combine(result.get(i), ajout[i]));
			} else {

				out.println(Fonctions.combine(result.get(i), ajout[i]));
			}
		}
		out.close();
	}

	public void addHistorique(ArrayList<String> aList) throws Exception {
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fichierHistorique, true)));

		for(String value : aList) {
		out.println(value);	
		}
		out.println("----------------------------------------\n\n\n\n");
		
		out.close();
		
	}

}