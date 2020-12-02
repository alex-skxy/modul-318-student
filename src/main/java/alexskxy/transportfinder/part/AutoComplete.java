package alexskxy.transportfinder.part;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * based on https://gist.github.com/Faoc/2093cf14c91033668b2e
 * by Caleb Brinkman
 * and Fabian Ochmann
 */
public class AutoComplete extends TextField {
    private StringListFunction entrySource;

    private ObservableList<String> filteredEntries
            = FXCollections.observableArrayList();

    private ContextMenu entriesPopup;

    private boolean caseSensitive = false;

    private boolean popupHidden = false;

    private String textOccurenceStyle = "-fx-font-weight: bold; "
            + "-fx-fill: red;";

    private int maxEntries = 10;

    public AutoComplete() {
        super();

        entriesPopup = new ContextMenu();
        textProperty().addListener(new ChangeListener<String>() {
            @SneakyThrows
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (getText().length() < 3) {
                    filteredEntries.clear();
                    entriesPopup.hide();
                } else {
                    LinkedList<String> searchResult = new LinkedList<>();
                    var search = CompletableFuture.supplyAsync(() -> entrySource.getStringList(getText()));
                    searchResult.addAll(search.get());

                    //Check if the entered Text is part of some entry
                    String text = getText();

                    if (searchResult.size() > 0) {
                        filteredEntries.clear();
                        filteredEntries.addAll(searchResult);

                        //Only show popup if not in filter mode
                        if (!isPopupHidden()) {
                            populatePopup(searchResult, text);
                            if (!entriesPopup.isShowing()) {
                                entriesPopup.show(AutoComplete.this, Side.BOTTOM, 0, 0);
                            }
                        }
                    } else {
                        entriesPopup.hide();
                    }
                }
            }
        });

        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                entriesPopup.hide();
            }
        });

    }

    public StringListFunction getEntrySource() {
        return entrySource;
    }

    public void setEntrySource(StringListFunction entrySource) {
        this.entrySource = entrySource;
    }

    private void populatePopup(List<String> searchResult, String text) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        int count = Math.min(searchResult.size(), getMaxEntries());
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            int occurence;

            if (isCaseSensitive()) {
                occurence = result.indexOf(text);
            } else {
                occurence = result.toLowerCase().indexOf(text.toLowerCase());
            }

            //Part before occurence (might be empty)
            Text pre = new Text(result.substring(0, occurence));
            //Part of (first) occurence
            Text in = new Text(result.substring(occurence,
                    occurence + text.length()));
            in.setStyle(getTextOccurenceStyle());
            //Part after occurence
            Text post = new Text(result.substring(occurence + text.length(),
                    result.length()));

            TextFlow entryFlow = new TextFlow(pre, in, post);

            CustomMenuItem item = new CustomMenuItem(entryFlow, true);
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setText(result);
                    entriesPopup.hide();
                }
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public String getTextOccurenceStyle() {
        return textOccurenceStyle;
    }

    public void setTextOccurenceStyle(String textOccurenceStyle) {
        this.textOccurenceStyle = textOccurenceStyle;
    }

    public boolean isPopupHidden() {
        return popupHidden;
    }

    public void setPopupHidden(boolean popupHidden) {
        this.popupHidden = popupHidden;
    }

    public ObservableList<String> getFilteredEntries() {
        return filteredEntries;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

}
