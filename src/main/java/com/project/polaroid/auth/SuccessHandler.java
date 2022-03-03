package com.project.polaroid.auth;

import com.project.polaroid.entity.MemberEntity;
import com.project.polaroid.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
public class SuccessHandler implements AuthenticationSuccessHandler {

    public final MemberRepository memberRepository;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session= request.getSession();

        MemberEntity member =memberRepository.findByMemberEmail(authentication.getName());
        session.setAttribute("LoginEmail", authentication.getName());
        session.setAttribute("LoginNumber", member.getId());

        if(member.getMemberPhone() != null)
            response.sendRedirect("/");
        else
            response.sendRedirect("/member/addInfo");
    }

}
