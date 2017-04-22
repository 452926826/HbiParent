package hbi.core.parsesoap;

import hbi.core.bom.dto.Bom;
import hbi.core.bom.dto.BomSync;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultBomSync {
    private Long result;
    private List<BomSync> bom;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<BomSync> getBom() {
        return bom;
    }

    public void setBom(List<BomSync> bom) {
        this.bom = bom;
    }
}
