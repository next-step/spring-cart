package cart.domain;

import cart.exception.MemberException;

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

    public void update(Cart cart) {
        if(!this.id.equals(cart.id)) {
            throw new MemberException();
        }
        this.product = cart.product;
        this.member = cart.member;
    }

    public boolean isOwner(Member member) {
        return this.member.equals(member);
    }

    public boolean isProduct(Product product) {
        return this.product.equals(product);
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
}
