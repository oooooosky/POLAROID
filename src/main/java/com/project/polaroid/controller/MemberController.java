package com.project.polaroid.controller;

import com.project.polaroid.auth.PrincipalDetails;
import com.project.polaroid.dto.MemberAddInfo;
import com.project.polaroid.entity.MemberEntity;
import com.project.polaroid.service.FollowService;
import com.project.polaroid.service.MemberService;
import com.project.polaroid.service.SellerRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final FollowService followService;
    private final SellerRoleService sellerRoleService;

    @GetMapping("/addInfo")
    public String addInfoForm(){
        return "member/addInfo";
    }

    // oauth로그인시 추가정보
    @PostMapping("/addInfo")
    public String addInfo(@ModelAttribute MemberAddInfo memberAddInfo,
                          @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails.getMember().getId() = " + principalDetails.getMember().getId());
        memberService.memberAddInfo(memberAddInfo,principalDetails.getMember().getId());
        return "index";
    }

    // 마이페이지 출력
    @GetMapping("/mypage")
    public String mypageForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        MemberEntity member=memberService.findById(principalDetails.getMember().getId());
        model.addAttribute("member",member);
        ArrayList<Integer> followCount=followService.followCount(principalDetails.getMember().getId());
        System.out.println("MemberController.mypageForm");
        System.out.println(followCount.get(0));
        System.out.println(followCount.get(1));
        model.addAttribute("follower",followCount.get(0));
        model.addAttribute("following",followCount.get(1));
        return "member/my-page";
    }

    // 판매자 권한신청
    @GetMapping("/sellerRole")
    public @ResponseBody String sellerRole(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("MemberController.sellerRole");
        String duplicate=sellerRoleService.save(principalDetails.getMember().getId());
        String result=null;
        if(duplicate=="ok"){
            result="<script>alert('판매자권한 신청이 완료되었습니다.');location.href='mypage'</script>";
        }
        else {
            result="<script>alert('이미 판매자권한을 신청 하셨습니다.');location.href='mypage'</script>";
        }
        return result;
    }

}
