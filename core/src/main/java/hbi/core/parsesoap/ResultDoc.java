package hbi.core.parsesoap;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultDoc {
    private Long result;
    private List<Object> doc;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Object> getDoc() {
        return doc;
    }

    public void setDoc(List<Object> doc) {
        this.doc = doc;
    }
}
