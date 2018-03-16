package us.dontcareabout.fontMetrics.client;

public class Font {
	public final String family;
	public final String weight;
	public final int size;

	public Font() {
		this("Times", "normal", 200);
	}

	public Font(String family, String weight, int size) {
		this.family = family;
		this.weight = weight;
		this.size = size;
	}

	@Override
	public String toString() {
		//CSS syntax
		return weight + " " + size + "px " + family;
	}
}
