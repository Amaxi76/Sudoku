package sudoku.vue;

import sudoku.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class PanelGrille extends JPanel 
{
	private Controleur ctrl;
	private GereSouris gs;

	private Integer[] caseSelect;

	public PanelGrille ( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.gs   = new GereSouris ( this.ctrl );
		this.caseSelect = new Integer[2];

		this.setPreferredSize ( new Dimension ( 1000, 10 ) );
		this.repaint ( );

		this.addMouseListener       ( this.gs );
		this.addMouseMotionListener ( this.gs );
	}

	@Override
	public void paintComponent ( Graphics g )
	{
		super.paintComponent ( g );

		g.setColor ( new Color ( 0, 0, 0 ) );

		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( );

		int x = 150;
		int y = this.ctrl.getIhm ( ) == null ? ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) / 2 - 250 : ( int ) this.ctrl.getHauteur ( ).getHeight ( ) / 2 - 250;


		//FIXME: Vu que le nombre n'est pas divisible par 3, un décalage se passe sur la fin 
		//Mise en valeur de la case sélectionnée
		if ( this.caseSelect[0] != null )
			g.fillRect ( x + ( 56 * this.caseSelect[0] ), y + ( 56 * this.caseSelect[1] ), 56, 56 );

		this.ecrireNumero ( g, y );

		for ( int cpt = 0; cpt < 9; cpt++ )
		{
			if ( cpt != 0  && cpt % 3 == 0 )
			{
				x  = 150;
				y += 168;
			}

			// Cadrillage
			g.drawLine ( x +  56, y      , x +  56, y + 168 );
			g.drawLine ( x + 112, y      , x + 112, y + 168 );
			g.drawLine ( x      , y +  56, x + 168, y +  56 );
			g.drawLine ( x      , y + 112, x + 168, y + 112 );

			g.drawRect ( x, y, 168, 168 );

			// Ajout de l'épaisseur du trait
			g.drawRect ( x-1, y-1, 168, 168 );

			x += 168;
		}
	}

	public void ecrireNumero ( Graphics g, int y )
	{
		for ( int lig = 0 ; lig < 9 ; lig++ )
			for ( int col = 0 ; col < 9 ; col++ )
			{
				int valCase = this.ctrl.getCase ( lig, col ).getValeur ( );
				if ( valCase > 0 )
					g.drawString ( "" + valCase, 173 + ( lig * 56 ), ( y + 34 ) + ( col * 56 ) );
			}
			
	}
	public class GereSouris extends MouseAdapter
	{
		private static final int NB_CASE = 9;

		private Rectangle[][] ensBoutons;
		private Controleur    ctrl;

		public GereSouris ( Controleur ctrl )
		{
			this.ctrl       = ctrl;
			this.ensBoutons = new Rectangle [ GereSouris.NB_CASE ][ GereSouris.NB_CASE ];

			dimensionnerRectangle ( );
		}

		public void dimensionnerRectangle ( )
		{
			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			int x = 150;
			int y = this.ctrl.getIhm ( ) == null ? ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) / 2 - 250 : ( int ) this.ctrl.getHauteur ( ).getHeight ( ) / 2 - 250;

			for  ( int lig = 0 ; lig < GereSouris.NB_CASE ; lig++ )
				for  ( int col = 0 ; col < GereSouris.NB_CASE ; col++ )
					this.ensBoutons[lig][col] = new Rectangle ( x + ( lig * 56 ), y + ( col * 56 ), 56, 56 );

		}

		@Override
		public void mouseClicked ( MouseEvent e )
		{
			Integer[] coordCase = this.getCoordCase ( e.getX ( ), e.getY ( ) );

			if ( coordCase[0] != null && this.ctrl.getCase( coordCase[0], coordCase[1] ).estModifiable ( ) )
			{
				PanelGrille.this.caseSelect[0] = coordCase[0];
				PanelGrille.this.caseSelect[1] = coordCase[1];
			}
			else
			{
				PanelGrille.this.caseSelect[0] = null;
				PanelGrille.this.caseSelect[1] = null;
			}

			PanelGrille.this.repaint ( );
		}

		@Override
		public void mouseMoved ( MouseEvent e )
		{
			System.out.println ( String.format("Coord X : %d | Coord Y : %d", e.getX(), e.getY()));
			
			Integer[] posCase = this.getCoordCase ( e.getX ( ), e.getY ( ) );

			if ( posCase != null ) PanelGrille.this.setCursor ( new Cursor ( Cursor.HAND_CURSOR    ) );
			else                   PanelGrille.this.setCursor ( new Cursor ( Cursor.DEFAULT_CURSOR ) );
			
			PanelGrille.this.repaint ( );
		}

		public Integer[] getCoordCase ( int x, int y )
		{
			Integer[] pos = new Integer[2];

			for ( int lig = 0 ; lig < GereSouris.NB_CASE ; lig++ )
				for ( int col = 0 ; col < GereSouris.NB_CASE ; col++ )
					if ( this.ensBoutons[lig][col].contains ( x, y ) )
					{
						pos[1] = lig;
						pos[0] = col;

						return pos;
					}

			return null;
		}
	}
}
