## SpringBoot



之前的web开发需要打war包，tomcat运行

SpringBoot后 打jar包，内嵌Tomcat

![image-20230408110417922](SpringBoot.assets/image-20230408110417922.png)



### SprinBoot概述

轻量级Java开发框架，解决了企业级应用开发的复杂性创建的，简化开发

+ 如何简化

  基于pojo的轻量级和最小侵入编程

  IOC

  AOP

  通过切面和模板减少样式代码 



+ SpringBoot就是一个javaweb开发框架

  + 可以用传统war包跑应用，但Boot使用jar包启动该Tomcat，就像maven整合了所有jar包，Spring Boot整合了所有的框架

  

+ 优点：
  + 更快入门Spring开发
  + 开箱即用，提供各种默认配置简化项目配置
  + 内嵌式容器简化Web项目
  + 没有冗余代码生成和xml配置的要求

### 微服务架构

论文地址：https://martinfowler.com/articles/microservices.html#CharacteristicsOfAMicroserviceArchitecture



### 创建springboot程序



官网提供了快速生成的网站，IDEA集成了它

可以i选择在官网下载后再导入IDEA开发

![image-20230408145155438](SpringBoot.assets/image-20230408145155438.png)

也可以使用IDEA创建一个springboot

![image-20230408143948822](SpringBoot.assets/image-20230408143948822.png)

  

![image-20230408144017519](SpringBoot.assets/image-20230408144017519.png)



==环境==

+ java 8
+ maven 3.6
+ SpringBoot 2.7.10





==项目结构==

![image-20230408154425301](SpringBoot.assets/image-20230408154425301.png)



pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!--有一个父项目-->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.10</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.lk</groupId>
	<artifactId>Hello</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>Hello</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<!--web依赖   tomcat dispacherServlet xml ...-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!--spring-boot-starter 所有的springboot依赖都是使用这个开头的-->
		<!--单元测试-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<!--打jar包插件-->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```



==编写一个http接口测试==

+ 在主程序HelloController**同级目录**下创建文件
+ 编写HelloController类

```java
//自动装配的核心原理
@RestController
public class HelloController {
    //接口：http://localhost:8080/hello
    @RequestMapping("/hello")
    //调用业务 ,接受前端的参数
    public String hello(){
        return "hello,world";
    }
}
```

+ 在主程序中启动项目，浏览器发出请求

![image-20230408154742360](SpringBoot.assets/image-20230408154742360.png)

访问**localhost:8080/hello**

![image-20230408154835279](SpringBoot.assets/image-20230408154835279.png)





#### IDEA创建springboot



![image-20230408160956728](SpringBoot.assets/image-20230408160956728.png)





![image-20230408161200346](SpringBoot.assets/image-20230408161200346.png)



==小tips==

+ sprinboot配置文件中通过 serve.port = ×××× 设置端口号

![image-20230408161328199](SpringBoot.assets/image-20230408161328199.png)

![image-20230408161318584](SpringBoot.assets/image-20230408161318584.png)



可以通过banner在线生成  修改跑主程序时候的文本图案

在配置目录下建立banner.txt ，自动识别，重启主程序，

![image-20230408161526181](SpringBoot.assets/image-20230408161526181.png)

![image-20230408161543509](SpringBoot.assets/image-20230408161543509.png)



### SpringBoot自动装配原理



#### pom.xml

通过向上寻找父项目 寻找springboot的核心依赖

![image-20230408161814495](SpringBoot.assets/image-20230408161814495.png)

![image-20230408161824245](SpringBoot.assets/image-20230408161824245.png)



当前项目pom.xml --> spring-boot-starter-parent --> spring-boot-dependencies

pom.xml中引入一些SpringBoot依赖时候，不用写依赖版本，是因为在父工程中**spring-boot-dependencies**，所有的版本都已经配置好了

![image-20230408161931896](SpringBoot.assets/image-20230408161931896.png)

#### 启动器

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**spring-boot-starter-××**

说白了就是springboot的启动场景，比如spring-boot-starter-web，它会帮我们自动导入web环境所有的依赖

springboot会将所有的功能场景，都变成一个个的启动器

要使用什么功能，导入对应的启动器就好



#### 主程序

```java

