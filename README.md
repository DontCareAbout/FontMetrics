FontMetrics for GWT  \囧/
=========================

> ## [Click here for English version.](README_en.md) ##


這是用純 GWT 重刻 [FontMetrics]、跟原作有 87% 相似的版本 :dancer:

[FontMetrics]: https://github.com/soulwire/FontMetrics


使用方式
--------

### Maven ###

```XML
<dependency>
	<groupId>us.dontcareabout</groupId>
	<artifactId>FontMetrics</artifactId>
	<version>0.0.1</version>
</dependency>
```

現在還沒有上 Maven Repository，請自己作 `mvn install`...... :u7121:


### Code ###

```Java
//如果 browser 不支援 Canvas（糟糕，是世界奇觀！）
//會炸 UnsupportedOperationException
FontMetrics fm = FontMetrics.get(new Font("Times", "normal", 200));

double ascent = fm.getAscent();	//相對於 font size 的比例
```
