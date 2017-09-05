import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
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
	 * Symbol names.
	 */
	private static final String[] SYMBOLS = { "Dollar", "Apple", "Bannana", "Plum", "Cherry", "Grape", "Bar", "Seven",
			"Wild" };

	/**
	 * Symbols coordinates.
	 */
	private static final int COORDINATES[][] = { { 126, 190, 314, 330 }, { 336, 190, 524, 330 }, { 546, 190, 734, 330 },
			{ 756, 190, 944, 330 }, { 966, 190, 1154, 330 }, { 126, 332, 314, 472 }, { 336, 332, 524, 472 },
			{ 546, 332, 734, 472 }, { 756, 332, 944, 472 }, { 966, 332, 1154, 472 }, { 126, 474, 314, 614 },
			{ 336, 474, 524, 614 }, { 546, 474, 734, 614 }, { 756, 474, 944, 614 }, { 966, 474, 1154, 614 },
			{ 126, 616, 314, 756 }, { 336, 616, 524, 756 }, { 546, 616, 734, 756 }, { 756, 616, 944, 756 },
			{ 966, 616, 1154, 756 }, };

	/**
	 * Calculate average color for the image.
	 * 
	 * @param image
	 *            Image as input.
	 * @return Average color as integer value.
	 */
	private static int averageColor(BufferedImage image) {
		long red = 0;
		long green = 0;
		long blue = 0;
		long count = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));

				red += pixel.getRed();
				green += pixel.getGreen();
				blue += pixel.getBlue();

				count++;
			}
		}

		return (new Color((int) (red / count), (int) (green / count), (int) (blue / count))).getRGB();
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
		// BufferedImage output = new BufferedImage(input.getWidth(null),
		// input.getHeight(null),
		// BufferedImage.TYPE_INT_RGB);
		//
		// Graphics g = output.getGraphics();
		//
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, output.getWidth(), output.getHeight());

		int i = 0, j = 0;
		for (int[] corners : COORDINATES) {
			BufferedImage symbol = ((BufferedImage) input).getSubimage(corners[0], corners[1],
					corners[2] - corners[0] + 1, corners[3] - corners[1] + 1);

			indices[i][j] = averageColor(symbol);

			// g.setColor(new Color(indices[i][j]));
			// g.fillRect(corners[0], corners[1], corners[2] - corners[0] + 1,
			// corners[3] - corners[1] + 1);

			j++;
			if (j >= indices[i].length) {
				i++;
				j = 0;
			}
		}

		// ImageIO.write((RenderedImage) output, "png", new File(result));

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
