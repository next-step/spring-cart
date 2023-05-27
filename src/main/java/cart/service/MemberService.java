package cart.service;

import cart.domain.Member;
import cart.exception.NotFoundEntityException;
import cart.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

  public boolean authenticate(Member member) {
    Member foundMember = findByEmail(member.getEmail());
    return foundMember.getPassword().equals(member.getPassword());
  }
}
