public class Boulangerie {
    public static String adresse = "sql7.freesqldatabase.com";
    public static String bd = "sql7617285";
    public static String login = "sql7617285";
    public static String password = "2Rg7ywak4s";

    public static void ajouter() {
        int choix;
        Ecran.afficherln("1. Ajouter un client");
        Ecran.afficherln("2. Ajouter un produit");
        Ecran.afficherln("3. Ajouter une commande");
        Ecran.afficherln("4. Ajouter un melange");
        Ecran.afficherln("5. Ajouter un approvisionnement");
        choix = Clavier.saisirInt();
        switch (choix) {
            case 1:
                ajouterClient();
                break;
            case 2:
                ajouterProduit();
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

    public static void ajouterProduit() {
        int choix;
        Ecran.afficherln("1. Ajouter de pain");
        Ecran.afficherln("2. Ajouter de viennoiserie");
        choix = Clavier.saisirInt();
        switch (choix) {
            case 1:
                ajouterPain();
                break;
            case 2:
                ajouterViennoiserie();
                break;
            default:
                Ecran.afficherln("Erreur de saisie");
                ajouterProduit();
                break;
        }
    }

    public static void ajouterPain() {
        int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
        int res, id, idMelange = 0;
        String desc, descMelange;
        double prix;

        // creation de la requête
        Ecran.afficherln("Saisir la description du pain");
        desc = Clavier.saisirString();
        Ecran.afficherln("Saisir la des description du melange du pain");
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

    public static void ajouterViennoiserie() {

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
        Ecran.afficherln("Saisir la description du melange du pain");
        descMelange = Clavier.saisirString();
        res = BD.executerSelect(connexion, "SELECT IDMelange FROM MELANGE WHERE DescMelange = '" + descMelange + "'");
        while (BD.suivant(res)) {
            idMelange = BD.attributInt(res, "IDMelange");
        }

        Ecran.afficherln("Saisir la semaine de l'approvisionnement");
        semaine = Clavier.saisirInt();
        Ecran.afficherln("Saisir la quantité de pain");
        quantite = Clavier.saisirInt();
        String sql = "INSERT INTO APPROVISIONNER VALUES (" + idMelange + ", " + semaine + ", " + quantite + ")";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
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
            int quantite = BD.attributInt(res, "SUM(NombreDePains)");
            String melange = BD.attributString(res, "DescMelange");
            Ecran.afficherln(melange + " | " + quantite);
        }


    }

    public static void main(String[] args) {
        totalMelangeLivrer();
    }
}