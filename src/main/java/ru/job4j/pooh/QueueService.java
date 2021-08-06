package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {

    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "204 No Content");
        ConcurrentLinkedQueue<String> texts = queue.get(req.mode());
        if ("POST".equals(req.httpRequestType())) {
            queue.putIfAbsent(req.mode(), new ConcurrentLinkedQueue<>());
            queue.get(req.mode()).add(req.text());
            result =  new Resp(req.text(), "201 Created");
        } else if (texts != null && "GET".equals(req.httpRequestType())) {
            String getString = queue.get(req.mode()).poll();
            if (getString != null) {
                result = new Resp(getString, "200 OK");
            }
        }
        return result;
    }
}
