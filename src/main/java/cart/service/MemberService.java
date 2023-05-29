package cart.service;

import cart.domain.Member;
import cart.exception.NotFoundEntityException;
import cart.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public List<Member> findAll() {
    List<Member> members = memberRepository.findAll();
    if (members.isEmpty()) {
      throw new NotFoundEntityException("Member");
    }
    return members;
  }

  public Member findByEmail(String email) throws NotFoundEntityException {
    return memberRepository.findByEmail(email)
        .orElseThrow(() -> new NotFoundEntityException("회원을 찾을 수 없습니다."));
  }

  public Member login(String email, String password) {
    Member foundMember = memberRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일 입니다."));

    foundMember.validatePassword(password);

    return foundMember;
  }
}
