package com.springTharupama.emplayee_service.servide.impl;

import feign.RequestInterceptor;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTracingConfig {
    @Bean
    public RequestInterceptor tracingRequestInterceptor(Tracer tracer) {
        return template -> {
            Span span = tracer.currentSpan();
            if (span != null) {
                var context = span.context();
                String sampled = Boolean.TRUE.equals(context.sampled()) ? "01" : "00";
                template.header("traceparent",
                        "00-" + context.traceId() + "-" + context.spanId() + "-" + sampled);
            }
        };
    }
}
