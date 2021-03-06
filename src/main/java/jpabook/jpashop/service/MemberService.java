package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기 전용임을 명시함으로써, 성능을 최적화 시켜줌 (조회 기능이 더 많은 클래스이기 때문에 전체 클래스에 적용 후, 쓰기인 메서드에 예외 처리)
@RequiredArgsConstructor //초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional //읽기 전용이 아닌 경우 @Transactional의 readOnly 기본 속성은 false
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(long id, String name) {
        //영속성 컨텍스트에서 해당 멤버를 가져온다.
        Member member = memberRepository.findById(id).get();
        //영속 상태에 있는 멤버의 이름을 변경 => @Transactional을 통해, AOP 트랜잭션 커밋이 수행 됨 (변경 감지 적용)
        member.setName(name);
    }
}
