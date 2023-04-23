package cart.service;

import cart.domain.AuthInfo;
import cart.domain.MemberEntity;
import cart.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean checkAuth(AuthInfo authInfo) {
        MemberEntity memberEntity = this.findByEmail(authInfo.getEmail());
        if (memberEntity.checkPassword(authInfo.getPassword())) {
            return true;
        }
        throw new IllegalArgumentException("회원 정보가 일치하지 않아 인증에 실패했습니다.");
    }

    private MemberEntity findByEmail(String email) {
        return this.memberRepository.findByEmail(email);
    }
}
