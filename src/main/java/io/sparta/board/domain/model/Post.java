package io.sparta.board.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity // entity 클래스임을 명시, 해당 클래스에는 기본 키가 포함되어 있어야 한다.
@Table(name = "p_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id
    @UuidGenerator // @UuidGenerator, @GeneratedValue(strategy = GenerationType.IDENTITY) 차이
    private UUID id;
    @Setter
    private String title; // VARCHAR(100)
    @Setter
    private String content; // TEXT 타입

    public void delete() {
        this.deleted = true;
    }
}
