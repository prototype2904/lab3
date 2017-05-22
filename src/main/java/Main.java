import org.opencv.calib3d.StereoBM;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;

/**
 * Created by roma on 15.05.17.
 */
public class Main {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        depthMap("PNG/1_2.png", "PNG/1_1.png", "out3.png");
    }

    private static void depthMap(String img1, String img2, String outImg){
        Mat image1 = imread(Main.class.getResource(img1).getPath());
        Mat image2 = imread(Main.class.getResource(img2).getPath());
        Mat grayImage1 = new Mat();
        Mat grayImage2 = new Mat();
        cvtColor(image1, grayImage1, COLOR_RGB2GRAY);
        cvtColor(image2, grayImage2, COLOR_RGB2GRAY);
        StereoBM stereo = StereoBM.create(16, 15);
        Mat disparity = new Mat();
        stereo.compute(grayImage1, grayImage2, disparity);
        imwrite(outImg, disparity);
    }
}
