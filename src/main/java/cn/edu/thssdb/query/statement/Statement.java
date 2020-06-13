package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Manager;

import java.io.IOException;

public abstract class Statement {
    private String message; // 语法分析时错误信息存储
    private boolean valid = true; // 语法分析时是否通过

    /**
     * sql 指令执行程序接口
     * @param manager 数据库整体管理者manager
     * */
    public abstract Result execute(Manager manager) throws RuntimeException;

    /**
     * statement分析过后设置pass标签, IO错误直接throw RuntimeException, 其他错误设置valid为false
     *
     * 从安全、报错信息准确的角度看, 不需要用多个exception.
     * */
    public void setValid(boolean valid){
        this.valid = valid;
    }

    public boolean isValid(){
        return this.valid;
    }

    /**
     * 如果分析不通过,则设置message
     * */
    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