//@SpringBootApplication : 标注这个类是一个springboot的应用
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        //将springboot应用启动，反射方式
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```



+  注解 @SpringBootApplication

 

![image-20230408162848797](SpringBoot.assets/image-20230408162848797.png)

```
@SpringBootConfiguration ： springboot的配置


@EnableAutoConfiguration
```

==@SpringBootConfiguration== ： springboot的配置

![image-20230408162948455](SpringBoot.assets/image-20230408162948455.png)

说明它是一个Spring配置类

==@EnableAutoConfiguration== ： 自动配置

![image-20230408163058690](SpringBoot.assets/image-20230408163058690.png)

```java
@EnableAutoConfiguration
	@AutoConfigurationPackage 自动配置包 ，下面导入了一个自动配置包
		@Import({AutoConfigurationPackages.Registrar.class})  `包注册`
		
	@Import({AutoConfigurationImportSelector.class})  ： 自动配置导入选择

//获取所有配置
 List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
```



获取候选的配置

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = new ArrayList(SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader()));
        ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).forEach(configurations::add);
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories nor in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
```



![image-20230408164225445](SpringBoot.assets/image-20230408164225445.png)

兜兜转转 只为了把启动类下的所有资源导入

==META-INF/spring.factories==自动配置的核心文件



![image-20230408164506230](SpringBoot.assets/image-20230408164506230.png)



```
Properties properties = PropertiesLoaderUtils.loadProperties(resource);
所有的资源会加载到配置类中
```







结论：springboot所有自动配置都是在启动类中扫描并加载：**spring.factories** ，所有的自动配置类都在这里，但不一定生效，要判断条件是否成立，只要导入了对应的start，就有了对应的启动器，有了启动器，自动装配就会生效，然后配置成功



1. springboot在启动的时候，从类路径下/META-INF/spring.factories获取指定的值
2. 将这些自动配置的类导入容器，自动配置就会生效
3. 以前需要配置的东西，springboot做完了
4. 整合javaEE，解决方案和自动配置的东西都在  spring-boot-autoconfigure-2.7.10.jar 这个包下
5. 它会把所有需要导入的组件，以类名的方式返回，这些组件会被添加到容器中
6. 它会给容器中导入很多的自动配置类 （×××AutoConfiguration），就是欸容器中导入这个场景需要的所有组件，并配置好这些组件
7. 有了自动配置类，不需要再手动编写配置注入功能组件的工作

![自动装配注解解析导图](SpringBoot.assets/自动装配注解解析导图.jpg)





### 主启动类运行机制



直观感受，主方法开启后，启动web服务



#### run方法的执行

#### SpringApplication类

做了四件事情

+ 推断应用的类型是普通项目还是Web项目
+ 查找并加载所有可用初始化器，设置到Initializers属性中
+ 找出所有的应用程序监听器，设置到listeners属性中
+ 推断并设置main方法的定义类，找到运行的主类



### yaml语法

SpringBoot使用一个全局的配置文件，配置文件名字是固定的



+ application.properties
  + 语法结构 **key=value**
+ application.yml
  + 语法结构 **key: 空格 value**



配置文件作用：修改springboot的默认配置



yaml配置文件除了可以配普通的 key-value，还可以存对象 properties只能保存键值对

```yaml
#注意空格！

# key-value
name: li

#对象
students:
  name: lik
  age: 24


#行内写法
student: {name: lik, age: 24}

#-数组
pets:
  - cat
  - dog
  - pig
    
    
pets1: [cat,dog,pig]

#可以注入到配置类中
```

####  给属性赋值



原生的给属性赋值方式

创建一个pojo类

