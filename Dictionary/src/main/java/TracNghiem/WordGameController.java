package TracNghiem;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;

import javafx.scene.text.Font;
import javafx.util.Duration;
import models.DictionaryManagement;
import models.Word;

import java.net.URL;
import java.util.*;


public class WordGameController {
    @FXML
    private TextField input;

    @FXML
    private Button enter, delete, shuffle;

    private int point;

    @FXML
    private Button firstChar, secondChar, thirdChar, fourthChar, fifthChar, sixChar, compulsoryChar;

    @FXML
    private Pane wordAdded;

    private final char[] vowels = {'A', 'E', 'I', 'O', 'U'};

    @FXML
    private Label Point, howManyWordAdded;

    @FXML
    private Label result = new Label("Result");

    private ArrayList<String> listWordAdded;

    char c;

    private int numOfWord = 0;

    public boolean alreadyAdded(String ans) {
        for (String fromList : listWordAdded) {
            if (fromList.equals(ans)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCompulsoryWord(String ans) {
        for (int i = 0; i < ans.length(); i++) {
            if (ans.charAt(i) == c) return true;
        }
        return false;
    }


    public boolean containAEIOU(char c) {
        return (c == 'A' || c == 'E' || c == 'U' || c == 'I' || c == 'O');
    }

    public void announceResult(String results) {
        result.setText(results);
        result.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 10px;");

        FadeTransition fade = new FadeTransition(Duration.seconds(3), result);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        // Play animation when label shown
        fade.setOnFinished(event -> result.setVisible(false));
        result.setVisible(true);
        fade.play();

        fade.setNode(result);
        fade.play();
    }

    public void loadSetCharacter() {
        char c1,c2,c3,c4,c5,c6;

        Random generates = new Random();

        HashSet<Character> usedChar = new HashSet<>();
        // Set nút 1
        c1 = (char)(generates.nextInt(26) + 'A');
        while (containAEIOU(c1)) {
            c1 = (char)(generates.nextInt(26) + 'A');
        }
        usedChar.add(c1);

        //Set nút 2
        c2 = (char)(generates.nextInt(26) + 'A');
        while (usedChar.contains(c2) || containAEIOU(c2)) {
            c2 = (char)(generates.nextInt(26) + 'A');
        }
        usedChar.add(c2);


        //Set nút 3
        int num = generates.nextInt(4);
        while (usedChar.contains(vowels[num])) {
            num = generates.nextInt(4);
        }
        c3 = vowels[num];
        usedChar.add(c3);

        //Set nút 4
        num = generates.nextInt(4);
        while (usedChar.contains(vowels[num])) {
            num = generates.nextInt(4);
        }
        c4 = vowels[num];

        usedChar.add(c4);

        //Set nút 5
        c5 = (char)('A' + generates.nextInt(26));
        while (usedChar.contains(c5) || containAEIOU(c5)) {
            c5 = (char)('A' + generates.nextInt(26));
        }
        usedChar.add(c5);


        //Set nút 6
        c6 = (char)('A' + generates.nextInt(26));
        while (usedChar.contains(c6) || containAEIOU(c6)) {
            c6 = (char)('A' + generates.nextInt(26));
        }
        usedChar.add(c6);
        /*
        FadeTransition task = new FadeTransition(Duration.millis(5000));
        task.setNode(firstChar);
        task.setNode(secondChar);
        task.setNode(thirdChar);
        task.setNode(fourthChar);
        task.setNode(fifthChar);
        task.setNode(sixChar);

        task.setCycleCount(Timeline.INDEFINITE);
        task.setFromValue(1.0);
        task.setToValue(0.1);



        task.setAutoReverse(true);

        task.play();
         */
        firstChar.setText(String.valueOf(c1));
        secondChar.setText(String.valueOf(c2));
        thirdChar.setText(String.valueOf(c3));
        fourthChar.setText(String.valueOf(c4));
        fifthChar.setText(String.valueOf(c5));
        sixChar.setText(String.valueOf(c6));
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random generates = new Random();

        HashSet<Character> usedChar = new HashSet<>();

        usedChar.add(c);

        // Set nút 1
        c = (char)(generates.nextInt(26) + 'A');
        while (containAEIOU(c)) {
            c = (char)(generates.nextInt(26) + 'A');
        }
        usedChar.add(c);
        firstChar.setText(String.valueOf(c));
        firstChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.appendText(firstChar.getText());
            }
        });

        //Set nút 2
        c = (char)(generates.nextInt(26) + 'A');
        while (usedChar.contains(c) || containAEIOU(c)) {
            c = (char)(generates.nextInt(26) + 'A');
        }
        usedChar.add(c);
        secondChar.setText(String.valueOf(c));
        secondChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.appendText(secondChar.getText());
            }
        });

        //Set nút 3
        int num = generates.nextInt(4);
        while (usedChar.contains(vowels[num])) {
            num = generates.nextInt(4);
        }
        thirdChar.setText(String.valueOf(vowels[num]));
        usedChar.add(vowels[num]);
        thirdChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.appendText(thirdChar.getText());
            }
        });

        //Set nút 4
        num = generates.nextInt(4);
        while (usedChar.contains(vowels[num])) {
            num = generates.nextInt(4);
        }
        fourthChar.setText(String.valueOf(vowels[num]));
        usedChar.add(vowels[num]);
        fourthChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.appendText(fourthChar.getText());
            }
        });

        //Set nút 5
        c = (char)('A' + generates.nextInt(26));
        while (usedChar.contains(c) || containAEIOU(c)) {
            c = (char)('A' + generates.nextInt(26));
        }
        usedChar.add(c);
        fifthChar.setText(String.valueOf(c));
        fifthChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.appendText(fifthChar.getText());
            }
        });

        //Set nút 6
        c = (char)('A' + generates.nextInt(26));
        while (usedChar.contains(c) || containAEIOU(c)) {
            c = (char)('A' + generates.nextInt(26));
        }
        usedChar.add(c);
        sixChar.setText(String.valueOf(c));
        sixChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.appendText(sixChar.getText());
            }
        });


        //Set nút bắt buộc
        c = (char)('A' + generates.nextInt(26));
        while (usedChar.contains(c)) {
            c = (char)('A' + generates.nextInt(26));
        }
        usedChar.add(c);
        compulsoryChar.setText(String.valueOf(c));
        compulsoryChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.appendText(compulsoryChar.getText());
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input.clear();
            }
        });
        result.setVisible(false);
        listWordAdded = new ArrayList<>();
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String answer = input.getText().toUpperCase();
                String searchWord = answer.toLowerCase();
                searchWord = DictionaryManagement.formatWord(searchWord);
                if (!hasCompulsoryWord(answer)) {
                    announceResult("No compulsory character");
                    input.clear();
                }
                else if(alreadyAdded(answer)) {
                    announceResult("Already add");
                    input.clear();
                }
                else if (!DictionaryManagement.isInDictionary(searchWord)) {
                    announceResult("This word is not already existed");
                    input.clear();
                }
                else {
                    point += answer.length();
                    Label word = new Label(answer);
                    word.setStyle("-fx-font-weight: bold; -fx-padding: 10px; -fx-font-size: 18");
                    wordAdded.getChildren().add(word);
                    announceResult("Good job");
                    Point.setText("Point = " + point);
                    listWordAdded.add(answer);
                    if (numOfWord == 0) {
                        howManyWordAdded.setText("You have found " + ++numOfWord + " word");
                    } else {
                        howManyWordAdded.setText("You have found " + ++numOfWord + " words");
                    }
                    input.clear();
                }

            }
        });

        shuffle.setOnAction(event -> {
            loadSetCharacter();
            input.clear();
        });
    }
}
