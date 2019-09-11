package aop;

import com.lingfly.tinyioc.beans.aop.AspectJExpressionPointcut;
import com.lingfly.tinyioc.entity.HelloWorldService;
import com.lingfly.tinyioc.entity.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ExpressionTest {
    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* com.lingfly.tinyioc.beans.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(matches);
    }
    @Test
    public void testClassFilter2() throws Exception {
        String expression = "execution(* lingfly.tinyioc.beans.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(matches);
    }
    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* com.lingfly.tinyioc.beans.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"),HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
}
