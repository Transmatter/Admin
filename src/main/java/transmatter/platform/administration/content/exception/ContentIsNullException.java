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
        super("News is null");
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

        extension.put("error_code", 000);
        extension.put("message", "News is null");
        extension.put("displayMessage", "News is null");
        return extension;
    }
}
