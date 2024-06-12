// import javax.swing.*;
// import javax.swing.event.ChangeEvent;
// import javax.swing.event.ChangeListener;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.image.BufferedImage;
// import java.awt.image.ConvolveOp;
// import java.awt.image.Kernel;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;

// public class ImageFilterTest extends JFrame {
//     private JLabel imageLabel;
//     private BufferedImage originalImage;
//     private BufferedImage filteredImage;
//     private JSlider brightnessSlider;
//     private JSlider contrastSlider;
//     private double zoomFactor = 1.0;

//     public ImageFilterTest() {
//         // Set the title of the frame
//         setTitle("Image Filter Application");

//         // Set the default close operation
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // Load the initial image
//         loadInitialImage("f1.jpg");

//         // Set the initial image to the JLabel
//         imageLabel = new JLabel(new ImageIcon(filteredImage));
//         imageLabel.setHorizontalAlignment(JLabel.CENTER);

//         // Add the image label to the frame
//         add(new JScrollPane(imageLabel), BorderLayout.CENTER);

//         // Create buttons for filters and actions
//         JPanel buttonPanel = new JPanel();
//         JButton grayscaleButton = createStyledButton("Grayscale");
//         JButton invertButton = createStyledButton("Invert");
//         JButton sepiaButton = createStyledButton("Sepia");
//         JButton blurButton = createStyledButton("Blur");
//         JButton originalButton = createStyledButton("Original Image");
//         JButton openButton = createStyledButton("Open Image");
//         JButton saveButton = createStyledButton("Save Image");
//         JButton zoomInButton = createStyledButton("Zoom In");
//         JButton zoomOutButton = createStyledButton("Zoom Out");

//         // Add action listeners to buttons
//         grayscaleButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 applyGrayscaleFilter();
//             }
//         });

//         invertButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 applyInvertFilter();
//             }
//         });

//         sepiaButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 applySepiaFilter();
//             }
//         });

//         blurButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 applyBlurFilter();
//             }
//         });

//         originalButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 showOriginalImage();
//             }
//         });

//         openButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 openImage();
//             }
//         });

//         saveButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 saveImage();
//             }
//         });

//         zoomInButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 zoomImage(1.25);
//             }
//         });

//         zoomOutButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 zoomImage(0.8);
//             }
//         });

//         // Add buttons to the panel
//         buttonPanel.add(grayscaleButton);
//         buttonPanel.add(invertButton);
//         buttonPanel.add(sepiaButton);
//         buttonPanel.add(blurButton);
//         buttonPanel.add(originalButton);
//         buttonPanel.add(openButton);
//         buttonPanel.add(saveButton);
//         buttonPanel.add(zoomInButton);
//         buttonPanel.add(zoomOutButton);

//         // Add the panel to the frame
//         add(buttonPanel, BorderLayout.SOUTH);

//         // Add title label
//         JLabel titleLabel = new JLabel("Image Filter Application", JLabel.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         add(titleLabel, BorderLayout.NORTH);

//         // Add sliders for brightness and contrast
//         JPanel sliderPanel = new JPanel();
//         sliderPanel.setLayout(new GridLayout(2, 1));

//         brightnessSlider = createStyledSlider("Brightness", -100, 100, 0);
//         contrastSlider = createStyledSlider("Contrast", -100, 100, 0);

//         sliderPanel.add(brightnessSlider);
//         sliderPanel.add(contrastSlider);

//         add(sliderPanel, BorderLayout.EAST);

//         // Adjust the frame size to fit the image and buttons
//         pack();

//         // Set the frame to be visible
//         setVisible(true);
//     }

//     private JButton createStyledButton(String text) {
//         JButton button = new JButton(text);
//         button.setFont(new Font("Arial", Font.BOLD, 16));
//         button.setMargin(new Insets(10, 20, 10, 20));
//         return button;
//     }

//     private JSlider createStyledSlider(String title, int min, int max, int value) {
//         JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
//         slider.setMajorTickSpacing((max - min) / 4);
//         slider.setMinorTickSpacing((max - min) / 20);
//         slider.setPaintTicks(true);
//         slider.setPaintLabels(true);
//         slider.setBorder(BorderFactory.createTitledBorder(title));
//         slider.addChangeListener(new ChangeListener() {
//             @Override
//             public void stateChanged(ChangeEvent e) {
//                 applyBrightnessAndContrastFilter();
//             }
//         });
//         return slider;
//     }

//     private void loadInitialImage(String path) {
//         try {
//             originalImage = ImageIO.read(new File(path));
//             filteredImage = originalImage;
//         } catch (IOException e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
//         }
//     }

