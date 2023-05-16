package Sudoku;

import java.util.Scanner;
import java.io.FileInputStream;
import iut.algo.*;

public class Grille
{	
	private static final int TAILLE_GRILLE = 9;
	
	private Nombre[][] grille;
	private int        niveau;
	private String     difficulte;

	public Grille ( int niveau )
	{
		this.grille = new Nombre [TAILLE_GRILLE] [TAILLE_GRILLE];

		this.lireNiveau ( niveau );

		while ( !this.estGrilleValide ( ) )
		{
			System.out.println ( this.afficherGrille ( this.grille ) );
			System.out.println ( "Veuillez saisir une ligne :"   );

			int ligne = Clavier.lire_int ( );

			char colonne;

			do
			{
				System.out.println ( "Veuillez saisir une colonne: " );
				colonne = Clavier.lire_char();
			}
			while ( ( colonne < 'A' || colonne > 'I' && colonne < 'a' || colonne > 'i') );


			colonne = Character.toUpperCase ( colonne );

			System.out.println ( "Veuillez saisir la valeur : "  );
			int valeur = Clavier.lire_int();

			this.insererGrille ( valeur, ligne, colonne );

		}

	}

	/** Fonction qui permet d'obtenir sous forme textuelle notre Grille de Sudoku
	 * @param grille Ce paramètre désigne le tableau à double dimensions d'entier qui contient notre grile
	 * @return retourne sous forme d'une chaine de caractère la grille pour l'afficher dans le terminal
	 */
	public String afficherGrille ( Nombre[][] grille )
	{
		String sRet = "-----------------------------\n" + 
		              "| / | A B C | D E F | G H I |\n" + 
		              "-----------------------------" ;

		int longeurGrille = grille.length;
		
		for ( int ligne = 0; ligne < longeurGrille; ligne ++ )
		{
			sRet += "\n";
			if ( ligne % 3 == 0 && ligne != 0 )
				sRet += "-----------------------------\n";

			sRet+= "| " + ( ligne + 1 ) + " ";
			
			for ( int colonne = 0; colonne < longeurGrille; colonne ++ )
			{
				if ( colonne == 0 )
					sRet += "|";
				
				if ( colonne % 3 == 0 && colonne != 0 )
					sRet += " |";
				if ( grille[ligne][colonne].getValeur() == 0 )
					sRet += " .";
				else
					sRet += " " + grille[ligne][colonne].getValeur();
				
				if ( colonne == longeurGrille - 1 )
					sRet += " |";
			}
			
		}
		sRet += "\n-----------------------------\n";

		return sRet;
	}

	/**
	* Fonction qui permet de lire
	* un niveau contenue dans un fichier txt
	* et qui le construit dans un tableau d'
	* entier à double dimension
	*/
	public void lireNiveau ( int niveau )
	{
		this.niveau = niveau;
		
		String fichier = "Sudoku/niveaux/niveau" + this.niveau + ".txt";
		String ligne;

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( fichier ) );

			this.difficulte = sc.nextLine ( );

			int cptLigne = 0;
			while ( sc.hasNextLine ( ) )
			{
				// Change de ligne si c'est un séparateur horizontal
				if ( cptLigne == 3 || cptLigne == 6 )
					ligne = sc.nextLine ( );

				ligne = sc.nextLine ( );

				int cptColonne = 0;
				for ( int cpt = 0; cpt < ligne.length( ); cpt++ )
				{
					// Passe à la prochaine itération si il s'agit d'un séparateur vertical
					if ( ligne.charAt(cpt) == '|' )
						continue;

					if ( ligne.charAt(cpt) == '0' )
						this.grille[cptLigne][cptColonne] = new Nombre ( Integer.parseInt("" + ligne.charAt ( cpt ) ), true );
					else
						this.grille[cptLigne][cptColonne] = new Nombre ( Integer.parseInt("" + ligne.charAt ( cpt ) ), false );
					
					cptColonne++;
				}	
				cptLigne++;
			}
			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	/**
	 * @return retourne un boolean qui indique si la grille est valide
	 */
	public boolean estGrilleValide ( )
	{
		// Vérifier chaque ligne
		for ( int cptLigne = 0; cptLigne < 9; cptLigne++ )
		{
			boolean[] chiffres = new boolean[9];
			for ( int cptColonne = 0; cptColonne < 9; cptColonne++ )
			{
				int valeur = this.grille[cptLigne][cptColonne].getValeur();
				if ( valeur < 1 || valeur > 9 || chiffres[valeur-1] )
					return false; // La grille est invalide

				chiffres[valeur-1] = true;
			}
		}
	
		// Vérifier chaque colonne
		for ( int cptColonne = 0; cptColonne < 9; cptColonne++ )
		{
			boolean[] chiffres = new boolean[9];
			for ( int cptLigne = 0; cptLigne < 9; cptLigne++ )
			{
				int valeur = this.grille[cptLigne][cptColonne].getValeur();
				if ( valeur < 1 || valeur > 9 || chiffres[valeur-1] )
					return false; // La grille est invalide
				
				chiffres[valeur-1] = true;
			}
		}
	
		// Vérifier chaque sous-grille
		for ( int sg = 0; sg < 9; sg++ )
		{
			boolean[] chiffres = new boolean[9];
			int i0 = ( sg / 3 ) * 3;
			int j0 = ( sg % 3 ) * 3;
			for ( int i = i0; i < i0+3; i++ )
			{
				for ( int j = j0; j < j0+3; j++ )
				{
					int valeur = this.grille[i][j].getValeur();
					if ( valeur < 1 || valeur > 9 || chiffres[valeur-1] )
						return false; // La grille est invalide

					chiffres[valeur-1] = true;
				}
			}
		}
	
		// La grille est valide
		return true;
	}

	/** Fonction qui permet de placer une valeur dans la case (ligne et colonne) indiqué par l'utilisateur
	 * @param valeur Ce paramètre désigne la valeur que l'utilisateur souhaite placer
	 * @param ligne Ce paramètre désigne la ligne ou l'utilisateur veut placer sa valeur
	 * @param colonne Ce paramètre désigne la colonne ou l'utilisateur veut placer sa valeur
	 * @return retourne un boolean qui indique si l'utilisateur peut ou non jouer sur cette case.
	 * Puisqu'on ne doit pas pouvoir modifier la grille de base
	 */
	public boolean insererGrille ( int valeur, int ligne, char colonne )
	{
		if ( !this.grille[ligne-1][colonne - 'A'].estModifiable() )
			return false;
		
		this.grille[ligne-1][colonne - 'A'] = new Nombre(valeur, true );
		
		if ( this.aGagner ( ) )
		{
			System.out.println ( " Félicitation ! Vous avez gagné ! Niveau suivant ->" );
			this.lireNiveau ( ++this.niveau );
		}
		return true;
	}

	public boolean aGagner ( )
	{
		return this.estGrilleValide();
	}
	
}