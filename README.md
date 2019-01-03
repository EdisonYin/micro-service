# micro-service

#INTRODUCE

The project is for personally spring-boot study.

I will add some intersting code in the project.

# Details

springboot web project.

#Function

1.schdule tasks, use quart and sprongboot-schduler config it.

2.email, can send eamil by some code.

3.About Baidu AI api named [OCTR]

Can get the words details from image. Some Detail you can learn as follow link:

http://ai.baidu.com/tech/ocr

pip 安装教程
http://www.ttlsa.com/python/how-to-install-and-use-pip-ttlsa/


# Spring Boot 启动流程

1.启动类里面调用SpringApplication.run(xxx.class,args)方法

2.在SpringApplicaiton的run方法中有两个步骤，首先创建SpringApplicaiton对象，然后再调用run方法。

3.在SpringApplicaiton构造器中调用initialize(sources)方法。

4.initialize方法中

  a.将sources转换成list加到this.sources属性中
  
  b.判断是否为web环境(在类路径下是否可以加载到Servlet和ConfigurableWebApplicationContext)
  
  c.加载Initializers(通过META-INF/spring.factories中键为ApplicationContextInitializer的配置进行加载)，dubug发现一共
  
    加载了6个initializer(spring-boot-1.5.10.RELEASE.jar中4个,spring-boot-autoconfigure-1.5.10.RELEASE.jar中2个)
    
  d.加载ApplicationListener(也是通过META-INF/spring.factories)，debug发现共加载了10个
  
  e.通过寻找main方法找到启动主类。
  
5.run方法中

  a.StopWatch主要是监控启动过程，统计启动时间，检测应用是否已经启动或者停止。
  
  b.加载SpringApplicationRunListener(也是通过META-INF/spring.factories),默认加载的是EventPublishingRunListener
  
  c.调用RunListener.starting()方法。
  
  d.根据args创建应用参数解析器ApplicationArguments;
  
  e.准备环境变量：获取环境变量environment，将应用参数放入到环境变量持有对象中，监听器监听环境变量对象的变化(listener.environmentPrepared)
  
  f.打印Banner信息(SpringBootBanner)
  
  g.创建SpringBoot的应用上下文(AnnotationConfigEmbeddedWebApplicationContext)
  
  h.prepareContext上下文之前的准备
  
  i.refreshContext刷新上下文
  
  j.afterRefresh(ApplicationRunner,CommandLineRunner接口实现类的启动)
  
  k.返回上下文对象








