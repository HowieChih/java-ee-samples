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