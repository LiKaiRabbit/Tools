自己常用到的一些小工具

AES加密、Base64加密、MD5加密。

Toast弹出在手机上部，不需要.show和设置长短

SharedPreferences 工具SPTools

Stringtools 字符串转十六进制byte[]数组等

PermissionUtils动态申请权限工具，app里有demo

后续还会添加

使用请添加仓库

	
        allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
        
     
 以及依赖
     
     dependencies {
	        compile 'com.github.LiKaiRabbit:Tools:1.0'
	}
