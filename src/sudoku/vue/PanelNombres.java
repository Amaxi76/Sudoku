package sudoku.vue;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

import sudoku.Controleur;

public class PanelNombres extends JPanel
{
	private Controleur ctrl;
	private GereSouris gs;

	public PanelNombres ( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.gs   = new GereSouris ( this.ctrl );

		this.setPreferredSize ( new Dimension ( 300, 0 ) );
		this.repaint ( );

		this.addMouseListener       ( gs );
		this.addMouseMotionListener ( gs );
	}

	public void paintComponent ( Graphics g )
	{
		super.paintComponent ( g );

		this.gs.dimensionnerRectangle ( );

		g.setFont ( new Font ( "", Font.BOLD, 25 ) );
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( );

		int x = 0;
		int y = this.ctrl.getIhm ( ) == null ? ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) / 2 - ( 60 * 3 ) : ( int ) this.ctrl.getHauteur ( ).getHeight ( ) / 2 - ( 60 * 3 );

		for ( int cpt = 0 ; cpt < 9 ; cpt++ )
		{
			if ( cpt % 3 == 0 )
			{
				x = 0;
				y += 60;
			}
			
			//Dessine le rectangle de fond du chiffre
			g.setColor      ( new Color ( 255, 255, 255 ) );
			g.fillRoundRect ( x, y, 50, 50, 20, 20 );

			// Centre le chiffre par rapport au rectangle et le dessiner
			g.setColor   ( new Color ( 0, 0, 0 ) );

			String chiffre = ( cpt + 1 ) + "";
			FontMetrics fm = g.getFontMetrics ( );

			int textWidth  = fm.stringWidth ( chiffre );
			int textHeight = fm.getHeight   (         );

			int textX = x + ( 50 - textWidth  ) / 2;
			int textY = y + ( 50 - textHeight ) / 2 + fm.getAscent ( );

			g.drawString ( chiffre, textX, textY );

			x+= 70;
		}

	}

	public class GereSouris extends MouseAdapter
	{
		private static final int NB_BOUTONS = 9;

		private Rectangle[] ensBoutons;
		private Controleur  ctrl;

		public GereSouris ( Controleur ctrl )
		{
			this.ctrl = ctrl;
			this.ensBoutons = new Rectangle [ GereSouris.NB_BOUTONS ];

			dimensionnerRectangle ( );
		}

		public void dimensionnerRectangle ( )
		{
			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			int x = 0;
			int y = this.ctrl.getIhm ( ) == null ? ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) / 2 - ( 60 * 3 ) : ( int ) this.ctrl.getHauteur ( ).getHeight ( ) / 2 - ( 60 * 3 );

			for  ( int cpt = 0 ; cpt < GereSouris.NB_BOUTONS ; cpt++ )
			{
				if ( cpt % 3 == 0 )
				{
					x = 0;
					y += 60;
				}

				this.ensBoutons[cpt] = new Rectangle ( x, y, 50, 50 );

				x+= 70;
			}
		}

		public void mouseMoved ( MouseEvent e )
		{
			Integer posCase = this.getCoordCase ( e.getX ( ), e.getY ( ) );

			if ( posCase != null ) PanelNombres.this.setCursor ( new Cursor ( Cursor.HAND_CURSOR    ) );
			else                   PanelNombres.this.setCursor ( new Cursor ( Cursor.DEFAULT_CURSOR ) );
			
			PanelNombres.this.repaint ( );
		}

		public Integer getCoordCase ( int x, int y )
		{
			for ( int i = 0 ; i < GereSouris.NB_BOUTONS ; i++ )
				if ( this.ensBoutons[i].contains ( x, y ) ) return i;

			return null;
		}
	}

}