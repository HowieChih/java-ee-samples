## Servlet API

Servlet API由以下两个包组成：

* javax.servlet包，定义了开发独立于协议的服务器小程序的接口和类。
* javax.servlet.http包，定义了开发采用HTTP协议通信的服务器小程序的接口和类。



## Servlet生命周期

1. 加载和实例化Servlet。当容器启动时，它将查找部署描述文件或者@WebServlet来加载和实例化Servlet。
2. 初始化Servlet。容器创建了Servlet实例后，调用``init(ServletConfig config)``来初始化Servlet。在``init(ServletConfig config)``方法调用后，容器将会调用无参数的``init()``方法，之后Servlet就被初始化了。**一个Servlet实例在生命周期只能被初始化一次，即调用``init``方法一次**，使用web.xml声明Servlet时，可以为同一个class定义多个Servlet实例，但@WebServlet好像就不太好这么做了。Servlet容器默认是在启动时不进行初始化（Lazy Loading）的，可以使用``load-on-startup``来进行预初始化。
3. 为请求提供服务。在Servlet实例正常初始化后，当容器接收到对该Servlet的请求时，都会创建一个新的线程，通过调用``service(ServletRequest, ServletResponse)``方法将该请求分配给该Servlet实例。有多少个请求，服务器将会创建多少线程。``HttpServlet``重写了``service``方法，这样我们可以方便地使用``doPost``或者``doGet``来处理HTTP请求。一个线程服务结束后，线程将被销毁或者返回到容器管理的线程池。当有新请求发生时，再次调用``service()``方法。
4. 销毁和卸载Servlet。当Servlet容器决定一个Servlet实例不再需要时，调用``destroy()``方法。在调用``destory()``方法之前，容器会等待其他执行Servlet的``service()``方法的线程结束。



## 分析请求

1、HTTP GET请求数据量一般不超过255个字符，而POST没有限制。

2、GET请求查询串中，所有以jsp为前缀的请求参数都是保留的，不能用作用户自定义的名称。

3、**获取请求数据**

HTTP请求发送内容在Request Body，形式可能为键值对文本，JSON，二进制数据。

``String value = request.getParameter(String name)`` - 获取键值对数据

``BufferedReader reader = request.getReader()`` - 获取纯文本数据

``ServletInputStream inputStream = request.getInputStream()`` - 获取二进制数据

4、请求转发。

``request.getRequestDispatcher(String path)``来获取RequestDispatcher对象，path可以是以 / 开头的绝对路径，解释为相对于当前应用程序的文档根目录，也可以是不以 / 开头的相对路径，解释为相对于当前资源所在的目录。

RequestDispatcher接口定义了两个方法：

- ``forward(ServletRequest request, ServletResponse response)``将请求从一个动态资源（Servlet或JSP）转发到服务器上的另一个动态或静态资源。该方法只能在响应没有提交的情况下调用，否则将抛出 ``IllegalStateException`` 异常。
- ``include(ServletRequest request, ServletResponse response)`` 将控制转发到指定的资源，这样其他资源可以对请求做部分处理，并将输出包含到当前资源中。这种控制的转移不是“永久”转发，相反，它只是暂时转向其他资源，然后当前资源再接着请求完成服务。



## 发送响应

1、**发送响应数据**

- ``PrintWriter out = response.getWriter()`` - 通过``PrintWriter``对象向客户发送文本数据。
- ``ServletOutputStream outputStream = response.getOutputStream()`` - 通过``ServletOutputStream``对象向客户发送二进制数据。

> 在response对象上**不能同时调用**``getWriter()``和 ``getOutputStream()``方法。

2、设置内容类型

在向客户端发送数据之前，一般应该设置发送数据的MIME内容类型。同时也可以设置响应的字符编码。

- ``response.setContentType(String type)`` - 设置内容类型，默认为``text/html。``
- ``response.setCharacterEncoding(String charset)`` - 设置响应字符编码，默认为ISO-8859-1，如果使用``setContentType``设置过编码的话，该方法将会覆盖``setContentType``设置的编码。

> 必须在``getWriter()``或者``getOutputStream()``之前调用设置响应类型或者字符编码的方法。

3、响应重定向

``response.sendRedirect(String location)`` 向客户发送一个重定向响应，该响应状态码为302，它命令浏览器连接到新的位置，该location可以是绝对地址（如 http://www.google.com）或相对地址（/helloapp/login.html）。

如果响应已经被提交，即响应头已经发送到浏览器，就不能调用该方法。sendRedirect前，主页面不能有内容输出。在调用sendRedrict后应该有一个return语句。

