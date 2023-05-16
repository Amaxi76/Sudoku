package Sudoku;

public class Controleur
{
	private Grille metier;

	public Controleur ( )
	{
		this.metier = new Grille ( 1 );
	}

	public static void main ( String[] args )
	{
		new Controleur ( );
	}
}