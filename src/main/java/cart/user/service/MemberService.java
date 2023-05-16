package cart.user.service;


import cart.enums.exceptions.ErrorCode;
import cart.exceptions.MemberNotFoundException;
import cart.user.domain.Member;
import cart.user.dto.request.MemberRequest;
import cart.user.dto.response.MemberResponse;
import cart.user.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberDao memberDao;

    public List<MemberResponse> findAll() {
        return memberDao.findAll()
                .stream()
                .map(member -> new MemberResponse(member.getEmail(), member.getPassword()))
                .collect(Collectors.toList());
    }

    public void insert(MemberRequest memberRequest) {
        memberDao.insert(new Member(memberRequest.getEmail(), memberRequest.getPassword()));
    }

    public void update(Long id, MemberRequest memberRequest) {
        Member member = memberDao.findById(id).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        memberDao.update(new Member(member.getId(), memberRequest.getEmail(), memberRequest.getPassword(), member.getCreateAt()));
    }

    public void deleteById(Long id) {
        Member member = memberDao.findById(id).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        memberDao.deleteById(member.getId());
    }
}
