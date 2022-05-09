package ben.dev.appSacDos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ben.dev.appSacDos.algorithme.SacDos;
import ben.dev.appSacDos.model.Produit;
import ben.dev.appSacDos.web.ProduitService;
import ilog.concert.IloException;

@RestController
@RequestMapping("/produit")
public class ProduitController {
	private final ProduitService produitService;
	public ProduitController(ProduitService produitService) {
		this.produitService = produitService;
	}
	@GetMapping("/all")
	public ResponseEntity<List<Produit>> getAllProduits(){
		List<Produit> produits = produitService.findAllProduits();
		return new ResponseEntity<List<Produit>>(produits,HttpStatus.OK);
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<Produit> getProduitById(@PathVariable("id") Long id){
		Produit produit = produitService.findProduitById(id);
		return new ResponseEntity<Produit>(produit,HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<Produit> addProduit(@RequestBody Produit produit){
		Produit newproduit = produitService.addProduit(produit);
		return new ResponseEntity<Produit>(newproduit,HttpStatus.CREATED);
	}
	@PutMapping("/update")
	public ResponseEntity<Produit> updateProduit(@RequestBody Produit produit){
		Produit updateproduit = produitService.updateProduit(produit);
		return new ResponseEntity<Produit>(updateproduit,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduit(@PathVariable("id") Long id){
		produitService.deleteProduit(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/solution/{poid}")
	public ResponseEntity<List<Produit>> solutionSacDos(@PathVariable("poid") float poid) throws IloException{
		List<Produit> produits = produitService.findAllProduits();
		SacDos ex1;
		double [] p =new double[produits.size()];
		double [] prix =new double[produits.size()];
		for(int i=0;i<produits.size();i++) {
			p[i] = produits.get(i).getPoid();
			prix[i]=produits.get(i).getPrix();
		}
		ex1 = new SacDos (prix,p, poid);
		List<Integer> sol = ex1. getsolutionSac();
		List<Produit> produitsSolution = new ArrayList();
		Produit produit;
		for(int i=0 ;i<sol.size();i++) {
			produit = produitService.findProduitById((long)sol.get(i)+1);
			produitsSolution.add(produit);
		}
		return new ResponseEntity<List<Produit>>(produitsSolution,HttpStatus.OK);
		
		
		
	}
}
