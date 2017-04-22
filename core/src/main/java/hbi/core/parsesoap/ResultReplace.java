package hbi.core.parsesoap;

import hbi.core.bom.dto.ReplacePart;

import java.util.List;

/**
 * Created by public1976 on 2017/4/18.
 */
public class ResultReplace {
    private Long result;
    private List<ReplacePart> replace;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<ReplacePart> getReplace() {
        return replace;
    }

    public void setReplace(List<ReplacePart> replace) {
        this.replace = replace;
    }
}
