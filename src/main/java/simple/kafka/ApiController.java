package simple.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/message")
public class ApiController {

    private final MessageService messageService;

    public ApiController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public String sendMessageToKafka(@RequestBody String message) {
        log.info("pass off  message: {}", message);
        messageService.handleMessage(message);
        return "done";
    }

}
