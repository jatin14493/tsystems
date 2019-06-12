package com.notification.subscriber;

import com.notification.queue.QueueStore;
import com.notification.request.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSubscriber {

    Thread messageSubscriberThread = new Thread(new MessageSubscriber());
    Thread mailSubscriber = new Thread(new MailSubscriber());

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        log.info("application started");
        messageSubscriberThread.start();
        mailSubscriber.start();
    }

    public class MessageSubscriber implements Runnable {
        /**
         * this Blocking Queue so we don't need synchronization manually
         */
        @Override
        public void run() {
            while (true) {
                Notification.Sms sms = QueueStore.messageBlockingDeque.remove();
                log.info("Sms processed successfully :" + sms);
            }
        }
    }

    public class MailSubscriber implements Runnable {

        @Override
        public void run() {
            /**
             * this is Blocking Queue so we don't need to synchronization manually
             */
            while (true) {
                Notification.Mail mail = QueueStore.mailMessagingQueue.remove();
                log.info("mail processed successfully :" + mail);
            }
        }
    }
}
