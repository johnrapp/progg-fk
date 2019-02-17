import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import textproc.FileReader;
import textproc.GeneralWordCounter;
import textproc.WordCountComparator;

import java.util.*;

public class BookReaderController extends Application {
    ListView<Map.Entry<String, Integer>> listView;
    HBox controlsContainer;
    ObservableList<Map.Entry<String, Integer>> words;
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Book reader!");
        primaryStage.setScene(scene);
        primaryStage.show();

        words = getWordsObservable();
        listView = new ListView<>(words);
        root.setCenter(listView);

        controlsContainer = new HBox();
        addControls();
        root.setBottom(controlsContainer);
    }

    private void addControls(){
        Button alphabeticSort = new Button("Alphabetic");
        alphabeticSort.setOnAction(e -> words.sort(Comparator.comparing(Map.Entry::getKey)));

        Button freqSort = new Button("Frequency");
        freqSort.setOnAction(e -> {
            Comparator<Map.Entry<String, Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
            words.sort(comparator.reversed());
        });

        TextField searchField = new TextField("holger");
        HBox.setHgrow(searchField, Priority.ALWAYS);
        Button searchButton = new Button("Find");
        searchButton.setOnAction(e -> {
            int index = search(searchField.getText());
            if (index != -1) {
                listView.scrollTo(index);
                listView.getSelectionModel().selectIndices(index);
            }
        });
        searchButton.setDefaultButton(true);

        controlsContainer.getChildren().addAll(alphabeticSort, freqSort, searchField, searchButton);
    }

    private ObservableList<Map.Entry<String, Integer>> getWordsObservable() {
        ArrayList<String> bookWords = FileReader.readFile("nilsholg.txt");
        ArrayList<String> stopWords = FileReader.readFile("undantagsord.txt");

        GeneralWordCounter counter = new GeneralWordCounter(new HashSet<>(stopWords));

        bookWords.forEach(counter::process);

        Set<Map.Entry<String, Integer>> counterWords = counter.getWords();

        return FXCollections.observableArrayList(counterWords);
    }

    private int search(String s) {
        int index = 0;
        for (Map.Entry<String, Integer> entry : words) {
            if (entry.getKey().equals(s)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
