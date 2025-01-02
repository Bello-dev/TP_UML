import java.util.ArrayList;
import java.util.List;

public class GestionnaireCommandes {
    private List<Commande> commandes;

    public GestionnaireCommandes() {
        this.commandes = new ArrayList<>();
    }

    public void ajouterCommande(Commande commande) {
        this.commandes.add(commande);
    }

    public void annulerCommande(int id) {
        this.commandes.removeIf(commande -> commande.getId() == id);
    }

    public void afficherCommandes() {
        this.commandes.forEach(Commande::afficherDetails);
    }

    public void modifierStatut(int idCommande, String nouveauStatut) {
        for (Commande commande : this.commandes) {
            if (commande.getId() == idCommande) {
                commande.modifierStatut(nouveauStatut);
                System.out.println("Statut de la commande mis à jour avec succès");
                return;
            }
        }
        System.out.println("Commande non trouvée");
    }

    public void afficherDetailsCommande(int idCommande) {
        for (Commande commande : this.commandes) {
            if (commande.getId() == idCommande) {
                commande.afficherDetails();
                return;
            }
        }
        System.out.println("Commande non trouvée");
    }

    public List<Commande> getCommandes() {
        return commandes;
    }
}