//     private void applyGrayscaleFilter() {
//         filteredImage = new BufferedImage(
//             originalImage.getWidth(),
//             originalImage.getHeight(),
//             BufferedImage.TYPE_INT_RGB
//         );

//         for (int y = 0; y < originalImage.getHeight(); y++) {
//             for (int x = 0; x < originalImage.getWidth(); x++) {
//                 Color color = new Color(originalImage.getRGB(x, y));
//                 int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
//                 int newColor = new Color(gray, gray, gray).getRGB();
//                 filteredImage.setRGB(x, y, newColor);
//             }
//         }

//         imageLabel.setIcon(new ImageIcon(filteredImage));
//     }

//     private void applyInvertFilter() {
//         filteredImage = new BufferedImage(
//             originalImage.getWidth(),
//             originalImage.getHeight(),
//             BufferedImage.TYPE_INT_RGB
//         );

//         for (int y = 0; y < originalImage.getHeight(); y++) {
//             for (int x = 0; x < originalImage.getWidth(); x++) {
//                 Color color = new Color(originalImage.getRGB(x, y));
//                 int r = 255 - color.getRed();
//                 int g = 255 - color.getGreen();
//                 int b = 255 - color.getBlue();
//                 int newColor = new Color(r, g, b).getRGB();
//                 filteredImage.setRGB(x, y, newColor);
//             }
//         }

//         imageLabel.setIcon(new ImageIcon(filteredImage));
//     }

//     private void applySepiaFilter() {
//         filteredImage = new BufferedImage(
//             originalImage.getWidth(),
//             originalImage.getHeight(),
//             BufferedImage.TYPE_INT_RGB
//         );

//         for (int y = 0; y < originalImage.getHeight(); y++) {
//             for (int x = 0; x < originalImage.getWidth(); x++) {
//                 Color color = new Color(originalImage.getRGB(x, y));
//                 int r = (int)(0.393 * color.getRed() + 0.769 * color.getGreen() + 0.189 * color.getBlue());
//                 int g = (int)(0.349 * color.getRed() + 0.686 * color.getGreen() + 0.168 * color.getBlue());
//                 int b = (int)(0.272 * color.getRed() + 0.534 * color.getGreen() + 0.131 * color.getBlue());
//                 int newColor = new Color(Math.min(r, 255), Math.min(g, 255), Math.min(b, 255)).getRGB();
//                 filteredImage.setRGB(x, y, newColor);
//             }
//         }

//         imageLabel.setIcon(new ImageIcon(filteredImage));
//     }

//     private void applyBlurFilter() {
//         float[] matrix = {
//             1f/9f, 1f/9f, 1f/9f,
//             1f/9f, 1f/9f, 1f/9f,
//             1f/9f, 1f/9f, 1f/9f
//         };

//         ConvolveOp op = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);
//         filteredImage = op.filter(originalImage, null);

//         imageLabel.setIcon(new ImageIcon(filteredImage));
//     }

//     private void applyBrightnessAndContrastFilter() {
//         filteredImage = new BufferedImage(
//             originalImage.getWidth(),
//             originalImage.getHeight(),
//             BufferedImage.TYPE_INT_RGB
//         );

//         int brightness = brightnessSlider.getValue();
//         double contrast = 1 + (contrastSlider.getValue()
//         / 100.0);

//         for (int y = 0; y < originalImage.getHeight(); y++) {
//             for (int x = 0; x < originalImage.getWidth(); x++) {
//                 Color color = new Color(originalImage.getRGB(x, y));
//                 int r = (int) Math.min(Math.max(((color.getRed() - 128) * contrast + 128) + brightness, 0), 255);
//                 int g = (int) Math.min(Math.max(((color.getGreen() - 128) * contrast + 128) + brightness, 0), 255);
//                 int b = (int) Math.min(Math.max(((color.getBlue() - 128) * contrast + 128) + brightness, 0), 255);
//                 int newColor = new Color(r, g, b).getRGB();
//                 filteredImage.setRGB(x, y, newColor);
//             }
//         }

//         imageLabel.setIcon(new ImageIcon(filteredImage));
//     }

//     private void showOriginalImage() {
//         filteredImage = originalImage;
//         imageLabel.setIcon(new ImageIcon(filteredImage));
//     }

//     private void openImage() {
//         JFileChooser fileChooser = new JFileChooser();
//         int returnValue = fileChooser.showOpenDialog(this);
//         if (returnValue == JFileChooser.APPROVE_OPTION) {
//             File selectedFile = fileChooser.getSelectedFile();
//             try {
//                 originalImage = ImageIO.read(selectedFile);
//                 showOriginalImage();
//                 pack(); // Adjust the frame size to fit the new image
//             } catch (IOException e) {
//                 e.printStackTrace();
//                 JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
//             }
//         }
//     }

