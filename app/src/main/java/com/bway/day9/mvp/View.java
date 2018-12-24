package com.bway.day9.mvp;

import com.bway.day9.bean.ShowBean;

/**
 * Created by 择木 on 2018/12/7.
 */

public interface View {
    void  getShow(ShowBean showBean);
    void   onFailed(Exception e);
}
