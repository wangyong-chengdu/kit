package cd.wangyong.util.test.pipeline.increment_tasks;

import java.util.List;

import org.springframework.util.Assert;

import cd.wangyong.util.pipeline.PipeTaskNode;
import cd.wangyong.util.pipeline.PipeValueObj;

/**
 * 累加任务：接受输入，+1
 * @author andy
 * @since 2020/10/25
 */
public abstract class AbstractIncrementTask implements PipeTaskNode {


    @Override
    public String businessName() {
        return "integer_increment";
    }

    @Override
    public PipeValueObj execute(List<PipeValueObj> inputs) {
        Assert.notEmpty(inputs, "Inputs is empty.");
        return PipeValueObj.success(inputs.stream().mapToInt(pipeValueObj -> (int)pipeValueObj.getValue()).sum() + 1);
    }
}
