package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 * 全局过滤器: 拦截请求，实现自己的业务逻辑
 * 需求：
 *      定义全局过滤器，拦截请求，判断请求的参数是否满足下面条件：
 *         - 参数中是否有authorization，
 *         - authorization参数值是否为admin
 * 访问路径为: http://127.0.0.1:10010/user/1?authorization=admin  时成功
 *           http://127.0.0.1:10010/user/1                      时失败
 */


// @Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         *  处理当前请求，有必要的话通过{@link GatewayFilterChain}将请求交给下一个过滤器处理
         *
         * @param exchange 请求上下文，里面可以获取Request、Response等信息
         * @param chain 用来把请求委托给下一个过滤器
         * @return {@code Mono<Void>} 返回标示当前过滤器业务结束
         */
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        // 2.获取参数中的 authorization 参数
        String auth = params.getFirst("authorization");
        // 3.判断参数值是否等于 admin
        if ("admin".equals(auth)) {
            // 4.是，放行
            return chain.filter(exchange);
        }
        // 5.否，拦截
        // 5.1.设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 5.2.拦截请求
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
