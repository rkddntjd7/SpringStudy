package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // static import로 assertThat 바로 사용 가능

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository(); // 테스트 할 객체 생성

    @AfterEach // 테스트 순서 상관없이 진행되기 때문에 테스트 끝날때마다 데이터를 클리어 해야함
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member(); // 멤버 객체 생성
        member.setName("spring"); // 멤버의 이름 세팅

        repository.save(member); // repository 에 저장

        Member result = repository.findById(member.getId()).get(); // optional 에서 get 을 사용하여 값 꺼냄
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result); // org.junit.jupiter 가 제
        assertThat(member).isEqualTo(result); // org.assertj 가 제공 (member 와 result 같은지 비교)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1"); // 멤버1에 스프링1
        repository.save(member1); // repository 에 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        // 2개의 회원가입

        Member result = repository.findByName("spring1").get(); // 스프링1을 찾음
        assertThat(result).isEqualTo(member1); // result 와 member1이 같은지 비교
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1"); // 멤버1에 스프링1
        repository.save(member1); // repo에 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 2개의 회원가입

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // result 의 총 개수가 2개가 맞는지 확인
    }
}

// test 먼저 작성 후 개발하는것이 test 주도 개발 (tdd)
// 여러명이서 많은량의 코드로 개발할때는 test 코드가 꼭 필수!!!