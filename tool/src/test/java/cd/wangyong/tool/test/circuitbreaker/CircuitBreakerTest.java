package cd.wangyong.tool.test.circuitbreaker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cd.wangyong.tool.circuitbreaker.CircuitBreaker;
import cd.wangyong.tool.circuitbreaker.DefaultCircuitBreaker;
import cd.wangyong.tool.circuitbreaker.InterfaceCBConfig;

/**
 * @author andy
 * @since 2020/11/10
 */
@RunWith(JUnit4.class)
public class CircuitBreakerTest {
    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerTest.class);

    @Test
    public void testHelloService() throws Exception {
        CircuitBreaker breaker = new DefaultCircuitBreaker(buildCBConfigMap());
        breaker.enable();

        String result = breaker.apply(HelloService.class, "say", () -> "你好.", () -> "hello.");
        logger.info(result);
    }

    private Map<Class<?>, InterfaceCBConfig> buildCBConfigMap() {
        Map<Class<?>, InterfaceCBConfig> map = new HashMap<>();
        return map;
    }

}
