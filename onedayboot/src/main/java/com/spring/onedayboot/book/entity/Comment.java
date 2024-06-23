package com.spring.onedayboot.book.entity;

import com.spring.onedayboot.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "comment")
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", columnDefinition = "BIGINT")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", columnDefinition = "BIGINT")
    private Book book;

    @Column(name = "comment", columnDefinition = "VARCHAR(255)", nullable = false)
    @ToString.Include
    private String comment;

    @Column(name = "page", columnDefinition = "INT", nullable = false)
    @ToString.Include
    private Integer page;
}
