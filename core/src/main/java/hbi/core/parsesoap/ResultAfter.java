package hbi.core.parsesoap;

import hbi.core.bom.dto.Revise;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultAfter {
    private Long result;
    private List<Revise> after;
    private List<ResultDes> des;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Revise> getAfter() {
        return after;
    }

    public void setAfter(List<Revise> after) {
        this.after = after;
    }

    public List<ResultDes> getDes() {
        return des;
    }

    public void setDes(List<ResultDes> des) {
        this.des = des;
    }
}
