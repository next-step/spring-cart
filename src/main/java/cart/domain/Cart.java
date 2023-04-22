package cart.domain;

public class Cart {

    private Long id;
    private Member member;
    private Product product;

    public Cart(Long id, Member member, Product product) {
        this.id = id;
        this.member = member;
        this.product = product;
    }

    public Cart(Member member, Product product) {
        this(null, member, product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isOwner(Member member) {
        return this.member.equals(member);
    }

    public boolean isProduct(Product product) {
        return this.product.equals(product);
    }
}
