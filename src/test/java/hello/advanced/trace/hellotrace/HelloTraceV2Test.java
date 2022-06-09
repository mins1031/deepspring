package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @DisplayName("간단하게 로그 추적 시작 - 끝 동작 확인 테스트")
    @Test
    void begin_end() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus traceStatus = traceV2.begin("hello");
        TraceStatus status2 = traceV2.beginSync(traceStatus.getTraceId(), "hello2");
        traceV2.end(status2);
        traceV2.end(traceStatus);
    }

    @DisplayName("간단하게 로그 추적 시작 - 예외 동작 확인 테스트")
    @Test
    public void begin_exception() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus traceStatus = traceV2.begin("hello");
        TraceStatus hello2 = traceV2.beginSync(traceStatus.getTraceId(), "hello2");
        traceV2.exception(hello2, new IllegalArgumentException());
        traceV2.exception(traceStatus, new IllegalArgumentException());
    }
}