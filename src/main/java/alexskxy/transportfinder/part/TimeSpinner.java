package alexskxy.transportfinder.part;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputEvent;
import javafx.util.StringConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * based on https://github.com/vJechsmayr/FitForFun_prSoftwareEngineering/blob/master/src/fitandfun/TimeSpinner.java
 * from Viktoria Jechsmayr
 */
public class TimeSpinner extends Spinner<LocalTime> {
    private final ObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.HOURS);

    public TimeSpinner() {
        this(roundTime(LocalTime.now()));
    }

    public TimeSpinner(LocalTime time) {
        setEditable(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        StringConverter<LocalTime> localTimeConverter = new StringConverter<>() {

            @Override
            public String toString(LocalTime time) {
                return formatter.format(time);
            }

            @Override
            public LocalTime fromString(String string) {
                String[] tokens = string.split(":");
                int hours = getIntField(tokens, 0);
                int minutes = getIntField(tokens, 1);
                int totalSeconds = (hours * 60 + minutes) * 60;
                return roundTime(LocalTime.of((totalSeconds / 3600) % 24, (totalSeconds / 60) % 60, 0));
            }

            private int getIntField(String[] tokens, int index) {
                if (tokens.length <= index || tokens[index].isEmpty()) {
                    return 0;
                }
                return Integer.parseInt(tokens[index]);
            }

        };

        TextFormatter<LocalTime> textFormatter = new TextFormatter<>(localTimeConverter, time,
                c -> {
                    String newText = c.getControlNewText();
                    if (newText.matches("[0-9]{0,2}:[0-9]{0,2}")) {
                        return c;
                    }
                    return null;
                });

        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<>() {
            {
                setConverter(localTimeConverter);
                setValue(time);
            }

            @Override
            public void decrement(int steps) {
                setValue(mode.get().decrement(getValue(), steps));
                mode.get().select(TimeSpinner.this);
            }

            @Override
            public void increment(int steps) {
                setValue(mode.get().increment(getValue(), steps));
                mode.get().select(TimeSpinner.this);
            }
        };

        this.setValueFactory(valueFactory);
        this.getEditor().setTextFormatter(textFormatter);

        this.getEditor().addEventHandler(InputEvent.ANY, e -> {
            int caretPos = this.getEditor().getCaretPosition();
            int hrIndex = this.getEditor().getText().indexOf(':');
            if (caretPos <= hrIndex) {
                mode.set(Mode.HOURS);
            } else {
                mode.set(Mode.MINUTES);
            }
        });

        mode.addListener((obs, oldMode, newMode) -> newMode.select(this));
    }

    private static LocalTime roundTime(LocalTime time) {
        int timeMinutes = time.getMinute();
        int minute = Math.round((float) timeMinutes / 15) * 15;
        return minute == 60 ? time.plusHours(1).withMinute(0) : time.withMinute(minute);
    }

    public ObjectProperty<Mode> modeProperty() {
        return mode;
    }

    public final Mode getMode() {
        return modeProperty().get();
    }

    public final void setMode(Mode mode) {
        modeProperty().set(mode);
    }

    enum Mode {
        HOURS {
            @Override
            LocalTime increment(LocalTime time, int steps) {
                return time.plusHours(steps);
            }

            @Override
            void select(TimeSpinner spinner) {
                int index = spinner.getEditor().getText().indexOf(':');
                spinner.getEditor().selectRange(0, index);
            }
        },
        MINUTES {
            @Override
            LocalTime increment(LocalTime time, int steps) {
                return time.plusMinutes(steps * 15);
            }

            @Override
            void select(TimeSpinner spinner) {
                int index = spinner.getEditor().getText().lastIndexOf(':');
                spinner.getEditor().selectRange(index + 1, spinner.getEditor().getText().length());
            }
        };

        abstract LocalTime increment(LocalTime time, int steps);

        abstract void select(TimeSpinner spinner);

        LocalTime decrement(LocalTime time, int steps) {
            return increment(time, -steps);
        }
    }
}