package com.estore.domain;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.estore.domain.User;
import com.estore.util.EstoreUtil;  
  
public class HibernateTest {
    public static void main(String[] args) {  
		Session session = null;    
        Transaction transaction;  
        Configuration con = new Configuration();
        SessionFactory sessionFactory = con.configure().buildSessionFactory();
		
        try {  
            session = sessionFactory.openSession();  
            //��������  
            transaction = session.beginTransaction();  
            User user = new User();  
            Date updatetime = new Date();
            user.setSalt(UUID.randomUUID().toString().substring(0, 6));
            user.setRole("admin");
            user.setUpdatetime(updatetime);
           // user.setState(0);
//            String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
//            user.setHeadUrl(head);
            user.setPassword(EstoreUtil.MD5("hello"+user.getSalt()));
         // 手动封装一个激活码
         	user.setActivecode(UUID.randomUUID().toString());
            user.setUsername("jiyuepeng");   
      
            user.setNickname("brikhoff");  
            user.setEmail("1197280223@qq.com");  
            user.setRole("admin");  
            user.setState(1);
            user.setId(UUID.randomUUID().toString().substring(0, 8));
            user.setActivecode("0ddf2485-fc29-487a-87b2-cbd35d7d3143");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
            Date updatetimes = new Date(df.parse("2018-02-10 14:32:22").getTime()); 
            user.setUpdatetime(updatetimes);
            Product p1 = new Product();
            p1.setId("482b5255-741d-4466-8596-26b68db91dbb");
            p1.setName("电冰箱");
            p1.setCategory("家用电器");
            p1.setPrice(1800);
            p1.setPnum(95);
            p1.setImgurl("/upload/1/7/caab30e4-7f1d-4a0d-a23d-1246840bc1a8.jpg");
            p1.setDescription("中国名牌");
            session.save(p1);
            session.save(user);  
            //�ύ����  
            transaction.commit();  
        }catch(Exception e) {  
            e.printStackTrace();  
            //�ع�����  
            session.getTransaction().rollback();  
        }finally {  
            if (session != null) {  
                if (session.isOpen()) {  
                    //�ر�session  
                    session.close();  
                }  
            }  
        }  
    }  
}  
