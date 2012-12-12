//确认删除
function confim(recordId, type, pageIndex) {
	var result = window.confirm("是否真正需要删除？")
	if(result) {
		window.location.href = type + ".do?method=delete&recordId=" + recordId + "&pageIndex=" + pageIndex;
	}
}

//跳转统计
function goCount(type, pageIndex) {
	var dateTime = $("#dateTime").attr("value");
	window.location.href = type + ".do?method=add&pageIndex=" + pageIndex + "&dateTime=" + dateTime;
}

// 输入栏置空
function setNull(obj) {
	obj.value = "";
}

// 切换，显示隐藏层
function changeOver(obj) {
	$("#obj").toggle();
}

// ================================登录页面调用的JS方法=================================
// 登录
function login() {
	$("#login_form").submit();
}

// 没有输入用户名和密码，则置回原始值
function showBack(obj) {
	if((obj.type == "text") && (obj.value == "")) {
		obj.value = "userName";
	}
	if((obj.type == "password") && (obj.value == "")) {
		obj.value = "******";
	}
}

// ====================== main页面js ========================
// 左右层切换
function fullRight() {
	var left = $("#left").css("display");
	if(left == "none") {
		$("#left").show("fast");
		$("#right").css("width", "83%");
	} else {
		$("#left").hide("fast");
		$("#right").css("width", "98%");
	}
}

//计算两个object中的差值
function countTotal(obj1, obj2, obj3) {
	//正则判断是否数字
	var reg = new RegExp("^[0-9]*.[0-9]{1,2}$");
	var value1 = $("#" + obj1).attr("value");
	var value2 = $("#" + obj2).attr("value");
	if(reg.test(value1) && reg.test(value2)) {
		var result = value1 - value2;
		$("#" + obj3).attr("value", result);
	} else {
		alert("计算值不是数字！");
	}
}

// ================================打开注册页面显示层和关闭层============================
// 注册
function signIn() {
	// 显示注册层，主窗口灰化
	$("#full_div").css("display","block");
	$("#register").css("display","block");
}

// 关闭注册层
function closeSign() {
	$("#full_div").css("display","none");
	$("#register").css("display","none");
}

// ================================注册页面调用注册方法：使用AJAX实现=================================
function register() {
	var userName = document.getElementById("userName").value;
	var passWord = document.getElementById("passWord").value;
	var passWords = document.getElementById("passWords").value;
	var name = document.getElementById("name").value;
	var age = document.getElementById("age").value;
	var phone = document.getElementById("phone").value;
	var idCard = document.getElementById("idCard").value;
	var genders = document.getElementsByName("gender");
	// 性别选项处理
	var gender;
	for(var i = 0; i < genders.length; i++) {
		if(genders[i].checked == true) {
			gender = genders[i].value;
			break;
		}
	}
	var param = "&userName=" + userName + "&passWord=" + passWord + "&passWords=" + passWords + "&name=" + encodeURI(encodeURI(name)) + "&age=" + age + "&phone=" + phone + "&idCard=" + idCard + "&gender=" + gender;
	ajaxRequest("POST", "/finance/memberInfoController?method=register" + param, null, getMessage);
}

// 获得AJAX的回调方法
function getMessage(data) {
	var messages = data[0];
	// 显示添加是否成功信息
	document.getElementById("message").innerText = messages.message == undefined ? "" : messages.message;
	// 打印错误信息
	document.getElementById("userNameError").innerText = messages.userNameError == undefined ? "" : messages.userNameError;
	document.getElementById("passWordError").innerText = messages.passWordError == undefined ? "" : messages.passWordError;
	document.getElementById("passWordsError").innerText = messages.passWordsError == undefined ? "" : messages.passWordsError;
	document.getElementById("nameError").innerText = messages.nameError == undefined ? "" : messages.nameError;
	document.getElementById("ageError").innerText = messages.ageError == undefined ? "" : messages.ageError;
	document.getElementById("phoneError").innerText = messages.phoneError == undefined ? "" : messages.phoneError;
	document.getElementById("idCardError").innerText = messages.idCardError == undefined ? "" : messages.idCardError;
}
