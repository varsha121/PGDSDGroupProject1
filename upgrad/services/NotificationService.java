
package org.upgrad.services;

import org.upgrad.models.Notification;

import java.util.List;

public interface NotificationService {


    List<Notification> getAllNotifications(int id);
    List<Notification> getNewNotifications(int id);

    void createNotification(int userId, String s);
}

