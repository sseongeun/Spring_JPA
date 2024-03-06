package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //모든것을 트랜잭션 내에서 관리하다는 뜻
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void update(Long id, String name) {
        Member member=memberRepository.findOne(id);
        member.setName(name);
    }


    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }


    public void validateDuplicateMember(Member member){
        //예외
        List<Member> findMembers=memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //id로 회원 한명 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
