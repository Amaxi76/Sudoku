package sudoku;

import java.awt.Dimension;

import sudoku.metier.*;
import sudoku.vue.*;

public class Controleur
{
	// TODO: faire statistique
	// TODO: Continuer la partie graphique, gérer la fin de partie

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
		// 		while ( !this.metier.estGrilleRemplie ( ) || valider != 'O' )
		// 		{
		// 			System.out.println ( this.metier );

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

		// 			if ( this.metier.estGrilleRemplie ( ) )
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

	public void rejouer ( )
	{
		this.metier = new Grille ( ++ this.numNiveau );
	}

	public FrameJeu  getIhm     (              ) { return ihm; }
	public Dimension getHauteur (              ) { return this.ihm.getSize ( );         }
	public Nombre    getCase    ( int x, int y ) { return this.metier.getCase ( x, y ); }

	public void jouer ( int valeur )
	{
		Integer[] caseS = this.ihm.getCoordCase ( );

		this.metier.insererGrille ( valeur, caseS[0] + 1, ( char ) ( 'A' + caseS[1] ) );

		this.ihm.resetSelection ( );
		this.ihm.maj            ( );

		switch ( this.metier.statutPartie ( ) )
		{
			case  1:
				break;

			case -1:
				break;
		
			default:
				break;
		}
	}

	public void finDePartie ( )
	{
		
	}

	public static void main ( String[] args )
	{
		new Controleur ( );
	}
}