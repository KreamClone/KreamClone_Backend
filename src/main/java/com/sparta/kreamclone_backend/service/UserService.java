package com.sparta.kreamclone_backend.service;

import com.sparta.kreamclone_backend.dto.ResponseDto;
import com.sparta.kreamclone_backend.dto.TokenDto;
import com.sparta.kreamclone_backend.dto.UserRequestDto;
import com.sparta.kreamclone_backend.entity.RefreshToken;
import com.sparta.kreamclone_backend.entity.User;
import com.sparta.kreamclone_backend.entity.UserRole;
import com.sparta.kreamclone_backend.repository.RefreshTokenRepository;
import com.sparta.kreamclone_backend.repository.UserRepository;
import com.sparta.kreamclone_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.sparta.kreamclone_backend.util.JwtUtil.ACCESS_KEY;
import static com.sparta.kreamclone_backend.util.JwtUtil.REFRESH_KEY;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private static final String SELLER_TOKEN = "sellerTest";
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto signup(UserRequestDto requestDto) {
        String userEmail = requestDto.getUserEmail();
        String userShoeSize = requestDto.getUserShoeSize();
        String userUrl = requestDto.getUserUrl();
        String userAddr = requestDto.getUserAddr();
        String userPassword = passwordEncoder.encode(requestDto.getUserPassword());
        String userRole = requestDto.getUserRole();

        Optional<User> found = userRepository.findUserByUserEmail(userEmail);

        if (found.isPresent()) {
            return new ResponseDto("아이디 중복", HttpStatus.BAD_REQUEST);
        }

        UserRole role;

        if (userRole.equals("seller")) {
            if (!SELLER_TOKEN.equals(requestDto.getSellerToken())) {
                return new ResponseDto("토큰값이 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
            role =  UserRole.SELLER;
        }else{
            role =  UserRole.BUYER;
        }

        User user = new User(userEmail, userShoeSize, userAddr, userPassword, userUrl, role);

        userRepository.save(user);

        return new ResponseDto("회원가입이 성공했습니다.", HttpStatus.OK);
    }


    @Transactional
    public ResponseDto login(UserRequestDto requestDto, HttpServletResponse response) {

        String userEmail = requestDto.getUserEmail();
        String userPassword = requestDto.getUserPassword();

        try {
            User user = userRepository.findUserByUserEmail(userEmail).orElseThrow(
                    () -> new IllegalArgumentException("없는 이메일 입니다.")
            );

            // 비밀번호 확인
            if(!passwordEncoder.matches(userPassword, user.getUserPassword())){
                return new ResponseDto("비밀번호를 확인해주세요!!", HttpStatus.BAD_REQUEST);
            }

            //username (ID) 정보로 Token 생성
            TokenDto tokenDto = jwtUtil.createAllToken(requestDto.getUserEmail(), user.getRole());

            //Refresh 토큰 있는지 확인
            Optional<RefreshToken> refreshToken = refreshTokenRepository.findRefreshTokenByUserEmail(requestDto.getUserEmail());

            //Refresh 토큰이 있다면 새로 발급 후 업데이트
            //없다면 새로 만들고 DB에 저장
            if (refreshToken.isPresent()) {
                RefreshToken savedRefreshToken = refreshToken.get();
                RefreshToken updateToken = savedRefreshToken.updateToken(tokenDto.getRefreshToken().substring(7));
                refreshTokenRepository.save(updateToken);
            } else {
                RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken().substring(7), userEmail);
                refreshTokenRepository.save(newToken);
            }

            //응답 헤더에 토큰 추가
            setHeader(response, tokenDto);
            return new ResponseDto("성공", HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(ACCESS_KEY, tokenDto.getAccessToken());
        response.addHeader(REFRESH_KEY, tokenDto.getRefreshToken());
    }


    @Transactional
    public ResponseDto logOut(UserRequestDto requestDto) {
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByUserEmail(requestDto.getUserEmail())
                .orElseThrow( () -> new IllegalArgumentException("리프레시 토큰 없습니다~"));

        refreshTokenRepository.delete(refreshToken);

        return new ResponseDto("로그아웃 성공", HttpStatus.OK);
    }
}
