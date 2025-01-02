import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestionnaireProduits gestionnaireProduits = new GestionnaireProduits();
    private static final GestionnaireCommandes gestionnaireCommandes = new GestionnaireCommandes();

    public static void main(String[] args) {
        clearConsole();
        chargerProduits();
        chargerCommandes();

        while (true) {
            int choixPrincipal = afficherMenuPrincipal();
            if (choixPrincipal == 0) {
                clearConsole();
                sauvegarderProduits();
                sauvegarderCommandes();
                System.out.println("Au revoir !");
                break;
            } else if (choixPrincipal == 1) {
                clearConsole();
                while (true) {
                    int choixProduits = afficherMenuProduits();
                    if (choixProduits == 0) {
                        clearConsole();
                        break;
                    } else if (choixProduits == 1) {
                        clearConsole();
                        ajouterProduit();
                    } else if (choixProduits == 2) {
                        clearConsole();
                        modifierProduit();
                    } else if (choixProduits == 3) {
                        clearConsole();
                        rechercherProduitParNom();
                    } else if (choixProduits == 4) {
                        clearConsole();
                        rechercherParCategorie();
                    } else if (choixProduits == 5) {
                        clearConsole();
                        rechercherParFournisseur();
                    } else if (choixProduits == 6) {
                        clearConsole();
                        gestionnaireProduits.afficherProduits();
                    } else {
                        clearConsole();
                        System.out.println("Choix invalide.");
                    }
                }
            } else if (choixPrincipal == 2) {
                clearConsole();
                while (true) {
                    int choixCommandes = afficherMenuCommandes();
                    if (choixCommandes == 0) {
                        clearConsole();
                        break;
                    } else if (choixCommandes == 1) {
                        clearConsole();
                        creerCommande();
                    } else if (choixCommandes == 2) {
                        clearConsole();
                        modifierStatutCommande();
                    } else if (choixCommandes == 3) {
                        clearConsole();
                        annulerCommande();
                    } else if (choixCommandes == 4) {
                        clearConsole();
                        gestionnaireCommandes.afficherCommandes();
                    } else if (choixCommandes == 5) {
                        clearConsole();
                        afficherDetailsCommande();
                    } else {
                        clearConsole();
                        System.out.println("Choix invalide.");
                    }
                }
            } else {
                clearConsole();
                System.out.println("Choix invalide.");
            }
        }
    }

    private static void ajouterProduit() {
        System.out.print("Nom du produit : ");
        String nom = scanner.nextLine();
        System.out.print("Prix du produit : ");
        double prix = scanner.nextDouble();
        System.out.print("quantité du produit : ");
        int quantité = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Catégorie du produit : ");
        String categorie = scanner.nextLine();
        System.out.print("Nom du fournisseur : ");
        String fournisseurNom = scanner.nextLine();
        System.out.print("Contact du fournisseur : ");
        String fournisseurContact = scanner.nextLine();
        System.out.print("Type de produit du fournisseur : ");
        String fournisseurType = scanner.nextLine();

        Fournisseur fournisseur = new Fournisseur(0, fournisseurNom, fournisseurContact, fournisseurType);
        Produit produit = new Produit(0, nom, prix, quantité, categorie, fournisseur);
        gestionnaireProduits.ajouterProduit(produit);

        sauvegarderProduits();
        System.out.println("Produit ajouté avec succès.");
    }

    private static void modifierProduit() {
        System.out.print("ID du produit à modifier : ");
        int id = scanner.nextInt();
        System.out.print("Nouveau prix : ");
        double prix = scanner.nextDouble();
        System.out.print("Nouveau quantité : ");
        int quantité = scanner.nextInt();
        gestionnaireProduits.modifierProduit(id, prix, quantité);
        sauvegarderProduits();
        System.out.println("Produit modifié avec succès.");
    }

    private static void rechercherProduitParNom() {
        System.out.print("Nom du produit à rechercher : ");
        String nom = scanner.nextLine();
        Produit produit = gestionnaireProduits.rechercherProduitParNom(nom);
        System.out.println("Produit trouvé :");
        produit.afficherDetails();
    }

    private static void rechercherParCategorie() {
        System.out.print("Catégorie à rechercher : ");
        String categorie = scanner.nextLine();
        gestionnaireProduits.rechercherParCategorie(categorie);
    }

    private static void rechercherParFournisseur() {
        System.out.print("Nom du fournisseur à rechercher : ");
        String fournisseur = scanner.nextLine();
        gestionnaireProduits.rechercherParFournisseur(fournisseur);
    }

    private static void creerCommande() {
        System.out.print("Nom du client : ");
        String nomClient = scanner.nextLine();
        System.out.print("Adresse du client : ");
        String adresseClient = scanner.nextLine();
        Client client = new Client(0, nomClient, adresseClient);
        Commande commande = new Commande(0, client, "En cours");

        while (true) {
            System.out.print("Nom du produit à ajouter : ");
            String produitNom = scanner.nextLine();
            Produit produit = gestionnaireProduits.rechercherProduitParNom(produitNom);
            System.out.print("Quantité désirée : ");
            int quantite = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            commande.ajouterProduit(produit, quantite);
            System.out.print("Ajouter un autre produit ? (1: Oui, 0: Non) : ");
            int ajouterProduit = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            if (ajouterProduit == 0) break;
        }

        gestionnaireCommandes.ajouterCommande(commande);
        sauvegarderCommandes();
        System.out.println("Commande ajoutée avec succès.");
    }

    private static void modifierStatutCommande() {
        System.out.print("ID de la commande à modifier : ");
        int idCommande = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Nouveau statut (en cours/terminée/annulée) : ");
        String statut = scanner.nextLine();
        gestionnaireCommandes.modifierStatut(idCommande, statut);
    }

    private static void annulerCommande() {
        System.out.print("ID de la commande à annuler : ");
        int idCommande = scanner.nextInt();
        gestionnaireCommandes.annulerCommande(idCommande);
        System.out.println("Commande annulée avec succès.");
    }

    private static void afficherDetailsCommande() {
        System.out.print("ID de la commande à afficher : ");
        int idCommande = scanner.nextInt();
        gestionnaireCommandes.afficherDetailsCommande(idCommande);
    }

    private static int afficherMenuPrincipal() {
        System.out.println("\n==== MENU PRINCIPAL ====");
        System.out.println("1. Gestion du quantité des produits");
        System.out.println("2. Gestion des commandes");
        System.out.println("0. Quitter");
        System.out.println("========================");
        return scanner.nextInt();
    }

    private static int afficherMenuProduits() {
        System.out.println("\n==== GESTION DU quantité DES PRODUITS ====");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier le prix et le quantité d'un produit");
        System.out.println("3. Rechercher un produit par nom");
        System.out.println("4. Rechercher un produit par catégorie");
        System.out.println("5. Rechercher un produit par fournisseur");
        System.out.println("6. Afficher tous les produits");
        System.out.println("0. Retour");
        System.out.println("======================================");
        return scanner.nextInt();
    }

    private static int afficherMenuCommandes() {
        System.out.println("\n==== GESTION DES COMMANDES ====");
        System.out.println("1. Créer une commande");
        System.out.println("2. Modifier le statut d'une commande");
        System.out.println("3. Annuler une commande");
        System.out.println("4. Afficher toutes les commandes");
        System.out.println("5. Afficher les détails d'une commande");
        System.out.println("0. Retour");
        return scanner.nextInt();
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sauvegarderProduits() {
        // Implémentez la sauvegarde des produits ici (suggestion: utilisez la sérialisation ou un fichier texte)
    }

    private static void sauvegarderCommandes() {
        // Implémentez la sauvegarde des commandes ici (suggestion: utilisez la sérialisation ou un fichier texte)
    }

    private static void chargerProduits() {
        // Implémentez le chargement des produits ici (suggestion: utilisez la désérialisation ou un fichier texte)
    }

    private static void chargerCommandes() {
        // Implémentez le chargement des commandes ici (suggestion: utilisez la désérialisation ou un fichier texte)
    }
}