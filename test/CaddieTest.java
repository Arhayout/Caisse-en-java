package test;

import org.junit.Before;
import org.junit.Test;
import supermarche.Caddie;
import supermarche.articles.Article;
import supermarche.articles.ArticleUnitaire;
import supermarche.articles.BaseArticles;
import supermarche.articles.ReferenceInconnueException;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CaddieTest {

    private static final String REF_1 = "ref-1";
    private static final String REF_2 = "ref-2";

    private Caddie caddie1;
    private Caddie caddie2;

    @Before
    public void setUp() {
        caddie1 = new Caddie();

        caddie2 = new Caddie();
        caddie2.ajouter(REF_1);
        caddie2.ajouter(REF_2);
        caddie2.ajouter(REF_2);
    }

    @Test
    public void testAjouter() {
        caddie1.ajouter(REF_1);

        String ref1 = caddie1.iterator().next();

        assertEquals(REF_1, ref1);
    }

    @Test
    public void testEnlever() {
        caddie2.enlever(REF_2);

        Iterator<String> iterateur = caddie2.iterator();
        String ref1 = iterateur.next();
        String ref2 = iterateur.next();

        assertEquals(REF_1, ref1);
        assertEquals(REF_2, ref2);
        assertFalse(iterateur.hasNext());
    }

    @Test
    public void testEnleverTous() {
        caddie2.enleverTous(REF_2);

        Iterator<String> iterateur = caddie2.iterator();
        String ref1 = iterateur.next();

        assertEquals(REF_1, ref1);
        assertFalse(iterateur.hasNext());
    }

}
