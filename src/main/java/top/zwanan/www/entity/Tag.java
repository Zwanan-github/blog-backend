package top.zwanan.www.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tags", indexes = {
        @Index(name = "idx_tag_name_unq", columnList = "name", unique = true)
})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}