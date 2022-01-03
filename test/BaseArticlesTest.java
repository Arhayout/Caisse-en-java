package test;

import org.junit.*;
import supermarche.articles.Article;
import supermarche.articles.ArticleUnitaire;
import supermarche.articles.BaseArticles;
import supermarche.articles.ReferenceInconnueException;

import static org.junit.Assert.*;

public class BaseArticlesTest {

    private static final String REF_1 = "ref-1";
    private static final String REF_2 = "ref-2";

    private static final Article ARTICLE_1 = new ArticleUnitaire(200L, REF_1);
    private static final Article ARTICLE_2 = new ArticleUnitaire(100L, REF_2);

    private BaseArticles baseArticles;

    @Before
    public void setUp() {
        baseArticles = new BaseArticles();
        baseArticles.enregistrer(REF_1, ARTICLE_1);
    }

    @Test
    public void testEnregistrer() throws ReferenceInconnueException {
        baseArticles.enregistrer(REF_2, ARTICLE_2);

        assertEquals(ARTICLE_2, baseArticles.article(REF_2));
    }

    @Test (expected = ReferenceInconnueException.class)
    public void testSupprimer() throws ReferenceInconnueException {
        baseArticles.supprimer(REF_1);

        baseArticles.article(REF_1); // On s'attend a obtenir une exception
    }

    @Test
    public void testArticle() throws ReferenceInconnueException {
        assertEquals(ARTICLE_1, baseArticles.article(REF_1));
    }

}
