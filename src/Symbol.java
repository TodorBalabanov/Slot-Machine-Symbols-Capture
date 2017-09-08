import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Symbol info.
 */
enum Symbol {
	DOLLAR("Dollar", "d", "./dat/samples/dollar.png"), APPLE("Apple", "a", "./dat/samples/apple.png"), BANNANA(
			"Bannana", "n", "./dat/samples/bannana.png"), PLUM("Plum", "p", "./dat/samples/plum.png"), CHERRY("Cherry",
					"c", "./dat/samples/cherry.png"), GRAPE("Grape", "g", "./dat/samples/grape.png"), BAR("Bar", "b",
							"./dat/samples/bar.png"), SEVEN("Seven", "s",
									"./dat/samples/seven.png"), WILD("Wild", "w", "./dat/samples/wild.png");

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
	BufferedImage sample;

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