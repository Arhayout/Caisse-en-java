package supermarche;

import supermarche.articles.Article;
import supermarche.articles.BaseArticles;
import supermarche.articles.ReferenceInconnueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Une caisse produit un ticket pour chaque caddie qui se présente à elle.
 * Elle fournit des statistiques sur le nombre de tickets produits à le total
 * des sommes encaissées.
 *
 * @author Xavier Crégut et XXX
 */
public class Caisse {

	private int ordreTicket;
	private BaseArticles articles;
	private long total;
	private List<String> referencesInconnues;

	public Caisse(BaseArticles articles) {
		this.articles = articles;
		this.ordreTicket = 0;
		this.total = 0;
		this.referencesInconnues = new ArrayList<>();
	}

	/** Obtenir le total des tickets traités.
	 * @return le total des tickets traités
	 */
	public long total() {
		return total;
	}

	/** Obtenir le nombre de tickets traités.
	 * @return le nombre de tickets traités
	 */
	public int nbTicketsTraites() {
		return ordreTicket;
	}

	/** Scanner tous les articles du caddie et éditer le ticket.
	 * @param caddie le caddie à scanner
	 */
	public void scanner(Caddie caddie) throws ReferenceInconnueException {
		this.ordreTicket++;

		TicketBuilder ticketBuilder = new TicketBuilder();
		ticketBuilder.definirNumero(this.ordreTicket);

		ArrayList<String> references = new ArrayList<>();


		for (String reference : caddie) {
			try {
				articles.article(reference);
				references.add(reference);
			}
			catch (ReferenceInconnueException e) {
				if (!referencesInconnues.contains(reference)) {
					referencesInconnues.add(reference);
				}
			}
		}

		int quantite = 1;

		for (int i = 0; i < references.size() - 1; i++) {
			String reference = references.get(i);
			String referenceSuivante = references.get(i+1);

			if (!reference.equals(referenceSuivante)) {
				Article article = articles.article(reference);
				ticketBuilder.ajouterArticle(article.libelle(), article.prix(), quantite);
				quantite = 1;
			}
			else {
				quantite++;
			}
		}


		Article dernierArticle = articles.article(references.get(references.size()-1));
		ticketBuilder.ajouterArticle(dernierArticle.libelle(), dernierArticle.prix(), 1);

		Ticket ticket = ticketBuilder.getTicket();
		total += ticket.getTotal();
		ticket.imprimer();
	}

	/** Imprimer sur le terminal des références inconnues dans l'ordre
	 * alphabétique et sans redondance.
	 */
	public void imprimerReferencesInconues() {
		referencesInconnues.sort(String::compareTo);
		referencesInconnues.forEach(System.out::println);
	}

}
