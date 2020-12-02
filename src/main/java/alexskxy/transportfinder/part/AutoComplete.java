package alexskxy.transportfinder.part;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private final ContextMenu entriesPopup;
    private StringListFunction entrySource;
    private boolean popupHidden = false;

    private String textoccurrenceStyle = "-fx-font-weight: bold; "
            + "-fx-fill: #00B1E7;";

    private int maxEntries = 10;

    public AutoComplete() {
        super();

        entriesPopup = new ContextMenu();
        textProperty().addListener(new ChangeListener<String>() {
            @SneakyThrows
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (getText().length() < 3) {
                    entriesPopup.hide();
                } else {
                    LinkedList<String> searchResult = new LinkedList<>();
                    var search = CompletableFuture.supplyAsync(() -> entrySource.getStringList(getText()));
                    searchResult.addAll(search.get());

                    //Check if the entered Text is part of some entry
                    String text = getText();

                    if (searchResult.size() > 0) {
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
            int occurrence;

            occurrence = result.toLowerCase().indexOf(text.toLowerCase());

            if (!(occurrence == -1)) {
                Text pre = new Text(result.substring(0, occurrence));
                Text in = new Text(result.substring(occurrence,
                        occurrence + text.length()));
                in.setStyle(getTextoccurrenceStyle());
                Text post = new Text(result.substring(occurrence + text.length()));
                TextFlow entryFlow = new TextFlow(pre, in, post);
                CustomMenuItem item = new CustomMenuItem(entryFlow, true);
                item.setOnAction(actionEvent -> {
                    setText(result);
                    entriesPopup.hide();
                });
                menuItems.add(item);
            }

            entriesPopup.getItems().clear();
            entriesPopup.getItems().addAll(menuItems);
        }
    }

    public String getTextoccurrenceStyle() {
        return textoccurrenceStyle;
    }

    public void setTextoccurrenceStyle(String textoccurrenceStyle) {
        this.textoccurrenceStyle = textoccurrenceStyle;
    }

    public boolean isPopupHidden() {
        return popupHidden;
    }

    public void setPopupHidden(boolean popupHidden) {
        this.popupHidden = popupHidden;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

}
