package cart.presentation.dto;

import cart.data.entity.CartMember;

public class ViewMember {

    private String email;
    private String password;

    public ViewMember(CartMember cartMember) {
        this.email = cartMember.getMemberEmail();
        this.password = cartMember.getMemberEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
