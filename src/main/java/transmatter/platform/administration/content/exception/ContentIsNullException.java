package transmatter.platform.administration.content.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentIsNullException extends RuntimeException implements GraphQLError {
    String id;

    public ContentIsNullException(String id){
        super("Content is null");
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

        extension.put("error_code", 300);
        extension.put("message", "Content is null");
        extension.put("displayMessage", "Content is null");
        return extension;
    }
}
