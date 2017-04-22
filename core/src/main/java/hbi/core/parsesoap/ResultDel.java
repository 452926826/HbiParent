package hbi.core.parsesoap;

import hbi.core.bom.dto.DeletePart;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultDel {
    private Long result;
    private List<DeletePart> del;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<DeletePart> getDel() {
        return del;
    }

    public void setDel(List<DeletePart> del) {
        this.del = del;
    }
}
