package com.project.polaroid.repository;

import com.project.polaroid.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRoleRepository extends JpaRepository<SellerEntity,Long> {


    @Query(value = "SELECT a FROM SellerEntity a WHERE a.memberId.id=:memberId ")
    public List<SellerEntity> sellerRoleDuplicate(Long memberId);

}
