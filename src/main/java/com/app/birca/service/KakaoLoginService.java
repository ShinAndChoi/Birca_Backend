package com.app.birca.service;

import com.app.birca.config.WebClientConfig;
import com.app.birca.dto.response.kakao.GetMemberInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final WebClientConfig webClientConfig;

    public GetMemberInfoResponse getKakaoMemberInfo(String accessToken) {
        return webClientConfig.kakao().get()
                .uri("/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToFlux(GetMemberInfoResponse.class)
                .onErrorMap(e -> {
                    log.error("카카오 사용자 정보 조회에 실패하였습니다.", e);
                    return new HttpServerErrorException(INTERNAL_SERVER_ERROR, "카카오 사용자 정보 조회에 실패하였습니다.");
                })
                .blockLast();
    }

}
