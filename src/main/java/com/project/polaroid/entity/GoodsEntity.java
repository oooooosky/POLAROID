package com.project.polaroid.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "goods_table")
public class GoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @Column
    private int goodsStock;

    @Column
    private int goodsPrice;

}
