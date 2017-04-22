package hbi.core.parsesoap;

import java.util.List;

/**
 * Created by public1976 on 2017/4/14.
 */
public class ResultUser {
    private Long result;
    private List<Object> user;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<Object> getUser() {
        return user;
    }

    public void setUser(List<Object> user) {
        this.user = user;
    }
}
