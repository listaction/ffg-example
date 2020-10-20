package simple.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ForkJoinPool;

@Slf4j
@Service
public class MessageService {

    private final static ForkJoinPool pool = new ForkJoinPool(4);
    private final static String TOPIC_NAME = "test";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void handleMessage(String message){
        pool.execute(() -> processMessage(message));
    }

    private void processMessage(String message) {
        log.info("processing message: {}", message);

        //here do something like this or put it inside something like an async code block (CompletableFuture.supplyAsync)
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
            }

            @Override
            public void onFailure(Throwable e) {
                log.error("unable to send message", e);
            }
        });
    }

}
