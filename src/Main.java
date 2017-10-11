import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * Application single entry point class.
 * 
 * @author Todor Balabanov
 */
public class Main {
	/**
	 * Spinning mode text image sample.
	 */
	private static BufferedImage SPIN_TEXT = null;

	/**
	 * Margin from the vertical borders.
	 */
	private static final int X_MARGIN = 15;

	/**
	 * Margin from the horizontal borders.
	 */
	private static final int Y_MARGIN = 10;

	/**
	 * X coordinate of the top left symbol.
	 */
	private static final int X_INITIAL = 3;

	/**
	 * Y coordinate of the top left symbol.
	 */
	private static final int Y_INITIAL = 4;

	/**
	 * X to the next symbol.
	 */
	private static final int X_OFFSET = 116;

	/**
	 * Y to the next symbol.
	 */
	private static final int Y_OFFSET = 80;

	/**
	 * X size of the symbol.
	 */
	private static final int X_SIZE = 104;

	/**
	 * Y size of the symbol.
	 */
	private static final int Y_SIZE = 80;

	/**
	 * Symbols coordinates.
	 */
	private static final int COORDINATES[][] = {

			{ 0 * X_OFFSET + X_INITIAL + X_MARGIN, 0 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					0 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 0 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 1 * X_OFFSET + X_INITIAL + X_MARGIN, 0 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					1 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 0 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 2 * X_OFFSET + X_INITIAL + X_MARGIN, 0 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					2 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 0 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 3 * X_OFFSET + X_INITIAL + X_MARGIN, 0 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					3 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 0 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 4 * X_OFFSET + X_INITIAL + X_MARGIN, 0 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					4 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 0 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 0 * X_OFFSET + X_INITIAL + X_MARGIN, 1 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					0 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 1 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 1 * X_OFFSET + X_INITIAL + X_MARGIN, 1 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					1 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 1 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 2 * X_OFFSET + X_INITIAL + X_MARGIN, 1 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					2 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 1 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 3 * X_OFFSET + X_INITIAL + X_MARGIN, 1 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					3 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 1 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 4 * X_OFFSET + X_INITIAL + X_MARGIN, 1 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					4 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 1 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 0 * X_OFFSET + X_INITIAL + X_MARGIN, 2 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					0 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 2 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 1 * X_OFFSET + X_INITIAL + X_MARGIN, 2 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					1 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 2 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 2 * X_OFFSET + X_INITIAL + X_MARGIN, 2 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					2 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 2 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 3 * X_OFFSET + X_INITIAL + X_MARGIN, 2 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					3 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 2 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 4 * X_OFFSET + X_INITIAL + X_MARGIN, 2 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					4 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 2 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 0 * X_OFFSET + X_INITIAL + X_MARGIN, 3 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					0 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 3 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 1 * X_OFFSET + X_INITIAL + X_MARGIN, 3 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					1 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 3 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 2 * X_OFFSET + X_INITIAL + X_MARGIN, 3 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					2 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 3 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 3 * X_OFFSET + X_INITIAL + X_MARGIN, 3 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					3 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 3 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },
			{ 4 * X_OFFSET + X_INITIAL + X_MARGIN, 3 * Y_OFFSET + Y_INITIAL + Y_MARGIN,
					4 * X_OFFSET + X_SIZE + X_INITIAL - X_MARGIN, 3 * Y_OFFSET + Y_SIZE + Y_INITIAL - Y_MARGIN },

	};

	/**
	 * Static data initialization.
	 */
	static {
		try {
			SPIN_TEXT = ImageIO.read(new File("./data/samples/spinning.png"));
		} catch (IOException e) {
		}
	}

	/**
	 * Calculate similarity with predefined samples.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Letter of the most similar symbol.
	 */
	private static String similarity(BufferedImage image) {
		return Symbol.sample(image).letter();
	}

	/**
	 * 
	 * @param file
	 *            Full input image file path and name.
	 * @param result
	 *            Name of the result file if images are stored as output.
	 * @param letters
	 *            The letters of the symbols on the screen.
	 * 
	 * @return Reference to the array with the letters on the screen.
	 * 
	 * @throws IOException
	 *             Exception if there is a problem with the file.
	 */
	private static void captureSamples(String file, String result) throws IOException {
		BufferedImage input = ImageIO.read(new File(file));

		BufferedImage output = new BufferedImage(input.getWidth(null), input.getHeight(null), input.getType());
		Graphics g = output.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, output.getWidth(), output.getHeight());

		for (int[] corners : COORDINATES) {
			BufferedImage symbol = ((BufferedImage) input).getSubimage(corners[0], corners[1], corners[2] - corners[0],
					corners[3] - corners[1]);

			g.drawImage(input, corners[0], corners[1], corners[2], corners[3], corners[0], corners[1], corners[2],
					corners[3], null);
		}

