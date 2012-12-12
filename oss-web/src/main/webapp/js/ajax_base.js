/**
 * 利用XMLHttpRequest 5步法，实现AJAX异步。最基础的方式实现
 * 
 * @param method
 * @param url
 * @param param
 * @param callback
 * @return
 */
function ajaxRequest(method, url, param, callMethod) {
	// 1、创建XmlHttpRequest对象
	var xmlHttp = createXMLHttpRequest();
	// 2、注册回调函数；赋值给XmlHttpRequest对象的是函数名；不是返回值 所以不能是：callback()
	xmlHttp.onreadystatechange = function() {
		callback(xmlHttp, callMethod);
	};
	// 3、设置连接信息。
	// open()方法：第一个参数：请求的方式：get ，post ；第二个参数：请求url ； 第三个参数：是否用同步还是异步。
	xmlHttp.open(method, url, true);
	// 设置编码
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded; charset=utf-8;");
	// 4、发送数据， 开始和服务器端进行交互
	xmlHttp.send(param); // 如果是"GET"方式，param则为null。如果是"POST"方式，则为传入的参数
}

/**
 * 创建XmlHttpRequest对象
 * 
 * @return
 */
function createXMLHttpRequest() {
	var xmlHttp;
	// 对于现阶段主流浏览器都能获得XmlHttpRequest对象
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
		// IE6以下的版本 获取不到XmlHttpRequest对象，利用ActiveXObject来获得
	} else if (window.ActiveXObject) {
		// 两个可以创建XmlHttpRequest对象的两个ActiveXObject名称： "MSXML2.XMLHTTP",
		// "MICROSOFT.XMLHTTP" ；
		// 存放一个数组
		var activeXname = [ "MSXML2.XMLHTTP", "MICROSOFT.XMLHTTP" ];
		// 循环这个数组，来创建XmlHttpRequest对象
		for ( var i = 0; i < activeXname.length; i++) {
			try {
				// 如果创建成功则退出循环；
				xmlHttp = new ActiveXObject(activeXname[i]);
				break;
				// 如果创建失败，则抛出异常并继续循环
			} catch (e) {
			}
		}
	}
	return xmlHttp;
}

// 5、接受响应数据
function callback(xmlHttp, callMethod) {
	// 判断对象的状态是否交互完成
	if (xmlHttp.readyState == 4) {
		// 判断http的交互是否成功
		if (xmlHttp.status == 200) {
			// 获取纯文本的数据
			var data = eval(xmlHttp.responseText);
			callMethod(data);
		}
	}
}
