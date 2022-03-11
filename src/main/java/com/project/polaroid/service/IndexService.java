package com.project.polaroid.service;

import com.project.polaroid.entity.MemberEntity;

public interface IndexService {
    // 이메일 찾기
    MemberEntity findPassword(String memberEmail);

    // 비밀번호 변경
    void lostPassword(String password,Long memberId);
}
