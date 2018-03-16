package us.dontcareabout.fontMetrics.client;

import java.util.HashMap;

//TODO bottom
public class FontMetrics {
	private final HashMap<MetricsField, Double> values = new HashMap<>();

	public void setValue(MetricsField type, double value) {
		values.put(type, value);
	}

	public double getValue(MetricsField type) {
		return values.get(type);
	}

	public double getCapHeight() {
		return values.get(MetricsField.capHeight);
	}

	public double getBaseline() {
		return values.get(MetricsField.baseline);
	}

	public double getxHeight() {
		return values.get(MetricsField.xHeight);
	}

	public double getDescent() {
		return values.get(MetricsField.descent);
	}

	public double getAscent() {
		return values.get(MetricsField.ascent);
	}

	public double getTittle() {
		return values.get(MetricsField.tittle);
	}

	@Override
	//Eclipse generate
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }

		FontMetrics a = (FontMetrics) obj;
		for (MetricsField type : MetricsField.values()) {
			if (a.getValue(type) != getValue(type)) {
				return false;
			}
		}

		return true;
	}

	public static FontMetrics get(Font font) {
		return new MainLogic().getMetrics(font, MetricsField.defaultTestText());
	}

	public static FontMetrics get(Font font, HashMap<MetricsField, String> testTexts) {
		return new MainLogic().getMetrics(font, testTexts);
	}
}
