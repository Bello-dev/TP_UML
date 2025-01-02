class Fournisseur:
    def __init__(self, id, nom, contact, type_produit):
        self.id = id
        self.nom = nom
        self.contact = contact
        self.type_produit = type_produit

    def afficher_details(self):
        print(f"Fournisseur - Nom: {self.nom}, Contact: {self.contact}, Type de produit fourni: {self.type_produit}")