package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getAll();

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) " +
            "VALUES (#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Note note);

    @Update("UPDATE NOTES " +
            "SET notetitle = #{title}, notedescription = #{description} " +
            "WHERE noteid = #{id}")
    int update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
    void delete(@Param("id") int id);
}
