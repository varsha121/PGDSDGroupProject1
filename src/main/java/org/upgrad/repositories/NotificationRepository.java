
package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Notification;

import java.util.List;

@Repository
public interface NotificationRepository  extends CrudRepository<Notification, Integer> {

    @Query(nativeQuery = true,value="select * from  where user_id=?1")
    List<Notification> getAllNotifications(int id);

    @Query(nativeQuery = true,value="select * from  where user_id=?1 and read=false ")
    List<Notification> getNewNotifications(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update notification set read = true")
    void markNewNotificationsAsRead();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into notification(user_id,message) values(?1 ,?2)")
    void createNotification(int userId, String s);
}