```java
//@Component 将该Dog类视为组件
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    @Value("lucky")
    private String name;
    @Value("4")
    private Integer age;
}
```

通过@Value 给Dog类赋值

测试输出

```java
@SpringBootTest
class Springboot02CofApplicationTests {
    @Autowired
    private Dog dog;
    @Test
    void contextLoads() {
        System.out.println(dog);
    }

}
```

![image-20230409094728281](SpringBoot.assets/image-20230409094728281.png)



**通过yml配置文件怎么给Dog赋值？**



在要赋值的pojo类增加**@ConfigurationProperties**注解

![image-20230409095156917](SpringBoot.assets/image-20230409095156917.png)

![image-20230409095335252](SpringBoot.assets/image-20230409095335252.png)

编写yaml配置文件

```yaml
person:
  name: lk
  age: 24
  happy: false
  birthday: 2022/11/20
  maps: {k1: v1,k2: v2}
  lists: [code,games]
  dog:
    name: lucky
    age: 4
```



**如何将实体类参数和yml配置文件绑定？**

![image-20230409095454273](SpringBoot.assets/image-20230409095454273.png)

绑定后就有注入的值了

测试

```java
@SpringBootTest
class Springboot02CofApplicationTests {
    @Autowired
    private Person p;
    @Test
    void contextLoads() {
        System.out.println(p);
    }

}
```

![image-20230409095559187](SpringBoot.assets/image-20230409095559187.png)



#### 给属性赋值 方式二

通过properties配置文件配置

![image-20230409101212031](SpringBoot.assets/image-20230409101212031.png)



```
@PropertySource 注解引入配置文件
```

![image-20230409101241891](SpringBoot.assets/image-20230409101241891.png)

```
//SPEL表达式取出配置文件的值
```

![image-20230409101308811](SpringBoot.assets/image-20230409101308811.png)

测试

```java
@SpringBootTest
class Springboot02CofApplicationTests {
    @Autowired
    private Person p;
    @Test
    void contextLoads() {
        System.out.println(p);
    }

}
```

![image-20230409101332524](SpringBoot.assets/image-20230409101332524.png)



另外需要注意的是，使用Properties配置，在properties中写中文会乱码，需要在IDEA中修改设置为UTF-8

![image-20230409101708081](SpringBoot.assets/image-20230409101708081.png)







|                | @ConfigrationProperties  | @Value     |
| -------------- | ------------------------ | ---------- |
| 功能           | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定       | 支持                     | 不支持     |
| SpEL           | 不支持                   | 支持       |
| JSR303数据校验 | 支持                     | 不支持     |
| 复杂类型封装   | 支持                     | 不支持     |

松散绑定：比如yml配置文件中写的是 **last-name**,这个和lastName是一样的，后面跟着的字母是默认大写的，这就是松散绑定

#### JSR303校验

可以在字段增加一层过滤器验证，保证数据的合法性

使用 @Validated 注解进行数据校验



比如，在name属性字段要进行Email验证，添加@Email注解

![image-20230409102955193](SpringBoot.assets/image-20230409102955193.png)

注意，需要在pom.xml导入**spring-boot-starter-validation**依赖



此时名字设置的是lk，自然报错

![image-20230409103223588](SpringBoot.assets/image-20230409103223588.png)

将name属性字段改为Email格式

![image-20230409103251752](SpringBoot.assets/image-20230409103251752.png)

测试通过

![image-20230409103317756](SpringBoot.assets/image-20230409103317756.png)

@Email 源码中，有设置一个message变量，提示报错信息

![image-20230409103629488](SpringBoot.assets/image-20230409103629488.png)

可以手动修改，![image-20230409103655891](SpringBoot.assets/image-20230409103655891.png)



![image-20230409103727927](SpringBoot.assets/image-20230409103727927.png)





**一些JSR303的注解** 

![image-20230409103936768](SpringBoot.assets/image-20230409103936768.png)





### 配置文件位置

