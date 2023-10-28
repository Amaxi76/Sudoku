package sudoku.vue;

import sudoku.*;
import java.awt.*;
import javax.swing.JPanel;

public class PanelGrille extends JPanel
{
	private Controleur ctrl;

	public PanelGrille ( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.setPreferredSize ( new Dimension ( 1000, 10 ) );

		this.repaint ( );
	}

	@Override
	public void paintComponent ( Graphics g )
	{
		super.paintComponent ( g );

		g.setColor ( new Color ( 0, 0, 0 ) );

		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( );

		int x = 150;
		int y = this.ctrl.getIhm ( ) == null ? ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) / 2 - 250 : ( int ) this.ctrl.getHauteur ( ).getHeight ( ) / 2 - 250;

		this.ecrireNumero ( g, y );

		for ( int cpt = 0; cpt < 9; cpt++ )
		{
			if ( cpt != 0  && cpt % 3 == 0 )
			{
				x  = 150;
				y += 166;
			}

			// Cadrillage
			g.drawLine ( x +  55, y      , x +  55, y + 166 );
			g.drawLine ( x + 110, y      , x + 110, y + 166 );
			g.drawLine ( x      , y +  55, x + 166, y +  55 );
			g.drawLine ( x      , y + 110, x + 166, y + 110 );

			g.drawRect ( x, y, 166, 166 );

			// Ajout de l'épaisseur du trait
			g.drawRect ( x-1, y-1, 166, 166 );

			x += 166;
		}
	}

	public void ecrireNumero ( Graphics g, int y )
	{
		for ( int lig = 0 ; lig < 9 ; lig++ )
			for ( int col = 0 ; col < 9 ; col++ )
			{
				int valCase = this.ctrl.getCase ( lig, col );
				if ( valCase > 0 )
					g.drawString ( "" + valCase, 173 + ( lig * 55 ), ( y + 34 ) + ( col * 55 ) );
			}
			
	}
}
