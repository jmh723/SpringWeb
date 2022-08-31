package study.springweb.service;

import study.springweb.domain.Member;
import study.springweb.repository.MemberRepository;
import study.springweb.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {
    // 회원 Service가 동작하려면 회원 Repository가 있어야 한다.

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {   // MemberRepository에 객체를 내가 직접 생성하는 것이 아닌 외부에서 넣어주게 한다.
        this.memberRepository = memberRepository;               // 이러한 기법을 DI(Dependency Injection, 의존성 주입)라고 한다.
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        // 위와 동일한 코드를 아래와 같이 간결하게 나타낼 수도 있다. (권장)
        vaildateDuplicateMember(member);    // 중복회원 검증

        /* ifPresent 메소드로 만약에 조회해서 해당 값이 있으면 예외 처리 동작을 수행한다.
         * 이러한 예외처리는 Optional이기 때문에 가능한 것이다. 기존 방식을 사용할 경우 if(!null) 명령을 사용했지만,
         * Optional로 감싸면 Optional 안에 멤버 객체가 있어서, Optional에 포함된 여러 메소드들을 사용할 수 있다.
         */

        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
/**
 * 전체 회원 조회
 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
