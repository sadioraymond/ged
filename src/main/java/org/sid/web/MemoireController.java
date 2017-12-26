package org.sid.web;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.sameInstance;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.tomcat.util.codec.binary.Base64;
import org.sid.dao.CategorieRepository;
import org.sid.entities.BonCommande;
import org.sid.entities.BonDachat;
import org.sid.entities.BonLivraison;
import org.sid.entities.BonSortie;
import org.sid.entities.Categorie;
import org.sid.entities.DemandeAchat;
import org.sid.entities.DemandeAppro;
import org.sid.entities.DemandePrestation;
import org.sid.entities.DetailBon;
import org.sid.entities.DetailCommande;
import org.sid.entities.DetailFiche;
import org.sid.entities.DetailLivraison;
import org.sid.entities.Documents;
import org.sid.entities.DroitAttribues;
import org.sid.entities.DroitDacces;
import org.sid.entities.Entreprise;
import org.sid.entities.FicheSortie;
import org.sid.entities.Fournisseur;
import org.sid.entities.Personne;
import org.sid.entities.Poste;
import org.sid.entities.Produit;
import org.sid.entities.Roles;
import org.sid.entities.Service;
import org.sid.entities.Utilisateur;
import org.sid.metier.Impression;
import org.sid.metier.MemoireMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qoppa.pdf.TIFFOptions;
import com.qoppa.pdfProcess.PDFDocument;

@Controller
public class MemoireController {
	@Autowired
	private MemoireMetier memoiremetier;
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E:\\temp/";
	//private Impression impression;
    JOptionPane jop1;
    
