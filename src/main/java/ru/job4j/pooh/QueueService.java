package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {

    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "204 No Content");
        ConcurrentLinkedQueue<String> texts = queue.get(req.mode());
        if (req.httpRequestType().equals("POST")) {
            queue.putIfAbsent(req.mode(), new ConcurrentLinkedQueue<>());
            queue.get(req.mode()).add(req.text());
            result =  new Resp(req.text(), "201 Created");
        } else if (texts != null && req.httpRequestType().equals("GET")) {
            String getString = queue.get(req.mode()).poll();
            if (getString != null) {
                result = new Resp(getString, "200 OK");
            }
        }
        return result;
    }
}
