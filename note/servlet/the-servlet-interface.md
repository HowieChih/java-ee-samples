## Request Handling Methods

The basic ``Servlet`` interface defines a ``service`` method for handling client requests. This method is called for each request that the servlet container routes to an instance of a servlet.

The handling of concurrent requests to a Web application generally requires that the Web Developer design servlets that can deal with multiple threads executing within the ``service`` method at a particular time.

Generally the Web container handles concurrent requests to the same servlet by concurrent execution of the ``service`` method on different threads.

### HTTP Specific Request Handling Methods

The ``HttpServlet`` abstract subclass adds additional methods beyond the basic ``Servlet`` interface that are automatically called by the ``service`` method in the ``HttpServlet`` class to aid in processing HTTP-based requests. These methods are: ``doGet``, ``doPost``, ``doPut``, ``doDelete``, ``doHead``, ``doOption``, ``doTrace``.

> https://java.net/downloads/servlet-spec/Final/servlet-3_1-final.pdf
>
> reference: 2.1.1, 2.1.2

### Conditional GET Support

The HttpServlet interface defines the ``getLastModified`` method to support conditional GET operations. A conditional GET operation requests a resource be sent only if it has been modified since a specified time. In appropriate situations, **implementation** of this method may aid efficient utilization of network resources.

## Number of Instances

The servlet declaration which is either via the annotation or part of the deployment descriptor of the Web
application containing the servlet, controls how the servlet container provides instances of the servlet.

For a servlet not hosted in a distributed environment (the default), **the servlet container must use only one instance per servlet declaration**. However, for a servlet implementing the ``SingleThreadModel`` interface, the servlet container may instantiate multiple instances to handle a heavy request load and serialize requests to a particular instance.

In the case where a servlet was deployed as part of an application marked in the deployment descriptor as distributable, a container may have only one instance per servlet declaration per Java Virtual Machine (JVM™). However, if the servlet in a distributable application implements the SingleThreadModel interface, the container may instantiate multiple instances of that servlet in each JVM of the container.

> SingleThreadModel is deprecated. 实现SingleThreadModel的Servlet，Servlet容器会实例化多个实例用以保证每次多线程访问时，在同一时间只有一个线程访问某个实例的service方法。但这种保证仅仅适用于这个Servlet**实例**，对于该Servlet的静态变量和其他类如HttpSession，该接口无法保证线程安全。

It is recommended that a developer take other means to resolve those issues instead of implementing this interface, such as **avoiding the usage of an instance variable** or **synchronizing the block of the code accessing those resources**.

## Servlet Life Cycle

A servlet is managed through a well defined life cycle that defines how it is **loaded and instantiated**, is **initialized**, **handles requests from clients**, and is **taken out of service**. This life cycle is expressed in the API by the ``init``, ``service``, and ``destroy`` methods of the javax.servlet.Servlet interface that all servlets must implement directly or indirectly through the GenericServlet or HttpServlet abstract classes.

###  Loading and Instantiation

The servlet container is responsible for loading and instantiating servlets. **The loading and instantiation can occur when the container is started, or delayed until the container determines the servlet is needed to service a request**.

When the servlet engine is started, needed servlet classes must be located by the servlet container. The servlet container loads the servlet class using normal Java class loading facilities. After loading the ``Servlet`` class, the container instantiates it for use.

### Initialization

After the servlet object is instantiated, the container must initialize the servlet before it can handle requests from clients. 

**TO**：Initialization is provided so that a servlet can read persistent configuration data, initialize costly resources (such as JDBC API-based connections), and perform other one-time activities.

**Way to initialize**: calling the ``init`` method of the ``Servlet`` interface with a unique (per servlet declaration) object implementing the ServletConfig interface.

**ServletConfig**: 
- to access name-value initialization parameters from the Web application's configuration information. 
- also gives the servlet access to an object (implementing the ServletContext) that describes the servlet's runtime environment.

#### Error Conditons on Initialization

1. during initialization, the servlet instance can throw an ``UnavailableException`` or a ``ServletException``. 
2. In this case, the servlet must not be placed into active service and must be released by the container.
3. The ``destroy`` method is not called as it is considered unsuccessful initialization.
4. 异常发生后，Servlet容器可能会重新实例化和初始化该servlet。重新实例化和初始化的条件：异常是UnavailableException``，并且Servlet容器等待的时间过了该异常指定的该Servlet最短的不可用时间。

#### Tool Considerations

工具因素 - 当工具（注：根据笔者的理解，这个工具可能是应用服务器的某些检查工具，通常是验证应用的合法性和完整性）加载和内省（introspect）一个web应用时，它可能加载和内省该应用中的类，这个行为将触发那些类的静态初始方法被执行。因此，开发者不能假定Servlet处于活动的容器运行状态（active container runtime）直到它的init方法被调用后。比如Servlet不建议在它的静态（类）方法被调用时建立数据库连接或者连接EJB容器。