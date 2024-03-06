package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value="/members/new") //해당 경로로 들어오는 get요청 처리하여 클라이언트에게 반환-> 회원 등록페이지 열어보는 기능
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    //회원 등록하는 페이지
    @PostMapping(value="/members/new")
    public String create(@Valid MemberForm form, BindingResult result){ //@valid하면 memberform의 not empty기능을 가능하게해줌

        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address=new Address(form.getCity(),form.getStreet(),form.getZipcode());

        Member member=new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    //회원 목록 반환
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
