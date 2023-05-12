package com.sparta.kreamclone_backend.entity;

public enum UserRole {
    BUYER(Authority.BUYER),  // 사용자 권한
    SELLER(Authority.SELLER);  // 관리자 권한

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String BUYER = "BUYER";
        public static final String SELLER = "SELLER";
    }
}
