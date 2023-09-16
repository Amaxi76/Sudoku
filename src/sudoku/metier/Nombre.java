package sudoku.metier;

public class Nombre
{
	private int     valeur;
	private boolean modifiable;

	public Nombre ( int valeur, boolean modifiable )
	{
		this.valeur     = valeur;
		this.modifiable = modifiable;
	}

	public boolean estModifiable ( ) { return this.modifiable; }
	public int     getValeur     ( ) { return this.valeur;     }

}