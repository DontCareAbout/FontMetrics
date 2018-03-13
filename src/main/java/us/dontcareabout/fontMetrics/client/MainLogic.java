package us.dontcareabout.fontMetrics.client;

import java.util.HashMap;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.canvas.dom.client.Context2d.TextBaseline;

public class MainLogic {
	private final Canvas canvas = Canvas.createIfSupported();
	private final Context2d context = canvas.getContext2d();

	private HashMap<MetricsField, String> testString = MetricsField.defaultTestText();
	private double padding;

	public FontMetrics getMetrics(Font font) {
		padding = font.size * 0.5;
		canvas.setCoordinateSpaceWidth(font.size * 2);
		canvas.setCoordinateSpaceHeight((int)(font.size * 2 + padding));
		context.setFont(font.weight + " " + font.size + "px " + font.family);
		context.setTextBaseline(TextBaseline.TOP);
		context.setTextAlign(TextAlign.CENTER);

		FontMetrics result = new FontMetrics();

		for (MetricsField type : MetricsField.values()) {
			String text = testString.get(type);
			result.setValue(
				type,
				type.measureTop ? measureTop(text) : measureBottom(text)
			);
		}

		//normalize()
		double baseline = result.getBaseline();

		for (MetricsField type : MetricsField.values()) {
			result.setValue(type, (result.getValue(type) - baseline) / (font.size * 1.0));
		}

		return result;
	}

	private CanvasPixelArray getPixels(String text) {
		int w = canvas.getCoordinateSpaceWidth();
		int h = canvas.getCoordinateSpaceHeight();
		context.clearRect(0, 0, w, h);
		context.fillText(text, w / 2, padding, w);
		return context.getImageData(0, 0, w, h).getData();
	}

	private double measureTop(String text) {
		return Math.round(
			PixelUtil.getFirstIndex(getPixels(text)) * 1.0 / canvas.getCoordinateSpaceWidth()
		) - padding;
	};

	private double measureBottom(String text) {
		return Math.round(
			PixelUtil.getLastIndex(getPixels(text)) * 1.0 / canvas.getCoordinateSpaceWidth()
		) - padding;
	};
}
