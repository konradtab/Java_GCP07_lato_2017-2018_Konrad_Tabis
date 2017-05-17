package application;

import javafx.util.StringConverter;

public class CustomStringConverter extends StringConverter<Number>
{
    @Override
    public String toString(Number object)
    {
        if (object.doubleValue()%object.intValue() == 0.0)
            return (object.intValue()) + "";
        else
            return "";
    }

    @Override
    public Number fromString(String string)
    {
        return null;
    }
}
