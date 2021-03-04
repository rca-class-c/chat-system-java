package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.dataDecoders.PackageDecoder;
import server.models.Package;
import server.models.Response;
import server.services.PackageService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class PackageRequestHandler {

    public void HandleSavePackage(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException{
        Package returned = new PackageService().savePackage(new PackageDecoder(data).createPackageDecoder());

        if(returned == null){
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }else{
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
    public void HandleViewPackageInfo(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException{
        Package packages = new PackageService().getPackageInfoById(new PackageDecoder(data).getPackageInfoDecoder());

        if(packages == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(packages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
    public void HandleViewPackages(PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<Package> packageList = new PackageService().getAllPackages();

        if(packageList == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(packageList,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleSearchPackage(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException{
        List<Package> messages = new PackageService().searchPackage(new PackageDecoder(data).searchPackageDecoder());

        if(messages == null){

            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(messages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleUpdatePackage(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException{
        boolean returned = new PackageService().updatePackage(new PackageDecoder(data).updatePackageDecoder());
        if(!returned){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleDeletePackage(String data, PrintWriter writer,ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        boolean returned = new PackageService().deletePackage(new PackageDecoder(data).deletePackageDecoder());
        if(!returned){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
}
