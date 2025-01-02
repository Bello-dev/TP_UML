const fs = require('fs');
const path = require('path');
const GestionnaireProduits = require('./GestionnaireProduits');
const GestionnaireCommandes = require('./GestionnaireCommandes');
const Produit = require('./Produit');
const Commande = require('./Commande');
const Client = require('./Client');
const Fournisseur = require('./Fournisseur');

const gestionnaireProduits = new GestionnaireProduits();
const gestionnaireCommandes = new GestionnaireCommandes();

function sauvegarderProduits() {
    const data = JSON.stringify(gestionnaireProduits.produits, null, 2);
    fs.writeFileSync(path.join(__dirname, 'produits.json'), data);
}

function sauvegarderCommandes() {
    const data = JSON.stringify(gestionnaireCommandes.commandes, null, 2);
    fs.writeFileSync(path.join(__dirname, 'commandes.json'), data);
}

function chargerProduits() {
    if (fs.existsSync(path.join(__dirname, 'produits.json'))) {
        const data = fs.readFileSync(path.join(__dirname, 'produits.json'));
        const produits = JSON.parse(data);
        produits.forEach(produitData => {
            const fournisseur = new Fournisseur(
                produitData.fournisseur.id,
                produitData.fournisseur.nom,
                produitData.fournisseur.contact,
                produitData.fournisseur.typeProduit
            );
            const produit = new Produit(
                produitData.id,
                produitData.nom,
                produitData.prix,
                produitData.quantite,
                produitData.categorie,
                fournisseur
            );
            gestionnaireProduits.ajouterProduit(produit);
        });
    }
}

function chargerCommandes() {
    if (fs.existsSync(path.join(__dirname, 'commandes.json'))) {
        const data = fs.readFileSync(path.join(__dirname, 'commandes.json'));
        const commandes = JSON.parse(data);
        commandes.forEach(commandeData => {
            const client = new Client(
                commandeData.client.id,
                commandeData.client.nom,
                commandeData.client.adresse
            );
            const commande = new Commande(
                commandeData.id,
                client,
                commandeData.statut
            );
            commandeData.produits.forEach(({ produit, quantite }) => {
                const fournisseur = new Fournisseur(
                    produit.fournisseur.id,
                    produit.fournisseur.nom,
                    produit.fournisseur.contact,
                    produit.fournisseur.typeProduit
                );
                const prod = new Produit(
                    produit.id,
                    produit.nom,
                    produit.prix,
                    produit.quantite,
                    produit.categorie,
                    fournisseur
                );
                commande.ajouterProduit(prod, quantite);
            });
            gestionnaireCommandes.ajouterCommande(commande);
        });
    }
}

function clearConsole() {
    process.stdout.write('\033c');
}

function afficherMenuPrincipal() {
    console.log("\n==== MENU PRINCIPAL ====");
    console.log("1. Gestion du stock des produits");
    console.log("2. Gestion des commandes");
    console.log("0. Quitter");
    console.log("========================");
    return parseInt(prompt("Choix : "));
}

function afficherMenuProduits() {
    console.log("\n==== GESTION DU STOCK DES PRODUITS ====");
    console.log("1. Ajouter un produit");
    console.log("2. Modifier le prix et le stock d'un produit");
    console.log("3. Rechercher un produit par nom");
    console.log("4. Rechercher un produit par catégorie");
    console.log("5. Rechercher un produit par fournisseur");
    console.log("6. Afficher tous les produits");
    console.log("0. Retour");
    console.log("======================================");
    return parseInt(prompt("Choix : "));
}

function afficherMenuCommandes() {
    console.log("\n==== GESTION DES COMMANDES ====");
    console.log("1. Créer une commande");
    console.log("2. Modifier le statut d'une commande");
    console.log("3. Annuler une commande");
    console.log("4. Afficher toutes les commandes");
    console.log("5. Afficher les détails d'une commande");
    console.log("0. Retour");
    return parseInt(prompt("Choix : "));
}

function prompt(question) {
    const readlineSync = require('readline-sync');
    return readlineSync.question(question);
}

