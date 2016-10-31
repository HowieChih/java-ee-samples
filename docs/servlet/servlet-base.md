## Servlet API

Servlet API由以下两个包组成：

* javax.servlet包，定义了开发独立于协议的服务器小程序的接口和类。
* javax.servlet.http包，定义了开发采用HTTP协议通信的服务器小程序的接口和类。

## Servlet生命周期

1. 加载和实例化Servlet。当容器启动时，它将查找部署描述文件或者@WebServlet来加载和实例化Servlet。
2. 初始化Servlet。容器创建了Servlet实例后，调用``init(ServletConfig config)``来初始化Servlet。在``init(ServletConfig config)``方法调用后，容器将会调用无参数的``init()``方法，之后Servlet就被初始化了。**一个Servlet实例在生命周期只能被初始化一次，即调用``init``方法一次**，使用web.xml声明Servlet时，可以为同一个class定义多个Servlet实例，但@WebServlet好像就不太好这么做了。Servlet容器默认是在启动时不进行初始化（Lazy Loading）的，可以使用``load-on-startup``来进行预初始化。
3. 为请求提供服务。在Servlet实例正常初始化后，当容器接收到对该Servlet的请求时，都会创建一个新的线程，通过调用``service(ServletRequest, ServletResponse)``方法将该请求分配给该Servlet实例。有多少个请求，服务器将会创建多少线程。``HttpServlet``重写了``service``方法，这样我们可以方便地使用``doPost``或者``doGet``来处理HTTP请求。一个线程服务结束后，线程将被销毁或者返回到容器管理的线程池。当有新请求发生时，再次调用``service()``方法。
4. 销毁和卸载Servlet。当Servlet容器决定一个Servlet实例不再需要时，调用``destroy()``方法。在调用``destory()``方法之前，容器会等待其他执行Servlet的``service()``方法的线程结束。