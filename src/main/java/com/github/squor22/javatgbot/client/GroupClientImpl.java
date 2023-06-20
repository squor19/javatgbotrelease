package com.github.squor22.javatgbot.client;

import com.github.squor22.javatgbot.dto.*;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupClientImpl implements GroupClient {
    private final String javarushApiGroupPath;

    public GroupClientImpl(@Value("${javarush.api.path}") String javaTGBotApi) {
        this.javarushApiGroupPath = javaTGBotApi + "/groups";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                })
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupsCountRequestArgs countRequestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", javarushApiGroupPath))
                        .queryString(countRequestArgs.populateQueries())
                        .asString()
                        .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", javarushApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }
}
