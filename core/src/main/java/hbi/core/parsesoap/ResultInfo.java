package hbi.core.parsesoap;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultInfo {
    private Long result;
    private List<Object> part;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Object> getPart() {
        return part;
    }

    public void setPart(List<Object> part) {
        this.part = part;
    }
}
