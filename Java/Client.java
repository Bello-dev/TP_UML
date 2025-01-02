public class Client {
    private int id;
    private String nom;
    private String adresse;

    public Client(int id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    public void afficherDetails() {
        System.out.println("Client - Nom: " + this.nom + ", Adresse: " + this.adresse);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }
}