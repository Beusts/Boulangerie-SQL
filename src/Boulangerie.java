public class Boulangerie {
    public static String adresse = "database.ejemw8z.mongodb.net";
    public static String bd = "beusts";
    public static String login = "beusts";
    public static String password = "A5gRLTca8V2qsV4s";

    public static void ajouter(){
        int choix;
        Ecran.afficherln("1. Ajouter un client");
        Ecran.afficherln("2. Ajouter un produit");
        Ecran.afficherln("3. Ajouter une commande");
        Ecran.afficherln("4. Ajouter un melange");
        Ecran.afficherln("5. Ajouter un approvisionnement");
        choix = Clavier.saisirInt();
        switch (choix){
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
    public static void ajouterClient(){
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
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
        String sql = "INSERT INTO CLIENTE VALUES ("+ (id + 1) +", '"+nom+"', '"+adresse+"', '"+ville+"')";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
    public static void ajouterProduit(){
        int choix;
        Ecran.afficherln("1. Ajouter de pain");
        Ecran.afficherln("2. Ajouter de viennoiserie");
        choix = Clavier.saisirInt();
        switch (choix){
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
    public static void ajouterPain(){
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        int res, id;
        String desc, melange;
        double prix;

        // creation de la requête
        Ecran.afficherln("Saisir la description du pain");
        desc = Clavier.saisirString();
        Ecran.afficherln("Saisir l'id du melange du pain");
        melange = Clavier.saisirString();
        Ecran.afficherln("Saisir le prix du pain");
        prix = Clavier.saisirDouble();
        id = BD.executerUpdate(connexion, "UPDATE PAIN SET IDPain = IDPain");
        String sql = "INSERT INTO PAIN VALUES ("+ (id + 1) +", '"+desc+"', "+prix+", '"+melange+"')";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
    public static void ajouterViennoiserie(){

    }
    public static void ajouterLivraison(){
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        int res, idCli, idPain, nbPain;
        String date;

        // creation de la requête
        Ecran.afficherln("Saisir l'id du client");
        idCli = Clavier.saisirInt();
        Ecran.afficherln("Saisir l'id du pain");
        idPain = Clavier.saisirInt();
        Ecran.afficherln("Saisir la date de livraison");
        date = Clavier.saisirString();
        Ecran.afficherln("Saisir le nombre de pain");
        nbPain = Clavier.saisirInt();
        String sql = "INSERT INTO LIVRER VALUES ("+ idCli +", "+idPain+", '"+date+"', "+nbPain+")";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
    public static void ajouterMelange(){
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        int res, id;
        String desc;

        // creation de la requête
        Ecran.afficherln("Saisir la description du melange");
        desc = Clavier.saisirString();
        id = BD.executerUpdate(connexion, "UPDATE MELANGE SET IDMelange = IDMelange");
        String sql = "INSERT INTO MELANGE VALUES ("+ (id + 1) +", '"+desc+"')";

        //envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
    public static void  ajouterApprovisionnement(){
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        int res, idMelange, semaine, quantite;

        // creation de la requête
        Ecran.afficherln("Saisir l'id du melange");
        idMelange = Clavier.saisirInt();
        Ecran.afficherln("Saisir la semaine de l'approvisionnement");
        semaine = Clavier.saisirInt();
        Ecran.afficherln("Saisir la quantité de pain");
        quantite = Clavier.saisirInt();
        String sql = "INSERT INTO APPROVISIONNER VALUES ("+ idMelange +", "+semaine+", "+quantite+")";

        // envoi de la requête
        res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
    public static void main(String[] args) {
        ajouter();
    }
}
