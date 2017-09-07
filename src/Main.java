import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * Application single entry point class.
 * 
 * @author Todor Balabanov
 */
public class Main {

	/**
	 * Symbol info.
	 */
	private static enum Symbol {
		DOLLAR("Dollar", "d"), APPLE("Apple", "a"), BANNANA("Bannana", "n"), PLUM("Plum", "p"), CHERRY("Cherry",
				"c"), GRAPE("Grape", "g"), BAR("Bar", "b"), SEVEN("Seven", "s"), WILD("Wild", "w");

		/**
		 * Symbol name.
		 */
		private String title;

		/**
		 * Letter used for the symbol.
		 */
		private String letter;

		/**
		 * Title factory function.
		 * 
		 * @param title
		 *            Symbol name.
		 * @return Symbol enumeration.
		 */
		public static Symbol title(String title) {
			for (Symbol value : values()) {
				if (value.name().equals(title)) {
					return value;
				}
			}

			return null;
		}

		/**
		 * Letter factory function.
		 * 
		 * @param letter
		 *            Symbol letter.
		 * @return Symbol enumeration.
		 */
		public Symbol letter(String letter) {
			for (Symbol value : values()) {
				if (value.letter().equals(letter)) {
					return value;
				}
			}

			return null;
		}

		/**
		 * Constructor with all parameters.
		 * 
		 * @param title
		 *            Symbol name.
		 * @param letter
		 *            Symbol letter.
		 */
		Symbol(String title, String letter) {
			this.title = title;
			this.letter = letter;
		}

		/**
		 * Symbol name getter.
		 * 
		 * @return Symbol name.
		 */
		public String title() {
			return title;
		}

		/**
		 * Symbol letter getter.
		 * 
		 * @return Symbol letter.
		 */
		public String letter() {
			return letter;
		}
	}

	/**
	 * Margin from the borders.
	 */
	private static final int MAGIN = 30;

	/**
	 * Symbols coordinates.
	 */
	private static final int COORDINATES[][] = { { 126 + MAGIN, 190 + MAGIN, 314 - MAGIN, 330 - MAGIN },
			{ 336 + MAGIN, 190 + MAGIN, 524 - MAGIN, 330 - MAGIN },
			{ 546 + MAGIN, 190 + MAGIN, 734 - MAGIN, 330 - MAGIN },
			{ 756 + MAGIN, 190 + MAGIN, 944 - MAGIN, 330 - MAGIN },
			{ 966 + MAGIN, 190 + MAGIN, 1154 - MAGIN, 330 - MAGIN },
			{ 126 + MAGIN, 332 + MAGIN, 314 - MAGIN, 472 - MAGIN },
			{ 336 + MAGIN, 332 + MAGIN, 524 - MAGIN, 472 - MAGIN },
			{ 546 + MAGIN, 332 + MAGIN, 734 - MAGIN, 472 - MAGIN },
			{ 756 + MAGIN, 332 + MAGIN, 944 - MAGIN, 472 - MAGIN },
			{ 966 + MAGIN, 332 + MAGIN, 1154 - MAGIN, 472 - MAGIN },
			{ 126 + MAGIN, 474 + MAGIN, 314 - MAGIN, 614 - MAGIN },
			{ 336 + MAGIN, 474 + MAGIN, 524 - MAGIN, 614 - MAGIN },
			{ 546 + MAGIN, 474 + MAGIN, 734 - MAGIN, 614 - MAGIN },
			{ 756 + MAGIN, 474 + MAGIN, 944 - MAGIN, 614 - MAGIN },
			{ 966 + MAGIN, 474 + MAGIN, 1154 - MAGIN, 614 - MAGIN },
			{ 126 + MAGIN, 616 + MAGIN, 314 - MAGIN, 756 - MAGIN },
			{ 336 + MAGIN, 616 + MAGIN, 524 - MAGIN, 756 - MAGIN },
			{ 546 + MAGIN, 616 + MAGIN, 734 - MAGIN, 756 - MAGIN },
			{ 756 + MAGIN, 616 + MAGIN, 944 - MAGIN, 756 - MAGIN },
			{ 966 + MAGIN, 616 + MAGIN, 1154 - MAGIN, 756 - MAGIN }, };

