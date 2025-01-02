class Produit {
    constructor(id, nom, prix, quantite, categorie, fournisseur) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
    }

    mettreAJourProduit(nouveauPrix, nouvelleQuantite) {
        this.prix = nouveauPrix;
        this.quantite = nouvelleQuantite;
    }

    afficherDetails() {
        console.log(`Produit - Nom: ${this.nom}, Prix: ${this.prix}, Quantité: ${this.quantite}, Catégorie: ${this.categorie}`);
    }
}

module.exports = Produit;