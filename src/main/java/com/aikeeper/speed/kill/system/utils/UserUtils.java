package com.aikeeper.speed.kill.system.utils;

import com.aikeeper.speed.kill.system.domain.info.SpeedKillUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/29 15:15
 * @Version V1.0
 **/
public class UserUtils {

    private static void createUser(int count) throws Exception{
        List<SpeedKillUser> users = new ArrayList<SpeedKillUser>(count);
        //生成用户
        for(int i=0;i<count;i++) {
            SpeedKillUser user = new SpeedKillUser();
            user.setId(13000000000L+i);
            user.setLoginCount(1);
            user.setNickName("user"+i);
            user.setRegisterDate(new Date());
            user.setSalt("1a2b3c4d5e6f7g");
            user.setPassword(Md5Utils.inputPassToDbPassword("123456", user.getSalt()));
            users.add(user);
        }

        		//插入数据库
//		Connection conn = DBUtil.getConn();
//		String sql = "insert into speed_kill_user(login_count, nick_name, register_date, salt, password, id)values(?,?,?,?,?,?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		for(int i=0;i<users.size();i++) {
//            SpeedKillUser user = users.get(i);
//			pstmt.setInt(1, user.getLoginCount());
//			pstmt.setString(2, user.getNickName());
//			pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
//			pstmt.setString(4, user.getSalt());
//			pstmt.setString(5, user.getPassword());
//			pstmt.setLong(6, user.getId());
//			pstmt.addBatch();
//		}
//		pstmt.executeBatch();
//		pstmt.close();
//		conn.close();
//		System.out.println("insert to db");

        //登录，生成token
        String urlString = "http://localhost:8080/login/doLogin";
        File file = new File("F:/JMeterTest/tokens.txt");
        if(file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++) {
            SpeedKillUser user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection)url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+Md5Utils.inputPassToFormPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0 ,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            JSONObject jo = JSON.parseObject(response);
            String token = jo.getString("data");
            System.out.println("create token : " + user.getId());

            String row = user.getId()+","+token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");
    }

    public static void main(String[] args)throws Exception {
        createUser(5000);
    }

}
