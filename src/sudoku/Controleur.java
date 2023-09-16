package sudoku;

import iut.algo.Clavier;
import sudoku.metier.*;

public class Controleur
{
	private Grille metier;
	private int    numNiveau;

	public Controleur ( )
	{
		this.initialiserJeu();
		char rejouer = 'O';
		while ( rejouer != 'N' )
		{

			while ( this.metier.statutPartie ( ) == 0 )
			{
				System.out.println ( this.metier.afficherGrille ( ) );

				int ligne;
				do
				{
					System.out.println ( "Veuillez saisir une ligne : " );
					ligne = Clavier.lire_int ( );

					if ( ligne < 1 || ligne > 9 )
						System.out.println ( "Erreur de saisie : veuillez saisir un nombre entre 1 et 9." );
				}
				while ( ligne < 1 || ligne > 9 );

				char colonne;
				do
				{
					System.out.println ( "Veuillez saisir une colonne: " );
					colonne = Clavier.lire_char ( );
				}
				while ( ( colonne < 'A' || colonne > 'I' && colonne < 'a' || colonne > 'i') );

				colonne = Character.toUpperCase ( colonne );

				int valeur;
				do
				{
					System.out.println ( "Veuillez saisir une valeur: " );
					valeur = Clavier.lire_int ( );
				}
				while ( ligne < 1 || ligne > 9 );

				this.metier.insererGrille ( valeur, ligne, colonne );
			}

			if ( this.metier.statutPartie() == 1 )
				System.out.println("Partie Gagnée");
			else
				System.out.println("Partie perdue");

			rejouer = Clavier.lire_char();

			if ( rejouer == 'O')
				this.rejouer();

		}

	}

	public void initialiserJeu ( )
	{
		this.numNiveau = 1;
		this.metier = new Grille ( this.numNiveau );
	}

	public void rejouer ( )
	{
		this.metier = new Grille ( ++this.numNiveau );
	}

	public static void main ( String[] args )
	{
		new Controleur ( );
	}
}