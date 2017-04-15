package hbi.core.parsesoap;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultObjs {
    private Long result;
    private List<Object> objs;
    private String descripton;

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Object> getObjs() {
        return objs;
    }

    public void setObjs(List<Object> objs) {
        this.objs = objs;
    }
}
