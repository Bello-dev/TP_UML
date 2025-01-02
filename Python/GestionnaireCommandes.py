from Commande import Commande

class GestionnaireCommandes:
    def __init__(self):
        self.commandes = []

    def ajouter_commande(self, commande):
        self.commandes.append(commande)

    def annuler_commande(self, id):
        self.commandes = [commande for commande in self.commandes if commande.id != id]

    def afficher_commandes(self):
        for commande in self.commandes:
            commande.afficher_details()

    def modifier_statut(self, id_commande, nouveau_statut):
        for commande in self.commandes:
            if commande.id == id_commande:
                commande.modifier_statut(nouveau_statut)
                print("Statut de la commande mis à jour avec succès")
                return
        print("Commande non trouvée")

    def afficher_details_commande(self, id_commande):
        for commande in self.commandes:
            if commande.id == id_commande:
                commande.afficher_details()
                return
        print("Commande non trouvée")