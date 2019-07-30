package sample;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller<ints> {

    int n, m, res;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelApp;

    @FXML
    private Label labelSizeMatrix;

    @FXML
    private TextField sizeMatrix;

    @FXML
    private Label labelArea;

    @FXML
    private TextArea textArea;

    @FXML
    private RadioButton radioMax;

    @FXML
    private RadioButton radioMin;

    @FXML
    private Button buttonRes;

    @FXML
    private TextField purpose;

    @FXML
    private TextArea textAreaResult;

    @FXML
    void initialize() {

        buttonRes.setOnAction(event -> {
            String WsizeMatrix = sizeMatrix.getText();
            int width = Integer.parseInt(WsizeMatrix);

            int height = width;

            String[] WtextArea = textArea.getText().split("\\n");
            int[][] ints = new int[WtextArea.length][];

        int[][] data = new int[height][width];
        for (n = 0; height > n; n++) {
            for (m = 0; width > m; m++) {
                for (int i = 0; i < WtextArea.length; i++) {
                    ints[i] = Arrays.stream(WtextArea[i].trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                        }
                data[n][m] = ints[n][m];
                    }
                }

            int[] arr = new int[height];
            //=====================================================

            //if MAX or Min
            if (radioMax.isSelected()) {
                res = HungarianAlgorithmMax.getAssignment(data, arr);
            }
            else if (radioMin.isSelected()) {
                res = HungarianAlgorithm.getAssignment(data, arr);
            }

            System.out.println("The Assignment problem solution with the Hungarian algorithm:");



            for (n = 0; height > n; n++) {
                for (m = 0; width > m; m++) {
                    if (arr[n] == m) {
                        System.out.printf(" [%2d]", data[n][m]);
                        data[n][m] = 0;
                        String result = Arrays
                                .stream(data)
                                .map(Arrays::toString)
                                .collect(Collectors.joining(System.lineSeparator()));

                        textAreaResult.setText(result);


                    } else {
                       System.out.printf("  %2d ", data[n][m]);
                        String result = Arrays
                                .stream(data)
                                .map(Arrays::toString)
                                .collect(Collectors.joining(System.lineSeparator()));
                        textAreaResult.setText(result);

                    }

                }

                System.out.println();
            }

            System.out.println();
            System.out.println("Result: " + res);

            String result = Integer.toString(res);
            purpose.setText(result);
        });
        }
}

