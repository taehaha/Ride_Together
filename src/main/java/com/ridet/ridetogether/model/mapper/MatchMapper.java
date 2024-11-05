package com.ridet.ridetogether.model.mapper;

import com.ridet.ridetogether.dto.match.Match;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchMapper {
    public int add(Match match);
    public int delete(int id);
    public Match findMatchById(int id);
    public Match findMatchByUserId(int id);
}