package hbi.core.parsesoap;

import hbi.core.bom.dto.Bom;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultBom {
    private Long result;
    private List<Bom> bom;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Bom> getBom() {
        return bom;
    }

    public void setBom(List<Bom> bom) {
        this.bom = bom;
    }
}
