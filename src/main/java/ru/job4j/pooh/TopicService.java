package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {

    ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topic =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "204 No Content");
        ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> consumersQueues = topic.get(req.mode());
        if (consumersQueues != null && "POST".equals(req.httpRequestType())) {
            consumersQueues.forEach((key, value) -> value.add(req.text()));
            result = new Resp(req.text(), "201 Created");
        } else if ("GET".equals(req.httpRequestType())) {
            topic.putIfAbsent(req.mode(), new ConcurrentHashMap<>());
            topic.get(req.mode()).putIfAbsent(req.id(), new ConcurrentLinkedQueue<>());
            String getString = topic.get(req.mode()).get(req.id()).poll();
            if (getString != null) {
                result = new Resp(getString, "200 OK");
            }
        }
        return result;
    }
}
