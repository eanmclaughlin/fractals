
package mandelbrot;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

import javax.tools.JavaCompiler;

import org.apache.commons.math3.complex.Complex;

public class JCalculation extends RowCalculation {
	double dx;
	double minx;
	
	double imaginary;
	double max;
	int y;
	int xTileNum;
	
	BufferedImage image;
	
	public JCalculation(double dx, double minX, int xTileNum, double i, int y, double maxIter, BufferedImage image){
		super(dx, minX, xTileNum, i, y, maxIter, image);
	}

	@Override
	protected void fillPixel(int x, int y, Complex c) {
		int iter;
		for(iter = 0; iter < this.max && c.abs() < 3; iter++ ){
			c = c.multiply(c).add(.279);
		}
		
		int color = (int) (767 * (iter / max));
		//int color = x;

		if(color > 255 & color <= 511){
			color = (color & 0xFF) << 8 | 0xFF;
		}

		else if(color > 511 && color < 767){
			color = (color & 0xFF) << 16 | 0xFFFF;
		} else if(color >= 767){
			color = 0;
		}

		image.setRGB(x, y, color);
	}
}
