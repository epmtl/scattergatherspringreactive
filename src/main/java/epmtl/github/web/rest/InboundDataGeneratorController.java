package epmtl.github.web.rest;

import epmtl.github.resource.inbound.Data1;
import epmtl.github.resource.inbound.Data2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SuppressWarnings("unused")
@RestController
public class InboundDataGeneratorController {

    private int indexData1 = 0;
    private int indexData2 = 0;
    private boolean emitter2Alive = false;
    private boolean emitter1Alive = false;


    @CrossOrigin
    @RequestMapping("/data1")
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseBodyEmitter getData1(){
        final SseEmitter emitter = new SseEmitter();
        emitter1Alive = true;
        emitter.onCompletion(() -> emitter1Alive=false);
        emitter.onTimeout(() -> emitter1Alive=false);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
                    while (true) {
                        if (emitter1Alive) {
                            try {
                                    emitter.send(new Data1(indexData1++,
                                            "Field1_" + String.valueOf(new Random().nextFloat()),
                                            "Field2_" + String.valueOf(new Random().nextFloat())
                                    ));
                                Thread.sleep(500);
                            } catch (Exception e) {
                                e.printStackTrace();
                                emitter.completeWithError(e);
                                return;
                            }
                        }
                    }
                }
        );
        return emitter;
    }

    @CrossOrigin
    @RequestMapping("/data2")
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseBodyEmitter getData2(){
        final SseEmitter emitter = new SseEmitter();
        emitter2Alive = true;
        emitter.onCompletion(() -> emitter2Alive=false);
        emitter.onTimeout(() -> emitter2Alive=false);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            while (true) {
                if (emitter2Alive) {
                    try {
                        emitter.send(new Data2(indexData2++,
                                "Field1_" + String.valueOf(new Random().nextFloat()),
                                "Field2_" + String.valueOf(new Random().nextFloat())
                            ));
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.completeWithError(e);
                        return;
                    }
                }
            }
        });

        return emitter;
    }

}
