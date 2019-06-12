package com.notification.service;

import com.notification.exception.NotificationException;

public interface NotificationService<T> {
     void send(T t) throws NotificationException;
}
