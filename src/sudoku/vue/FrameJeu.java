package sudoku.vue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import sudoku.*;

public class FrameJeu extends JFrame
{
	private Controleur   ctrl;
	private PanelNombres panelNombre;
	private PanelGrille  panelGrille;
	private JPanel       panelTmp;

	public FrameJeu ( Controleur ctrl )
	{
		this.ctrl = ctrl;

		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( );

		int l = ( tailleEcran.width  - ( int ) ( tailleEcran.getWidth  ( ) * 0.80 ) ) / 2;
		int h = ( tailleEcran.height - ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) ) / 2;

		this.setTitle    ( "Sudoku"                                                                                  );
		this.setSize     ( ( int ) ( tailleEcran.getWidth ( ) * 0.80 ), ( int ) ( tailleEcran.getHeight ( ) * 0.80 ) );
		this.setLocation (    l,   h                                                                                 );

		// Création des composants
		this.panelTmp    = new JPanel       ( new GridLayout ( 3, 1 ) );
		this.panelNombre = new PanelNombres ( this.ctrl               );
		this.panelGrille = new PanelGrille  ( this.ctrl               );

		//Possitionnement des composants
		this.panelTmp.add ( new JPanel ( )   );
		this.panelTmp.add ( this.panelNombre );
		this.panelTmp.add ( new JPanel ( )   );

		this.add ( this.panelGrille, BorderLayout.CENTER );
		this.add ( this.panelNombre, BorderLayout.EAST   );

		this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		this.setVisible               ( true                 );
	}

	public Integer[] getCoordCase ( )
	{
		return this.panelGrille.getCoordCase ( );
	}

	public void maj ( )
	{
		this.panelGrille.repaint ( );
		this.panelNombre.repaint ( );
	}

	public void resetSelection ( )
	{
		this.panelGrille.resetSelection ( );
	}
}
