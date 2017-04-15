package hbi.core.parsesoap;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultDel {
    private Long result;
    private List<Object> del;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Object> getDel() {
        return del;
    }

    public void setDel(List<Object> del) {
        this.del = del;
    }
}
