package cart.testdouble;

import cart.domain.entity.Member;
import cart.domain.repository.MemberRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberRepository implements MemberRepository {

    private Map<Long, Member> memberMap = new HashMap<>();

    @Override
    public Collection<Member> findAll() {
        return memberMap.values();
    }

    @Override
    public Member findByEmailAndPassword(String email, String password) {
        List<Member> members = memberMap.values()
                .stream()
                .filter(member -> email.equals(member.getEmail()) && password.equals(member.getPassword()))
                .collect(Collectors.toList());
        if (members.isEmpty()) {
            throw new NoSuchElementException("해당 회원을 찾을 수 없습니다.");
        }
        return members.get(0);
    }

    void insert(Member member) {
        memberMap.put(member.getId(), member);
    }
}
