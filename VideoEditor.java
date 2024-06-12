import org.bytedeco.javacv.*;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

import java.io.File;

public class VideoEditor {
    public static void main(String[] args) {
        String inputFile = "input.mp4";
        String outputFile = "output.mp4";

        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;

        try {
            // Initialize the FrameGrabber
            grabber = new FFmpegFrameGrabber(inputFile);
            grabber.start();

            // Initialize the FrameRecorder
            recorder = new FFmpegFrameRecorder(outputFile, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.setVideoCodec(grabber.getVideoCodec());
            recorder.setFormat("mp4");
            recorder.start();

            OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                if (frame.image != null) {
                    Mat mat = converter.convert(frame);

                    // Apply a grayscale filter
                    Mat grayMat = new Mat();
                    opencv_imgproc.cvtColor(mat, grayMat, opencv_imgproc.COLOR_BGR2GRAY);

                    Frame grayFrame = converter.convert(grayMat);
                    recorder.record(grayFrame);
                } else if (frame.samples != null) {
                    recorder.recordSamples(frame.samples);
                }
            }

            System.out.println("Video processing completed. Output saved as " + outputFile);
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
            System.err.println("Error grabbing frames from video file.");
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
            System.err.println("Error recording frames to output file.");
        } finally {
            try {
                if (grabber != null) {
                    grabber.stop();
                    grabber.release();
                }
                if (recorder != null) {
                    recorder.stop();
                    recorder.release();
                }
            } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
                e.printStackTrace();
            }
        }
    }
}
