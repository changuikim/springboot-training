package com.spring.onedayboot.book.entity;

import com.spring.onedayboot.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "BIGINT")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long bookId;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @Column(name = "title", columnDefinition = "VARCHAR(255)", nullable = false)
    @ToString.Include
    private String title;

    @Column(name = "price", columnDefinition = "BIGINT", nullable = false)
    @ToString.Include
    private Long price;

}
