package sudoku;

import java.awt.Dimension;

import sudoku.metier.*;
import sudoku.vue.*;

public class Controleur
{
	// TODO: Faire une partie GUI, faire statistique

	private FrameJeu ihm;
	private Grille   metier;
	private int      numNiveau;
	
	public Controleur ( )
	{
		new FrameAccueil ( this );
		// this.initialiserJeu ( );
		// char rejouer = 'O';
		// char valider = 'F';
		// int statutPartie = this.metier.statutPartie ( );
		// while ( rejouer != 'N' )
		// {
		// 	while ( statutPartie != 1 )
		// 	{
		// 		while ( !this.metier.grilleRemplie ( ) || valider != 'O' )
		// 		{
		// 			System.out.println ( this.metier.afficherGrille ( ) );

		// 			int ligne;
		// 			do
		// 			{
		// 				System.out.println ( "Veuillez saisir une ligne : " );
		// 				ligne = Clavier.lire_int ( );

		// 				if ( ligne < 1 || ligne > 9 )
		// 					System.out.println ( "Erreur de saisie : veuillez saisir un nombre entre 1 et 9." );
		// 			}
		// 			while ( ligne < 1 || ligne > 9 );

		// 			char colonne;
		// 			do
		// 			{
		// 				System.out.println ( "Veuillez saisir une colonne: " );
		// 				colonne = Clavier.lire_char ( );
		// 			}
		// 			while ( ( colonne < 'A' || colonne > 'I' && colonne < 'a' || colonne > 'i') );

		// 			colonne = Character.toUpperCase ( colonne );

		// 			int valeur;
		// 			do
		// 			{
		// 				System.out.println ( "Veuillez saisir une valeur: " );
		// 				valeur = Clavier.lire_int ( );
		// 			}
		// 			while ( ligne < 1 || ligne > 9 );

		// 			this.metier.insererGrille ( valeur, ligne, colonne );

		// 			if ( this.metier.grilleRemplie ( ) )
		// 			{
		// 				System.out.println ( "Voulez-vous valider la grille ? ");
		// 				valider = Clavier.lire_char ( );
		// 			}

		// 			statutPartie = this.metier.statutPartie ( );
		// 		}

		// 		if ( statutPartie == -1 )
		// 		{
		// 			this.metier.setNbErreur ( this.metier.getNbErreur ( ) + 1);

		// 			System.out.println ( "La grille n'est pas validé, plus que " + ( 3 - this.metier.getNbErreur ( ) ) );
		// 			valider = 'F';
		// 		}
				
		// 	}

		// 	if ( statutPartie == 1  )
		// 		System.out.println ( "Partie gagnée" );
		// 	if ( statutPartie == -1 )
		// 		System.out.println ( "Partie perdue" );
		// 	rejouer = Clavier.lire_char ( );

		// 	if ( rejouer == 'O' )
		// 		this.rejouer ( );
		// }

	}

	public void initialiserJeu ( )
	{
		this.numNiveau = 1;
		this.ihm       = new FrameJeu ( this           );
		this.metier    = new Grille   ( this.numNiveau );
	}

	public FrameJeu getIhm ( ) { return ihm; }

	public void rejouer ( )
	{
		this.metier = new Grille ( ++ this.numNiveau );
	}

	public Dimension getHauteur ( )
	{
		return this.ihm.getSize ( );
	}

	public static void main ( String[] args )
	{
		new Controleur ( );
	}
}