package ua.in.dris4ecoder.model.utilityServices;

/**
 * Created by Alex Korneyko on 07.11.2016 17:28.
 */
public class FormsDataValidator {

    public static String CommaToDot(String value) {

        StringBuilder result = new StringBuilder();

        for (char c: value.toCharArray()) {
            if (c == ',') {
                c = '.';
            }
            result.append(c);
        }

        return result.toString();
    }
}
