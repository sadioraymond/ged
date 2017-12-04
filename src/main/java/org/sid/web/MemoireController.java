package org.sid.web;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import org.sid.metier.Impression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemoireController {
	
	private Impression impression;
	@RequestMapping("/acceuil")
	public String acceuil() {
		return "acceuil";
	}
	@RequestMapping("/inscription")
	public String inscription() {
		return "inscription";
	}
/*	@RequestMapping("/imprimer")
	public void imprimer() {
		 // Récupère un PrinterJob
	      PrinterJob job = PrinterJob.getPrinterJob();
	      // Définit son contenu à imprimer
	      Impression impression =new Impression();
	      job.setPrintable(impression);
	      // Affiche une boîte de choix d'imprimante
	      if (job.printDialog()){
	         try {
	            // Effectue l'impression
	            job.print();
	         } catch (PrinterException ex) {
	            ex.printStackTrace();
	         }
	      }
	}*/

}
