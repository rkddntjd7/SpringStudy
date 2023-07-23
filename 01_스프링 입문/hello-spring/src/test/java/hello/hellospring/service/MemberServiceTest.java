package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;  // 테스트할 서비스 객체 생성
    MemoryMemberRepository memberRepository; // 클리어를 위해 생성

    @BeforeEach
    public void beforeEach() { // 각 테스트 실행전 (같은 MemoryMemberRepository를 사용하기위해)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 테스트는 한글로 변경 가능
    @Test
    void 회원가입() {
        //given 뭔가 주어져서
        Member member = new Member(); // 멤버 객체 생성
        member.setName("spring"); // 멤버에 spring

        //when 실행했을때
        Long saveId = memberService.join(member); // 회원가입시 아이디가 나오도록 해놓았음

        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 멤버의 이름이 findMember의 이름과 같은지 확인
    }

    @Test
    public void 중복_회원_예외() { // 회원가입시 중복 검사 테스트
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1); // member1 회원가입
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 멤버서비스의 회원가입의 member2가 회원가입시 앞의 예외가 터져야함

        assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}