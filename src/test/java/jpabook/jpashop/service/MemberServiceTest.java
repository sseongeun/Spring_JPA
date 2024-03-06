package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given -> 이런게 주어졌을때
        Member member=new Member();
        member.setName("sseongeun");

        //when -> 이렇게 하면
        Long savedId=memberService.join(member);

        //then -> 이렇게 된다
        assertEquals(member,memberRepository.findOne(savedId));

    }


    @Test(expected=IllegalStateException.class)
    public void 중복회원예외() throws Exception{
        //given
        Member member1 =new Member();
        Member member2=new Member();
        member1.setName("sseonguen");
        member2.setName("sseonguen");

        //when
        memberService.join(member1);
        memberService.join(member2); //여기서 예외가 발생해야함!

        //then
        fail("예외가 발생해야 한다.");

    }
}