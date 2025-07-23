package com.example.javafx.flight;

import com.tobiasdiez.easybind.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;

import java.time.*;

public class FlightBookerEasyBind extends FlightBookerBase {

    protected void bind() {
        BooleanBinding isOneWayFlight = flightType.valueProperty().isEqualTo(FlightType.ONE_WAY_FLIGHT);

        EasyBinding<Boolean> isNotBookable = EasyBind.combine(isOneWayFlight, startDate.textProperty(), returnDate.textProperty(), (oneWayFlight, startDate, returnDate) -> {
            LocalDate localStartDate = stringToDate(startDate);
            if (oneWayFlight) {
                return localStartDate == null;
            } else {
                LocalDate localReturnDate = stringToDate(returnDate);
                return localStartDate == null || localReturnDate == null || localStartDate.compareTo(localReturnDate) > 0;
            }
        });

        returnDate.disableProperty().bind( isOneWayFlight );
        startDate.styleProperty().bind( errorBinding(startDate.textProperty()) );
        returnDate.styleProperty().bind( errorBinding(returnDate.textProperty()) );
        book.disableProperty().bind( isNotBookable );
    }

    private static EasyBinding<String> errorBinding(StringProperty stringProperty) {
        return EasyBind.map(stringProperty, s -> (stringToDate(s) != null) ? STYLE_NORMAL : STYLE_ERROR);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
