public class Cabinet {
	public static String adresse = "sql7.freemysqlhosting.net";
	public static String bd = "sql7615754";
	public static String login = "sql7615754";
	public static String password = "Gp8NFVRvEz";

	public static void main(String[] args) {
		int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
		// cr ´eation de la requ^ete
		String sql = "SELECT NumSecu, PATIENT.Nom, COUNT(NumCons) FROM CONSULTATION, PATIENT WHERE"
		+" CONSULTATION.Patient = NumSecu GROUP BY Nom";
		// envoi de la requ^ete
		int res = BD.executerSelect(connexion, sql);
		// parcours du r ´esultat (ligne par ligne)
		while (BD.suivant(res)) {
			int numCons = BD.attributInt(res,"COUNT(NumCons)");
			String NumSecu = BD.attributString(res,"NumSecu");
			String nomPatient = BD.attributString(res,"PATIENT.Nom");
			System.out.println(""+numCons+ " "+ nomPatient +" "+ NumSecu);
		}
		BD.fermerResultat(res);
		BD.fermerConnexion(connexion);
	}
}
