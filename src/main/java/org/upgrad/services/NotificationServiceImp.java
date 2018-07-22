
package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import org.upgrad.repositories.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService{
    private final NotificationRepository notificationRepository;

    public NotificationServiceImp(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotifications(int id){
        notificationRepository.markNewNotificationsAsRead();
        return  this.notificationRepository.getAllNotifications(id);

    }

    @Override
    public List<Notification> getNewNotifications(int id) {
        notificationRepository.markNewNotificationsAsRead();
        return this.notificationRepository.getNewNotifications(id);
    }

    @Override
    public void createNotification(int userId, String s) {
        notificationRepository.createNotification(userId,s);
    }
}