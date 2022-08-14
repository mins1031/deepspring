package hello.advanced.proxy.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class ServiceImpl implements ServiceInterface {
    @Override
    public void save() {
        log.info("Save 호출");
    }

    @Override
    public void find() {
        log.info("find 호출");
    }
}
