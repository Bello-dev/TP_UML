class Fournisseur {
    constructor(id, nom, contact, typeProduit) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
        this.typeProduit = typeProduit;
    }

    afficherDetails() {
        console.log(`Fournisseur - Nom: ${this.nom}, Contact: ${this.contact}, Type de produit fourni: ${this.typeProduit}`);
    }
}

module.exports = Fournisseur;