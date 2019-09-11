# tinyspring

这是自己编写的一个简略spring框架

## 项目概览

实现了spring IOC和AOP两个主要模块
代码实现分别在ioc和aop两个包里面

### IOC

IOC的实现分为以下几部分：
1. Resource接口及以此散发出来的几个类，负责资源的获取；spring的资源获取方式有从类路径获取，从系统路径获取，注解方式获取等多种方式，本项目只实现从类路径下获取。
2. BeanDefinition及相关类从资源中获取并保存bean的定义信息
3. BeanFactory获取容器中的bean
4. ApplicationContext获取容器中的bean

BeanFactory和ApplicationContext的区别：
1. BeanFactory初始化容器时并未实例化bean，等到第一次调用bean时才实例化；ApplicationContext初始化时就实例化所有单例bean
2. 关于BeanPostProcessor等处理器，BeanFactory想要处理器生效，需要在第一次调用bean之前手动将处理器添加到BeanFactory；而ApplicationContext则在容器初始化时就通过反射机制将所有处理器添加到上下文中，不用手动添加。

### AOP
实现AOP的两个核心问题：

1. 在容器何处植入AOP？
2. 对哪些对象的哪些方法植入AOP？

对于问题1，答案在于我们在IOC中提到过的BeanPostProcessor，它有两个接口方法，分别在bean初始化的前后执行
```java
bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
```
这里的后处理器方法执行完后返回一个bean替代原来的bean，那么我们只要使一个类实现BeanPostProcessor接口，并在postProcessAfterInitialization方法中判断是否植入AOP，如果是，则生成一个AOP代理对象返回，从而实现AOP的植入。项目中AspectJAwareAdvisorAutoProxyCreator类实现这部分内容。


对于问题2，Spring用几个术语描述程序执行的某个特定点
1. 连接点(JoinPoint):比如方法执行前，方法执行后，方法返回前等具有边界性质的特定点称为连接点。AOP正是在连接点上植入，连接点由切点和通知组成
2. 切点(Pointcut):使用类和方法作为查询条件，定位到某个特定的方法
3. 通知(Advice)，也有翻译为增强，携带方位和AOP执行内容的信息，结合方位和切点可定位到确定的连接点。
4. 切面(Aspect):既有连接点的定义，也有横切逻辑(AOP所要执行的程序)

项目中PointcutAdvisor负责定义切面，用于提供对哪个对象的哪个方法进行什么样的拦截的具体内容。

在AOP的具体实现中，则有JDK和Cglib两种方式

JDK通过实现和目标类相同的接口来实现代理，所以目标类至少需要实现一个接口，Cglib通过继承目标类来实现代理，不要求目标类实现接口

如果bean是目标类，则调用org.aopalliance.intercept.MethodInterceptor的invoke方法
参数是MethodInvocation对象，包含了目标类，目标方法，入参等信息
当使用AOP时，实现类只需实现MethodInvocation接口，并在invoke方法执行相应横切逻辑并调用目标方法
而该方法不是需要AOP植入的方法，则使用反射机制java.lang.reflect.MethodMethodInvocation.proceed调用原始方法

项目中Cglib2AopProxy和JdkDynamicAopProxy实现了这两种方式