package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class HelloTraceV1Test {

    @DisplayName("간단하게 로그 추적 시작 - 끝 동작 확인 테스트")
    @Test
    void begin_end() {
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus traceStatus = traceV1.begin("hello");
        traceV1.end(traceStatus);
    }

    @DisplayName("간단하게 로그 추적 시작 - 예외 동작 확인 테스트")
    @Test
    public void begin_exception() {
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus traceStatus = traceV1.begin("hello");
        traceV1.exception(traceStatus, new IllegalArgumentException());
    }
}