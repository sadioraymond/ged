package org.sid.web;

import java.util.List;

import org.sid.dao.CategorieRepository;
import org.sid.entities.Categorie;
import org.sid.entities.Produit;
import org.sid.metier.Impression;
import org.sid.metier.MemoireMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoireController {
	@Autowired
	private MemoireMetier memoiremetier;

	//private Impression impression;

	@RequestMapping("/acceuil")
	public String acceuil() {
		return "acceuil";
	}

	@RequestMapping("/inscription")
	public String inscription() {
		return "inscription";
	}
	@RequestMapping("/creationdoc")
	public String creationdoc() {
		return "uploaddoc";
	}
	@RequestMapping("/ajoutypeproduit")
	public String ajoutypeproduit() {
		return "ajouttypeproduit";
	}

	@RequestMapping("/ajoutproduit")
	public String ajoutproduit(Model model) {
		List<Categorie> cat=memoiremetier.allcategorie();
		model.addAttribute("listCategories",cat);
		return "ajoutproduit";
	}
	@RequestMapping(value = "/saveCategorie", method = RequestMethod.POST)
	public String saveCategorie(Model model, String libelle) {
		memoiremetier.savecategorie(libelle);
		return "ajouttypeproduit";
	}

	@RequestMapping(value = "/saveProduit", method = RequestMethod.POST)
	public String saveProduit(Model model, String libelle,Long optionsListId) {
		memoiremetier.saveproduit(libelle,optionsListId);
		return "ajoutproduit";
	}

	@RequestMapping(value = "/delecategorie")
	public String delecategorie(Model model,@RequestParam(name="id_categorie") Long id_categorie) {
		memoiremetier.deletecategorie(id_categorie);;
		return "listcatproduit";
	}
	@RequestMapping(value = "/modifiercategorie", method = RequestMethod.POST)
	public String modifiercategorie(Model model,@RequestParam(name="id_categorie") Long id_categorie,
			@RequestParam(name="libelle") String libelle) {
		memoiremetier.modifiercategorie(id_categorie, libelle);
		return "listcatproduit";
	}
	@RequestMapping("/allcategories")
	public String listCat(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Categorie> pageCategories = memoiremetier.listCategorie(page, size);
			model.addAttribute("listCategories", pageCategories.getContent());
			int [] pages=new int [pageCategories.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}
		return "listcatproduit";
	}
	@RequestMapping(value="/findonecategorie", method = RequestMethod.GET)
	public String findonecategorie(Model model,@RequestParam(name="id_categorie")Long id_categorie) {
		Categorie cat= new Categorie();
		cat= memoiremetier.findonecategorie(id_categorie);
		model.addAttribute("cat",cat);
		model.addAttribute("libelle",cat.getLibelle());
		model.addAttribute("id_categorie",cat.getId_categorie());
		return "modifiercategorie";
	}
	@RequestMapping("/allproduits")
	public String listProd(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Produit> pageProduits = memoiremetier.listProduit(page, size);
			model.addAttribute("listProduits", pageProduits.getContent());
			int [] pages=new int [pageProduits.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}
		return "listeproduit";
	}
	/*
	 * public String listCat(Model model,@RequestParam(name="page",defaultValue="0")
	 * int page,
	 * 
	 * @RequestParam(name="size",defaultValue="5") int size) { try { Page<Categorie>
	 * pagecategories= memoiremetier.listCategorie(page, size);
	 * model.addAttribute("listCategories",pagecategories.getContent()); int []
	 * pages=new int [pagecategories.getTotalPages()];
	 * model.addAttribute("pages",pages); } catch (Exception e) { // TODO: handle
	 * exception model.addAttribute("exception", e); } return "listcatproduit"; }
	 */
	/*
	 * @RequestMapping("/imprimer") public void imprimer() { // Récupère un
	 * PrinterJob PrinterJob job = PrinterJob.getPrinterJob(); // Définit son
	 * contenu à imprimer Impression impression =new Impression();
	 * job.setPrintable(impression); // Affiche une boîte de choix d'imprimante if
	 * (job.printDialog()){ try { // Effectue l'impression job.print(); } catch
	 * (PrinterException ex) { ex.printStackTrace(); } } }
	 */

}
