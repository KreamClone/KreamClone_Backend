package com.sparta.kreamclone_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity(name="TB_USER")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @Column(name = "userId", nullable = false, unique = true)
    private String id;
    
    @Column(nullable = false)
    private Long userKakaoId;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userShoeSize;

    @Column(nullable = false)
    private String userAddr;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userUrl;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    public User(Long userKakaoId, String userEmail, String userShoeSize, String userAddr, String userPassword, String userUrl, UserRole role) {
        this.userKakaoId = userKakaoId;
        this.userEmail = userEmail;
        this.userShoeSize = userShoeSize;
        this.userAddr = userAddr;
        this.userPassword = userPassword;
        this.userUrl = userUrl;
        this.role = role;
    }
}
