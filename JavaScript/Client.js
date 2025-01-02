class Client {
    constructor(id, nom, adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    afficherDetails() {
        console.log(`Client - Nom: ${this.nom}, Adresse: ${this.adresse}`);
    }
}

module.exports = Client;