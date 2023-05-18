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
    if (members == null) {
      throw new NotFoundEntityException("Member");
    }
    return members;
  }
}
