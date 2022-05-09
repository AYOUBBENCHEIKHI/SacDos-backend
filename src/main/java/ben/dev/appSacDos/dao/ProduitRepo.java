package ben.dev.appSacDos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ben.dev.appSacDos.model.Produit;

public interface ProduitRepo extends JpaRepository<Produit,Long> {
	void deleteProduitById(Long id);

	Optional<Produit> findProduitById(Long id);

}