function main() {
    clearConsole();
    chargerProduits();
    chargerCommandes();

    while (true) {
        const choixPrincipal = afficherMenuPrincipal();
        if (choixPrincipal === 0) {
            clearConsole();
            sauvegarderProduits();
            sauvegarderCommandes();
            console.log("Au revoir !");
            break;
        } else if (choixPrincipal === 1) {
            clearConsole();
            while (true) {
                const choixProduits = afficherMenuProduits();
                if (choixProduits === 0) {
                    clearConsole();
                    break;
                } else if (choixProduits === 1) {
                    clearConsole();
                    const nom = prompt("Nom du produit : ");
                    const prix = parseFloat(prompt("Prix du produit : "));
                    const stock = parseInt(prompt("Stock du produit : "));
                    const categorie = prompt("Catégorie du produit : ");
                    const fournisseurNom = prompt("Nom du fournisseur : ");
                    const fournisseurContact = prompt("Contact du fournisseur : ");
                    const fournisseurType = prompt("Type de produit du fournisseur : ");

                    const fournisseur = new Fournisseur(0, fournisseurNom, fournisseurContact, fournisseurType);
                    const produit = new Produit(0, nom, prix, stock, categorie, fournisseur);
                    gestionnaireProduits.ajouterProduit(produit);

                    sauvegarderProduits();
                    console.log("Produit ajouté avec succès.");
                } else if (choixProduits === 2) {
                    clearConsole();
                    const id = parseInt(prompt("ID du produit à modifier : "));
                    const prix = parseFloat(prompt("Nouveau prix : "));
                    const stock = parseInt(prompt("Nouveau stock : "));
                    gestionnaireProduits.modifierProduit(id, prix, stock);
                    sauvegarderProduits();
                    console.log("Produit modifié avec succès.");
                } else if (choixProduits === 3) {
                    clearConsole();
                    const nom = prompt("Nom du produit à rechercher : ");
                    const produit = gestionnaireProduits.rechercherProduitParNom(nom);
                    console.log("Produit trouvé :");
                    produit.afficherDetails();
                } else if (choixProduits === 4) {
                    clearConsole();
                    const categorie = prompt("Catégorie à rechercher : ");
                    gestionnaireProduits.rechercherParCategorie(categorie);
                } else if (choixProduits === 5) {
                    clearConsole();
                    const fournisseur = prompt("Nom du fournisseur à rechercher : ");
                    gestionnaireProduits.rechercherParFournisseur(fournisseur);
                } else if (choixProduits === 6) {
                    clearConsole();
                    gestionnaireProduits.afficherProduits();
                } else {
                    clearConsole();
                    console.log("Choix invalide.");
                }
            }
        } else if (choixPrincipal === 2) {
            clearConsole();
            while (true) {
                const choixCommandes = afficherMenuCommandes();
                if (choixCommandes === 0) {
                    clearConsole();
                    break;
                } else if (choixCommandes === 1) {
                    clearConsole();
                    const nomClient = prompt("Nom du client : ");
                    const adresseClient = prompt("Adresse du client : ");
                    const client = new Client(0, nomClient, adresseClient);
                    const commande = new Commande(0, client);

                    while (true) {
                        const produitNom = prompt("Nom du produit à ajouter : ");
                        const produit = gestionnaireProduits.rechercherProduitParNom(produitNom);
                        const quantite = parseInt(prompt("Quantité désirée : "));
                        commande.ajouterProduit(produit, quantite);
                        const ajouterProduit = prompt("Ajouter un autre produit ? (1: Oui, 0: Non) : ");
                        if (ajouterProduit === '0') break;
                    }

                    gestionnaireCommandes.ajouterCommande(commande);
                    sauvegarderCommandes();
                    console.log("Commande ajoutée avec succès.");
                } else if (choixCommandes === 2) {
                    clearConsole();
                    const idCommande = parseInt(prompt("ID de la commande à modifier : "));
                    const statut = prompt("Nouveau statut (en cours/terminée/annulée) : ");
                    gestionnaireCommandes.modifierStatut(idCommande, statut);
                } else if (choixCommandes === 3) {
                    clearConsole();
                    const idCommande = parseInt(prompt("ID de la commande à annuler : "));
                    gestionnaireCommandes.annulerCommande(idCommande);
                    console.log("Commande annulée avec succès.");
                } else if (choixCommandes === 4) {
                    clearConsole();
                    gestionnaireCommandes.afficherCommandes();
                } else if (choixCommandes === 5) {
                    clearConsole();
                    const idCommande = parseInt(prompt("ID de la commande à afficher : "));
                    gestionnaireCommandes.afficherDetailsCommande(idCommande);
                } else {
                    clearConsole();
                    console.log("Choix invalide.");
                }
            }
        } else {
            clearConsole();
            console.log("Choix invalide.");
        }
    }
}

main();