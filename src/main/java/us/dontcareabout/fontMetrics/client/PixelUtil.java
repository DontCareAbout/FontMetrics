package us.dontcareabout.fontMetrics.client;

import com.google.gwt.canvas.dom.client.CanvasPixelArray;

public class PixelUtil {
	public static int getFirstIndex(CanvasPixelArray pixels) {
		for (int i = 3; i < pixels.getLength(); i += 4) {
			if (pixels.get(i) > 0) { return (i - 3) / 4; }
		}

		return pixels.getLength();
	}

	public static int getLastIndex(CanvasPixelArray pixels) {
		for (int i = pixels.getLength() - 1; i >= 3; i -= 4) {
			if (pixels.get(i) > 0) { return i / 4; }
		}

		return 0;
	}
}
