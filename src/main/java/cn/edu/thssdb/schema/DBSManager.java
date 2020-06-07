package cn.edu.thssdb.schema;
import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 实现连接Manager的sessionId管理
 * 只有一个manager*/

public class DBSManager {
    /**存储用户信息的hash
     * 存储当前连接情况*/
    private static HashMap<String, String> userInfo;
    private ArrayList<Integer> sessionId = new ArrayList<>();
    private Manager manager;
    /**加载用户信息*/
    private static void loadUserInfo(){
        userInfo = new HashMap<>();
        try {
            File file = new File(Global.infoPath); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            while (line != null) {
                String[] info = line.split(" ");
                userInfo.put(info[0], info[1]);
                line = br.readLine(); // 一次读入一行数据
            }
            reader.close();
        }catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
            System.out.println("Manager load user information error.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DBSManager() {
        manager = Manager.getInstance();
    }

    public static DBSManager getInstance() {
        loadUserInfo();
        return DBSManagerHolder.INSTANCE;
    }

    private static class DBSManagerHolder{
        private static final DBSManager INSTANCE = new DBSManager();
        private DBSManagerHolder(){

        }
    }

    public Manager getManager(){
        return manager;
    }

    /**
     * 检查username, password是否正确*/
    public int login(String username, String password){
        if (userInfo.containsKey(username) && userInfo.get(username).equals(password)) {
            // 随机生成id
            int id = 1000 + ((int) (new Random().nextFloat() * (1000)));
            while(sessionId.contains(id)) {
                id = 1000 + ((int) (new Random().nextFloat() * (1000)));
            }
            sessionId.add(id);
            if(manager == null){    // 在连接的时候创建
                manager = Manager.getInstance();
            }
            return id;
        }else {
            return -1;
        }
    }

    /**
     * 检查是否为自身的sessionId*/
    public boolean checkSessionId(int id){
        return sessionId.contains(id);
    }

    public boolean logOut(int id) throws IOException{
        if (checkSessionId(id)) {
            sessionId.remove(new Integer(id));
            if(sessionId.isEmpty()) // 为空, 则将manager释放
            {
                manager.quit();
                manager = null;
            }
            return true;
        } else {
            return false;
        }
    }
}
