package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 멤버를 Map에 저장
    private static long sequence = 0L; // 키값을 생성해주는 시퀀스 (ID)

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // save시 시퀀스 올려줌
        store.put(member.getId(), member); // 스토어에 저장
        return member;
    }

    @Override // 스토어에서 get을 사용하여 아이디 꺼냄 null이여도 반환가능하게 optional 사용
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override // 루프를 돌면서 map에서 같은 이름의 네임을 찾는다
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프를 돌려서
                .filter(member -> member.getName().equals(name)) // 멤버에서 member.getName()이 파라미터로 받아온 name과 같은지
                .findAny(); // 하나 찾아지면 찾은거 반환 null일 가능성 optional 사용
    }

    @Override // values를 통해 전부 리턴 (values == 멤버들)
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    } // 스토어를 비움
}
