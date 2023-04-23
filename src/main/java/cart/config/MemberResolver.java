package cart.config;

import cart.domain.AuthInfo;
import cart.domain.MemberEntity;
import cart.service.MemberService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MemberResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;

    public MemberResolver(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberEntity.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        AuthInfo authInfo = AuthExtractor.extractAuthInfo(webRequest.getHeader("Authorization"));
        return memberService.findByEmail(authInfo.getEmail());
    }
}
