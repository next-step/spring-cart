package cart.data.entity;

public class CartMember {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;

    public CartMember(Long memberId, String memberEmail, String memberPassword) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }
}
