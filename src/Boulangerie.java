public class Boulangerie {
    public static String adresse = "sql7.freemysqlhosting.net";
    public static String bd = "sql7616392";
    public static String login = "sql7616392";
    public static String password = "7aqbrPxLPg";

    public static void main(String[] args) {
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        // creation de la requête
        String sql = "";
        // envoi de la requête
        int res = BD.executerSelect(connexion, sql);

        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
}
