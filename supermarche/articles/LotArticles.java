package supermarche.articles;

public class LotArticles implements Article {

    private long prix;
    private String libelle;

    public LotArticles(ArticleUnitaire article, int nombreArticles, long prixLot, String libelle) {
        assert prixLot <= nombreArticles * article.prix();

        this.prix = prixLot;
        this.libelle = libelle;
    }

    @Override
    public long prix() {
        return this.prix;
    }

    @Override
    public String libelle() {
        return this.libelle;
    }
}
