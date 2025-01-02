import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private Client client;
    private String statut;
    private List<ProduitQuantite> produits;

    public Commande(int id, Client client, String statut) {
        this.id = id;
        this.client = client;
        this.statut = statut;
        this.produits = new ArrayList<>();
    }

    public void ajouterProduit(Produit produit, int quantite) {
        this.produits.add(new ProduitQuantite(produit, quantite));
    }

    public void modifierStatut(String nouveauStatut) {
        this.statut = nouveauStatut;
        System.out.println("Statut modifié : " + this.statut);
    }

    public void afficherDetails() {
        System.out.println("Commande ID: " + this.id + ", Statut: " + this.statut);
        System.out.println("Client associé :");
        this.client.afficherDetails();
        System.out.println("Produits dans la commande :");
        this.produits.forEach(produitQuantite -> {
            produitQuantite.produit.afficherDetails();
            System.out.println("Quantité : " + produitQuantite.quantite);
        });
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getStatut() {
        return statut;
    }

    public List<ProduitQuantite> getProduits() {
        return produits;
    }

    private static class ProduitQuantite {
        Produit produit;
        int quantite;

        ProduitQuantite(Produit produit, int quantite) {
            this.produit = produit;
            this.quantite = quantite;
        }
    }
}