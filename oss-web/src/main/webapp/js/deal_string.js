// 用于实现表格中内容过长以省略号代替
function initTd() {
	var tds = document.getElementsByTagName("td");
	for ( var i = 0; i < tds.length; i++) {
		tds[i].innerHTML = dealStrLen(tds[i].innerHTML, 30);
	}
}

//字符串截取显示，显示前 num 个字符，后接省略号，一个中文为2个字节
function dealStrLen(str, num) {
	var shortString;
	if (str.length <= num) {
		shortString = str;
	} else {
		shortString = str.substring(0, num) + "...";
	}
	return shortString;
}

function jsAccount(a, how, b) {// 正确浮点运算
	if (a.toString().indexOf(".") < 0 && b.toString().indexOf(".") < 0) {// 没小数
		return eval(a + how + b);
	}
	// 至少一个有小数
	var alen = a.toString().split(".");
	if (alen.length == 1) {// 没有小数
		alen = 0;
	} else {
		alen = alen[1].length;
	}
	var blen = b.toString().split(".");
	if (blen.length == 1) {
		blen = 0;
	} else {
		blen = blen[1].length;
	}
	if (blen > alen)
		alen = blen;
	blen = "1";
	for (; alen > 0; alen--) {// 创建一个相应的倍数
		blen = blen + "0";
	}
	switch (how) {
	case "+":
		return (a * blen + b * blen) / blen;
		break;
	case "-":
		return (a * blen - b * blen) / blen;
		break;
	case "*":
		return ((a * blen) * (b * blen)) / (blen * blen);
		break;
	default:
		alert("你要求的\t" + how + "\t运算未完成!");
		return eval(a + how + b);
	}
}