![image-20230409104514083](SpringBoot.assets/image-20230409104514083.png)

我可以直接在项目下新建 config目录，在目录下新建一个配置文件，也可以在当前config目录下再新建子目录，在新的子目录下导入配置文件

![image-20230409104643914](SpringBoot.assets/image-20230409104643914.png)

也可以在当前类目录路径下新建一个custom-config目录，在该目录下导入配置文件

![image-20230409104837874](SpringBoot.assets/image-20230409104837874.png)

也可以在当前类目录路径下（resources/）直接配置所需配置文件

![image-20230409105014690](SpringBoot.assets/image-20230409105014690.png)





==当多个路径下都有配置文件时，默认优先选择项目路径下/config目录下的配置文件==





### 多环境配置

当有多个环境配置文件时：

![image-20230409110303112](SpringBoot.assets/image-20230409110303112.png)

默认走第一个默认的配置文件，可以在**application.yaml** 指定配置文件

![image-20230409110443827](SpringBoot.assets/image-20230409110443827.png)

这时走的就是dev环境



也可以在一个 dpplication.yaml中创建多个环境版块，这样子就不用创建多个文件了

![image-20230409110945824](SpringBoot.assets/image-20230409110945824.png)



### 自动配置原理

在 Application.yaml 中能配置的东西，  xxxProperties 都通过 xxxAutoConfigration自动装配 ，和配置文件绑定，就可以自定义配置

1. springboot启动会加载大量的自动配置类
2. 看需要的功能有没有在springboot默认写好的自动配置类中
3. 再看这个自动配置类中配置了哪些组件；（只要组件在，就不需要手动配置）
4. 给容器中自动配置类添加组件时，会从properties类中获取某些属性，只需要在配置文件中指定这些属性的值即可



xxxAutoConfigration 自动配置类；给容器中添加组件



xxxProperties 封装配置文件中的相关属性 ，可以通过yaml配置文件手动修改再配置



==可以通过 debug = true（配置文件中配置） 来查看配置的哪些类生效哪些类没有生效==

没有生效的东西，找到它对应的start依赖导入即可



### Springboot Web开发

#### 静态资源导入



```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
if (!this.resourceProperties.isAddMappings()) {
logger.debug("Default resource handling disabled");
return;
}
addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
registration.addResourceLocations(this.resourceProperties.getStaticLocations());
if (this.servletContext != null) {
ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
registration.addResourceLocations(resource);
}
});
}
```



![image-20230409152048432](SpringBoot.assets/image-20230409152048432.png)

![image-20230409152155241](SpringBoot.assets/image-20230409152155241.png)



在springboot 中，可以使用以下方式处理静态资源

