FontMetrics for GWT  \囧/
=========================

This project re-writes [FontMetrics] in pure GWT.
It is almost the same as the original design, but with a more Java style approach. :dancer:

[FontMetrics]: https://github.com/soulwire/FontMetrics


Usage
-----

### Maven ###

```XML
<dependency>
	<groupId>us.dontcareabout</groupId>
	<artifactId>FontMetrics</artifactId>
	<version>0.0.1</version>
</dependency>
```

At this monent, it doesn't exist on Maven Repository, so please do `mvn install` yourself. :u7121:


### Code ###

```Java
//If the browser doesn't support Canvas, it will throw an UnsupportedOperationException.
FontMetrics fm = FontMetrics.get(new Font("Times", "normal", 200));

double ascent = fm.getAscent();	//the ratio of font size
```