		ImageIO.write((RenderedImage) output, "png", new File(result));
	}

	/**
	 * 
	 * @param file
	 *            Full input image file path and name.
	 * @param letters
	 *            The letters of the symbols on the screen.
	 * @return Reference to the array with the letters on the screen.
	 * @throws IOException
	 *             Exception if there is a problem with the file.
	 */
	private static String[][] captureSymbols(String file, String[][] letters) throws IOException {
		BufferedImage input = ImageIO.read(new File(file));

		int i = 0, j = 0;
		for (int[] corners : COORDINATES) {
			BufferedImage symbol = ((BufferedImage) input).getSubimage(corners[0], corners[1], corners[2] - corners[0],
					corners[3] - corners[1]);

			letters[i][j] = "" + similarity(symbol);

			j++;
			if (j >= letters[i].length) {
				i++;
				j = 0;
			}
		}

		return letters;
	}

	/**
	 * Obtain symbols as samples in image files.
	 * 
	 * @throws IOException
	 *             Procedure end if there is a input/output problem.
	 */
	private static void samples() throws IOException {
		for (File file : (new File("./data/examples")).listFiles()) {
			if (file.isFile() == false) {
				continue;
			}

			if (file.getName().contains(".png") == false) {
				continue;
			}

			captureSamples(file.getPath(),
					file.getPath().substring(0, file.getPath().length() - file.getName().length()) + "result"
							+ file.getName());
		}
	}

	/**
	 * Capture part of the image with spinning mode information.
	 * 
	 * @param file
	 *            Image file to check.
	 * 
	 * @return True if it is spinning mode picture, false otherwise.
	 * 
	 * @throws IOException
	 *             Procedure end if there is a input/output problem.
	 */
	private static boolean captureSpinMode(String file) throws IOException {
		BufferedImage text = ((BufferedImage) ImageIO.read(new File(file))).getSubimage(245, 342, 66, 9);

		if (text.getWidth() != SPIN_TEXT.getWidth() || text.getHeight() != SPIN_TEXT.getHeight()) {
			throw new RuntimeException(String.format("Size of the images is different: [%d,%d] <> [%d,%d] !",
					text.getWidth(), text.getHeight(), SPIN_TEXT.getWidth(), SPIN_TEXT.getHeight()));
		}

		int buffer1[] = new int[text.getWidth() * text.getHeight()];
		text.getRGB(0, 0, text.getWidth(), text.getHeight(), buffer1, 0, text.getWidth());
		int buffer2[] = new int[SPIN_TEXT.getWidth() * SPIN_TEXT.getHeight()];
		SPIN_TEXT.getRGB(0, 0, SPIN_TEXT.getWidth(), SPIN_TEXT.getHeight(), buffer2, 0, SPIN_TEXT.getWidth());

		double sum = 0;
		int size = Math.min(buffer1.length, buffer2.length);
		for (int i = 0; i < size; i++) {
			sum += Math.abs((buffer1[i] & 0xFFFFFF) - (buffer2[i] & 0xFFFFFF));
		}

		if (sum / size < 1_400_000) {
			return true;
		} else {
			System.err.println(file);
			System.err.println(sum / size);
		}

		return false;
	}

	/**
	 * Filter all images in spin mode.
	 */
	private static void filter() throws IOException {
		for (File file : (new File("./data/input")).listFiles()) {
			if (file.isFile() == false) {
				continue;
			}

			if (file.getName().contains(".png") == false) {
				continue;
			}

			if (captureSpinMode(file.getPath()) == true) {
				Files.deleteIfExists(file.toPath());
			}
		}
	}

	/**
	 * Processing of the images.
	 * 
	 * @throws IOException
	 *             Procedure end if there is a input/output problem.
	 */
	private static void process() throws IOException {
		String pieces[] = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "" };

		for (File file : (new File("./data/input")).listFiles()) {
			if (file.isFile() == false) {
				continue;
			}

			if (file.getName().contains(".png") == false) {
				continue;
			}

			String[][] view = captureSymbols(file.getPath(), new String[][] { { "", "", "", "", "" },
					{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" } });

			for (int j = 0; j < view[0].length; j++) {
				for (int i = 0; i < view.length; i++) {
					pieces[i + 5 * j] += view[i][j] + "\t";
				}
			}

			System.err.println(Arrays.deepToString(view));
		}

		/*
		 * Report final result.
		 */
		for (String value : pieces) {
			System.out.println(value);
		}
	}

	/**
	 * Application single entry point method.
	 * 
	 * Command which can be used to start the application:
	 * 
	 * java -cp ./bin/ Main >./bin/out.txt & disown
	 * 
	 * @param args
	 *            Command line arguments.
	 * 
	 * @throws IOException
	 *             Image file problem.
	 */
	public static void main(String[] args) throws IOException {
		// samples();
		// filter();
		process();
	}

}
