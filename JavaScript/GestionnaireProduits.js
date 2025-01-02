const Produit = require('./Produit');

class GestionnaireProduits {
    constructor() {
        this.produits = [];
    }

    ajouterProduit(produit) {
        this.produits.push(produit);
    }

    supprimerProduit(id) {
        this.produits = this.produits.filter(produit => produit.id !== id);
    }

    rechercherProduitParNom(nom) {
        const produit = this.produits.find(produit => produit.nom === nom);
        if (!produit) throw new Error("Produit non trouvé");
        return produit;
    }

    afficherProduits() {
        this.produits.forEach(produit => produit.afficherDetails());
    }

    modifierProduit(id, nouveauPrix, nouveauStock) {
        const produit = this.produits.find(produit => produit.id === id);
        if (produit) {
            produit.mettreAJourProduit(nouveauPrix, nouveauStock);
            console.log("Produit mis à jour avec succès");
        } else {
            console.log("Produit non trouvé");
        }
    }

    rechercherParCategorie(categorie) {
        this.produits.filter(produit => produit.categorie === categorie)
                     .forEach(produit => produit.afficherDetails());
    }

    rechercherParFournisseur(fournisseur) {
        this.produits.filter(produit => produit.fournisseur.nom === fournisseur)
                     .forEach(produit => produit.afficherDetails());
    }
}

module.exports = GestionnaireProduits;