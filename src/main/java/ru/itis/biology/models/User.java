package ru.itis.biology.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fullname;
    private Integer classNumber;
    private Integer age;
    private String email;
    private String hashPassword;
    private LocalDateTime createdAt;
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String confirmCode;

    @OneToMany(mappedBy = "user")
    @Where(clause = "actual = true")
    private List<Comment> activeComments;


    @OneToOne(mappedBy = "user")
    private FileInfo image;
}
