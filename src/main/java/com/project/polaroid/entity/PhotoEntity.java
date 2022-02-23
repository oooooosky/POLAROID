package com.project.polaroid.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "photo_table")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @Column
    private String boardFilename;

}