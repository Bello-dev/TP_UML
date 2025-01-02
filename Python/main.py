import os
import json
from GestionnaireProduits import GestionnaireProduits
# from Produit import Produit
from GestionnaireCommandes import GestionnaireCommandes
# from Commande import Commande
from Client import Client
from Fournisseur import Fournisseur

def sauvegarder_produits(gestionnaire_produits):
    with open("produits.json", "w") as fichier:
        produits = [produit.__dict__ for produit in gestionnaire_produits.produits]
        json.dump(produits, fichier)

def sauvegarder_commandes(gestionnaire_commandes):
    with open("commandes.json", "w") as fichier:
        commandes = [commande.__dict__ for commande in gestionnaire_commandes.commandes]
        json.dump(commandes, fichier)

def charger_produits(gestionnaire_produits):
    if os.path.exists("produits.json"):
        with open("produits.json", "r") as fichier:
            produits = json.load(fichier)
            for produit_data in produits:
                fournisseur = Fournisseur(**produit_data['fournisseur'])
                produit = Produit(**produit_data)
                produit.fournisseur = fournisseur
                gestionnaire_produits.ajouter_produit(produit)

def charger_commandes(gestionnaire_commandes, gestionnaire_produits):
    if os.path.exists("commandes.json"):
        with open("commandes.json", "r") as fichier:
            commandes = json.load(fichier)
            for commande_data in commandes:
                client = Client(**commande_data['client'])
                commande = Commande(**commande_data)
                commande.client = client
                for produit_data, quantite in commande_data['produits']:
                    produit = gestionnaire_produits.rechercher_produit_par_nom(produit_data['nom'])
                    commande.ajouter_produit(produit, quantite)
                gestionnaire_commandes.ajouter_commande(commande)

def afficher_menu_principal():
    print("\n==== MENU PRINCIPAL ====")
    print("1. Gestion du stock des produits")
    print("2. Gestion des commandes")
    print("0. Quitter")
    print("========================")
    return int(input("Choix : "))

def afficher_menu_produits():
    print("\n==== GESTION DU STOCK DES PRODUITS ====")
    print("1. Ajouter un produit")
    print("2. Modifier le prix et la quantite d'un produit")
    print("3. Rechercher un produit par nom")
    print("4. Rechercher un produit par catégorie")
    print("5. Rechercher un produit par fournisseur")
    print("6. Afficher tous les produits")
    print("0. Retour")
    print("======================================")
    return int(input("Choix : "))

def afficher_menu_commandes():
    print("\n==== GESTION DES COMMANDES ====")
    print("1. Créer une commande")
    print("2. Modifier le statut d'une commande")
    print("3. Annuler une commande")
    print("4. Afficher toutes les commandes")
    print("5. Afficher les détails d'une commande")
    print("0. Retour")
    return int(input("Choix : "))

def main():
    gestionnaire_produits = GestionnaireProduits()
    gestionnaire_commandes = GestionnaireCommandes()

    os.system('clear')
    # Charger les données existantes
    charger_produits(gestionnaire_produits)
    charger_commandes(gestionnaire_commandes, gestionnaire_produits)

    while True:
        choix_principal = afficher_menu_principal()
        if choix_principal == 0:
            os.system('clear')
            sauvegarder_produits(gestionnaire_produits)
            sauvegarder_commandes(gestionnaire_commandes)
            print("Au revoir !")
            break

        elif choix_principal == 1:
            os.system('clear')
            while True:
                choix_produits = afficher_menu_produits()
                if choix_produits == 0:
                    os.system('clear')
                    break
                elif choix_produits == 1:
                    os.system('clear')
                    nom = input("Nom du produit : ")
                    prix = float(input("Prix du produit : "))
                    quantite = int(input("Quantité du produit : "))
                    categorie = input("Catégorie du produit : ")
                    fournisseur_nom = input("Nom du fournisseur : ")
                    fournisseur_contact = input("Contact du fournisseur : ")
                    fournisseur_type = input("Type de produit du fournisseur : ")

                    fournisseur = Fournisseur(0, fournisseur_nom, fournisseur_contact, fournisseur_type)
                    produit = Produit(0, nom, prix, quantite, categorie, fournisseur)
                    gestionnaire_produits.ajouter_produit(produit)

                    sauvegarder_produits(gestionnaire_produits)
                    print("Produit ajouté avec succès.")
                elif choix_produits == 2:
                    os.system('clear')
                    id = int(input("ID du produit à modifier : "))
                    prix = float(input("Nouveau prix : "))
                    quantite = int(input("Nouvelle quantité : "))
                    gestionnaire_produits.modifier_produit(id, prix, quantite)
                    sauvegarder_produits(gestionnaire_produits)
                    print("Produit modifié avec succès.")
                elif choix_produits == 3:
                    os.system('clear')
                    nom = input("Nom du produit à rechercher : ")
                    produit = gestionnaire_produits.rechercher_produit_par_nom(nom)
                    print("Produit trouvé :")
                    produit.afficher_details()
                elif choix_produits == 4:
                    os.system('clear')
                    categorie = input("Catégorie à rechercher : ")
                    gestionnaire_produits.rechercher_par_categorie(categorie)
                elif choix_produits == 5:
                    os.system('clear')
                    fournisseur = input("Nom du fournisseur à rechercher : ")
                    gestionnaire_produits.rechercher_par_fournisseur(fournisseur)
                elif choix_produits == 6:
                    os.system('clear')
                    gestionnaire_produits.afficher_produits()
                else:
                    os.system('clear')
                    print("Choix invalide.")

        elif choix_principal == 2:
            os.system('clear')
            while True:
                choix_commandes = afficher_menu_commandes()
                if choix_commandes == 0:
                    os.system('clear')
                    break
                elif choix_commandes == 1:
                    os.system('clear')
                    nom_client = input("Nom du client : ")
                    adresse_client = input("Adresse du client : ")
                    client = Client(0, nom_client, adresse_client)
                    commande = Commande(0, client)

                    while True:
                        produit_nom = input("Nom du produit à ajouter : ")
                        produit = gestionnaire_produits.rechercher_produit_par_nom(produit_nom)
                        quantite = int(input("Quantité désirée : "))
                        commande.ajouter_produit(produit, quantite)
                        ajouter_produit = input("Ajouter un autre produit ? (1: Oui, 0: Non) : ")
                        if ajouter_produit == '0':
                            break

                    gestionnaire_commandes.ajouter_commande(commande)
                    sauvegarder_commandes(gestionnaire_commandes)
                    print("Commande ajoutée avec succès.")
                elif choix_commandes == 2:
                    os.system('clear')
                    id_commande = int(input("ID de la commande à modifier : "))
                    statut = input("Nouveau statut (en cours/terminée/annulée) : ")
                    gestionnaire_commandes.modifier_statut(id_commande, statut)
                elif choix_commandes == 3:
                    os.system('clear')
                    id_commande = int(input("ID de la commande à annuler : "))
                    gestionnaire_commandes.annuler_commande(id_commande)
                    print("Commande annulée avec succès.")
                elif choix_commandes == 4:
                    os.system('clear')
                    gestionnaire_commandes.afficher_commandes()
                elif choix_commandes == 5:
                    os.system('clear')
                    id_commande = int(input("ID de la commande à afficher : "))
                    gestionnaire_commandes.afficher_details_commande(id_commande)
                else:
                    os.system('clear')
                    print("Choix invalide.")

if __name__ == "__main__":
    main()