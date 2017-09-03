import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Application single entry point class.
 * 
 * @author Todor Balabanov
 */
public class Main {

	/**
	 * Symbols coordinates.
	 */
	private static int coordinates[][] = { { 126, 190, 314, 330 }, { 336, 190, 524, 330 }, { 546, 190, 734, 330 },
			{ 756, 190, 944, 330 }, { 966, 190, 1154, 330 }, { 126, 332, 314, 472 }, { 336, 332, 524, 472 },
			{ 546, 332, 734, 472 }, { 756, 332, 944, 472 }, { 966, 332, 1154, 472 }, { 126, 474, 314, 614 },
			{ 336, 474, 524, 614 }, { 546, 474, 734, 614 }, { 756, 474, 944, 614 }, { 966, 474, 1154, 614 },
			{ 126, 616, 314, 756 }, { 336, 616, 524, 756 }, { 546, 616, 734, 756 }, { 756, 616, 944, 756 },
			{ 966, 616, 1154, 756 }, };

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
		Image input = ImageIO.read(new File("./dat/00001.png"));
		BufferedImage output = new BufferedImage(input.getWidth(null), input.getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		Graphics g = output.getGraphics();

		g.drawImage(input, 0, 0, null);

		g.setColor(Color.BLACK);
		for (int[] symbol : coordinates) {
			g.fillRect(symbol[0], symbol[1], symbol[2] - symbol[0] + 1, symbol[3] - symbol[1] + 1);
		}

		ImageIO.write((RenderedImage) output, "png", new File("./dat/example00001.png"));
	}

}
