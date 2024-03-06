package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }


    @PostMapping("/api/v2/members") //별도의 dto로 받는것
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    //====회원 수정 API=====//
    @PutMapping("/api/v2/members/{id}") //이름을 바꾸는 api
    public UpdateMemberResponse updateMemberV2(@PathVariable("id")Long id,@RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id,request.getName()); // 해당 id를 가진 멤버의 이름 변경
        Member findMember=memberService.findOne(id);

        return new UpdateMemberResponse(findMember.getId(),findMember.getName());


    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;

    }


    //=== 회원 조회 API ===//
    @GetMapping("api/v2/members")
    public Result memberV2()
    {
        List<Member> members=memberService.findMembers();
        List<memberResponse> collect=members.stream()
                .map(m-> new memberResponse(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;

    }
    @Data
    @AllArgsConstructor
    static class memberResponse{
        private String name;
    }




}