const Commande = require('./Commande');

class GestionnaireCommandes {
    constructor() {
        this.commandes = [];
    }

    ajouterCommande(commande) {
        this.commandes.push(commande);
    }

    annulerCommande(id) {
        this.commandes = this.commandes.filter(commande => commande.id !== id);
    }

    afficherCommandes() {
        this.commandes.forEach(commande => commande.afficherDetails());
    }

    modifierStatut(idCommande, nouveauStatut) {
        const commande = this.commandes.find(commande => commande.id === idCommande);
        if (commande) {
            commande.modifierStatut(nouveauStatut);
            console.log("Statut de la commande mis à jour avec succès");
        } else {
            console.log("Commande non trouvée");
        }
    }

    afficherDetailsCommande(idCommande) {
        const commande = this.commandes.find(commande => commande.id === idCommande);
        if (commande) {
            commande.afficherDetails();
        } else {
            console.log("Commande non trouvée");
        }
    }
}

module.exports = GestionnaireCommandes;