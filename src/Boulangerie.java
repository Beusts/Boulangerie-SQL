public class Boulangerie {
    public static String adresse = "sql7.freemysqlhosting.net";
    public static String bd = "sql7616392";
    public static String login = "sql7616392";
    public static String password = "7aqbrPxLPg";

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
            /*case 2:
                ajouterProduit();
                break;
            case 3:
                ajouterCommande();
                break;
            case 4:
                ajouterMelange();
                break;
            case 5:
                ajouterApprovisionnement();
                break;*/
            default:
                Ecran.afficherln("Erreur de saisie");
                ajouter();
                break;
        }
    }
    public static void ajouterClient(){
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        int id;
        String nom, ville, adresse;
        // creation de la requête

        Ecran.afficherln("Saisir le nom du client");
        nom = Clavier.saisirString();
        Ecran.afficherln("Saisir la ville du client");
        ville = Clavier.saisirString();
        Ecran.afficherln("Saisir l'adresse du client");
        adresse = Clavier.saisirString();
        id = BD.executerUpdate(connexion, "UPDATE CLIENT");
        String sql = "INSERT INTO CLIENT VALUES ("+id+", '"+nom+"', '"+ville+"', '"+adresse+"')";
        // envoi de la requête
        int res = BD.executerUpdate(connexion, sql);
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }

    public static void main(String[] args) {
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        BD.fermerConnexion(connexion);
    }
}
