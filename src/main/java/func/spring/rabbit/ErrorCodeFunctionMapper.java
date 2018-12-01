package func.spring.rabbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ErrorCodeFunctionMapper {

    CONTENT_TO_LARGE(311),

    NO_QUEUE(312),

    NO_CONSUMERS(313),

    INVALID_PATH(402),

    ACCESS_REFUSED(403),

    NOT_FOUND(404),

    PRECONDITION_FAILED(406),

    FRAME_ERROR(501),

    SYNTAX_ERROR(502),

    COMMAND_INVALID(503),

    CHANNEL_ERROR(504),

    NOT_ALLOWED(530);




    private int replyCode;

    private List<MethodObject> methodObjectList = new ArrayList<MethodObject>();



    ErrorCodeFunctionMapper(int replyCode){
        this.replyCode = replyCode;
    }

    static void clear(){
        Arrays.stream(values()).forEach(errorCodeFunctionMapper -> errorCodeFunctionMapper.methodObjectList = new ArrayList<>());
    }

    static ErrorCodeFunctionMapper  getErrorEnum(int replyCode){
        return Arrays.stream(values()).filter(errorCodeFunctionMapper -> errorCodeFunctionMapper.replyCode == replyCode).findFirst().orElse(null);
    }

    void setCallBack(MethodObject methodObject){
        this.methodObjectList.add(methodObject);
    }

    void callBackInvoke(){
        this.methodObjectList.stream().forEach(
                MethodObject::invoke
        );
    }






}
