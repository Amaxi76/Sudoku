package sudoku.metier;

import java.util.Scanner;
import java.io.FileInputStream;

public class Grille
{	
	private static final int TAILLE_GRILLE = 9;
	
	private Nombre[][] grille;
	private int        niveau;
	private int        nbErreur;

	public Grille ( int niveau )
	{
		this.grille   = new Nombre [TAILLE_GRILLE] [TAILLE_GRILLE];
		this.niveau   = niveau;
		this.nbErreur = 0;

		this.initialiseNiveau ( );
	}

	public int  getNbErreur ( )            { return this.nbErreur;   }
	
	public void setNbErreur ( int nombre ) { this.nbErreur = nombre; }

	/** Fonction qui permet d'obtenir sous forme textuelle notre Grille de Sudoku
	 * @return retourne sous forme d'une chaine de caractère la grille pour l'afficher dans le terminal
	 */
	@Override
	public String toString ( )
	{
		String sRet = "-----------------------------\n" +
		              "| / | A B C | D E F | G H I |\n" +
		              "-----------------------------" ;

		int longeurGrille = this.grille.length;
		
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
				if ( this.grille[ligne][colonne].getValeur ( ) == 0 )
					sRet += " .";
				else
					sRet += " " + this.grille[ligne][colonne].getValeur ( );
				
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
	public void initialiseNiveau ( )
	{
		String fichier = "./bin/niveaux/niveau" + this.niveau + ".txt";
		String ligne;

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( fichier ) );

			int cptLigne = 0;
			while ( sc.hasNextLine ( ) )
			{
				ligne = sc.nextLine ( );

				int cptColonne = 0;
				for ( int cpt = 0; cpt < ligne.length( ); cpt++ )
				{
					Nombre nb = ligne.charAt(cpt) == '.' ? new Nombre ( 0 , true  ) :  new Nombre ( Integer.parseInt ( "" + ligne.charAt ( cpt ) ), false );
					
					this.grille[cptLigne][cptColonne] = nb;
					cptColonne++;
				}
				cptLigne++;
			}
			sc.close();
		}
		catch ( Exception e ){ e.printStackTrace ( ); }
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
				int valeur = this.grille[cptLigne][cptColonne].getValeur ( );
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
		if ( !this.grille[ligne-1][colonne - 'A'].estModifiable ( ) )
			return false;

		// if ( this.estGrilleValide ( ) )
		// {
		// 	this.nbErreur++;
		// 	return false;
		// }
		
		this.grille[ligne-1][colonne - 'A'] = new Nombre ( valeur, true );
		return true;
	}

	public boolean grilleRemplie ( )
	{
		for ( int cptLig = 0; cptLig < this.grille.length; cptLig++ )
			for ( int cptCol = 0; cptCol < this.grille.length; cptCol++ )
				if ( this.grille[cptLig][cptCol].getValeur ( ) == 0 )
					return false;
		
		return true;
	}

	public int statutPartie ( )
	{
		if ( !this.estGrilleValide ( ) &&  this.grilleRemplie ( ) || this.niveau > 3 ) return -1; // Partie perdu
		if ( !this.estGrilleValide ( ) && !this.grilleRemplie ( )                    ) return 0;  // Partie en cours

		return 1; // Partie gagné
	}
	
	public int getCase ( int x, int y )
	{
		return this.grille [ x ] [ y ].getValeur ( );
	}
}