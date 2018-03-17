package us.dontcareabout.fontMetrics.client;

import java.util.HashMap;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.canvas.dom.client.Context2d.TextBaseline;

class MainLogic {
	private static final Canvas canvas = Canvas.createIfSupported();
	private static final Context2d context = canvas.getContext2d();

	private double padding;

	FontMetrics getMetrics(Font font, HashMap<MetricsField, String> testTexts) {
		if (canvas == null) {
			throw new UnsupportedOperationException("Browser is not supported.");
		}

		padding = font.size * 0.5;
		canvas.setCoordinateSpaceWidth(font.size * 2);
		canvas.setCoordinateSpaceHeight((int)(font.size * 2 + padding));
		context.setFont(font.toString());
		context.setTextBaseline(TextBaseline.TOP);
		context.setTextAlign(TextAlign.CENTER);

		FontMetrics result = new FontMetrics();

		for (MetricsField type : MetricsField.values()) {
			String text = testTexts.get(type);

			if (text == null) { text = type.text; }

			if (type == MetricsField.bottom) {
				result.setValue(type, computeLineHeight());
				continue;
			}

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

	//==== bottom 專用 ====
	private double computeLineHeight() {
		setAlignment(true);
		double gutter = canvas.getCoordinateSpaceHeight() - measureBottom(MetricsField.bottom.text);
		setAlignment(false);
		return measureBottom(MetricsField.bottom.text) + gutter;
	}

	private void setAlignment(boolean isBottom) {
		context.setTransform(1, 0, 0, 1, 0, isBottom ? canvas.getCoordinateSpaceHeight() : 0);
		context.setTextBaseline(isBottom ? TextBaseline.BOTTOM : TextBaseline.TOP);
	}
	// ======== //
}
