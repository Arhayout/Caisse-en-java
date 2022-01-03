package supermarche.articles;

import java.util.HashMap;
import java.util.Map;

/**
 * BaseArticles enregistre et donne accès aux articles à partir de leur
 * référence.
 *
 * @author Xavier Crégut et XXX
 */
public class BaseArticles {

    private Map<String, Article> articles;

    /**
     * Construire une base d'articles vide.
     */
    public BaseArticles() {
        this.articles = new HashMap<>();
    }

    //@ requires reference != null;
    //@ requires article != null;
    //@ ensures this.article(reference) == article;
    public void enregistrer(String reference, Article article) {
        articles.put(reference, article);
    }

    //@ requires reference != null;
    public void supprimer(String reference) throws ReferenceInconnueException {
        if (!articles.containsKey(reference)) {
            throw new ReferenceInconnueException();
        }
        else {
            articles.remove(reference);
        }
    }

    //@ requires reference != null;
    //@ signals_only ReferenceInconnueException;
    public Article article(String reference) throws ReferenceInconnueException {
        if (!articles.containsKey(reference)) {
            throw new ReferenceInconnueException();
        }
        return articles.get(reference);
    }

}
