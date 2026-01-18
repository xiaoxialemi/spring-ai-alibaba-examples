package com.alibaba.cloud.ai.examples.a2a;

import com.alibaba.cloud.ai.graph.agent.Agent;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphStateException;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Java A2A Server Application
 * 
 * 简单的 Java A2A 服务，用于测试 A2A 协议调用。
 */
@SpringBootApplication
public class JavaA2aServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaA2aServerApplication.class, args);
    }

    @Configuration
    public static class AgentConfiguration {

        private static final String SYSTEM_PROMPT = """
            你是一个简单的测试 Agent。你的任务是：
            1. 收到用户消息后，用中文简单回复
            2. 如果用户问"你是谁"，回答"我是 Java Echo Agent，一个用于测试 A2A 协议的简单 Agent"
            3. 其他问题尽量简短回答
            """;

        @Bean
        @Primary
        public Agent rootAgent(ChatModel chatModel) throws GraphStateException {
            return ReactAgent.builder()
                    .name("JavaEchoAgent")
                    .description("一个简单的 Java Echo Agent，用于测试 A2A 协议调用")
                    .model(chatModel)
                    .instruction(SYSTEM_PROMPT)
                    .outputKey("messages")
                    .build();
        }
    }
}
