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

		g.drawRect ( x, y, 498, 498 );

		for ( int cpt = 0; cpt < 9; cpt++ )
		{
			if ( cpt != 0  && cpt % 3 == 0 )
			{
				x  = 150;
				y += 166;
			}

			int x2 = x;
			int y2 = y;

			for ( int cpt2 = 0; cpt2 < 9; cpt2++ )
			{
				if ( cpt2 != 0  && cpt2 % 3 == 0 )
				{
					x2  = x;
					y2 += 55;
				}

				g.drawRect ( x2, y2, 55, 55 );
				
				x2 += 55;
			}

			g.drawRect ( x, y, 166, 166 );
			
			x += 166;
		}
	}
}
