package todo;

public class TodoUtil {

    public class toSafeString {

	}

	/**
     * 安全なHTML文字列に変換します。
     * @param str
     * @return
     */
    protected static String toSafeString(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("'", "&#39;");
        str = str.replaceAll("\"", "&quot;");

        return str;
    }
}
