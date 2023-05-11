package cart.product.domain.vo;

public class Price {
    private int price;

    public Price(int price) {
        validatePositive(price);
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    private void validatePositive(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0이하일 수 없습니다");
        }
    }
}
