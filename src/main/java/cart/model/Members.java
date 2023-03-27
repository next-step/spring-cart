package cart.model;

import java.util.List;

public class Members {

    private List<Member> members;

    public Members(List<Member> members) {
        this.members = members;
    }

    public List<Member> getUsers() {
        return members;
    }
}