+ webjars
+ public static /** resources 目录下的访问

![image-20230409152532915](SpringBoot.assets/image-20230409152532915.png)

![image-20230409152540663](SpringBoot.assets/image-20230409152540663.png)

优先级： resources > static(默认的) > public



#### 首页和图标定制

```java
private Resource getWelcomePage() {
   for (String location : this.resourceProperties.getStaticLocations()) {
      Resource indexHtml = getIndexHtml(location);
      if (indexHtml != null) {
         return indexHtml;
      }
   }
   ServletContext servletContext = getServletContext();
   if (servletContext != null) {
      return getIndexHtml(new ServletContextResource(servletContext, SERVLET_LOCATION));
   }
   return null;
}

private Resource getIndexHtml(String location) {
   return getIndexHtml(this.resourceLoader.getResource(location));
}

private Resource getIndexHtml(Resource location) {
   try {
      Resource resource = location.createRelative("index.html");
      if (resource.exists() && (resource.getURL() != null)) {
         return resource;
      }
   }
   catch (Exception ex) {
   }
   return null;
}
```

![image-20230409170319840](SpringBoot.assets/image-20230409170319840.png)





![image-20230410103851838](SpringBoot.assets/image-20230410103851838.png)

访问localhost:8080  直接默认访问到 Index.html页面



#### 模板引擎 thymeleaf



前端交给我们的页面是html页面，之前都是编写的jsp页面，jsp的好处就是当我们查出一些数据转发到JSP页面后，可以用jsp轻松实现数据的显示和交互，还包括能写java代码。现在使用springboot开发，首先是以jar包的方式，不是之前打包成war包，第二，嵌入式的使用Tomcat，所以SPringboot开发默认不支持jsp

直接使用纯静态页面的方式不可行，springboot推荐使用模板引擎 thymeleaf

![image-20230410104621528](SpringBoot.assets/image-20230410104621528.png)



导入thymeleaf依赖

```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
</dependency>

<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-java8time</artifactId>

</dependency>
```

模板引擎是以业务逻辑层和表现层分离为目的的，将规定格式的模板代码转换为业务数据的算法实现。

+ 它可以是一个过程代码、一个类，甚至是一个类库。不同的模板引擎其功用也不尽相同，但其基本原理都差不多。

Thymeleaf是用来开发Web和独立环境项目的服务器端的Java模版引擎

+ Spring官方支持的服务的渲染模板中，并不包含jsp。而是Thymeleaf和 Freemarker等，而Thymeleaf与SpringMVC的视图技术，及SpringBoot的 自动化配置集成非常完美，几乎没有任何成本，你只用关注Thymeleaf的语法 即可。

+ 动静结合：Thymeleaf 在有网络和无网络的环境下皆可运行，即它可以让美工在浏 览器查看页面的静态效果，也可以让程序员在服务器查看带数据的动态页面效果。这是 由于它支持 html 原型，然后在 html 标签里增加额外的属性来达到模板+数据的展示 方式。浏览器解释 html 时会忽略未定义的标签属性，所以 thymeleaf 的模板可以静态 地运行；当有数据返回到页面时，Thymeleaf 标签会动态地替换掉静态内容，使页面动 态显示。

+ 开箱即用：它提供标准和spring标准两种方言，可以直接套用模板实现JSTL、 OGNL表达式效果，避免每天套模板、该jstl、改标签的困扰。同时开发人员也可以扩展 和创建自定义的方言。

+ 多方言支持：Thymeleaf 提供spring标准方言和一个与 SpringMVC 完美集成的 可选模块，可以快速的实现表单绑定、属性编辑器、国际化等功能。

+ 与SpringBoot完美整合：SpringBoot提供了Thymeleaf的默认配置，并且 为Thymeleaf设置了视图解析器，我们可以像以前操作jsp一样来操作Thymeleaf。代 码几乎没有任何区别，就是在模板语法上有区别。







![image-20230410110533563](SpringBoot.assets/image-20230410110533563.png)



编写一个controller简单测试

```java
//在templates 目录下的所有页面，稚嫩通过Controller来跳转
//需要模板引擎的支持  thymeleaf
@Controller
public class indexController {
    @RequestMapping("/test")
    public String index(){
        return "test";
    }
}
```



https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html 官方文档





测试

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--所有的 html元素都可以被 thymeleaf替换接管 ，  th: 元素名-->
<div th:text="${msg}"></div>
<!--<h1 th:text="${msg}"></h1>-->
</body>
</html>
```

```java
//在templates 目录下的所有页面，稚嫩通过Controller来跳转
//需要模板引擎的支持  thymeleaf
@Controller
public class indexController {
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","springboot");
        return "test";
    }
}
```

![image-20230410112452744](SpringBoot.assets/image-20230410112452744.png)





**语法**

![image-20230410121139881](SpringBoot.assets/image-20230410121139881.png)

==test & utext==

```html
<div th:text="${msg}"></div>
<!--<h1 th:text="${msg}"></h1>-->
<div th:utext="${msg}"></div>
```

![image-20230410121633630](SpringBoot.assets/image-20230410121633630.png)



==each==

```html
<h3 th:each="user:${users}" th:text="${user}"></h3>
```

... ...

### MVC配置原理

