package test;

import org.junit.Before;
import org.junit.Test;
import supermarche.Caddie;
import supermarche.Caisse;
import supermarche.articles.*;

import static org.junit.Assert.assertEquals;

public class CaisseTest {

    public static final double EPSILON = 1e-6; // précision pour la comparaison entre réels.

    private static final ArticleUnitaire moutarde = new ArticleUnitaire(102, "Moutarde");
    private static final ArticleUnitaire brocoli = new ArticleUnitaire(198, "Brocoli");
    private static final ArticleUnitaire coulomier = new ArticleUnitaire(220, "Coulomier");
    private static final ArticleUnitaire lait = new ArticleUnitaire(67, "Lait 1L");
    private static final ArticleUnitaire lecteur = new ArticleUnitaire(6999, "Lecteur DiVX");
    private static final LotArticles packLait = new LotArticles(lait, 6, 365, "Lait pack 6L");

    private BaseArticles baseArticles;
    private Caddie caddie1;
    private Caddie caddie2;
    private Caisse caisse;

    @Before
    public void setUp() {
        baseArticles = new BaseArticles();
        baseArticles.enregistrer("moutarde", moutarde);
        baseArticles.enregistrer("brocoli", brocoli);
        baseArticles.enregistrer("coulomier", coulomier);
        baseArticles.enregistrer("lecteur", lecteur);
        baseArticles.enregistrer("lait", lait);
        baseArticles.enregistrer("packLait", packLait);

        caddie1 = new Caddie();
        caddie1.ajouter("moutarde");
        caddie1.ajouter("brocoli");
        caddie1.ajouter("coulomier");

        caddie1.ajouter("brocoli");
        caddie1.ajouter("brocoli");
        caddie1.ajouter("brocoli");

        caddie1.ajouter("lecteur");
        caddie1.ajouter("lait");
        caddie1.ajouter("packLait");

        caddie2 = new Caddie();
        caddie2.ajouter("lecteur");

        caisse = new Caisse(baseArticles);
    }

    @Test
    public void testScanner() throws ReferenceInconnueException {
        caisse.scanner(caddie1);

        assertEquals(1, caisse.nbTicketsTraites());
        assertEquals(8545, caisse.total(), EPSILON);
    }

    @Test
    public void testImprimerReferencesInconnues() throws ReferenceInconnueException {
        caddie1.ajouter("poisson");
        caddie1.ajouter("viande");
        caddie1.ajouter("ordinateur");
        caddie1.ajouter("ordinateur");

        caisse.scanner(caddie1);

        caisse.imprimerReferencesInconues();
    }

    @Test
    public void testTotal() throws ReferenceInconnueException {
        caisse.scanner(caddie1);
        caisse.scanner(caddie2);

        assertEquals(15544, caisse.total(), EPSILON);
    }
}
