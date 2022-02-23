package com.project.polaroid.service;

import com.project.polaroid.entity.SellerEntity;
import com.project.polaroid.repository.SellerRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerRoleServiceImpl implements SellerRoleService{
    private final SellerRoleRepository sellerRoleRepository;
    private final MemberService memberService;

    // 판매자 권한신청
    @Override
    public String save(Long memberId) {
        String duplicate=null;
        List<SellerEntity> sellerEntityList = sellerRoleRepository.sellerRoleDuplicate(memberId);

        if(sellerEntityList.isEmpty()) {
            SellerEntity sellerEntity=new SellerEntity();
            sellerEntity.setMemberId(memberService.findById(memberId));
            sellerRoleRepository.save(sellerEntity);
            duplicate="ok";
        }
        else{
            duplicate="no";
        }
        return duplicate;
    }
}
