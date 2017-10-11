import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Symbol info.
 */
enum Symbol {
	
	DOLLAR("Dollar", "d", "./data/samples/dollar.png"),

	APPLE("Apple", "a", "./data/samples/apple.png"),

	BANNANA("Bannana", "n", "./data/samples/bannana.png"),

	PLUM("Plum", "p", "./data/samples/plum.png"),

	CHERRY("Cherry", "c", "./data/samples/cherry.png"),

	GRAPE("Grape", "g", "./data/samples/grape.png"),

	BAR("Bar", "b", "./data/samples/bar.png"),

	SEVEN("Seven", "s", "./data/samples/seven.png"),

	WATERMELON("Watermelon", "m", "./data/samples/watermelon.png"),

	WILD("Wild", "w", "./data/samples/wild.png");

	/**
	 * Symbol name.
	 */
	private String title;

	/**
	 * Letter used for the symbol.
	 */
	private String letter;

	/**
	 * Bitmap image sample.
	 */
	private BufferedImage sample;

	/**
	 * Compare two images for similarity.
	 * 
	 * @param image1
	 *            First image.
	 * @param image2
	 *            Second image.
	 * 
	 * @return Similarity as weighted difference number. It it is zero the images
	 *         are identical.
	 */
	private static double compare(BufferedImage image1, BufferedImage image2) {
		if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
			throw new RuntimeException(String.format("Size of the images is different: [%d,%d] <> [%d,%d] !",
					image1.getWidth(), image1.getHeight(), image2.getWidth(), image2.getHeight()));
		}

		int buffer1[] = new int[image1.getWidth() * image1.getHeight()];
		image1.getRGB(0, 0, image1.getWidth(), image1.getHeight(), buffer1, 0, image1.getWidth());
		int buffer2[] = new int[image2.getWidth() * image2.getHeight()];
		image2.getRGB(0, 0, image2.getWidth(), image2.getHeight(), buffer2, 0, image2.getWidth());

		double sum = 0;
		int size = Math.min(buffer1.length, buffer2.length);
		for (int i = 0; i < size; i++) {
			sum += Math.abs((buffer1[i] & 0xFFFFFF) - (buffer2[i] & 0xFFFFFF));
		}

		return sum / size;
	}

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
	 * Sample factory function.
	 * 
	 * @param image
	 *            Symbol letter.
	 * @return Symbol enumeration.
	 */
	public static Symbol sample(BufferedImage image) {
		Symbol result = values()[0];
		double min = compare(values()[0].sample, image);
		// TODO Regular for will do one comparison less.
		for (Symbol value : values()) {
			double similarity = compare(value.sample, image);
			if (min > similarity) {
				min = similarity;
				result = value;
			}
		}

		return result;
	}

	/**
	 * Letter factory function.
	 * 
	 * @param letter
	 *            Symbol letter.
	 * @return Symbol enumeration.
	 */
	public static Symbol letter(String letter) {
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
	Symbol(String title, String letter, String file) {
		this.title = title;
		this.letter = letter;
		try {
			this.sample = ImageIO.read(new File(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
