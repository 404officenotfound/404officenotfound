package com.office.notfound.event.model.service;

import com.office.notfound.event.model.dto.EventDTO;
import com.office.notfound.event.model.dao.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }


    public List<EventDTO> selectAllEventList() {
        return eventMapper.selectAllEventList();
    }

    public EventDTO selectEventByCode(int code) {

        return eventMapper.selectEventByCode(code);
    }
}
