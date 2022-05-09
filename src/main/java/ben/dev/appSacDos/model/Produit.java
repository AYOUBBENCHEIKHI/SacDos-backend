package ben.dev.appSacDos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Produit {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
	private Long id;
	private String name;
	private float poid;
	private float prix;
	private boolean sprim;
	public Produit() {
	
	}
	public Produit(String name, float poid, float prix, boolean sprim) {
		super();
		this.name = name;
		this.poid = poid;
		this.prix = prix;
		this.sprim = sprim;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPoid() {
		return poid;
	}
	public void setPoid(float poid) {
		this.poid = poid;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public boolean isSprim() {
		return sprim;
	}
	public void setSprim(boolean sprim) {
		this.sprim = sprim;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", name=" + name + ", poid=" + poid + ", prix=" + prix + ", sprim=" + sprim + "]";
	}
	
	
}
