package org.dominokit.domino.ui.forms;

import elemental2.dom.HTMLInputElement;
import org.dominokit.domino.ui.forms.validations.InputAutoValidator;
import org.jboss.elemento.Elements;

import static java.util.Objects.nonNull;

public class TextBox extends AbstractValueBox<TextBox, HTMLInputElement, String> {

    private static final String TEXT = "text";
    private boolean emptyAsNull;

    public TextBox() {
        this(TEXT, "");
    }

    public TextBox(String label) {
        this(TEXT, label);
    }

    public TextBox(String type, String label) {
        super(type, label);
    }

    public static TextBox create() {
        return new TextBox();
    }

    public static TextBox create(String label) {
        return new TextBox(label);
    }

    public static TextBox password(String label) {
        return new TextBox("password", label);
    }

    public static TextBox password() {
        return new TextBox("password", "");
    }

    @Override
    protected HTMLInputElement createInputElement(String type) {
        return Elements.input(type).element();
    }

    @Override
    protected void clearValue() {
        value("");
    }

    @Override
    protected void doSetValue(String value) {
        if (nonNull(value)) {
            getInputElement().element().value = value;
        } else {
            getInputElement().element().value = "";
        }
    }

    @Override
    public String getValue() {
        String value = getInputElement().element().value;
        if (value.isEmpty() && isEmptyAsNull()) {
            return null;
        }
        return value;
    }

    public TextBox setType(String type) {
        getInputElement().element().type = type;
        return this;
    }

    @Override
    public String getStringValue() {
        return getValue();
    }

    public TextBox setEmptyAsNull(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
        return this;
    }

    public boolean isEmptyAsNull() {
        return emptyAsNull;
    }

    @Override
    protected AutoValidator createAutoValidator(AutoValidate autoValidate) {
        return new InputAutoValidator<>(getInputElement(), autoValidate);
    }
}
