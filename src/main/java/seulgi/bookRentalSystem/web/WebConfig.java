package seulgi.bookRentalSystem.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import seulgi.bookRentalSystem.web.login.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns(
                        "/book/{bookId}/rent", // 책 대여 버튼 클릭 시 로그인 체크
                        "/book/{authorId}/booksByAuthorId", // 나의 책 페이지
                        "/book/{bookRentalId}/booksByBookRentalId" // 빌린 책 페이지
                )
                .excludePathPatterns(
                        "/book", // 모든 책 리스트는 비회원도 볼 수 있음
                        "/book/{bookId}", // 책 단건 조회는 비회원도 볼 수 있음
                        "/login", "/logout", "/findLoginInfo" // 로그인 관련 경로 제외
                );
    }
}