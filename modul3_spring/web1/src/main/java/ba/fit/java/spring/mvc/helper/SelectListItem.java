package ba.fit.java.spring.mvc.helper;

public class SelectListItem {
    public String text;
    public String value;

    public SelectListItem(Object value, Object text) {
        this.text = text.toString();
        this.value = value.toString();
    }
}
