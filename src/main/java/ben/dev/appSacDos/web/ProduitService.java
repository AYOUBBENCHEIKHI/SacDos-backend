package ben.dev.appSacDos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ben.dev.appSacDos.dao.ProduitRepo;
import ben.dev.appSacDos.exception.UserNotFoundException;
import ben.dev.appSacDos.model.Produit;

@Service
public class ProduitService {
	private final ProduitRepo produitRepo;
	@Autowired
	public ProduitService(ProduitRepo produitRepo) {
		this.produitRepo = produitRepo;
	}
	
	public Produit addProduit(Produit produit) {
		return produitRepo.save(produit);
	}
	public List<Produit> findAllProduits(){
		return produitRepo.findAll();
	}
	public Produit updateProduit(Produit produit) {
		return produitRepo.save(produit);
	}
	public Produit findProduitById(Long id) {
		return produitRepo.findProduitById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
	}
	public void deleteProduit(Long id) {
		produitRepo.deleteProduitById(id);
	}
}
