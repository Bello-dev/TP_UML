class Produit:
    def __init__(self, id, nom, prix, quantite, categorie, fournisseur):
        self.id = id
        self.nom = nom
        self.prix = prix
        self.quantite = quantite
        self.categorie = categorie
        self.fournisseur = fournisseur

    def mettre_a_jour_produit(self, nouveau_prix, nouvelle_quantite):
        self.prix = nouveau_prix
        self.quantite = nouvelle_quantite

    def afficher_details(self):
        print(f"Produit - Nom: {self.nom}, Prix: {self.prix}, Quantité: {self.quantite}, Catégorie: {self.categorie}")