	/**
	 * Calculate average color for the image.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Average color as integer value.
	 */
	private static int averageColor1(BufferedImage image) {
		long red = 0;
		long green = 0;
		long blue = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));

				red += pixel.getRed();
				green += pixel.getGreen();
				blue += pixel.getBlue();
			}
		}

		double count = image.getWidth() * image.getHeight();
		return (new Color((int) Math.round((red / count)), (int) Math.round((green / count)),
				(int) Math.round((blue / count)))).getRGB();
	}

	/**
	 * Calculate average color for the image.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Average color as integer value.
	 */
	private static int averageColor2(BufferedImage image) {
		long sum = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				sum += image.getRGB(x, y);
			}
		}

		return (int) (sum /= image.getWidth() * image.getHeight());
	}

	/**
	 * Calculate average color for the image.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Average color as integer value.
	 */
	private static int averageColor3(BufferedImage image) {
		long red = 0;
		long green = 0;
		long blue = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));

				red += pixel.getRed();
				green += pixel.getGreen();
				blue += pixel.getBlue();
			}
		}

		return (int) Math.round((red + green + blue) / (3D * image.getWidth() * image.getHeight()));
	}

	/**
	 * Calculate average color for the image.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Average color as integer value.
	 */
	private static int averageColor4(BufferedImage image) {
		double hue = 0;
		double saturation = 0;
		double brightness = 0;

		float hsb[] = { 0, 0, 0 };

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));
				Color.RGBtoHSB(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), hsb);

				hue += hsb[0];
				saturation += hsb[1];
				brightness += hsb[2];
			}
		}

		hue /= image.getWidth() * image.getHeight();
		saturation /= image.getWidth() * image.getHeight();
		brightness /= image.getWidth() * image.getHeight();

		return (Color.getHSBColor((float) hue, (float) saturation, (float) brightness)).getRGB();
	}

	/**
	 * Calculate average color for the image.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Average color as integer value.
	 */
	private static int averageColor5(BufferedImage image) {
		long red = 0;
		long green = 0;
		long blue = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));

				red += pixel.getRed();
				green += pixel.getGreen();
				blue += pixel.getBlue();
			}
		}

		double count = image.getWidth() * image.getHeight();
		return 1000000 * (int) Math.round((red / count)) + 1000 * (int) Math.round((green / count))
				+ (int) Math.round((blue / count));
	}

	/**
	 * Capture symbols in particular image.
	 * 
	 * @param file
	 *            Full file path and name.
	 * 
	 * @throws IOException
	 *             Exception if there is a problem with the file.
	 */
	private static int[][] captureSymbols(String file, String result, int[][] indices) throws IOException {
		Image input = ImageIO.read(new File(file));

		int i = 0, j = 0;
		for (int[] corners : COORDINATES) {
			BufferedImage symbol = ((BufferedImage) input).getSubimage(corners[0], corners[1],
					corners[2] - corners[0] + 1, corners[3] - corners[1] + 1);

			indices[i][j] = averageColor5(symbol);

			j++;
			if (j >= indices[i].length) {
				i++;
				j = 0;
			}
		}

		return indices;
	}

	/**
	 * Application single entry point method.
	 * 
	 * @param args
	 *            Command line arguments.
	 * 
	 * @throws IOException
	 *             Image file problem.
	 */
	public static void main(String[] args) throws IOException {
		for (File file : (new File("./dat")).listFiles()) {
			if (file.isFile() == false) {
				continue;
			}

			if (file.getName().contains(".png") == false) {
				continue;
			}

			System.out.println(Arrays.deepToString(captureSymbols(file.getPath(),
					file.getPath().substring(0, file.getPath().length() - file.getName().length()) + "result"
							+ file.getName(),
					new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } })));
		}
	}

}
