package hbi.core.parsesoap;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultBom {
    private Long result;
    private List<Object> bom;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Object> getBom() {
        return bom;
    }

    public void setBom(List<Object> bom) {
        this.bom = bom;
    }
}
