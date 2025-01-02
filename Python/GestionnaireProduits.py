from Produit import Produit

class GestionnaireProduits:
    def __init__(self):
        self.produits = []

    def ajouter_produit(self, produit):
        self.produits.append(produit)

    def supprimer_produit(self, id):
        self.produits = [produit for produit in self.produits if produit.id != id]

    def rechercher_produit_par_nom(self, nom):
        for produit in self.produits:
            if produit.nom == nom:
                return produit
        raise Exception("Produit non trouvé")

    def afficher_produits(self):
        for produit in self.produits:
            produit.afficher_details()

    def modifier_produit(self, id, nouveau_prix, nouveau_stock):
        for produit in self.produits:
            if produit.id == id:
                produit.mettre_a_jour_produit(nouveau_prix, nouveau_stock)
                print("Produit mis à jour avec succès")
                return
        print("Produit non trouvé")

    def rechercher_par_categorie(self, categorie):
        for produit in self.produits:
            if produit.categorie == categorie:
                produit.afficher_details()

    def rechercher_par_fournisseur(self, fournisseur):
        for produit in self.produits:
            if produit.fournisseur.nom == fournisseur:
                produit.afficher_details()