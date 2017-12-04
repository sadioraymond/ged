package org.sid.metier;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Impression implements Printable {
	
	public Impression() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		
		//PAGE_EXISTS si la page est affichée avec succès ou NO_SUCH_PAGE si pageIndexspécifie une page inexistante.
		//PrinterException - jeté lorsque le travail d'impression est terminé
		//graphics - le contexte dans lequel la page est dessinée
		//pageFormat - la taille et l'orientation de la page en cours de dessin
		//pageIndex - l'index basé sur zéro de la page à dessiner
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
			//Renvoyé printpour signifier que le pageIndexest trop grand et que la page demandée n'existe pas
		}
		/* On définit une marge pour l'impression */
		int marge=30;

		/* On récupère les coordonnées des bords de la page */
		int x = (int)pageFormat.getImageableX();
		int y = (int)pageFormat.getImageableY();
		int w = (int)pageFormat.getImageableWidth();
		int h = (int)pageFormat.getImageableHeight();

		/* Dessin d'un cadre gris clair*/
		//setColor Permet de changer la couleur du crayon
		graphics.setColor(Color.LIGHT_GRAY);
		//fillRect Permet de dessiner un rectangle
		graphics.fillRect(x+10, y+10, w-20, h-20);

		/* On écrit une ligne de titre en rouge, en gras de taille 18 */
		//setFont Permet de changer le style d'écriture
		graphics.setFont(new Font("Arial", Font.BOLD, 18));
		graphics.setColor(Color.RED);
		//drawString Permet d'afficher un texte
		graphics.drawString("Rapport\n", x + marge, y+marge);

		/* On écrit une ligne en noir de taille 14 */
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		graphics.setColor(Color.BLACK);
		graphics.drawString("Première ligne du rapport", x+marge, y+marge+20);

		return PAGE_EXISTS;
		//Retourné de print(Graphics, PageFormat, int) pour signifier que la page demandée a été rendue
	}

}
