package cd.wangyong.util.circuitbreaker;

import java.util.Map;

/**
 * @author andy
 * @since 2020/11/9
 */
public class InterfaceCBConfig {

    /**
     * 接口熔断配置
     */
    private CBConfig interfaceConfig;

    /**
     * 方法熔断配置
     */
    private Map<String, CBConfig> methodConfig;

    public InterfaceCBConfig(CBConfig interfaceConfig, Map<String, CBConfig> methodConfig) {
        this.interfaceConfig = interfaceConfig;
        this.methodConfig = methodConfig;
    }

    public CBConfig getInterfaceConfig() {
        return interfaceConfig;
    }

    public Map<String, CBConfig> getMethodConfig() {
        return methodConfig;
    }
}
