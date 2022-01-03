package supermarche.articles;

public class ArticleUnitaire implements Article {

    private long prix;
    private String libelle;

    public ArticleUnitaire(long prix, String libelle) {
        this.prix = prix;
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
