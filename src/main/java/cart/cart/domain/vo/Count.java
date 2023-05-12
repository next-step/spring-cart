package cart.cart.domain.vo;

public class Count {
    private int count;
    public Count(int count) {
        validatePositive();
        this.count = count;
    }

    public void increase(int value) {
        count += value;
    }

    public int getCount(){
        return count;
    }

    private void validatePositive() {
        if (count < 0) {
            throw new IllegalStateException("갯수는 0이하일 수 없습니다.");
        }
    }
}