//     private void saveImage() {
//         JFileChooser fileChooser = new JFileChooser();
//         int returnValue = fileChooser.showSaveDialog(this);
//         if (returnValue == JFileChooser.APPROVE_OPTION) {
//             File file = fileChooser.getSelectedFile();
//             try {
//                 ImageIO.write(filteredImage, "png", file);
//             } catch (IOException e) {
//                 e.printStackTrace();
//                 JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage());
//             }
//         }
//     }

//     private void zoomImage(double factor) {
//         zoomFactor *= factor;
//         int width = (int) (originalImage.getWidth() * zoomFactor);
//         int height = (int) (originalImage.getHeight() * zoomFactor);
//         Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//         filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//         Graphics g = filteredImage.getGraphics();
//         g.drawImage(scaledImage, 0, 0, null);
//         g.dispose();

//         imageLabel.setIcon(new ImageIcon(filteredImage));
//         pack();
//     }

//     public static void main(String[] args) {
//         // Run the GUI construction in the Event Dispatch Thread for thread safety
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new ImageFilterTest();
//             }
//         });
//     }
// }














import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;

public class ImageFilterApp extends JFrame {
    private JLabel imageLabel;
    private BufferedImage originalImage;
    private BufferedImage filteredImage;
    private JSlider brightnessSlider;
    private JSlider contrastSlider;
    private double zoomFactor = 1.0;
    private Stack<BufferedImage> undoStack;
    private Stack<BufferedImage> redoStack;

