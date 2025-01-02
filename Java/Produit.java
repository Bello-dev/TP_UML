public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int quantite;
    private String categorie;
    private Fournisseur fournisseur;

    public Produit(int id, String nom, double prix, int quantite, String categorie, Fournisseur fournisseur) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
    }

    public void mettreAJourProduit(double nouveauPrix, int nouvelleQuantite) {
        this.prix = nouveauPrix;
        this.quantite = nouvelleQuantite;
    }

    public void afficherDetails() {
        System.out.println("Produit - Nom: " + this.nom + ", Prix: " + this.prix + ", Quantité: " + this.quantite + ", Catégorie: " + this.categorie);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getCategorie() {
        return categorie;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }
}