    @RequestMapping("/creationdoc")
	public String creationdoc(Model model) {
    	String nomfic="CdC-appli-Ouvaton.pdf.tif";
    	model.addAttribute("nomfic", nomfic);
		return "uploaddoc";
	}
	 @RequestMapping("/scanne")
		public void scanne() {
			try
	        {
	            PDFDocument pdfDoc = new PDFDocument ("C:\\Users\\Raymond SADIO\\Downloads\\Documents/15-COURS_MSI_Urbanisation_exo_Corr.pdf", null);
	            TIFFOptions options = new TIFFOptions (150, TIFFOptions.TIFF_PACKBITS);
	            FileOutputStream outStream = new FileOutputStream ("C:\\Users\\Raymond SADIO\\Downloads\\Documents/15-COURS_MSI_Urbanisation_exo_Corr.tif");
	            pdfDoc.saveDocumentAsTIFF(outStream, options);
	            outStream.close();
	        }
	        catch (Throwable t)
	        {
	            t.printStackTrace();
	        }
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
		 @RequestMapping("/ajoutfournisseur")
		public String ajoutfournisseur() {
			return "ajoutfournisseur";
		}
		 @RequestMapping("/saveFournisseur")
		public String saveFournisseur(Model model,String adresse,String tel,String email,String nom,
				String typefournisseur) {
			 if(typefournisseur.equals("Entr")) {
				 memoiremetier.saveEntreprise(adresse, tel, email, nom);
			 }else {
				 if(typefournisseur.equals("Pers")) {
					 memoiremetier.savePersonne(adresse, tel, email, nom);
				 }
			 }
			return "redirect:ajoutfournisseur";
		}
		 @RequestMapping("/modifierFournisseur")
			public String modifierFournisseur(Model model,@RequestParam(name="idfour") Long idfour,
					String adresse,String tel,
					String email,String nom,
					String typefournisseur,@RequestParam(name="typefour")String typefour) {
			 if(typefour.equals("Entreprise")) {
				 if(typefournisseur.equals("Entr")) {
					 memoiremetier.updateEntreprise(idfour, adresse, tel, email, nom);
				 }else {
					 if(typefournisseur.equals("Pers")) {
						 memoiremetier.updatesavepersonne(idfour, adresse, tel, email, nom);
					 }
				 }
			 }else {
				 if(typefour.equals("Personne")) {
					 if(typefournisseur.equals("Pers")) {
						 memoiremetier.updatePersonne(idfour, adresse, tel, email, nom);
					 }else {
						 if(typefournisseur.equals("Entr")) {
							 memoiremetier.updatesaveentreprise(idfour, adresse, tel, email, nom);
						 }
					 }
				 }
			 }
				return "redirect:historiquefournisseur";
			}
			 @RequestMapping("/findoneournisseurandsuppr")
			public String findoneournisseurandsuppr(Model model,@RequestParam(name="id_fournisseur") Long idfour) {
				memoiremetier.deletefournisseur(idfour);
				return "redirect:historiquefournisseur";
			}
		 @RequestMapping("/historiquefournisseur")
			public String historiquefournisseur(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						Page<Fournisseur> pageFour=memoiremetier.listFournisseur(page, size);
						model.addAttribute("listfour", pageFour.getContent());
						int [] pages=new int [pageFour.getTotalPages()];
						model.addAttribute("pages",pages);
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquefournisseur";
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
		 @RequestMapping("/historiquebonachat")
			public String historiquebonachat(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						Page<BonDachat> pageBonAchat = memoiremetier.listbonahat(page, size);
						model.addAttribute("listbonachat", pageBonAchat.getContent());
						int [] pages=new int [pageBonAchat.getTotalPages()];
						model.addAttribute("pages",pages);
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquebonachat";
			}
			 @RequestMapping("/historiquebonacommande")
			public String historiquebonacommande(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						Page<BonCommande> pageBonCom = memoiremetier.listBonCommande(page, size);
						model.addAttribute("listboncom", pageBonCom.getContent());
						int [] pages=new int [pageBonCom.getTotalPages()];
						model.addAttribute("pages",pages);
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquebonacommande";
			}
			 @RequestMapping("/historiquebonlivraison")
			public String historiquebonlivraison(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						Page<BonLivraison> pageBonLiv = memoiremetier.listBonLivraison(page, size);
						model.addAttribute("listbonliv", pageBonLiv.getContent());
						int [] pages=new int [pageBonLiv.getTotalPages()];
						model.addAttribute("pages",pages);
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquebonlivraison";
			}
			 @RequestMapping("/historiquefichedesortie")
			public String historiquefichedesortie(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						Page<FicheSortie> pageFichSortie=memoiremetier.listfiche(page, size);
						model.addAttribute("listfichesor", pageFichSortie.getContent());
						int [] pages=new int [pageFichSortie.getTotalPages()];
						model.addAttribute("pages",pages);
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquebonsortie";
			}
		 @RequestMapping("/historiquedemandeappro")
			public String historiquedemandeappro(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						Page<DemandeAppro> pageAppro = memoiremetier.listdemandeappro(page, size);
						model.addAttribute("listappro", pageAppro.getContent());
						int [] pages=new int [pageAppro.getTotalPages()];
						model.addAttribute("pages",pages);
						/*model.addAttribute("valide", true);
						model.addAttribute("nonvalide", false);*/
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquedemandeappro";
			}
			 @RequestMapping("/historiqueprestation")
			public String historiqueprestation(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "5") int size) {
					try {
						String typedoc="DP";
						Page<DemandePrestation> pagePrest=memoiremetier.listPrestation(typedoc, page, size);
						model.addAttribute("listprest", pagePrest.getContent());
						int [] pages=new int [pagePrest.getTotalPages()];
						model.addAttribute("pages",pages);
						/*model.addAttribute("valide", true);
						model.addAttribute("nonvalide", false);*/
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("exception", e);
					}
				return "historiquedemandeprest";
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
    @RequestMapping("/numerisationprestation")
   	public String numerisationprestation(Model model) {
    	
   		return "numerisation";
   	}
    @RequestMapping("/ajoututilisateur")
    private String ajoututilisateur(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
    	try {
			Page<Utilisateur> pageUtili=memoiremetier.listusernonval(page, size);
			model.addAttribute("listuser", pageUtili.getContent());
			int [] pages=new int [pageUtili.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}
    	return "valideruser";
    }
    @RequestMapping("/findoneuserandmodif")
    private String findoneuserandmodif(Model model,@RequestParam(name="id_user") Long id_user) {
    	Utilisateur us=memoiremetier.findoneuser(id_user);
    	model.addAttribute("nom", us.getNom_user());
    	model.addAttribute("prenom", us.getPrenom_user());
    	model.addAttribute("adresse", us.getAdresse());
    	model.addAttribute("email", us.getEmail());
    	List<Roles> listrole=memoiremetier.allroles();
    	model.addAttribute("listrole", listrole);
    	model.addAttribute("user", us.getId_user());
    	return "activeruser";
    }
    @RequestMapping("/activeruser")
    private String activeruser(Model model,@RequestParam(name="optionsListId") Long optionsListId,
    		@RequestParam(name="user") Long user) {
    	memoiremetier.activeruser(user);
    	memoiremetier.saveuserrole(optionsListId, user);
    	return "redirect:ajoututilisateur";
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
    
    @PostMapping("/savePrestation")
 	public String savePrestation(Model model,@RequestParam(name="idChecked") List<String> idChecked,
 			@RequestParam(name="iddoc") Long iddoc) {
    	
    	String stringArray[] = idChecked.toArray(new String[0]);
    	memoiremetier.savedroitattribue(iddoc, stringArray);
    	return "redirect:numerisationprestation";
    }
    @PostMapping("/modifierprest") // //new annotation since 4.3
    public String modifierprest(Model model,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   String titre,String demandeur,String objet,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                       			@RequestParam(name = "size", defaultValue = "5") int size,
                       			@RequestParam(name="idChecked") List<String> idChecked,
                       			@RequestParam(name="iddoc") Long iddoc,@RequestParam(name="iddetail") List<Long> iddetail) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Choisissez un fichier");
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
    	try
        {
    		
            PDFDocument pdfDoc = new PDFDocument ("E:\\temp/"+""+file.getOriginalFilename(), null);
            TIFFOptions options = new TIFFOptions (150, TIFFOptions.TIFF_PACKBITS);
            Documents docs=memoiremetier.dernierdoc();
            String nom="DP"+docs.getIddoc();
            FileOutputStream outStream = new FileOutputStream ("C:\\Users\\Raymond SADIO\\memoire-workspace\\memoire\\src\\main\\resources\\static\\assets\\img/"+""+nom+".tif");
            pdfDoc.saveDocumentAsTIFF(outStream, options);
            outStream.close();
            String nomfic=nom+".tif";
            String stringArray[] = idChecked.toArray(new String[0]);
            memoiremetier.updatedemandeprest(iddoc, titre, objet, demandeur, nomfic,stringArray,iddetail);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        return "redirect:historiqueprestation";
    }
    @PostMapping("/uploadprest") // //new annotation since 4.3
    public String uploadprest(Model model,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   String titre,String demandeur,String objet,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                       			@RequestParam(name = "size", defaultValue = "5") int size) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Choisissez un fichier");
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
    	try
        {
    		
            PDFDocument pdfDoc = new PDFDocument ("E:\\temp/"+""+file.getOriginalFilename(), null);
            TIFFOptions options = new TIFFOptions (150, TIFFOptions.TIFF_PACKBITS);
            Documents docs=memoiremetier.dernierdoc();
            String nom="DP"+docs.getIddoc();
            FileOutputStream outStream = new FileOutputStream ("C:\\Users\\Raymond SADIO\\memoire-workspace\\memoire\\src\\main\\resources\\static\\assets\\img/"+""+nom+".tif");
            pdfDoc.saveDocumentAsTIFF(outStream, options);
            outStream.close();
            String nomfic=nom+".tif";
            memoiremetier.savedemandeprestation(titre, objet, demandeur, nomfic);
            Documents doc=memoiremetier.dernierdoc();
            DemandePrestation dp=memoiremetier.findonedp(doc.getIddoc());
            model.addAttribute("titre", dp.getTitre());
            model.addAttribute("objet", dp.getObjet());
            model.addAttribute("demandeur", dp.getDemandeur());
            model.addAttribute("iddoc", dp.getIddoc());
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
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
    
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
    
	@RequestMapping("/acceuil")
	public String acceuil(Model model,Authentication authentication) {
		String username=authentication.getName();
		model.addAttribute("username", username);
		return "acceuil";
	}
	@RequestMapping("validerinscription")
	public String validerinscription(Model model,String nom,String prenom,String email,String adresse,String username,
			String password,String password1,String tel) {
		try {
			memoiremetier.saveuser(nom, prenom, email, adresse, username, password, password1, tel);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e);
			return "redirect:inscription?"+"&error="+e.getMessage();
		}
		return "redirect:login";
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
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	@RequestMapping(value = "/savedemandeappro", method = RequestMethod.POST)
	public String savedemandeappro(Model model, String titre,String texte,@RequestParam(name="id_demandeachat") 
	Long id_demandeachat,@RequestParam(name="valide") 
	Boolean valide) {
		if(valide==false) {
			Boolean validite=true;
			memoiremetier.validedeamandeachat(id_demandeachat,validite);
		}else {
			Boolean validite=false;
			memoiremetier.validedeamandeachat(id_demandeachat,validite);
		}
		memoiremetier.savedemandeappro(titre, texte,id_demandeachat);
		model.addAttribute("valide", valide);
		return "test";
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
	@RequestMapping(value = "/savebonachat", method = RequestMethod.POST)
	public String savebonachat(Model model, String titre,String texte, 
			@RequestParam(name="id_demandeappro") Long id_demandeappro) {
		memoiremetier.savebonachat(titre, texte, id_demandeappro);
		return "ajoutproduit";
	}
	@RequestMapping(value = "/findbonachat", method = RequestMethod.POST)
	public String findbonachat(Model model,String optionsListId,
			@RequestParam(name="id_demandeappro") Long id_demandeappro) {
		if(optionsListId.equals("Non Accordé") || optionsListId.equals("Accordé Partiellement")) {
			if(optionsListId.equals("Non Accordé")) {
				int accord=0;
				memoiremetier.updateaccorddemandeappro(id_demandeappro, accord);
			}else {
				if(optionsListId.equals("Accordé Partiellement")) {
					int accord=1;
					memoiremetier.updateaccorddemandeappro(id_demandeappro, accord);
				}
			}
			model.addAttribute("id_demandeappro", id_demandeappro);
			return "ajoutbondachat";
		}
		return "test";
	}
	@RequestMapping(value = "/savePoste", method = RequestMethod.POST)
	public String savePoste(Model model, String libelle,Long optionsListId) {
		memoiremetier.saveposte(libelle, optionsListId);
		return "ajoutposte";
	}
	@RequestMapping(value = "/generationcommande")
	public String generationcommande() {
		return "redirect:creationboncommande";
	}
	@RequestMapping(value = "/generationlivraison")
	public String generationlivraison() {
		return "redirect:creationbonlivraison";
	}
	@RequestMapping(value = "/generationbonsortie")
	public String generationbonsortie() {
		return "redirect:creationfichesortie";
	}
	@RequestMapping(value = "/creationboncommande")
	public String creationboncommande(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "30") int size) {
		List<BonDachat> bon=null;
		bon=memoiremetier.allbondachat();
		List<Fournisseur> listfour=memoiremetier.allFournisseurs();
		String Produit="Produit";
		Page<Produit> pageProduits = memoiremetier.listProduit(page, size);
		model.addAttribute("listfour", listfour);
		model.addAttribute("listebon", bon);
		model.addAttribute("listProduits", pageProduits.getContent());
		model.addAttribute("Produit", Produit);
		int [] pages=new int [pageProduits.getTotalPages()];
		model.addAttribute("pages",pages);
		int produit=0;
		model.addAttribute("produit",produit);
		return "creationboncommande";
	}
	@RequestMapping(value = "/creationbonlivraison")
	public String creationbonlivraison(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "30") int size) {
		List<BonCommande> listbon=memoiremetier.allCommandes();
		List<Fournisseur> listfour=memoiremetier.allFournisseurs();
		Page<Produit> pageProduits = memoiremetier.listProduit(page, size);
		model.addAttribute("listebon", listbon);
		model.addAttribute("listfour", listfour);
		model.addAttribute("listProduits", pageProduits.getContent());
		int [] pages=new int [pageProduits.getTotalPages()];
		model.addAttribute("pages",pages);
		int produit=0;
		model.addAttribute("produit",produit);
		return "creationbonlivraison";
	}
	@RequestMapping(value = "/creationfichesortie")
	public String creationfichesortie(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "30") int size) {
		Page<Produit> pageProduits = memoiremetier.listProduit(page, size);
		model.addAttribute("listProduits", pageProduits.getContent());
		int [] pages=new int [pageProduits.getTotalPages()];
		model.addAttribute("pages",pages);
		List<DemandeAppro> listappro=memoiremetier.listappro();
		model.addAttribute("listappro",listappro);
		return "creationfichesortie";
	}
	@RequestMapping(value = "/saveBonCommande")
	public String saveBonCommande(Model model,@RequestParam(name="produit")
    List<String> produit,Long optionsListId,Long optionsListIds, @RequestParam(name="qte")
    List<String> qte,String titre) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0;i<stringArrayqte.length;i++,j++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]);
			}
		}
		memoiremetier.savecommande(tamp, titre, optionsListId,optionsListIds);
		BonCommande bc=new BonCommande();
		bc=memoiremetier.dernierbon();
		List<DetailCommande> dtc=memoiremetier.detailbycommande(bc.getId_boncommande());
		DetailBon detbon=new DetailBon();
		detbon=memoiremetier.detailbyboncommande(bc.getId_boncommande());
		model.addAttribute("fournisseur", detbon.getFournisseurs().getNom());
		model.addAttribute("titre", bc.getTitre());
		model.addAttribute("bonachat", bc.getBondachats().getTitre());
		model.addAttribute("detailcommande", dtc);
		return "generationcommande";
	}
	@RequestMapping(value = "/saveBonLivraison")
	public String saveBonLivraison(Model model,@RequestParam(name="produit")
    List<String> produit,Long optionsListId,Long optionsListIds, @RequestParam(name="qte")
    List<String> qte,@RequestParam(name="prix")List<String> prix,String titre) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		String stringArrayprix[] = prix.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0,k=0;i<stringArrayqte.length;i++,j++,k++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]+"-"+stringArrayprix[k]);
			}
		}
		memoiremetier.savelivraison(tamp, titre, optionsListId, optionsListIds);
		BonLivraison bc=new BonLivraison();
		bc=memoiremetier.dernierbonliv();
		List<DetailLivraison> dtc=memoiremetier.detailbyLivraison(bc.getId_bonlivraison());
		model.addAttribute("titre", bc.getTitre());
		model.addAttribute("boncommande", bc.getBoncommandes().getTitre());
		model.addAttribute("fournisseur", bc.getFournisseurs().getNom());
		model.addAttribute("detailcommande", dtc);
		return "generationlivraison";
	}
	@RequestMapping(value = "/saveficheSortie")
	public String saveficheSortie(Model model,@RequestParam(name="produit")
    List<String> produit,Long optionsListId, @RequestParam(name="qte")
    List<String> qte,String libelle) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0;i<stringArrayqte.length;i++,j++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]);
			}
		}
		memoiremetier.savefichesortie(tamp, libelle, optionsListId);
		FicheSortie bc=new FicheSortie();
		bc=memoiremetier.dernierefiche();
		List<DetailFiche> dtc=memoiremetier.detailbyfiche(bc.getId_fichesortie());
		model.addAttribute("libelle", bc.getLibelle());
		List<BonSortie> bonso=memoiremetier.bonbydetailfice(dtc);
		model.addAttribute("bonsortie", dtc);
		model.addAttribute("demandeappro",bonso.get(0).getDemandeappros().getTitre());
		return "generationbonsortie";
	}
	@RequestMapping(value = "/saveBonCommandes")
	public String saveBonCommandes(Model model,@RequestParam(name="produit")
    List<String> produit,@RequestParam(name="qte")List<String> qte,
    @RequestParam(name="idbon")Long idbon) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0;i<stringArrayqte.length;i++,j++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]);
			}
		}
		BonCommande bc=new BonCommande();
		bc=memoiremetier.findoneboncommande(idbon);
		memoiremetier.savemodifcommande(tamp, bc);
		List<DetailCommande> dtc=memoiremetier.detailbycommande(bc.getId_boncommande());
		DetailBon detbon=memoiremetier.detailbyboncommande(bc.getId_boncommande());
		model.addAttribute("fournisseur", detbon.getFournisseurs().getNom());
		model.addAttribute("titre", bc.getTitre());
		model.addAttribute("bonachat", bc.getBondachats().getTitre());
		model.addAttribute("detailcommande", dtc);
		return "generationcommande";
	}
	@RequestMapping(value = "/saveLivraisons")
	public String saveLivraisons(Model model,@RequestParam(name="produit")
    List<String> produit,@RequestParam(name="qte")List<String> qte,
    @RequestParam(name="idbon")Long idbon,@RequestParam(name="prix")List<String> prix) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		String stringArrayprix[] = prix.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0,k=0;i<stringArrayqte.length;i++,j++,k++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]+"-"+stringArrayprix[k]);
			}
		}
		BonLivraison bl=new BonLivraison();
		bl=memoiremetier.findonelivraison(idbon);
		memoiremetier.savemodiflivraison(tamp, bl);
		List<DetailLivraison> dtc=memoiremetier.detailbyLivraison(bl.getId_bonlivraison());
		model.addAttribute("titre", bl.getTitre());
		model.addAttribute("boncommande", bl.getBoncommandes().getTitre());
		model.addAttribute("fournisseur", bl.getFournisseurs().getNom());
		model.addAttribute("detailcommande", dtc);
		return "generationlivraison";
	}
	@RequestMapping(value = "/saveFiches")
	public String saveFiches(Model model,@RequestParam(name="produit")
    List<String> produit,@RequestParam(name="qte")List<String> qte,
    @RequestParam(name="idbon")Long idbon,@RequestParam(name="demandeappros") Long demandeappros) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0;i<stringArrayqte.length;i++,j++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]);
			}
		}
		FicheSortie fic=new FicheSortie();
		fic=memoiremetier.findonefichesortie(idbon);
		memoiremetier.savemodifFiche(tamp, fic, demandeappros);
		List<DetailFiche> dtc=memoiremetier.detailbyfiche(fic.getId_fichesortie());
		model.addAttribute("libelle", fic.getLibelle());
		List<BonSortie> bonso=memoiremetier.bonbydetailfice(dtc);
		model.addAttribute("bonsortie", dtc);
		model.addAttribute("demandeappro",bonso.get(0).getDemandeappros().getTitre());
		return "generationbonsortie";
	}
	@RequestMapping(value = "/modifiercommande")
	public String modifiercommande(Model model,@RequestParam(name="produit")
    List<String> produit,Long optionsListId,Long optionsListIds, @RequestParam(name="qte")
    List<String> qte,String titre,Long idbon,@RequestParam(name="iddetail") List<Long> iddetail) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0;i<stringArrayqte.length;i++,j++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]);
			}
		}
		List<DetailCommande> dtc = memoiremetier.detailbycommande(idbon);
		model.addAttribute("tamp", tamp);
		//model.addAttribute("detailcommande", dtc);
		model.addAttribute("produit", produit);
		model.addAttribute("optionsListId", optionsListId);
		model.addAttribute("qte", qte);
		model.addAttribute("titre", titre);
		model.addAttribute("idbon", idbon);
		model.addAttribute("iddetail", iddetail);
		memoiremetier.updatecommande(idbon, titre, optionsListId,tamp,iddetail,optionsListIds);
		BonCommande bnco=memoiremetier.findoneboncommande(idbon);
		DetailBon detbon=memoiremetier.detailbyboncommande(bnco.getId_boncommande());
		model.addAttribute("fournisseur", detbon.getFournisseurs().getNom());
		model.addAttribute("bonachat", bnco.getBondachats().getTitre());
		model.addAttribute("detailcommande", dtc);
		List<Produit> prodboubess=memoiremetier.listprodpourmodif(tamp);
		model.addAttribute("listProduits",prodboubess);
		return "modifierajoutcom";
	}
	@RequestMapping(value = "/modifierlivraison")
	public String modifierlivraison(Model model,@RequestParam(name="produit")
    List<String> produit,Long optionsListId,Long optionsListIds, @RequestParam(name="qte")
    List<String> qte,String titre,Long idbon,@RequestParam(name="iddetail") List<Long> iddetail,
    @RequestParam(name="prix")List<String> prix) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		String stringArrayprix[] = prix.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0,k=0;i<stringArrayqte.length;i++,j++,k++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]+"-"+stringArrayprix[k]);
			}
		}
		memoiremetier.updatelivraison(idbon, tamp, titre, optionsListId, optionsListIds, iddetail);
		BonLivraison bc=new BonLivraison();
		bc=memoiremetier.findonelivraison(idbon);
		List<DetailLivraison> dtc=memoiremetier.detailbyLivraison(bc.getId_bonlivraison());
		model.addAttribute("titre", bc.getTitre());
		model.addAttribute("idbon", bc.getId_bonlivraison());
		model.addAttribute("boncommande", bc.getBoncommandes().getTitre());
		model.addAttribute("fournisseur", bc.getFournisseurs().getNom());
		model.addAttribute("detailcommande", dtc);
		List<Produit> prodboubess=memoiremetier.listprodpourmodif(tamp);
		model.addAttribute("listProduits",prodboubess);
		return "modifierajoutliv";
	}
	@RequestMapping(value = "/modifiersortie")
	public String modifiersortie(Model model,@RequestParam(name="produit")
    List<String> produit,Long optionsListId,
    @RequestParam(name="qte")
    List<String> qte,String libelle,Long id_fiche,@RequestParam(name="iddetail") 
	List<Long> iddetail) {
		String stringArrayprod[] = produit.toArray(new String[0]);
		String stringArrayqte[] = qte.toArray(new String[0]);
		List<String> tamp=new ArrayList<>();
		for(int i=0,j=0;i<stringArrayqte.length;i++,j++) {
			if(!(stringArrayqte[i].equals(""))) {
				tamp.add(stringArrayqte[i]+"-"+stringArrayprod[j]);
			}
		}
		memoiremetier.updatefiche(id_fiche, libelle, optionsListId, tamp, iddetail);
		FicheSortie bc=new FicheSortie();
		bc=memoiremetier.findonefichesortie(id_fiche);
		List<DetailFiche> dtc=memoiremetier.detailbyfiche(bc.getId_fichesortie());
		model.addAttribute("libelle", bc.getLibelle());
		List<BonSortie> bonso=memoiremetier.bonbydetailfice(dtc);
		model.addAttribute("bonsortie", dtc);
		model.addAttribute("id_fiche", id_fiche);
		model.addAttribute("demandeappro",bonso.get(0).getDemandeappros().getTitre());
		model.addAttribute("demandeappros",bonso.get(0).getDemandeappros().getId_demandeappro());
		List<Produit> prodboubess=memoiremetier.listprodpourmodif(tamp);
		model.addAttribute("listProduits",prodboubess);
		return "modifierajoutfiche";
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
		return "redirect:listservice";
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
	@RequestMapping(value="/findonefournisseurandmodif", method = RequestMethod.GET)
	public String findonefournisseurandmodif(Model model,@RequestParam(name="id_fournisseur")Long id_fournisseur) {
		Fournisseur four=memoiremetier.findonefournisseur(id_fournisseur);
		model.addAttribute("cat",four);
		model.addAttribute("adresse",four.getAdresse());
		model.addAttribute("email",four.getEmail());
		model.addAttribute("tel",four.getTel());
		model.addAttribute("typefour",four.getClass().getSimpleName());
		model.addAttribute("idfour",four.getId_fournisseur());
		if(four.getClass().getSimpleName().equals("Entreprise")) {
			Entreprise er=memoiremetier.findoneentreprise(id_fournisseur);
			model.addAttribute("nomEntreprise",er.getNomEntreprise());
		}else {
			if(four.getClass().getSimpleName().equals("Personne")) {
				Personne per=memoiremetier.findonepersonne(id_fournisseur);
				model.addAttribute("nom",per.getNompersonne());
			}
		}
		return "modifierfournisseur";
	}
	@RequestMapping(value="/findonedemandeachat", method = RequestMethod.GET)
	public String findonedemandeachat(Model model,@RequestParam(name="id_doc")Long id_doc) {
		DemandeAchat demach=new DemandeAchat();
		demach=memoiremetier.findonedemandeachat(id_doc);
		model.addAttribute("demach", demach);
		model.addAttribute("texte", demach.getTextedemande());
		model.addAttribute("titre", demach.getTitre());
		model.addAttribute("valide", demach.getValide());
		model.addAttribute("id_demandeachat", demach.getIddoc());
		return "demandeappro";
	}
	@RequestMapping(value="/findonedemandeappro", method = RequestMethod.GET)
	public String findonedemandeappro(Model model,@RequestParam(name="id_demappro")Long id_demappro) {
		DemandeAppro demach=new DemandeAppro();
		demach=memoiremetier.findonedemandeappro(id_demappro);
		model.addAttribute("demach", demach);
		model.addAttribute("texte", demach.getTexte());
		model.addAttribute("titre", demach.getTitre());
		model.addAttribute("accorde", demach.getAccorde());
		model.addAttribute("id_demandeappro", demach.getId_demandeappro());
		String accord="Accordé";
		String nonaccord="Non Accordé";
		String accordpar="Accordé Partiellement";
		model.addAttribute("accord",accord );
		model.addAttribute("nonaccord",nonaccord );
		model.addAttribute("accordpar",accordpar );
		return "verifdemande";
	}
	@RequestMapping(value="/findonebondachat", method = RequestMethod.GET)
	public String findonebondachat(Model model,@RequestParam(name="id_bondachat")Long id_bondachat) {
		BonDachat bonach=new BonDachat();
		bonach=memoiremetier.findonebonachat(id_bondachat);
		model.addAttribute("bonach", bonach);
		model.addAttribute("texte", bonach.getTexte());
		model.addAttribute("titre", bonach.getTitre());
		model.addAttribute("valide", bonach.getValide());
		model.addAttribute("id_bondachat", bonach.getId_bondachat());
		return "verifbon";
	}
	@RequestMapping(value="/findoneboncommande", method = RequestMethod.GET)
	public String findoneboncommande(Model model,@RequestParam(name="id_boncommande")Long id_boncommande) {
		BonCommande bonach=new BonCommande();
		bonach=memoiremetier.findoneboncommande(id_boncommande);
		DetailBon detbon=new DetailBon();
		detbon=memoiremetier.detailbyboncommande(bonach.getId_boncommande());
		model.addAttribute("fournisseur", detbon.getFournisseurs().getNom());
		model.addAttribute("bonach", bonach);
		model.addAttribute("titre", bonach.getTitre());
		model.addAttribute("titrebonachat", bonach.getBondachats().getTitre());
		List<DetailCommande> dtcom=memoiremetier.detailbycommande(bonach.getId_boncommande());
		model.addAttribute("detailcom", dtcom);
		return "voircommande";
	}
	@RequestMapping(value="/findoneprestation", method = RequestMethod.GET)
	public String findoneprestation(Model model,@RequestParam(name="iddoc")Long iddoc) {
		DemandePrestation demprest=new DemandePrestation();
		String typedoc="DP";
		demprest=memoiremetier.findoneprestation(iddoc, typedoc);
		model.addAttribute("titre", demprest.getTitre());
		model.addAttribute("objet", demprest.getObjet());
		model.addAttribute("demandeur", demprest.getDemandeur());
		model.addAttribute("iddoc", demprest.getIddoc());
		model.addAttribute("nomfichier", demprest.getNomfichier());
		return "voirprestation";
	}
	@RequestMapping(value="/deleteprest", method = RequestMethod.GET)
	public String deleteprest(Model model,@RequestParam(name="iddoc")Long iddoc) {
		memoiremetier.deleteprest(iddoc);
		return "redirect:historiqueprestation";
	}
	@RequestMapping(value="/findoneprestandupdate", method = RequestMethod.GET)
	public String findoneprestandupdate(Model model,@RequestParam(name="iddoc")Long iddoc) {
		DemandePrestation demprest=new DemandePrestation();
		String typedoc="DP";
		demprest=memoiremetier.findoneprestation(iddoc, typedoc);
		model.addAttribute("titre", demprest.getTitre());
		model.addAttribute("objet", demprest.getObjet());
		model.addAttribute("demandeur", demprest.getDemandeur());
		model.addAttribute("iddoc", demprest.getIddoc());
		model.addAttribute("nomfichier", demprest.getNomfichier());
		List<DroitAttribues> listdroi=memoiremetier.listdroitattr(iddoc);
		model.addAttribute("listdroi", listdroi);
		List<Utilisateur> lisuti=memoiremetier.listutibydroi(listdroi);
		model.addAttribute("lisuti", lisuti);
		return "modifierdemandeprest";
	}
	@RequestMapping(value="/findonebonlivraison", method = RequestMethod.GET)
	public String findonebonlivraison(Model model,@RequestParam(name="id_bonlivraison")Long id_bonlivraison) {
		BonLivraison bc=new BonLivraison();
		bc=memoiremetier.findonelivraison(id_bonlivraison);
		List<DetailLivraison> dtc=memoiremetier.detailbyLivraison(bc.getId_bonlivraison());
		model.addAttribute("titre", bc.getTitre());
		model.addAttribute("boncommande", bc.getBoncommandes().getTitre());
		model.addAttribute("fournisseur", bc.getFournisseurs().getNom());
		model.addAttribute("detailcommande", dtc);
		return "voirlivraison";
	}
	@RequestMapping(value="/findonebonsortie", method = RequestMethod.GET)
	public String findonebonsortie(Model model,@RequestParam(name="id_fichesortie")Long id_fichesortie) {
		FicheSortie ficsort=new FicheSortie();
		ficsort=memoiremetier.findonefichesortie(id_fichesortie);
		model.addAttribute("ficsort", ficsort);
		List<DetailFiche> dtc=memoiremetier.detailbyfiche(ficsort.getId_fichesortie());
		model.addAttribute("libelle", ficsort.getLibelle());
		List<BonSortie> bonso=memoiremetier.bonbydetailfice(dtc);
		model.addAttribute("bonsortie", dtc);
		model.addAttribute("demandeappro",bonso.get(0).getDemandeappros().getTitre());
		return "voirbonsortie";
	}
	@RequestMapping(value="/findoneboncommandeandsuppr", method = RequestMethod.GET)
	public String findoneboncommandeandsuppr(Model model,@RequestParam(name="id_boncommande")Long id_boncommande) {
		
		memoiremetier.supprcommande(id_boncommande);
		return "redirect:historiquebonacommande";
	}
	@RequestMapping(value="/findonebonlivandsuppr", method = RequestMethod.GET)
	public String findonebonlivandsuppr(Model model,@RequestParam(name="id_bonlivraison")Long id_bonlivraison) {
		
		memoiremetier.deletelivraison(id_bonlivraison);
		return "redirect:historiquebonlivraison";
	}
	 @RequestMapping("/historiquebonsortie")
		public String historiquebonsortie(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "size", defaultValue = "5") int size) {
			try {
				Page<FicheSortie> pageFichSortie=memoiremetier.listfiche(page, size);
				model.addAttribute("listfichesor", pageFichSortie.getContent());
				int [] pages=new int [pageFichSortie.getTotalPages()];
				model.addAttribute("pages",pages);
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("exception", e);
			}
			return "historiquebonsortie";
		}
	@RequestMapping(value="/findonebonsortieandsuppr", method = RequestMethod.GET)
	public String findonebonsortieandsuppr(Model model,@RequestParam(name="id_fichesortie")Long id_fichesortie) {
		
		memoiremetier.supprbonsortie(id_fichesortie);
		return "redirect:historiquebonsortie";
	}
	@RequestMapping(value="/findonedetailandsupprr", method = RequestMethod.GET)
	public String findonedetailandsupprr(Model model,@RequestParam(name="id_detail")Long id_detail,
			@RequestParam(name="idcommande")Long idcommande) {
		
		memoiremetier.supprdetailcommande(id_detail);
		return "redirect:findoneboncommandeandmodif?id_boncommande="+idcommande;
	}
	@RequestMapping(value="/findonedetaillivandsupprr", method = RequestMethod.GET)
	public String findonedetaillivandsupprr(Model model,@RequestParam(name="id_detail")Long id_detail,
			@RequestParam(name="id_bonlivraison")Long idbon) {
		
		memoiremetier.deletedetailliv(id_detail);
		return "redirect:findonebonlivandmodif?id_bonlivraison="+idbon;
	}
	@RequestMapping(value="/findonedetailsortandsupprr", method = RequestMethod.GET)
	public String findonedetailsortandsupprr(Model model,@RequestParam(name="id_detail")Long id_detail,
			@RequestParam(name="id_fiche")Long id_fiche) {
		
		memoiremetier.supprdetailfiche(id_detail);
		return "redirect:findonebonsortieandmodif?id_fichesortie="+id_fiche;
	}
	@RequestMapping(value="/findoneboncommandeandmodif", method = RequestMethod.GET)
	public String findoneboncommandeandmodif(Model model,@RequestParam(name="id_boncommande")Long id_boncommande) {
		BonCommande bonach=new BonCommande();
		bonach=memoiremetier.findoneboncommande(id_boncommande);
		model.addAttribute("bonach", bonach);
		model.addAttribute("titre", bonach.getTitre());
		List<Fournisseur> fou=memoiremetier.allFournisseurs();
		model.addAttribute("fou", fou);
		model.addAttribute("idbon", bonach.getId_boncommande());
		model.addAttribute("bonachat", bonach.getBondachats().getId_bondachat());
		model.addAttribute("codebonachat", bonach.getBondachats().getCode_bon());
		List<DetailCommande> dtcom=memoiremetier.detailbycommande(bonach.getId_boncommande());
		model.addAttribute("detailcom", dtcom);
		List<BonDachat> lisach=memoiremetier.allbondachat();
		model.addAttribute("lisach", lisach);
		return "modifcommande";
	}
	@RequestMapping(value="/findonebonlivandmodif", method = RequestMethod.GET)
	public String findonebonlivandmodif(Model model,@RequestParam(name="id_bonlivraison")Long id_bonlivraison) {
		BonLivraison bc=new BonLivraison();
		bc=memoiremetier.findonelivraison(id_bonlivraison);
		List<DetailLivraison> dtc=memoiremetier.detailbyLivraison(bc.getId_bonlivraison());
		model.addAttribute("titre", bc.getTitre());
		model.addAttribute("idbon", bc.getId_bonlivraison());
		model.addAttribute("boncommande", bc.getBoncommandes().getTitre());
		model.addAttribute("fournisseur", bc.getFournisseurs().getNom());
		model.addAttribute("detailcommande", dtc);
		List<BonCommande> listbon=memoiremetier.allCommandes();
		List<Fournisseur> listfour=memoiremetier.allFournisseurs();
		model.addAttribute("listbon", listbon);
		model.addAttribute("listfour", listfour);
		return "modiflivraison";
	}
	@RequestMapping(value="/findonebonsortieandmodif", method = RequestMethod.GET)
	public String findonebonsortieandmodif(Model model,@RequestParam(name="id_fichesortie")Long id_fichesortie) {
		FicheSortie ficsort=new FicheSortie();
		ficsort=memoiremetier.findonefichesortie(id_fichesortie);
		model.addAttribute("ficsort", ficsort);
		List<DetailFiche> dtc=memoiremetier.detailbyfiche(ficsort.getId_fichesortie());
		model.addAttribute("libelle", ficsort.getLibelle());
		model.addAttribute("id_fiche", ficsort.getId_fichesortie());
		List<BonSortie> bonso=memoiremetier.bonbydetailfice(dtc);
		model.addAttribute("bonsortie", dtc);
		model.addAttribute("demandeappro",bonso.get(0).getDemandeappros().getTitre());
		List<DemandeAppro> listappro=memoiremetier.listappro();
		model.addAttribute("listappro",listappro);
		return "modifsortie";
	}
	@RequestMapping(value="/validerbonachat", method = RequestMethod.POST)
	public String validerbonachat(Model model,@RequestParam(name="id_bondachat")Long id_bondachat) {
		memoiremetier.validerbonachat(id_bondachat);
		return "verifbon";
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
