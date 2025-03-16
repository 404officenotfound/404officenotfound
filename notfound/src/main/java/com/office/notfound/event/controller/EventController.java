package com.office.notfound.event.controller;

import com.office.notfound.event.model.dto.EventDTO;
import com.office.notfound.event.model.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

        private final EventService eventService;

        public EventController (EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public String selectAllEventList( Model model) {

        List<EventDTO> eventList = eventService.selectAllEventList();

        model.addAttribute("eventList", eventList);

        return "event/event";
    }

    @GetMapping("/detail/{code}")
    public String selectEventDetail(@PathVariable("code") int code, Model model) {

        EventDTO event = eventService.selectEventByCode(code);

        model.addAttribute("event", event);
        System.out.println("event상세조회-----> " + event);

        return "event/eventDetail";
    }
}

