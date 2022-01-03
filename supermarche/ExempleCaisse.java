package supermarche;

import supermarche.articles.ArticleUnitaire;
import supermarche.articles.BaseArticles;
import supermarche.articles.LotArticles;
import supermarche.articles.ReferenceInconnueException;

/** L'exemple du sujet.
 *
 * @author	Xavier Crégut et XXX
 */
public class ExempleCaisse {

	public static void main(String[] args) throws ReferenceInconnueException {
		BaseArticles baseArticles = new BaseArticles();

		// On ajoute les différents articles
		ArticleUnitaire moutarde = new ArticleUnitaire(102, "Moutarde");
		ArticleUnitaire brocoli = new ArticleUnitaire(198, "Brocoli");
		ArticleUnitaire coulomier = new ArticleUnitaire(220, "Coulomier");
		ArticleUnitaire lait = new ArticleUnitaire(67, "Lait 1L");
		ArticleUnitaire lecteur = new ArticleUnitaire(6999, "Lecteur DiVX");
		LotArticles packLait = new LotArticles(lait, 6, 365, "Lait pack 6L");

		baseArticles.enregistrer("moutarde", moutarde);
		baseArticles.enregistrer("brocoli", brocoli);
		baseArticles.enregistrer("coulomier", coulomier);
		baseArticles.enregistrer("lecteur", lecteur);
		baseArticles.enregistrer("lait", lait);
		baseArticles.enregistrer("packLait", packLait);

		// Création du caddie
		Caddie caddie = new Caddie();
		caddie.ajouter("moutarde");
		caddie.ajouter("brocoli");
		caddie.ajouter("coulomier");

		caddie.ajouter("brocoli");
		caddie.ajouter("brocoli");
		caddie.ajouter("brocoli");

		caddie.ajouter("lecteur");
		caddie.ajouter("lait");
		caddie.ajouter("packLait");

		// On créer la caisse et on affiche le ticket
		Caisse caisse = new Caisse(baseArticles);
		caisse.scanner(caddie);
	}

}
