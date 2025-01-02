class Client:
    def __init__(self, id, nom, adresse):
        self.id = id
        self.nom = nom
        self.adresse = adresse

    def afficher_details(self):
        print(f"Client - Nom: {self.nom}, Adresse: {self.adresse}")