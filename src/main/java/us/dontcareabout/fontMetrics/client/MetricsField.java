package us.dontcareabout.fontMetrics.client;

import java.util.HashMap;

public enum MetricsField {
	bottom("A"),	//bottom 的計算比較特別，給不給 isTop 都沒差
	capHeight("S", true),
	baseline("n"),
	xHeight("x", true),
	descent("p"),
	ascent("h", true),
	tittle("i", true);

	public final String text;
	public final boolean measureTop;


	MetricsField(String text) {
		this(text, false);
	}

	MetricsField(String text, boolean isTop) {
		this.text = text;
		this.measureTop = isTop;
	}

	public static HashMap<MetricsField, String> defaultTestText() {
		HashMap<MetricsField, String> result = new HashMap<>();

		for (MetricsField type : MetricsField.values()) {
			result.put(type, type.text);
		}

		return result;
	}
}
