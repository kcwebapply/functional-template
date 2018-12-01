package func.spring.rabbit;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class FunctionalTemplate extends RabbitTemplate {


    private final List<MethodObject> ackMethodList = new ArrayList<MethodObject>();
    private final List<MethodObject> notAckMethodList = new ArrayList<MethodObject>();

    public FunctionalTemplate(String hostname){
        ErrorCodeFunctionMapper.clear();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname);
        connectionFactory.setPublisherConfirms(true);
        this.setConnectionFactory(connectionFactory);
        final ConfirmCallback confirmCallback = (CorrelationData correlationData, boolean ack, String cause)  -> {
            if(!ack) {
                notAckMethodList.stream().forEach(MethodObject::invoke);
            }else {
                ackMethodList.stream().forEach(MethodObject::invoke);
            }
        };
        final RabbitTemplate.ReturnCallback returnCallback = (org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) ->{
            ErrorCodeFunctionMapper.getErrorEnum(replyCode).callBackInvoke();
        };
        this.setConfirmCallback(confirmCallback);
        this.setReturnCallback(returnCallback);
        this.setMandatory(true);
    }

    public FunctionalTemplate(){
        ErrorCodeFunctionMapper.clear();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setPublisherConfirms(true);
        this.setConnectionFactory(connectionFactory);
        final ConfirmCallback confirmCallback = (CorrelationData correlationData, boolean ack, String cause)  -> {
            if(!ack) {
                notAckMethodList.stream().forEach(MethodObject::invoke);
            }else {
                ackMethodList.stream().forEach(MethodObject::invoke);
            }
        };
        final RabbitTemplate.ReturnCallback returnCallback = (org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) ->{
            ErrorCodeFunctionMapper.getErrorEnum(replyCode).callBackInvoke();
        };
        this.setConfirmCallback(confirmCallback);
        this.setReturnCallback(returnCallback);
        this.setMandatory(true);
    }

    public void setOnAckMethod(Object instance, Method method, Object ... args ) {
        ackMethodList.add(methodObject(instance,method,args));
    }

    public void setNoAckedMethod(Object instance, Method method, Object ... args){
        notAckMethodList.add(methodObject(instance,method,args));
    }

    public void setNoQueueErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.NO_QUEUE.setCallBack(methodObject(instance,method,args));
    }

    public void setContentToLargeErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.CONTENT_TO_LARGE.setCallBack(methodObject(instance,method,args));
    }

    public void setNoConsumersErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.NO_CONSUMERS.setCallBack(methodObject(instance,method,args));
    }

    public void setInvalidPatheErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.INVALID_PATH.setCallBack(methodObject(instance,method,args));
    }

    public void setAccessRefusedErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.ACCESS_REFUSED.setCallBack(methodObject(instance,method,args));
    }

    public void setNotFoundErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.NOT_FOUND.setCallBack(methodObject(instance,method,args));
    }

    public void setPreconditionFailedErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.PRECONDITION_FAILED.setCallBack(methodObject(instance,method,args));
    }

    public void setFrameErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.FRAME_ERROR.setCallBack(methodObject(instance,method,args));
    }

    public void setSyntaxErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.SYNTAX_ERROR.setCallBack(methodObject(instance,method,args));
    }

    public void setCommandInvalidErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.COMMAND_INVALID.setCallBack(methodObject(instance,method,args));
    }

    public void setChannelErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.CHANNEL_ERROR.setCallBack(methodObject(instance,method,args));
    }

    public void setNotAllowedErrorMethod(Object instance,Method method,Object ... args ){
        ErrorCodeFunctionMapper.NOT_ALLOWED.setCallBack(methodObject(instance,method,args));
    }


    private static MethodObject methodObject(Object instance , Method method, Object ... args){
        return new MethodObject(instance,method,args);
    }
}

