class Commande:
    def __init__(self, id, client, statut="En cours"):
        self.id = id
        self.client = client
        self.statut = statut
        self.produits = []

    def ajouter_produit(self, produit, quantite):
        self.produits.append((produit, quantite))

    def modifier_statut(self, nouveau_statut):
        self.statut = nouveau_statut
        print(f"Statut modifié : {self.statut}")

    def afficher_details(self):
        print(f"Commande ID: {self.id}, Statut: {self.statut}")
        print("Client associé :")
        self.client.afficher_details()
        print("Produits dans la commande :")
        for produit, quantite in self.produits:
            produit.afficher_details()
            print(f"Quantité : {quantite}")