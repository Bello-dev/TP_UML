class Commande {
    constructor(id, client, statut = "En cours") {
        this.id = id;
        this.client = client;
        this.statut = statut;
        this.produits = [];
    }

    ajouterProduit(produit, quantite) {
        this.produits.push({ produit, quantite });
    }

    modifierStatut(nouveauStatut) {
        this.statut = nouveauStatut;
        console.log(`Statut modifié : ${this.statut}`);
    }

    afficherDetails() {
        console.log(`Commande ID: ${this.id}, Statut: ${this.statut}`);
        console.log("Client associé :");
        this.client.afficherDetails();
        console.log("Produits dans la commande :");
        this.produits.forEach(({ produit, quantite }) => {
            produit.afficherDetails();
            console.log(`Quantité : ${quantite}`);
        });
    }
}

module.exports = Commande;