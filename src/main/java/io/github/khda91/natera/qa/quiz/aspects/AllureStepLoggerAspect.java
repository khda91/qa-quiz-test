package io.github.khda91.natera.qa.quiz.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import okhttp3.Request;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import retrofit2.Response;

@Aspect
public class AllureStepLoggerAspect {

    @AfterReturning(pointcut = "call(* retrofit2.Call.execute())", returning = "retVal")
    public void attachToAllure(JoinPoint joinPoint, Object retVal) throws Throwable {
        Response<?> response = (Response<?>) retVal;
        Request request = response.raw().request();
        ObjectMapper mapper = new ObjectMapper();

        RequestAttachment requestAttachment = new RequestAttachment(request.url().toString(), request.method(),
                request.headers().toString(), request.body() != null ? request.body().toString() : null);
        Allure.addAttachment("Request", "application/json", mapper.writeValueAsString(requestAttachment));

        ResponseAttachment responseAttachment = new ResponseAttachment(response.code(),
                response.body() != null ? response.body().toString() : null,
                response.errorBody() != null ? response.errorBody().string() : null);
        Allure.addAttachment("Response", "application/json", mapper.writeValueAsString(responseAttachment));
    }

    class RequestAttachment {
        String url;
        String method;
        String headers;
        String body;

        public RequestAttachment(String url, String method, String headers, String body) {
            this.url = url;
            this.method = method;
            this.headers = headers;
            this.body = body;
        }

        public String getUrl() {
            return url;
        }

        public String getMethod() {
            return method;
        }

        public String getHeaders() {
            return headers;
        }

        public String getBody() {
            return body;
        }
    }

    class ResponseAttachment {
        int code;
        String body;
        String errorBody;

        public ResponseAttachment(int code, String body, String errorBody) {
            this.code = code;
            this.body = body;
            this.errorBody = errorBody;
        }

        public int getCode() {
            return code;
        }

        public String getBody() {
            return body;
        }

        public String getErrorBody() {
            return errorBody;
        }
    }


}
