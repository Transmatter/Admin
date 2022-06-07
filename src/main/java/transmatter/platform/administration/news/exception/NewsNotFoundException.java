package transmatter.platform.administration.news.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsNotFoundException  extends RuntimeException implements GraphQLError {
    String id;

    public NewsNotFoundException(String id){
        super(String.format("News id %d is not found",id));
        this.id = id;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ExecutionAborted;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> extension = new HashMap<>();

        extension.put("error_code", 320);
        extension.put("message", String.format("The News id: %d that you looking for, does not exists",id));
        extension.put("displayMessage", String.format("no News id: %d that you looking for",id));
        return extension;
    }
}
