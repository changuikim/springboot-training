package com.spring.onedayboot.book.entity;

import com.spring.onedayboot.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

}
