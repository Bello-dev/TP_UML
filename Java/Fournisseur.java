public class Fournisseur {
    private int id;
    private String nom;
    private String contact;
    private String typeProduit;

    public Fournisseur(int id, String nom, String contact, String typeProduit) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
        this.typeProduit = typeProduit;
    }

    public void afficherDetails() {
        System.out.println("Fournisseur - Nom: " + this.nom + ", Contact: " + this.contact + ", Type de produit fourni: " + this.typeProduit);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getContact() {
        return contact;
    }

    public String getTypeProduit() {
        return typeProduit;
    }
}