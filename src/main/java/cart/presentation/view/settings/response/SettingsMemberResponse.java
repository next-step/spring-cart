package cart.presentation.view.settings.response;

import cart.domain.member.model.MemberModel;

import java.util.List;
import java.util.stream.Collectors;

public class SettingsMemberResponse {

    private final Long id;
    private final String email;
    private final String password;

    public SettingsMemberResponse(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static List<SettingsMemberResponse> list(List<MemberModel> memberList) {
        return memberList.stream()
            .map(memberModel -> new SettingsMemberResponse(memberModel.getId(), memberModel.getEmail(), memberModel.getPassword()))
            .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
