package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {

    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> store = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "204 No Content");
        ConcurrentLinkedQueue<String> params = store.get(req.getSourceName());
        if ("POST".equals(req.httpRequestType())) {
            store.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            store.get(req.getSourceName()).add(req.getParam());
            result =  new Resp(req.getParam(), "201 Created");
        } else if (params != null && "GET".equals(req.httpRequestType())) {
            String getString = store.get(req.getSourceName()).poll();
            if (getString != null) {
                result = new Resp(getString, "200 OK");
            }
        }
        return result;
    }
}
