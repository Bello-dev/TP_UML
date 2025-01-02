import java.util.ArrayList;
import java.util.List;

public class GestionnaireProduits {
    private List<Produit> produits;

    public GestionnaireProduits() {
        this.produits = new ArrayList<>();
    }

    public void ajouterProduit(Produit produit) {
        this.produits.add(produit);
    }

    public void supprimerProduit(int id) {
        this.produits.removeIf(produit -> produit.getId() == id);
    }

    public Produit rechercherProduitParNom(String nom) {
        return this.produits.stream()
                .filter(produit -> produit.getNom().equals(nom))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    public void afficherProduits() {
        this.produits.forEach(Produit::afficherDetails);
    }

    public void modifierProduit(int id, double nouveauPrix, int nouveauStock) {
        for (Produit produit : this.produits) {
            if (produit.getId() == id) {
                produit.mettreAJourProduit(nouveauPrix, nouveauStock);
                System.out.println("Produit mis à jour avec succès");
                return;
            }
        }
        System.out.println("Produit non trouvé");
    }

    public void rechercherParCategorie(String categorie) {
        this.produits.stream()
                .filter(produit -> produit.getCategorie().equals(categorie))
                .forEach(Produit::afficherDetails);
    }

    public void rechercherParFournisseur(String fournisseur) {
        this.produits.stream()
                .filter(produit -> produit.getFournisseur().getNom().equals(fournisseur))
                .forEach(Produit::afficherDetails);
    }

    public List<Produit> getProduits() {
        return produits;
    }
}