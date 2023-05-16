package cart.cart.domain.vo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count1 = (Count) o;
        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
