import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;

public class ImageEditorPanel extends JPanel {

    Color[][] pixels;
    
    public ImageEditorPanel() {
        BufferedImage imageIn = null;
        try {
            // the image should be in the main project folder, not in \src or \bin
            imageIn = ImageIO.read(new File("f18.jpg"));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        pixels = makeColorArray(imageIn);
        setPreferredSize(new Dimension(pixels[0].length, pixels.length));
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        // paints the array pixels onto the screen
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                g.setColor(pixels[row][col]);
                g.fillRect(col, row, 1, 1);
            }
        }
    }

    public void run() {
        // call your image-processing methods here OR call them from keyboard event
        // handling methods
        // write image-processing methods as pure functions - for example: pixels =
        // flip(pixels);
        //pixels = grayScale(pixels);
        //pixels = sepia(pixels);
        pixels = negativeColor(pixels);
        //pixels = negativeColor(pixels);
        repaint();
    }

    public Color[][] posterize(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int i = 0; i < outputArr.length; i++){
            for (int j = 0; j < outputArr[i].length; j++) {
                Color c = inputArr[i][j];

            }
        }
        return outputArr;
    }



    public Color[][] negativeColor(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int i = 0; i < outputArr.length; i++){
            for (int j = 0; j < outputArr[i].length; j++) {
                Color c = inputArr[i][j];
                int red = c.getRed();
                int blue = c.getBlue();
                int green = c.getGreen();
                Color newC = new Color(255-red, 255-green, 255-blue);
                outputArr[i][j] = newC;
                

            }
        }
        return outputArr;
    }
public Color[][] sepia(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int i = 0; i < outputArr.length; i++){
            for (int j = 0; j < outputArr[i].length; j++) {
                Color c = inputArr[i][j];
                int red = (int) (c.getRed() * 0.4292);
                int blue = (int) (c.getBlue() * 0.07152);
                int green = (int) (c.getGreen() * 0.2588);
                int avg = (int)((red*0.4392) + (green*0.2588) + (blue *0.07152))/3;

                Color newC = new Color(red, green, blue);
                outputArr[i][j] = newC;

            }
        }
        return outputArr;
    }

    public Color[][] grayScale(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int i = 0; i < outputArr.length; i++){
            for (int j = 0; j < outputArr[i].length; j++) {
                Color c = inputArr[i][j];
                int red = c.getRed();
                int blue = c.getBlue();
                int green = c.getGreen();
                int avg = (int)((red*0.2162) + (blue*0.0722) + (green *0.7152))/3;

                Color newC = new Color(avg, avg, avg);
                outputArr[i][j] = newC;

            }
        }
        return outputArr;
    }
    // Single-Pixel Algorithm Template
    public Color[][] singlePixelAlgo(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int i = 0; i < outputArr.length; i++){
            for (int j = 0; j < outputArr[i].length; j++) {
                Color c = inputArr[i][j];

            }
        }
        return outputArr;
    }

    public Color[][] multiPixelAlgorithm(Color[][] inputArr){
        final int RADIUS = 5;
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        int count = 0;
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int neighborCount = 0;
        for(int row = 0; row < outputArr.length; row++){
            for (int col = 0; row < outputArr[row].length; col++) {
                // visit the neighbors centered at row, col
                for(int row2 = row - RADIUS; row2 <= row + RADIUS; row2++){
                    for(int col2 = col - RADIUS; col2 <= col + RADIUS; col2++){
                        if(row2 >= 0 && row2 <= outputArr.length){
                        Color neighbor = inputArr[row2][col2];
                        redSum += neighbor.getRed();
                        greenSum += neighbor.getGreen();
                        blueSum += neighbor.getBlue();
                        neighborCount++;
                    }
                }
            }
            int averageRed = redSum / neighborCount;
            int averageGreen = greenSum / neighborCount;
            int averageBlue = blueSum / neighborCount;
            // figure out what to do with all that neighbor data
            // outputArr[row][col] = newC;
            outputArr[row][col] = new Color(averageRed, averageGreen, averageBlue);
            }
        }
        return outputArr;
    }






    public Color[][] flipHorizontal(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int i = 0; i < outputArr.length; i++){
            for (int j = 0; j < outputArr[i].length; j++) {
                outputArr[i][j] = inputArr[i][inputArr[0].length - j - 1];
            }
        }
        return outputArr;
    }
    public Color[][] brighten(Color[][] inputArr){
        Color[][] outputArr = new Color[inputArr.length][inputArr[0].length];
        for(int row = 0; row < inputArr.length; row++){
            for(int col = 0; col < inputArr[0].length; col++){
                Color c = inputArr[row][col];
                outputArr[row][col] = c.darker();
            }
        }
        return outputArr;
    }

    public Color[][] makeColorArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color[][] result = new Color[height][width];
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(image.getRGB(col, row), true);
                result[row][col] = c;
            }
        }
        // System.out.println("Loaded image: width: " +width + " height: " + height);
        return result;
    }
}
