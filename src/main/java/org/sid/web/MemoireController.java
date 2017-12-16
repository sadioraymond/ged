package org.sid.web;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.tomcat.util.codec.binary.Base64;
import org.sid.dao.CategorieRepository;
import org.sid.entities.Categorie;
import org.sid.entities.DemandeAchat;
import org.sid.entities.Documents;
import org.sid.entities.DroitAttribues;
import org.sid.entities.DroitDacces;
import org.sid.entities.Poste;
import org.sid.entities.Produit;
import org.sid.entities.Service;
import org.sid.entities.Utilisateur;
import org.sid.metier.Impression;
import org.sid.metier.MemoireMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemoireController {
	@Autowired
	private MemoireMetier memoiremetier;
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E:\\temp/";
	//private Impression impression;
    JOptionPane jop1;
    @RequestMapping("/creationdoc")
	public String creationdoc() {
		return "uploaddoc";
	}
	 @RequestMapping("/demandeachat")
		public String demandeachat() {
			return "demandeachat";
		}
	 @RequestMapping("/savedemandeachat")
		public String savedemandeachat(Model model, @RequestParam(name="comment") String textbi,String titre) {
		 memoiremetier.savedemandeachat(titre,textbi);
			return "demandeachat";
		}
		 @RequestMapping("/historiquedemandeachat")
		public String historiquedemandeachat(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "size", defaultValue = "5") int size) {
				try {
					Page<DemandeAchat> pageAchat = memoiremetier.listdemandeahat(page, size);
					model.addAttribute("listachat", pageAchat.getContent());
					int [] pages=new int [pageAchat.getTotalPages()];
					model.addAttribute("pages",pages);
					model.addAttribute("valide", true);
					model.addAttribute("nonvalide", false);
				} catch (Exception e) {
					// TODO: handle exception
					model.addAttribute("exception", e);
				}
			return "historiquedemandeachat";
		}
    @RequestMapping("/creationdocument")
   	public String creationdocument(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
    	try {
			Page<Utilisateur> pageUsers = memoiremetier.listUser(page, size);
			model.addAttribute("listuser", pageUsers.getContent());
			int [] pages=new int [pageUsers.getTotalPages()];
			model.addAttribute("pages",pages);
			Page<DroitDacces> pageDroits=memoiremetier.listdroit(page, size);
			model.addAttribute("listdroit", pageDroits.getContent());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}
   		return "upload";
   	}
    @RequestMapping("/ajoututilisateur")
    private String ajoututilisateur() {
    	return "ajout_utilisateur";
    }
    @RequestMapping("/ajoutservice")
    private String ajoutservice() {
    	return "ajoutservice";
    }
    @RequestMapping(value = "/asPdf", method = RequestMethod.GET)
    public void getViewAsPdf(Model model,HttpServletResponse response) throws IOException {
        // put stuff in your model
    	response.setContentType("application/pdf");
    	String filepath = "C:\\Users\\Raymond SADIO\\hubiC\\Documents/creez-votre-application-web-avec-java-ee.pdf";

    	//response.setHeader("Content-Disposition", "inline; filename=" + filepath + ";");
    	response.setHeader("Content-Disposition", "attachment; filename="+filepath+";");
    	FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Raymond SADIO\\hubiC\\Documents/creez-votre-application-web-avec-java-ee.pdf");

    	fileOut.close();
    	response.getWriter().close();
    }
    @RequestMapping("/testreaddoc")
    public String testreaddoc() {
    			//File pdfFile = new File("E:\\\\temp/creez-votre-application-web-avec-java-ee.pdf");
    			try {

    				if ((new File("E:\\\\temp/creez-votre-application-web-avec-java-ee.pdf")).exists()) {

    					Process p = Runtime
    					   .getRuntime()
    					   .exec("rundll32 url.dll,FileProtocolHandler c:\\Java-Interview.pdf");
    					p.waitFor();

    				} else {

    					System.out.println("File is not exists");

    				}

    				System.out.println("Done");

    		  	  } catch (Exception ex) {
    				ex.printStackTrace();
    			  }

    			
    	  return "uploaddoc";
    }
    private String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.encodeBase64(bytes).toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch <span id="IL_AD6" class="IL_AD">block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        return encodedfile;
    }
    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(Model model,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@RequestParam(name="idChecked")
    List<String> list) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
       
       /* jop1 = new JOptionPane();
        jop1.showMessageDialog(null, "Document Enregistr", "Information", JOptionPane.INFORMATION_MESSAGE);*/
       // memoiremetier.savedemandeachat(file.getContentType());
        Documents doc=memoiremetier.dernierdoc();
        model.addAttribute("nom", doc.getIddoc());
        String stringArray[] = list.toArray(new String[0]);
        model.addAttribute("list", stringArray);
        memoiremetier.savedroitattribue(doc.getIddoc(), stringArray);
        return "uploadStatus";
    }
    
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
    
	@RequestMapping("/acceuil")
	public String acceuil() {
		return "acceuil";
	}

	@RequestMapping("/inscription")
	public String inscription() {
		return "inscription";
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
	@RequestMapping(value = "/saveService", method = RequestMethod.POST)
	public String saveService(Model model, String libelle) {
		memoiremetier.saveService(libelle);
		return "ajoutservice";
	}
	@RequestMapping("/ajoutposte")
	public String ajoutposte(Model model) {
		List<Service> cat=memoiremetier.allservice();
		model.addAttribute("listService",cat);
		return "ajoutposte";
	}
	@RequestMapping(value = "/saveProduit", method = RequestMethod.POST)
	public String saveProduit(Model model, String libelle,Long optionsListId) {
		memoiremetier.saveproduit(libelle,optionsListId);
		return "ajoutproduit";
	}
	@RequestMapping(value = "/savePoste", method = RequestMethod.POST)
	public String savePoste(Model model, String libelle,Long optionsListId) {
		memoiremetier.saveposte(libelle, optionsListId);
		return "ajoutposte";
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
	@RequestMapping(value = "/modifierservice", method = RequestMethod.POST)
	public String modifierservice(Model model,@RequestParam(name="id_service") Long id_service,
			@RequestParam(name="libelle") String libelle) {
		memoiremetier.modifierservice(id_service, libelle);
		return "redirect:/listservice";
	}
	@RequestMapping(value = "/modifierproduit", method = RequestMethod.POST)
	public String modifierproduit(Model model,@RequestParam(name="id_produit") Long id_produit,
			@RequestParam(name="libelle") String libelle,Long optionsListId) {
		memoiremetier.modifierproduit(id_produit, libelle, optionsListId);
		return "listeproduit";
	}
	@RequestMapping(value = "/modifierposte", method = RequestMethod.POST)
	public String modifierposte(Model model,@RequestParam(name="id_poste") Long id_poste,
			@RequestParam(name="libelle") String libelle,Long optionsListId) {
		memoiremetier.modifierposte(id_poste, libelle, optionsListId);
		return "listerposte";
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
	@RequestMapping(value="/findonedemandeachat", method = RequestMethod.GET)
	public String findonedemandeachat(Model model,@RequestParam(name="id_doc")Long id_doc) {
		DemandeAchat demach=new DemandeAchat();
		demach=memoiremetier.findonedemandeachat(id_doc);
		model.addAttribute("demach", demach);
		model.addAttribute("texte", demach.getTextedemande());
		model.addAttribute("titre", demach.getTitre());
		model.addAttribute("valide", demach.getValide());
		return "demandeappro";
	}
	@RequestMapping(value="/findoneservice", method = RequestMethod.GET)
	public String findoneservice(Model model,@RequestParam(name="id_service")Long id_service) {
		Service cat= new Service();
		cat= memoiremetier.findoneservice(id_service);
		model.addAttribute("cat",cat);
		model.addAttribute("libelle",cat.getLibelle());
		model.addAttribute("id_service",cat.getId_service());
		return "modifierservice";
	}
	@RequestMapping(value="/findoneproduit", method = RequestMethod.GET)
	public String findoneproduit(Model model,@RequestParam(name="id_produit")Long id_produit) {
		Produit prod=new Produit();
		prod= memoiremetier.findoneproduit(id_produit);
		List<Categorie> cat=memoiremetier.allcategorie();
		model.addAttribute("listCategories",cat);
		model.addAttribute("prod",prod);
		model.addAttribute("libelle",prod.getLibelle());
		model.addAttribute("categorie",prod.getCategories());
		model.addAttribute("id_produit", prod.getId_produit());
		return "modifierproduit";
	}
	@RequestMapping(value="/findoneposte", method = RequestMethod.GET)
	public String findoneposte(Model model,@RequestParam(name="id_poste")Long id_poste) {
		Poste prod=new Poste();
		prod= memoiremetier.findoneposte(id_poste);
		List<Service> cat=memoiremetier.allservice();
		model.addAttribute("listServices",cat);
		model.addAttribute("prod",prod);
		model.addAttribute("libelle",prod.getLibelle());
		model.addAttribute("service",prod.getServices());
		model.addAttribute("id_poste", prod.getId_poste());
		return "modifierposte";
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
	@RequestMapping("/allpostes")
	public String allpostes(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Poste> pagePostes = memoiremetier.listPoste(page, size);
			model.addAttribute("listPostes", pagePostes.getContent());
			int [] pages=new int [pagePostes.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}
		return "listerposte";
	}
	@RequestMapping("/allservices")
	public String allservices(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Service> pageServices = memoiremetier.listService(page, size);
			model.addAttribute("listServices", pageServices.getContent());
			int [] pages=new int [pageServices.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}
		return "listeservice";
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
