package com.office.notfound.event.model.dao;

import com.office.notfound.event.model.dto.EventDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    List<EventDTO> selectAllEventList();

    EventDTO selectEventByCode(int code);
}
