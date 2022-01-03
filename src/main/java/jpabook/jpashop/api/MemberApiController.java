package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.name);

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") long id,
            @RequestBody UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    //엔티티를 반환하게 되면 유연성이 떨어진다.
    @GetMapping("api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> members = findMembers.stream()
                .map( m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(members.size(), members);
    }

    //API는 필요한 값만 반환 -> API Spec과 동일해야 함
    @Data
    @AllArgsConstructor
    public class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    public class MemberDto {
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor //실용적인 관점에서 dto는 특정 기능이 있는 것도 아니고 편리하게 롬복을 사용
    static class UpdateMemberResponse {
        private long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private long id;

        public CreateMemberResponse(long id) {
            this.id = id;
        }
    }
}
