package server.requestHandlers;

import server.dataDecoders.PackageDecoder;

public class PackageRequestHandler {

    public void HandleSavePackage(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException{
        server.models.Package returned = new server.services.PackageService().savePackage(new PackageDecoder(data).createPackageDecoder());

        if(returned == null){
            server.models.Response response = new server.models.Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }else{
            server.models.Response response = new server.models.Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
    public void HandleViewPackageInfo(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException{
        server.models.Package packages = new server.services.PackageService().getPackageInfoById(new PackageDecoder(data).getPackageInfoDecoder());

        if(packages == null){
            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(packages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
    public void HandleViewPackages(java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
        java.util.List<server.models.Package> packageList = new server.services.PackageService().getAllPackages();

        if(packageList == null){
            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(packageList,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleSearchPackage(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException{
        java.util.List<server.models.Package> messages = new server.services.PackageService().searchPackage(new PackageDecoder(data).searchPackageDecoder());

        if(messages == null){

            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(messages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleUpdatePackage(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException{
        boolean returned = new server.services.PackageService().updatePackage(new PackageDecoder(data).updatePackageDecoder());
        if(!returned){
            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleDeletePackage(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
        boolean returned = new server.services.PackageService().deletePackage(new PackageDecoder(data).deletePackageDecoder());
        if(!returned){
            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
}
