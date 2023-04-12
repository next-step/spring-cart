package cart.member;

import cart.infra.AuthorizationException;
import cart.member.model.AuthInfo;
import cart.member.model.Member;
import cart.member.model.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findByAll() {

        return memberRepository.findAll().stream()
                .map(MemberMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public Member login(AuthInfo authInfo) {

        return memberRepository.findByEmailAndPassword(authInfo.getEmail(), authInfo.getPassword())
                .map(MemberMapper.INSTANCE::toDto)
                .orElseThrow(() -> new AuthorizationException("인증 실패"));
    }
}
