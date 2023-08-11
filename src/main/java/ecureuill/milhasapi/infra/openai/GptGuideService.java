package ecureuill.milhasapi.infra.openai;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

@Service
public class GptGuideService {
    
    public String generate(String destination){
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_KEY"));

        List<ChatMessage> messages = new ArrayList<>();

        messages.add(new ChatMessage(ChatMessageRole.USER.value(), "I want you to act as a travel guide. I will write you a location and you will write a 200 character text about this location, it's highlights and unique experiences. My first request sugestion is " + destination));

        ChatCompletionRequest completion = ChatCompletionRequest
            .builder()
            .model("gpt-3.5-turbo")
            .messages(messages)
            .maxTokens(200)
            .build();

        ChatMessage response = service.createChatCompletion(completion).getChoices().get(0).getMessage();

        return response.getContent();
    }
}
