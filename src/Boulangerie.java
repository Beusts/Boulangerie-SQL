public class Boulangerie {
    public static String adresse = "sql7.freesqldatabase.com";
    public static String bd = "sql7617285";
    public static String login = "sql7617285";
    public static String password = "2Rg7ywak4s";

    public static void ajouter() {
        int choix;
        Ecran.afficherln("1. Ajouter un client");
        Ecran.afficherln("2. Ajouter un pain");
        Ecran.afficherln("3. Ajouter une commande");
        Ecran.afficherln("4. Ajouter un melange");
        Ecran.afficherln("5. Ajouter un approvisionnement");
        Ecran.afficherln("6. Quitter");
        choix = Clavier.saisirInt();
        switch (choix) {
            case 1:
                ajouterClient();
                break;
            case 2:
                ajouterPain();
                break;
            case 3:
                ajouterLivraison();
                break;
            case 4:
                ajouterMelange();
                break;
            case 5:
                ajouterApprovisionnement();
                break;
            case 6:
                break;
            default:
                Ecran.afficherln("Erreur de saisie");
                ajouter();
                break;
        }
    }

    public static void ajouterClient() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res, id;
        String nom, ville, adresse;

        // creation de la requête
        Ecran.afficherln("Saisir le nom du client");
        nom = Clavier.saisirString();
        Ecran.afficherln("Saisir la ville du client");
        ville = Clavier.saisirString();
        Ecran.afficherln("Saisir l'adresse du client");
        adresse = Clavier.saisirString();
        id = BD.executerUpdate(connexion, "UPDATE CLIENTE SET IDCli = IDCli");
        String sql = "INSERT INTO CLIENTE VALUES (" + (id + 1) + ", '" + nom + "', '" + adresse + "', '" + ville + "')";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void ajouterPain() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res, id, idMelange = 0;
        String desc, descMelange;
        double prix;

        // creation de la requête
        Ecran.afficherln("Saisir la description du pain");
        desc = Clavier.saisirString();
        Ecran.afficherln("Saisir la description du melange du pain");
        descMelange = Clavier.saisirString();
        res = BD.executerSelect(connexion, "SELECT IDMelange FROM MELANGE WHERE DescMelange = '" + descMelange + "'");
        while (BD.suivant(res)) {
            idMelange = BD.attributInt(res, "IDMelange");
        }
        Ecran.afficherln("Saisir le prix du pain");
        prix = Clavier.saisirDouble();
        id = BD.executerUpdate(connexion, "UPDATE PAIN SET IDPain = IDPain");
        String sql = "INSERT INTO PAIN VALUES (" + (id + 1) + ", '" + desc + "', " + prix + ", " + idMelange + ")";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void ajouterLivraison() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res, idPain = 0, nbPain, idCli = 0;
        String date, nomCli, nomPain;

        // creation de la requête
        Ecran.afficherln("Saisir le nom du client");
        nomCli = Clavier.saisirString();
        res = BD.executerSelect(connexion, "SELECT IDCli FROM CLIENTE WHERE NomCli = '" + nomCli + "'");
        while (BD.suivant(res)) {
            idCli = BD.attributInt(res, "IDCli");
        }
        BD.fermerResultat(res);

        Ecran.afficherln("Saisir le nom du pain");
        nomPain = Clavier.saisirString();
        res = BD.executerSelect(connexion, "SELECT IDPain FROM PAIN WHERE DescPain = '" + nomPain + "'");
        while (BD.suivant(res)) {
            idPain = BD.attributInt(res, "IDPain");
        }
        BD.fermerResultat(res);

        Ecran.afficherln("Saisir la date de livraison");
        date = Clavier.saisirString();
        Ecran.afficherln("Saisir le nombre de pain");
        nbPain = Clavier.saisirInt();
        String sql = "INSERT INTO LIVRER VALUES (" + idCli + ", " + idPain + ", '" + date + "', " + nbPain + ")";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void ajouterMelange() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res, id;
        String desc;

        // creation de la requête
        Ecran.afficherln("Saisir la description du melange");
        desc = Clavier.saisirString();
        id = BD.executerUpdate(connexion, "UPDATE MELANGE SET IDMelange = IDMelange");
        String sql = "INSERT INTO MELANGE VALUES (" + (id + 1) + ", '" + desc + "')";

        //envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void ajouterApprovisionnement() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res, semaine, quantite;
        int idMelange = 0;
        String descMelange;

        // creation de la requête
        Ecran.afficherln("Saisir la description du melange");
        descMelange = Clavier.saisirString();
        res = BD.executerSelect(connexion, "SELECT IDMelange FROM MELANGE WHERE DescMelange = '" + descMelange + "'");
        while (BD.suivant(res)) {
            idMelange = BD.attributInt(res, "IDMelange");
        }

        Ecran.afficherln("Saisir la semaine de l'approvisionnement");
        semaine = Clavier.saisirInt();
        while (semaine < 1 || semaine > 52) {
            Ecran.afficherln("Saisir la semaine de l'approvisionnement");
            semaine = Clavier.saisirInt();
        }
        Ecran.afficherln("Saisir la quantité de pain");
        quantite = Clavier.saisirInt();
        String sql = "INSERT INTO APPROVISIONNER VALUES (" + idMelange + ", " + semaine + ", " + quantite + ")";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void afficher() {
        int choix;
        Ecran.afficherln("1. Afficher les pains");
        Ecran.afficherln("2. Afficher les melanges");
        Ecran.afficherln("3. Afficher les livraisons");
        Ecran.afficherln("4. Afficher les approvisionnements");
        Ecran.afficherln("5. Afficher les clients");
        Ecran.afficherln("6. Afficher les quantités de melanges nécessaire pour les commandes de la semaine prochaine");
        Ecran.afficherln("7. Afficher facture");
        Ecran.afficherln("8. Quitter");

        choix = Clavier.saisirInt();
        switch (choix) {
            case 1:
                afficherPain();
                break;
            case 2:
                afficherMelange();
                break;
            case 3:
                afficherLivraison();
                break;
            case 4:
                afficherApprovisionnement();
                break;
            case 5:
                afficherClient();
                break;
            case 6:
                totalMelangeLivrer();
                break;
            case 7:
                afficherFacture();
                break;
            case 8:
                break;
            default:
                Ecran.afficherln("Erreur de saisie");
                break;
        }

    }

    public static void afficherPain() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res;
        String sql = "SELECT * FROM PAIN NATURAL JOIN MELANGE";
        res = BD.executerSelect(connexion, sql);
        System.out.printf("%-10s | %-20s | %-10s | %-10s \n", "IDPain", "DescPain", "PrixPainHT", "Melange");
        while (BD.suivant(res)) {
            int idPain = BD.attributInt(res, "IDPain");
            String descPain = BD.attributString(res, "DescPain");
            int prixPain = BD.attributInt(res, "PrixPainHT");
            String melange = BD.attributString(res, "DescMelange");
            System.out.printf("%-10d | %-20s | %-10d | %-10s\n", idPain, descPain, prixPain, melange);
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void afficherMelange() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res;
        String sql = "SELECT * FROM MELANGE";
        res = BD.executerSelect(connexion, sql);
        System.out.printf("%-10s | %-20s\n", "IDMelange", "DescMelange");
        while (BD.suivant(res)) {
            int idMelange = BD.attributInt(res, "IDMelange");
            String descMelange = BD.attributString(res, "DescMelange");
            System.out.printf("%-10d | %-20s\n", idMelange, descMelange);
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void afficherLivraison() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res;
        String sql = "SELECT * FROM LIVRER NATURAL JOIN PAIN NATURAL JOIN CLIENTE";
        res = BD.executerSelect(connexion, sql);
        System.out.printf("%-10s | %-20s | %-15s | %-10s\n", "Client", "Pain", "DateLivraison", "NombreDePains");
        while (BD.suivant(res)) {
            String client = BD.attributString(res, "NomCli");
            String pain = BD.attributString(res, "DescPain");
            String dateLivraison = BD.attributString(res, "DateLivraison");
            int nombreDePains = BD.attributInt(res, "NombreDePains");
            System.out.printf("%-10s | %-20s | %-15s | %-10d\n", client, pain, dateLivraison, nombreDePains);
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void afficherApprovisionnement() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res;
        String sql = "SELECT * FROM APPROVISIONNER NATURAL JOIN MELANGE";
        res = BD.executerSelect(connexion, sql);
        System.out.printf("%-20s | %-15s | %-20s\n", "Melange", "SemaineAppro", "QuantiteMelange");
        while (BD.suivant(res)) {
            String idMelange = BD.attributString(res, "DescMelange");
            int semaineAppro = BD.attributInt(res, "SemaineAppro");
            int quantiteMelange = BD.attributInt(res, "QuantiteMelange");
            System.out.printf("%-20s | %-15d | %-20d\n", idMelange, semaineAppro, quantiteMelange);
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void afficherClient() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res;
        String sql = "SELECT * FROM CLIENTE";
        res = BD.executerSelect(connexion, sql);
        System.out.printf("%-10s | %-10s | %-20s | %-10s\n", "IDClient", "NomClient", "AdresseClient", "VilleClient");
        while (BD.suivant(res)) {
            int idClient = BD.attributInt(res, "IDCli");
            String nomClient = BD.attributString(res, "NomCli");
            String adresseClient = BD.attributString(res, "AdrCli");
            String villeClient = BD.attributString(res, "VilleCli");
            System.out.printf("%-10d | %-10s | %-20s | %-10s\n", idClient, nomClient, adresseClient, villeClient);
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void totalMelangeLivrer() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res;
        // Récupère le nombre de melange pour les commandes de la semaine prochaine
        String sql = "SELECT SUM(NombreDePains), DescMelange FROM LIVRER NATURAL JOIN PAIN NATURAL JOIN MELANGE WHERE DateLivraison " +
                "BETWEEN CURRENT_DATE() AND (DATE_ADD(CURRENT_DATE(), INTERVAL 7 DAY)) GROUP BY IDMelange";
        res = BD.executerSelect(connexion, sql);
        Ecran.afficherln("Quantités de melanges nécessaire pour les commandes de la semaine prochaine :\n");
        //Affiche les quantités de melanges nécessaire pour les commandes de la semaine prochaine
        while (BD.suivant(res)) {
            int quantiteMelange = BD.attributInt(res, "SUM(NombreDePains)");
            String descMelange = BD.attributString(res, "DescMelange");
            System.out.printf("%-20s | %-20d\n", descMelange, quantiteMelange);
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void afficherFacture() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        String nomCli;
        int res, idCli = 0;
        float totalHT = 0;

        Ecran.afficherln("Saisir le nom du client");
        nomCli = Clavier.saisirString();
        res = BD.executerSelect(connexion, "SELECT IDCli FROM CLIENTE WHERE NomCli = '" + nomCli + "'");
        while (BD.suivant(res)) {
            idCli = BD.attributInt(res, "IDCli");
        }
        BD.fermerResultat(res);
        res = BD.executerSelect(connexion, "SELECT DateLivraison, DescPain, PrixPainHT FROM LIVRER NATURAL JOIN PAIN " +
                "WHERE DateLivraison BETWEEN (CURRENT_DATE()-DAY(CURRENT_DATE()) + 1 ) AND LAST_DAY(CURRENT_DATE()) AND IDCli = " + idCli);
        System.out.printf("%-15s | %-15s | %-10s | %s\n", "DateLivraison", "DescPain", "PrixPainHT", "Prix");
        while (BD.suivant(res)) {
            String date = BD.attributString(res, "DateLivraison");
            String pain = BD.attributString(res, "DescPain");
            float prix = BD.attributLong(res, "PrixPainHT");
            totalHT += prix;
            System.out.printf("%-15s | %-15s | %-10.2f | %.2f\n", date, pain, prix, prix * 1.055);
        }

        System.out.printf("%-15s | %-15s | %-10.2f | %.2f\n", "Total", "", totalHT, totalHT * 1.055);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void menu() {
        int choix;
        Ecran.afficherln("1. Ajouter");
        Ecran.afficherln("2. Afficher");
        Ecran.afficherln("3. Quitter");

        choix = Clavier.saisirInt();
        switch (choix) {
            case 1:
                ajouter();
                menu();
                break;
            case 2:
                afficher();
                menu();
                break;
            case 3:
                break;
            default:
                Ecran.afficherln("Erreur de saisie");
                menu();
                break;
        }

    }

    public static void main(String[] args) {
        menu();
    }
}