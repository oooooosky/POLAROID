package com.project.polaroid.service;

import com.project.polaroid.dto.MemberAddInfo;
import com.project.polaroid.entity.MemberEntity;
import com.project.polaroid.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    // 패스워드 암호화
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Override
    public void memberSave(MemberEntity member) {
        member.setMemberRole("ROLE_MEMBER");
        // 회원 프로필 기본화
        member.setMemberFilename("defaultProfile");
        // 세줄 패스워드 암호화 과정
        String rawPassword=member.getMemberPw();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        member.setMemberPw(encPassword);

        memberRepository.save(member);
    }

    // oauth 로그인 추가정보(닉네임,전화번호,주소)
    @Override
    @Transactional
    public void memberAddInfo(MemberAddInfo memberAddInfo,Long memberId) {

        memberRepository.addInfo(memberAddInfo.getMemberAddress(),memberAddInfo.getMemberPhone(),memberAddInfo.getMemberNickname(),memberId);
    }

    // 아이디 중복체크
    @Override
    public String mailDuplicate(String mail) {
         MemberEntity member=memberRepository.findByMemberEmail(mail);
        if (member==null)
            return "ok";
        else
            return "no";
    }

    // 닉네임 중복체크
    @Override
    public String  nicknameDuplicate(String nickname) {
        MemberEntity member = memberRepository.findByMemberNickname(nickname);
        if (member==null)
            return "ok";
        else
            return "no";
    }

    // 멤버 정보 (마이페이지)
    @Override
    public MemberEntity findById(Long memberId) {
        return (memberRepository.findById(memberId)).get();
    }
}
