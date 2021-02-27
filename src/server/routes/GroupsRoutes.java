package server.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.requestHandlers.GroupMemberRequestHandler;
import server.requestHandlers.GroupRequestHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 *@author: Didier Munezero,
 *This is a class that now where to direct a given request of groups to a responsive direct handler.
 */
public class GroupsRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private ChatServer server;
    private String request;
    public GroupsRoutes(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server, String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public GroupsRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public void Main() throws IOException, SQLException {
        if(request.equals("groups/")){
            new GroupRequestHandler().HandleGetAllGroups(data,writer,objectMapper);
        }
        else if(request.equals("groups/new")){
            new GroupRequestHandler().HandleCreateGroup(data,writer,objectMapper);
        }
        else if(request.equals("groups/search")){
            new GroupRequestHandler().HandlerSearchGroup(data,writer,objectMapper);
        }
        else if(request.equals("groups/add")){
            new GroupMemberRequestHandler().handleCreateGroupMembers(data,writer,objectMapper);
        }
        else if(request.equals("groups/remove")){
            new GroupRequestHandler().HandleDeleteGroup(data,writer,objectMapper);
        }
        else if(request.equals("groups/update")){
            new GroupRequestHandler().HandleGroupUpdate(data,writer,objectMapper);
        }
        else if(request.equals("groups/profile")){
            new GroupRequestHandler().HandleGetGroup(data,writer,objectMapper);
        }
        else if(request.equals("groups/members")){
            new GroupMemberRequestHandler().handlerGetGroupMembers(data,writer,objectMapper);
        }
        else if (request.equals("groups/members/create")){
            new GroupMemberRequestHandler().handleCreateGroupMembers(data,writer,objectMapper);
        }
        else if (request.equals("groups/members/delete")){
            new GroupMemberRequestHandler().handleDeleteGroupMember(data,writer,objectMapper);
        }

    }
}
