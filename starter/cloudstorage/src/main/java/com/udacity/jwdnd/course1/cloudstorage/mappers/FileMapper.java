package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    List<File> getAll();

    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    List<File> getById(int id);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{name}, #{contentType}, #{size}, #{userId}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    void delete(int id);
}
