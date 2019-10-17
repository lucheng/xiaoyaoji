package cn.com.xiaoyaoji.test;

import cn.com.xiaoyaoji.data.DataFactory;
import org.junit.Test;

/**
 * ...
 *
 * Date : 2019-04-17 17:33
 */
public class DeleteDoc {

    @Test
    public void test(){
        DataFactory.instance().deleteDocHistory();
    }
}
