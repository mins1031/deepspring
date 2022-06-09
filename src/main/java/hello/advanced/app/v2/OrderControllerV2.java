package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderServiceV2;
    private final HelloTraceV2 trace;

    /**
     * 1. 간단하게 begin, end 만해서 해결되면 얼마나 간단할까.. (물론 모든 컨트롤러 로직에 로그를 위한 로직이 있어서 이 부분은 나중에 처리가 필요.)
     * 2. 단순하게 로직이 잘되면 로그 출력에 문제가 없지만 예외가 터진다면 로그가 출력이 안됨. 그렇다고 try - catch로 감싸서 해줘도 로직이 너무 더러워지고, 무엇보다 예외를 던져주질않음.
     * */
    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderServiceV2.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

}