    public ImageFilterApp() {
        // Initialize undo and redo stacks
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        // Set the title of the frame
        setTitle("Image Filter Application");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the initial image
        loadInitialImage("f1.jpg");

        // Set the initial image to the JLabel
        imageLabel = new JLabel(new ImageIcon(filteredImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the image label to the frame
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);

        // Create buttons for filters and actions
        JPanel buttonPanel = new JPanel();
        JButton grayscaleButton = createStyledButton("Grayscale");
        JButton invertButton = createStyledButton("Invert");
        JButton sepiaButton = createStyledButton("Sepia");
        JButton blurButton = createStyledButton("Blur");
        JButton originalButton = createStyledButton("Original Image");
        JButton openButton = createStyledButton("Open Image");
        JButton saveButton = createStyledButton("Save Image");
        JButton zoomInButton = createStyledButton("Zoom In");
        JButton zoomOutButton = createStyledButton("Zoom Out");
        JButton undoButton = createStyledButton("Undo");
        JButton redoButton = createStyledButton("Redo");

        // Add action listeners to buttons
        grayscaleButton.addActionListener(e -> applyFilter(this::applyGrayscaleFilter));
        invertButton.addActionListener(e -> applyFilter(this::applyInvertFilter));
        sepiaButton.addActionListener(e -> applyFilter(this::applySepiaFilter));
        blurButton.addActionListener(e -> applyFilter(this::applyBlurFilter));
        originalButton.addActionListener(e -> showOriginalImage());
        openButton.addActionListener(e -> openImage());
        saveButton.addActionListener(e -> saveImage());
        zoomInButton.addActionListener(e -> zoomImage(1.25));
        zoomOutButton.addActionListener(e -> zoomImage(0.8));
        undoButton.addActionListener(e -> undo());
        redoButton.addActionListener(e -> redo());

        // Create sticker panel
        stickerPanel = new JPanel();
        stickerPanel.setLayout(new GridLayout(3, 3));
        createStickerPanel();

        // Add buttons to the panel
        buttonPanel.add(grayscaleButton);
        buttonPanel.add(invertButton);
        buttonPanel.add(sepiaButton);
        buttonPanel.add(blurButton);
        buttonPanel.add(originalButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);

        // Add the panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
        add(stickerPanel, BorderLayout.WEST);

        // Add title label
        JLabel titleLabel = new JLabel("Image Filter Application", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Add sliders for brightness and contrast
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(2, 1));

        brightnessSlider = createStyledSlider("Brightness", -100, 100, 0);
        contrastSlider = createStyledSlider("Contrast", -100, 100, 0);

        sliderPanel.add(brightnessSlider);
        sliderPanel.add(contrastSlider);

        add(sliderPanel, BorderLayout.EAST);

        // Adjust the frame size to fit the image and buttons
        pack();

        // Set the frame to be visible
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setMargin(new Insets(10, 20, 10, 20));
        return button;
    }

    private JSlider createStyledSlider(String title, int min, int max, int value) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
        slider.setMajorTickSpacing((max - min) / 4);
        slider.setMinorTickSpacing((max - min) / 20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(title));
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                applyBrightnessAndContrastFilter();
            }
        });
        return slider;
    }

    private void loadInitialImage(String path) {
        try {
            originalImage = ImageIO.read(new File(path));
            filteredImage = originalImage;
            saveStateForUndo();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
        }
    }

    private void applyGrayscaleFilter() {
        filteredImage = new BufferedImage(
            originalImage.getWidth(),
            originalImage.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                Color color = new Color(originalImage.getRGB(x, y));
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int newColor = new Color(gray, gray, gray).getRGB();
                filteredImage.setRGB(x, y, newColor);
            }
        }

        imageLabel.setIcon(new ImageIcon(filteredImage));
    }

    private void applyInvertFilter() {
        filteredImage = new BufferedImage(
            originalImage.getWidth(),
            originalImage.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                Color color = new Color(originalImage.getRGB(x, y));
                int r = 255 - color.getRed();
                int g = 255 - color.getGreen();
                int b = 255 - color.getBlue();
                int newColor = new Color(r, g, b).getRGB();
                filteredImage.setRGB(x, y, newColor);
            }
        }

        imageLabel.setIcon(new ImageIcon(filteredImage));
    }

    private void applySepiaFilter() {
        filteredImage = new BufferedImage(
            originalImage.getWidth(),
            originalImage.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                Color color = new Color(originalImage.getRGB(x, y));
                int r = (int)(0.393 * color.getRed() + 0.769 * color.getGreen() + 0.189 * color.getBlue());
                int g = (int)(0.349 * color.getRed() + 0.686 * color.getGreen() + 0.168 * color.getBlue());
                int b = (int)(0.272 * color.getRed() + 0.534 * color.getGreen() + 0.131 * color.getBlue());
                int newColor = new Color(Math.min(r, 255), Math.min(g, 255), Math.min(b, 255)).getRGB();
                filteredImage.setRGB(x, y, newColor);
            }
        }

        imageLabel.setIcon(new ImageIcon(filteredImage));
    }

    private void applyBlurFilter() {
        float[] matrix = {
            1f/9f, 1f/9f, 1f/9f,
            1f/9f, 1f/9f, 1f/9f,
            1f/9f, 1f/9f, 1f/9f
        };

        ConvolveOp op = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);
        filteredImage = op.filter(originalImage, null);

        imageLabel.setIcon(new ImageIcon(filteredImage));
    }

    private void applyBrightnessAndContrastFilter() {
        filteredImage = new BufferedImage(
            originalImage.getWidth(),
            originalImage.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        int brightness = brightnessSlider.getValue();
        double contrast = 1 + (contrastSlider.getValue() / 100.0);

        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                Color color = new Color(originalImage.getRGB(x, y));
                int r = (int) Math.min(Math.max(((color.getRed() - 128) * contrast + 128) + brightness, 0), 255);
                int g = (int) Math.min(Math.max(((color.getGreen() - 128) * contrast + 128) + brightness, 0), 255);
                int b = (int) Math.min(Math.max(((color.getBlue() - 128) * contrast + 128) + brightness, 0), 255);
                int newColor = new Color(r, g, b).getRGB();
                filteredImage.setRGB(x, y, newColor);
            }
        }

        imageLabel.setIcon(new ImageIcon(filteredImage));
    }

    private void showOriginalImage() {
        filteredImage = originalImage;
        imageLabel.setIcon(new ImageIcon(filteredImage));
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                showOriginalImage();
                pack(); // Adjust the frame size to fit the new image
                saveStateForUndo();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
            }
        }
    }

    private void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(filteredImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage());
            }
        }
    }

    private void zoomImage(double factor) {
        zoomFactor *= factor;
        int width = (int) (originalImage.getWidth() * zoomFactor);
        int height = (int) (originalImage.getHeight() * zoomFactor);
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = filteredImage.getGraphics();
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();

        imageLabel.setIcon(new ImageIcon(filteredImage));
        pack();
        saveStateForUndo();
    }

    private void applyFilter(Runnable filter) {
        saveStateForUndo();
        filter.run();
        redoStack.clear();
    }

    private void saveStateForUndo() {
        undoStack.push(copyImage(filteredImage));
    }

    private void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(copyImage(filteredImage));
            filteredImage = undoStack.pop();
            imageLabel.setIcon(new ImageIcon(filteredImage));
        }
    }

    private void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copyImage(filteredImage));
            filteredImage = redoStack.pop();
            imageLabel.setIcon(new ImageIcon(filteredImage));
        }
    }

    private BufferedImage copyImage(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    public static void main(String[] args) {
        // Run the GUI construction in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageFilterTest();
            }
        });
    }
}

